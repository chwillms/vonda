/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.LinkedHashMap;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VRuleConditionVisitor implements RudiVisitor {

  private String currentRule;
  private LinkedHashMap<String, <String,Boolean>> condLog;
  private String biggestExp;

  public String getBiggestExp() {
    return biggestExp;
  }

  public void renewMap(String rule, LinkedHashMap<String, <String,Boolean
    >> condLog){
    this.condLog = condLog;
    this.currentRule = rule;
  }

  @Override
  public void visitNode(RudiTree node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpAbstractWrapper node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpAssignment node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpBoolean node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpIf node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpLambda node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(GrammarFile node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(GrammarRule node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatDoWhile node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFor1 node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFor2 node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFor3 node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFunDef node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatIf node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatImport node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatListCreation node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatPropose node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatReturn node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatSetOperation node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatTimeout node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatVarDef node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatWhile node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UCharacter node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UComment node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UCommentBlock node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UFieldAccess node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UFuncCall node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UNull node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UNumber node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UString node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UVariable node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UWildcard node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
