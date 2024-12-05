package application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class L14_8 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane to hold the circle
        Pane pane = new StackPane(); // StackPane is likely better here for centering

        // Create a circle and set its properties
        Circle circle = new Circle();
        circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(new Color(0.5, 0.5, 0.5, 0.1));  // Semi-transparent gray

        // Add circle to the pane
        pane.getChildren().add(circle);


        // Create a label and set its properties (including font)
        Label label = new Label("JavaFX");
        label.setFont(Font.font("Times New Roman", 
                                FontWeight.BOLD, FontPosture.ITALIC, 20));


        // Add label to the pane
        pane.getChildren().add(label);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane); // Scene size will adjust to content in StackPane
        primaryStage.setTitle("FontDemo");  // Set the stage title
        primaryStage.setScene(scene);         // Place the scene in the stage
        primaryStage.show();                // Display the stage
    }


    public static void main(String[] args) {
        launch(args);
    }
}