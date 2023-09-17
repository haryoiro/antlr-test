# antlr-test

ANTRL4を使ってみる

## 環境

Java 17

## パーサーの生成

パーサーを生成するときは以下のコマンドを実行する。

```
 gradle :app:generateGrammarSource
```
`app/src/main/antlr4`に配置した文法ファイルから`java`で記述されたパーサーが生成され、`app/src/main/java`に配置される。
