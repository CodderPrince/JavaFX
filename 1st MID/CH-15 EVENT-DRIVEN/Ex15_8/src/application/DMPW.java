package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DMPW extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a pane to hold the text
        Pane pane = new Pane();
        Text text = new Text();
        text.setFill(Color.DARKGREEN); // Colorful text
        pane.getChildren().add(text);

        // Set mouse press and release events
        pane.setOnMousePressed(e -> {
            text.setX(e.getX());
            text.setY(e.getY());
            text.setText("(" + e.getX() + ", " + e.getY() + ")");
        });

        pane.setOnMouseReleased(e -> {
            text.setText(""); // Clear the text when mouse button is released
        });

        // Create a scene with a light blue background
        Scene scene = new Scene(pane, 400, 300, Color.LIGHTBLUE);

        // Set up the stage
        primaryStage.setTitle("Display Mouse Position While Pressed");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
