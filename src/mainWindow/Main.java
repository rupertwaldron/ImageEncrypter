package mainWindow;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Message Hider");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        Group root = new Group();
//        Scene scene = new Scene(root, 551, 400);
//
//
//        scene.setOnDragOver(new EventHandler<DragEvent>() {
//            @Override
//            public void handle(DragEvent event) {
//                Dragboard db = event.getDragboard();
//                if (db.hasFiles()) {
//                    event.acceptTransferModes(TransferMode.COPY);
//                } else {
//                    event.consume();
//                }
//            }
//        });
//
//        // Dropping over surface
//        scene.setOnDragDropped(new EventHandler<DragEvent>() {
//            @Override
//            public void handle(DragEvent event) {
//                Dragboard db = event.getDragboard();
//                boolean success = false;
//                if (db.hasFiles()) {
//                    success = true;
//                    String filePath = null;
//                    for (File file:db.getFiles()) {
//                        filePath = file.getAbsolutePath();
//                        System.out.println(filePath);
//                    }
//                }
//                event.setDropCompleted(success);
//                event.consume();
//            }
//        });
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
}
