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

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 15;
    private static final int BALL_SIZE = 15;
    private static final int BRICK_WIDTH = 5;  // Updated brick width
    private static final int BRICK_HEIGHT = 5; // Updated brick height
    private static final int BRICK_SPACING = 3; // Spacing between bricks

    private double paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;
    private double ballX = WIDTH / 2;
    private double ballY = HEIGHT - 200;
    private double ballDX = 3;
    private double ballDY = -3;

    private boolean gameRunning = true;
    private final List<Brick> bricks = new ArrayList<>();
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Button restartButton = new Button("Restart");
        restartButton.setLayoutX(WIDTH / 2 - 40);
        restartButton.setLayoutY(HEIGHT / 2 + 50);
        restartButton.setVisible(false);

        Button backButton = new Button("Back");
        backButton.setLayoutX(WIDTH / 2 - 40);
        backButton.setLayoutY(HEIGHT / 2 + 100);
        backButton.setVisible(false);

        Pane root = new Pane(canvas, restartButton, backButton);
        Scene scene = new Scene(root);

        createCorridorBricks();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                moveLeft = true;
            } else if (e.getCode() == KeyCode.RIGHT) {
                moveRight = true;
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                moveLeft = false;
            } else if (e.getCode() == KeyCode.RIGHT) {
                moveRight = false;
            }
        });

        restartButton.setOnAction(e -> {
            gameRunning = true;
            paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;
            ballX = WIDTH / 2;
            ballY = HEIGHT - 200;
            ballDX = 3;
            ballDY = -3;
            bricks.clear();
            createCorridorBricks();
            restartButton.setVisible(false);
            backButton.setVisible(false);
            timer.start();
        });

        backButton.setOnAction(e -> primaryStage.close());

        primaryStage.setTitle("Enhanced Brick Breaker");
        primaryStage.setScene(scene);
        primaryStage.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render(gc);
                if (!gameRunning) {
                    restartButton.setVisible(true);
                    backButton.setVisible(true);
                    this.stop();
                }
            }
        };

        timer.start();
    }

    private void createCorridorBricks() {
        int patternCols = 8; // Number of columns in each grid
        int patternRows = 5; // Number of rows in each grid
        int gridCount = 10;  // Number of grids in a row
        double gridSpacing = 15; // Spacing between grids
        double xOffset = 50;     // Horizontal starting offset
        double yOffset = 50;     // Vertical starting offset

        for (int gridRow = 0; gridRow < 3; gridRow++) { // Create 2 rows of grids
            for (int gridColumn = 0; gridColumn < gridCount; gridColumn++) { // 10 grids in each row
                for (int i = 0; i < patternRows; i++) { // Rows in the grid
                    for (int j = 0; j < patternCols; j++) { // Columns in the grid
                        // Calculate position of each brick
                        double x = j * (BRICK_WIDTH + BRICK_SPACING) + xOffset + gridColumn * (patternCols * (BRICK_WIDTH + BRICK_SPACING) + gridSpacing);
                        double y = i * (BRICK_HEIGHT + BRICK_SPACING) + yOffset + gridRow * (patternRows * (BRICK_HEIGHT + BRICK_SPACING) + gridSpacing);
                        // Alternate colors as in the provided layout
                        Color color = (i + j) % 2 == 0 ? Color.ORANGE : Color.LIMEGREEN;
                        // Add the brick with updated dimensions and color
                        bricks.add(new Brick(x, y, color));
                    }
                }
            }
        }
    }

    private void update() {
        if (moveLeft) {
            paddleX = Math.max(0, paddleX - 6);
        }
        if (moveRight) {
            paddleX = Math.min(WIDTH - PADDLE_WIDTH, paddleX + 6);
        }

        ballX += ballDX;
        ballY += ballDY;

        // Bounce off walls
        if (ballX <= 0 || ballX >= WIDTH - BALL_SIZE) {
            ballDX *= -1.1; // Increase speed slightly on bounce
        }
        if (ballY <= 0) {
            ballDY *= -1.1; // Increase speed slightly on bounce
        }

        // Bounce off paddle
        if (ballY + BALL_SIZE >= HEIGHT - PADDLE_HEIGHT &&
                ballX + BALL_SIZE >= paddleX &&
                ballX <= paddleX + PADDLE_WIDTH) {
            ballDY *= -1.1; // Increase speed slightly on bounce
        }

        // Check for game over
        if (ballY > HEIGHT) {
            gameRunning = false;
        }

        // Check collision with bricks
        bricks.removeIf(brick -> {
            if (ballX + BALL_SIZE >= brick.x && ballX <= brick.x + BRICK_WIDTH &&
                    ballY + BALL_SIZE >= brick.y && ballY <= brick.y + BRICK_HEIGHT) {
                ballDY *= -1;
                return true;
            }
            return false;
        });

        // Win condition
        if (bricks.isEmpty()) {
            gameRunning = false;
        }
    }

    private void render(GraphicsContext gc) {
        // Draw solid background
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw paddle
        gc.setFill(Color.BLUE);
        gc.fillRect(paddleX, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Draw ball
        gc.setFill(Color.RED);
        gc.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

        // Draw bricks
        for (Brick brick : bricks) {
            gc.setFill(brick.color);
            gc.fillRect(brick.x, brick.y, BRICK_WIDTH, BRICK_HEIGHT);
        }

        // Draw game over or win message
        if (!gameRunning) {
            gc.setFill(Color.BLACK);
            gc.setFont(new Font(36));
            gc.fillText(bricks.isEmpty() ? "You Win!" : "Game Over", WIDTH / 2 - 100, HEIGHT / 2);
        }
    }

    private static class Brick {
        double x, y;
        Color color;

        Brick(double x, double y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
