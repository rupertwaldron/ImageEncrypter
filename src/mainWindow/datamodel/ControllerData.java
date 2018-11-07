package mainWindow.datamodel;

import mainWindow.State;

public class ControllerData {
    private static ControllerData ourInstance = new ControllerData();
    private String inputPath;
    private String outputPath;
    private String password;
    private String message;
    private boolean isImageLoaded;
    private State state;

    public static ControllerData getInstance() {
        return ourInstance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    private ControllerData() {
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isImageLoaded() {
        return isImageLoaded;
    }

    public void setIsImageLoaded(boolean isImageLoaded) {
        this.isImageLoaded = isImageLoaded;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
