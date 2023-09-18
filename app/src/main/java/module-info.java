module com.haryoiro.calcformat {
    requires org.antlr.antlr4.runtime;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.toml;

    requires org.tinylog.api;

    requires info.picocli;
    opens com.haryoiro.calcformat.app to info.picocli;

    exports com.haryoiro.calcformat.app;
}