/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.ArrayList;

/**
 * type_spec VARIABLE LPAR
    ( type_spec VARIABLE (COMMA type_spec VARIABLE)* )? RPAR SEMICOLON
 * = only to get the types of this function into memory
 *
 * @author Anna Welker
 */
public class StatFunDef implements RTStatement, RudiTree{

  String funcname;
  String type;
  ArrayList<String> parameters;
  ArrayList<String> parameterTypes;
  String position;

  public StatFunDef(String type, String funcname, ArrayList<String> parameters,
          ArrayList<String> parameterTypes, String position){
    this.type = type;
    this.funcname = funcname;
    this.position = position;
    this.parameters = parameters;
    this.parameterTypes = parameterTypes;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}