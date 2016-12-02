package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.dfki.lt.loot.Pair;
import de.dfki.lt.loot.gui.adapters.MapAdapterIterator;
import de.dfki.lt.loot.gui.adapters.ModelAdapter;
import de.dfki.lt.loot.gui.adapters.ModelAdapterFactory;

public class TreeModelAdapter extends ModelAdapter {

  public static void init() {
    ModelAdapterFactory.registerAdapter(RudiTree.class, TreeModelAdapter.class);
  }

  private class EdgeAdapterIterator implements MapAdapterIterator {
    Map<String, String> _values;

    Iterator<String> _it;

    public EdgeAdapterIterator(RudiTree node) {
      if (node instanceof RTExpression) {
        _values = new HashMap<>();
        RTExpression exp = (RTExpression)node;
        _values.put("type", exp.type + (exp.isRdfType() ? "[R]" : ""));
      }
      if (_values == null) {
        _values = Collections.emptyMap();
      }
      _it = _values.keySet().iterator();
    }


    public boolean hasNext() { return _it.hasNext(); }

    public Pair<Object, Object> next() {
      String key = _it.next();
      return new Pair<Object, Object>(key, _values.get(key));
    }
  }

  private class MapMarker {
    RudiTree _root;
    MapMarker(RudiTree root) { _root = root; }
  }

  @Override
  public int facets(Object model) {
    if (model instanceof MapMarker) {
      return ModelAdapter.MAP;
    }
    if (model instanceof RudiTree) {
      RudiTree node = (RudiTree) model;
    /*
      if (node.getNewType() == RudiTree.getGrammar().getConsType())
        return ModelAdapter.MAP | ModelAdapter.CONS;
      if (node.getNewType() == RudiTree.getGrammar().getNullType())
        return ModelAdapter.MAP | ModelAdapter.NULL;
      if (node.getNthArg(0) != null)
        return ModelAdapter.MAP | ModelAdapter.TREE;
      return ModelAdapter.MAP;
*/
      return ModelAdapter.SYMBOL | ModelAdapter.TREE;
    }
    if (model instanceof String) {
      return ModelAdapter.ATOM;
    }
    return ModelAdapter.NONE;
  }

  public String getString(Object model) {
    String result;
    if (model instanceof RTBinaryExp) {
      result = ((RTBinaryExp)model).operator;
    } else if (model instanceof StatReturn) {
      result = "return";
    } else if (model instanceof GrammarRule) {
      result = ((GrammarRule)model).label + ":";
    } else if (model instanceof StatIf) {
      result = "if";
    } else if (model instanceof ExpDialogueAct) {
      result = "DialAct";
    } else if (model instanceof ExpAssignment) {
      result = ":=";
    } else if (model instanceof ExpIf) {
      result = "_ ? _ : _";
    } else if (model instanceof StatPropose) {
      result = "propose";
    } else if (model instanceof UFieldAccess) {
      result = "FieldAcc";
    } else if (model instanceof StatMethodDeclaration) {
      StatMethodDeclaration md = (StatMethodDeclaration)model;
      result = "meth " + ((md.return_type != null) ? md.return_type + " ": "")
          + md.name + '(';
      if (! md.parameters.isEmpty()) {
        if (md.partypes.get(0) != null) { result += md.partypes.get(0) + " "; }
        result += md.parameters.get(0);
      }
      for (int i = 1; i < md.parameters.size(); ++i) {
        result += ", ";
        if (md.partypes.get(i) != null) { result += md.partypes.get(i) + " "; }
        result += md.parameters.get(i);
      }
      result += ')';
    } else if (model instanceof StatAbstractBlock) {
      if (((StatAbstractBlock)model).braces)
        result = "{ _ }";
      else
        result = "block";
    } else if (model instanceof UFuncCall) {
      result = ((UFuncCall)model).content + "( )";
    } else {
      if (null == model) {
        result = "<null>";
      } else {
        result = model.toString();
      }
    }
    if (model instanceof RTExpression) {
      RTExpression exp = (RTExpression)model;
      if (exp.type != null)
        result += "\n[" + exp.type + (exp.isRdfType() ? "[R]]" : "]");
    }
    return result;
  }

  @Override
  public String getAttribute(Object model, String name) {
    RudiTree node;
    if (model instanceof MapMarker) {
      node = ((MapMarker) model)._root;
    } else if (model instanceof RudiTree) {
      node = (RudiTree) model;
    } else {
      return null;
    }
    return null;
  }

  @Override
  public Object getNodeContent(Object model) {
    //return new MapMarker((RudiTree) model);
    return model;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Iterable getTreeDaughters(Object model) {
    // if (!(model instanceof TreeMarker)) return null;
    RudiTree tree = (RudiTree) model;
    return tree.getDtrs();
  }


  //@Override
  //public boolean isNull(Object model) { return false; }

  /**/
  @Override
  public MapAdapterIterator mapIterator(Object model) {
    if (model instanceof MapMarker) {
      return new EdgeAdapterIterator(((MapMarker)model)._root);
    }
    return new EdgeAdapterIterator((RudiTree) model);
  }/**/
}