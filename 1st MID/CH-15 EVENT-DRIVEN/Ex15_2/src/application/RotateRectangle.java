package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RotateRectangle extends Application {
    private double angle = 0; // Track the rotation angle

    @Override
    public void start(Stage primaryStage) {
        // Create a BorderPane as the main layout
        BorderPane pane = new BorderPane();

        // Set background color
        pane.setStyle("-fx-background-color: #d3d3d3;"); // Light gray background

        // Create a Rectangle
        Rectangle rectangle = new Rectangle(200, 100, Color.LIGHTBLUE); // Light blue rectangle
        rectangle.setStroke(Color.BLACK); // Add a black border
        pane.setCenter(rectangle); // Center the rectangle

        // Create a Rotate button
        Button rotateButton = new Button("Rotate");

        // Handle button click to rotate the rectangle
        rotateButton.setOnAction(e -> {
            angle += 15; // Increase the angle by 15 degrees
            rectangle.setRotate(angle); // Apply the rotation
        });

        // Place the button at the bottom
        VBox buttonBox = new VBox(rotateButton);
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10;");
        pane.setBottom(buttonBox);

        // Create the scene and set the stage
        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Rotate Rectangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
