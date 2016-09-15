/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author pal
 */
public class VConditionCreatorVisitor implements RudiVisitor {

  // map the new variables to what they represent
  private Object[] expNames;
  int counter = 0;
  StringBuffer condition;
  StringBuffer creation;

  LinkedHashMap<String, String> compiledLook;

  public void newMap(Object[] expNames, LinkedHashMap<String, String> compiledLook) {
    this.expNames = expNames;
    condition = new StringBuffer();
    creation = new StringBuffer();
    counter = 0;
    this.compiledLook = compiledLook;
  }

  public StringBuffer getBoolCreation() {
    return this.creation;
  }

  public StringBuffer getCondition() {
    return this.condition;
  }

  @Override
  public void visitNode(RudiTree node) {
    node.visit(this);
  }

  @Override
  public void visitNode(ExpAbstractWrapper node) {
    node.exp.visit(this);
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    throw new UnsupportedOperationException("Not supported yet.");
//    creation.append(compiledLook.get(expNames[counter]));
//    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(ExpAssignment node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  private boolean not = false;

  @Override
  public void visitNode(ExpBoolean node) {
    System.out.println(node.fullexp);
    String n = "";
    if (node.not  || this.not) {
      this.condition.append("!");
      n = "!";
      this.not = false;
    }
    if (node.doesSubsume || node.isSubsumed) {
      this.condition.append(expNames[counter]);
      this.creation.append("boolean " + expNames[counter] + " = " + n 
              + compiledLook.get(expNames[counter]) + ";\n");
      counter++;
      return;
    }
    if (node.right != null) {
//      if (node.left.getType() == null // then this is probably an rdf
//              || !node.left.getType().equals("boolean")) {
//        this.condition.append(expNames[counter++]);
//        return;
//      }
      if (!(node.operator.equals("||") || node.operator.equals("&&"))) {
        // we do not go deeper
        this.creation.append("boolean " + expNames[counter] + " = " + n
                + compiledLook.get(expNames[counter]) + ";\n");
        this.condition.append(expNames[counter++]);
        return;
      } // else
      this.condition.append("(");
      this.creation.append("(");
      this.visitNode(node.left);
      condition.append(node.operator);
      creation.append(node.operator);

      if (node.operator.equals("||")) {
        this.creation.append("if (!" + expNames[counter - 1] + "){\n");
        this.visitNode(node.right);
        this.condition.append(")");
        this.creation.append(")");
        this.creation.append("} else {\n");
        this.creation.append("boolean " + expNames[counter++] + " = false;\n");
      } else if (node.operator.equals("&&")) {
        this.creation.append("if (" + expNames[counter - 1] + "){\n");
        this.visitNode(node.right);
        this.condition.append(")");
        this.creation.append(")");
        this.creation.append("} else {\n");
        this.creation.append("boolean " + expNames[counter++] + " = false;\n");
      }
    } else {
      if(node.not){
        this.not = true;
      }
      this.visitNode(node.left);
    }
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    creation.append(compiledLook.get(expNames[counter]));
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(ExpIf node) {
    creation.append(compiledLook.get(expNames[counter]));
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(ExpLambda node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(GrammarFile node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(GrammarRule node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatDoWhile node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFor1 node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFor2 node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFor3 node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFunDef node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatIf node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatImport node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatListCreation node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatPropose node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatReturn node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatSetOperation node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatTimeout node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatVarDef node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatWhile node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UCharacter node) {
    creation.append(compiledLook.get(expNames[counter]));
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UComment node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UCommentBlock node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(UFieldAccess node) {
    creation.append(compiledLook.get(expNames[counter]));
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UFuncCall node) {
    creation.append(compiledLook.get(expNames[counter]));
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UNull node) {
    creation.append(compiledLook.get(expNames[counter]));
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UNumber node) {
    creation.append(compiledLook.get(expNames[counter]));
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UString node) {
    creation.append(compiledLook.get(expNames[counter]));
    System.out.println(counter + " listlenght " + expNames.length);
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UVariable node) {
    creation.append(compiledLook.get(expNames[counter]));
    System.out.println(counter + " listlenght " + expNames.length + " ["
            + node.representation + "]");
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UWildcard node) {
    creation.append(compiledLook.get(expNames[counter]));
    condition.append(expNames[counter++]);
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    creation.append(compiledLook.get(expNames[counter]));
    condition.append(expNames[counter++]);
  }

}
