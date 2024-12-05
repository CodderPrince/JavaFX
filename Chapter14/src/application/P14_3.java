package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class P14_3 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        Button button = new Button("Ok");
        
        //create a stackpane
        StackPane pane = new StackPane();
        pane.getChildren().add(button);
        
        //create scene
        Scene scene = new Scene(pane,200,300);
        primaryStage.setScene(scene); 
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
