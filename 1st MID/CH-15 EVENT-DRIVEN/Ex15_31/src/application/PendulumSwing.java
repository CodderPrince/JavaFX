package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class PendulumSwing extends Application {

    private double angle = 45; // Initial angle in degrees
    private double angleDelta = 2; // Change in angle per frame
    private double speed = 2; // Speed multiplier
    private boolean isPaused = false; // Pause flag

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // Create the pendulum pivot
        double pivotX = 300;
        double pivotY = 100;

        // Create the pendulum's string (line)
        Line pendulumString = new Line();
        pendulumString.setStroke(Color.BLACK);
        pendulumString.setStrokeWidth(5);

        // Create the pendulum's bob (circle)
        Circle pendulumBob = new Circle(15, Color.BLUE); // Blue bob

        // Add the pendulum to the pane
        pane.getChildren().addAll(pendulumString, pendulumBob);

        // Animation timer to swing the pendulum
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isPaused) {
                    // Calculate the pendulum's bob position
                    double x = pivotX + 150 * Math.sin(Math.toRadians(angle)); // Radius = 150
                    double y = pivotY + 150 * Math.cos(Math.toRadians(angle));

                    // Update pendulum's string and bob
                    pendulumString.setStartX(pivotX);
                    pendulumString.setStartY(pivotY);
                    pendulumString.setEndX(x);
                    pendulumString.setEndY(y);

                    pendulumBob.setCenterX(x);
                    pendulumBob.setCenterY(y);

                    // Update the angle
                    angle += angleDelta * speed;

                    // Reverse direction at limits
                    if (angle > 60 || angle < -60) {
                        angleDelta = -angleDelta;
                    }
                }
            }
        };

        // Start the animation
        timer.start();

        // Set up key controls
        Scene scene = new Scene(pane, 600, 400, Color.LIGHTGRAY); // Light gray background
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                speed += 0.5; // Increase speed
            } else if (event.getCode() == KeyCode.DOWN && speed > 0.5) {
                speed -= 0.5; // Decrease speed
            } else if (event.getCode() == KeyCode.S) {
                isPaused = true; // Stop animation
            } else if (event.getCode() == KeyCode.R) {
                isPaused = false; // Resume animation
            }
        });

        // Set up the stage
        primaryStage.setTitle("Pendulum Swing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
