package com.haryoiro.calcformat.cli;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.List;

@AllArgsConstructor
public class CliOptionParser {

    private String[] args;


    static HelpFormatter helpFormatter = new HelpFormatter();
    static String HELP_HEADER = "calc formatter";

    @Getter
    static Options options = new Options();
    static {
        options.addOption(Option.builder("f").longOpt("fix").desc("fix input files").build());
        options.addOption(Option.builder("c").longOpt("check").desc("check input files").build());
        options.addOption(
                Option.builder("o").longOpt("option").desc("option file").hasArg().build());
    }

    /**
     * コマンドライン引数を解析しオプションにマッピングする
     *
     * @return CliOptions
     */
    public CliOptions parse() {

        if (args.length < 1) {
            return null;
        }

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (Exception e) {
            return null;
        }

        return processOptions(cmd);
    }

    /**
     * コマンドライン引数を解析して、オプションを設定する
     *
     * @param cmd
     */
    private CliOptions processOptions(CommandLine cmd) {
        var attributes = new CliOptions();

        if (cmd.hasOption("fix")) {
            attributes.setFix(true);
        }

        if (cmd.hasOption("check")) {
            attributes.setCheck(true);
        }

        if (cmd.hasOption("option")) {
            attributes.setOptionFile(cmd.getOptionValue("option"));
        }

        String[] remainingArgs = cmd.getArgs();
        if (remainingArgs.length > 0) {
            attributes.setInputFiles(List.of(remainingArgs));
        }

        return attributes;
    }

    public static void printHelp() {
        helpFormatter.printHelp(HELP_HEADER, options);
    }

}
