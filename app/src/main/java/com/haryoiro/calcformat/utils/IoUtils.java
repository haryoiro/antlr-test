package com.haryoiro.calcformat.utils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class IoUtils {

    /**
     * ファイルを読み込む
     *
     * @param path
     * @param bufSize
     * @return
     */
    public static String readFile(Path path, int bufSize) {
        return readFile(path.toFile(), bufSize);
    }

    /**
     * バッファサイズを1024に固定しファイルを読み込む
     *
     * @param f
     * @return
     */
    public static String readFile(File f) {
        return readFile(f, 1024);
    }

    /**
     * ファイルを読み込む
     *
     * @param f
     * @param bufSize
     * @return
     */
    public static String readFile(File f, int bufSize) {
        try (ReadableByteChannel rbc = FileChannel.open(Paths.get(f.toURI()),
                StandardOpenOption.READ)) {
            ByteBuffer bb = ByteBuffer.allocate(bufSize);
            StringBuilder sb = new StringBuilder();
            while (rbc.read(bb) > -1) {
                bb.flip();  // バッファを読み取りモード
                CharBuffer cb = StandardCharsets.UTF_8.decode(bb);
                sb.append(cb);
                bb.clear();  // バッファをクリアして次の読み取りモードへ
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * パスをファイルに変換する
     *
     * @param path
     * @return
     */
    public static File pathToFile(String path) {
        return Paths.get(path).toFile();
    }

}
