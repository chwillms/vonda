/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2015 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

package de.dfki.mlt.rudimant.compiler.io;
/* First part of user declarations.  */

/* "VondaGrammar.java":37  */ /* lalr1.java:91  */

/* "VondaGrammar.java":39  */ /* lalr1.java:92  */
/* "%code imports" blocks.  */
/* "VondaGrammar.y":3  */ /* lalr1.java:93  */


import java.io.Reader;
import java.util.*;
import de.dfki.mlt.rudimant.compiler.Type;
import de.dfki.mlt.rudimant.compiler.Position;
import de.dfki.mlt.rudimant.compiler.tree.*;

@SuppressWarnings({"serial", "unchecked", "fallthrough", "unused"})

/* "VondaGrammar.java":52  */ /* lalr1.java:93  */

/**
 * A Bison parser, automatically generated from <tt>VondaGrammar.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class VondaGrammar
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.0.4";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";


  /**
   * True if verbose error messages are enabled.
   */
  private boolean yyErrorVerbose = true;

  /**
   * Return whether verbose error messages are enabled.
   */
  public final boolean getErrorVerbose() { return yyErrorVerbose; }

  /**
   * Set the verbosity of error messages.
   * @param verbose True to request verbose error messages.
   */
  public final void setErrorVerbose(boolean verbose)
  { yyErrorVerbose = verbose; }



  /**
   * A class defining a pair of positions.  Positions, defined by the
   * <code>Position</code> class, denote a point in the input.
   * Locations represent a part of the input through the beginning
   * and ending positions.
   */
  public class Location {
    /**
     * The first, inclusive, position in the range.
     */
    public Position begin;

    /**
     * The first position beyond the range.
     */
    public Position end;

    /**
     * Create a <code>Location</code> denoting an empty range located at
     * a given point.
     * @param loc The position at which the range is anchored.
     */
    public Location (Position loc) {
      this.begin = this.end = loc;
    }

    /**
     * Create a <code>Location</code> from the endpoints of the range.
     * @param begin The first position included in the range.
     * @param end   The first position beyond the range.
     */
    public Location (Position begin, Position end) {
      this.begin = begin;
      this.end = end;
    }

    /**
     * Print a representation of the location.  For this to be correct,
     * <code>Position</code> should override the <code>equals</code>
     * method.
     */
    public String toString () {
      if (begin.equals (end))
        return begin.toString ();
      else
        return begin.toString () + "-" + end.toString ();
    }
  }



  
  private Location yylloc (YYStack rhs, int n)
  {
    if (n > 0)
      return new Location (rhs.locationAt (n-1).begin, rhs.locationAt (0).end);
    else
      return new Location (rhs.locationAt (0).end);
  }

  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>VondaGrammar</tt>.
   */
  public interface Lexer {
    /** Token returned by the scanner to signal the end of its input.  */
    public static final int EOF = 0;

/* Tokens.  */
    /** Token number,to be returned by the scanner.  */
    static final int BREAK = 258;
    /** Token number,to be returned by the scanner.  */
    static final int CANCEL = 259;
    /** Token number,to be returned by the scanner.  */
    static final int CANCEL_ALL = 260;
    /** Token number,to be returned by the scanner.  */
    static final int CASE = 261;
    /** Token number,to be returned by the scanner.  */
    static final int CONTINUE = 262;
    /** Token number,to be returned by the scanner.  */
    static final int DEFAULT = 263;
    /** Token number,to be returned by the scanner.  */
    static final int DO = 264;
    /** Token number,to be returned by the scanner.  */
    static final int ELSE = 265;
    /** Token number,to be returned by the scanner.  */
    static final int FINAL = 266;
    /** Token number,to be returned by the scanner.  */
    static final int FOR = 267;
    /** Token number,to be returned by the scanner.  */
    static final int IF = 268;
    /** Token number,to be returned by the scanner.  */
    static final int IMPORT = 269;
    /** Token number,to be returned by the scanner.  */
    static final int NEW = 270;
    /** Token number,to be returned by the scanner.  */
    static final int NULL = 271;
    /** Token number,to be returned by the scanner.  */
    static final int PRIVATE = 272;
    /** Token number,to be returned by the scanner.  */
    static final int PROPOSE = 273;
    /** Token number,to be returned by the scanner.  */
    static final int PROTECTED = 274;
    /** Token number,to be returned by the scanner.  */
    static final int PUBLIC = 275;
    /** Token number,to be returned by the scanner.  */
    static final int RETURN = 276;
    /** Token number,to be returned by the scanner.  */
    static final int SWITCH = 277;
    /** Token number,to be returned by the scanner.  */
    static final int TIMEOUT = 278;
    /** Token number,to be returned by the scanner.  */
    static final int TIMEOUT_BEHAVIOUR = 279;
    /** Token number,to be returned by the scanner.  */
    static final int WHILE = 280;
    /** Token number,to be returned by the scanner.  */
    static final int ARROW = 281;
    /** Token number,to be returned by the scanner.  */
    static final int ANDAND = 282;
    /** Token number,to be returned by the scanner.  */
    static final int OROR = 283;
    /** Token number,to be returned by the scanner.  */
    static final int EQEQ = 284;
    /** Token number,to be returned by the scanner.  */
    static final int NOTEQ = 285;
    /** Token number,to be returned by the scanner.  */
    static final int GTEQ = 286;
    /** Token number,to be returned by the scanner.  */
    static final int LTEQ = 287;
    /** Token number,to be returned by the scanner.  */
    static final int MINUSEQ = 288;
    /** Token number,to be returned by the scanner.  */
    static final int PLUSEQ = 289;
    /** Token number,to be returned by the scanner.  */
    static final int MINUSMINUS = 290;
    /** Token number,to be returned by the scanner.  */
    static final int PLUSPLUS = 291;
    /** Token number,to be returned by the scanner.  */
    static final int STRING = 292;
    /** Token number,to be returned by the scanner.  */
    static final int WILDCARD = 293;
    /** Token number,to be returned by the scanner.  */
    static final int INT = 294;
    /** Token number,to be returned by the scanner.  */
    static final int BOOL_LITERAL = 295;
    /** Token number,to be returned by the scanner.  */
    static final int VARIABLE = 296;
    /** Token number,to be returned by the scanner.  */
    static final int OTHER_LITERAL = 297;


    /**
     * Method to retrieve the beginning position of the last scanned token.
     * @return the position at which the last scanned token starts.
     */
    Position getStartPos ();

    /**
     * Method to retrieve the ending position of the last scanned token.
     * @return the first position beyond the last scanned token.
     */
    Position getEndPos ();

    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    Object getLVal ();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * and beginning/ending positions of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex () throws java.io.IOException;

    /**
     * Entry point for error reporting.  Emits an error
     * referring to the given location in a user-defined way.
     *
     * @param loc The location of the element to which the
     *                error message is related
     * @param msg The string for the error message.
     */
     void yyerror (Location loc, String msg);
  }

  /**
   * The object doing lexical analysis for us.
   */
  private Lexer yylexer;
  
  



  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public VondaGrammar (Lexer yylexer) 
  {
    
    this.yylexer = yylexer;
    
  }

  private java.io.PrintStream yyDebugStream = System.err;

  /**
   * Return the <tt>PrintStream</tt> on which the debugging output is
   * printed.
   */
  public final java.io.PrintStream getDebugStream () { return yyDebugStream; }

  /**
   * Set the <tt>PrintStream</tt> on which the debug output is printed.
   * @param s The stream that is used for debugging output.
   */
  public final void setDebugStream(java.io.PrintStream s) { yyDebugStream = s; }

  private int yydebug = 0;

  /**
   * Answer the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   */
  public final int getDebugLevel() { return yydebug; }

  /**
   * Set the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   * @param level The verbosity level for debugging output.
   */
  public final void setDebugLevel(int level) { yydebug = level; }

  /**
   * Print an error message via the lexer.
   * Use a <code>null</code> location.
   * @param msg The error message.
   */
  public final void yyerror (String msg)
  {
    yylexer.yyerror ((Location)null, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param loc The location associated with the message.
   * @param msg The error message.
   */
  public final void yyerror (Location loc, String msg)
  {
    yylexer.yyerror (loc, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param pos The position associated with the message.
   * @param msg The error message.
   */
  public final void yyerror (Position pos, String msg)
  {
    yylexer.yyerror (new Location (pos), msg);
  }

  protected final void yycdebug (String s) {
    if (yydebug > 0)
      yyDebugStream.println (s);
  }

  private final class YYStack {
    private int[] stateStack = new int[16];
    private Location[] locStack = new Location[16];
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, Object value                            , Location loc) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;
          
          Location[] newLocStack = new Location[size * 2];
          System.arraycopy (locStack, 0, newLocStack, 0, height);
          locStack = newLocStack;

          Object[] newValueStack = new Object[size * 2];
          System.arraycopy (valueStack, 0, newValueStack, 0, height);
          valueStack = newValueStack;

          size *= 2;
        }

      stateStack[height] = state;
      locStack[height] = loc;
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (num > 0) {
        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
        java.util.Arrays.fill (locStack, height - num + 1, height + 1, null);
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }

    public final Location locationAt (int i) {
      return locStack[height - i];
    }

    public final Object valueAt (int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print (java.io.PrintStream out)
    {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++)
        {
          out.print (' ');
          out.print (stateStack[i]);
        }
      out.println ();
    }
  }

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return success (<tt>true</tt>).
   */
  public static final int YYACCEPT = 0;

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return failure (<tt>false</tt>).
   */
  public static final int YYABORT = 1;



  /**
   * Returned by a Bison action in order to start error recovery without
   * printing an error message.
   */
  public static final int YYERROR = 2;

  /**
   * Internal return codes that are not supported for user semantic
   * actions.
   */
  private static final int YYERRLAB = 3;
  private static final int YYNEWSTATE = 4;
  private static final int YYDEFAULT = 5;
  private static final int YYREDUCE = 6;
  private static final int YYERRLAB1 = 7;
  private static final int YYRETURN = 8;


  private int yyerrstatus_ = 0;


  /**
   * Return whether error recovery is being done.  In this state, the parser
   * reads token until it reaches a known state, and then restarts normal
   * operation.
   */
  public final boolean recovering ()
  {
    return yyerrstatus_ == 0;
  }

  /** Compute post-reduction state.
   * @param yystate   the current state
   * @param yysym     the nonterminal to push on the stack
   */
  private int yy_lr_goto_state_ (int yystate, int yysym)
  {
    int yyr = yypgoto_[yysym - yyntokens_] + yystate;
    if (0 <= yyr && yyr <= yylast_ && yycheck_[yyr] == yystate)
      return yytable_[yyr];
    else
      return yydefgoto_[yysym - yyntokens_];
  }

  private int yyaction (int yyn, YYStack yystack, int yylen) 
  {
    Object yyval;
    Location yyloc = yylloc (yystack, yylen);

    /* If YYLEN is nonzero, implement the default value of the action:
       '$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    if (yylen > 0)
      yyval = yystack.valueAt (yylen - 1);
    else
      yyval = yystack.valueAt (0);

    yy_reduce_print (yyn, yystack);

    switch (yyn)
      {
          case 2:
  if (yyn == 2)
    /* "VondaGrammar.y":115  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((StatMethodDeclaration)(yystack.valueAt (3-(2)))).setVisibility(((String)(yystack.valueAt (3-(1))))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (3-(2)))));
  };
  break;
    

  case 3:
  if (yyn == 3)
    /* "VondaGrammar.y":118  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (2-(1))))); };
  break;
    

  case 4:
  if (yyn == 4)
    /* "VondaGrammar.y":119  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 5:
  if (yyn == 5)
    /* "VondaGrammar.y":121  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((Import)(yystack.valueAt (2-(1))))); };
  break;
    

  case 6:
  if (yyn == 6)
    /* "VondaGrammar.y":122  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(new StatFieldDef(((String)(yystack.valueAt (3-(1)))), ((StatVarDef)(yystack.valueAt (3-(2))))).setPos(yystack.locationAt (3-(1)), yystack.locationAt (3-(2))));
  };
  break;
    

  case 7:
  if (yyn == 7)
    /* "VondaGrammar.y":125  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (2-(1))))).setPos(yystack.locationAt (2-(1))));
  };
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":128  */ /* lalr1.java:489  */
    { yyval = _statements;};
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":132  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":133  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":134  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":138  */ /* lalr1.java:489  */
    {
    new Import((( String )(yystack.valueAt (4-(2)))), ((List<String>)(yystack.valueAt (4-(3)))).toArray(new String[((List<String>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":144  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(); };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":145  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(3)))); ((List<String>)(yystack.valueAt (3-(3)))).add((( String )(yystack.valueAt (3-(2))))); };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":149  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":150  */ /* lalr1.java:489  */
    { yyval = new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))).setPos((yyloc)); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":151  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = new ExpSingleValue("1", "int"); es.setPos((yyloc));
    ExpArithmetic ar = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(2)))), es, "+");
    ar.setPos((yyloc));
    yyval = new StatExpression(ar).setPos((yyloc));
  };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":157  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = new ExpSingleValue("1", "int"); es.setPos((yyloc));
    ExpArithmetic ar = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(2)))), es, "-");
    ar.setPos((yyloc));
    yyval = new StatExpression(ar).setPos((yyloc));
  };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":163  */ /* lalr1.java:489  */
    { yyval = new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))).setPos((yyloc)); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":164  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":165  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":166  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":167  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":168  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":169  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":170  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":171  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":172  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":173  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":174  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":178  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":179  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":185  */ /* lalr1.java:489  */
    { yyval = new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true).setPos((yyloc)); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":186  */ /* lalr1.java:489  */
    {
    yyval = new StatAbstractBlock(new ArrayList<RTStatement>(), true).setPos((yyloc));
  };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":190  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":191  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":195  */ /* lalr1.java:489  */
    { yyval = new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))).setPos((yyloc)); };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":199  */ /* lalr1.java:489  */
    { yyval = new StatReturn("return").setPos((yyloc)); };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":200  */ /* lalr1.java:489  */
    { yyval = new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))).setPos((yyloc)); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":201  */ /* lalr1.java:489  */
    { yyval = new StatReturn("break").setPos((yyloc)); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":202  */ /* lalr1.java:489  */
    { yyval = new StatReturn("break", (( String )(yystack.valueAt (3-(2))))).setPos((yyloc)); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":203  */ /* lalr1.java:489  */
    { yyval = new StatReturn("cancel").setPos((yyloc)); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":204  */ /* lalr1.java:489  */
    { yyval = new StatReturn("cancel_all").setPos((yyloc)); };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":205  */ /* lalr1.java:489  */
    { yyval = new StatReturn("continue").setPos((yyloc)); };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":209  */ /* lalr1.java:489  */
    { yyval = new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null).setPos((yyloc)); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":210  */ /* lalr1.java:489  */
    {
    yyval = new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))).setPos((yyloc));
  };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":216  */ /* lalr1.java:489  */
    { yyval = new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true).setPos((yyloc)); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":217  */ /* lalr1.java:489  */
    {
    yyval = new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false).setPos((yyloc));
  };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":223  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))).setPos((yyloc)); };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":225  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))).setPos((yyloc)); };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":227  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))).setPos((yyloc)); };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":229  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))).setPos((yyloc)); };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":231  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))).setPos((yyloc)); };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":233  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (7-(3))))); var.setPos(yystack.locationAt (7-(3)));
    yyval = new StatFor2(var, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))).setPos((yyloc));
  };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":237  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (8-(4))))); var.setPos(yystack.locationAt (8-(4)));
    yyval = new StatFor2(((Type)(yystack.valueAt (8-(3)))), var, ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))).setPos((yyloc));
  };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":246  */ /* lalr1.java:489  */
    { yyval = new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc)); };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":250  */ /* lalr1.java:489  */
    {
    yyval = new StatTimeout(null, ((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":256  */ /* lalr1.java:489  */
    {
    yyval = new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))).setPos((yyloc));
  };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":262  */ /* lalr1.java:489  */
    {
    yyval = new StatSwitch(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":288  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos((yyloc)); lbl.setPos((yyloc));
    yyval = lbl;
  };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":295  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos((yyloc)); lbl.setPos((yyloc));
    yyval = lbl;
  };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":302  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos((yyloc)); lbl.setPos((yyloc));
    yyval = lbl;
  };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":309  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos((yyloc)); lbl.setPos((yyloc));
    yyval = lbl;
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":316  */ /* lalr1.java:489  */
    {
    ExpSingleValue val = new ExpSingleValue("default:", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos((yyloc)); lbl.setPos((yyloc));
    yyval = lbl;
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":325  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":328  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":331  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), (( String )(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(4))))).setPos((yyloc));
  };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":334  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (3-(2)))), null).setPos((yyloc));
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":337  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2)))), null).setPos((yyloc));
  };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":340  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3)))), null).setPos((yyloc));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":346  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":347  */ /* lalr1.java:489  */
    {
    yyval = new ExpListLiteral(new LinkedList<RTExpression>()).setPos(yystack.locationAt (3-(2)), yystack.locationAt (3-(3)));
  };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":350  */ /* lalr1.java:489  */
    {
    yyval = new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))).setPos(yystack.locationAt (4-(2)), yystack.locationAt (4-(4)));
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":356  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":357  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":361  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), null, ((StatAbstractBlock)(yystack.valueAt (6-(6))))).setPos((yyloc));
  };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":364  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (7-(2)))), ((Type)(yystack.valueAt (7-(1)))), (( String )(yystack.valueAt (7-(3)))), ((LinkedList)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))).setPos((yyloc));
  };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":367  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":370  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))).setPos((yyloc));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":373  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":376  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(1)))), null, (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))).setPos((yyloc));
  };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":379  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), null, ((StatAbstractBlock)(yystack.valueAt (4-(4))))).setPos((yyloc));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":382  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (5-(1)))), ((LinkedList)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":387  */ /* lalr1.java:489  */
    { yyval = ((Type)(yystack.valueAt (4-(2)))); };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":391  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":392  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":396  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":397  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (2-(1))))); add((( String )(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":398  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (3-(3)))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":399  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":407  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (4-(1))))); var.setPos(yystack.locationAt (4-(1)));
    yyval = new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":411  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (4-(1))))); var.setPos(yystack.locationAt (4-(1)));
    yyval = new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":415  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":418  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":421  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":424  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":433  */ /* lalr1.java:489  */
    {
    yyval = new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false).setPos((yyloc));
  };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":436  */ /* lalr1.java:489  */
    {
    yyval = new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false).setPos((yyloc));
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":442  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":443  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":444  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":445  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":449  */ /* lalr1.java:489  */
    { yyval = new Type("Array", new Type((( String )(yystack.valueAt (3-(1)))))); };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":450  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":451  */ /* lalr1.java:489  */
    {
    yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":457  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":458  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":462  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":463  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":464  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":469  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||").setPos((yyloc));
  };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":472  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":476  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&").setPos((yyloc));
  };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":479  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":483  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":484  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|").setPos((yyloc));
  };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":490  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":491  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^").setPos((yyloc));
  };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":497  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":498  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&").setPos((yyloc));
  };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":504  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":505  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "==").setPos((yyloc));
  };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":508  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!=").setPos((yyloc));
  };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":514  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":515  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<").setPos((yyloc));
  };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":518  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">").setPos((yyloc));
  };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":521  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">=").setPos((yyloc));
  };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":524  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<=").setPos((yyloc));
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":530  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":531  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+").setPos((yyloc));
  };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":534  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-").setPos((yyloc));
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":540  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":541  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*").setPos((yyloc));
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":544  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/").setPos((yyloc));
  };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":547  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%").setPos((yyloc));
  };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":553  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = new ExpSingleValue("1", "int"); es.setPos((yyloc));
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "+").setPos((yyloc));
  };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":557  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = new ExpSingleValue("1", "int"); es.setPos((yyloc));
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "-").setPos((yyloc));
  };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":561  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+").setPos((yyloc)); };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":562  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-").setPos((yyloc)); };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":563  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":567  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":568  */ /* lalr1.java:489  */
    { yyval = new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))).setPos((yyloc)); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":573  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":574  */ /* lalr1.java:489  */
    { yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!").setPos((yyloc)); };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":575  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~").setPos((yyloc)); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":579  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":585  */ /* lalr1.java:489  */
    { yyval = new ExpSingleValue("null", "null").setPos((yyloc)); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":586  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":587  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":591  */ /* lalr1.java:489  */
    { yyval = new ExpVariable((( String )(yystack.valueAt (1-(1))))).setPos((yyloc)); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":592  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":596  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":597  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":601  */ /* lalr1.java:489  */
    { yyval = ((ExpSingleValue)(yystack.valueAt (1-(1)))); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":602  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":603  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":604  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":605  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":609  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); (( ExpSingleValue )(yystack.valueAt (1-(1)))).setPos((yyloc)); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":610  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); (( ExpSingleValue )(yystack.valueAt (1-(1)))).setPos((yyloc)); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":611  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); (( ExpSingleValue )(yystack.valueAt (1-(1)))).setPos((yyloc)); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":612  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); (( ExpSingleValue )(yystack.valueAt (1-(1)))).setPos((yyloc)); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":616  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (4-(1))))); var.setPos(yystack.locationAt (4-(1)));
    yyval = new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":620  */ /* lalr1.java:489  */
    { yyval = new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc)); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":624  */ /* lalr1.java:489  */
    { yyval = new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))).setPos((yyloc)); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":630  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (2-(1))))); var.setPos(yystack.locationAt (2-(1)));
    yyval = new ExpAssignment(var, ((RTExpression)(yystack.valueAt (2-(2))))).setPos((yyloc));
  };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":634  */ /* lalr1.java:489  */
    { yyval = new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))).setPos((yyloc)); };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":635  */ /* lalr1.java:489  */
    { yyval = new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))).setPos((yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":639  */ /* lalr1.java:489  */
    {
    List<String> repr = new ArrayList<>(((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).size());
    for (int i = 0; i < ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).size(); ++i) repr.add("");
    yyval = new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))), repr).setPos((yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":647  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":648  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":652  */ /* lalr1.java:489  */
    { yyval = new ExpVariable((( String )(yystack.valueAt (1-(1))))).setPos((yyloc)); };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":653  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":654  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":658  */ /* lalr1.java:489  */
    { yyval = new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))).setPos((yyloc)); };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":659  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()).setPos((yyloc));
  };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":662  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))).setPos((yyloc));
  };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":665  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (5-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }}).setPos((yyloc));
  };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":669  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (7-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (7-(6))))); }}).setPos((yyloc));
  };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":673  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type((( String )(yystack.valueAt (7-(2)))), ((LinkedList<Type>)(yystack.valueAt (7-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (7-(4)))).size()])),
                    new LinkedList<>()).setPos((yyloc));
  };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":677  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type((( String )(yystack.valueAt (8-(2)))), ((LinkedList<Type>)(yystack.valueAt (8-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (8-(4)))).size()])),
                    ((LinkedList<RTExpression>)(yystack.valueAt (8-(7))))).setPos((yyloc));
  };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":684  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":687  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":690  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(new LinkedList<>(), ((RTExpression)(yystack.valueAt (4-(4))))).setPos((yyloc));
  };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":693  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(new LinkedList<>(), ((StatAbstractBlock)(yystack.valueAt (4-(4))))).setPos((yyloc));
  };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":700  */ /* lalr1.java:489  */
    {
    yyval = new ExpDialogueAct(((RTExpression)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(4)))), new LinkedList<RTExpression>()).setPos((yyloc));
  };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":703  */ /* lalr1.java:489  */
    {
    yyval = new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5))))).setPos((yyloc));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":709  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":710  */ /* lalr1.java:489  */
    { yyval = new ExpVariable((( String )(yystack.valueAt (1-(1))))).setPos((yyloc)); };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":711  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":712  */ /* lalr1.java:489  */
    { yyval = new ExpSingleValue((( String )(yystack.valueAt (1-(1)))), "String").setPos((yyloc)); };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":716  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 193:
  if (yyn == 193)
    /* "VondaGrammar.y":719  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    


/* "VondaGrammar.java":2055  */ /* lalr1.java:489  */
        default: break;
      }

    yy_symbol_print ("-> $$ =", yyr1_[yyn], yyval, yyloc);

    yystack.pop (yylen);
    yylen = 0;

    /* Shift the result of the reduction.  */
    int yystate = yy_lr_goto_state_ (yystack.stateAt (0), yyr1_[yyn]);
    yystack.push (yystate, yyval, yyloc);
    return YYNEWSTATE;
  }


  /* Return YYSTR after stripping away unnecessary quotes and
     backslashes, so that it's suitable for yyerror.  The heuristic is
     that double-quoting is unnecessary unless the string contains an
     apostrophe, a comma, or backslash (other than backslash-backslash).
     YYSTR is taken from yytname.  */
  private final String yytnamerr_ (String yystr)
  {
    if (yystr.charAt (0) == '"')
      {
        StringBuffer yyr = new StringBuffer ();
        strip_quotes: for (int i = 1; i < yystr.length (); i++)
          switch (yystr.charAt (i))
            {
            case '\'':
            case ',':
              break strip_quotes;

            case '\\':
              if (yystr.charAt(++i) != '\\')
                break strip_quotes;
              /* Fall through.  */
            default:
              yyr.append (yystr.charAt (i));
              break;

            case '"':
              return yyr.toString ();
            }
      }
    else if (yystr.equals ("$end"))
      return "end of input";

    return yystr;
  }


  /*--------------------------------.
  | Print this symbol on YYOUTPUT.  |
  `--------------------------------*/

  private void yy_symbol_print (String s, int yytype,
                                 Object yyvaluep                                 , Object yylocationp)
  {
    if (yydebug > 0)
    yycdebug (s + (yytype < yyntokens_ ? " token " : " nterm ")
              + yytname_[yytype] + " ("
              + yylocationp + ": "
              + (yyvaluep == null ? "(null)" : yyvaluep.toString ()) + ")");
  }


  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
   public boolean parse () throws java.io.IOException

  {
    /* @$.  */
    Location yyloc;


    /* Lookahead and lookahead in internal form.  */
    int yychar = yyempty_;
    int yytoken = 0;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;
    YYStack yystack = new YYStack ();
    int label = YYNEWSTATE;

    /* Error handling.  */
    int yynerrs_ = 0;
    /* The location where the error started.  */
    Location yyerrloc = null;

    /* Location. */
    Location yylloc = new Location (null, null);

    /* Semantic value of the lookahead.  */
    Object yylval = null;

    yycdebug ("Starting parse\n");
    yyerrstatus_ = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval , yylloc);



    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
           pushed when we come here.  */
      case YYNEWSTATE:
        yycdebug ("Entering state " + yystate + "\n");
        if (yydebug > 0)
          yystack.print (yyDebugStream);

        /* Accept?  */
        if (yystate == yyfinal_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yy_pact_value_is_default_ (yyn))
          {
            label = YYDEFAULT;
            break;
          }

        /* Read a lookahead token.  */
        if (yychar == yyempty_)
          {


            yycdebug ("Reading a token: ");
            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal ();
            yylloc = new Location (yylexer.getStartPos (),
                            yylexer.getEndPos ());

          }

        /* Convert token to internal form.  */
        if (yychar <= Lexer.EOF)
          {
            yychar = yytoken = Lexer.EOF;
            yycdebug ("Now at end of input.\n");
          }
        else
          {
            yytoken = yytranslate_ (yychar);
            yy_symbol_print ("Next token is", yytoken,
                             yylval, yylloc);
          }

        /* If the proper action on seeing token YYTOKEN is to reduce or to
           detect an error, take that action.  */
        yyn += yytoken;
        if (yyn < 0 || yylast_ < yyn || yycheck_[yyn] != yytoken)
          label = YYDEFAULT;

        /* <= 0 means reduce or error.  */
        else if ((yyn = yytable_[yyn]) <= 0)
          {
            if (yy_table_value_is_error_ (yyn))
              label = YYERRLAB;
            else
              {
                yyn = -yyn;
                label = YYREDUCE;
              }
          }

        else
          {
            /* Shift the lookahead token.  */
            yy_symbol_print ("Shifting", yytoken,
                             yylval, yylloc);

            /* Discard the token being shifted.  */
            yychar = yyempty_;

            /* Count tokens shifted since error; after three, turn off error
               status.  */
            if (yyerrstatus_ > 0)
              --yyerrstatus_;

            yystate = yyn;
            yystack.push (yystate, yylval, yylloc);
            label = YYNEWSTATE;
          }
        break;

      /*-----------------------------------------------------------.
      | yydefault -- do the default action for the current state.  |
      `-----------------------------------------------------------*/
      case YYDEFAULT:
        yyn = yydefact_[yystate];
        if (yyn == 0)
          label = YYERRLAB;
        else
          label = YYREDUCE;
        break;

      /*-----------------------------.
      | yyreduce -- Do a reduction.  |
      `-----------------------------*/
      case YYREDUCE:
        yylen = yyr2_[yyn];
        label = yyaction (yyn, yystack, yylen);
        yystate = yystack.stateAt (0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs_;
            if (yychar == yyempty_)
              yytoken = yyempty_;
            yyerror (yylloc, yysyntax_error (yystate, yytoken));
          }

        yyerrloc = yylloc;
        if (yyerrstatus_ == 3)
          {
        /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

        if (yychar <= Lexer.EOF)
          {
          /* Return failure if at end of input.  */
          if (yychar == Lexer.EOF)
            return false;
          }
        else
            yychar = yyempty_;
          }

        /* Else will try to reuse lookahead token after shifting the error
           token.  */
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------.
      | errorlab -- error raised explicitly by YYERROR.  |
      `-------------------------------------------------*/
      case YYERROR:

        yyerrloc = yystack.locationAt (yylen - 1);
        /* Do not reclaim the symbols of the rule which action triggered
           this YYERROR.  */
        yystack.pop (yylen);
        yylen = 0;
        yystate = yystack.stateAt (0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

        for (;;)
          {
            yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                yyn += yyterror_;
                if (0 <= yyn && yyn <= yylast_ && yycheck_[yyn] == yyterror_)
                  {
                    yyn = yytable_[yyn];
                    if (0 < yyn)
                      break;
                  }
              }

            /* Pop the current state because it cannot handle the
             * error token.  */
            if (yystack.height == 0)
              return false;

            yyerrloc = yystack.locationAt (0);
            yystack.pop ();
            yystate = yystack.stateAt (0);
            if (yydebug > 0)
              yystack.print (yyDebugStream);
          }

        if (label == YYABORT)
            /* Leave the switch.  */
            break;


        /* Muck with the stack to setup for yylloc.  */
        yystack.push (0, null, yylloc);
        yystack.push (0, null, yyerrloc);
        yyloc = yylloc (yystack, 2);
        yystack.pop (2);

        /* Shift the error token.  */
        yy_symbol_print ("Shifting", yystos_[yyn],
                         yylval, yyloc);

        yystate = yyn;
        yystack.push (yyn, yylval, yyloc);
        label = YYNEWSTATE;
        break;

        /* Accept.  */
      case YYACCEPT:
        return true;

        /* Abort.  */
      case YYABORT:
        return false;
      }
}




  // Generate an error message.
  private String yysyntax_error (int yystate, int tok)
  {
    if (yyErrorVerbose)
      {
        /* There are many possibilities here to consider:
           - If this state is a consistent state with a default action,
             then the only way this function was invoked is if the
             default action is an error action.  In that case, don't
             check for expected tokens because there are none.
           - The only way there can be no lookahead present (in tok) is
             if this state is a consistent state with a default action.
             Thus, detecting the absence of a lookahead is sufficient to
             determine that there is no unexpected or expected token to
             report.  In that case, just report a simple "syntax error".
           - Don't assume there isn't a lookahead just because this
             state is a consistent state with a default action.  There
             might have been a previous inconsistent state, consistent
             state with a non-default action, or user semantic action
             that manipulated yychar.  (However, yychar is currently out
             of scope during semantic actions.)
           - Of course, the expected token list depends on states to
             have correct lookahead information, and it depends on the
             parser not to perform extra reductions after fetching a
             lookahead from the scanner and before detecting a syntax
             error.  Thus, state merging (from LALR or IELR) and default
             reductions corrupt the expected token list.  However, the
             list is correct for canonical LR with one exception: it
             will still contain any token that will not be accepted due
             to an error action in a later state.
        */
        if (tok != yyempty_)
          {
            /* FIXME: This method of building the message is not compatible
               with internationalization.  */
            StringBuffer res =
              new StringBuffer ("syntax error, unexpected ");
            res.append (yytnamerr_ (yytname_[tok]));
            int yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                /* Start YYX at -YYN if negative to avoid negative
                   indexes in YYCHECK.  In other words, skip the first
                   -YYN actions for this state because they are default
                   actions.  */
                int yyxbegin = yyn < 0 ? -yyn : 0;
                /* Stay within bounds of both yycheck and yytname.  */
                int yychecklim = yylast_ - yyn + 1;
                int yyxend = yychecklim < yyntokens_ ? yychecklim : yyntokens_;
                int count = 0;
                for (int x = yyxbegin; x < yyxend; ++x)
                  if (yycheck_[x + yyn] == x && x != yyterror_
                      && !yy_table_value_is_error_ (yytable_[x + yyn]))
                    ++count;
                if (count < 5)
                  {
                    count = 0;
                    for (int x = yyxbegin; x < yyxend; ++x)
                      if (yycheck_[x + yyn] == x && x != yyterror_
                          && !yy_table_value_is_error_ (yytable_[x + yyn]))
                        {
                          res.append (count++ == 0 ? ", expecting " : " or ");
                          res.append (yytnamerr_ (yytname_[x]));
                        }
                  }
              }
            return res.toString ();
          }
      }

    return "syntax error";
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yy_pact_value_is_default_ (int yyvalue)
  {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code>
   * value indicates a syntax error.
   * @param yyvalue the value to check
   */
  private static boolean yy_table_value_is_error_ (int yyvalue)
  {
    return yyvalue == yytable_ninf_;
  }

  private static final short yypact_ninf_ = -304;
  private static final short yytable_ninf_ = -151;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     731,    70,    56,    71,   339,   102,   -26,   893,   133,     9,
     139,   194,   217,  -304,   223,  -304,  -304,   202,   235,   238,
     245,   273,  1321,  1321,  -304,  -304,  -304,   110,  -304,   559,
    1355,   327,   293,   370,    19,   731,   731,  -304,  -304,  -304,
    -304,  -304,  -304,  -304,  -304,  -304,  -304,  -304,   731,   731,
     332,  -304,   355,   377,   375,   383,  -304,  -304,   108,   393,
     213,  -304,  -304,   396,  -304,  -304,  -304,   382,   395,   399,
     400,  -304,  -304,   138,   430,   418,   402,    21,  1355,   416,
     345,  1355,  -304,  1321,  1321,   292,  -304,  1389,  1423,  1423,
    1321,  1321,  -304,   -32,   440,   447,   422,   423,   421,   168,
      14,   255,   401,  -304,  -304,  -304,  -304,  -304,   375,   383,
     432,  -304,  -304,   432,  1355,  1355,  1355,  1355,   140,   442,
    -304,  -304,   445,  1355,  1355,   480,   417,   907,   941,   327,
    -304,   285,  -304,  -304,   800,   448,  -304,   455,    -5,   197,
     453,  -304,  -304,  -304,  1355,   451,  -304,   386,   731,   731,
    -304,  -304,  -304,  -304,   419,   459,  -304,   212,    37,  -304,
    1355,  1355,  1355,  -304,  -304,  1355,  1355,  -304,  -304,  -304,
    -304,  -304,  -304,   975,  1355,   458,  -304,   454,   465,   -31,
     312,  1355,  1010,   468,    89,   469,   470,  1044,  1079,   327,
     114,  -304,  -304,   438,   462,  -304,  -304,  -304,  -304,  -304,
    1355,  1423,  1423,  1423,  1423,  1423,  1423,  1423,  1423,  1423,
    1423,  1423,  1423,  1423,  1423,  1423,  1423,   130,   -42,   134,
     187,  -304,  -304,    16,    20,  -304,   301,  1113,   467,   466,
     471,   473,   118,   472,  1148,   450,  -304,   -47,   474,   463,
    -304,  -304,    69,  -304,   464,    28,   293,    91,  -304,  -304,
      98,   475,  -304,   124,   476,   477,  1355,  -304,   375,    27,
      30,    34,    38,    52,  -304,  1355,  -304,  -304,   478,  1355,
      55,  1182,    67,   311,   893,   416,  -304,  -304,   479,   127,
     481,    39,   482,   467,  1423,   -14,   447,   422,   423,   421,
     168,    14,    14,   255,   255,   255,   255,   401,   401,  -304,
    -304,  -304,   467,  1355,   467,   893,  -304,  -304,   486,   504,
     483,   229,  -304,   467,  -304,   489,  1457,  1457,  -304,   487,
    -304,   327,  -304,  -304,  -304,   334,   315,   467,   279,   491,
     271,   279,   492,  -304,   191,  -304,  -304,  -304,  -304,  -304,
    -304,   199,  -304,   224,  1355,   893,   233,  1217,  1355,   526,
    -304,  -304,  1355,  1355,  -304,   495,  -304,  -304,  1355,  -304,
     240,  -304,  -304,  -304,  1252,   518,  -304,   486,  -304,  -304,
    -304,  -304,  -304,   293,   496,  -304,  -304,  -304,   279,   279,
     497,  -304,   279,  -304,  -304,   893,   242,  -304,   893,   893,
     247,   260,   893,  -304,   262,  1286,   450,   467,  -304,   450,
    1252,  -304,   498,  -304,  -304,  -304,   279,  -304,  -304,   893,
    -304,  -304,   893,   893,  -304,  -304,  -304,   501,  -304,  -304,
     450,   293,  -304,  -304,  -304,  -304,  -304,   502,  -304
    };
  }

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
  private static final short yydefact_[] = yydefact_init();
  private static final short[] yydefact_init()
  {
    return new short[]
    {
       8,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    11,     0,    10,     9,     0,     0,     0,
       0,     0,     0,     0,   159,   160,   162,   104,   161,     0,
       0,     0,     0,     0,     0,     8,     8,    15,    20,    22,
      26,    27,    28,    23,    25,    24,    29,    30,     8,     8,
       0,    21,   157,     0,     0,     0,   153,   154,   155,     0,
     156,   151,   158,     0,    40,    42,    43,     0,     0,     0,
       0,    44,    64,   150,     0,   104,     0,     0,     0,    13,
     175,     0,   147,     0,     0,   150,    38,     0,     0,     0,
       0,     0,   157,     0,   110,   112,   114,   115,   117,   119,
     121,   124,   129,   141,   132,   140,   143,   146,   148,   149,
     155,   108,   109,   156,     0,     0,     0,     0,   150,     0,
     155,   156,     0,     0,     0,     0,     0,     0,     0,     0,
     166,   104,    34,    31,    35,     0,    32,     0,     0,   104,
       0,   190,   191,   189,     0,     0,     1,   104,     8,     8,
       5,     4,     7,     3,   104,     0,    19,     0,     0,   169,
       0,     0,     0,   168,    16,     0,     0,   167,    41,    60,
      61,    62,    63,     0,     0,     0,    68,     0,     0,     0,
     104,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   137,   136,   150,     0,   138,   139,   144,   145,    39,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    18,    17,     0,     0,    37,   150,     0,    97,     0,
       0,     0,    99,   100,     0,    71,   103,     0,   106,     0,
      36,    33,     0,   152,     0,     0,     0,     0,     6,     2,
       0,     0,    69,     0,     0,   172,     0,   173,   171,     0,
       0,     0,     0,     0,    97,     0,    65,    70,     0,     0,
       0,     0,     0,     0,     0,    13,    12,   176,     0,    74,
       0,     0,     0,     0,     0,     0,   111,   113,   116,   118,
     120,   122,   123,   127,   128,   125,   126,   130,   131,   133,
     134,   135,     0,     0,     0,     0,    92,    91,     0,     0,
       0,     0,    82,     0,    98,    88,     0,     0,    72,     0,
     163,     0,   105,    84,   188,     0,    87,     0,     0,     0,
       0,     0,     0,    66,     0,   170,   164,    94,    93,    96,
      95,     0,    67,     0,     0,     0,     0,     0,     0,    45,
      14,   177,     0,     0,   178,     0,    56,   142,     0,    59,
       0,    57,    47,    89,     0,     0,    83,     0,   101,   102,
      73,   107,   186,     0,     0,    86,    85,    78,     0,     0,
       0,    80,     0,   174,    48,     0,     0,    52,     0,     0,
       0,     0,     0,    75,     0,     0,   165,     0,   185,   184,
       0,    90,     0,   187,    79,    76,     0,    81,    54,     0,
      50,    51,     0,     0,    46,   179,   180,     0,    58,   183,
     182,     0,    77,    53,    49,    55,   181,   193,   192
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -304,   -22,  -304,  -304,   272,    36,  -304,    44,   420,  -304,
    -304,   426,  -304,  -304,  -304,  -304,  -304,  -304,  -304,   -19,
      12,  -217,   519,  -304,  -303,  -211,  -304,     2,    79,   248,
    -184,   531,  -304,   359,   373,   376,   372,   380,   154,   294,
     192,    33,   -85,  -304,  -304,  -304,   146,   252,  -304,  -304,
       0,  -304,   268,    18,   328,  -304,  -304,  -304,  -304,  -245,
     160
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    33,    34,    35,   186,    36,   134,    37,   135,    38,
      39,    40,    41,    42,    43,    44,    45,    46,    47,    48,
     130,   278,    49,    50,   377,   229,    51,    92,   230,    53,
     239,   232,    94,    95,    96,    97,    98,    99,   100,   101,
     102,   103,   104,   105,   106,   107,   108,   109,    56,    57,
     110,   111,   112,   113,   159,   258,    61,   233,    62,   145,
     374
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final short yytable_[] = yytable_init();
  private static final short[] yytable_init()
  {
    return new short[]
    {
      58,   325,    52,   195,   196,   282,   320,    58,   303,    52,
     136,   199,   267,   150,   151,   148,   310,   319,    60,   200,
     127,    72,   120,   120,   200,    60,   152,   153,   381,    58,
       8,    52,     8,   358,   200,    58,    58,    52,    52,   329,
     121,   121,   332,    74,   243,   208,   209,    60,    58,    58,
      52,    52,   200,    60,    60,   119,   122,    77,   182,   306,
     147,   200,   180,   307,   181,   133,    60,    60,   210,   211,
     163,    31,   167,   337,   324,   404,   405,   338,   255,   407,
     336,   339,   200,   120,   120,   256,   200,   178,   120,   120,
     120,   120,   354,   200,   200,   340,   200,   363,   344,    65,
     200,   121,   121,   422,   200,   200,   121,   121,   121,   121,
     347,    63,   252,    64,    66,   136,   191,   192,   200,   380,
     127,   200,   163,   197,   198,   167,   248,   249,   402,   299,
     300,   301,   326,   200,    58,   393,    52,   371,   274,   326,
     327,   161,   162,   123,   124,    71,    54,   328,    58,    58,
      52,    52,    60,    54,  -150,   200,   401,   125,   126,   127,
     257,   127,   128,   283,   129,   326,    60,    60,   316,   254,
     133,   123,   124,   331,    75,    54,   427,   352,   417,   302,
     200,    54,    54,   304,   200,   125,   173,    78,   173,   127,
     174,   268,   174,   200,    54,    54,   200,   206,   207,   357,
     200,   120,   120,   120,   120,   120,   120,   120,   120,   120,
     120,   120,   120,   120,   120,   120,   120,    12,    82,   121,
     121,   121,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,    79,   305,    83,    84,    24,
     383,    25,    26,    85,    28,    86,   165,   166,   384,   177,
      87,   129,    55,   200,   254,   252,    76,   200,    80,    55,
     253,    88,    89,   127,   127,   200,    90,    91,    59,    32,
     315,    81,   312,   385,    58,    59,    52,   137,   284,   140,
      54,    55,   388,   114,   120,   254,   115,    55,    55,   397,
     200,   409,    60,   116,    54,    54,   412,    59,   155,   200,
      55,    55,   121,    59,    59,    58,   200,    52,   200,   413,
     349,   415,   326,   200,   212,   213,    59,    59,   123,   124,
     379,   117,   375,    60,    29,   183,   200,   356,   200,  -150,
     141,   142,   125,   173,   143,   194,   127,   128,   144,   129,
     173,   362,  -104,   127,   174,    58,   359,    52,   361,   173,
     -87,   308,   127,   128,   252,   129,  -104,   366,   348,   269,
     291,   292,   127,    60,   177,   308,   129,   177,   139,   129,
     146,   312,   376,   154,   231,   376,    67,   238,    68,    69,
      70,   387,   137,   372,   373,    58,    55,    52,    58,    58,
      52,    52,    58,   187,    52,   368,   369,   188,   156,   189,
      55,    55,    59,    60,   297,   298,    60,    60,   398,    58,
      60,    52,    58,    58,    52,    52,    59,    59,   157,   158,
      54,   408,   376,   376,   410,   411,   376,    60,   414,   169,
      60,    60,    12,    82,   247,   160,   164,   238,   177,   168,
     129,   418,   170,   179,   419,   423,   171,   172,   424,   425,
     376,    54,    83,    84,    24,   175,    25,    26,   226,    28,
     185,   176,   214,   215,   216,   227,   228,   250,   201,   127,
     177,   177,   129,   129,   202,   311,    88,    89,   203,   205,
     204,    90,    91,   127,    32,   221,   173,  -104,   222,   127,
     128,    54,   129,    10,   241,   231,   242,   244,   231,   246,
     251,   231,   293,   294,   295,   296,   265,   236,   266,   273,
     275,   284,    29,   276,   315,   313,   200,   323,   322,   333,
     314,   342,   317,   330,   321,   173,    55,   326,   351,   353,
     364,    54,   365,   370,    54,    54,   392,   355,    54,   367,
     378,   382,    59,   395,   400,   403,   406,   350,    93,   421,
     426,   225,   373,   149,   240,    54,   231,    55,    54,    54,
     286,   138,     1,     2,     3,     4,     5,     6,     7,   238,
       8,     9,    10,    59,    12,   287,   289,    14,   231,   288,
      17,    18,    19,    20,    21,   290,   335,   428,     0,     0,
       0,     0,     0,     0,    22,    23,    24,    55,    25,    26,
     131,    28,     0,     0,    29,   132,     0,    30,     0,   184,
       0,     0,   190,    59,     0,   231,     0,     0,   138,     0,
       0,     0,     0,     0,     0,     0,    32,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    55,     0,     0,
      55,    55,     0,     0,    55,   217,   218,   219,   220,     0,
       0,     0,     0,    59,   223,   224,    59,    59,   235,   237,
      59,    55,     0,     0,    55,    55,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   245,     0,    59,     0,     0,
      59,    59,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   259,   260,   261,     0,     0,   262,   263,     0,     0,
       0,     0,     0,     0,     0,   237,     0,     0,     0,     0,
       0,     0,   270,   272,     0,     0,     0,     0,   279,   281,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   285,     0,     0,     1,     2,     3,     4,     5,     6,
       7,     0,     8,     9,    10,    11,    12,     0,    13,    14,
      15,    16,    17,    18,    19,    20,    21,     0,   138,     0,
       0,     0,     0,     0,     0,   279,    22,    23,    24,     0,
      25,    26,    27,    28,     0,     0,    29,     0,     0,    30,
       0,     0,     0,    31,     0,     0,     0,   334,     0,     0,
       0,     0,     0,     0,     0,     0,   341,     0,    32,     0,
     343,     0,   346,     1,     2,     3,     4,     5,     6,     7,
       0,     8,     9,    10,     0,    12,     0,     0,    14,     0,
       0,    17,    18,    19,    20,    21,     0,     0,     0,     0,
       0,     0,     0,     0,   360,    22,    23,    24,     0,    25,
      26,   131,    28,     0,     0,    29,     0,     0,    30,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    32,     0,     0,
       0,     0,     0,     0,     0,   386,     0,     0,   390,   391,
       0,     0,     0,   279,   394,     0,     0,     0,     0,   396,
       0,     0,     0,     0,     0,   399,     1,     2,     3,     4,
       5,     6,     7,     0,     0,     9,    10,     0,    12,     0,
       0,    14,     0,     0,    17,    18,    19,    20,    21,     0,
       0,     0,    12,    82,     0,     0,   279,     0,    22,    23,
      24,   420,    25,    26,    73,    28,     0,     0,    29,     0,
       0,    30,    83,    84,    24,     0,    25,    26,    85,    28,
       0,     0,   234,     0,     0,    87,    12,    82,     0,     0,
      32,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    32,     0,    83,    84,    24,     0,
      25,    26,    85,    28,     0,     0,     0,     0,     0,    87,
      12,    82,     0,     0,   236,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    32,     0,
      83,    84,    24,     0,    25,    26,    85,    28,     0,     0,
       0,     0,     0,   227,   264,    12,    82,     0,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    32,     0,     0,    83,    84,    24,     0,    25,
      26,    85,    28,   271,     0,     0,     0,     0,    87,    12,
      82,     0,     0,     0,     0,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    32,     0,    83,
      84,    24,     0,    25,    26,    85,    28,     0,     0,     0,
       0,     0,    87,   277,    12,    82,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    32,     0,     0,    83,    84,    24,     0,    25,    26,
      85,    28,     0,     0,     0,     0,     0,    87,    12,    82,
       0,     0,   280,     0,     0,     0,     0,     0,    88,    89,
       0,     0,     0,    90,    91,     0,    32,     0,    83,    84,
      24,     0,    25,    26,   226,    28,     0,     0,     0,     0,
       0,    87,   309,    12,    82,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      32,     0,     0,    83,    84,    24,     0,    25,    26,    85,
      28,     0,     0,     0,   318,     0,    87,    12,    82,     0,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    32,     0,    83,    84,    24,
       0,    25,    26,    85,    28,     0,     0,     0,     0,     0,
      87,   345,    12,    82,     0,     0,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    32,
       0,     0,    83,    84,    24,     0,    25,    26,    85,    28,
       0,     0,     0,     0,     0,    87,   389,    12,    82,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    32,     0,     0,    83,    84,    24,
       0,    25,    26,    85,    28,     0,     0,    29,     0,     0,
      87,    12,    82,     0,     0,     0,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    32,
       0,    83,    84,    24,     0,    25,    26,    85,    28,     0,
       0,     0,     0,     0,    87,   416,    12,    82,     0,     0,
       0,     0,     0,     0,     0,    88,    89,     0,     0,     0,
      90,    91,     0,    32,     0,     0,    83,    84,    24,     0,
      25,    26,   118,    28,     0,     0,     0,     0,     0,    30,
      12,    82,     0,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    32,     0,
      83,    84,    24,     0,    25,    26,    85,    28,     0,     0,
       0,     0,     0,    87,    12,    82,     0,     0,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    32,     0,    83,    84,    24,     0,    25,    26,
     193,    28,     0,     0,     0,     0,     0,    87,    12,    82,
       0,     0,     0,     0,     0,     0,     0,     0,    88,    89,
       0,     0,     0,    90,    91,     0,    32,     0,    83,    84,
      24,     0,    25,    26,   118,    28,     0,     0,     0,     0,
       0,    87,    12,    82,     0,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      32,     0,    83,    84,    24,     0,    25,    26,    85,    28,
       0,     0,     0,     0,     0,   227,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    32
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   246,     0,    88,    89,   189,    53,     7,    50,     7,
      29,    43,    43,    35,    36,    34,   227,   234,     0,    66,
      51,    47,    22,    23,    66,     7,    48,    49,   331,    29,
      11,    29,    11,    47,    66,    35,    36,    35,    36,   250,
      22,    23,   253,     7,    49,    31,    32,    29,    48,    49,
      48,    49,    66,    35,    36,    22,    23,    48,    77,    43,
      41,    66,    41,    43,    43,    29,    48,    49,    54,    55,
      58,    52,    60,    43,    46,   378,   379,    43,    41,   382,
      53,    43,    66,    83,    84,    48,    66,    75,    88,    89,
      90,    91,    53,    66,    66,    43,    66,   308,    43,    43,
      66,    83,    84,   406,    66,    66,    88,    89,    90,    91,
      43,    41,    43,    43,    43,   134,    83,    84,    66,   330,
      51,    66,   110,    90,    91,   113,   148,   149,   373,   214,
     215,   216,    41,    66,   134,   352,   134,   321,    49,    41,
      49,    33,    34,    33,    34,    43,     0,    49,   148,   149,
     148,   149,   134,     7,    44,    66,   367,    47,    48,    51,
     158,    51,    52,    49,    54,    41,   148,   149,    50,   157,
     134,    33,    34,    49,    41,    29,   421,    50,   395,    49,
      66,    35,    36,    49,    66,    47,    48,    48,    48,    51,
      52,   179,    52,    66,    48,    49,    66,    29,    30,   284,
      66,   201,   202,   203,   204,   205,   206,   207,   208,   209,
     210,   211,   212,   213,   214,   215,   216,    15,    16,   201,
     202,   203,   204,   205,   206,   207,   208,   209,   210,   211,
     212,   213,   214,   215,   216,    41,    49,    35,    36,    37,
      49,    39,    40,    41,    42,    43,    33,    34,    49,    52,
      48,    54,     0,    66,   242,    43,     8,    66,    41,     7,
      48,    59,    60,    51,    51,    66,    64,    65,     0,    67,
      41,    48,   228,    49,   274,     7,   274,    29,    49,    31,
     134,    29,    49,    48,   284,   273,    48,    35,    36,    49,
      66,    49,   274,    48,   148,   149,    49,    29,    50,    66,
      48,    49,   284,    35,    36,   305,    66,   305,    66,    49,
     274,    49,    41,    66,    59,    60,    48,    49,    33,    34,
      49,    48,    43,   305,    45,    77,    66,   283,    66,    44,
      37,    38,    47,    48,    41,    87,    51,    52,    45,    54,
      48,   305,    41,    51,    52,   345,   302,   345,   304,    48,
      49,    50,    51,    52,    43,    54,    41,   313,    47,    47,
     206,   207,    51,   345,    52,    50,    54,    52,    41,    54,
       0,   327,   328,    41,   126,   331,    37,   129,    39,    40,
      41,   345,   134,    49,    50,   385,   134,   385,   388,   389,
     388,   389,   392,    48,   392,   316,   317,    52,    43,    54,
     148,   149,   134,   385,   212,   213,   388,   389,   364,   409,
     392,   409,   412,   413,   412,   413,   148,   149,    41,    44,
     274,   385,   378,   379,   388,   389,   382,   409,   392,    47,
     412,   413,    15,    16,    48,    52,    43,   189,    52,    43,
      54,   397,    47,    41,   400,   409,    47,    47,   412,   413,
     406,   305,    35,    36,    37,    25,    39,    40,    41,    42,
      44,    43,    61,    62,    63,    48,    49,    48,    28,    51,
      52,    52,    54,    54,    27,   227,    59,    60,    56,    58,
      57,    64,    65,    51,    67,    43,    48,    49,    43,    51,
      52,   345,    54,    13,    46,   247,    41,    44,   250,    48,
      41,   253,   208,   209,   210,   211,    48,    53,    43,    41,
      41,    49,    45,    43,    41,    49,    66,    53,    55,    43,
      49,    43,    50,    48,    50,    48,   274,    41,    49,    48,
      26,   385,    49,    46,   388,   389,    10,    55,   392,    50,
      49,    49,   274,    48,    26,    49,    49,   275,    17,    51,
      49,   125,    50,    34,   134,   409,   308,   305,   412,   413,
     201,    30,     3,     4,     5,     6,     7,     8,     9,   321,
      11,    12,    13,   305,    15,   202,   204,    18,   330,   203,
      21,    22,    23,    24,    25,   205,   258,   427,    -1,    -1,
      -1,    -1,    -1,    -1,    35,    36,    37,   345,    39,    40,
      41,    42,    -1,    -1,    45,    46,    -1,    48,    -1,    78,
      -1,    -1,    81,   345,    -1,   367,    -1,    -1,    87,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    67,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   385,    -1,    -1,
     388,   389,    -1,    -1,   392,   114,   115,   116,   117,    -1,
      -1,    -1,    -1,   385,   123,   124,   388,   389,   127,   128,
     392,   409,    -1,    -1,   412,   413,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   144,    -1,   409,    -1,    -1,
     412,   413,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,   160,   161,   162,    -1,    -1,   165,   166,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   174,    -1,    -1,    -1,    -1,
      -1,    -1,   181,   182,    -1,    -1,    -1,    -1,   187,   188,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,   200,    -1,    -1,     3,     4,     5,     6,     7,     8,
       9,    -1,    11,    12,    13,    14,    15,    -1,    17,    18,
      19,    20,    21,    22,    23,    24,    25,    -1,   227,    -1,
      -1,    -1,    -1,    -1,    -1,   234,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    45,    -1,    -1,    48,
      -1,    -1,    -1,    52,    -1,    -1,    -1,   256,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   265,    -1,    67,    -1,
     269,    -1,   271,     3,     4,     5,     6,     7,     8,     9,
      -1,    11,    12,    13,    -1,    15,    -1,    -1,    18,    -1,
      -1,    21,    22,    23,    24,    25,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,   303,    35,    36,    37,    -1,    39,
      40,    41,    42,    -1,    -1,    45,    -1,    -1,    48,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    67,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   344,    -1,    -1,   347,   348,
      -1,    -1,    -1,   352,   353,    -1,    -1,    -1,    -1,   358,
      -1,    -1,    -1,    -1,    -1,   364,     3,     4,     5,     6,
       7,     8,     9,    -1,    -1,    12,    13,    -1,    15,    -1,
      -1,    18,    -1,    -1,    21,    22,    23,    24,    25,    -1,
      -1,    -1,    15,    16,    -1,    -1,   395,    -1,    35,    36,
      37,   400,    39,    40,    41,    42,    -1,    -1,    45,    -1,
      -1,    48,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    45,    -1,    -1,    48,    15,    16,    -1,    -1,
      67,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,
      15,    16,    -1,    -1,    53,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    49,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    -1,    -1,    35,    36,    37,    -1,    39,
      40,    41,    42,    43,    -1,    -1,    -1,    -1,    48,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,
      60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,
      -1,    -1,    48,    49,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,
      -1,    67,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    -1,    -1,    -1,    48,    15,    16,
      -1,    -1,    53,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      -1,    48,    49,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    46,    -1,    48,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,
      48,    49,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    49,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    45,    -1,    -1,
      48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      -1,    35,    36,    37,    -1,    39,    40,    41,    42,    -1,
      -1,    -1,    -1,    -1,    48,    49,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,
      64,    65,    -1,    67,    -1,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    -1,    -1,    -1,    48,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      -1,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final short yystos_[] = yystos_init();
  private static final short[] yystos_init()
  {
    return new short[]
    {
       0,     3,     4,     5,     6,     7,     8,     9,    11,    12,
      13,    14,    15,    17,    18,    19,    20,    21,    22,    23,
      24,    25,    35,    36,    37,    39,    40,    41,    42,    45,
      48,    52,    67,    69,    70,    71,    73,    75,    77,    78,
      79,    80,    81,    82,    83,    84,    85,    86,    87,    90,
      91,    94,    95,    97,   114,   115,   116,   117,   118,   120,
     121,   124,   126,    41,    43,    43,    43,    37,    39,    40,
      41,    43,    47,    41,    73,    41,    97,    48,    48,    41,
      41,    48,    16,    35,    36,    41,    43,    48,    59,    60,
      64,    65,    95,    99,   100,   101,   102,   103,   104,   105,
     106,   107,   108,   109,   110,   111,   112,   113,   114,   115,
     118,   119,   120,   121,    48,    48,    48,    48,    41,   109,
     118,   121,   109,    33,    34,    47,    48,    51,    52,    54,
      88,    41,    46,    73,    74,    76,    87,    97,    99,    41,
      97,    37,    38,    41,    45,   127,     0,    41,    87,    90,
      69,    69,    69,    69,    41,    97,    43,    41,    44,   122,
      52,    33,    34,    88,    43,    33,    34,    88,    43,    47,
      47,    47,    47,    48,    52,    25,    43,    52,    88,    41,
      41,    43,    87,    97,    99,    44,    72,    48,    52,    54,
      99,   109,   109,    41,    97,   110,   110,   109,   109,    43,
      66,    28,    27,    56,    57,    58,    29,    30,    31,    32,
      54,    55,    59,    60,    61,    62,    63,    99,    99,    99,
      99,    43,    43,    99,    99,    79,    41,    48,    49,    93,
      96,    97,    99,   125,    45,    99,    53,    99,    97,    98,
      76,    46,    41,    49,    44,    99,    48,    48,    69,    69,
      48,    41,    43,    48,    88,    41,    48,    95,   123,    99,
      99,    99,    99,    99,    49,    48,    43,    43,    88,    47,
      99,    43,    99,    41,    49,    41,    43,    49,    89,    99,
      53,    99,    98,    49,    49,    99,   101,   102,   103,   104,
     105,   106,   106,   107,   107,   107,   107,   108,   108,   110,
     110,   110,    49,    50,    49,    49,    43,    43,    50,    49,
      93,    97,    75,    49,    49,    41,    50,    50,    46,    89,
      53,    50,    55,    53,    46,   127,    41,    49,    49,    93,
      48,    49,    93,    43,    99,   122,    53,    43,    43,    43,
      43,    99,    43,    99,    43,    49,    99,    43,    47,    73,
      72,    49,    50,    48,    53,    55,    75,   110,    47,    75,
      99,    75,    73,    93,    26,    49,    75,    50,    96,    96,
      46,    98,    49,    50,   128,    43,    75,    92,    49,    49,
      93,    92,    49,    49,    49,    49,    99,    73,    49,    49,
      99,    99,    10,    89,    99,    48,    99,    49,    75,    99,
      26,    93,   127,    49,    92,    92,    49,    92,    73,    49,
      73,    73,    49,    49,    73,    49,    49,    89,    75,    75,
      99,    51,    92,    73,    73,    73,    49,   127,   128
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final short yyr1_[] = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    68,    69,    69,    69,    69,    69,    69,    69,    70,
      70,    70,    71,    72,    72,    73,    73,    73,    73,    73,
      73,    73,    73,    73,    73,    73,    73,    73,    73,    73,
      73,    74,    74,    75,    75,    76,    76,    77,    78,    78,
      78,    78,    78,    78,    78,    79,    79,    80,    80,    81,
      81,    81,    81,    81,    81,    81,    82,    83,    84,    85,
      86,    86,    86,    86,    86,    87,    87,    87,    87,    87,
      87,    88,    88,    88,    89,    89,    90,    90,    90,    90,
      90,    90,    90,    90,    91,    92,    92,    93,    93,    93,
      93,    94,    94,    94,    94,    94,    94,    95,    95,    96,
      96,    96,    96,    97,    97,    97,    98,    98,    99,    99,
      99,   100,   100,   101,   101,   102,   102,   103,   103,   104,
     104,   105,   105,   105,   106,   106,   106,   106,   106,   107,
     107,   107,   108,   108,   108,   108,   109,   109,   109,   109,
     109,   110,   110,   111,   111,   111,   112,   113,   113,   113,
     114,   114,   115,   115,   116,   116,   116,   116,   116,   117,
     117,   117,   117,   118,   118,   119,   120,   120,   120,   121,
     122,   122,   123,   123,   123,   124,   124,   124,   124,   124,
     124,   124,   125,   125,   125,   125,   126,   126,   127,   127,
     127,   127,   128,   128
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     3,     2,     2,     2,     3,     2,     0,     1,
       1,     1,     4,     0,     3,     1,     2,     3,     3,     2,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     3,     2,     1,     2,     3,     2,     3,
       2,     3,     2,     2,     2,     5,     7,     5,     6,     8,
       7,     7,     6,     8,     7,     8,     5,     5,     7,     5,
       3,     3,     3,     3,     2,     4,     4,     5,     3,     3,
       4,     2,     3,     4,     1,     3,     6,     7,     5,     6,
       5,     6,     4,     5,     4,     1,     1,     1,     2,     3,
       4,     4,     4,     4,     4,     4,     4,     3,     4,     1,
       1,     3,     3,     3,     1,     4,     1,     3,     1,     1,
       1,     3,     1,     3,     1,     1,     3,     1,     3,     1,
       3,     1,     3,     3,     1,     3,     3,     3,     3,     1,
       3,     3,     1,     3,     3,     3,     2,     2,     2,     2,
       1,     1,     4,     1,     2,     2,     1,     1,     1,     1,
       1,     1,     3,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     4,     4,     5,     2,     2,     2,     2,
       3,     2,     1,     1,     3,     2,     4,     5,     5,     7,
       7,     8,     5,     5,     4,     4,     5,     6,     3,     1,
       1,     1,     5,     0
    };
  }

  /* YYTOKEN_NUMBER[YYLEX-NUM] -- Internal symbol number corresponding
      to YYLEX-NUM.  */
  private static final short yytoken_number_[] = yytoken_number_init();
  private static final short[] yytoken_number_init()
  {
    return new short[]
    {
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,    59,    46,   123,   125,    58,    40,    41,
      44,    61,    91,    93,    60,    62,   124,    94,    38,    43,
      45,    42,    47,    37,    33,   126,    63,    35
    };
  }

  /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
     First, the terminals, then, starting at \a yyntokens_, nonterminals.  */
  private static final String yytname_[] = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "$end", "error", "$undefined", "BREAK", "CANCEL", "CANCEL_ALL", "CASE",
  "CONTINUE", "DEFAULT", "DO", "ELSE", "FINAL", "FOR", "IF", "IMPORT",
  "NEW", "NULL", "PRIVATE", "PROPOSE", "PROTECTED", "PUBLIC", "RETURN",
  "SWITCH", "TIMEOUT", "TIMEOUT_BEHAVIOUR", "WHILE", "ARROW", "ANDAND",
  "OROR", "EQEQ", "NOTEQ", "GTEQ", "LTEQ", "MINUSEQ", "PLUSEQ",
  "MINUSMINUS", "PLUSPLUS", "STRING", "WILDCARD", "INT", "BOOL_LITERAL",
  "VARIABLE", "OTHER_LITERAL", "';'", "'.'", "'{'", "'}'", "':'", "'('",
  "')'", "','", "'='", "'['", "']'", "'<'", "'>'", "'|'", "'^'", "'&'",
  "'+'", "'-'", "'*'", "'/'", "'%'", "'!'", "'~'", "'?'", "'#'", "$accept",
  "grammar_file", "visibility_spec", "imports", "path", "statement",
  "blk_statement", "block", "statements", "grammar_rule",
  "return_statement", "if_statement", "while_statement", "for_statement",
  "propose_statement", "timeout_behaviour_statement", "timeout_statement",
  "switch_statement", "label_statement", "var_def", "assgn_exp",
  "nonempty_exp_list", "method_declaration", "class_spec", "opt_block",
  "args_list", "set_operation", "function_call", "nonempty_args_list",
  "type_spec", "type_spec_list", "exp", "ConditionalOrExpression",
  "ConditionalAndExpression", "InclusiveOrExpression",
  "ExclusiveOrExpression", "AndExpression", "EqualityExpression",
  "RelationalExpression", "AdditiveExpression", "MultiplicativeExpression",
  "UnaryExpression", "CastExpression", "LogicalUnaryExpression",
  "PostfixExpression", "PrimaryExpression", "NotJustName",
  "ComplexPrimary", "ComplexPrimaryNoParenthesis", "Literal",
  "ArrayAccess", "ConditionalExpression", "assignment", "field_access",
  "field_access_rest", "simple_nofa_exp", "new_exp", "lambda_exp",
  "dialogueact_exp", "da_token", "da_args", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   115,   115,   118,   119,   121,   122,   125,   128,   132,
     133,   134,   138,   144,   145,   149,   150,   151,   157,   163,
     164,   165,   166,   167,   168,   169,   170,   171,   172,   173,
     174,   178,   179,   185,   186,   190,   191,   195,   199,   200,
     201,   202,   203,   204,   205,   209,   210,   216,   217,   223,
     225,   227,   229,   231,   233,   237,   246,   250,   256,   262,
     288,   295,   302,   309,   316,   325,   328,   331,   334,   337,
     340,   346,   347,   350,   356,   357,   361,   364,   367,   370,
     373,   376,   379,   382,   387,   391,   392,   396,   397,   398,
     399,   407,   411,   415,   418,   421,   424,   433,   436,   442,
     443,   444,   445,   449,   450,   451,   457,   458,   462,   463,
     464,   469,   472,   476,   479,   483,   484,   490,   491,   497,
     498,   504,   505,   508,   514,   515,   518,   521,   524,   530,
     531,   534,   540,   541,   544,   547,   553,   557,   561,   562,
     563,   567,   568,   573,   574,   575,   579,   585,   586,   587,
     591,   592,   596,   597,   601,   602,   603,   604,   605,   609,
     610,   611,   612,   616,   620,   624,   630,   634,   635,   639,
     647,   648,   652,   653,   654,   658,   659,   662,   665,   669,
     673,   677,   684,   687,   690,   693,   700,   703,   709,   710,
     711,   712,   716,   719
    };
  }


  // Report on the debug stream that the rule yyrule is going to be reduced.
  private void yy_reduce_print (int yyrule, YYStack yystack)
  {
    if (yydebug == 0)
      return;

    int yylno = yyrline_[yyrule];
    int yynrhs = yyr2_[yyrule];
    /* Print the symbols being reduced, and their result.  */
    yycdebug ("Reducing stack by rule " + (yyrule - 1)
              + " (line " + yylno + "), ");

    /* The symbols being reduced.  */
    for (int yyi = 0; yyi < yynrhs; yyi++)
      yy_symbol_print ("   $" + (yyi + 1) + " =",
                       yystos_[yystack.stateAt(yynrhs - (yyi + 1))],
                       ((yystack.valueAt (yynrhs-(yyi + 1)))),
                       yystack.locationAt (yynrhs-(yyi + 1)));
  }

  /* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
  private static final byte yytranslate_table_[] = yytranslate_table_init();
  private static final byte[] yytranslate_table_init()
  {
    return new byte[]
    {
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    64,     2,    67,     2,    63,    58,     2,
      48,    49,    61,    59,    50,    60,    44,    62,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,    47,    43,
      54,    51,    55,    66,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    52,     2,    53,    57,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    45,    56,    46,    65,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42
    };
  }

  private static final byte yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 1524;
  private static final int yynnts_ = 61;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 146;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 68;

  private static final int yyuser_token_number_max_ = 297;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */
/* Unqualified %code blocks.  */
/* "VondaGrammar.y":58  */ /* lalr1.java:1066  */

  private List<RudiTree> _statements = new LinkedList<>();

  private GrammarFile _result = new GrammarFile(_statements);

  public GrammarFile getResult() { return _result; }

/* "VondaGrammar.java":3226  */ /* lalr1.java:1066  */

}

