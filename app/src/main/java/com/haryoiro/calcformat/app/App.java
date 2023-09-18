package com.haryoiro.calcformat.app;

import com.haryoiro.calcformat.config.FormatOption;
import com.haryoiro.calcformat.formatting.CalcFormatter;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import static com.haryoiro.calcformat.utils.IoUtils.pathToFile;

@Command(name = "calcformat", mixinStandardHelpOptions = true, version = "calcformat 1.0",
        description = "calc format tool")
public class App implements Runnable {

    // フォーマットするファイル名をカンマ区切りで指定
    @Parameters(index = "0..*", description = "input files")
    private List<String> inputFiles;

    // フォーマットを実行する
    @Option(names = { "-f", "--fix" }, description = "fix input files")
    private boolean fix = false;

    // フォーマットを実行せずに、dry-runする
    @Option(names = { "-d", "--dry" }, description = "check input files")
    private boolean check = false;

    // オプションファイルを指定する
    @Option(names = { "-o", "--option" }, description = "option file")
    private String optionFile;

    public static void main(String[] args) {
        int exitCode = new picocli.CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        // デフォルトのフォーマットオプションを設定
        FormatOption formatOption = new FormatOption();

        // オプションファイルが指定されている場合は、オプションファイルを読み込む
        if (optionFile != null) {
            formatOption = formatOption.fromPath(optionFile);
        }

        // 指定されたすべてのファイルに対してフォーマットをかける
        if (inputFiles != null) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

            try {
                for (String file : inputFiles) {
                    var res = CalcFormatter.format(pathToFile(file), formatOption);
                    writer.write(res);
                    writer.newLine();
                    writer.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
