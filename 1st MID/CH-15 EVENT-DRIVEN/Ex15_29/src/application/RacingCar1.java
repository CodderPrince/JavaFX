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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RacingCar1 extends Application {

    private double carX = 50;
    private double carY = 150;
    private double speed = 2.0;
    private boolean isPaused = false;
    private boolean moveInCircle = false;
    private boolean moveInRectangle = false;
    private double angle = 0;
    private double centerX = 300, centerY = 200; // For circle movement
    private double circleRadius = 100; // Default circle radius
    private double rectWidth = 300, rectHeight = 200; // Default rectangle dimensions
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

        pauseResumeBtn.setOnAction(e -> isPaused = !isPaused);
        circleMoveBtn.setOnAction(e -> {
            moveInCircle = true;
            moveInRectangle = false;
            circleRadius += 50; // Increase circle radius by 50 units each click
        });
        rectMoveBtn.setOnAction(e -> {
            moveInCircle = false;
            moveInRectangle = true;
            rectWidth += 50; // Increase rectangle width by 50 units each click
            rectHeight += 30; // Increase rectangle height by 30 units each click
        });

        Scene scene = new Scene(root, 800, 600);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                speed += 0.5;
            } else if (e.getCode() == KeyCode.DOWN) {
                speed -= 0.5;
                if (speed < 0) speed = 0;
            }
        });

        primaryStage.setTitle("Racing Car Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawScene(GraphicsContext gc) {
        // Clear the canvas
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, 800, 600);

        // Draw the car
        gc.setFill(Color.RED);
        gc.fillRect(carX, carY, 100, 50); // Car body
        gc.setFill(Color.BLACK);
        gc.fillOval(carX + 10, carY + 40, 20, 20); // Front wheel
        gc.fillOval(carX + 70, carY + 40, 20, 20); // Back wheel

        // Draw the boy with glasses
        gc.setFill(Color.BEIGE);
        gc.fillOval(carX + 30, carY - 20, 40, 40); // Head
        gc.setFill(Color.BLUE);
        gc.fillRect(carX + 35, carY, 30, 20); // Body
        gc.setFill(Color.WHITE);
        gc.fillRect(carX + 35, carY - 10, 30, 10); // Glasses

        // Draw circle and rectangle outlines for visualization
        gc.setStroke(Color.GREEN);
        gc.strokeOval(centerX - circleRadius, centerY - circleRadius, circleRadius * 2, circleRadius * 2);
        gc.setStroke(Color.BLUE);
        gc.strokeRect(centerX, centerY, rectWidth, rectHeight);
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
