module com.haryoiro.calcformat {
    requires org.antlr.antlr4.runtime;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.toml;

    requires org.apache.logging.log4j;
    requires info.picocli;
    requires lombok;
    exports com.haryoiro.calcformat.app;
}