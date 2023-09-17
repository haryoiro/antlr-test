package calcparser.optoins;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CmdAttributes {

    // フォーマットするファイル名をカンマ区切りで指定
    private List<String> inputFiles;
    // フォーマットを実行する
    private boolean fix = false;
    // フォーマットを実行せずに、フォーマットの必要性をチェックする
    private boolean check = false;
    // オプションファイルを指定する
    private String optionFile;
}
