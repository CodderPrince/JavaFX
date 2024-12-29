package application;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class BMF28 extends Application {

    private static final int BALL_RADIUS = 10;
    private static final int PINS = 7;
    private int ballCount = 10;
    private ArrayList<Circle> pegs; // Store pegs for reuse
    private Rectangle pot; // The pot shape
    private int collectedBalls = 0; // Number of balls collected in the pot
    private ArrayList<Circle> collectedBallList = new ArrayList<>(); // Balls inside the pot

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, peachpuff, ivory);");

        VBox controlBox = new VBox(10);
        controlBox.setLayoutX(20);
        controlBox.setLayoutY(20);

        Label timerLabel = new Label("Time Left: 30s");
        timerLabel.setStyle("-fx-text-fill: red;" +
                "-fx-font-size: 24px;" +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold;");
        timerLabel.setLayoutX(350);
        timerLabel.setLayoutY(20);
        pane.getChildren().add(timerLabel);

        Label resultLabel = new Label("Balls Collected: 0");
        resultLabel.setStyle("-fx-text-fill: blue;" +
                "-fx-font-size: 20px;" +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold;");
        resultLabel.setLayoutX(350);
        resultLabel.setLayoutY(60);
        pane.getChildren().add(resultLabel);

        ComboBox<String> durationDropdown = new ComboBox<>();
        durationDropdown.getItems().addAll("30 Seconds", "1 Minute", "1.3 Minutes", "2 Minutes");
        durationDropdown.setValue("30 Seconds");
        durationDropdown.setStyle("-fx-background-color: lightblue;" +
                "-fx-text-fill: black;" +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;");
        durationDropdown.setPrefWidth(222);

        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-background-color: darkgreen; " +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-size: 18px; " +
                "-fx-font-weight: bold;");

        controlBox.getChildren().addAll(new Label("Galton Game | Developed by PRINCE"),
                new Label("Select Play Duration:"), durationDropdown, startButton);

        pegs = drawMachine(pane);

        // Create the pot at the bottom
        pot = createPot(400, 720, 150, 50);
        pane.getChildren().add(pot);

        startButton.setOnAction(e -> {
            pane.getChildren().removeIf(node -> node instanceof Circle && !pegs.contains(node));
            collectedBalls = 0;
            collectedBallList.clear();
            resultLabel.setText("Balls Collected: 0");

            // Get duration from dropdown and calculate total time in seconds
            String selectedDuration = durationDropdown.getValue();
            int totalTimeInSeconds = switch (selectedDuration) {
                case "30 Seconds" -> 30;
                case "1 Minute" -> 60;
                case "1.3 Minutes" -> 78;
                case "2 Minutes" -> 120;
                default -> 30;
            };

            startCountdown(timerLabel, totalTimeInSeconds);
            dropBallsSequentially(pane, totalTimeInSeconds, resultLabel);
        });

        pane.getChildren().add(controlBox);

        Scene scene = new Scene(pane, 900, 850);

        // Add KeyEvent handler for moving the pot
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> movePot(-10, 0);
                case RIGHT -> movePot(10, 0);
                case UP -> movePot(0, -10);
                case DOWN -> movePot(0, 10);
            }
        });

        primaryStage.setTitle("Bean Machine with Pot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ArrayList<Circle> drawMachine(Pane pane) {
        ArrayList<Circle> pegList = new ArrayList<>();
        int paneCenterX = 450;

        for (int i = 0; i < PINS; i++) {
            for (int j = 0; j <= i; j++) {
                double x = paneCenterX - i * 20 + j * 40;
                double y = 150 + i * 50;
                Circle pin = new Circle(x, y, 7, Color.BLACK);
                pegList.add(pin);
                pane.getChildren().add(pin);
            }
        }

        return pegList;
    }

    private Rectangle createPot(double x, double y, double width, double height) {
        Rectangle pot = new Rectangle(x - width / 2, y - height / 2, width, height);
        pot.setFill(Color.DARKCYAN); // Change color if needed
        pot.setStroke(Color.BLACK);
        pot.setStrokeWidth(3);
        return pot;
    }

    private void dropBallsSequentially(Pane pane, int totalTimeInSeconds, Label resultLabel) {
        Random random = new Random();
        double delayPerBall = totalTimeInSeconds / (double) ballCount;

        for (int i = 0; i < ballCount; i++) {
            final int ballIndex = i;

            PauseTransition delay = new PauseTransition(Duration.seconds(i * delayPerBall));
            delay.setOnFinished(event -> {
                Circle ball = new Circle(450, 100, BALL_RADIUS);
                ball.setFill(randomColor());
                ball.setId("ball" + ballIndex);
                pane.getChildren().add(ball);

                Path path = new Path();
                path.getElements().add(new MoveTo(ball.getCenterX(), ball.getCenterY()));

                double currentX = ball.getCenterX();
                double currentY = ball.getCenterY();

                for (Circle peg : pegs) {
                    double pegX = peg.getCenterX();
                    double pegY = peg.getCenterY();

                    double bounceDirection = random.nextBoolean() ? -30 : 30;
                    double newX = currentX + bounceDirection;

                    path.getElements().add(new QuadCurveTo(pegX, pegY, newX, pegY + 20));

                    currentX = newX;
                    currentY = pegY + 20;
                }

                PathTransition pathTransition = new PathTransition(Duration.seconds(5), path, ball);
                pathTransition.setCycleCount(1);
                pathTransition.setOnFinished(ev -> {
                    if (isBallInPot(ball)) {
                        collectedBalls++;
                        resultLabel.setText("Balls Collected: " + collectedBalls);
                        collectedBallList.add(ball); // Add ball to the pot
                    }
                });

                pathTransition.play();
            });

            delay.play();
        }
    }

    private boolean isBallInPot(Circle ball) {
        double ballX = ball.getTranslateX() + ball.getCenterX();
        double ballY = ball.getTranslateY() + ball.getCenterY();

        return ballX >= pot.getX() && ballX <= pot.getX() + pot.getWidth() &&
               ballY >= pot.getY() && ballY <= pot.getY() + pot.getHeight();
    }

    private void startCountdown(Label timerLabel, int totalTimeInSeconds) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(totalTimeInSeconds);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            int currentTime = Integer.parseInt(timerLabel.getText().replaceAll("\\D", ""));
            currentTime--;
            timerLabel.setText("Time Left: " + currentTime + "s");

            if (currentTime <= 0) {
                timeline.stop();
                timerLabel.setText("Time's Up!");
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void movePot(double dx, double dy) {
        pot.setX(pot.getX() + dx);
        pot.setY(pot.getY() + dy);

        // Move all collected balls with the pot
        for (Circle ball : collectedBallList) {
            ball.setTranslateX(ball.getTranslateX() + dx);
            ball.setTranslateY(ball.getTranslateY() + dy);
        }
    }

    private Color randomColor() {
        Random random = new Random();
        double red = random.nextDouble();
        double green = random.nextDouble();
        double blue = random.nextDouble();
        double brightness = (red + green + blue) / 3;

        if (brightness > 0.6) {
            red *= 0.5;
            green *= 0.5;
            blue *= 0.5;
        }

        return Color.color(red, green, blue);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
