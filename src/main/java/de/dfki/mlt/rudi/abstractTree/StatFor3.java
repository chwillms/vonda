/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.List;


/**
 * FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR
 * loop_statement_block
 * = a special for statement allowing tuples of arbitrary size
 *
 * @author Anna Welker
 */
public class StatFor3 implements RTStatement, RudiTree {

  List<String> variables;
  RudiTree exp;
  StatAbstractBlock statblock;
  String position;

  public StatFor3(List<String> variables, RudiTree exp,
          StatAbstractBlock block, String position) {
    this.variables = variables;
    this.exp = exp;
    this.statblock = block;
    this.position = position;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}