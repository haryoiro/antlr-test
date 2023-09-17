package config;

import com.haryoiro.config.FormatOption;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigTest {

    @Test

    void test() {
        // Arrange
        FormatOption.Option option = new FormatOption.Option();
        option.setAddParenthesis(false);
        option.setSpaceAroundParenthesis(false);
        option.setNewLineAfterParenthesis(false);
        option.setSpaceAroundOperator(false);
        FormatOption expectOption = new FormatOption();
        expectOption.setOption(option);

        // Act
        FormatOption formatOption = new FormatOption().fromPath("src/test/resources/config/config.toml");

        // Assert
        assertThat(formatOption).isEqualTo(expectOption);
    }
}
