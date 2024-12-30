package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    // Game dimensions
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // Paddle properties
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_Y = HEIGHT - 50;
    private double paddleX = WIDTH / 2.0 - PADDLE_WIDTH / 2.0;
    private double paddleSpeed = 5;

    // Ball properties
    private double ballX = WIDTH / 2.0;
    private double ballY = HEIGHT / 2.0;
    private double ballRadius = 10;
    private double ballSpeedX = 3;
    private double ballSpeedY = 3;

    // Bricks properties
    private static final int ROWS = 6;
    private static final int COLS = 10;
    private static final int BRICK_WIDTH = 70;
    private static final int BRICK_HEIGHT = 20;
    private boolean[][] bricks = new boolean[ROWS][COLS];

    @Override
    public void start(Stage primaryStage) {
        // Initialize bricks
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                bricks[i][j] = true;
            }
        }

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        Group root = new Group(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                paddleX = Math.max(0, paddleX - paddleSpeed);
            } else if (e.getCode() == KeyCode.RIGHT) {
                paddleX = Math.min(WIDTH - PADDLE_WIDTH, paddleX + paddleSpeed);
            }
        });

        primaryStage.setTitle("Brick Breaker");
        primaryStage.setScene(scene);
        primaryStage.show();
        timeline.play();
    }

    private void run(GraphicsContext gc) {
        // Clear screen
        gc.setFill(Color.DARKBLUE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw paddle
        gc.setFill(Color.WHITE);
        gc.fillRect(paddleX, PADDLE_Y, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Draw ball
        gc.setFill(Color.WHITE);
        gc.fillOval(ballX - ballRadius, ballY - ballRadius, ballRadius * 2, ballRadius * 2);

        // Draw bricks
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (bricks[i][j]) {
                    gc.setFill(i % 2 == 0 ? Color.ORANGE : Color.LIME);
                    gc.fillRect(j * (BRICK_WIDTH + 10) + 30, i * (BRICK_HEIGHT + 10) + 30, BRICK_WIDTH, BRICK_HEIGHT);
                }
            }
        }

        // Move ball
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Ball collision with walls
        if (ballX - ballRadius <= 0 || ballX + ballRadius >= WIDTH) {
            ballSpeedX *= -1;
        }
        if (ballY - ballRadius <= 0) {
            ballSpeedY *= -1;
        }

        // Ball collision with paddle
        if (ballY + ballRadius >= PADDLE_Y && ballX >= paddleX && ballX <= paddleX + PADDLE_WIDTH) {
            ballSpeedY *= -1;
        }

        // Ball collision with bricks
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (bricks[i][j]) {
                    double brickX = j * (BRICK_WIDTH + 10) + 30;
                    double brickY = i * (BRICK_HEIGHT + 10) + 30;

                    if (ballX + ballRadius >= brickX && ballX - ballRadius <= brickX + BRICK_WIDTH &&
                            ballY + ballRadius >= brickY && ballY - ballRadius <= brickY + BRICK_HEIGHT) {
                        bricks[i][j] = false;
                        ballSpeedY *= -1;
                    }
                }
            }
        }

        // Game over if ball goes below paddle
        if (ballY - ballRadius > HEIGHT) {
            gc.setFill(Color.RED);
            gc.fillText("Game Over", WIDTH / 2.0 - 50, HEIGHT / 2.0);
            ballSpeedX = ballSpeedY = 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
