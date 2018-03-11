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
    /* "VondaGrammar.y":138  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((StatMethodDeclaration)(yystack.valueAt (3-(2)))).setVisibility(((String)(yystack.valueAt (3-(1))))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (3-(2)))));
  };
  break;
    

  case 3:
  if (yyn == 3)
    /* "VondaGrammar.y":141  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (2-(1))))); };
  break;
    

  case 4:
  if (yyn == 4)
    /* "VondaGrammar.y":142  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 5:
  if (yyn == 5)
    /* "VondaGrammar.y":144  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((Import)(yystack.valueAt (2-(1))))); };
  break;
    

  case 6:
  if (yyn == 6)
    /* "VondaGrammar.y":145  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(setPos(new StatFieldDef(((String)(yystack.valueAt (3-(1)))), ((StatVarDef)(yystack.valueAt (3-(2))))), yystack.locationAt (3-(1)), yystack.locationAt (3-(2))));
  };
  break;
    

  case 7:
  if (yyn == 7)
    /* "VondaGrammar.y":148  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1))));
  };
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":151  */ /* lalr1.java:489  */
    { yyval = _statements;};
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":155  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":156  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":157  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":161  */ /* lalr1.java:489  */
    {
    new Import((( String )(yystack.valueAt (4-(2)))), ((List<String>)(yystack.valueAt (4-(3)))).toArray(new String[((List<String>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":167  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(); };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":168  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(3)))); ((List<String>)(yystack.valueAt (3-(3)))).add((( String )(yystack.valueAt (3-(2))))); };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":172  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":173  */ /* lalr1.java:489  */
    { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":174  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":175  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    ExpArithmetic ar = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(2)))), es, "+");
    setPos(ar, (yyloc));
    yyval = setPos(new StatExpression(ar), (yyloc));
  };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":181  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    ExpArithmetic ar = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(2)))), es, "-");
    setPos(ar, (yyloc));
    yyval = setPos(new StatExpression(ar), (yyloc));
  };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":187  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":188  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":189  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":190  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":191  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":192  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":193  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":194  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":195  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":196  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":197  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":198  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":202  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":203  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":207  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":213  */ /* lalr1.java:489  */
    { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true), (yyloc)); };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":214  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":218  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":219  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":223  */ /* lalr1.java:489  */
    { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))), (yyloc)); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":227  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":228  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":229  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":230  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":231  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":232  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":233  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":237  */ /* lalr1.java:489  */
    { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null), (yyloc)); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":238  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":244  */ /* lalr1.java:489  */
    { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true), (yyloc)); };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":245  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false), (yyloc));
  };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":251  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":253  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":255  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":257  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))), (yyloc)); };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":259  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":261  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (7-(3))))), yystack.locationAt (7-(3)));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":265  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (8-(4))))), yystack.locationAt (8-(4)));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (8-(3)))), var, ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc));
  };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":274  */ /* lalr1.java:489  */
    { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":278  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatTimeout(null, ((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":284  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":290  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":316  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":323  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":330  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":337  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":344  */ /* lalr1.java:489  */
    {
    ExpSingleValue val = new ExpSingleValue("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":353  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":356  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":359  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), (( String )(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":362  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, new Type(null), (( String )(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(2))))), (yyloc));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":365  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":368  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":371  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3)))), null), (yyloc));
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":377  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":378  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (3-(2)), yystack.locationAt (3-(3)));
  };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":381  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(4)));
  };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":387  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":388  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":392  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), null, ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":395  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (7-(2)))), ((Type)(yystack.valueAt (7-(1)))), (( String )(yystack.valueAt (7-(3)))), ((LinkedList)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":398  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":401  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":404  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":407  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(1)))), null, (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":410  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), null, ((StatAbstractBlock)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":413  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (5-(1)))), ((LinkedList)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":418  */ /* lalr1.java:489  */
    { yyval = ((Type)(yystack.valueAt (4-(2)))); };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":422  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":423  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":427  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":428  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (2-(1))))); add((( String )(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":429  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (3-(3)))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":430  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":438  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":442  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":446  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":449  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":452  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":455  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":464  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":467  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false), (yyloc));
  };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":473  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":474  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":475  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":476  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":480  */ /* lalr1.java:489  */
    { yyval = new Type("Array", new Type((( String )(yystack.valueAt (3-(1)))))); };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":481  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":482  */ /* lalr1.java:489  */
    {
    yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":488  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":489  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":493  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":494  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":495  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":500  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||"), (yyloc));
  };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":503  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":507  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&"), (yyloc));
  };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":510  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":514  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":515  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|"), (yyloc));
  };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":521  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":522  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^"), (yyloc));
  };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":528  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":529  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&"), (yyloc));
  };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":535  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":536  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "=="), (yyloc));
  };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":539  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!="), (yyloc));
  };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":545  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":546  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<"), (yyloc));
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":549  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">"), (yyloc));
  };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":552  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">="), (yyloc));
  };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":555  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<="), (yyloc));
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":561  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":562  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+"), (yyloc));
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":565  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-"), (yyloc));
  };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":571  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":572  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*"), (yyloc));
  };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":575  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/"), (yyloc));
  };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":578  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%"), (yyloc));
  };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":584  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(2)))), setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "+"), (yyloc))), (yyloc));
  };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":588  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(2)))), setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "-"), (yyloc))), (yyloc));
  };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":600  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+"), (yyloc)); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":601  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-"), (yyloc)); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":602  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":606  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":607  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc)); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":612  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":613  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!"), (yyloc)); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":614  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~"), (yyloc)); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":618  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":619  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(1)))), es, "+"), (yyloc))), (yyloc));
  };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":623  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(1)))), es, "-"), (yyloc))), (yyloc));
  };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":630  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpSingleValue("null", "null"), (yyloc)); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":631  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":632  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":636  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":637  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":638  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (6-(3)))), ((RTExpression)(yystack.valueAt (6-(5))))), (yyloc)); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":642  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":643  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":647  */ /* lalr1.java:489  */
    { yyval = ((ExpSingleValue)(yystack.valueAt (1-(1)))); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":648  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":649  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":650  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":651  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":655  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":656  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":657  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":658  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":662  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":666  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc)); };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":670  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":676  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1)));
    yyval = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc));
  };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":680  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":681  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":685  */ /* lalr1.java:489  */
    {
    //List<String> repr = new ArrayList<>($2.size());
    //for (int i = 0; i < $2.size(); ++i) repr.add("");
    //$$ = setPos(new ExpFieldAccess($2, repr), @$); $2.addFirst($1);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":691  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":696  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":697  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":701  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":702  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":703  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":707  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))), (yyloc)); };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":708  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()), (yyloc));
  };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":711  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":714  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (5-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }}), (yyloc));
  };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":718  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (7-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (7-(6))))); }}), (yyloc));
  };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":722  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (7-(2)))), ((LinkedList<Type>)(yystack.valueAt (7-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (7-(4)))).size()])),
                    new LinkedList<>()), (yyloc));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":726  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (8-(2)))), ((LinkedList<Type>)(yystack.valueAt (8-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (8-(4)))).size()])),
                    ((LinkedList<RTExpression>)(yystack.valueAt (8-(7))))), (yyloc));
  };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":733  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":736  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":739  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(new LinkedList<>(), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":742  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(new LinkedList<>(), ((StatAbstractBlock)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 193:
  if (yyn == 193)
    /* "VondaGrammar.y":749  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(4)))), new LinkedList<RTExpression>()), (yyloc));
  };
  break;
    

  case 194:
  if (yyn == 194)
    /* "VondaGrammar.y":752  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5))))), (yyloc));
  };
  break;
    

  case 195:
  if (yyn == 195)
    /* "VondaGrammar.y":758  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 196:
  if (yyn == 196)
    /* "VondaGrammar.y":759  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 197:
  if (yyn == 197)
    /* "VondaGrammar.y":760  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 198:
  if (yyn == 198)
    /* "VondaGrammar.y":761  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpSingleValue((( String )(yystack.valueAt (1-(1)))), "String"), (yyloc)); };
  break;
    

  case 199:
  if (yyn == 199)
    /* "VondaGrammar.y":765  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 200:
  if (yyn == 200)
    /* "VondaGrammar.y":768  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    


/* "VondaGrammar.java":2113  */ /* lalr1.java:489  */
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

  private static final short yypact_ninf_ = -289;
  private static final short yytable_ninf_ = -156;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     750,    37,   -37,   -32,   347,     4,    26,   884,    66,    77,
     110,   123,   135,  -289,   147,  -289,  -289,   231,   150,   207,
     229,   249,  1279,  1279,  -289,  -289,  -289,   206,  -289,   818,
    1313,   274,   -25,   340,    19,   750,   750,  -289,  -289,  -289,
    -289,  -289,  -289,  -289,  -289,  -289,  -289,  -289,   750,   750,
     280,  -289,   102,   306,   299,   322,  -289,  -289,   106,   363,
     145,  -289,  -289,   372,  -289,  -289,  -289,   375,   399,   400,
     409,  -289,  -289,   275,  -289,   424,  -289,   420,   417,   422,
      47,  1347,   423,   453,  1347,  -289,  1279,  1279,   149,  -289,
    1381,  1415,  1415,  1279,  1279,   299,   -33,   436,   439,   428,
     430,   421,   304,    30,   228,   413,  -289,  -289,  -289,   318,
    -289,   299,   322,   438,  -289,  -289,   438,  1347,  1347,  1347,
    1347,   117,   455,  -289,  -289,   471,  1347,  1347,   495,   557,
     630,   899,   274,   472,  -289,  -289,   884,   470,  1381,    51,
      80,   467,  -289,  -289,  -289,  1347,   473,  -289,   458,   750,
     750,  -289,  -289,  -289,  -289,   465,   482,  -289,    72,  -289,
     402,  -289,  1347,  1347,  1347,  -289,  -289,  1347,  1347,  -289,
    -289,  -289,  -289,  -289,  -289,  -289,   933,   476,    92,  -289,
     474,   485,   198,   426,  1347,   968,   489,    78,   490,   491,
    1002,  1037,   274,   195,  -289,  -289,  1347,  -289,   434,   483,
    -289,  -289,  -289,  -289,  -289,  1347,  1415,  1415,  1415,  1415,
    1415,  1415,  1415,  1415,  1415,  1415,  1415,  1415,  1415,  1415,
    1415,  1415,  -289,  -289,   199,    55,   203,   214,  -289,  -289,
     -24,    13,  -289,   329,  1071,   488,   493,   494,   497,   294,
     496,  1106,   478,  -289,   -45,   498,   484,  -289,  -289,  -289,
     504,  -289,   501,   -41,   -25,   202,   472,  -289,  -289,   215,
     512,   513,  1347,  -289,   299,  -289,   237,   522,     1,    31,
      32,    33,    52,  -289,  1347,  -289,  -289,   523,  1347,    53,
    1140,    71,   361,   884,   423,  -289,  -289,   518,   295,   520,
      15,   515,   488,  1415,    -2,   439,   428,   430,   421,   304,
      30,    30,   228,   228,   228,   228,   413,   413,  -289,  -289,
    -289,   488,  1347,   488,   884,  -289,  -289,   530,   548,   526,
     271,  -289,   488,  -289,   529,  1449,  1449,  -289,   536,  -289,
     274,  -289,  1415,  -289,  -289,   314,   321,   488,   386,   527,
     344,   233,  -289,   386,   534,  -289,  -289,  -289,  -289,  -289,
    -289,   252,  -289,   258,  1347,   884,   262,  1175,  1347,   574,
    -289,  -289,  1347,  1347,  -289,   538,  -289,  -289,  1347,  -289,
     264,  -289,  -289,  -289,  1210,   561,  -289,   530,  -289,  -289,
    -289,  -289,   542,  -289,   -25,   546,  -289,  -289,  -289,   386,
     386,   551,  -289,  -289,   386,  -289,   884,   286,  -289,   884,
     884,   290,   292,   884,  -289,   293,  1244,   478,   488,  -289,
     478,  1210,  -289,  -289,   550,  -289,  -289,  -289,   386,  -289,
    -289,   884,  -289,  -289,   884,   884,  -289,  -289,  -289,   558,
    -289,  -289,   478,   -25,  -289,  -289,  -289,  -289,  -289,   559,
    -289
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
       0,     0,     0,     0,   165,   166,   168,   107,   167,     0,
       0,     0,     0,     0,     0,     8,     8,    15,    21,    23,
      27,    28,    29,    24,    26,    25,    30,    31,     8,     8,
       0,    22,   163,     0,     0,     0,   159,   160,   161,     0,
     162,   156,   164,     0,    42,    44,    45,     0,     0,     0,
       0,    46,    66,   107,    32,     0,    33,     0,   107,     0,
       0,     0,    13,   182,     0,   152,     0,     0,   155,    40,
       0,     0,     0,     0,     0,   163,     0,   113,   115,   117,
     118,   120,   122,   124,   127,   132,   144,   135,   143,   146,
     149,   153,   154,   161,   111,   112,   162,     0,     0,     0,
       0,   155,     0,   161,   162,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    36,    34,    37,     0,     0,     0,
     107,     0,   197,   198,   196,     0,     0,     1,   107,     8,
       8,     5,     4,     7,     3,   107,     0,    20,     0,   176,
       0,   175,     0,     0,     0,   174,    16,     0,     0,    17,
     173,    43,    62,    63,    64,    65,     0,     0,     0,    71,
       0,     0,     0,   107,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   140,   139,     0,   172,   155,     0,
     141,   142,   147,   148,    41,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   151,   150,     0,     0,     0,     0,    19,    18,
       0,     0,    39,   155,     0,   100,     0,     0,     0,   102,
     103,     0,    74,   106,     0,   109,     0,    70,    38,    35,
       0,   158,     0,     0,     0,     0,     0,     6,     2,     0,
       0,   179,     0,   180,   178,    72,     0,     0,     0,     0,
       0,     0,     0,   100,     0,    67,    73,     0,     0,     0,
       0,     0,     0,     0,    13,    12,   183,     0,    77,     0,
       0,     0,     0,     0,     0,   114,   116,   119,   121,   123,
     125,   126,   130,   131,   128,   129,   133,   134,   136,   137,
     138,     0,     0,     0,     0,    95,    94,     0,     0,     0,
       0,    85,     0,   101,    91,     0,     0,    75,     0,   169,
       0,   108,     0,    87,   195,     0,    90,     0,     0,     0,
       0,     0,   177,     0,     0,    68,   170,    97,    96,    99,
      98,     0,    69,     0,     0,     0,     0,     0,     0,    47,
      14,   184,     0,     0,   185,     0,    58,   145,     0,    61,
       0,    59,    49,    92,     0,     0,    86,     0,   104,   105,
      76,   110,   145,   193,     0,     0,    89,    88,    81,     0,
       0,     0,   181,    83,     0,    50,     0,     0,    54,     0,
       0,     0,     0,     0,    78,     0,     0,   171,     0,   192,
     191,     0,    93,   157,     0,   194,    82,    79,     0,    84,
      56,     0,    52,    53,     0,     0,    48,   186,   187,     0,
      60,   190,   189,     0,    80,    55,    51,    57,   188,   200,
     199
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -289,   -21,  -289,  -289,   324,   154,    11,  -289,    54,   475,
    -289,  -289,   487,  -289,  -289,  -289,  -289,  -289,  -289,  -289,
     302,    -1,  -238,   576,  -289,  -288,  -118,  -289,     2,    43,
     554,  -188,   373,  -289,   406,   411,   405,   410,   415,   183,
     281,   201,   100,   -48,  -289,  -289,  -289,    34,   126,  -289,
    -289,     0,  -289,   156,    17,   -22,  -289,  -289,  -289,  -289,
    -253,   181
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    33,    34,    35,   189,    74,   135,   136,    37,   137,
      38,    39,    40,    41,    42,    43,    44,    45,    46,    47,
      76,   197,   287,    49,    50,   388,   236,    51,    95,   237,
      77,   246,   139,    97,    98,    99,   100,   101,   102,   103,
     104,   105,   106,   107,   108,   109,   110,   111,   112,    56,
      57,   113,   114,   115,   116,   159,   264,    61,   240,    62,
     146,   385
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
      58,   335,    52,   328,   291,   334,    65,    58,   329,    52,
     204,    66,   142,   143,   151,   152,   144,    60,    75,   315,
     145,   205,   123,   123,    60,   205,   133,   153,   154,    58,
       8,    52,   161,   205,    54,    58,    58,    52,    52,   124,
     124,    54,   205,   200,   201,   368,    60,    71,    58,    58,
      52,    52,    60,    60,   346,   393,   316,   165,     8,   170,
     148,   213,   214,    54,   205,    60,    60,   205,   364,    54,
      54,    31,   133,    72,   347,   348,   349,   181,    63,   205,
      64,   205,    54,    54,   215,   216,   123,   123,   183,   161,
     184,   123,   123,   123,   123,   350,   354,   205,   205,   205,
     251,   416,   417,   124,   124,   312,   419,    78,   124,   124,
     124,   124,   165,   261,   357,   170,   319,   205,   205,   205,
     262,   205,   122,   125,   404,    80,    55,   283,   257,   258,
     434,   414,   180,    55,   132,   265,    58,   205,    52,   163,
     164,   339,   381,   130,   205,   157,   158,   256,   344,    58,
      58,    52,    52,    60,    36,    55,    59,   130,    81,   267,
     263,    55,    55,    59,    82,   176,    60,    60,   429,   196,
      54,   308,   309,   310,    55,    55,    83,   267,   167,   168,
     439,   277,   256,    54,    54,    59,   194,   195,   169,    36,
      36,    59,    59,   202,   203,    84,   130,   176,   117,   373,
     130,   196,    36,    36,    59,    59,   123,   123,   123,   123,
     123,   123,   123,   123,   123,   123,   123,   123,   123,   123,
     123,   123,   391,   124,   124,   124,   124,   124,   124,   124,
     124,   124,   124,   124,   124,   124,   124,   124,   124,   126,
     127,   276,   342,   336,   292,   367,    12,    85,   311,   130,
    -155,   337,   313,   128,   129,   118,   336,   130,   131,   412,
     132,   205,    55,   314,   338,   205,    86,    87,    24,   205,
      25,    26,    88,    28,    89,    55,    55,   119,   336,    90,
     205,   267,   392,    58,   382,    52,   343,   217,   218,   321,
      91,    92,    59,   123,   359,    93,    94,   120,    32,   205,
      60,   395,    48,    36,    36,    59,    59,   396,   126,   127,
     124,   399,   324,   408,    58,   140,    52,    54,   205,  -155,
     293,   155,   128,   176,   205,   372,   130,   131,   205,   132,
     205,    60,   123,   211,   212,   421,   149,    48,    48,   424,
     147,   425,   427,   158,   325,   362,   366,   160,    54,   124,
      48,    48,   205,   222,   223,    58,   205,    52,   205,   205,
     205,   205,  -107,   383,   384,   369,   398,   371,   378,   379,
    -107,   317,    60,   180,   162,   132,   376,   176,   -90,   317,
     130,   131,   185,   132,    67,   336,    68,    69,    70,    54,
      96,   321,   387,   390,   300,   301,    58,   387,    52,    58,
      58,    52,    52,    58,   265,    52,   166,   420,   358,    55,
     422,   423,   130,    60,   426,   171,    60,    60,   306,   307,
      60,    58,   172,    52,    58,    58,    52,    52,   409,   386,
      54,    29,   435,    54,    54,   436,   437,    54,    60,    59,
      55,    60,    60,   387,   387,   265,   173,   174,   387,   177,
     266,    48,    48,   130,   187,    54,   175,   193,    54,    54,
     179,   178,   430,   182,   206,   431,   207,   188,   130,   180,
      59,   132,   387,   278,   219,   220,   221,   130,   180,   210,
     132,    55,   176,  -107,   208,   130,   131,   209,   132,   130,
     224,   225,   226,   227,   302,   303,   304,   305,   228,   230,
     231,   190,   239,   242,   244,   191,   255,   192,    10,   130,
     180,    59,   132,   259,   229,   247,   249,   180,   253,   132,
     252,   254,    55,   260,   274,    55,    55,   243,   275,    55,
     282,   284,   293,    29,   285,   268,   269,   270,   324,   331,
     271,   272,   322,   323,   205,   333,   326,    55,   330,   239,
      55,    55,    59,   332,    53,    59,    59,   279,   281,    59,
     340,   176,    79,   288,   290,   345,   352,   361,   363,   244,
     365,   336,    12,    85,   374,   375,   389,    59,   294,   377,
      59,    59,   380,   394,   403,   141,   406,   411,    53,    53,
      53,   413,    86,    87,    24,   415,    25,    26,   233,    28,
     418,   433,    53,    53,   156,   234,   235,   438,   360,   384,
     150,   248,   295,   297,   288,   232,    91,    92,   296,   298,
     440,    93,    94,     0,    32,   299,     0,     0,     0,     0,
       0,     0,     0,     0,   186,   341,     0,     0,     0,     0,
       0,     0,     0,     0,   199,    12,    85,   351,     0,     0,
       0,   353,     0,   356,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    86,    87,    24,     0,    25,
      26,    88,    28,     0,     0,   241,     0,     0,    90,     0,
       0,     0,     0,   238,     0,   370,   245,     0,     0,    91,
      92,     0,   250,     0,    93,    94,     0,    32,   239,   239,
       0,     0,     0,    53,    53,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,   397,     0,     0,
     401,   402,     0,     0,     0,   288,   405,     0,     0,     0,
       0,   407,     0,     0,     0,     0,   245,   410,     0,     0,
       0,     0,     0,     1,     2,     3,     4,     5,     6,     7,
       0,     8,     9,    10,    11,    12,     0,    13,    14,    15,
      16,    17,    18,    19,    20,    21,     0,     0,     0,   288,
       0,     0,     0,     0,   432,    22,    23,    24,   320,    25,
      26,    27,    28,     0,     0,    29,     0,     0,    30,     0,
       0,     0,    31,     0,     0,     0,     0,     0,     0,   238,
       0,     0,     0,   238,     0,     0,     0,    32,     0,     0,
     238,     1,     2,     3,     4,     5,     6,     7,     0,     8,
       9,    10,     0,    12,     0,     0,    14,     0,     0,    17,
      18,    19,    20,    21,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    22,    23,    24,     0,    25,    26,    73,
      28,     0,     0,    29,   134,     0,    30,     0,     0,     0,
       0,   238,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   245,    32,     0,     1,     2,     3,
       4,     5,     6,     7,   238,     8,     9,    10,     0,    12,
       0,     0,    14,     0,     0,    17,    18,    19,    20,    21,
       0,     0,     0,     0,    12,    85,     0,     0,     0,    22,
      23,    24,     0,    25,    26,    73,    28,     0,     0,    29,
       0,   238,    30,     0,    86,    87,    24,     0,    25,    26,
      88,    28,     0,     0,     0,     0,     0,    90,    12,    85,
       0,    32,   243,     0,     0,     0,     0,     0,    91,    92,
       0,     0,     0,    93,    94,     0,    32,     0,    86,    87,
      24,     0,    25,    26,    88,    28,     0,     0,     0,     0,
       0,   234,   273,    12,    85,     0,     0,     0,     0,     0,
       0,     0,    91,    92,     0,     0,     0,    93,    94,     0,
      32,     0,     0,    86,    87,    24,     0,    25,    26,    88,
      28,   280,     0,     0,     0,     0,    90,    12,    85,     0,
       0,     0,     0,     0,     0,     0,     0,    91,    92,     0,
       0,     0,    93,    94,     0,    32,     0,    86,    87,    24,
       0,    25,    26,    88,    28,     0,     0,     0,     0,     0,
      90,   286,    12,    85,     0,     0,     0,     0,     0,     0,
       0,    91,    92,     0,     0,     0,    93,    94,     0,    32,
       0,     0,    86,    87,    24,     0,    25,    26,    88,    28,
       0,     0,     0,     0,     0,    90,    12,    85,     0,     0,
     289,     0,     0,     0,     0,     0,    91,    92,     0,     0,
       0,    93,    94,     0,    32,     0,    86,    87,    24,     0,
      25,    26,   233,    28,     0,     0,     0,     0,     0,   138,
     318,    12,    85,     0,     0,     0,     0,     0,     0,     0,
      91,    92,     0,     0,     0,    93,    94,     0,    32,     0,
       0,    86,    87,    24,     0,    25,    26,    88,    28,     0,
       0,     0,   327,     0,    90,    12,    85,     0,     0,     0,
       0,     0,     0,     0,     0,    91,    92,     0,     0,     0,
      93,    94,     0,    32,     0,    86,    87,    24,     0,    25,
      26,    88,    28,     0,     0,     0,     0,     0,    90,   355,
      12,    85,     0,     0,     0,     0,     0,     0,     0,    91,
      92,     0,     0,     0,    93,    94,     0,    32,     0,     0,
      86,    87,    24,     0,    25,    26,    88,    28,     0,     0,
       0,     0,     0,    90,   400,    12,    85,     0,     0,     0,
       0,     0,     0,     0,    91,    92,     0,     0,     0,    93,
      94,     0,    32,     0,     0,    86,    87,    24,     0,    25,
      26,    88,    28,     0,     0,    29,     0,     0,    90,    12,
      85,     0,     0,     0,     0,     0,     0,     0,     0,    91,
      92,     0,     0,     0,    93,    94,     0,    32,     0,    86,
      87,    24,     0,    25,    26,    88,    28,     0,     0,     0,
       0,     0,    90,   428,    12,    85,     0,     0,     0,     0,
       0,     0,     0,    91,    92,     0,     0,     0,    93,    94,
       0,    32,     0,     0,    86,    87,    24,     0,    25,    26,
     121,    28,     0,     0,     0,     0,     0,    30,    12,    85,
       0,     0,     0,     0,     0,     0,     0,     0,    91,    92,
       0,     0,     0,    93,    94,     0,    32,     0,    86,    87,
      24,     0,    25,    26,    88,    28,     0,     0,     0,     0,
       0,   138,    12,    85,     0,     0,     0,     0,     0,     0,
       0,     0,    91,    92,     0,     0,     0,    93,    94,     0,
      32,     0,    86,    87,    24,     0,    25,    26,    88,    28,
       0,     0,     0,     0,     0,    90,    12,    85,     0,     0,
       0,     0,     0,     0,     0,     0,    91,    92,     0,     0,
       0,    93,    94,     0,    32,     0,    86,    87,    24,     0,
      25,    26,   198,    28,     0,     0,     0,     0,     0,   138,
      12,    85,     0,     0,     0,     0,     0,     0,     0,     0,
      91,    92,     0,     0,     0,    93,    94,     0,    32,     0,
      86,    87,    24,     0,    25,    26,   121,    28,     0,     0,
       0,     0,     0,    90,    12,    85,     0,     0,     0,     0,
       0,     0,     0,     0,    91,    92,     0,     0,     0,    93,
      94,     0,    32,     0,    86,    87,    24,     0,    25,    26,
      88,    28,     0,     0,     0,     0,     0,   234,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    91,    92,
       0,     0,     0,    93,    94,     0,    32
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   254,     0,   241,   192,    46,    43,     7,    53,     7,
      43,    43,    37,    38,    35,    36,    41,     0,     7,    43,
      45,    66,    22,    23,     7,    66,    27,    48,    49,    29,
      11,    29,    54,    66,     0,    35,    36,    35,    36,    22,
      23,     7,    66,    91,    92,    47,    29,    43,    48,    49,
      48,    49,    35,    36,    53,   343,    43,    58,    11,    60,
      41,    31,    32,    29,    66,    48,    49,    66,    53,    35,
      36,    52,    73,    47,    43,    43,    43,    78,    41,    66,
      43,    66,    48,    49,    54,    55,    86,    87,    41,   111,
      43,    91,    92,    93,    94,    43,    43,    66,    66,    66,
      49,   389,   390,    86,    87,    50,   394,    41,    91,    92,
      93,    94,   113,    41,    43,   116,   234,    66,    66,    66,
      48,    66,    22,    23,   362,    48,     0,    49,   149,   150,
     418,   384,    52,     7,    54,    43,   136,    66,   136,    33,
      34,   259,   330,    51,    66,    43,    44,   148,   266,   149,
     150,   149,   150,   136,     0,    29,     0,    51,    48,   160,
     158,    35,    36,     7,    41,    48,   149,   150,   406,    52,
     136,   219,   220,   221,    48,    49,    41,   178,    33,    34,
     433,   182,   183,   149,   150,    29,    86,    87,    43,    35,
      36,    35,    36,    93,    94,    48,    51,    48,    48,   317,
      51,    52,    48,    49,    48,    49,   206,   207,   208,   209,
     210,   211,   212,   213,   214,   215,   216,   217,   218,   219,
     220,   221,   340,   206,   207,   208,   209,   210,   211,   212,
     213,   214,   215,   216,   217,   218,   219,   220,   221,    33,
      34,    43,   264,    41,    49,   293,    15,    16,    49,    51,
      44,    49,    49,    47,    48,    48,    41,    51,    52,   377,
      54,    66,   136,    49,    49,    66,    35,    36,    37,    66,
      39,    40,    41,    42,    43,   149,   150,    48,    41,    48,
      66,   282,    49,   283,   332,   283,    49,    59,    60,   235,
      59,    60,   136,   293,   283,    64,    65,    48,    67,    66,
     283,    49,     0,   149,   150,   149,   150,    49,    33,    34,
     293,    49,    41,    49,   314,    41,   314,   283,    66,    44,
      49,    41,    47,    48,    66,   314,    51,    52,    66,    54,
      66,   314,   332,    29,    30,    49,    34,    35,    36,    49,
       0,    49,    49,    44,    50,    50,   292,    41,   314,   332,
      48,    49,    66,    35,    36,   355,    66,   355,    66,    66,
      66,    66,    41,    49,    50,   311,   355,   313,   325,   326,
      41,    50,   355,    52,    52,    54,   322,    48,    49,    50,
      51,    52,    80,    54,    37,    41,    39,    40,    41,   355,
      17,   337,   338,    49,   211,   212,   396,   343,   396,   399,
     400,   399,   400,   403,    43,   403,    43,   396,    47,   283,
     399,   400,    51,   396,   403,    43,   399,   400,   217,   218,
     403,   421,    47,   421,   424,   425,   424,   425,   374,    43,
     396,    45,   421,   399,   400,   424,   425,   403,   421,   283,
     314,   424,   425,   389,   390,    43,    47,    47,   394,    25,
      48,   149,   150,    51,    81,   421,    47,    84,   424,   425,
      43,    41,   408,    41,    28,   411,    27,    44,    51,    52,
     314,    54,   418,    47,    61,    62,    63,    51,    52,    58,
      54,   355,    48,    49,    56,    51,    52,    57,    54,    51,
     117,   118,   119,   120,   213,   214,   215,   216,    43,   126,
     127,    48,   129,   130,   131,    52,    48,    54,    13,    51,
      52,   355,    54,    48,    43,    43,    46,    52,   145,    54,
      53,    48,   396,    41,    48,   399,   400,    53,    43,   403,
      41,    41,    49,    45,    43,   162,   163,   164,    41,    55,
     167,   168,    49,    49,    66,    44,    50,   421,    50,   176,
     424,   425,   396,    49,     0,   399,   400,   184,   185,   403,
      48,    48,     8,   190,   191,    43,    43,    49,    48,   196,
      55,    41,    15,    16,    26,    49,    49,   421,   205,    50,
     424,   425,    46,    49,    10,    31,    48,    26,    34,    35,
      36,    49,    35,    36,    37,    49,    39,    40,    41,    42,
      49,    51,    48,    49,    50,    48,    49,    49,   284,    50,
      34,   136,   206,   208,   241,   128,    59,    60,   207,   209,
     439,    64,    65,    -1,    67,   210,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    80,   262,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    90,    15,    16,   274,    -1,    -1,
      -1,   278,    -1,   280,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    35,    36,    37,    -1,    39,
      40,    41,    42,    -1,    -1,    45,    -1,    -1,    48,    -1,
      -1,    -1,    -1,   129,    -1,   312,   132,    -1,    -1,    59,
      60,    -1,   138,    -1,    64,    65,    -1,    67,   325,   326,
      -1,    -1,    -1,   149,   150,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   354,    -1,    -1,
     357,   358,    -1,    -1,    -1,   362,   363,    -1,    -1,    -1,
      -1,   368,    -1,    -1,    -1,    -1,   192,   374,    -1,    -1,
      -1,    -1,    -1,     3,     4,     5,     6,     7,     8,     9,
      -1,    11,    12,    13,    14,    15,    -1,    17,    18,    19,
      20,    21,    22,    23,    24,    25,    -1,    -1,    -1,   406,
      -1,    -1,    -1,    -1,   411,    35,    36,    37,   234,    39,
      40,    41,    42,    -1,    -1,    45,    -1,    -1,    48,    -1,
      -1,    -1,    52,    -1,    -1,    -1,    -1,    -1,    -1,   255,
      -1,    -1,    -1,   259,    -1,    -1,    -1,    67,    -1,    -1,
     266,     3,     4,     5,     6,     7,     8,     9,    -1,    11,
      12,    13,    -1,    15,    -1,    -1,    18,    -1,    -1,    21,
      22,    23,    24,    25,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    45,    46,    -1,    48,    -1,    -1,    -1,
      -1,   317,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,   330,    67,    -1,     3,     4,     5,
       6,     7,     8,     9,   340,    11,    12,    13,    -1,    15,
      -1,    -1,    18,    -1,    -1,    21,    22,    23,    24,    25,
      -1,    -1,    -1,    -1,    15,    16,    -1,    -1,    -1,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    45,
      -1,   377,    48,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    -1,    -1,    -1,    48,    15,    16,
      -1,    67,    53,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      -1,    48,    49,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    43,    -1,    -1,    -1,    -1,    48,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,
      48,    49,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    15,    16,    -1,    -1,
      53,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,
      49,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,
      -1,    35,    36,    37,    -1,    39,    40,    41,    42,    -1,
      -1,    -1,    46,    -1,    48,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,
      64,    65,    -1,    67,    -1,    35,    36,    37,    -1,    39,
      40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,    49,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,
      60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,    -1,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    49,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    -1,    -1,    35,    36,    37,    -1,    39,
      40,    41,    42,    -1,    -1,    45,    -1,    -1,    48,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,
      60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,
      -1,    -1,    48,    49,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,
      -1,    67,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    -1,    -1,    -1,    48,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      -1,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    -1,    -1,    -1,    48,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67
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
      48,    52,    67,    69,    70,    71,    73,    76,    78,    79,
      80,    81,    82,    83,    84,    85,    86,    87,    88,    91,
      92,    95,    96,    98,   115,   116,   117,   118,   119,   121,
     122,   125,   127,    41,    43,    43,    43,    37,    39,    40,
      41,    43,    47,    41,    73,    74,    88,    98,    41,    98,
      48,    48,    41,    41,    48,    16,    35,    36,    41,    43,
      48,    59,    60,    64,    65,    96,   100,   101,   102,   103,
     104,   105,   106,   107,   108,   109,   110,   111,   112,   113,
     114,   115,   116,   119,   120,   121,   122,    48,    48,    48,
      48,    41,   110,   119,   122,   110,    33,    34,    47,    48,
      51,    52,    54,    89,    46,    74,    75,    77,    48,   100,
      41,    98,    37,    38,    41,    45,   128,     0,    41,    88,
      91,    69,    69,    69,    69,    41,    98,    43,    44,   123,
      41,   123,    52,    33,    34,    89,    43,    33,    34,    43,
      89,    43,    47,    47,    47,    47,    48,    25,    41,    43,
      52,    89,    41,    41,    43,    88,    98,   100,    44,    72,
      48,    52,    54,   100,   110,   110,    52,    89,    41,    98,
     111,   111,   110,   110,    43,    66,    28,    27,    56,    57,
      58,    29,    30,    31,    32,    54,    55,    59,    60,    61,
      62,    63,    35,    36,   100,   100,   100,   100,    43,    43,
     100,   100,    80,    41,    48,    49,    94,    97,    98,   100,
     126,    45,   100,    53,   100,    98,    99,    43,    77,    46,
      98,    49,    53,   100,    48,    48,    89,    69,    69,    48,
      41,    41,    48,    96,   124,    43,    48,    89,   100,   100,
     100,   100,   100,    49,    48,    43,    43,    89,    47,   100,
      43,   100,    41,    49,    41,    43,    49,    90,   100,    53,
     100,    99,    49,    49,   100,   102,   103,   104,   105,   106,
     107,   107,   108,   108,   108,   108,   109,   109,   111,   111,
     111,    49,    50,    49,    49,    43,    43,    50,    49,    94,
      98,    76,    49,    49,    41,    50,    50,    46,    90,    53,
      50,    55,    49,    44,    46,   128,    41,    49,    49,    94,
      48,   100,   123,    49,    94,    43,    53,    43,    43,    43,
      43,   100,    43,   100,    43,    49,   100,    43,    47,    74,
      72,    49,    50,    48,    53,    55,    76,   111,    47,    76,
     100,    76,    74,    94,    26,    49,    76,    50,    97,    97,
      46,    99,   111,    49,    50,   129,    43,    76,    93,    49,
      49,    94,    49,    93,    49,    49,    49,   100,    74,    49,
      49,   100,   100,    10,    90,   100,    48,   100,    49,    76,
     100,    26,    94,    49,   128,    49,    93,    93,    49,    93,
      74,    49,    74,    74,    49,    49,    74,    49,    49,    90,
      76,    76,   100,    51,    93,    74,    74,    74,    49,   128,
     129
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
      73,    73,    74,    74,    75,    76,    76,    77,    77,    78,
      79,    79,    79,    79,    79,    79,    79,    80,    80,    81,
      81,    82,    82,    82,    82,    82,    82,    82,    83,    84,
      85,    86,    87,    87,    87,    87,    87,    88,    88,    88,
      88,    88,    88,    88,    89,    89,    89,    90,    90,    91,
      91,    91,    91,    91,    91,    91,    91,    92,    93,    93,
      94,    94,    94,    94,    95,    95,    95,    95,    95,    95,
      96,    96,    97,    97,    97,    97,    98,    98,    98,    99,
      99,   100,   100,   100,   101,   101,   102,   102,   103,   103,
     104,   104,   105,   105,   106,   106,   106,   107,   107,   107,
     107,   107,   108,   108,   108,   109,   109,   109,   109,   110,
     110,   110,   110,   110,   111,   111,   112,   112,   112,   113,
     113,   113,   114,   114,   114,   115,   115,   115,   116,   116,
     117,   117,   117,   117,   117,   118,   118,   118,   118,   119,
     119,   120,   121,   121,   121,   122,   122,   123,   123,   124,
     124,   124,   125,   125,   125,   125,   125,   125,   125,   126,
     126,   126,   126,   127,   127,   128,   128,   128,   128,   129,
     129
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     3,     2,     2,     2,     3,     2,     0,     1,
       1,     1,     4,     0,     3,     1,     2,     2,     3,     3,
       2,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     3,     2,     1,     2,     3,
       2,     3,     2,     3,     2,     2,     2,     5,     7,     5,
       6,     8,     7,     7,     6,     8,     7,     8,     5,     5,
       7,     5,     3,     3,     3,     3,     2,     4,     4,     5,
       3,     3,     3,     4,     2,     3,     4,     1,     3,     6,
       7,     5,     6,     5,     6,     4,     5,     4,     1,     1,
       1,     2,     3,     4,     4,     4,     4,     4,     4,     4,
       3,     4,     1,     1,     3,     3,     3,     1,     4,     1,
       3,     1,     1,     1,     3,     1,     3,     1,     1,     3,
       1,     3,     1,     3,     1,     3,     3,     1,     3,     3,
       3,     3,     1,     3,     3,     1,     3,     3,     3,     2,
       2,     2,     2,     1,     1,     4,     1,     2,     2,     1,
       2,     2,     1,     1,     1,     1,     1,     6,     3,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     4,
       4,     5,     2,     2,     2,     2,     2,     3,     2,     1,
       1,     3,     2,     4,     5,     5,     7,     7,     8,     5,
       5,     4,     4,     5,     6,     3,     1,     1,     1,     5,
       0
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
  "grammar_file", "visibility_spec", "imports", "path", "statement_no_def",
  "statement", "blk_statement", "block", "statements", "grammar_rule",
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
       0,   138,   138,   141,   142,   144,   145,   148,   151,   155,
     156,   157,   161,   167,   168,   172,   173,   174,   175,   181,
     187,   188,   189,   190,   191,   192,   193,   194,   195,   196,
     197,   198,   202,   203,   207,   213,   214,   218,   219,   223,
     227,   228,   229,   230,   231,   232,   233,   237,   238,   244,
     245,   251,   253,   255,   257,   259,   261,   265,   274,   278,
     284,   290,   316,   323,   330,   337,   344,   353,   356,   359,
     362,   365,   368,   371,   377,   378,   381,   387,   388,   392,
     395,   398,   401,   404,   407,   410,   413,   418,   422,   423,
     427,   428,   429,   430,   438,   442,   446,   449,   452,   455,
     464,   467,   473,   474,   475,   476,   480,   481,   482,   488,
     489,   493,   494,   495,   500,   503,   507,   510,   514,   515,
     521,   522,   528,   529,   535,   536,   539,   545,   546,   549,
     552,   555,   561,   562,   565,   571,   572,   575,   578,   584,
     588,   600,   601,   602,   606,   607,   612,   613,   614,   618,
     619,   623,   630,   631,   632,   636,   637,   638,   642,   643,
     647,   648,   649,   650,   651,   655,   656,   657,   658,   662,
     666,   670,   676,   680,   681,   685,   691,   696,   697,   701,
     702,   703,   707,   708,   711,   714,   718,   722,   726,   733,
     736,   739,   742,   749,   752,   758,   759,   760,   761,   765,
     768
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

  private static final int yylast_ = 1516;
  private static final int yynnts_ = 62;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 147;
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

  public <T extends RudiTree> T setPos(T rt, Location l) {
    rt.setPos(l, this);
    return rt;
  }

  public <T extends RudiTree> T setPos(T rt, Location start, Location end) {
    rt.setPos(start, end, this);
    return rt;
  }

  public String getFullText(Position start, Position end) {
  	return ((VondaLexer)this.yylexer).getFullText(start, end);
  }
  
  // if the left part is a variable, this must be transformed to StatVarDef
  private RudiTree getAssignmentStat(RTExpression assign) {
    assert(assign instanceof ExpAssignment);
    if (((ExpAssignment)assign).leftIsVariable()) {
      return new StatVarDef(false, new Type(null), assign);
    }
    return new StatExpression(assign);
  }

/* "VondaGrammar.java":3314  */ /* lalr1.java:1066  */

}

