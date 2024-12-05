package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class L14_7 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a scene and place a button in the scene
        StackPane pane = new StackPane();

        Button btOK = new Button("OK");
        btOK.setStyle("-fx-border-color: blue;"); // Set button border color
        pane.getChildren().add(btOK);


        pane.setRotate(45); // Rotate the pane
        pane.setStyle("-fx-border-color: red; -fx-background-color: lightgray;"); // Set pane style

        Scene scene = new Scene(pane, 200, 250);
        primaryStage.setTitle("NodeStyleRotateDemo"); // Set the stage title
        primaryStage.setScene(scene);                // Place the scene in the stage
        primaryStage.show();                       // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}