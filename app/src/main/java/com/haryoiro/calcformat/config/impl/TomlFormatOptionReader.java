package com.haryoiro.calcformat.config.impl;

import com.haryoiro.calcformat.utils.IoUtils;
import com.haryoiro.calcformat.config.FormatOption;
import com.haryoiro.calcformat.config.FormatOptionReader;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class TomlFormatOptionReader implements FormatOptionReader {

    final static TomlMapper tomlMapper = new TomlMapper();

    @Override
    public @Nullable FormatOption fromFile(@NotNull File file) {
        String target = IoUtils.readFile(file, 1024);

        if (target == null) {
            return null;
        }

        return fromString(target);
    }

    @Override
    public @Nullable FormatOption fromString(@NotNull String target) {
        FormatOption formatOption;

        try {
            formatOption = tomlMapper.readValue(target, FormatOption.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return formatOption;
    }
}
