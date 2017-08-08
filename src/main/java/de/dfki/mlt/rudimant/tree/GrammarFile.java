/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Utils.*;
import static de.dfki.mlt.rudimant.io.RobotGrammarParser.*;

import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.*;
import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;

/**
 * class that represents a top-level file that was handed over to GrammarMain (=
 * the root of the whole tree)
 *
 * @author Anna Welker
 */
public class GrammarFile extends RudiTree implements RTBlockNode {

  public static final Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  // imports* (comment grammar_rule | method_declaration | statement )* comment
  List<RudiTree> rules;
  static Writer out;

  public LinkedList<Token> tokens;


  // **********************************************************************
  // static methods
  // **********************************************************************

  private static GrammarFile parseInput(final String realName, InputStream in)
      throws IOException {
    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(new ANTLRInputStream(in));

    List<Integer> toCollect = Arrays.asList(
        new Integer[] { JAVA_CODE, ONE_L_COMMENT, MULTI_L_COMMENT, NLWS });
    CollectorTokenSource collector = new CollectorTokenSource(lexer, toCollect);

    // initialise the parser
    RobotGrammarParser parser =
        new RobotGrammarParser(new CommonTokenStream(collector));
    final boolean[] errorOccured = { false };
    parser.addErrorListener(new BaseErrorListener() {
      @Override
      public void syntaxError(Recognizer<?, ?> recognizer,
          Object offendingSymbol, int line, int charPositionInLine, String msg,
          RecognitionException e) {
        errorOccured[0] = true;
        logger.error("{}.rudi:{}:{}: {}", realName, line, charPositionInLine, msg);
      }
    });

    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();
    if (errorOccured[0])
      return null;

    // initialise the visitor that will do all the work
    ParseTreeVisitor visitor = new ParseTreeVisitor(realName);

    // create the abstract syntax tree
    RudiTree myTree = visitor.visit(tree);
    if (! (myTree instanceof GrammarFile))
      return null;
    GrammarFile result = (GrammarFile)myTree;
    result.tokens = collector.getCollectedTokens();
    return (GrammarFile)myTree;
  }


  /** Prerequisite: mem.enterClass(<className>) has been called, and
   *  mem.leaveClass(<className>) is called afterwards. Parse the content of
   *  in and write the generated output to output
   * @param in     the stream containing rudi input
   * @param output the writer to output generated java code
   * @return
   * @throws IOException
   */
  public static GrammarFile parseAndTypecheck(RudimantCompiler rudi,
      InputStream in, String inputRealName) throws IOException {
    GrammarFile gf = parseInput(inputRealName, in);
    logger.info("Done parsing ");
    if (gf == null) return null;

    // do the type checking, which also adds function and variable definitions
    // to Mem
    gf.startTypeInference(rudi, rudi.typeErrorsFatal());
    logger.info("Done type checking");
    return gf;
  }

  public void generate(RudimantCompiler rudi, Writer output) throws IOException {
    // generate the output
    startGeneration(rudi, output);
    logger.info("Done generating code");
  }

  /** Constructor, only to be used by the parser */
  public GrammarFile(List<RudiTree> rules) {
    this.rules = rules;
  }

  // As expected, this works equally well.
  private void startTypeInference(RudimantCompiler rudi, boolean errorsFatal) {
    Mem mem = rudi.getMem();
    VisitorType ttv = new VisitorType(mem, errorsFatal);

    for (RudiTree t : rules) {
      if (t instanceof Import) {
        Import node = (Import)t;
        String conargs = "";
        mem.addImport(node.name, conargs);
        rudi.processImport(node.content);
      } else if (t instanceof RTStatement) {
        ((RTStatement)t).visit(ttv);
      } else if (t instanceof RTExpression) {
        ((RTExpression)t).visit(ttv);
      }
    }
  }

  private void writeRuleList(Writer out, Mem mem, VisitorGeneration gv)
      throws IOException{
    List<RTStatement> later = new ArrayList<>();
    // do all assignments on toplevel here, those are class attributes
    for(RudiTree r : rules){
      if(r instanceof StatExpression &&
          ((StatExpression)r).expression instanceof ExpAssignment){
        ExpAssignment ass = (ExpAssignment)((StatExpression)r).expression;
        if(ass.declaration) {
          out.append(ass.type.toString()).append(" ")
             .append(ass.left.fullexp).append(";\n");
          ass.declaration = false;
        }
      }
    }
    // create the process method
    out.append("  public boolean process(){\n");
    // use all methods created from rules in this file
    for(RudiTree r : rules) {
      if (r instanceof StatMethodDeclaration) {
        // retain method declarations for later
        // TODO: also move all appropriate comments to a laterComments list
        later.add((StatMethodDeclaration)r);
        continue;
      }

      if (r instanceof StatGrammarRule || r instanceof Import) {
        // rules and imports are called as functions and may return a non-zero
        // value. If the value is 1
        out.append("if (");
        if (r instanceof StatGrammarRule){
          out.append(((StatGrammarRule)r).label).append("()");
          // TODO: move all appropriate comments to a laterComments list
          later.add((StatGrammarRule)r);
        } else {
          out.append("new " + capitalize(((Import)r).name) + "(");
          Set<String> ncs = mem.getNeededClasses();
          if (ncs != null) {
            boolean notfirst = false;
            for (String c : ncs) {
              if (c.equals(mem.getClassName())
                  || capitalize(c).equals(mem.getClassName())) {
                c = "this";
              }
              if (notfirst) out.append(", ");
              else notfirst = true;
              out.append(lowerCaseFirst(c));
            }
          }
          out.append(").process()");
        }
        out.append(") return true;\n");
      } else if (r instanceof RTStatement) {
        gv.visitNode((RTStatement)r);
      }
    }
    out.append("return false; \n}\n");
    // now, add everything that we did not want in the process method
    // TODO: replace the comments list by the laterComments list
    for(RTStatement t : later){
      gv.visitNode(t);
    }
  }


  private void startGeneration(RudimantCompiler rudi, Writer out)
      throws IOException  {
    Mem mem = rudi.getMem();
    // tell the file in which package it lies
    String pkg = rudi.getPackageName();
    if (pkg == null) {
      pkg = "";
    } else {
      out.append("package " + pkg + ";\n");
      pkg += ".";
    }
    out.append("import java.util.*;\n\n" +
        "import de.dfki.mlt.rudimant.agent.DialogueAct;\n" +
        "import de.dfki.lt.hfc.db.rdfProxy.*;\n" +
        "import de.dfki.lt.hfc.types.*;\n");
    // Let's import our supersuper class, if we're the top-level class
    if (rudi.getParent() == null) {
      out.append("import ").append(rudi.getWrapperClass()).append(";\n");
    }

    // Now, print all initial comments (preceding the first element) before the
    // class starts, for, e.g., imports
    int firstPos = rules.get(0).positions[0];
    Iterator<Token> it = tokens.iterator();
    while (it.hasNext()) {
      Token curr = it.next();
      if (curr.getTokenIndex() < firstPos && curr.getText().startsWith("/*@")) {
        out.append(RudiTree.removeJavaBrackets(curr.getText()));
        it.remove();
      } else break;
    }

    // maybe we need to import the class that imported us to use its variables
    out.append("public class ").append(mem.getClassName());
    // check if this should extend the wrapper class
    if (rudi.getParent() == null) {
      out.append(" extends ").append(rudi.getWrapperClass());
    }
    out.append("{\n");

    // create variable fields for all those classes whose concrete instances we
    // will need
    for (String n : mem.getNeededClasses()) {
      if(n.equals(mem.getClassName())) continue;
      out.append("private final ");
      out.append(capitalize(n)).append(' ').append(lowerCaseFirst(n));
      out.append(";\n");
    }
    // add a constructor
    // also, to use them for imports, declare those parameters class attributes
    String declare = "";

    // get all those classes the toplevel rules need
    boolean notfirst = false;
    out.append("public ").append(mem.getClassName()).append('(');
    for (String n : mem.getNeededClasses()) {
      if(n.equals(mem.getClassName())) continue;
      String name = lowerCaseFirst(n);
      if (notfirst)
        out.append(", ");
      out.append(capitalize(n)).append(' ').append(name);
      declare += "this." + name + " = " + name + ";\n";
      notfirst = true;
    }
    out.append(") {\n super();\n").append(declare).append("\n}\n");

    // finally, the main processing method that will call all rules and imports
    // declared in this file
    VisitorGeneration gv =
        new VisitorGeneration(out, mem, rudi.logRudi(), tokens);
    mem.enterEnvironment(this);
    writeRuleList(out, mem, gv);
    mem.leaveEnvironment(this);

    // at the very end of the file, there might still be unprinted comments
    for (Token comment : gv.collectedTokens) {
      out.append(RudiTree.removeJavaBrackets(comment.getText())).append("\n");
    }
    out.append("}\n");
  }

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    return rules;
  }

  @Override
  public String visitStringV(RTStringVisitor v) {
    throw new UnsupportedOperationException("not supported");
  }

  @Override
  public void visitVoidV(VisitorGeneration v) {
    throw new UnsupportedOperationException("not supported");
  }

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings, _parentBindings;

  public void setBindings(Environment parent, Environment local) {
    _parentBindings = parent; _localBindings = local;
  }

  public Environment getBindings() { return _localBindings; }

  public Environment getParentBindings() { return _parentBindings; }
}
