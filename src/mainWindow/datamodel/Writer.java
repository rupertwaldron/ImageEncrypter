package mainWindow.datamodel;

import java.io.IOException;

public interface Writer {
    void write(byte[] dataToFile) throws IOException;
    byte[] decrypt(byte[] data);
}
