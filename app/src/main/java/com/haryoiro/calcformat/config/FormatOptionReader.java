package com.haryoiro.calcformat.config;

import java.io.File;

public interface FormatOptionReader {
    FormatOption fromFile(File file);

    FormatOption fromString(String string);

    default FormatOption getDefault() {
        return new FormatOption();
    };
}
