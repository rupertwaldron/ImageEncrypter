package mainWindow.datamodel;

import java.io.IOException;

abstract public class AbstractFileReader implements Reader {
    protected Reader reader;

    public AbstractFileReader(Reader reader) {
        this.reader = reader;
    }

    public byte[] read(){
        byte[] data = reader.read();
        return encrypt(data);
    }


}

