/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import de.dfki.mlt.rudimant.Type;

/**
 * representation of a variable
 *
 * @author Anna Welker
 */
public class ExpUVariable extends RTExpLeaf {

  public ExpUVariable(String type, String representation) {
    this(representation);
    this.type = new Type(type);
  }

  public ExpUVariable(String representation) {
    content = representation;
    type = Type.getNoType();
  }

  @Override
  public String toString() {
    return this.content;
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
  public void visitVoidV(VGenerationVisitor v) {
    v.out.append(v.visitNode(this));
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }

  public void propagateType(Type upperType) {
    // it's not an error if the type is null
    type = upperType;
  }
}
