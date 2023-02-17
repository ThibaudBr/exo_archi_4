package infra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

public class FileParserImpl implements FileParser {
    private BufferedReader reader;

    @Override
    public void openFile(String filename) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
    }

    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
