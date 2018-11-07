package mainWindow.datamodel;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class RevealMessage {
    private static RevealMessage ourInstance = new RevealMessage();
    private byte[] message;
    private byte[] image;

    public static RevealMessage getInstance() {
        return ourInstance;
    }

    private RevealMessage() {
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

    public void reveal() throws Exception {
        boolean foundBackStop = false;
        byte[] input = Arrays.copyOf(image, image.length);
        byte[] backStop = "ruppy_rup".getBytes();
        int messageStart = 0;

        for (int i = 0; i < image.length - backStop.length; i++) {
            byte[] buffer = Arrays.copyOfRange(input, i, i + backStop.length);
            if (Arrays.equals(buffer, backStop)) {
                System.out.println("Start of message is " + new String(buffer));
                messageStart = i + backStop.length;
                foundBackStop = true;
            }
        }
        if (foundBackStop) {
            message = Arrays.copyOfRange(input, messageStart, image.length);
            //System.out.println("Reveal message is: " + new String(message));
        } else {
            throw new Exception("Can't find backstop");
        }


    }

    public void decrypt(String password) throws Exception {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            //System.out.println("Message going into cipher = " + new String(message) + password);
            message = cipher.doFinal(message);
        } catch (Exception e) {
            throw new Exception("Can't decrypt message");
        }
    }

}
