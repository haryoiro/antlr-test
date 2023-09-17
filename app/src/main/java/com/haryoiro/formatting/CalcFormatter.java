package com.haryoiro.formatting;

import com.haryoiro.antlr.CalcLexer;
import com.haryoiro.antlr.CalcParser;
import com.haryoiro.config.FormatOption;
import com.haryoiro.utils.IoUtils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;

public class CalcFormatter {

    static FormatOption formatOption = new FormatOption();

    /**
     * フォーマットオプションを指定してファイルをフォーマットする
     * @param file
     * @param opt
     * @return
     */
    public static String format(File file, FormatOption opt) {
        String target = IoUtils.readFile(file, 1024);
        return format(target, opt);
    }

    /**
     * デフォルトのフォーマットオプションでファイルをフォーマットする
     * @param file
     * @return
     */
    public static String format(File file) {
        return format(file, formatOption);
    }

    /**
     * フォーマットオプションを指定してフォーマットする
     *
     * @param target
     * @param opt
     * @return
     */
    public static String format(String target, FormatOption opt) {
        var stream = CharStreams.fromString(target);
        var lexer = new CalcLexer(stream);
        var tokens = new CommonTokenStream(lexer);
        var parser = new CalcParser(tokens);

        // パースを行い、ParseTree（構文木）を取得
        CalcParser.StartContext tree = parser.start();

        // Visitorを作成し、構文木を走査
        CalcFormatVisitor visitor = new CalcFormatVisitor(opt);

        return visitor.visit(tree);
    }

    /**
     * デフォルトのフォーマットオプションで渡された文字列をフォーマットする
     * @param target
     * @return
     */
    public static String formatFromString(String target) {
        return format(target, formatOption);
    }


}
