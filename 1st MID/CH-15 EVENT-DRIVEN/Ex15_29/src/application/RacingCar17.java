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

public class RacingCar17 extends Application {

    private double userCarX = 100, userCarY = 750;
    private double computerCarX = 100, computerCarY = 650;
    private double userSpeed = 2.0;
    private double computerSpeed = 2.0;
    private boolean isPaused = true; // Game starts paused
    private boolean userBraking = false; // Space key for braking
    private double computerSpeedMultiplier = 1.0;
    private boolean raceFinished = false;
    private final double FINISH_LINE_X = 3000; // Extended finish line for a longer race
    private double cameraX = 0; // For dynamic scrolling

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(1600, 900); // Canvas size for visible area
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

        Scene scene = new Scene(root, 1600, 900); // Window size
        primaryStage.setFullScreen(false); // Not full screen but adjustable window size

        startButton.setOnAction(e -> {
            isPaused = false;
            userCarX = 100;
            computerCarX = 100;
            userSpeed = 2.0;
            computerSpeed = 2.0;
            raceFinished = false;
            cameraX = 0;
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
        gc.fillRect(0, 0, 1600, 900);

        // Adjust the camera to follow the cars
        double offsetX = Math.min(Math.max(userCarX - 800, 0), FINISH_LINE_X - 1600);
        cameraX = offsetX;

        // Draw the racing track
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(-cameraX, 700, 3200, 200);

        // Speed-up zones
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(400 - cameraX, 600, 100, 300); // Speed-up zone 1
        gc.fillRect(1800 - cameraX, 600, 100, 300); // Speed-up zone 2

        // Slow-down obstacles
        gc.setFill(Color.ORANGE);
        gc.fillRect(1200 - cameraX, 600, 100, 300); // Slow-down zone 1
        gc.fillRect(2500 - cameraX, 600, 100, 300); // Slow-down zone 2

        // Brake zones (obstacles)
        gc.setFill(Color.RED);
        gc.fillRect(2000 - cameraX, 600, 100, 300); // Brake zone 1
        gc.fillRect(2700 - cameraX, 600, 100, 300); // Brake zone 2

        // Draw the user's car
        gc.setFill(Color.BLUE);
        gc.fillRect(userCarX - cameraX, userCarY, 50, 30);

        // Draw the computer's car
        gc.setFill(Color.RED);
        gc.fillRect(computerCarX - cameraX, computerCarY, 50, 30);

        // Finish line
        gc.setFill(Color.WHITE);
        gc.fillRect(FINISH_LINE_X - cameraX, 600, 10, 300);

        // "FINISH" text
        gc.setFill(Color.YELLOW);
        gc.setFont(javafx.scene.text.Font.font(20));
        gc.fillText("FINISH", FINISH_LINE_X - cameraX + 20, 580);
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
        if ((userCarX >= 400 && userCarX <= 500) || (userCarX >= 1800 && userCarX <= 1900)) {
            userSpeed += 0.1; // Speed up in green zones
        } else if ((userCarX >= 1200 && userCarX <= 1300) || (userCarX >= 2500 && userCarX <= 2600)) {
            userSpeed = Math.max(0.5, userSpeed - 0.1); // Slow down in orange zones
        } else if ((userCarX >= 2000 && userCarX <= 2100) || (userCarX >= 2700 && userCarX <= 2800)) {
            userSpeed = Math.max(0, userSpeed - 0.2); // Brake in red zones
        }
    }

    private double calculateComputerSpeedMultiplier() {
        if ((computerCarX >= 400 && computerCarX <= 500) || (computerCarX >= 1800 && computerCarX <= 1900)) {
            return 1.2; // Speed up in green zones
        } else if ((computerCarX >= 1200 && computerCarX <= 1300) || (computerCarX >= 2500 && computerCarX <= 2600)) {
            return 0.8; // Slow down in orange zones
        } else if ((computerCarX >= 2000 && computerCarX <= 2100) || (computerCarX >= 2700 && computerCarX <= 2800)) {
            return 0.5; // Brake in red zones
        }
        return 1.0; // Normal speed
    }

    private void checkForFinish(GraphicsContext gc) {
        if (userCarX >= FINISH_LINE_X || computerCarX >= FINISH_LINE_X) {
            isPaused = true;
            raceFinished = true;

            String winner = userCarX >= FINISH_LINE_X ? "User" : "Computer";

            // Display result
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font(24));
            gc.fillText("Race Over! Winner: " + winner, 800, 450);
        }
    }
}
