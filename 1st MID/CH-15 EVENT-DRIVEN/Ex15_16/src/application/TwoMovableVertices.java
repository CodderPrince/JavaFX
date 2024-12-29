package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TwoMovableVertices extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Pane for the layout
        Pane pane = new Pane();

        // Create two circles
        Circle circle1 = new Circle(40, 40, 10);
        circle1.setFill(Color.LIGHTBLUE);
        circle1.setStroke(Color.BLUE);

        Circle circle2 = new Circle(120, 150, 10);
        circle2.setFill(Color.LIGHTGREEN);
        circle2.setStroke(Color.GREEN);

        // Create a line connecting the two circles
        Line line = new Line();
        line.setStroke(Color.BLACK);

        // Create a text to display the distance
        Text distanceText = new Text();
        updateDistance(line, circle1, circle2, distanceText);

        // Add drag functionality to the circles
        addDragFunctionality(circle1, circle2, line, distanceText);
        addDragFunctionality(circle2, circle1, line, distanceText);

        // Add all elements to the pane
        pane.getChildren().addAll(line, circle1, circle2, distanceText);

        // Create a scene with a light background color
        Scene scene = new Scene(pane, 400, 300, Color.LIGHTYELLOW);

        // Set up the stage
        primaryStage.setTitle("Two Movable Vertices");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add drag functionality to a circle
    private void addDragFunctionality(Circle circle, Circle otherCircle, Line line, Text distanceText) {
        circle.setOnMouseDragged((MouseEvent event) -> {
            // Update the circle's position
            circle.setCenterX(event.getX());
            circle.setCenterY(event.getY());

            // Update the line and distance text
            updateDistance(line, circle, otherCircle, distanceText);
        });
    }

    // Method to update the line and distance text
    private void updateDistance(Line line, Circle circle1, Circle circle2, Text distanceText) {
        // Update the line's start and end points
        line.setStartX(circle1.getCenterX());
        line.setStartY(circle1.getCenterY());
        line.setEndX(circle2.getCenterX());
        line.setEndY(circle2.getCenterY());

        // Calculate the distance between the two circles
        double distance = Math.sqrt(Math.pow(circle2.getCenterX() - circle1.getCenterX(), 2)
                + Math.pow(circle2.getCenterY() - circle1.getCenterY(), 2));

        // Update the distance text
        distanceText.setText(String.format("%.2f", distance));
        distanceText.setX((circle1.getCenterX() + circle2.getCenterX()) / 2);
        distanceText.setY((circle1.getCenterY() + circle2.getCenterY()) / 2 - 5); // Position above the line
    }

    public static void main(String[] args) {
        launch(args);
    }
}
