package de.dfki.mlt.rudimant.agent;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.Rdf;
import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HfcDbApiHandler;
import de.dfki.lt.hfc.db.server.StreamingClient;
import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.nlg.Pair;

/**
 *
 * @author chbu02, Bernd Kiefer
 */
public abstract class Agent extends DataComparator implements StreamingClient {

  public static final Logger logger = LoggerFactory.getLogger(Agent.class);

  protected String executedLast = null;

  protected long lastDAprocessed = -1;

  protected String _language;

  // TODO: that's not nice. The mood is transported in the Intention, and
  // i set this field in the intention manager.
  public double proposedRobotMood = 0;

  protected Random random = new Random(System.currentTimeMillis());

  public abstract class Proposal implements Runnable {
    public String name;
  }

  /** The object that is responsible for outgoing communication */
  protected CommunicationHub _hub;

  /** A class that cares about ASR events and interpretation */
  protected AsrTts asr;

  /** Is new data in the repository */
  private boolean newData = false;

  /** The DAs I emitted, newest first */
  protected Deque<DialogueAct> myLastDAs;

  /** The DAs I received, newest first */
  private Deque<DialogueAct> lastDAs;

  private Timeouts timeouts = new Timeouts();

  /** Proposals to be executed in the next round, coming from fired timeouts
   *  or other triggers, like finished behaviours
   */
  protected Deque<Proposal> proposalsToExecute;

  /** The set of all proposals generated in one (fixpoint) run of the rules */
  public Map<String, Proposal> pendingProposals = new HashMap<>();

  /** Are we waiting for a proposal to be selected? In this case, put all
   *  incoming events into the event queue
   */
  protected boolean proposalsSent;

  /** The next two variable determine which rudi rules are logged */
  public Set<String> rulesToLog = new HashSet<>();
  public boolean logAllRules = false;


  /** Send something out to the world */
  protected void sendBehaviour(Object obj) {
    // TODO implement it, possibly with a Listener.
  }

  // TODO: we need sth like this to use the dialogue history; is this the right way?
  protected Deque<DialogueAct> getLastDAs(){
    return lastDAs;
  }

  protected void reset() {
    timeouts.clear();
    myLastDAs = new LinkedList<>();
    lastDAs = new LinkedList<>();
    proposalsToExecute = new LinkedList<>();
    proposalsSent = false;
  }

  public static String putTogether(char sep, String... strings) {
    if (strings.length == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(strings[0]);
    for (int i = 1; i < strings.length; ++i) {
      sb.append(sep).append(strings[i]);
    }
    return sb.toString();
  }

  public static String putTogether(char sep, Iterable<String> strings) {
    Iterator<String> it = strings.iterator();
    if (!it.hasNext()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(it.next());
    while (it.hasNext()) {
      sb.append(sep).append(it.next());
    }
    return sb.toString();
  }

  public static String[] tearApart(char sep, String s) {
    String pat = ((".*".indexOf(sep) >= 0) ? "\\" : "") + sep;
    String result[] = s.split(pat, -1);
    return result;
  }

  // Constructors ************************************************************
  // public Agent() {}

  // **********************************************************************
  // StreamingClient init() & compute() function to register changes in DB
  // **********************************************************************
  @Override
  public void init(HfcDbApiHandler handler) {
    // nothing to do
  }

  @Override
  public void compute() {
    newData();
  }


  // **********************************************************************
  // DialogueAct functions
  // **********************************************************************

  /** Generate DialogueAct from a raw speech act representation */
  public DialogueAct addToMyDA(DialogueAct da) {
    myLastDAs.addFirst(da);
    newData();
    return da;
  }

  /** Generate text and motion from a raw speech act representation and send it
   * to the Behaviourmanager
   */
  public DialogueAct emitDA(int delay, DialogueAct da) {
    Pair<String, String> toSay = asr.generate(da.getDag());
    _hub.sendBehaviour(new Behaviour(toSay.second, toSay.first, delay));
    return addToMyDA(da);
  }

  /** Generate text and motion from a raw speech act representation and send it
   * to the Behaviourmanager
   */
  public DialogueAct emitDA(DialogueAct da) {
    return emitDA(Behaviour.DEFAULT_DELAY, da);
  }

  public DialogueAct myLastDA() {
    return myLastDAs.peekFirst();
  }

  /** Return the index of the last speech act equal or more specific than the
   *  given one
   */
  protected int lastOccurence(DialogueAct da, Iterable<DialogueAct> daList) {
    int i = 1;
    for (DialogueAct evt : daList) {
      if (da.subsumes(evt)) {
        return i;
      }
      ++i;
    }
    return 0;
  }

  /** When did i say this in this session? */
  public int saidInSession(DialogueAct da) {
    return lastOccurence(da, myLastDAs);
  }

  /** Am I currently waiting for a response?
   *  The condition is: I was the last to say something, and my dialogue act
   *  was a question or a request.
   * @return true if i'm waiting for a response.
   */
  public boolean waitingForResponse() {
    // if my last DA was a request or a question, and there is no newer incoming
    // da, i'm waiting for an answer.
    DialogueAct myLast = myLastDA();
    //logger.error("waitResp: {} ", myLast);
    if (myLast == null) {
      return false;
    }
    // Do not use lastDA() here! it may return null because of lastDAprocessed(),
    // not because the queue is empty
    DialogueAct lastDA = lastDAs.peekFirst();
    final DialogueAct[] requests = {
        new DialogueAct("Question(top)"),
        new DialogueAct("Request(top)"),
        new DialogueAct("IndirectRequest(top)")
    };
    if (lastDA != null && myLast.timeStamp < lastDA.timeStamp)
      return false;
    for (DialogueAct req : requests) {
      RdfClass my = _proxy.getClass(myLast.getDialogueActType());
      RdfClass r = _proxy.getClass(req.getDialogueActType());
      //logger.error("waitResp: {} <= {}", my, r);
      if (my.isSubclassOf(r)) return true;
      //if (myLast.isSubsumedBy(req)) return true;
    }
    return false;
  }

  /** Return the index of the last speech act equal or more specific than the
   *  given one
   */
  public int receivedInSession(DialogueAct da) {
    return lastOccurence(da, lastDAs);
  }

  public DialogueAct lastDA() {
    DialogueAct last = lastDAs.peekFirst();
    // TODO: THIS IS NOT QUITE RIGHT. I SHOULD MARK SINGLE INCOMING DA'S AS
    // PROCESSED
    if (last == null || last.timeStamp < lastDAprocessed) {
      return null;
    }
    return last;
  }

  public DialogueAct addLastDA(DialogueAct newDA) {
    if (newDA == null) {
      logger.error("input DA is null");
      return null;
    }
    lastDAs.addFirst(newDA);
    newData();
    return newDA;
  }

  public void lastDAprocessed() {
    lastDAprocessed = System.currentTimeMillis();
  }

  public static String getSlot(DialogueAct diaAct, String feature) {
    return diaAct.getValue(feature);
  }

  public static boolean hasSlot(DialogueAct diaAct, String feature) {
    return diaAct.hasSlot(feature);
  }

  public static String getDialogueAct(DialogueAct diaAct) {
    return diaAct.getDialogueActType();
  }

  // **********************************************************************
  // Timeouts
  // **********************************************************************
  Proposal emptyProposal = new Proposal() {
    public void run() {
    }
  };

  public void newTimeout(String name, int millis) {
    timeouts.newTimeout(name, millis, emptyProposal);
  }

  protected void newTimeout(String name, int millis, final Proposal p) {
    timeouts.newTimeout(name, millis,
        new Proposal(){ public void run(){
          proposalsToExecute.offerLast(p);
        }});
  }

  public boolean isTimedOut(String name) {
    boolean result = timeouts.isTimedout(name);
    if (result) removeTimeout(name);
    return result;
  }

  public void removeTimeout(String name) {
    timeouts.remove(name);
  }

  public boolean hasActiveTimeout(String name) {
    return timeouts.hasActiveTimeout(name);
  }

  protected void cancelTimeout(String name) {
    timeouts.cancelTimeout(name);
  }

  /* *************************************************************************

  ************************************************************************* */

  public void newData() {
    newData = true;
  }

  public String getLanguage() {
    return _language;
  }

  public boolean waitForIntention() {
    return proposalsSent;
  }

  /* *************************************************************************
   Rdf shortcuts
   ************************************************************************* */

  public Rdf toRdf(String uri) {
    if (uri.startsWith("\"")) uri = uri.substring(1, uri.length() -1);
    return _proxy.getRdf(uri);
  }

  public String toUri(Rdf rdf) {
    return rdf.getURI();
  }

  /* *************************************************************************
  Java shortcuts
  ************************************************************************* */

  public float random() {
    return random.nextFloat();
  }

  public int random(int bound) {
    return random.nextInt(bound);
  }

  public DialogueAct analyse(String input) {
    return asr.interpret(input);
  }

//  /**
//   * Interpret what he's saying and create the proper reaction
//   * @param fromAsr a SpeechActEvent coming from the ASR
//   */
//  void treatAsrSpeechact(SpeechActEvent fromAsr) { // I CAN NOT DO ALL THE
//    INTERPRETATION IN THE ASR
//    . A SIMPLE EXAMPLE // IS "yes" -> confirm() is a
//        agreement if the
//      SA
//    it refers to is a // ynquestion, and acceptoffer() if
//    it is an offer, and acceptrequest // if it is a request. // also, missing
//        slots may have to be filled // turn confirm/disconfirm with previous offer into accept/decline offer
//        SpeechActEvent lastSpeechact = getLastSpeechAct();
//    if (lastSpeechact
//        != null) {
//      if (lastSpeechact.getSpeechact().equals("Offer")) {
//        switch (fromAsr.getSpeechact()) {
//          case "Disconfirm":
//            fromAsr.setSpeechact("DeclineOffer");
//            break;
//          case "Confirm":
//            fromAsr.setSpeechact("AcceptOffer");
//            break;
//        }
//      }
//      boolean referring = App.referringSpeechactType(fromAsr.getSpeechact(),
//          lastSpeechact.getSpeechact());
//      if (fromAsr.getProposition().equals("Frame")) {
//        if (referring) {
//          fromAsr.setProposition(lastSpeechact.getProposition());
//        }
//      }
//      if (fromAsr.getAddressee().equals("UNKNOWN")) {
//        if (referring) {
//          fromAsr.setAddressee(lastSpeechact.getSender());
//        } else {
//          fromAsr.setAddressee(lastSpeechact.getAddressee());
//        }
//      }
//      if (referring && !fromAsr.getArguments().containsKey("refersTo")) {
//        fromAsr.getArguments().put("refersTo", lastSpeechact.getId());
//      }
//      if (fromAsr.getSpeechact().equals("Request")
//          && fromAsr.getProposition().equals("Greeting")
//          && fromAsr.getArguments().containsKey("hasTheme")
//          && fromAsr.getArguments().get("hasTheme").startsWith("pron")
//          && lastSpeechact.getArguments().containsKey("hasTheme")) {
//        fromAsr.getArguments().put("hasTheme",
//            lastSpeechact.getArguments().get("hasTheme"));
//      }
//    }
//    logger.info(fromAsr);
//    comSys.send(".*", "SpeechActEvent", fromAsr);
//  }
  public abstract boolean process();

  @SuppressWarnings("rawtypes")
  public void init(File configDir, String language, RdfProxy proxy, Map configs) {
    _proxy = proxy;
    _proxy.registerStreamingClient(this);
    _language = language;
    DagNode.init(new DiaHierarchy(_proxy));
    asr = new AsrTts();
    try {
      asr.loadGrammar(configDir, language, this, configs);
    } catch (IOException ex) {
      logger.error("Error loading grammar: {}", ex);
      System.exit(1);
    }
    reset();
  }

  public void setCommunicationHub(CommunicationHub hub) {
    _hub = hub;
  }

  // ######################################################################
  // behaviour handling --> move to another class!
  // ######################################################################
  /** How much time in milliseconds must pass between two behaviours, if
   *  no message came back that the previous behaviour was finished.
   */
  public static long MIN_TIME_BETWEEN_BEHAVIOURS = 10000;

  /** The minimum pause after we got a signal that the previous behaviour
   *  was finished.
   */
  public static long MIN_PAUSE_FOR_FINISHED_BEHAVIOURS = 500;

  protected String lastBehaviourId = null;

  /** Don't send the next behaviour before this point in time is reached */
  private long behaviourNotBefore = 0;

  // to estimate the duration per word/motion behaviour, can change during session
  protected long sumOfDurations = 400;
  protected long numberOfItems = 1;

  protected class BHContainer {
    public long delayBefore, delayAfter, issued;
    public int items; // no of words and motion behaviours in this container
    String id;
    Behaviour b;
  }

  /** The queue of unfinished behaviours already sent out */
  protected Deque<BHContainer> _pendingBehaviours = new ArrayDeque<>();

  private IdentityHashMap<Behaviour, Integer> _customDelay = new IdentityHashMap<>();


  Map<String, Pair<Proposal, Integer>> behaviourTriggers = new HashMap<>();

  protected void lastBehaviourTrigger(int maxWait, Proposal p) {
    synchronized(behaviourTriggers) {
      behaviourTriggers.put(lastBehaviourId,
          new Pair<Proposal, Integer>(p, maxWait));
      /*timeouts.newTimeout(lastBehaviourId, maxWait, new Proposal() {
        public void run() {
          synchronized(behaviourTriggers) { executeTrigger(lastBehaviourId); }
        }
      });
      */
    }
  }

  protected void startLastBehaviourTriggerTimeout(String behaviourId) {
    Pair<Proposal, Integer> p = behaviourTriggers.get(behaviourId);
    if (p != null && ! timeouts.hasActiveTimeout(behaviourId)) {
      timeouts.newTimeout(lastBehaviourId, p.second, new Proposal() {
        public void run() {
          synchronized(behaviourTriggers) { executeTrigger(lastBehaviourId); }
        }
      });
    }
  }



  protected void executeTrigger(String behaviourId) {
    synchronized(behaviourTriggers) {
      timeouts.cancelTimeout(behaviourId);
      if (behaviourTriggers.containsKey(behaviourId)) {
        proposalsToExecute.offerLast(behaviourTriggers.get(behaviourId).first);
        behaviourTriggers.remove(behaviourId);
      }
    }
  }

  /** Seems agnostic to implementation --> Agent ?? */
  boolean waitForBehaviours(Object message) {
    long currentTime = System.currentTimeMillis();
    if (currentTime < behaviourNotBefore) {
      return true;
    } else {
      //TODO: check if that does the trick
      long delay = MIN_TIME_BETWEEN_BEHAVIOURS;
      if (_customDelay.containsKey(message)) {
        delay = _customDelay.get(message);
        _customDelay.remove(message);
      }
      behaviourNotBefore = currentTime + delay;
    }
    return false;
  }

  double estimatedTime(int items) {
    return items * ((double)sumOfDurations / numberOfItems);
  }

  // Uses BHContainer
  public boolean waitForBehaviours() {
    long currentTime = System.currentTimeMillis();
    while (!_pendingBehaviours.isEmpty()) {
      BHContainer bc = _pendingBehaviours.peek();
      long timeUsed = currentTime - bc.issued;
      double timeEstimated = estimatedTime(bc.items);
      if (timeUsed > 3 * timeEstimated) {
        _pendingBehaviours.pop();
        executeTrigger(bc.id);
        logger.info("removing overdue behaviour {} (ETA {}/{}), was {}", bc.id,
            timeEstimated, bc.items, timeUsed);
      } else {
        // logger.info("Waiting for behaviour {} to finish", bc.id);
        break;
      }
    }
    return (! _pendingBehaviours.isEmpty());
  }

  /** Put the behaviour into a waiting queue to see when it's finished.
   *  There will be a SystemInfo "finished" message that contains the id of the
   *  behaviour
   * @param c the message containing the behaviour
   */
  public void enqueueBehaviour(Behaviour b) {
    startLastBehaviourTriggerTimeout(b.getId());

    BHContainer bc = new BHContainer();
    bc.items = b.getText().split("  *").length + b.getMotion().split("\\|").length;
    bc.id = b.getId();
    bc.b = b;
    bc.issued = System.currentTimeMillis();
    /*
    if (minTime < 0) {
      bc.delayBefore = - minTime;
    } else if (minTime > 0) {
      bc.delayAfter = minTime;
    }
    */
    logger.info("enqueueing behaviour {} (ETA {}/{})", bc.id,
        (long)estimatedTime(bc.items), bc.items);
    _pendingBehaviours.push(bc);
  }

  /**
   * A low level NAO command (or a timeout) signalled that the NAO finished
   * speaking / moving
   *
   * If event sending was blocked while waiting for this signal, unblock it now.
   */
  public void setBehaviourFinished(String behaviourId) {
    if (_pendingBehaviours.isEmpty()) return;
    int i = 0;
    for (BHContainer b : _pendingBehaviours) {
      if (b.id.equals(behaviourId)) {
        long timeNeeded = System.currentTimeMillis() - b.issued;
        logger.info("behaviour {} (ETA {}/{}), was {}", b.id,
            (long) estimatedTime(b.items), b.items, timeNeeded);
        sumOfDurations += timeNeeded;
        numberOfItems += b.items;
        break;
      }
      ++i;
    }
    if (i == _pendingBehaviours.size()) {
      logger.warn("no matching behaviour found for: {}", behaviourId);
      return;
    } else if (i > 0) {
      logger.warn("Receiving behaviour id in wrong order: in: {} pending: {}"
          , behaviourId, _pendingBehaviours.peek().id);
    }
    while (i >= 0) {
      BHContainer bc = _pendingBehaviours.pop();
      executeTrigger(bc.id);
      logger.info("removing behaviour {}", bc.id);
      --i;
    }
  }

  // ######################################################################
  // proposal handling and main loop functions
  // ######################################################################


  public void propose(String name, Proposal p) {
    // add the proposal to the pending proposals, but not twice
    if (!pendingProposals.containsKey(name)) {
      p.name = name;
      pendingProposals.put(name, p);
    }
  }

  public void executeProposal(Intention intention) {
    String continuationName = intention.getContent();
    Proposal p = pendingProposals.get(continuationName);
    if (p != null) {
      logger.info("Execute intention: {}", continuationName);
      executedLast = p.name;
      pendingProposals.clear();
      // this is the first thing to execute in the next round
      proposalsToExecute.offerFirst(p);
      proposalsSent = false;
    } else {
      logger.error("Inactive intention: {}", continuationName);
    }
  }

  /** Send the list of possible intentions to the communication hub */
  void sendIntentions(Set<String> strings) {
    _hub.sendIntentions(strings);
    proposalsSent = true;
  }

  /**
   * If new data arrived, start the rules processing until no new proposals are
   * added and send the final set to the decision process. After that, the flag
   * signalling that new data arrived is reset
   */
  private void actOnNewData() {
    if (newData || timeouts.timeoutOccured()) {
      int oldSize = 0;
      do {
        oldSize = pendingProposals.size();
        process();
      } while (pendingProposals.size() != oldSize);
      if (oldSize > 0) {
        sendIntentions(pendingProposals.keySet());
      }
      newData = false;
    }
  }

  public void processRules() {
    synchronized (this) {
      // execute code injected from timeouts or external triggers
      while (!proposalsToExecute.isEmpty()) {
        proposalsToExecute.pollFirst().run();
        newData();
      }
      if (newData) {
        actOnNewData();
      }
    }
  }

  // ######################################################################
  // Collection + lambda methods
  // ######################################################################

  public <T> boolean contains(Collection<T> coll, Predicate<? super T> p) {
    for(T elt : coll)
      if (p.test(elt)) return true;
    return false;
  }

  public <T> boolean all(Collection<T> coll, Predicate<? super T> p) {
    for(T elt : coll)
      if (! p.test(elt)) return false;
    return true;
  }

  public <T> List<T> filter(Collection<T> coll, Predicate<? super T> p) {
    List<T> result = new ArrayList<>();
    for(T elt : coll)
      if (p.test(elt)) result.add(elt);
    return result;
  }

  public <T> List<T> sort(Collection<T> coll,  Comparator<? super T> c) {
    List<T> l = new LinkedList<>(coll);
    Collections.sort(l, c);
    return l;
  }

  public <T> int count(Collection<T> coll, Predicate<? super T> p) {
    int result = 0;
    for(T elt : coll)
      if (p.test(elt)) ++result;
    return result;
  }

  // ######################################################################
  // Rule logging for debugging
  // ######################################################################

  /** log all rules */
  public void logAllRules() {
    logAllRules = true;
  }

  /** Reset logging to false for all rules */
  public void resetLogging() {
    logAllRules = false;
    rulesToLog.clear();
  }

  /** Start logging a specific rule */
  public void logRule(String name) {
    rulesToLog.add(name);
  }

  /** Stop logging a specific rule */
  public void unLogRule(String name) {
    rulesToLog.remove(name);
  }

  /** For the compiled code, to determine if a rule should be logged */
  public boolean shouldLog(String name) {
    return logAllRules || rulesToLog.contains(name);
  }

  /**
   * function that logs (rule) conditions
   * @param values the parts of the condition, mapped to true or false
   * @param rule the name of the rule whose condition this is
   * @param file the file the rule is in
   */
  public void logRule(Map<String,Boolean> values, String rule, String file){
    StringBuffer sb = new StringBuffer();
    //sb.append("Rule ").append(file).append(":").append(rule).append('\n');
    boolean first = true;
    for (Map.Entry<String, Boolean> e : values.entrySet()) {
      if (first) {
        sb.append(e.getValue().toString().toUpperCase())
          .append(":[").append(file).append("|").append(rule).append("] ")
          .append(e.getKey());
        first = false;
      } else {
        sb.append("\n   ")
        .append(e.getKey()).append(": ").append(e.getValue());
      }
    }
    //logger.debug("{}", sb.toString());
    System.err.println(sb.toString());
  }
}
