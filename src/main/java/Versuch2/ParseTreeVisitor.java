/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2;

import Versuch1.RobotContext;
import Versuch2.abstractTree.*;
import Versuch2.abstractTree.expressions.*;
import Versuch2.abstractTree.leafs.*;
import Versuch2.abstractTree.statements.*;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import de.dfki.mlt.rudimant.io.RobotGrammarVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author anna
 */
public class ParseTreeVisitor implements RobotGrammarVisitor<AbstractTree>{
  
  // this memory will know the types of variables
  private HashMap<String, AbstractType> memory;
  
  // the object that nows about context that we cannot see
  private RobotContext context;
  
  public ParseTreeVisitor(RobotContext context){
    this.memory = new HashMap<String, AbstractType>();
    this.context = context;
  }

  @Override
  public AbstractTree visitGrammar_file(RobotGrammarParser.Grammar_fileContext ctx) {
    ArrayList<AbstractTree> rules = new ArrayList<AbstractTree>();
    for (int i = 0; i < ctx.getChildCount(); i++){
      rules.add(this.visit(ctx.getChild(i)));
    }
    return new AGrammarFile(rules);
  }

  @Override
  public AbstractTree visitLabel(RobotGrammarParser.LabelContext ctx) {
    // this method should never be reached as label is a part of grammar rule and
    // is not invoked there
    return null;
  }

  @Override
  public AbstractTree visitGrammar_rule(RobotGrammarParser.Grammar_ruleContext ctx) {
    return new AGrammarRule(ctx.getChild(0).getText(),
            (AIfStatement)this.visit(ctx.getChild(1)));
  }

  @Override
  public AbstractTree visitStatement_block(RobotGrammarParser.Statement_blockContext ctx) {
    // LBRACE ( statement )* RBRACE
    List<AbstractStatement> statblock = new ArrayList<AbstractStatement> ();
    for (int i = 1; i < ctx.getChildCount() -1; i++){
      statblock.add((AbstractStatement)this.visit(ctx.getChild(i)));
    }
    return new AbstractBlock(statblock, true);
  }

  @Override
  public AbstractTree visitStatement(RobotGrammarParser.StatementContext ctx) {
    // childcount can be one to infinity since we allow comments
    // -> solution: use an abstract block without braces as return container
    List<AbstractStatement> statblock = new ArrayList<AbstractStatement> ();
    for (int i = 0; i < ctx.getChildCount(); i++){
      statblock.add((AbstractStatement)this.visit(ctx.getChild(i)));
    }
    return new AbstractBlock(statblock, false);
  }

  @Override
  public AbstractTree visitLoop_statement_block(RobotGrammarParser.Loop_statement_blockContext ctx) {
    // LBRACE ( statement )* RBRACE
    List<AbstractStatement> statblock = new ArrayList<AbstractStatement> ();
    for (int i = 1; i < ctx.getChildCount() -1; i++){
      statblock.add((AbstractStatement)this.visit(ctx.getChild(i)));
    }
    return new AbstractBlock(statblock, true);
  }

  @Override
  public AbstractTree visitLoop_statement(RobotGrammarParser.Loop_statementContext ctx) {
    // childcount can be one to infinity since we allow comments
    // -> solution: use an abstract block without braces as return container
    List<AbstractStatement> statblock = new ArrayList<AbstractStatement> ();
    for (int i = 0; i < ctx.getChildCount(); i++){
      statblock.add((AbstractStatement)this.visit(ctx.getChild(i)));
    }
    return new AbstractBlock(statblock, false);
  }

  @Override
  public AbstractTree visitFunction_call(RobotGrammarParser.Function_callContext ctx) {
    // as we do not declare funccalls in grammar files, this must be a function access
    return new AFunctAccess(context.getFunctionAccessType(ctx.getText()), ctx.getText());
  }

  @Override
  public AbstractTree visitArithmetic(RobotGrammarParser.ArithmeticContext ctx) {
    AArithmeticExp arit = new AArithmeticExp(
            (AbstractExpression)this.visit(ctx.getChild(ctx.getChildCount() - 1)),
            null, null, false);
    for (int i = ctx.getChildCount() - 2; i >= 0; i--){
      if(i % 2 == 1){
        arit = new AArithmeticExp(
                new AArithmeticExp(
                        (AbstractExpression)this.visit(ctx.getChild(i--)),
                              null, null, false),
                        arit, ctx.getChild(i).getText(), false);
      }
    }
    return arit;
  }

  @Override
  public AbstractTree visitTerm(RobotGrammarParser.TermContext ctx) {
    // dasselbe in Grün
    AArithmeticExp arit = new AArithmeticExp(
            (AbstractExpression)this.visit(ctx.getChild(ctx.getChildCount() - 1)),
            null, null, false);
    for (int i = ctx.getChildCount() - 2; i >= 0; i--){
      if(i % 2 == 1){
        arit = new AArithmeticExp(
                new AArithmeticExp(
                        (AbstractExpression)this.visit(ctx.getChild(i--)),
                              null, null, false),
                        arit, ctx.getChild(i).getText(), false);
      }
    }
    return arit;
  }

  @Override
  public AbstractTree visitFactor(RobotGrammarParser.FactorContext ctx) {
    // number
    if(ctx.getChildCount() == 1){
      return new ANumber(ctx.getText());
    }
    // MINUS arithmetic
    else if (ctx.getChildCount() == 2){
      return new AArithmeticExp(
              (AbstractExpression)this.visit(ctx.getChild(1)), null, null, true);
    }
    // LPAR arithmetic RPAR
    else{
      // we write a lot of nice parenthesis anyways
      return this.visit(ctx.getChild(1));
    }
  }

  @Override
  public AbstractTree visitArithmetic_operator(RobotGrammarParser.Arithmetic_operatorContext ctx) {
    // we should never get here since operators are directly used as Strings in arithmetics
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public AbstractTree visitArithmetic_lin_operator(RobotGrammarParser.Arithmetic_lin_operatorContext ctx) {
    // we should never get here since operators are directly used as Strings in arithmetics
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public AbstractTree visitArithmetic_dot_operator(RobotGrammarParser.Arithmetic_dot_operatorContext ctx) {
    // we should never get here since operators are directly used as Strings in arithmetics
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public AbstractTree visitNumber(RobotGrammarParser.NumberContext ctx) {
    return new ANumber(ctx.getChild(0).getText());
  }

  @Override
  public AbstractTree visitWhile_statement(RobotGrammarParser.While_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitExp(RobotGrammarParser.ExpContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitLiteral_or_graph_exp(RobotGrammarParser.Literal_or_graph_expContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitSimple_b_exp(RobotGrammarParser.Simple_b_expContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitBoolean_exp(RobotGrammarParser.Boolean_expContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitBoolean_op(RobotGrammarParser.Boolean_opContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitAssignment(RobotGrammarParser.AssignmentContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitPropose_arg(RobotGrammarParser.Propose_argContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitString_expression(RobotGrammarParser.String_expressionContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitPropose_statement(RobotGrammarParser.Propose_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitLoop_propose_statement(RobotGrammarParser.Loop_propose_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitPropose_block(RobotGrammarParser.Propose_blockContext ctx) {
    // LBRACE ( statement )* RBRACE
    List<AbstractStatement> statblock = new ArrayList<AbstractStatement> ();
    for (int i = 1; i < ctx.getChildCount() -1; i++){
      statblock.add((AbstractStatement)this.visit(ctx.getChild(i)));
    }
    return new AbstractBlock(statblock, true);
  }

  @Override
  public AbstractTree visitLoop_propose_block(RobotGrammarParser.Loop_propose_blockContext ctx) {
    // LBRACE ( statement )* RBRACE
    List<AbstractStatement> statblock = new ArrayList<AbstractStatement> ();
    for (int i = 1; i < ctx.getChildCount() -1; i++){
      statblock.add((AbstractStatement)this.visit(ctx.getChild(i)));
    }
    return new AbstractBlock(statblock, true);
  }

  @Override
  public AbstractTree visitIf_statement(RobotGrammarParser.If_statementContext ctx) {
    // IF LPAR boolean_exp RPAR statement (ELSE statement)?
    if(ctx.getChildCount() == 5){   // no else
      return new AIfStatement((ABooleanExp)this.visit(ctx.getChild(2)),
              (AbstractBlock) this.visit(ctx.getChild(4)), null);
    }
    // if there is an else
    return new AIfStatement((ABooleanExp)this.visit(ctx.getChild(2)),
            (AbstractBlock) this.visit(ctx.getChild(4)),
            (AbstractBlock) this.visit(ctx.getChild(6)));
  }

  @Override
  public AbstractTree visitLoop_if_statement(RobotGrammarParser.Loop_if_statementContext ctx) {
    // IF LPAR boolean_exp RPAR statement (ELSE statement)?
    if(ctx.getChildCount() == 5){   // no else
      return new AIfStatement((ABooleanExp)this.visit(ctx.getChild(2)),
              (AbstractBlock) this.visit(ctx.getChild(4)), null);
    }
    // if there is an else
    return new AIfStatement((ABooleanExp)this.visit(ctx.getChild(2)),
            (AbstractBlock) this.visit(ctx.getChild(4)),
            (AbstractBlock) this.visit(ctx.getChild(6)));
  }

  @Override
  public AbstractTree visitFor_statement(RobotGrammarParser.For_statementContext ctx) {
    if (ctx.getChildCount() == 7){
      // statement looks like "FOR LPAR VARIABLE COLON exp RPAR loop_statement_block"
      // TODO: or should we check here that the type of the variable in assignment
      // is the type the iterable in exp returns? How?
      return new AFor2Stat(new ALocalVar(ctx.getChild(2).getText()), this.visit(ctx.getChild(4)),
              (AbstractBlock)this.visit(ctx.getChild(6)));
    }
    else if(ctx.getChildCount() == 8){
      // statement looks like "FOR LPAR assignment SEMICOLON exp SEMICOLON RPAR loop_statement_block"
      return new AFor1Stat((AAssignment)this.visit(ctx.getChild(2)),
              (ABooleanExp)this.visit(ctx.getChild(4)),
              null, (AbstractBlock)this.visit(ctx.getChild(7)));
    }
    else if(ctx.getChildCount() == 9){
      // statement looks like "FOR LPAR assignment SEMICOLON exp SEMICOLON exp RPAR loop_statement_block"
      return new AFor1Stat((AAssignment)this.visit(ctx.getChild(2)),
              (ABooleanExp)this.visit(ctx.getChild(4)),
              (AbstractExpression)this.visit(ctx.getChild(6)),
              (AbstractBlock)this.visit(ctx.getChild(8)));
    }
    else{
      // statement looks like "FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR loop_statement_block"
      // TODO: implement For3Stat; exp will return some Object[]
      return new AFor3Stat();
    }
  }

  @Override
  public AbstractTree visitField_access(RobotGrammarParser.Field_accessContext ctx) {
    return new AFieldAccess(context.getFieldAccessType(ctx.getText()), ctx.getText());
  }

  @Override
  public AbstractTree visitField_access_vfunc(RobotGrammarParser.Field_access_vfuncContext ctx) {
    return new AFieldAccess(context.getFieldAccessType(ctx.getText()), ctx.getText());
  }

  @Override
  public AbstractTree visitLambda_exp(RobotGrammarParser.Lambda_expContext ctx) {
    return new ALambdaExp(ctx.getText());
  }

  @Override
  public AbstractTree visitComment(RobotGrammarParser.CommentContext ctx) {
    return new AComment(ctx.getText());
  }

  @Override
  public AbstractTree visit(ParseTree pt) {
    return pt.accept(this);
  }

  @Override
  public AbstractTree visitChildren(RuleNode rn) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitTerminal(TerminalNode tn) {
    switch(tn.getSymbol().getType()){
      case 9:   // token is TRUE
        return new AUnaryBoolean(tn.getText());
      case 10:  // token is FALSE
        return new AUnaryBoolean(tn.getText());
      case 11:  // token is character
        return new ACharacter(tn.getText());
      case 12:  // token is String
        return new AString(tn.getText());
      case 27:  // token is <=
        // TODO: how to determine whether this is normal boolean or magic?
      case 40:  //token is wildcard
        return new AWildcard();
      case 47:  // token is variable
        if (memory.containsKey(tn.getText())){
          return new ALocalVar(memory.get(tn.getText()), tn.getText());
        }
        else if (context.isGlobalVariable(tn.getText())){
          return new AGlobalVar(context.getVariableType(tn.getText()), tn.getText());
        }
        else{
          // TODO: find a nice exception
          throw new UnsupportedOperationException("This variable isn't declared "
                  + "anywhere: " + tn.getText());
        }
      case 48:  // token is int
        return new ANumber(tn.getText());
      case 49:  // token is float
        return new ANumber(tn.getText());
    }
    return null;
  }

  @Override
  public AbstractTree visitErrorNode(ErrorNode en) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
