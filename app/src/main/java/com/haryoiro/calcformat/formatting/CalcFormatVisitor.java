package com.haryoiro.calcformat.formatting;

import com.haryoiro.calcformat.antlr.CalcBaseVisitor;
import com.haryoiro.calcformat.antlr.CalcParser;
import com.haryoiro.calcformat.config.FormatOption;
import com.haryoiro.calcformat.config.FormatOption.Option;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Ascii.SPACE;

@RequiredArgsConstructor
public class CalcFormatVisitor extends CalcBaseVisitor<String> {

    private final FormatOption option;

    private int level = 0;
    private String indent() {
        return "  ".repeat(level);  // 2スペースでインデント
    }

    private String joinExpressions(List<? extends ParserRuleContext> expressions,
            List<? extends TerminalNode> operators,
            Function<ParserRuleContext, String> visitorMethod, boolean addParentheses) {
        StringBuilder sb = new StringBuilder();

        if (getOption().isAddParenthesis() && addParentheses) {
            sb.append(spaceAroundParentheses("("));
            if (getOption().isNewLineAfterParenthesis()) {
                sb.append("\n");
            }
        }
        if (getOption().isAddParenthesis() && getOption().isNewLineAfterParenthesis()) {
            sb.append(indent());
        }

        sb.append(visitorMethod.apply(expressions.get(0)));

        for (int i = 1; i < expressions.size(); i++) {

            if (getOption().isSpaceAroundOperator()) {
                sb.append(" ");
            }
            sb.append(operators.get(i - 1).getText());
            if (getOption().isSpaceAroundOperator()) {
                sb.append(" ");
            }

            if (getOption().isNewLineAfterParenthesis()) {
                sb.append("\n").append(indent());
            }

            sb.append(visitorMethod.apply(expressions.get(i)));
        }

        if (getOption().isAddParenthesis() && addParentheses) {
            if (getOption().isNewLineAfterParenthesis()) {
                sb.append("\n").append(indent());
            }
            sb.append(spaceAroundParentheses(")"));
        }

        return sb.toString();
    }

    @Override
    public String visitStart(CalcParser.StartContext ctx) {
        String origin = visit(ctx.expr());

        return origin + "\n";
    }

    @Override
    public String visitExpr(CalcParser.ExprContext ctx) {
        return visit(ctx.add_expr());
    }

    @Override
    public String visitAdd_expr(CalcParser.Add_exprContext ctx) {
        return joinExpressions(ctx.mul_expr(), ctx.PLUS_MINUS(), this::visit,
                ctx.mul_expr().size() > 1);
    }

    @Override
    public String visitMul_expr(CalcParser.Mul_exprContext ctx) {
        return joinExpressions(ctx.atom(), ctx.MUL_DIV(), this::visit, ctx.atom().size() > 1);
    }

    @Override
    public String visitAtom(CalcParser.AtomContext ctx) {
        StringBuilder sb = new StringBuilder();

        if (ctx.NUMBER() != null) {
            sb.append(ctx.NUMBER().getText());
        }
        if (ctx.IDENTIFIER() != null) {
            sb.append(ctx.IDENTIFIER().getText());
        }
        if (ctx.expr() != null) {
            var st = visit(ctx.expr());
            sb.append(st);
        }

        return sb.toString();
    }

    private Option getOption() {
        return option.getOption();
    }

    private String spaceAroundParentheses(String expr) {
        if (getOption().isSpaceAroundParenthesis()) {
            StringBuilder sb = new StringBuilder();

            if (expr.startsWith("(")) {
                sb.append("(");
                sb.append(" ");
            } else if (expr.endsWith(")")) {
                if (!getOption().isNewLineAfterParenthesis()) {
                    sb.append(" ");
                }
                sb.append(")");
            }

            return sb.toString();
        }

        return expr;
    }

}
