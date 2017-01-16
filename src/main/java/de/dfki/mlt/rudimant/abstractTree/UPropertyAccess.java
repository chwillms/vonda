/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class UPropertyAccess extends RTExpLeaf {

  UVariable label;
  boolean propertyVariable = false;
  String rangeType;
  boolean functional;


  public UPropertyAccess(UVariable l, boolean var, String rt,
      boolean func) {
    label = l;
    propertyVariable = var;
    rangeType = rt;
    functional = func;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { label };
    return Arrays.asList(dtrs);
  }
}
