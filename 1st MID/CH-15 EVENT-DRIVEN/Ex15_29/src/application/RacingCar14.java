package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RacingCar14 extends Application {

    private double userCarX = 100, userCarY = 450;
    private double computerCarX = 100, computerCarY = 350;
    private double userSpeed = 2.0;
    private double computerSpeed = 2.0;
    private boolean isPaused = true; // Game starts paused
    private boolean userBraking = false; // Space key for braking
    private double computerSpeedMultiplier = 1.0;
    private boolean raceFinished = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Button startButton = new Button("Start Race");
        startButton.setLayoutX(20);
        startButton.setLayoutY(20);
        startButton.setStyle("-fx-font-size: 14px; -fx-background-color: green; -fx-text-fill: white;");

        root.getChildren().addAll(canvas, startButton);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isPaused && !raceFinished) {
                    drawScene(gc);
                    updatePositions();
                    checkForFinish(gc);
                }
            }
        };

        timer.start();

        Scene scene = new Scene(root, 1400, 800);

        startButton.setOnAction(e -> {
            isPaused = false;
            userCarX = 100;
            computerCarX = 100;
            userSpeed = 2.0;
            computerSpeed = 2.0;
            raceFinished = false;
            root.requestFocus();
        });

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    userSpeed += 0.5;
                    break;
                case DOWN:
                    userSpeed = Math.max(0, userSpeed - 0.5);
                    break;
                case SPACE:
                    userBraking = true;
                    break;
                default:
                    break;
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == javafx.scene.input.KeyCode.SPACE) {
                userBraking = false;
            }
        });

        primaryStage.setTitle("Racing Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawScene(GraphicsContext gc) {
        // Clear the canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1400, 800);

        // Draw the racing track
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 500, 1000, 400);

        // Road speed modifiers
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(200, 300, 100, 200); // Speed-up zone
        gc.setFill(Color.ORANGE);
        gc.fillRect(400, 300, 100, 200); // Slow-down zone
        gc.setFill(Color.RED);
        gc.fillRect(600, 300, 100, 200); // Brake zone

        // Draw the user's car
        gc.setFill(Color.BLUE);
        gc.fillRect(userCarX, userCarY, 50, 30);

        // Draw the computer's car
        gc.setFill(Color.RED);
        gc.fillRect(computerCarX, computerCarY, 50, 30);

        // Finish line
        gc.setFill(Color.WHITE);
        gc.fillRect(750, 300, 10, 200);
    }

    private void updatePositions() {
        // Update user's car position
        if (userBraking) {
            userSpeed = Math.max(0, userSpeed - 0.2); // Slow down when braking
        }
        userCarX += userSpeed;

        // Update computer's car position (AI logic)
        computerSpeedMultiplier = calculateComputerSpeedMultiplier();
        computerSpeed += computerSpeedMultiplier * 0.05;
        computerCarX += computerSpeed;

        // Check for road effects on the user's car
        if (userCarX >= 200 && userCarX <= 300) {
            userSpeed += 0.1; // Speed up
        } else if (userCarX >= 400 && userCarX <= 500) {
            userSpeed = Math.max(0.5, userSpeed - 0.1); // Slow down
        } else if (userCarX >= 600 && userCarX <= 700) {
            userSpeed = Math.max(0, userSpeed - 0.2); // Brake zone
        }
    }

    private double calculateComputerSpeedMultiplier() {
        if (computerCarX >= 200 && computerCarX <= 300) {
            return 1.2; // Speed up
        } else if (computerCarX >= 400 && computerCarX <= 500) {
            return 0.8; // Slow down
        } else if (computerCarX >= 600 && computerCarX <= 700) {
            return 0.5; // Brake zone
        }
        return 1.0; // Normal speed
    }

    private void checkForFinish(GraphicsContext gc) {
        if (userCarX >= 750 || computerCarX >= 750) {
            isPaused = true;
            raceFinished = true;

            String winner = userCarX >= 750 ? "User" : "Computer";

            // Display result
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font(24));
            gc.fillText("Race Over! Winner: " + winner, 300, 200);
        }
    }
}
