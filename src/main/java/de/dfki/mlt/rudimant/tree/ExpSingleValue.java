/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import de.dfki.mlt.rudimant.Type;

/**
 * class representing a simple value like a String, an int or null
 * @author Anna Welker
 */
public class ExpSingleValue extends RTExpLeaf {

  public ExpSingleValue(String representation, String type){
    this.content = representation;
    this.type = new Type(type);
  }

  @Override
  public void visit(RTExpressionVisitor v) {
    v.visitNode(this);
  }

  /**
   * if we are an expression but this method is called, we should write to out;
   * it means that the instance calling us must be a statement
   * @param v
   */
  @Override
  public void visitVoidV(VisitorGeneration v) {
    v.out.append(v.visitNode(this));
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }

}