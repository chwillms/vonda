/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Constants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;

/**
 * this visitor generates the java code
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VGenerationVisitor implements RudiVisitor {

  public static Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  RudimantCompiler out;
  private RudimantCompiler rudi;
  private Mem mem;
  private VRuleConditionVisitor condV;
  private VConditionCreatorVisitor condV2;
  LinkedList<Token> collectedTokens;

  // activate this bool to get double escaped String literals
  private boolean escape = false;

  // flag to tell the if if is a real rule if (contains the condition that was calculated)
  private String ruleIf = null;

  public VGenerationVisitor(RudimantCompiler r, LinkedList<Token> collectedTokens) {
    this.rudi = r;
    this.out = r;
    this.mem = rudi.getMem();
    condV = new VRuleConditionVisitor();
    condV2 = new VConditionCreatorVisitor();
    this.collectedTokens = collectedTokens;
  }

  @Override
  public void visitNode(RTExpression node) {
    node.visitWithComments(this);
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    if (node.right == null) {
      // unary operator
      // TODO: ENCAPSULATE THIS INTO TWO FUNCTIONS: isPrefixOperator() and
      // isPostFixOperator()
      if ("-".equals(node.operator) || "!".equals(node.operator)) {
        out.append(node.operator);
      }
      out.append('(');
      node.left.visitWithComments(this);
      // something like .isEmpty(), which is a postfix operator
      if (node.operator.endsWith(")")) {
        out.append(node.operator);
      }
      out.append(')');
    } else {
      out.append('(');
      node.left.visitWithComments(this);
      out.append(node.operator);
      node.right.visitWithComments(this);
      if (node.operator.endsWith("(")) {
        out.append(')');
      }
      out.append(')');
    }
  }

  boolean notPrintLastField = false;

  @Override
  public void visitNode(ExpAssignment node) {
    //System.out.println(((UVariable) left).toString());
    // visit also the left side, it could be using another class's variable!
    // System.out.println("Generating assignment");
    /* BK THIS DOESN'T SEEM RIGHT
    if (!node.declaration && node.left instanceof UFieldAccess) {
      //rudi.getProxy().fetchRdfClass(node.right.getType()) != null) {

      // if the second last part of the field access was an rdf, than we actually
      // want to put the right child into the database here
      List<String> representation = new ArrayList<>();
      int i = ((UFieldAccess) node.left).representation.size() - 1;
      representation.addAll(((UFieldAccess) node.left).representation
          .subList(0, i));
      String lefttype = ((UFieldAccess) node.left)
          .getPropertyType(mem.getProxy(), mem, representation);
//    if (this.rudi.getProxy().fetchRdfClass(lefttype) != null) {
      if (lefttype != null && !lefttype.equals("Object")) {
        // then getPredicateType found an rdf class related
        notPrintLastField = true;
        node.left.visitWithComments(this);
        out.append(".setValue(\"");
        out.append(((UFieldAccess) node.left).representation.get(i));
        out.append("\", ");
        node.right.visitWithComments(this);
        out.append(")");
        notPrintLastField = false;
        return;
      }
    }
    */
    if (node.declaration) {
//    if(node.actualType == null){
//    throw new UnsupportedOperationException("no actualtype for declaration of " + node.fullexp);
//    }
      if (Mem.isRdfType(node.actualType)) {
        // All RDF types except DialogueAct has to be replaced with Rdf
        out.append((DIALOGUE_ACT_TYPE.equals(node.actualType))
            ? "DialogueAct" : "Rdf");
      } else {
        out.append(node.actualType);
      }
    }
    out.append(' ');
    UPropertyAccess pa = null;
    boolean functional = false;
    if (node.left instanceof UFieldAccess) {
      UFieldAccess acc = (UFieldAccess)node.left;
      RudiTree lastPart = acc.parts.get(acc.parts.size() - 1);
      if (lastPart instanceof UPropertyAccess)
        pa = (UPropertyAccess)lastPart;
      functional = pa != null && pa.functional;
      //
      notPrintLastField = pa != null;
      node.left.visitWithComments(this);
      notPrintLastField = false;
      if (pa != null) {
        out.append(functional ? ".setSingleValue(\"" : ".setValue(\"");
        out.append(pa.label.content).append("\", ");
      }
    } else {
      node.left.visitWithComments(this);
      out.append(" = ");
    }
    if(!node.type.equals(node.right.getType())){
      // then there is either sth wrong here, what would at least have resulted 
      // in warnings in type testing, or it is possible to cast the right part
      out.append("(" + node.type + ") ");
    }
    node.right.visitWithComments(this);
    if (pa != null) {
      out.append(")"); // close call to set(Single)Value()
    }
    //out.append(";");
  }

  @Override
  public void visitNode(ExpBoolean node) {
    if (node.type == null) {
      node.getType();
    }
    String ret = "";
    if ("!".equals(node.operator)) {
      out.append("!");
    }

    String function = "";
//    if (node.rdf) {
//      function = "RdfClass.isSubclassOf(";
//    }
//    else {
//      function = "isSubsumed(";
//    }
    /* TODO: TAKE CARE OF OPERATORS THAT START WITH "." AND END WITH "(" (BINARY)
     * OR ")" (UNARY, LIKE '.isEmpty()')
     *
     if (node.isSubsumed) {
     node.left.visitWithComments(this);
     out.append(".isSubsumed(");
     node.right.visitWithComments(this);
     out.append(")");
     return;
     } else if (node.doesSubsume) {
     node.right.visitWithComments(this);
     out.append(".isSubsumed(");
     node.left.visitWithComments(this);
     out.append(")");
     return;
     }
     */
    if(node.operator.contains("(")){
      out.append(node.operator);
      node.left.visitWithComments(this);
      out.append(", ");
      node.right.visitWithComments(this);
      out.append(")");
      return;
    }
    if (node.right != null) {
      out.append("(");
      node.left.visitWithComments(this);
      out.append(" ");
      out.append(node.operator);
      out.append(" ");
      node.right.visitWithComments(this);
      /*if (node.operator.contains("(")) {
        out.append(")");
      }*/
      out.append(")");
      //out.context.doLog(
      //        "\"" + ret.replace('"', ' ') +  " _ resulted to \" + " + ret);
      return;
    }
//    out.append("(");
    node.left.visitWithComments(this);
    this.conditionHandling(node);
//    out.append(")");
    //out.context.doLog("\"" + ret.replace('"', ' ') +  " resulted to \" + ("
    //        + ret + ")");
  }

  public void visitDaToken(RTExpression exp) {
//    System.out.println("visiting datoken " + exp.fullexp + " which is "
//            + (exp instanceof UVariable ? "" : "not ") + "instance of UVariable");
    if (exp instanceof UVariable) {
      out.append("\" + ");
      out.append(((UVariable) exp).fullexp);
      out.append("+ \"");
    } else if (exp instanceof USingleValue
            && ((USingleValue) exp).type.equals("String")) {
      String s = ((USingleValue) exp).content;
      if (s.contains("\"")) {
        s = s.substring(1, s.length() - 1);
      }
      out.append(s);
    } else {
      out.append("\" + ");
      this.visitNode(exp);
      out.append(" + \"");
    }
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    out.append("new DialogueAct(\"");
    visitDaToken(node.daType);
    out.append('(');
    visitDaToken(node.proposition);
    for (int i = 0; i < node.exps.size(); i += 2) {
      out.append(", ");
      // TODO: is there a reason why this should ever be something special?
//      visitDaToken(node.exps.get(i));
      out.append(node.exps.get(i).fullexp);
      out.append(" = ");
      visitDaToken(node.exps.get(i + 1));
    }
    out.append(')');
    out.append("\")");
  }

  @Override
  public void visitNode(ExpConditional node) {
    node.boolexp.visitWithComments(this);
    out.append(" ? ");
    node.thenexp.visitWithComments(this);
    out.append(" : ");
    node.elseexp.visitWithComments(this);
  }

  @Override
  public void visitNode(ExpLambda node) {
    out.append(node.content);
  }

  @Override
  public void visitNode(GrammarFile node) {

    String oldname = mem.getClassName();
    String oldrule = mem.getCurrentRule();
    String oldTrule = mem.getCurrentTopRule();
    mem.enterClass(rudi.className);

    //mem.enterNextEnvironment();
    // tell the file in which package it lies
    String pkg = rudi.getPackageName();
    if (pkg == null) {
      pkg = "";
    } else {
      out.append("package " + pkg + ";\n");
      pkg += ".";
    }
    // Let's import our supersuper class
    out.append("import de.dfki.mlt.rudimant.agent.DialogueAct;\n");
    out.append("import de.dfki.lt.hfc.db.rdfProxy.Rdf;\n");
    // maybe we need to import the class that imported us to use its variables
    out.append("import ");
    if (rudi.getParent() != null) {
      out.append(pkg + rudi.getParent().className);
    } else {
      out.append(rudi.getWrapperClass());
    }
    out.append(";\n");
    // moved to typevisitor where it belongs
//    for (String s : mem.getTopLevelRules(out.className)) {
//      for (String n : mem.getNeededClasses(s)) {
//        mem.needsClass(out.className, n);
//      }
//    }
    out.append("import java.util.ArrayList;\n"
            + "import java.util.List;\n"
            + "import java.util.Set;\n"
            + "import java.util.HashSet;\n"
            + "import java.util.HashMap;\n"
            + "import org.slf4j.Logger;\n"
            + "import org.slf4j.LoggerFactory;\n\n");
    out.append("public class " + node.classname + " extends "
            + rudi.getWrapperClass() + "{\n");
    out.append("public static Logger logger = LoggerFactory.getLogger("
            + mem.getClassName() + ".class);\n");
    out.append("// add to this set the name of all rules you want to be logged\n");
    out.append("private Set<String> rulesToLog = new HashSet<>();\n");
//    out.append("private Boolean wholeCondition = null;\n\n");
    //        + "\tprivate int returnTo = 0;\n");
    // initialize all return markers
//    for (String k : out.rm.getMarkers()) {
//      out.append("int return_" + k + " = " + out.rm.getMarker(k) + ";\n");
//    }
    // initialize all class attributes before the main process method,
    // do all those import things now - but before that, we have to know about
    // all the variables declared here
    for (RudiTree r : node.rules) {
      if (r instanceof StatAbstractBlock) {
        for (RudiTree e : ((StatAbstractBlock) r).statblock) {
          if (e instanceof ExpAssignment) {
            if (((ExpAssignment) e).declaration) {
              ((ExpAssignment) e).visitWithComments(this);
              out.append(";");
            }
          } else if (e instanceof StatVarDef
                  || (e instanceof StatMethodDeclaration
                  && ((StatMethodDeclaration) e).block == null)) {
            e.visitWithComments(this);
          }
        }
      }
    }
    for (RudiTree r : node.rules) {
      if (r instanceof StatAbstractBlock) {
        for (RudiTree e : ((StatAbstractBlock) r).statblock) {
          if (e instanceof StatImport) {
            r.visitWithComments(this);
          }
        }
      }
    }
    // now, we should add a constructor, including constructor parameters if
    // specified in configs
    // also, to use them for imports, declare those parameters class attributes
    String conargs = "";
    String declare = "";
    String args = rudi.getConstructorArgs();
    if (null != args && !args.isEmpty()) {
      int i = 0;
      for (String a : args.split(",")) {
        if (i > 0) {
          conargs += ", ";
        }
        String s = a.trim().split(" ")[1];
        out.append("private final " + a + ";\n");
        declare += "this." + s + " = " + s + ";\n";
        conargs += a.trim().split(" ")[1];
        i++;
      }
    } else {
      args = "";
    }
    out.append("public " + rudi.className + "(" + args + ") {\n"
            + "super(" + conargs + ");\n" + declare + "}\n");

    // finally, the main processing method that will call all rules and imports
    // declared in this file
    out.append("\tpublic void process(");
    // get all those classes the toplevel rules need
    int i = 0;
    for (String n : mem.getNeededClasses(rudi.className)) {
      if (i == 0) {
        out.append(n.substring(0, 1).toUpperCase() + n.substring(1) + " "
                + n.substring(0, 1).toLowerCase() + n.substring(1));
      } else {
        out.append(", " + n.substring(0, 1).toUpperCase() + n.substring(1) + " "
                + n.substring(0, 1).toLowerCase() + n.substring(1));
      }
      i++;
    }
    out.append("){\n");
    // initialize me according to the super class init
    out.append("// this.init();\n");
    // use all methods created from rules in this file
    for (String toplevel : mem.getToplevelCalls(rudi.className)) {
      if (toplevel.contains("(")) {
        out.append(toplevel);

        String t = toplevel.substring(0, toplevel.indexOf(" "));
        Set<String> ncs = mem.getNeededClasses(t);
//        Set<String> ncs = mem.getNeededClasses(toplevel.substring(0, toplevel.indexOf(" ")));
        if (ncs != null) {
          i = 0;
          for (String c : ncs) {
            if (c.equals(rudi.className)
                    || (c.substring(0, 1).toUpperCase()
                    + c.substring(1)).equals(rudi.className)) {
              c = "this";
            }
            if (i == 0) {
              out.append(c.substring(0, 1).toLowerCase()
                      + c.substring(1));
            } else {
              out.append(", " + c.substring(0, 1).toLowerCase()
                      + c.substring(1));
            }
            i++;
          }
        }
        out.append(");\n");
      } else {
        out.append(toplevel + "(");
        // don't forget the needed class instances here
        i = 0;
        for (String n : mem.getNeededClasses(toplevel)) {
          if (i == 0) {
            out.append(n.substring(0, 1).toLowerCase()
                    + n.substring(1));
          } else {
            out.append(", " + n.substring(0, 1).toLowerCase()
                    + n.substring(1));
          }
          i++;
        }
        out.append(");");
      }
    }
    out.append("}\n");
    // Now, only get those statements that are not assignments of class attributes
    for (RudiTree r : node.rules) {
      if (r instanceof StatAbstractBlock) {
        for (RudiTree e : ((StatAbstractBlock) r).statblock) {
          if (e instanceof ExpAssignment) {
            if (((ExpAssignment) e).declaration) {
              continue;
            }
          } else if (e instanceof StatImport) {
            continue;
          } else if (e instanceof StatVarDef
                  || (e instanceof StatMethodDeclaration
                  && ((StatMethodDeclaration) e).block == null)) {
            continue;
          }
          e.visitWithComments(this);
        }
      } else {
        r.visitWithComments(this);
      }
    }
//    // add all the logger methods
//    for (String l : out.ll.getLogRules()) {
//      this.printRuleLogger(l, out.ll.getCond2log(l));
//    }
    out.append("}\n");
    mem.leaveClass(oldname, oldrule, oldTrule);
    mem.leaveEnvironment();
  }

  @Override
  public void visitNode(GrammarRule node
  ) {
    if (node.toplevel) {
      // this is a toplevel rule and will be converted to a method
      out.append("public void " + node.label + "(");
      // get all the required class instances
      int i = 0;
      for (String n : mem.getNeededClasses(node.label)) {
        if (i == 0) {
          out.append("final ");
          out.append(n.substring(0, 1).toUpperCase() + n.substring(1) + " "
                  + n.substring(0, 1).toLowerCase() + n.substring(1));
        } else {
          out.append(", final " + n.substring(0, 1).toUpperCase() + n.substring(1) + " "
                  + n.substring(0, 1).toLowerCase() + n.substring(1));
        }
        i++;
      }
      out.append("){\n");
      this.ruleIf = this.printRuleLogger(node.label, node.ifstat.condition);
      out.append(node.label + ":\n");
      //mem.enterNextEnvironment();
      node.ifstat.visitWithComments(this);
      //mem.leaveEnvironment();
      out.append("}\n");
    } else {
      // this is a sublevel rule and will get an if to determine whether it should be executed
      out.append("//Rule " + node.label + "\n");
      this.ruleIf = this.printRuleLogger(node.label, node.ifstat.condition);
      out.append(node.label + ":\n");
//      if (out.rm.shouldAddReturnto(node.label) != null) {
//        out.append("if ((returnTo | (");
//        int i = 0;
//        for (String r : out.rm.shouldAddReturnto(node.label)) {
//          if (i != 0) {
//            out.append(" | return_" + r);
//          } else {
//            out.append("return_" + r);
//          }
//          i++;
//        }
//        out.append(")) == 0) {\n");
//      }
      node.ifstat.visitWithComments(this);
    }
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    if (node.braces) {
      // when entering a statement block, we need to create a new local environment
      //mem.enterNextEnvironment();
      out.append("{");
    }
    for (RudiTree stat : node.statblock) {
      if (stat instanceof RTExpression) {
        stat.visitWithComments(this);
        out.append(";\n");
        continue;
      }
      stat.visitWithComments(this);
    }
    if (node.braces) {
      out.append("}");
      //mem.leaveEnvironment();
    }
  }

  @Override
  public void visitNode(StatDoWhile node) {
    out.append("do");
    node.block.visitWithComments(this);
    out.append("while (");
    node.condition.visitWithComments(this);
    out.append(");");
  }

  @Override
  public void visitNode(StatFor1 node) {
    out.append("for ( ");
    node.assignment.visitWithComments(this);
    out.append("; ");
    node.condition.visitWithComments(this);
    out.append(";");
    if (node.arithmetic != null) {
      node.arithmetic.visitWithComments(this);
    }
    out.append(");");
    node.statblock.visitWithComments(this);
  }

  @Override
  public void visitNode(StatFor2 node) {
    if (node.varType == null) {
      node.varType = ((RTExpression) node.exp).getType();
    }
    out.append("for (" + node.varType + " ");
    node.var.visitWithComments(this);
    out.append(": ");
    node.exp.visitWithComments(this);
    out.append(") ");
    node.statblock.visitWithComments(this);
  }

  @Override
  public void visitNode(StatFor3 node) {
    out.append("for (Object[] o : ");
    node.exp.visitWithComments(this);
    out.append(") {");
    int count = 0;
    for (String s : node.variables) {
      out.append("\nObject " + s + " = o[" + count++ + "]");
    }
    node.statblock.visitWithComments(this);
    out.append("}");
  }

  @Override
  public void visitNode(StatIf node) {
    if (this.ruleIf != null) {
      out.append("if (" + ruleIf + ") ");
//      out.append("if (rulesToLog.contains(\"" + node.currentRule + "\") ? wholeCondition : ");
      ruleIf = null;
    } else {
      out.append("if (");
      node.condition.visitWithComments(this);
      out.append(") ");
    }
    node.statblockIf.visitWithComments(this);
    if (node.statblockElse != null) {
      out.append("else");
      node.statblockElse.visitWithComments(this);
    }
  }

  @Override
  public void visitNode(StatImport node) {
    logger.info("Processing import " + node.content);
    try {
      RudimantCompiler.getEmbedded(rudi).process(node.content);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    } //    outs.append(node.text + ".process(");
    //    Set<String> ncs = mem.getNeededClasses(node.name);
    //    if (ncs != null) {
    //      int i = 0;
    //      for (String c : ncs) {
    //        if (c.equals(out.className)) {
    //          c = "this";
    //        }
    //        if (i == 0) {
    //          out.append(c.toLowerCase());
    //        } else {
    //          out.append(", " + c.toLowerCase());
    //        }
    //        i++;
    //      }
    //    }
    //    out.append(");\n");
  }

  @Override
  public void visitNode(StatListCreation node) {
//    System.out.println("Rudi was here");
    out.append(node.listType + " " + node.variableName + " = new ArrayList<>();");
    for (RTExpression e : node.objects) {
      out.append(node.variableName + ".add(");
      this.visitNode(e);
      out.append(");\n");
    }
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    if (node.block == null) {
      return;
    }
    //mem.enterNextEnvironment();
    out.append(node.visibility + " ");
    if (node.return_type.startsWith("<")) {
          if (node.return_type.startsWith("<dial")) {  // <dial:DialogueAct > has to be replaced by DialogueAct
            out.append("DialogueAct ");
          } else { // every other type starting with < has to be replaced by Rdf
            out.append("Rdf ");
          }
        } else {
          out.append(node.return_type + " ");
        }
    out.append(node.name + "(");
    for (int i = 0; i < node.parameters.size(); i++) {
      if (i != 0) {
        out.append(", ");
      }
      out.append(node.partypes.get(i) + " " + node.parameters.get(i));
    }
    out.append(")\n");
    node.block.visitWithComments(this);
    //mem.leaveEnvironment();
  }

  @Override
  public void visitNode(StatPropose node) {
    out.append("propose(");
    node.arg.visitWithComments(this);
    out.append(", new Proposal() {public void run()\n");
    node.block.visitWithComments(this);
    out.append("});");
  }

  @Override
  public void visitNode(StatReturn node) {
    if (mem.isExistingRule(node.lit)) {
      if (mem.getTopLevelRules(mem.getClassName()).contains(node.curRuleLabel)) {
        out.append("return;\n");
        return;
      }
      //out.append("returnTo = returnTo | return_" + node.lit + ";\n");
      out.append("break " + node.lit + ";\n");
      return;

    } else if (node.toRet == null) {
      if (mem.getCurrentRule().equals(mem.getClassName())) {
        out.append("return;\n");
        return;
      }
      out.append("break " + mem.getCurrentRule() + ";\n");
      return;
    }
    out.append("return ");
    node.toRet.visitWithComments(this);
    out.append(";\n");
  }

  @Override
  public void visitNode(StatSetOperation node) {
    node.left.visitWithComments(this);
    if (node.add) {
      out.append(".add(");
    } else {
      out.append(".remove(");
    }
    node.right.visitWithComments(this);
    out.append(");");
  }

  @Override
  public void visitNode(StatVarDef node) {
    // no generation here
  }

  @Override
  public void visitNode(StatWhile node) {
    out.append("while (");
    node.condition.visitWithComments(this);
    out.append(")");
    node.block.visitWithComments(this);
  }

  @Override
  public void visitNode(StatSwitch node) {
    out.append("switch (");
    node.condition.visitWithComments(this);
    out.append(")");
    node.block.visitWithComments(this);
  }

  @Override
  public void visitNode(UFieldAccess node) {
    int to = node.parts.size();
    List<String> representation = new ArrayList<>();
    node.parts.get(0).visitWithComments(this);
    representation.add(node.representation.get(0));
    String lastType = ((RTExpression) (node.parts.get(0))).getType();
    // TODO: EXPLAIN WHEN THE BOOLEAN IS TRUE, AND WHY
    to = notPrintLastField ? to - 1 : to;
    for (int i = 1; i < to; i++) {
      /*
      if (node.parts.get(i) instanceof UVariable) {
        if (node.isRdfType(lastType)) {
          representation.add(node.representation.get(i));
          // then we are in the case that this is actually an rdf operation
          out.append(".getValue(\"" + node.representation.get(i) + "\") ");
          lastType = node.getPropertyType(mem.getProxy(), mem, representation);
          continue;
        } else {
          representation.clear();
        }
      }
      out.append(".");
      node.parts.get(i).visitWithComments(this);
      */
      if (node.parts.get(i) instanceof UPropertyAccess) {
        UPropertyAccess pa = (UPropertyAccess)node.parts.get(i);
        // then we are in the case that this is actually an rdf operation
        representation.add(node.representation.get(i));
        out.append(pa.functional ? ".getSingleValue(\"" : ".getValue(\"");
        out.append(pa.label.content).append("\") ");
      } else {
        // TODO: EXPLAIN THIS IF
        if (! (node.parts.get(i) instanceof UVariable))
          representation.clear();
        out.append(".");
        node.parts.get(i).visit(this);
      }
    }
  }


  @Override
  public void visitNode(UFuncCall node) {
    if (node.realOrigin != null) {
      String t = node.realOrigin;
      out.append(t.substring(0, 1).toLowerCase() + t.substring(1) + ".");
    }
    out.append(node.content + "(");
    for (int i = 0; i < node.exps.size(); i++) {
      node.exps.get(i).visitWithComments(this);
      if (i != node.exps.size() - 1) {
        out.append(", ");
      }
    }
    out.append(")" + " ");
  }

  @Override
  public void visitNode(USingleValue node) {
    if ("String".equals(node.type) && this.escape) {
      // properly escape if needed
      out.append("\\" + node.content.substring(0, node.content.length() - 1) + "\\\"" + " ");
      return;
    }
    out.append(node.content);
  }

  @Override
  public void visitNode(UVariable node) {// if the variable is not in the memory,
    if (node.realOrigin != null) {
      String t = node.realOrigin;
      out.append(t.substring(0, 1).toLowerCase() + t.substring(1) + "." + node.content);
    } else {
      out.append(node.content);
    }
  }

  @Override
  public void visitNode(UWildcard node) {
    out.append("this.wildcard" + " ");   // wildcard is a local variable in resulting class
  }

  private void conditionHandling(ExpBoolean node) {
  }

  /**
   * creates and prints the logging method of the given rule
   *
   * @param rule
   */
  private String printRuleLogger(String rule, RTExpression bool_exp) {

    // TODO BK: bool_exp can be a simple expression, in which case it
    // has to be turned into a comparison with zero, null or a call to
    // the has(...) method
    if (bool_exp instanceof USingleValue && bool_exp.getType().equals("boolean")) {
      // there isnt much we could log
//      out.append("wholeCondition = " + ((UnaryBoolean) bool_exp).content + ";\n");
      return ((USingleValue) bool_exp).content;
//      return;
    }
    RTExpression bool = bool_exp;

    // remembers how the expressions should be realized by rudimant
    LinkedHashMap<String, String> compiledLook = new LinkedHashMap<>();

    // remembers how the expressions looked (for logging)
    LinkedHashMap<String, String> realLook = new LinkedHashMap<>();
    condV.renewMap(rule, realLook, compiledLook, this.rudi);
    condV.visitNode(bool);
    // now create a condition from those things
    //System.out.println(realLook.keySet().size());
    condV2.newMap(realLook.keySet().toArray(), compiledLook);
    condV2.visitNode(bool_exp);

    out.append(condV2.getBoolCreation().toString());
//    for (String var : compiledLook.keySet()) {
//      out.append("//boolean " + var + " = " + compiledLook.get(var) + ";\n");
//    }

    out.append("if (rulesToLog.contains(\"" + rule + "\")){\n");
    // do all that logging

    out.append("HashMap<String, Boolean> " + rule + " = new HashMap<>();\n");
    for (String var : realLook.keySet()) {
      out.append(rule + ".put(\"" + realLook.get(var).replaceAll("\\\"", "\\\\\"") + "\", " + var + ");\n");
    }

    out.append("LoggerFunction(" + rule + ", \"" + rule + "\", \""
            + mem.getClassName() + "\");\n");

    out.append("}\n");
//    out.append("wholeCondition = " + condV2.getCondition().toString() + ";\n");
    return condV2.getCondition().toString();

//    out.append(rule + "Logger(");
//    if (!(out.ll.getVarAndType2log(rule) == null)) {
//      int i = 0;
//      for (String[] var2type : out.ll.getVarAndType2log(rule)) {
//        if (i < 1) {
//          out.append(var2type[0]);
//        } else {
//          out.append(", " + var2type[0]);
//        }
//      }
//    }
//    out.append(");");
  }

  public void dummyLoggingMethod(String rule, String className, Map toLog) {
    // log this very extensive
  }

//  /**
//   * creates and prints the logging method of the given rule
//   *
//   * @param rule
//   */
//  private void printRuleLogger(String rule, RudiTree bool_exp) {
//    if (rule == null) {
////      if (bool_exp instanceof ExpAbstractWrapper) {
////        printRuleLogger(null, ((ExpAbstractWrapper) bool_exp).exp);
////      } else if (bool_exp instanceof ExpBoolean) {
////        out.append("logger.info(\"" + ((ExpBoolean)bool_exp).fullexp
////                + ((ExpBoolean)bool_exp).isTrue + " was \" + "
////                + ((ExpBoolean)bool_exp).isTrue + ((ExpBoolean)bool_exp).fullexp);
////        if(!(((ExpBoolean)bool_exp).isSubsumed | ((ExpBoolean)bool_exp).doesSubsume)){
////          printRuleLogger(null, ((ExpBoolean)bool_exp).left);
////          if(((ExpBoolean)bool_exp).right != null){
////            printRuleLogger(null, ((ExpBoolean)bool_exp).right);
////          }
////        }
//      out.append("logger.info(\"");
//      this.escape = true;
//      this.visitNode(bool_exp);
//      this.escape = false;
//      out.append(" was \" + (");
//      this.visitNode(bool_exp);
//      out.append("));");
////      } else if (bool_exp instanceof ExpArithmetic) {
////
////      } else if (bool_exp instanceof UVariable) {
////
////      } else if (bool_exp instanceof ExpDialogueAct) {
////
////      } else { // it should be sth simple like String, Number, Variable, UnaryBoolean, ...
////
////      }
//      return;
//    }
//    out.append("public void " + rule + "Logger(");
//    if (!out.ll.getVarAndType2log(rule).isEmpty()) {
//      int i = 0;
//      for (String[] var2type : out.ll.getVarAndType2log(rule)) {
//        if (i < 1) {
//          out.append(var2type[1] + " " + var2type[0]);
//        } else {
//          out.append(", " + var2type[1] + " " + var2type[0]);
//        }
//      }
//    }
//    out.append(") {\n if (rulesToLog.contains(\"" + rule + "\")){\n");
//    // do all that logging
//    printRuleLogger(null, bool_exp);
//    out.append("}\n}\n");
//  }
  @Override
  public void visitNode(ExpNew node) {
    if (node.construct != null) {
      out.append("new ");
      node.construct.visit(this);
    } else {
      // TODO: how to do rdf generation?????
      out.append("_proxy.getClass(\""
          + mem.getProxy().getClass(node.type)
          + "\").newInstance(DEFNS)");

    }
  }
}
