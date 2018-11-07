package mainWindow;

import javafx.scene.control.Alert;
import mainWindow.datamodel.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DecryptAndFindMessageState implements State {
    String message;
    String inputFilePath;
    String password;
    @Override
    public void process() {
        inputFilePath = ControllerData.getInstance().getInputPath();
        password = ControllerData.getInstance().getPassword();
        Path imageInPath = Paths.get(inputFilePath);
        Reader imageReader = new SimpleFileReader(new InputReader(imageInPath));
        byte[] image = imageReader.read();
        RevealMessage.getInstance().setImage(image);
        try {
            RevealMessage.getInstance().reveal();
            RevealMessage.getInstance().decrypt(password);
            message = new String(RevealMessage.getInstance().getMessage());
            ControllerData.getInstance().setMessage(message);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Problem finding message");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
