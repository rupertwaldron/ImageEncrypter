package mainWindow;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.skin.ColorPickerSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import mainWindow.datamodel.ControllerData;
import mainWindow.datamodel.HideMessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {
    @FXML
    GridPane mainWindowGrid;

    @FXML
    ImageView inputImage;

    @FXML
    ImageView outputImage;

    @FXML
    ChoiceBox processChoice;

    @FXML
    PasswordField password;

    @FXML
    TextArea message;

    @FXML
    Label passwordLabel;

    @FXML
    Button startButton;

    private String inputImagePath;
    private String outputImagePath;
    private boolean imageLoaded = false;

    private State state;
    private State hideMessageState;
    private State encryptAndHideMessageState;
    private State findMessageState;
    private State decryptAndFindMessageState;


    // starts when GUI is loaded
    @FXML
    public void initialize() {
        processChoice.setItems(FXCollections.observableArrayList(
                "Hide Message",
                "Encrypt -> Hide Message",
                "Find Message",
                "Decrypt -> Find Message"));

        processChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                //System.out.println(processChoice.getItems().get((Integer) t1));
                setState(t1.intValue());
            }
        });
        passwordLabel.setVisible(false);
        password.setVisible(false);
    }

    public Controller() {
        this.hideMessageState = new HideMessageState();
        this.encryptAndHideMessageState = new EncryptAndHideMessageState();
        this.findMessageState = new FindMessageState();
        this.decryptAndFindMessageState = new DecryptAndFindMessageState();
        this.state = this.hideMessageState;
    }

    @FXML
    public void startPressed() {
        /* Get password and text depending on state */
        System.out.println("Start has been pressed");
        //state.process();
        if (imageLoaded) {
            ControllerData.getInstance().setInputPath(inputImagePath);
            ControllerData.getInstance().setPassword(password.getCharacters().toString());
            ControllerData.getInstance().setState(state);
            ControllerData.getInstance().setMessage(message.getText());
            ControllerData.getInstance().setIsImageLoaded(imageLoaded);
            state.process();
            startButton.setStyle("-fx-background-color: Lime");
            message.setText(ControllerData.getInstance().getMessage());
            password.clear();
            if (ControllerData.getInstance().getOutputPath() != null) {
                File outputFilePic = new File(ControllerData.getInstance().getOutputPath());
                try {
                    outputImage.setImage(new Image(new FileInputStream(outputFilePic)));
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to load output image: " + e.getMessage());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Input Image Not Loaded");
            alert.setContentText("Please drag an input image onto the green box");
            alert.showAndWait();
        }
    }

    @FXML
    public void imageDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
        //System.out.println("Enter loop");
    }

    @FXML
    public void imageDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            File newImage = db.getFiles().get(0);
            inputImagePath = newImage.getAbsolutePath();
            System.out.println(inputImagePath);
            try {
                inputImage.setImage(new Image(new FileInputStream(newImage)));
                imageLoaded = true;
            } catch (FileNotFoundException e) {
                System.out.println("Unable to load image: " + e.getMessage());
                imageLoaded = false;
            }

        }
        event.setDropCompleted(success);
        event.consume();
    }

    public void setState(int newState) {
        switch (newState) {
            case 0:
                state = hideMessageState;
                password.setVisible(false);
                passwordLabel.setVisible(false);
                break;
            case 1: state = encryptAndHideMessageState;
                password.setVisible(true);
                passwordLabel.setVisible(true);
                break;
            case 2: state = findMessageState;
                password.setVisible(false);
                passwordLabel.setVisible(false);
                break;
            case 3: state = decryptAndFindMessageState;
                password.setVisible(true);
                passwordLabel.setVisible(true);
        }
        ControllerData.getInstance().setOutputPath(null);
        startButton.setStyle("-fx-background-color: Red");
        outputImage.setImage(null);
        //System.out.println("State changed to: " + state.toString());
    }

}

