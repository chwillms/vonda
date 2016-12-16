/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import java.util.*;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;

/**
 * this is rudimants memory, used for type checking
 *
 * @author Anna Welker
 */
public class Mem {

  private static Logger logger = LoggerFactory.getLogger(Mem.class);

  // to remember those labels that get an own method
  public int ruleNumber = 1;
  private HashMap<String, HashMap<String, Integer>> ruleNums = new HashMap<>();

  final private Deque<Environment> environment;

  private Environment current;

  // every toplevel rule might use variables of super rules from the super file
  private String curRule;
  private String curClass;
  private String curTopRule;
  private HashMap<String, HashSet<String>> neededClasses = new HashMap<>();

  // remember the toplevel rules and imports in the correct order
  private Map<String, List<String>> rulesAndImports;

  private RdfProxy _proxy;

  public Mem(RdfProxy proxy) {
    environment = new ArrayDeque<>();
    current = null;
    rulesAndImports = new HashMap<>();
    _proxy = proxy;
  }

  static Map<String, Long> typeCodes = new HashMap<>();

  static final long JAVA_TYPE = 0x10;
  static {
    typeCodes.put("Object", 0x11l);
    typeCodes.put("String", 0x1l);
    typeCodes.put("Rdf", 0x100l);
    typeCodes.put("double", 0x100000l);
    typeCodes.put("float", 0x11000l);
    typeCodes.put("int", 0x111000l);
    typeCodes.put("boolean", 0x1000000l);
  }

  private static boolean isRdfType(String type) {
    return (type != null && type.charAt(0) == '<');
  }

  /** Return the more specific of the two types, if it exists, null otherwise */
  public String mergeTypes(String left, String right) {
    // check if these are RDF types and are in a type relation.
    if (isRdfType(left) || isRdfType(right)) {
    if (isRdfType(left) && isRdfType(right))
        return _proxy.fetchMostSpecific(left, right);
      if ("Rdf".equals(left)) return right;
      if ("Rdf".equals(right)) return left;
      return null;
    }

    // this should return the more specific of the two, or null if they are
    // incompatible
    Long leftCode = typeCodes.get(left);
    if (leftCode == null) leftCode = JAVA_TYPE;
    Long rightCode = typeCodes.get(right);
    if (rightCode == null) rightCode = JAVA_TYPE;

    long common = leftCode & rightCode;
    if (common == leftCode) {
      return left;
    }
    if (common == rightCode) {
      return right;
    }
    return null;
  }

  public RdfProxy getProxy() {
    return _proxy;
  }

  public String checkRdf(String type) {
    // if is necessary, because otherwise, Object as the static type in a
    // declaration gets changed to RdfType by
    // VGenerationVisitor.visitNode(ExpAssignment node)
    if ("Object".equals(type)){
      return type;
    }
    try {
      RdfClass clazz = _proxy.fetchClass(type);
      if (clazz != null) {
        type = clazz.toString(); // the URI of the class
      }
    } catch (TException e) {
      throw new RuntimeException(e);
    }
    return type;
  }

  /**
   * enter a new (imported) class. please store the old values of
   * getcurrentClass() and getCurrentRule in your visitor and use leaveClass()
   * to give them back to the memory after the new class has been handled
   *
   * @param classname of the new class
   */
  public void enterClass(String classname) {
    this.curClass = classname;
    this.curRule = classname;
    if (ruleNums.get(classname) == null) {
      this.ruleNums.put(classname, new HashMap<String, Integer>());
      this.neededClasses.put(classname, new HashSet<String>());
      this.rulesAndImports.put(classname, new ArrayList<String>());
    }
  }

  /**
   * get the name of the current class
   *
   * @return
   */
  public String getClassName() {
    return this.curClass;
  }

  /**
   * get the name of the current rule
   *
   * @return
   */
  public String getCurrentRule() {
    return this.curRule;
  }

  public String getCurrentTopRule() {
    if (this.curTopRule == null) {
      return this.curClass;
    }
    return this.curTopRule;
  }

  public void leaveClass(String oldClassName, String oldCurRule, String oldCurTRule) {
    if (oldClassName != null) {
      this.curClass = oldClassName;
      if (oldCurRule != null) {
        this.curRule = oldCurRule;
        this.curTopRule = oldCurTRule;
      } else {
        this.curRule = oldClassName;
        this.curTopRule = oldClassName;
      }
    }
  }

  /** Add a function/method declaration, optionally with return and parameter
   *  types. If the types are not known, it's assumed they are null.
   *
   * @param funcname The name of the function
   * @param functype the return type of the function, or null
   * @param partypes the parameter types of the function's parameters
   * @param origin first element class, second rule origin
   */
  public void addFunction(String funcname, String functype,
          ArrayList<String> partypes, String origin) {
    this.current.addFunction(funcname, functype, partypes, origin, this);
  }

  public String getFunctionOrigin(String funcname){
    return current.getFunctionOrigin(funcname);
  }

  public boolean existsFunction(String funcname,
          ArrayList<String> partypes) {
    return current.existsFunction(funcname, partypes);
  }

  /**
   * returns null if there is no such function
   *
   * @param funcname the name of the function
   * @return its return type or null
   */
  public String getFunctionRetType(String funcname) {
    // TODO: we could also identify the function by the parameter types, is this
    // necessary?
    return current.getFunctionRetType(funcname);
  }

  /**
   *
   * @param variable
   * @param type
   * @param origin first element class, second rule origin
   * @return
   */
  public boolean addVariableDeclaration(String variable, String type, String origin) {
    if (current.containsKey(variable)) {
      return false;
    }
    type = checkRdf(type);
    current.put(variable, type, origin);
    logger.debug("Add var {}:{} [{}]", environment.size(), variable, type);
    return true;
  }

  public boolean variableExists(String variable) {
    return (current.containsKey(variable));
  }

  public boolean isRdf(String variable) {
    String type = current.get(variable);
    return (type != null && type.charAt(0) == '<');
  }

  /**
   * get the class the given variable is located
   *
   * @param variable a variable
   * @return the class it came from, null if not declared
   */
  public String getVariableOriginClass(String variable) {
    return current.getOrigin(variable);
  }

  /**
   * get the rule the given variable is located
   *
   * @param variable a variable
   * @return the toplevel rule it came from
   *
   * public String getVariableOriginTRule(String variable) { return
   * variableOrigin.get(variable).get(1); }
   */
  /**
   * get the type of the given variable
   *
   * @param variable a variable
   * @return the variable's type
   */
  public String getVariableType(String variable) {
    return current.get(variable);
  }

  /**
   * adds a new Environment with the given depth
   *
   * @return the position in memory where the environment is stored
   */
  public void enterEnvironment() {
    logger.trace("Enter level {}", environment.size());
    if (current == null) {
      current = new Environment();
    } else {
      environment.push(current);
      current = current.deepCopy();
    }
  }

  public void leaveEnvironment() {
    logger.trace("Leave level {}", environment.size());
    // restore the values in actual that we changed
    current = environment.isEmpty() ? null : environment.pop();
  }

  /**
   * add the rule to the memory
   *
   * @param rule
   * @param toplevel
   * @return the rule's number, to be used in bitwise if markers
   */
  public void addRule(String rule, boolean toplevel) {
    if (toplevel) {
      this.rulesAndImports.get(this.curClass).add(rule);
      this.ruleNumber = 1;
      curTopRule = rule;
    } else {
      this.ruleNumber *= 2;
    }
    this.ruleNums.get(curClass).put(rule, ruleNumber);
    curRule = rule;
    this.neededClasses.put(rule, new HashSet<String>());
  }

  public void addImport(String importName, String conargs) {
    String importClassName = importName.substring(0, 1).toUpperCase() + importName.substring(1);
    this.rulesAndImports.get(this.curClass).add(importClassName + " "
            + importName + " = new " + importClassName + "(" + conargs +
            ");\n" + importName + ".process(");
  }

  /**
   * not only toplevel rules, but also import calls
   *
   * @param classname
   * @return
   */
  public List<String> getToplevelCalls(String classname) {
    return this.rulesAndImports.get(this.curClass);
  }

  /**
   * return all toplevel rules contained in the given class
   *
   * @param classname
   * @return
   */
  public Set<String> getTopLevelRules(String classname) {
    HashSet<String> rs = new HashSet<>();
    for (String k : this.ruleNums.get(classname).keySet()) {
      if (this.ruleNums.get(classname).get(k) == 1) {
        rs.add(k);
      }
    }
    return rs;
  }

  /**
   * tell the memory to remember that the given rule/class needs an instance of
   * the given class
   *
   * @param rule a rule or class
   * @param ruleclass the class needed
   */
  public void needsClass(String rule, String ruleclass) {
    //System.out.println(ruleclass);
    this.neededClasses.get(rule).add(ruleclass);
  }

  public Set<String> getNeededClasses(String ruleOrClass) {
    return this.neededClasses.get(ruleOrClass);
  }

  public boolean isExistingRule(String rule) {
    return ruleNums.get(this.curClass).containsKey(rule);
  }
}
