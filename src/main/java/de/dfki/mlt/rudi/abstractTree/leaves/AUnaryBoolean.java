/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.leaves;

import de.dfki.mlt.rudi.abstractTree.*;
import java.io.IOException;
import java.io.Writer;

/**
 * this represents either true or false
 *
 * @author Anna Welker
 */
public class AUnaryBoolean extends AbstractLeaf{

  // true or false
  private String content;

  public AUnaryBoolean(String content) {
    this.content = content;
  }

  @Override
  public void testType() {
    // everything okay
  }

  @Override
  public void generate(Writer out) throws IOException{
    out.append(this.content);
  }

  @Override
  public String getType() {
    return "boolean";
  }

  @Override
  public void returnManaging() {
    // nothing to do
  }
}
