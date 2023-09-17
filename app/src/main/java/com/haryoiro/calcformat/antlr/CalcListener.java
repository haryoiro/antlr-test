// Generated from Calc.g4 by ANTLR 4.13.1

package com.haryoiro.calcformat.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalcParser}.
 */
public interface CalcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalcParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(CalcParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(CalcParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CalcParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CalcParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcParser#add_expr}.
	 * @param ctx the parse tree
	 */
	void enterAdd_expr(CalcParser.Add_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcParser#add_expr}.
	 * @param ctx the parse tree
	 */
	void exitAdd_expr(CalcParser.Add_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcParser#mul_expr}.
	 * @param ctx the parse tree
	 */
	void enterMul_expr(CalcParser.Mul_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcParser#mul_expr}.
	 * @param ctx the parse tree
	 */
	void exitMul_expr(CalcParser.Mul_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(CalcParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(CalcParser.AtomContext ctx);
}