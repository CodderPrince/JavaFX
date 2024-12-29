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
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class RacingCar9 extends Application {

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
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, 800, 600);

        // Draw car body
        gc.setFill(Color.PURPLE);
        gc.fillRoundRect(carX, carY + 20, 200, 60, 30, 30); // Main body

        gc.setFill(Color.YELLOW);
        gc.fillRect(carX + 40, carY + 40, 120, 20); // Racing stripe

        // Front nose
        gc.setFill(Color.ORANGE);
        gc.fillArc(carX - 20, carY + 30, 40, 40, 90, 180, ArcType.ROUND);

        // Cockpit
        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(carX + 80, carY, 40, 40);

        // Driver's head
        gc.setFill(Color.BEIGE);
        gc.fillOval(carX + 85, carY + 5, 30, 30); // Head

        // Driver's helmet
        gc.setFill(Color.GRAY);
        gc.fillArc(carX + 85, carY + 5, 30, 30, 0, 180, ArcType.ROUND);
        gc.setFill(Color.PURPLE);
        gc.fillRect(carX + 85, carY + 15, 30, 5); // Helmet stripe

        // Driver's arms
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(carX + 85, carY + 25, 10, 10); // Left arm
        gc.fillRect(carX + 105, carY + 25, 10, 10); // Right arm

        // Steering wheel
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeOval(carX + 90, carY + 20, 20, 20);

        // Rear spoiler
        gc.setFill(Color.PURPLE);
        gc.fillRect(carX + 150, carY + 10, 40, 10); // Horizontal part
        gc.fillRect(carX + 170, carY + 10, 10, 30); // Vertical support

        // Wheels
        gc.setFill(Color.BLACK);
        gc.fillOval(carX + 20, carY + 60, 40, 40); // Front wheel
        gc.fillOval(carX + 140, carY + 60, 40, 40); // Back wheel

        // Wheel rims
        gc.setFill(Color.SILVER);
        gc.fillOval(carX + 30, carY + 70, 20, 20); // Front rim
        gc.fillOval(carX + 150, carY + 70, 20, 20); // Back rim

        // Ground shadow
        gc.setFill(Color.DARKGRAY);
        gc.fillOval(carX + 10, carY + 95, 180, 10);
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
