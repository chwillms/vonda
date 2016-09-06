/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.List;


/**
 * this is used to represent a set of comments
 *
 * @author Anna Welker
 */
public class UCommentBlock implements RTStatement {

  List<UComment> comments;

  public UCommentBlock(List<UComment> comments) {
    this.comments = comments;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}