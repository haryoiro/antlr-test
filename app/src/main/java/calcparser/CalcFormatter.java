package calcparser;

import calcparser.antlr.CalcBaseListener;
import calcparser.antlr.CalcListener;
import calcparser.antlr.CalcParser;

public class CalcFormatter extends CalcBaseListener implements CalcListener {

    private int indentLevel = 0;
    private StringBuilder formattedCode = new StringBuilder();

    private void addIndent() {
        formattedCode.append("  ".repeat(Math.max(0, indentLevel)));  // 2 spaces per indent level
    }

    private void addNewLine() {
        formattedCode.append("\n");
    }

    @Override
    public void exitAdd_expr(CalcParser.Add_exprContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            formattedCode.append(ctx.getChild(i).getText()).append(" ");
        }
        formattedCode.append("\n"); // 改行を追加

    }

    @Override
    public void exitMul_expr(CalcParser.Mul_exprContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            formattedCode.append(ctx.getChild(i).getText()).append(" ");
        }
        formattedCode.append("\n"); // 改行を追加

    }

    @Override
    public void exitAtom(CalcParser.AtomContext ctx) {
        if (ctx.NUMBER() != null) {
            formattedCode.append(ctx.NUMBER().getText()).append(" ");
        } else if (ctx.IDENTIFIER() != null) {
            formattedCode.append(ctx.IDENTIFIER().getText()).append(" ");
        }
    }

    public String getFormattedCode() {
        return formattedCode.toString().trim();
    }
}
