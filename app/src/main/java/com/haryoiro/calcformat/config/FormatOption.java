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
        private boolean addParenthesis = false;
        private boolean spaceAroundParenthesis = true;
        private boolean newLineAfterParenthesis = false;
        private boolean spaceAroundOperator = false;
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

