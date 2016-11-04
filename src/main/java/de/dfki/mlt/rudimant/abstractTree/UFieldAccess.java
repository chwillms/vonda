/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.List;
import java.util.Objects;

import org.apache.thrift.TException;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.mlt.rudimant.Mem;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class UFieldAccess extends RTLeaf {

  String type;
  List<RudiTree> parts;
  List<String> representation;
  boolean asked = false;

  public UFieldAccess(List<RudiTree> parts, List<String> representation) {
    this.parts = parts;
    this.representation = representation;
  }

  public String getType(Mem mem) {
    return this.type;
  }

  /**
   * ask the ontology about the type of this object
   *
   * @param proxy
   * @param mem
   * @param representation
   * @return the type of this object
   * @throws org.apache.thrift.TException
   */
  public String getPredicateType(RdfProxy proxy, Mem mem,
          List<String> representation) throws TException {
    asked = true;
    // first element of representation is type
    // everything else specifies the wanted predicate information
    String typ = mem.getVariableType(representation.get(0));
    RdfClass clazz = proxy.fetchRdfClass(typ);
    if(clazz == null){
      // then this is no rdf type; just assume/hope that it is a valid access to sth else
      // TODO: we should probably crash if we do not recognize this combination
      return "Object";
    }
    List<String> predicatesBaseName
            = representation.subList(1, representation.size());
    List<String> predicatesURI = clazz.getPredicateType(predicatesBaseName);
    if (predicatesURI == null)
      return null;
    return predicatesURI.get(predicatesURI.size() - 1);
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + Objects.hashCode(this.representation);
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
    final UFieldAccess other = (UFieldAccess) obj;
    if (!Objects.equals(this.representation, other.representation)) {
      return false;
    }
    return true;
  }

  @Override
  public void setType(String to) {
    this.type = to;
  }
}
