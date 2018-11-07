package mainWindow.datamodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputWriter implements Writer {
    private Path path;

    public InputWriter(Path path) {
        this.path = path;
    }

    @Override
    public void write(byte[] dataToFile) throws IOException {
        try {
            long start = System.currentTimeMillis();
            Files.write(path, dataToFile);
            long end = System.currentTimeMillis();
            System.out.println("File written in: " + (end - start) + " ms");
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            throw new IOException("Error writing to file");
        }
    }

    @Override
    public byte[] decrypt(byte[] data) {
        return data;
    }
}
