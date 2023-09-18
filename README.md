# calcFormatterAntlr4Sample

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

calcFormatterAntlrSampleは、基本的な四則演算のフォーマットを行うJavaプロジェクトです。ANTLR4を使用して構文解析を行い、設定に応じて数式を適切にフォーマットします。
ANTLR4を使用したフォーマッタのテンプレートとしても使用できます。

## Development

### ビルド

```shell
$ gradle build
```

### 実行

```shell
$ gradle run --args="src/test/resources/calc1"
```

### パーサーの生成

```shell
gradle :app:generateGrammarSource
gradle :app:buildDependents
```

## プロジェクト構成

| パス                                 | 説明                       |
|:-----------------------------------|:-------------------------|
| main/antlr/Calc.g4                 | ANTLR4用の文法ファイル           |
| main/java/com/haryoiro/calcformat/ | メインのJavaパッケージ            |
| .../antlr/                         | ANTLR4によって生成されるJavaファイル群 |
| .../app/App.java                   | アプリケーションのエントリーポイント       |
| .../config/                        | フォーマッタの設定                |
| .../formatting/                    | フォーマッタ本体                 |
| main/resources/tinylog.properties  | ロギング設定        |
| test/java/                         | ユニットテスト関連                |
| test/resources/                    | テストで使用するリソース             |


## License

このプロジェクトはApache License 2.0に基づいています。
