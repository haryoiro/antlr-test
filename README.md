# calcFormatterAntlrSample

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

calcFormatterAntlrSampleは、基本的な四則演算のフォーマットを行うJavaプロジェクトです。ANTLR4を使用して構文解析を行い、設定に応じて数式を適切にフォーマットします。
ANTLR4を使用したフォーマッタのテンプレートとしても使用できます。

## 実行方法

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

| パス                                 | 説明                                 |
|:-----------------------------------|:-----------------------------------|
| main/antlr/Calc.g4                 | ANTLR4用の文法ファイル                     |
| main/java/com/haryoiro/calcformat/ | メインのJavaパッケージ                      |
| .../antlr/                         | ANTLRによって生成される各種Javaファイルが格納されています。 |
| .../app/App.java                   | アプリケーションのエントリーポイント                 |
| .../config/                        | フォーマットに関する設定に関するJavaファイルがあります      |
| .../formatting/                    | フォーマット関連のJavaファイルがあります。            |
| .../utils/IoUtils.java             | 入出力関連のユーティリティがあります。                |
| main/resources/log4j2.xml          | ロギング設定のXMLファイルです。                  |
| test/java/                         | ユニットテスト関連のJavaファイルがあります。           |
| test/resources/                    | テストで使用するリソースが格納されています。             |


## ライセンス

このプロジェクトはApache License 2.0に基づいています。
