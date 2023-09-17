package com.haryoiro.calcformat.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haryoiro.calcformat.config.impl.TomlFormatOptionReader;

import java.io.File;

public class FormatOption {

    private Option option = new Option();

    public Option getOption() {
        return this.option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

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

        public boolean isTabToSpace() {
            return this.tabToSpace;
        }

        public int getTabWidth() {
            return this.tabWidth;
        }

        public boolean isAddParenthesis() {
            return this.addParenthesis;
        }

        public boolean isSpaceAroundParenthesis() {
            return this.spaceAroundParenthesis;
        }

        public boolean isNewLineAfterParenthesis() {
            return this.newLineAfterParenthesis;
        }

        public boolean isSpaceAroundOperator() {
            return this.spaceAroundOperator;
        }

        public void setTabToSpace(boolean tabToSpace) {
            this.tabToSpace = tabToSpace;
        }

        public void setTabWidth(int tabWidth) {
            this.tabWidth = tabWidth;
        }

        public void setAddParenthesis(boolean addParenthesis) {
            this.addParenthesis = addParenthesis;
        }

        public void setSpaceAroundParenthesis(boolean spaceAroundParenthesis) {
            this.spaceAroundParenthesis = spaceAroundParenthesis;
        }

        public void setNewLineAfterParenthesis(boolean newLineAfterParenthesis) {
            this.newLineAfterParenthesis = newLineAfterParenthesis;
        }

        public void setSpaceAroundOperator(boolean spaceAroundOperator) {
            this.spaceAroundOperator = spaceAroundOperator;
        }
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

