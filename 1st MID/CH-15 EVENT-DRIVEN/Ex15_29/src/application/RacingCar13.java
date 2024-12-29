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

public class RacingCar13 extends Application {

    private double userCarX = 50, userCarY = 300;
    private double compCarX = 50, compCarY = 400;
    private double userSpeed = 2.0, compSpeed = 3.0;
    private boolean isRaceStarted = false;
    private boolean isRaceFinished = false;

    private final double MAX_USER_SPEED = 6.0; // Increased maximum speed
    private final double MIN_USER_SPEED = 0.5;
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
                    userCarX += userSpeed; // Updated user speed directly affects position
                    compCarX += compSpeed;

                    // Computer gets speed advantages
                    if (Math.random() < 0.05) compSpeed += 0.1;
                    if (Math.random() < 0.02) compSpeed = Math.max(2.0, compSpeed - 0.2);

                    // Check for speed zones
                    handleSpeedZones();

                    // Check if either car has reached the finish line
                    if (userCarX >= FINISH_LINE || compCarX >= FINISH_LINE) {
                        isRaceFinished = true;
                        stop();
                        showResult(gc);
                        return;
                    }
                }

                drawScene(gc);
            }
        };

        // Start button action
        startBtn.setOnAction(e -> {
            isRaceStarted = true;
            isRaceFinished = false;
            userCarX = 50;
            compCarX = 50;
            userSpeed = 2.0;
            compSpeed = 3.0;
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            timer.start();
        });

        // Key press events for controlling the user's car
        Scene scene = new Scene(root, 800, 600);
        scene.setOnKeyPressed(e -> {
            if (!isRaceStarted || isRaceFinished) return;

            if (e.getCode() == KeyCode.UP) {
                userSpeed = Math.min(MAX_USER_SPEED, userSpeed + 0.5); // Increase speed
            } else if (e.getCode() == KeyCode.DOWN) {
                userSpeed = Math.max(MIN_USER_SPEED, userSpeed - 0.5); // Decrease speed
            }
        });

        primaryStage.setTitle("Racing Car with Fixed Speed Logic");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSpeedZones() {
        // Speed zones logic
        if (userCarX >= 200 && userCarX <= 250) userSpeed = Math.min(MAX_USER_SPEED, userSpeed + 0.3); // Boost zone
        if (userCarX >= 500 && userCarX <= 550) userSpeed = Math.max(MIN_USER_SPEED, userSpeed - 0.3); // Slow zone
        if (userCarX >= 350 && userCarX <= 400) userSpeed = 0.5; // Brake zone

        if (compCarX >= 200 && compCarX <= 250) compSpeed += 0.5; // Boost zone
        if (compCarX >= 500 && compCarX <= 550) compSpeed = Math.max(2.0, compSpeed - 0.2); // Slow zone
        if (compCarX >= 350 && compCarX <= 400) compSpeed = Math.max(2.0, compSpeed - 0.5); // Reduced braking
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
        gc.clearRect(0, 0, 800, 600);

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 600);

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
