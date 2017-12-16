import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * TODO comments
 *
 * @author Willow
 * @date 12/16/17
 */
public class App {
    public String read() throws IOException {
        // test.property expect a string of 'jenkins-{machine label}'
        File file = new File("/tmp/test.property");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[Long.valueOf(file.length()).intValue()];
        fileInputStream.read(buffer);
        fileInputStream.close();
        return new String(buffer, StandardCharsets.UTF_8).trim();
    }
}
