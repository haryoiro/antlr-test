package com.haryoiro.calcformat.formatting;

import com.haryoiro.calcformat.antlr.CalcBaseVisitor;
import com.haryoiro.calcformat.antlr.CalcParser;
import com.haryoiro.calcformat.config.FormatOption;
import com.haryoiro.calcformat.config.FormatOption.Option;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.function.Function;

public class CalcFormatVisitor extends CalcBaseVisitor<String> {

    private final FormatOption option;

    private int indentLevel = 0;

    public CalcFormatVisitor(FormatOption option) {
        this.option = option;
    }

    private String indent() {
        if (getOption().isTabToSpace()) {
            return " ".repeat(indentLevel * getOption().getTabWidth());
        } else {
            return "\t".repeat(indentLevel);
        }
    }

    private String joinExpressions(ParserRuleContext ctx,
            List<? extends ParserRuleContext> expressions, List<? extends TerminalNode> operators,
            Function<ParserRuleContext, String> visitorMethod) {

        StringBuilder sb = new StringBuilder();

        boolean addParenthesis = (option.getOption().isAddParenthesis()
                && !(ctx instanceof CalcParser.ExprContext)
                && !(ctx instanceof CalcParser.Add_exprContext) && !ctx.getText().startsWith("("));

        if (addParenthesis) {
            applyGlobalFormatOptions(sb, true);
        }

        sb.append(visitorMethod.apply(expressions.get(0)));

        for (int i = 1; i < expressions.size(); i++) {
            if (getOption().isSpaceAroundOperator()) {
                sb.append(addSpaceToBoth(operators.get(i - 1).getText()));
            } else {
                sb.append(operators.get(i - 1).getText());
            }

            sb.append(visitorMethod.apply(expressions.get(i)));
        }

        if (addParenthesis) {
            applyGlobalFormatOptions(sb, false);
        }

        return sb.toString();
    }

    @Override
    public String visitStart(CalcParser.StartContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public String visitExpr(CalcParser.ExprContext ctx) {
        return visit(ctx.add_expr());
    }

    @Override
    public String visitAdd_expr(CalcParser.Add_exprContext ctx) {
        return joinExpressions(ctx, ctx.mul_expr(), ctx.PLUS_MINUS(), this::visit);
    }

    @Override
    public String visitMul_expr(CalcParser.Mul_exprContext ctx) {
        return joinExpressions(ctx, ctx.atom(), ctx.MUL_DIV(), this::visit);
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
            sb.append(visit(ctx.expr()));
        }

        return sb.toString();
    }

    // オプションをえる
    private Option getOption() {
        return option.getOption();
    }

    /**
     * オプションに従い括弧や改行、スペースを追加する
     *
     * @param sb
     * @param isStart
     */
    private void applyGlobalFormatOptions(StringBuilder sb, boolean isStart) {
        Option option = getOption();

        if (option.isAddParenthesis()) {
            if (isStart) {
                if (option.isNewLineAfterParenthesis()) {
                    indentLevel++;
                    sb.append("(\n").append(indent());
                } else if (option.isSpaceAroundParenthesis()) {
                    sb.insert(0, "( ");
                } else {
                    sb.insert(0, "(");
                }
            } else {
                if (option.isNewLineAfterParenthesis()) {
                    indentLevel--;
                    sb.append("\n").append(indent()).append(")");
                } else if (option.isSpaceAroundParenthesis()) {
                    sb.append(" )");
                } else {
                    sb.append(")");
                }
            }
        }
    }

    // 左側に空白を追加する
    private String addSpaceToLeft(String str) {
        return " " + str;
    }

    // 右側に空白を追加する
    private String addSpaceToRight(String str) {
        return str + " ";
    }

    // 両側に空白を追加する
    private String addSpaceToBoth(String str) {
        return addSpaceToLeft(addSpaceToRight(str));
    }

}
