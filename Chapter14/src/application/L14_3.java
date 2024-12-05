package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class L14_3 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a StackPane and place a button in it
        StackPane pane = new StackPane();
        pane.getChildren().add(new Button("OK"));


        // Create a scene with the pane as the root node
        Scene scene = new Scene(pane, 200, 50);  // Width: 200, Height: 50

        primaryStage.setTitle("Button in a pane"); // Set the stage title
        primaryStage.setScene(scene);              // Place the scene in the stage
        primaryStage.show();                     // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}