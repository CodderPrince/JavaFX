package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class L14_17 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane
        Pane pane = new Pane();

        for (int i = 0; i < 16; i++) {
            // Create an ellipse and add it to pane
            Ellipse el = new Ellipse(150, 100, 100, 50); //centerX, centerY, radiusX, radiusY
            el.setStroke(Color.color(Math.random(), Math.random(), Math.random())); //Random stroke color
            el.setFill(Color.WHITE);  //Fill color is white
            el.setRotate(i * 180 / 16);  //Rotate ellipses by different angles.
            pane.getChildren().add(el);
        }



        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("ShowEllipse"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }

    public static void main(String[] args) {
        launch(args);
    }
}