/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

// TODO: test whether variable exists?
/**
 * representation of a variable
 *
 * @author Anna Welker
 */
public class UVariable extends RTLeaf {

  String representation;
  String originClass;
  // set to the class whose attribute this variable is; null if the variable
  // is declared in originClass
  String realOrigin;
  boolean isRdfClass;

  public UVariable(String type, String representation, String originClass,
          String originTRule) {
    this.type = type;
    this.representation = representation;
    this.originClass = originClass;
  }

  public UVariable(String representation, String originClass, String originTRule
  ) {
    this.representation = representation;
    this.originClass = originClass;
  }

  @Override
  public String toString() {
    return this.representation;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
