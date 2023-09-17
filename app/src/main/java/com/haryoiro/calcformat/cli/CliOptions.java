package com.haryoiro.calcformat.cli;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CliOptions {

    // フォーマットするファイル名をカンマ区切りで指定
    private List<String> inputFiles;
    // フォーマットを実行する
    private boolean fix = false;
    // フォーマットを実行せずに、フォーマットの必要性をチェックする
    private boolean check = false;
    // オプションファイルを指定する
    private String optionFile;
}
