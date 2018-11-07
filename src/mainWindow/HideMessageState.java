package mainWindow;

import mainWindow.datamodel.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HideMessageState implements State {
    String message;
    String inputFilePath;
    String outputFilePath;
    @Override
    public void process() {
        inputFilePath = ControllerData.getInstance().getInputPath();
        message = ControllerData.getInstance().getMessage();
        System.out.println("Input file path: " + inputFilePath);
        System.out.println("Message: " + message);
        String fileExt = inputFilePath.substring(inputFilePath.length() - 4);
        outputFilePath = inputFilePath.substring(0, inputFilePath.length() - 4) + "_rr"
                + fileExt;
        System.out.println(outputFilePath);
        ControllerData.getInstance().setOutputPath(outputFilePath);
        Path imageInPath = Paths.get(inputFilePath);
        Path imageOutPath = Paths.get(outputFilePath);
        System.out.println(imageInPath);
        Reader imageReader = new SimpleFileReader(new InputReader(imageInPath));
        byte[] image = imageReader.read();
        //System.out.println(new String(image));
        Writer writer = new SimpleFileWriter(new InputWriter(imageOutPath));
        System.out.println(imageOutPath);
        HideMessage.getInstance().setImage(image);
        HideMessage.getInstance().setMessage(message.getBytes());
        ControllerData.getInstance().setMessage("");

        try {
            writer.write(HideMessage.getInstance().hide());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
