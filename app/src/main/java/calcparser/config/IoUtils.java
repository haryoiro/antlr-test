package calcparser.config;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class IoUtils {

    public static String readFile(File f, int bufSize) {
        try (ReadableByteChannel rbc = FileChannel.open(Paths.get(f.toURI()),
                StandardOpenOption.READ)) {
            char[] ca = new char[bufSize];
            ByteBuffer bb = ByteBuffer.allocate(bufSize);
            StringBuilder sb = new StringBuilder();
            while (rbc.read(bb) > -1) {
                CharBuffer cb = bb.asCharBuffer();
                cb.flip();
                cb.get(ca);
                sb.append(ca);
                cb.clear();
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
