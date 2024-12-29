package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RacingCar7 extends Application {

    private double carX = 50;
    private double carY = 150;
    private double speed = 2.0;
    private boolean isPaused = false;
    private boolean moveInCircle = false;
    private boolean moveInRectangle = false;
    private double angle = 0;
    private double centerX = 300, centerY = 200; // For circle movement
    private double circleRadius = 150; // Adjustable circle radius
    private double rectWidth = 300, rectHeight = 200; // Adjustable rectangle dimensions
    private String currentDirection = "RIGHT"; // Direction for rectangle movement

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Button pauseResumeBtn = new Button("Pause/Resume");
        pauseResumeBtn.setLayoutX(20);
        pauseResumeBtn.setLayoutY(20);
        pauseResumeBtn.setStyle("-fx-font-size: 14px; -fx-background-color: orange; -fx-text-fill: white;");

        Button circleMoveBtn = new Button("Circle Move");
        circleMoveBtn.setLayoutX(140);
        circleMoveBtn.setLayoutY(20);
        circleMoveBtn.setStyle("-fx-font-size: 14px; -fx-background-color: green; -fx-text-fill: white;");

        Button rectMoveBtn = new Button("Rectangle Move");
        rectMoveBtn.setLayoutX(260);
        rectMoveBtn.setLayoutY(20);
        rectMoveBtn.setStyle("-fx-font-size: 14px; -fx-background-color: blue; -fx-text-fill: white;");

        root.getChildren().addAll(canvas, pauseResumeBtn, circleMoveBtn, rectMoveBtn);

        // Initialize the AnimationTimer
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isPaused) {
                    drawScene(gc);
                    if (moveInCircle) {
                        carX = centerX + circleRadius * Math.cos(angle);
                        carY = centerY + circleRadius * Math.sin(angle);
                        angle += speed * 0.01; // Speed affects circle rotation
                    } else if (moveInRectangle) {
                        moveInRectanglePath();
                    } else {
                        carX += speed;
                        if (carX > canvas.getWidth()) carX = -100; // Reset car position
                    }
                }
            }
        };
        timer.start();

        // Initialize the Scene
        Scene scene = new Scene(root, 800, 600);

        // Pause/Resume Button
        pauseResumeBtn.setOnAction(e -> {
            isPaused = !isPaused;
            root.requestFocus(); // Refocus on the root Pane
        });

        // Circle Move Button
        circleMoveBtn.setOnAction(e -> {
            moveInCircle = true;
            moveInRectangle = false;
            root.requestFocus(); // Refocus on the root Pane
        });

        // Rectangle Move Button
        rectMoveBtn.setOnAction(e -> {
            moveInCircle = false;
            moveInRectangle = true;
            root.requestFocus(); // Refocus on the root Pane
        });

        // Key press events for controlling the car
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                speed += 0.5;
            } else if (e.getCode() == KeyCode.DOWN) {
                speed -= 0.5;
                if (speed < 0) speed = 0;
            } else if (e.getCode() == KeyCode.LEFT) {
                circleRadius += 10; // Increase circle radius
            } else if (e.getCode() == KeyCode.RIGHT) {
                circleRadius = Math.max(10, circleRadius - 10); // Decrease circle radius
            } else if (e.getCode() == KeyCode.W) {
                rectHeight += 10; // Increase rectangle height
            } else if (e.getCode() == KeyCode.S) {
                rectHeight = Math.max(50, rectHeight - 10); // Decrease rectangle height
            } else if (e.getCode() == KeyCode.A) {
                rectWidth += 10; // Increase rectangle width
            } else if (e.getCode() == KeyCode.D) {
                rectWidth = Math.max(50, rectWidth - 10); // Decrease rectangle width
            }
        });

        // Mouse click event to request focus
        scene.setOnMouseClicked(e -> root.requestFocus());

        primaryStage.setTitle("Racing Car Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set focus initially to ensure key events are captured
        root.requestFocus();
    }

    private void drawScene(GraphicsContext gc) {
        // Clear the canvas
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, 800, 600);

        // Racing track
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 500, 800, 100);
        gc.setFill(Color.WHITE);
        for (int i = 0; i < 800; i += 80) {
            gc.fillRect(i, 550, 40, 5); // Track stripes
        }

        // Car body (main chassis)
        gc.setFill(Color.CRIMSON);
        gc.fillPolygon(
            new double[]{carX, carX + 140, carX + 120, carX + 20},
            new double[]{carY + 40, carY + 40, carY + 30, carY + 30},
            4
        );

        // Front wing
        gc.setFill(Color.DARKRED);
        gc.fillPolygon(
            new double[]{carX, carX + 20, carX + 40, carX + 20},
            new double[]{carY + 50, carY + 50, carY + 60, carY + 60},
            4
        );

        // Rear wing
        gc.setFill(Color.DARKRED);
        gc.fillPolygon(
            new double[]{carX + 100, carX + 140, carX + 130, carX + 90},
            new double[]{carY + 20, carY + 20, carY + 10, carY + 10},
            4
        );

        // Driver cockpit
        gc.setFill(Color.BLACK);
        gc.fillOval(carX + 50, carY + 20, 40, 20);

        // Driver
        gc.setFill(Color.BEIGE); // Driver's head
        gc.fillOval(carX + 65, carY + 15, 20, 20);
        gc.setFill(Color.RED); // Driver's body
        gc.fillRect(carX + 65, carY + 30, 20, 15);
        gc.setStroke(Color.BLACK); // Driver's arms
        gc.strokeLine(carX + 65, carY + 35, carX + 55, carY + 45); // Left arm
        gc.strokeLine(carX + 85, carY + 35, carX + 95, carY + 45); // Right arm

        // Wheels
        gc.setFill(Color.BLACK);
        gc.fillOval(carX + 10, carY + 60, 30, 30); // Front left wheel
        gc.fillOval(carX + 100, carY + 60, 30, 30); // Front right wheel
        gc.fillOval(carX + 10, carY + 10, 30, 30); // Rear left wheel
        gc.fillOval(carX + 100, carY + 10, 30, 30); // Rear right wheel

        // Wheel rims
        gc.setFill(Color.SILVER);
        gc.fillOval(carX + 15, carY + 65, 20, 20); // Front left rim
        gc.fillOval(carX + 105, carY + 65, 20, 20); // Front right rim
        gc.fillOval(carX + 15, carY + 15, 20, 20); // Rear left rim
        gc.fillOval(carX + 105, carY + 15, 20, 20); // Rear right rim

        // Racing number
        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font("Arial", 18));
        gc.fillText("88", carX + 60, carY + 35); // Racing number on the car body
    }





    private void moveInRectanglePath() {
        switch (currentDirection) {
            case "RIGHT":
                carX += speed;
                if (carX >= centerX + rectWidth) {
                    carX = centerX + rectWidth;
                    currentDirection = "DOWN";
                }
                break;
            case "DOWN":
                carY += speed;
                if (carY >= centerY + rectHeight) {
                    carY = centerY + rectHeight;
                    currentDirection = "LEFT";
                }
                break;
            case "LEFT":
                carX -= speed;
                if (carX <= centerX) {
                    carX = centerX;
                    currentDirection = "UP";
                }
                break;
            case "UP":
                carY -= speed;
                if (carY <= centerY) {
                    carY = centerY;
                    currentDirection = "RIGHT";
                }
                break;
        }
    }
}
