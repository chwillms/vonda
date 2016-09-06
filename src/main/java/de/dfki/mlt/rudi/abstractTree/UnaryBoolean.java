/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 * this represents either true or false
 *
 * @author Anna Welker
 */
public class UnaryBoolean extends RTLeaf{

  // true or false
  String content;

  public UnaryBoolean(String content) {
    this.content = content;
  }

  @Override
  public String getType() {
    return "boolean";
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}