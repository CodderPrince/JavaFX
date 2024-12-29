package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BoundingRectangle extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Pane for the drawing area
        Pane pane = new Pane();

        // List to store all points (circles)
        ArrayList<Circle> points = new ArrayList<>();

        // Create a Rectangle for the bounding box
        Rectangle boundingRect = new Rectangle();
        boundingRect.setFill(Color.TRANSPARENT);
        boundingRect.setStroke(Color.BLUE);
        boundingRect.setStrokeWidth(2);
        pane.getChildren().add(boundingRect);

        // Add instructions
        Text instructions = new Text(10, 20, "INSTRUCTION\nAdd: Left Click\nRemove: Right Click");
        pane.getChildren().add(instructions);

        // Event handler for mouse clicks
        pane.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            if (event.getButton() == MouseButton.PRIMARY) {
                // Add a new point (circle) on left-click
                Circle point = new Circle(x, y, 10, getRandomColor());
                point.setStroke(Color.BLACK); // Add a border
                points.add(point);
                pane.getChildren().add(point);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                // Remove a point on right-click if close to the mouse pointer
                Circle toRemove = null;
                for (Circle point : points) {
                    if (point.contains(x, y)) {
                        toRemove = point;
                        break;
                    }
                }
                if (toRemove != null) {
                    points.remove(toRemove);
                    pane.getChildren().remove(toRemove);
                }
            }

            // Update the bounding rectangle
            updateBoundingRectangle(points, boundingRect);
        });

        // Create the Scene and display the stage
        Scene scene = new Scene(pane, 600, 400, Color.LIGHTGRAY);
        primaryStage.setTitle("Bounding Rectangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to update the bounding rectangle
    private void updateBoundingRectangle(ArrayList<Circle> points, Rectangle boundingRect) {
        if (points.isEmpty()) {
            boundingRect.setWidth(0);
            boundingRect.setHeight(0);
            return;
        }

        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for (Circle point : points) {
            minX = Math.min(minX, point.getCenterX());
            minY = Math.min(minY, point.getCenterY());
            maxX = Math.max(maxX, point.getCenterX());
            maxY = Math.max(maxY, point.getCenterY());
        }

        boundingRect.setX(minX);
        boundingRect.setY(minY);
        boundingRect.setWidth(maxX - minX);
        boundingRect.setHeight(maxY - minY);
    }

    // Method to generate a random color for points
    private Color getRandomColor() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
