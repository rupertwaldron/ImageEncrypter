package mainWindow.datamodel;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class HideMessage {
    private static HideMessage ourInstance = new HideMessage();
    private byte[] message;
    private byte[] image;

    public static HideMessage getInstance() {
        return ourInstance;
    }

    private HideMessage() {
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] hide() {

        byte[] output = Arrays.copyOf(image, image.length);

        int j = message.length - 1;
        for (int i = image.length - 1; i >= image.length - message.length; i--) {
            output[i] = message[j--];
        }

        byte[] backStop = "ruppy_rup".getBytes();

        j = backStop.length - 1;
        for (int i = 1; i <= backStop.length; i++) {
            output[image.length - message.length - i] = backStop[j--];
        }

        return output;
    }

    public void encrypt(String password) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            message = cipher.doFinal(message);
            //System.out.println("Hidden message is: " + new String(message));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
