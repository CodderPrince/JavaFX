package course.oop.application;

import course.oop.controller.Controller;
import course.oop.fileio.FileIO;
import javafx.application.Application;
import javafx.stage.Stage;

public class InitializeTable extends Application {
    @Override
    public void start(Stage primaryStage) {
        FileIO.init();
        System.exit(0);
    }
}
