package com.haryoiro.calcformat.config;

import lombok.RequiredArgsConstructor;

import java.util.List;

public enum FileType {
    TOML("toml"),

    JSON("json"),
    YAML("yaml", "yml"),
    UNKNOWN()
    ;

    private String[] extensions;

    FileType(String... extensions) {
        this.extensions = extensions;
    }

    public static FileType fromExtension(String extension) {
        for (FileType fileType : FileType.values()) {
            for (String ext : fileType.extensions) {
                if (ext.equals(extension)) {
                    return fileType;
                }
            }
        }
        return UNKNOWN;
    }
}
