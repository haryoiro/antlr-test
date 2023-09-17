package calcparser.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.File;

@Data
public class FormatOption {

    private Option option;

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

