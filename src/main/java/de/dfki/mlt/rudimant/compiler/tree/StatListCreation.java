/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.ArrayList;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class StatListCreation extends RTStatement {

  ArrayList<RTExpression> objects;
  String variableName;
  Type listType;

  public StatListCreation(String varName, ArrayList<RTExpression> objs) {
    objects = objs;
    variableName = varName;
    listType = Type.getNoType();
  }

  public StatListCreation(String varName, ArrayList<RTExpression> objs,
      String lType) {
    objects = objs;
    variableName = varName;
    listType = new Type(lType);
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    return objects;
  }
}