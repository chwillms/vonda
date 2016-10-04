/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * this is an arithmetic expression with an operator, two expressions left and
 * right, and eventually if this is no complex term but a single number there is
 * a - in front of it
 *
 * @author Anna Welker
 */
public class ExpArithmetic implements RTExpression {

  RTExpression left;
  RTExpression right;
  String type;
  String operator;
  boolean minus;

  /**
   * if the expression consists of only one part, set right and operator to null
   *
   * @param left the left part
   * @param right the right part
   * @param operator the operator in between
   * @param minus set true if there is a minus in front of the arithmetic
   */
  public ExpArithmetic(RTExpression left,
          RTExpression right, String operator, boolean minus) {
    this.left = left;
    this.right = right;
    this.operator = operator;
    this.minus = minus;
    this.type = left.getType();
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 73 * hash + Objects.hashCode(this.left);
    hash = 73 * hash + Objects.hashCode(this.right);
    hash = 73 * hash + Objects.hashCode(this.operator);
    hash = 73 * hash + (this.minus ? 1 : 0);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ExpArithmetic other = (ExpArithmetic) obj;
    if (this.minus != other.minus) {
      return false;
    }
    if (!Objects.equals(this.operator, other.operator)) {
      return false;
    }
    if (!Objects.equals(this.left, other.left)) {
      return false;
    }
    return Objects.equals(this.right, other.right);
  }

  @Override
  public void setType(String to) {
    this.type = to;
  }

}