package mainWindow.datamodel;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptFileReader extends AbstractFileReader {
    String password = "bob";

    public EncryptFileReader(Reader reader) {
        super(reader);
    }

    @Override
    public byte[] encrypt(byte[] data) {
        byte[] encryptedData = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            encryptedData = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedData;
    }
}
