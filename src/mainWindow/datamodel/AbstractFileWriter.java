package mainWindow.datamodel;;

import java.io.IOException;

abstract public class AbstractFileWriter implements Writer {
    protected Writer writer;

    public AbstractFileWriter(Writer writer) {
        this.writer = writer;
    }

    public void write(byte[] dataToFile) throws IOException {
        byte[] data = decrypt(dataToFile);
        writer.write(data);
    }
}
