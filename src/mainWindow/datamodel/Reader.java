package mainWindow.datamodel;

public interface Reader {
    byte[] read();
    byte[] encrypt(byte[] data);
}
