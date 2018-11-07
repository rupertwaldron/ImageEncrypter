package mainWindow.datamodel;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DecryptFileWriter extends AbstractFileWriter {
    String password = "bob";

    public DecryptFileWriter(Writer writer) {
        super(writer);
    }

    @Override
    public byte[] decrypt(byte[] data) {
        byte[] decryptedData = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            decryptedData = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedData;
    }
}
