package config;

import com.haryoiro.calcformat.config.FileType;
import com.haryoiro.calcformat.config.FormatOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigTest {

    @Test
    @DisplayName("ファイルからコンフィグファイルを読み込める")
    void canReadTomlConfigFromFile() {
        // Act
        FormatOption formatOption = new FormatOption().fromPath("src/test/resources/config/config.toml");

        // Assert
        assertThat(formatOption).isNotNull();
    }

    @Test
    @DisplayName("文字列からコンフィグファイルを読み込める")
    void canReadTomlConfigFromString() {
        // Arrange
        String tomlString = """
                [option]
                tabToSpace = false
                tabWidth = 2
                addParenthesis = false
                spaceAroundParenthesis = false
                newLineAfterParenthesis = false
                spaceAroundOperator = false
                """;

        // Act
        FormatOption formatOption = new FormatOption().fromString(tomlString, FileType.TOML);

        // Assert
        assertThat(formatOption).isNotNull();
    }
}
