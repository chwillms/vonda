/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Constants.DIALOGUE_ACT_TYPE;

import java.util.ArrayList;
import java.util.List;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * this class represents dialogue acts
 *
 * @author Anna Welker
 */
public class ExpDialogueAct extends RTExpression {

  // comment LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR comment
  RTExpression daType;
  RTExpression proposition;
  List<RTExpression> exps;

  public ExpDialogueAct(RTExpression da,
      RTExpression prop, List<RTExpression> args) {
    daType = da;
    proposition = prop;
    exps = args;
    type = new Type(DIALOGUE_ACT_TYPE);
  }

  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    List<RudiTree> dtrs = new ArrayList<>();
    dtrs.add(daType);
    dtrs.add(proposition);
    dtrs.addAll(exps);
    return dtrs;
  }

  public void propagateType(Type upperType) {
    logger.error("Why didn't this type percolate up? " + fullexp + " " + type);
  }
}