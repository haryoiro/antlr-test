package com.haryoiro.calcformat.formatting;

import com.haryoiro.calcformat.antlr.CalcBaseVisitor;
import com.haryoiro.calcformat.antlr.CalcParser;
import com.haryoiro.calcformat.config.FormatOption;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class CalcFormatVisitor extends CalcBaseVisitor<String> {

    private final FormatOption option;

    private String joinExpressions(List<? extends ParserRuleContext> expressions,
            List<? extends TerminalNode> operators,
            Function<ParserRuleContext, String> visitorMethod) {
        StringBuilder sb = new StringBuilder();
        sb.append(visitorMethod.apply(expressions.get(0)));
        for (int i = 1; i < expressions.size(); i++) {
            sb.append(" ").append(operators.get(i - 1).getText()).append(" ");
            sb.append(visitorMethod.apply(expressions.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitStart(CalcParser.StartContext ctx) {
        return visit(ctx.expr()) + "\n";
    }

    @Override
    public String visitExpr(CalcParser.ExprContext ctx) {
        return visit(ctx.add_expr());
    }

    @Override
    public String visitAdd_expr(CalcParser.Add_exprContext ctx) {
        return "(" + joinExpressions(ctx.mul_expr(), ctx.PLUS_MINUS(), this::visit) + ")";
    }

    @Override
    public String visitMul_expr(CalcParser.Mul_exprContext ctx) {
        return "(" + joinExpressions(ctx.atom(), ctx.MUL_DIV(), this::visit) + ")";
    }


    @Override
    public String visitAtom(CalcParser.AtomContext ctx) {
        if (ctx.NUMBER() != null) {
            return ctx.NUMBER().getText();
        }
        if (ctx.IDENTIFIER() != null) {
            return ctx.IDENTIFIER().getText();
        }
        if (ctx.expr() != null) {
            return "(" + visit(ctx.expr()) + ")";
        }
        throw new RuntimeException("Unknown atom: " + ctx.getText());
    }
}
