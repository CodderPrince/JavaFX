package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class P14_1 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        
        // Create the buttons
        Button button = new Button("Prince");
        Button button1 = new Button("Hi");

        // Create a VBox layout and add the buttons to it
        VBox root = new VBox(50);  // The "10" is the space between the buttons
        root.getChildren().addAll(button, button1);
        
        // Create a scene with the root node and dimensions
        Scene scene = new Scene(root, 200, 300);
        
        // Set the scene to the stage
        primaryStage.setScene(scene);
        
        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
