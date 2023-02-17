package infra;

import java.io.IOException;

public interface FileParser {
    void openFile(String filename) throws IOException;
    String readLine() throws IOException;
    void close() throws IOException;
}