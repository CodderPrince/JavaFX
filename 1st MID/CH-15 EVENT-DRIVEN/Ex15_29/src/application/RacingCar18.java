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

public class RacingCar18 extends Application {

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

    // Define additional obstacles
    private final Obstacle[] speedUpZones = {
        new Obstacle(400, 500, Color.LIGHTGREEN, "speedUp"),
        new Obstacle(1800, 1900, Color.LIGHTGREEN, "speedUp"),
        new Obstacle(2200, 2300, Color.LIGHTGREEN, "speedUp") // New speed-up zone
    };

    private final Obstacle[] slowDownZones = {
        new Obstacle(1200, 1300, Color.ORANGE, "slowDown"),
        new Obstacle(2500, 2600, Color.ORANGE, "slowDown"),
        new Obstacle(1600, 1700, Color.ORANGE, "slowDown") // New slow-down zone
    };

    private final Obstacle[] brakeZones = {
        new Obstacle(2000, 2100, Color.RED, "brake"),
        new Obstacle(2700, 2800, Color.RED, "brake"),
        new Obstacle(1400, 1500, Color.RED, "brake") // New brake zone
    };

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

        // Draw speed-up zones
        for (Obstacle zone : speedUpZones) {
            gc.setFill(zone.color);
            gc.fillRect(zone.startX - cameraX, 600, zone.endX - zone.startX, 300);
        }

        // Draw slow-down zones
        for (Obstacle zone : slowDownZones) {
            gc.setFill(zone.color);
            gc.fillRect(zone.startX - cameraX, 600, zone.endX - zone.startX, 300);
        }

        // Draw brake zones
        for (Obstacle zone : brakeZones) {
            gc.setFill(zone.color);
            gc.fillRect(zone.startX - cameraX, 600, zone.endX - zone.startX, 300);
        }

        // Draw the user's car
        gc.setFill(Color.BLUE);
        gc.fillRect(userCarX - cameraX, userCarY, 50, 30);

        // Draw the computer's car
        gc.setFill(Color.RED);
        gc.fillRect(computerCarX - cameraX, computerCarY, 50, 30);

        // Draw the finish line
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
        for (Obstacle zone : speedUpZones) {
            if (userCarX >= zone.startX && userCarX <= zone.endX) {
                userSpeed += 0.1; // Speed up in green zones
            }
        }

        for (Obstacle zone : slowDownZones) {
            if (userCarX >= zone.startX && userCarX <= zone.endX) {
                userSpeed = Math.max(0.5, userSpeed - 0.1); // Slow down in orange zones
            }
        }

        for (Obstacle zone : brakeZones) {
            if (userCarX >= zone.startX && userCarX <= zone.endX) {
                userSpeed = Math.max(0, userSpeed - 0.2); // Brake in red zones
            }
        }
    }

    private double calculateComputerSpeedMultiplier() {
        for (Obstacle zone : speedUpZones) {
            if (computerCarX >= zone.startX && computerCarX <= zone.endX) {
                return 1.2; // Speed up in green zones
            }
        }

        for (Obstacle zone : slowDownZones) {
            if (computerCarX >= zone.startX && computerCarX <= zone.endX) {
                return 0.8; // Slow down in orange zones
            }
        }

        for (Obstacle zone : brakeZones) {
            if (computerCarX >= zone.startX && computerCarX <= zone.endX) {
                return 0.5; // Brake in red zones
            }
        }

        return 1.0; // Normal speed
    }

    private void checkForFinish(GraphicsContext gc) {
        if (userCarX >= FINISH_LINE_X || computerCarX >= FINISH_LINE_X) {
            isPaused = true;
            raceFinished = true;

            String winner;
            if (userCarX >= FINISH_LINE_X && computerCarX >= FINISH_LINE_X) {
                if (userCarX > computerCarX) {
                    winner = "User";
                } else if (computerCarX > userCarX) {
                    winner = "Computer";
                } else {
                    winner = "It's a Tie!";
                }
            } else if (userCarX >= FINISH_LINE_X) {
                winner = "User";
            } else {
                winner = "Computer";
            }

            // Display result
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font(36));
            gc.fillText("Race Over! Winner: " + winner, 800 - cameraX, 450);
        }
    }

    // Helper class to define obstacles
    private static class Obstacle {
        double startX;
        double endX;
        Color color;
        String type;

        Obstacle(double startX, double endX, Color color, String type) {
            this.startX = startX;
            this.endX = endX;
            this.color = color;
            this.type = type;
        }
    }
}
