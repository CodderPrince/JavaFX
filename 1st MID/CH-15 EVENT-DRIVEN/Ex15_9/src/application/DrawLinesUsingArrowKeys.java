package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DrawLinesUsingArrowKeys extends Application {

    private double currentX; // Current X position of the line
    private double currentY; // Current Y position of the line
    private static final double STEP = 10; // Step size for each arrow key press

    @Override
    public void start(Stage primaryStage) {
        // Create a Pane to draw lines
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #f5f5dc;"); // Light beige background

        // Get the initial center of the pane
        currentX = 300;
        currentY = 200;

        // Create the scene
        Scene scene = new Scene(pane, 600, 400);

        // Request focus on the pane to capture key events
        pane.requestFocus();

        // Handle key events
        scene.setOnKeyPressed(event -> {
            double newX = currentX;
            double newY = currentY;

            // Determine the direction of movement based on the arrow key pressed
            switch (event.getCode()) {
                case UP -> newY -= STEP; // Move up
                case DOWN -> newY += STEP; // Move down
                case LEFT -> newX -= STEP; // Move left
                case RIGHT -> newX += STEP; // Move right;
            }

            // Draw a line from the current position to the new position
            Line line = new Line(currentX, currentY, newX, newY);
            line.setStroke(Color.color(Math.random(), Math.random(), Math.random())); // Random color for each line
            line.setStrokeWidth(2);
            pane.getChildren().add(line);

            // Update the current position
            currentX = newX;
            currentY = newY;
        });

        // Set up the stage
        primaryStage.setTitle("Draw Lines Using Arrow Keys");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
