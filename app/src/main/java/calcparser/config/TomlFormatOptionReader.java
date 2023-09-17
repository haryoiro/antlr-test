package calcparser.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;

public class TomlFormatOptionReader implements FormatOptionReader {

    final static TomlMapper tomlMapper = new TomlMapper();

    @Override
    public @Nullable FormatOption fromFile(File file) {
        String target = IoUtils.readFile(file, 1024);
        FormatOption formatOption;

        try {
            formatOption = tomlMapper.readValue(target, FormatOption.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return formatOption;
    }

    @Override
    public @Nullable FormatOption fromString(String string) {
        return null;
    }
}
