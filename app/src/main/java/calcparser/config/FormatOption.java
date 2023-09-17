package calcparser.config;

public class FormatOption {

    // 括弧をつけるかどうか
    private boolean addParenthesis = false;

    // 括弧の前後にスペースを入れるかどうか
    // addParenthesisがfalseの場合は無視される
    private boolean spaceAroundParenthesis = true;

    // 括弧の後に改行を入れるかどうか
    // addParenthesisがfalseの場合は無視される
    private boolean newLineAfterParenthesis = false;

    // 演算子の前後にスペースを入れるかどうか
    private boolean spaceAroundOperator = false;
}
