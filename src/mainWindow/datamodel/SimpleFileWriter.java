package mainWindow.datamodel;

public class SimpleFileWriter extends AbstractFileWriter {

    public SimpleFileWriter(Writer writer) {
        super(writer);
    }

    @Override
    public byte[] decrypt(byte[] data) {
        return data;
    }
}
