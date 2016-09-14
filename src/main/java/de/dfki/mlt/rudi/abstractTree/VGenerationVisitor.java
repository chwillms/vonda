/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this visitor generates the java code
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VGenerationVisitor implements RudiVisitor {

  public static Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private RudimantCompiler out;
  private Mem mem;
  private VRuleConditionVisitor condV;

  // activate this bool to get double escaped String literals
  private boolean escape = false;

  // flag to tell the if if it is a real rule if
  private boolean ruleIf = false;

  public VGenerationVisitor(RudimantCompiler out) {
    this.out = out;
    this.mem = out.getMem();
    condV = new VRuleConditionVisitor();
  }

  @Override
  public void visitNode(RudiTree node) {
    node.visit(this);
  }

  @Override
  public void visitNode(ExpAbstractWrapper node) {
    node.commentbefore.visit(this);
    node.exp.visit(this);
    node.commentafter.visit(this);
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    String ret = "";
    if (node.minus) {
      out.append("-");
    }
    if (node.right != null) {
      out.append("(");
      node.left.visit(this);
      out.append(node.operator);
      node.right.visit(this);
      out.append(")");
      return;
    }
    node.left.visit(this);
  }

  @Override
  public void visitNode(ExpAssignment node) {
    //System.out.println(((UVariable) left).toString());
    if (node.declaration) {
      out.append(node.actualType + " ");
    }
    // visit also the left side, it could be using another class's variable!
    // System.out.println("Generating assignment");
    node.left.visit(this);
    out.append(" = ");
    node.right.visit(this);
    //out.append(";");
  }

  @Override
  public void visitNode(ExpBoolean node) {
    if (node.type == null) {
      node.getType();
    }
    String ret = "";
    if (node.not) {
      out.append("!");
    }
    if (node.isSubsumed) {
      out.append("isSubsumed(");
      node.left.visit(this);
      out.append(", ");
      node.right.visit(this);
      out.append(")");
      return;
    } else if (node.doesSubsume) {
      out.append("isSubsumed(");
      node.right.visit(this);
      out.append(", ");
      node.left.visit(this);
      out.append(")");
      return;
    }
    if (node.right != null) {
      out.append("(");
      node.left.visit(this);
      out.append(" ");
      out.append(node.operator);
      out.append(" ");
      node.right.visit(this);
      out.append(")");
      //out.context.doLog(
      //        "\"" + ret.replace('"', ' ') +  " _ resulted to \" + " + ret);
      return;
    }
    out.append("(");
    node.left.visit(this);
    this.conditionHandling(node);
    out.append(")");
    //out.context.doLog("\"" + ret.replace('"', ' ') +  " resulted to \" + ("
    //        + ret + ")");
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    out.append("new DialogueAct(\"" + node.litGraph + "(");
    String[] parameters = node.rest.split(",");
    // the first argument will never need to be more than a String
    out.append(parameters[0]);
    for (int i = 1; i < parameters.length; i++) {
      String[] parts = parameters[i].split("=");
      if (parts.length == 1) {
        // then this argument is a variable that is passed and should be found somewhere
        if (mem.variableExists(parts[0])) {
          out.append(", \" + " + parts[0] + " + \"");
        } else {
          // TODO: or throw an error here?
          out.append(", " + parts[0]);
        }
      } else // this argument is of kind x = y, look if y is a variable we know
       if (mem.variableExists(parts[1])) {
          out.append(", " + parts[0] + " = \" + " + parts[1] + " + \"");
        } else {
          out.append(", " + parts[0] + " = " + parts[1]);
        }
    }
    out.append(")\")");
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    node.on.visit(this);
    out.append(".");
    node.funccall.visit(this);
  }

  @Override
  public void visitNode(ExpIf node) {
    node.boolexp.visit(this);
    out.append(" ? ");
    node.thenexp.visit(this);
    out.append(" : ");
    node.elseexp.visit(this);
  }

  @Override
  public void visitNode(ExpLambda node) {
    out.append(node.exp);
  }

  @Override
  public void visitNode(GrammarFile node) {

    String oldname = mem.getClassName();
    String oldrule = mem.getCurrentRule();
    String oldTrule = mem.getCurrentTopRule();
    mem.enterClass(out.className);
    //mem.enterNextEnvironment();

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
            + "import org.slf4j.Logger;\n"
            + "import org.slf4j.LoggerFactory;\n\n");
    out.append("public class " + node.classname + "{\n");
    out.append("public static Logger logger = LoggerFactory.getLogger("
            + mem.getClassName() + ".class);\n");
    out.append("// add to this set the name of all rules you want to be logged\n");
    out.append("private Set<String> rulesToLog = new HashSet<>();\n");
    out.append("private Boolean wholeCondition = null;\n\n");
    //        + "\tprivate int returnTo = 0;\n");
    // initialize all return markers
//    for (String k : out.rm.getMarkers()) {
//      out.append("int return_" + k + " = " + out.rm.getMarker(k) + ";\n");
//    }
    // initialize all class attributes before the main process method
    for (RudiTree r : node.rules) {
      if (r instanceof StatAbstractBlock) {
        for (RudiTree e : ((StatAbstractBlock) r).statblock) {
          if (e instanceof ExpAbstractWrapper && (((ExpAbstractWrapper) e).exp instanceof ExpAssignment)) {
            if (((ExpAssignment) ((ExpAbstractWrapper) e).exp).declaration) {
              ((ExpAbstractWrapper) e).exp.visit(this);
              out.append(";");
            }
          } else if (e instanceof ExpAbstractWrapper
                  && (((ExpAbstractWrapper) e).exp instanceof ExpAbstractWrapper
                  && ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp instanceof ExpAssignment)) {
            // then it is a class attribute and we want it to be defined outside
            // of the process method
            if (((ExpAssignment) ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp).declaration) {
              ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp.visit(this);
              out.append(";");
            }
          }
        }
      }
    }
    out.append("\tpublic void process(");
    // get all those classes the toplevel rules need
    int i = 0;
    for (String n : mem.getNeededClasses(out.className)) {
      if (i == 0) {
        out.append(n + " " + n.toLowerCase());
      } else {
        out.append(", " + n + " " + n.toLowerCase());
      }
      i++;
    }
    out.append("){\n");
    // use all methods created from rules in this file
    for (String toplevel : mem.getToplevelCalls(out.className)) {
      if (toplevel.contains("(")) {
        out.append(toplevel);
        Set<String> ncs = mem.getNeededClasses(toplevel.substring(0, toplevel.indexOf(".")));
        if (ncs != null) {
          i = 0;
          for (String c : ncs) {
            if (c.equals(out.className)) {
              c = "this";
            }
            if (i == 0) {
              out.append(c.toLowerCase());
            } else {
              out.append(", " + c.toLowerCase());
            }
            i++;
          }
        }
        out.append(");\n");
        continue;
      }
      out.append(toplevel + "(");
      // don't forget the needed class instances here
      i = 0;
      for (String n : mem.getNeededClasses(toplevel)) {
        if (i == 0) {
          out.append(n.toLowerCase());
        } else {
          out.append(", " + n.toLowerCase());
        }
        i++;
      }
      out.append(");");
    }
    out.append("}\n");
    // Now, only get those statements that are not assignments of class attributes
    for (RudiTree r : node.rules) {
      if (r instanceof StatAbstractBlock) {
        for (RudiTree e : ((StatAbstractBlock) r).statblock) {
          if (e instanceof ExpAbstractWrapper && ((ExpAbstractWrapper) e).exp instanceof ExpAbstractWrapper
                  && ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp instanceof ExpAssignment) {
            if (((ExpAssignment) ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp).declaration) {
              continue;
            }
          } else if (e instanceof ExpAbstractWrapper && (((ExpAbstractWrapper) e).exp instanceof ExpAssignment)) {
            if (((ExpAssignment) ((ExpAbstractWrapper) e).exp).declaration) {
              continue;
            }
          }
          e.visit(this);

        }
      } else {
        r.visit(this);
      }
    }
//    // add all the logger methods
//    for (String l : out.ll.getLogRules()) {
//      this.printRuleLogger(l, out.ll.getCond2log(l));
//    }
    out.append("}\n");
    mem.leaveClass(oldname, oldrule, oldTrule);
    //out.flush();
    // mem.leaveEnvironment();
  }

  @Override
  public void visitNode(GrammarRule node) {
    this.ruleIf = true;
    if (node.toplevel) {
      // this is a toplevel rule and will be converted to a method
      out.append("public void " + node.label + "(");
      // get all the required class instances
      int i = 0;
      for (String n : mem.getNeededClasses(node.label)) {
        if (i == 0) {
          out.append(n + " " + n.toLowerCase());
        } else {
          out.append(", " + n + " " + n.toLowerCase());
        }
        i++;
      }
      out.append("){\n");
      this.printRuleLogger(node.label, node.ifstat);
      out.append(node.label + ":\n");
      //mem.enterNextEnvironment();
      node.comment.visit(this);
      node.ifstat.visit(this);
      //mem.leaveEnvironment();
      out.append("}\n");
    } else {
      // this is a sublevel rule and will get an if to determine whether it should be executed
      out.append("//Rule " + node.label + "\n");
      this.printRuleLogger(node.label, node.ifstat);
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
      node.comment.visit(this);
      node.ifstat.visit(this);
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
        stat.visit(this);
        out.append(";\n");
        break;
      }
      stat.visit(this);
    }
    if (node.braces) {
      out.append("}");
      //mem.leaveEnvironment();
    }
  }

  @Override
  public void visitNode(StatDoWhile node) {
    out.append("do");
    node.statblock.visit(this);
    out.append("while (");
    node.condition.visit(this);
    out.append(");");
  }

  @Override
  public void visitNode(StatFor1 node) {
    out.append("for ( ");
    node.assignment.visit(this);
    out.append("; ");
    node.condition.visit(this);
    out.append(";");
    if (node.arithmetic != null) {
      node.arithmetic.visit(this);
    }
    out.append(");");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor2 node) {
    // TODO: or should we check here that the type of the variable in assignment
    // is the type the iterable in exp returns? How?
    if (node.varType == null) {
      node.varType = ((RTExpression) node.exp).getType();
    }
    out.append("for (" + node.varType + " ");
    node.var.visit(this);
    out.append(": ");
    node.exp.visit(this);
    out.append(") ");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor3 node) {
    out.append("for (Object[] o : ");
    node.exp.visit(this);
    out.append(") {");
    int count = 0;
    for (String s : node.variables) {
      out.append("\nObject " + s + " = o[" + count++ + "]");
    }
    node.statblock.visit(this);
    out.append("}");
  }

  @Override
  public void visitNode(StatFunDef node) {
    // no generation here
  }

  @Override
  public void visitNode(StatIf node) {
    if (node.statblockElse != null) {
      out.append("if (");
      node.condition.visit(this);
      out.append(") ");
      node.statblockIf.visit(this);
      out.append("else");
      node.statblockElse.visit(this);
    } else {
      if (this.ruleIf) {
        out.append("if ((wholeCondition != null) ? wholeCondition : ");
        ruleIf = false;
      } else {
        out.append("if (");
      }
      node.condition.visit(this);
      out.append(") ");
      node.statblockIf.visit(this);
    }
  }

  @Override
  public void visitNode(StatImport node) {
    logger.info("Processing import " + node.text);
    try {
      RudimantCompiler.getEmbedded(out).process(node.text);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
//    out.append(node.text + ".process(");
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
    System.out.println("Rudi was here");
    out.append("List<" + node.listType + "> " + node.variableName + " = new ArrayList<>();");
    for (RTExpression e : node.objects) {
      out.append(node.variableName + ".add(");
      this.visitNode(e);
      out.append(");\n");
    }
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    //mem.enterNextEnvironment();
    String ret = node.visibility + " " + node.return_type + " " + node.name + "(";
    if (!node.parameters.isEmpty()) {
      for (int i = 0; i < node.parameters.size(); i++) {
        if (i == 1) {
          ret += node.partypes.get(i) + " " + node.parameters.get(i);
        } else {
          ret += ", " + node.partypes.get(i) + " " + node.parameters.get(i);
        }
      }
    }
    ret += ")";
    out.append(ret + "\n");
    node.block.visit(this);
    //mem.leaveEnvironment();
  }

  @Override
  public void visitNode(StatPropose node) {
    out.append("propose(");
    node.arg.visit(this);
    out.append(", new Proposal() {public void run()\n");
    node.block.visit(this);
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
    node.toRet.visit(this);
    out.append(";\n");
  }

  @Override
  public void visitNode(StatSetOperation node) {
    node.left.visit(this);
    if (node.add) {
      out.append(".add(");
    } else {
      out.append(".remove(");
    }
    node.right.visit(this);
    out.append(");");
  }

  @Override
  public void visitNode(StatTimeout node) {
    if (node.statblock == null) {
      out.append("newTimeout(" + node.name + "," + node.time + ");\n");
    }
    out.append("MyTimer t = newSpecialTimeout(" + node.name + "," + node.time + ");"
            + "t.timer = new Timer(timeToFire, new ActionListener(){\n"
            + "@Override\n public void actionPerformed(ActionEvent e)" + node.statblock + "});"
            + "\n" + "t.started = System.currentTimeMillis();\n"
            + " t.timer.start();\n");
  }

  @Override
  public void visitNode(StatVarDef node) {
    // no generation here
  }

  @Override
  public void visitNode(StatWhile node) {
    out.append("while (");
    node.condition.visit(this);
    out.append(")");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(UCharacter node) {
    out.append("\'" + node.content + "\'" + " ");
  }

  @Override
  public void visitNode(UComment node) {
    out.append(node.comment + " ");
  }

  @Override
  public void visitNode(UCommentBlock node) {
    for (UComment c : node.comments) {
      c.visit(this);
      out.append("\n");
    }
  }

  @Override
  public void visitNode(UFieldAccess node) {
    // TODO: tell me how the client is named!!!
    out.append(node.representation.get(0));
    for (int i = 1; i < node.representation.size(); i++) {
      out.append(".getValue(" + node.representation.get(i) + ", client)" + " ");
    }
  }

  @Override
  public void visitNode(UFuncCall node) {
    out.append(node.representation + "(");
    for (int i = 0; i < node.exps.size(); i++) {
      node.exps.get(i).visit(this);
      if (i != node.exps.size() - 1) {
        out.append(", ");
      }
    }
    out.append(")" + " ");
  }

  @Override
  public void visitNode(UNull node) {
    out.append("null" + " ");
  }

  @Override
  public void visitNode(UNumber node) {
    out.append(node.value);
  }

  @Override
  public void visitNode(UString node) {
    if (this.escape) {
      out.append("\\" + node.content.substring(0, node.content.length() - 1) + "\\\"" + " ");
    } else {
      out.append(node.content + " ");
    }
  }

  @Override
  public void visitNode(UVariable node) {
    // if the variable is not in the memory,
    if (node.realOrigin != null) {
      out.append(node.realOrigin.toLowerCase() + "." + node.representation + " ");
      return;
    }
    out.append(node.representation + " ");
  }

  @Override
  public void visitNode(UWildcard node) {
    out.append("this.wildcard" + " ");   // wildcard is a local variable in resulting class
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    out.append(node.content + " ");
  }

  private void conditionHandling(ExpBoolean node) {
    out.append(node.isTrue + " ");
  }

  /**
   * creates and prints the logging method of the given rule
   *
   * @param rule
   */
  private void printRuleLogger(String rule, ExpBoolean bool_exp) {
    if (rule != null) {
      out.append("if (rulesToLog.contains(\"" + rule + "\")){\n");
      // do all that logging
      printRuleLogger(null, bool_exp);
      out.append("}\n");
    } else {
      // Neuen Visitor erzeugen, der condition ablaufen kann, kriegt als info die CurrentRule aus Mem (die zur Generierung der Zwischenspeichervariablen
      // benutzt wird), eine Linked HashMap, in die er einträgt <Zwischenspeicher als String, <urspr. Ausdruck als String, boolean Ergebnis>>
      LinkedHashMap<String,<String,Boolean>> condLog = new LinkedHashMap<>();
      condV.renewMap(bool_exp.rule, condLog);
      condV.visitNode(bool_exp);

      // Danach ablaufen, im Visitor hinschreiben was zu loggen ist

      // Variable wholeCondition wird dann auf das letzte Element der Keylist der Map gesetzt
      out.append("wholeCondition = " + condV.getBiggestExp() + ";\n");
    }
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

  public void dummyLoggingMethod(String rule, String className, Map toLog){
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

}