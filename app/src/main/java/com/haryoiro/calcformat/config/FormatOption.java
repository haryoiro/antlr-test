package com.haryoiro.calcformat.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haryoiro.calcformat.config.impl.TomlFormatOptionReader;
import com.haryoiro.calcformat.utils.IoUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class FormatOption {

    @Getter
    @Setter
    private Option option = new Option();

    @Getter
    @Setter
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
    public FormatOption fromFile(File file) {
        return new TomlFormatOptionReader().fromFile(file);
    }

    // パスより読み込む
    public FormatOption fromPath(String path) {

        if (path == null) {
            throw new IllegalArgumentException("パスがnullです。");
        }

        String extension = IoUtils.getExtension(path);
        if (extension == null) {
            throw new IllegalArgumentException("ファイルの拡張子が不正です。");
        }

        FileType fileType = FileType.fromExtension(extension);
        return switch (fileType) {
            case TOML -> new TomlFormatOptionReader().fromFile(IoUtils.pathToFile(path));
            case JSON -> throw new UnsupportedOperationException("JSONは未実装です。");
            case YAML -> throw new UnsupportedOperationException("YAMLは未実装です。");
            case UNKNOWN -> throw new IllegalArgumentException("ファイルの拡張子が不正です。");
        };
    }

    public FormatOption fromString(String string, FileType fileType) {
        return switch (fileType) {
            case TOML -> new TomlFormatOptionReader().fromString(string);
            case JSON -> throw new UnsupportedOperationException("JSONは未実装です。");
            case YAML -> throw new UnsupportedOperationException("YAMLは未実装です。");
            case UNKNOWN -> throw new IllegalArgumentException("ファイルの拡張子が不正です。");
        };
    }
}

