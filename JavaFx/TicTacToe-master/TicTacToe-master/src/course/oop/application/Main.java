package course.oop.application;

import course.oop.controller.Controller;
import course.oop.fileio.FileIO;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        //FileIO.init();
        Controller.initiate(primaryStage);
        //primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Tic Tac Toe: The Game");
        primaryStage.show();
    }
}
