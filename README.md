# antlr-test

ANTRL4を使ってみる

## 環境

Java 17

## パーサーの生成

パーサーを生成するときは以下のコマンドを実行する。

```
 gradle :app:generateGrammarSource
 gradle :app:buildDependents
 
```

`app/src/main/antlr4`に配置した文法ファイルから`java`で記述されたパーサーが生成され、`app/src/main/java`に配置される。

## パーサーの実行

テスト用のファイルをパースしてみる

```
gradle run --args="src/test/resources/calc1"
```
