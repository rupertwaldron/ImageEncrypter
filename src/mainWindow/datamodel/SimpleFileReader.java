package mainWindow.datamodel;

public class SimpleFileReader extends AbstractFileReader {

    public SimpleFileReader(Reader reader) {
        super(reader);
    }

    @Override
    public byte[] encrypt(byte[] data) {
        return data;
    }

}
