package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.TypeException;
import de.dfki.mlt.rudimant.compiler.tree.ExpAssignment;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;
import de.dfki.mlt.rudimant.compiler.tree.StatExpression;


public class TestTypeInference {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void test() {
    String boolexp = ""
            + "public void foo() { b = 5 < 1; }";
    String s = generate(boolexp);
    String expected = "public void foo() { boolean b = (5 < 1);";
    assertEquals(expected, getForMarked(s, expected));


    // can't be tested like that because the memory is now handled correctly
    // and after leaving the last block is empty.
    /*
    parseAndTypecheck(boolexp);
    String type = rc.getMem().getVariableType("b");
    assertTrue(type.equals("boolean"));
    */
  }

  @Test
  public void testPredefFn() {
    String in = "boolean firstEncounter(); b = firstEncounter();";
    GrammarFile gf = parseAndTypecheck(in);
    Iterator<? extends RudiTree> it = gf.getDtrs().iterator();
    it.next();
    it.next();
    RudiTree rt = it.next();
    assertTrue(rt instanceof StatExpression);
    assertTrue(((StatExpression)rt).expression instanceof ExpAssignment);
    ExpAssignment ass = (ExpAssignment)((StatExpression)rt).expression;
    assertEquals("boolean", ass.left.type.toJava());
  }

  @Test
  public void test3() {
    String in = "QuizHistory getCurrentTurn();"
            + " turn = getCurrentTurn(); ";
    String s = generate(in);
    String expected = "Rdf turn = getCurrentTurn();";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf turn;"));
  }


  @Test
  public void test3a() {
    String in = "QuizHistory getCurrentTurn(); "
            + "Rdf turn = getCurrentTurn();";
    String s = generate(in);
    String expected = "Rdf turn = getCurrentTurn();";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf turn"));
  }

  @Test
  public void test4() {
    String in = "Activity a; if (a) { }";
    String s = generate(in);
    String expected = "if (a != null) { }";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf a;"));
  }

  @Test
  public void test5() {
    String in = "String foo(); "
            + "boolean b = foo(); ";
    String s = generate(in);
    String expected = "b = exists(foo());";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("boolean b;"));
  }

  @Test
  public void test6() {
    String in = " boolean b = true; ";
    String s = generate(in);
    String expected = "b = true;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("boolean b;"));
  }

  @Test
  public void test7() {
    String in = "public int b = 7; ";
    String s = generate(in);
    String expected = "b = 7;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("int b;"));
  }

  @Test
  public void test8() {
    String in = " Quiz q; if(q.quizbool) i = 7; ";
    String s = generate(in);
    String expected = "if ((q != null && "
        + "((Boolean)q.getSingleValue(\"<dom:quizbool>\")))) int i = 7;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf q;"));
  }

  @Test
  public void test9() {
    String in = "Quiz q;if(q.tabletOrientation) i = 7; ";
    String s = generate(in);
    String expected = "if ((q != null && "
        + "exists(((String)q.getSingleValue(\"<dom:tabletOrientation>\")))))"
        + " int i = 7;";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("Rdf q;"));
  }

  /** TODO: Fix this problem (issue #55)
   * aw: wouldn't an entry like [Rdf]. boolean has(String sth) in Agent.rudi fix this?
  @Test
  public void testUnknownBoolean() {
    String in = "Rdf r; if(r.has(\"prop\")) i = 1;";
    String s = generate(in);
    String expected = "if ((r != null && r.has(\"prop\"))) int i = 1; ";
    assertEquals(expected, getForMarked(s, expected));
  }
  */

  @Test
  public void test10() {
    String in = " double f; void fun() { Clazz c; c.bf = 1.0; }";
    String s = generate(in);
    String expected = "double f; public void fun() { Rdf c;c.setValue(\"<dom:bf>\", 1.0); } }";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("double f;"));
  }

  @Test(expected=TypeException.class)
  public void test11() throws Throwable {
    String in = " int fun(int i); long l = 1; k = fun(l);";
    getTypeError(in);
  }

  @Test
  public void test12() {
    String in = " long fun(long i); int l = 1; k = fun(l);";
    String s = generate(in);
    String expected = "int l = 1;long k = fun(l);";
    assertEquals(expected, getForMarked(s, expected));
  }
}
