package com.haryoiro.calcformat.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haryoiro.calcformat.config.impl.TomlFormatOptionReader;
import lombok.Data;

import java.io.File;

@Data
public class FormatOption {

    private Option option = new Option();

    @Data
    public static class Option {

        // タブをスペースにするか
        private boolean tabToSpace = true;
        // tabの幅
        private int tabWidth = 2;

        // 括弧をつけるかどうか
        private boolean addParenthesis = true;
        // 括弧の前後にスペースを入れるかどうか
        private boolean spaceAroundParenthesis = true;
        // 括弧の後に改行を入れるかどうか
        private boolean newLineAfterParenthesis = true;

        // 演算子の前後にスペースを入れるかどうか
        private boolean spaceAroundOperator = true;
    }

    // Fileより読み込む
    @JsonIgnore
    public FormatOption fromFile(File file) {
        return new TomlFormatOptionReader().fromFile(file);
    }

    // パスより読み込む
    @JsonIgnore
    public FormatOption fromPath(String path) {
        return new TomlFormatOptionReader().fromFile(new File(path));
    }
}

