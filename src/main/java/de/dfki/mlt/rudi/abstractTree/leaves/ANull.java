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
 *
 * @author anna
 */
public class ANull extends AbstractLeaf {

  @Override
  public void testType() {
    // nothing to do
  }

  @Override
  public String getType() {
    return "Object";   // for you can assign null to anything
  }

  @Override
  public void generate(Writer out) throws IOException{
    out.append("null");
  }

}