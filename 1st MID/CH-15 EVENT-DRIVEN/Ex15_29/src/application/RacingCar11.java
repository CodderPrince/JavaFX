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

public class RacingCar11 extends Application {

    private double userCarX = 50, userCarY = 300;
    private double compCarX = 50, compCarY = 400;
    private double userSpeed = 1.0, compSpeed = 1.5;
    private boolean isRaceStarted = false;
    private boolean isRaceFinished = false;

    private final double FINISH_LINE = 700;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Button startBtn = new Button("Start Race");
        startBtn.setLayoutX(350);
        startBtn.setLayoutY(20);
        startBtn.setStyle("-fx-font-size: 14px; -fx-background-color: green; -fx-text-fill: white;");

        root.getChildren().addAll(canvas, startBtn);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isRaceStarted && !isRaceFinished) {
                    // Move the cars
                    userCarX += userSpeed;
                    compCarX += compSpeed;

                    // Random events for computer car speed adjustments
                    if (Math.random() < 0.01) compSpeed += 0.2;
                    if (Math.random() < 0.01) compSpeed = Math.max(0, compSpeed - 0.3);

                    // Check if either car has reached the finish line
                    if (userCarX >= FINISH_LINE || compCarX >= FINISH_LINE) {
                        isRaceFinished = true; // Mark race as finished
                        stop(); // Stop the AnimationTimer
                        showResult(gc); // Display the result
                        return;
                    }
                }

                drawScene(gc); // Draw the updated scene
            }
        };

        // Start button action
        startBtn.setOnAction(e -> {
            isRaceStarted = true;
            isRaceFinished = false;
            userCarX = 50;
            compCarX = 50;
            userSpeed = 1.0;
            compSpeed = 1.5;
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            timer.start(); // Restart the timer
        });

        // Key press events for controlling the user's car
        Scene scene = new Scene(root, 800, 600);
        scene.setOnKeyPressed(e -> {
            if (!isRaceStarted || isRaceFinished) return;

            switch (e.getCode()) {
                case UP -> userSpeed += 0.5;
                case DOWN -> userSpeed = Math.max(0, userSpeed - 0.5);
                case SPACE -> userSpeed = 0; // Brake
            }
        });

        primaryStage.setTitle("Racing Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawScene(GraphicsContext gc) {
        // Clear canvas
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, 800, 600);

        // Draw road
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 250, 800, 200);

        // Speed zones
        gc.setFill(Color.YELLOW);
        gc.fillRect(200, 250, 50, 200); // Increase speed
        gc.setFill(Color.RED);
        gc.fillRect(500, 250, 50, 200); // Decrease speed
        gc.setFill(Color.BLUE);
        gc.fillRect(350, 250, 50, 200); // Brake zone

        // Draw finish line
        gc.setFill(Color.WHITE);
        gc.fillRect(FINISH_LINE, 250, 10, 200);

        // Draw user's car
        gc.setFill(Color.BLUE);
        gc.fillRect(userCarX, userCarY, 50, 30);
        gc.setFill(Color.BLACK);
        gc.fillOval(userCarX + 5, userCarY + 25, 10, 10);
        gc.fillOval(userCarX + 35, userCarY + 25, 10, 10);

        // Draw computer's car
        gc.setFill(Color.RED);
        gc.fillRect(compCarX, compCarY, 50, 30);
        gc.setFill(Color.BLACK);
        gc.fillOval(compCarX + 5, compCarY + 25, 10, 10);
        gc.fillOval(compCarX + 35, compCarY + 25, 10, 10);
    }

    private void showResult(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 600); // Clear the canvas for result display

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 600); // Background for result

        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font("Arial", 36));

        if (userCarX >= FINISH_LINE && userCarX > compCarX) {
            gc.fillText("You Win!", 350, 300);
        } else if (compCarX >= FINISH_LINE && compCarX > userCarX) {
            gc.fillText("Computer Wins!", 300, 300);
        } else {
            gc.fillText("It's a Draw!", 350, 300);
        }
    }
}
