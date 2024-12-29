package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PolygonMouseCheck extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Pane to hold the shapes and text
        Pane pane = new Pane();

        // Create a Polygon with the given points
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
            100.0, 50.0,  // Point 1
            160.0, 90.0,  // Point 2
            140.0, 150.0, // Point 3
            110.0, 110.0, // Point 4
            70.0, 130.0   // Point 5
        );

        // Add a colorful gradient fill
        LinearGradient gradient = new LinearGradient(
            0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.LIGHTBLUE),
            new Stop(1, Color.LIGHTGREEN)
        );
        polygon.setFill(gradient); // Gradient fill for the polygon
        polygon.setStroke(Color.BLACK); // Black border for better visibility

        // Create a Text node to display the message
        Text statusText = new Text(20, 20, ""); // Initially empty text

        // Add event listener for mouse movement
        pane.setOnMouseMoved((MouseEvent event) -> {
            if (polygon.contains(event.getX(), event.getY())) {
                statusText.setText("Mouse point is inside the polygon");
            } else {
                statusText.setText("Mouse point is outside the polygon");
            }
            // Update text position to follow the mouse
            statusText.setX(event.getX() + 10);
            statusText.setY(event.getY() - 10);
        });

        // Add the polygon and text to the pane
        pane.getChildren().addAll(polygon, statusText);

        // Create a Scene and set the stage
        Scene scene = new Scene(pane, 300, 200, Color.LIGHTGRAY); // Light gray background for better contrast
        primaryStage.setTitle("Polygon Mouse Check");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
