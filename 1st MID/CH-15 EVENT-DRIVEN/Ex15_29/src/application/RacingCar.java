package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RacingCar extends Application {

    private double carX = 10; // Initial X position of the car
    private double carY = 200; // Y position of the car (fixed)
    private double speed = 2; // Car speed (pixels per frame)
    private boolean isPaused = false; // Pause flag

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // Create the car shape
        Rectangle carBody = new Rectangle(40, 20, Color.CYAN);
        carBody.setY(carY);

        // Create the wheels of the car
        Circle wheel1 = new Circle(5, Color.BLACK);
        wheel1.setCenterX(carX + 10);
        wheel1.setCenterY(carY + 20);
        Circle wheel2 = new Circle(5, Color.BLACK);
        wheel2.setCenterX(carX + 30);
        wheel2.setCenterY(carY + 20);

        // Button to pause/resume the animation
        Button pauseButton = new Button("Pause");
        pauseButton.setLayoutX(300);
        pauseButton.setLayoutY(350);
        pauseButton.setOnAction(e -> togglePause(pauseButton));

        // Add the car and wheels to the pane
        pane.getChildren().addAll(carBody, wheel1, wheel2, pauseButton);

        // Set up the key event listener for speed control
        Scene scene = new Scene(pane, 600, 400);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                speed += 1; // Increase speed
            } else if (event.getCode() == KeyCode.DOWN && speed > 1) {
                speed -= 1; // Decrease speed
            }
        });

        // Set up the animation timer to move the car
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isPaused) {
                    carX += speed; // Move the car horizontally

                    // If the car reaches the end, reset to the left
                    if (carX > 600) {
                        carX = -40; // Reset to left side of the screen
                    }

                    // Update the position of the car and wheels
                    carBody.setX(carX);
                    wheel1.setCenterX(carX + 10);
                    wheel2.setCenterX(carX + 30);
                }
            }
        };

        // Start the animation
        timer.start();

        // Set up the scene and show the stage
        scene.setOnMouseClicked(e -> {
            // Optionally, handle clicks to reset the car position
            if (e.getClickCount() == 2) {
                carX = 10; // Reset the car position on double-click
            }
        });

        primaryStage.setTitle("Racing Car");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to toggle pause and resume
    private void togglePause(Button pauseButton) {
        isPaused = !isPaused;
        pauseButton.setText(isPaused ? "Resume" : "Pause");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
