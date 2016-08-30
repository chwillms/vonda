/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

/**
 *
 * @author anna
 */
public class AMethodDeclaration implements AbstractTree {

  private String visibility;
  private String return_type;
  private String name;
  private HashMap<String, String> parameters_to_types;
  private AbstractTree block;

  public AMethodDeclaration(String visibility, String return_type, String name,
          HashMap<String, String> parameters_to_types, AbstractTree block) {
    this.visibility = visibility;
    this.return_type = return_type;
    this.name = name;
    this.parameters_to_types = parameters_to_types;
    this.block = block;
  }

  @Override
  public void generate(Writer out) throws IOException {
    String ret = visibility + " " + return_type + " " + name + "(";
    if (!parameters_to_types.isEmpty()) {
      int i = 0;
      for (String s : parameters_to_types.keySet()) {
        i++;
        if (i == 1) {
          ret += this.parameters_to_types.get(s) + " " + s;
        } else {
          ret += ", " + this.parameters_to_types.get(s) + " " + s;
        }
      }
    }
    ret += ")";
    out.append(ret + "\n");
    block.generate(out);
  }

  @Override
  public void testType() {
    // ...
  }
}
