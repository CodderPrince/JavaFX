/************************************
 * Galton Game | Bean Machine Game
 * Language: JavaFx
 * Developed: Md. An Nahian Prince
 ************************************/

package application;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class BMF26 extends Application {

    private static final int BALL_RADIUS = 10;
    private static final int PINS = 7;
    private static final int SLOT_WIDTH = 80;
    private static final int SLOT_COUNT = 8; // Updated to 8 for Slot 1 to Slot 7
    private int[] slots = new int[SLOT_COUNT];
    private Label[] slotLabels = new Label[SLOT_COUNT];
    private int ballCount = 10;
    private ArrayList<Circle> pegs; // Store pegs for reuse
    private ArrayList<Line> slotLines; // Store the slot lines to move them
    private Rectangle slotBase; // Store the base rectangle to move it

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, peachpuff, ivory);");

        VBox controlBox = new VBox(10);
        controlBox.setLayoutX(20);
        controlBox.setLayoutY(20);

        // Timer Label
        Label timerLabel = new Label("Time Left: 30s"); // Initial value
        timerLabel.setStyle("-fx-text-fill: red;" +
                "-fx-font-size: 24px;" +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold;");
        timerLabel.setLayoutX(350); // Center the label
        timerLabel.setLayoutY(20);
        pane.getChildren().add(timerLabel);

        // Create a ComboBox for play duration selection
        ComboBox<String> durationDropdown = new ComboBox<>();
        durationDropdown.getItems().addAll("30 Seconds", "1 Minute", "1.3 Minutes", "2 Minutes");
        durationDropdown.setValue("30 Seconds"); // Default value
        durationDropdown.setStyle("-fx-background-color: lightblue;" +
                "-fx-text-fill: black;" +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;");
        durationDropdown.setPrefWidth(222);

        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-background-color: darkgreen; " +
                "-fx-text-fill: white; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-size: 18px; " +
                "-fx-font-weight: bold;");

        controlBox.getChildren().addAll(new Label("Galton Game | Developed by PRINCE"),
                new Label("Select Play Duration:"), durationDropdown, startButton);

        VBox slotInfoBox = new VBox(10);
        slotInfoBox.setLayoutX(700);
        slotInfoBox.setLayoutY(100);

        for (int i = 1; i < SLOT_COUNT; i++) { // Start from Slot 1
            slotLabels[i] = new Label("Slot " + i + ": 0 balls");
            slotLabels[i].setStyle("-fx-text-fill: blue;" +
                    "-fx-font-size: 20px;" +
                    "-fx-font-family: 'Verdana';" +
                    "-fx-font-weight: bold;");
            slotInfoBox.getChildren().add(slotLabels[i]);
        }

        pegs = drawMachine(pane);
        pane.getChildren().add(slotInfoBox);

        startButton.setOnAction(e -> {
            pane.getChildren().removeIf(node -> node instanceof Circle && !pegs.contains(node));

            for (int i = 1; i < SLOT_COUNT; i++) {
                slots[i] = 0;
                slotLabels[i].setText("Slot " + i + ": 0 balls");
            }

            // Get duration from dropdown and calculate total time in seconds
            String selectedDuration = durationDropdown.getValue();
            int totalTimeInSeconds = switch (selectedDuration) {
                case "30 Seconds" -> 30;
                case "1 Minute" -> 60;
                case "1.3 Minutes" -> 78;
                case "2 Minutes" -> 120;
                default -> 30;
            };

            // Start the countdown timer
            startCountdown(timerLabel, totalTimeInSeconds);

            dropBallsSequentially(pane, totalTimeInSeconds);
        });

        pane.getChildren().add(controlBox);

        Scene scene = new Scene(pane, 900, 850);

        // Add KeyEvent handler for moving slots
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> moveSlots(-10, 0); // Move slots left
                case RIGHT -> moveSlots(10, 0); // Move slots right
                case UP -> moveSlots(0, -10); // Move slots up
                case DOWN -> moveSlots(0, 10); // Move slots down
            }
        });

        primaryStage.setTitle("Bean Machine Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ArrayList<Circle> drawMachine(Pane pane) {
        ArrayList<Circle> pegList = new ArrayList<>();
        int paneCenterX = 450;
        int machineWidth = SLOT_WIDTH * (SLOT_COUNT - 1);
        int slotStartX = paneCenterX - machineWidth / 2;

        for (int i = 0; i < PINS; i++) {
            for (int j = 0; j <= i; j++) {
                double x = paneCenterX - i * (SLOT_WIDTH / 2) + j * SLOT_WIDTH;
                double y = 150 + i * 50;
                Circle pin = new Circle(x, y, 7, Color.BLACK);
                pegList.add(pin);
                pane.getChildren().add(pin);
            }
        }

        int slotTopY = 500;
        int slotBottomY = 795;

        slotLines = new ArrayList<>();
        for (int i = 0; i < SLOT_COUNT; i++) {
            Line slot = new Line(slotStartX + i * SLOT_WIDTH, slotTopY, slotStartX + i * SLOT_WIDTH, slotBottomY);
            slot.setStrokeWidth(18.0);
            slot.setStrokeLineCap(StrokeLineCap.ROUND);

            Color color1 = darkRandomColor();
            Color color2 = darkRandomColor();
            LinearGradient gradient = new LinearGradient(
                    0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0, color1), new Stop(1, color2)
            );
            slot.setStroke(gradient);

            pane.getChildren().add(slot);
            slotLines.add(slot); // Add the slot to the list for movement
        }

        double baseHeight = 20;
        double additionalWidth = 15;
        double baseWidth = SLOT_WIDTH * (SLOT_COUNT - 1) + additionalWidth;
        slotBase = new Rectangle(slotStartX - additionalWidth / 2, slotBottomY, baseWidth, baseHeight);
        slotBase.setArcWidth(15);
        slotBase.setArcHeight(15);

        Color baseColor1 = darkRandomColor();
        Color baseColor2 = darkRandomColor();
        LinearGradient baseGradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, baseColor1), new Stop(1, baseColor2)
        );
        slotBase.setFill(baseGradient);

        pane.getChildren().add(slotBase);

        return pegList;
    }

    private void dropBallsSequentially(Pane pane, int totalTimeInSeconds) {
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

                // Simulate the ball path
                for (Circle peg : pegs) {
                    double pegX = peg.getCenterX();
                    double pegY = peg.getCenterY();

                    double bounceDirection = random.nextBoolean() ? -30 : 30;
                    double newX = currentX + bounceDirection;

                    path.getElements().add(new QuadCurveTo(pegX, pegY, newX, pegY + 20));

                    currentX = newX;
                    currentY = pegY + 20;
                }

                // Final slot calculation
                final int finalSlotIndex = Math.max(1, Math.min(
                        (int) ((currentX - (450 - (SLOT_WIDTH * (SLOT_COUNT - 1)) / 2)) / SLOT_WIDTH) + 1,
                        SLOT_COUNT - 1
                ));

                final double finalX = currentX;
                final double finalY = 780 - slots[finalSlotIndex] * (BALL_RADIUS * 2 + 5);

                PathTransition pathTransition = new PathTransition(Duration.seconds(9), path, ball);
                pathTransition.setCycleCount(1);
                pathTransition.setOnFinished(ev -> {
                    // Apply the final translation to the ball
                    ball.setTranslateX(finalX - ball.getCenterX());
                    ball.setTranslateY(finalY - ball.getCenterY());

                    // Update the slot count and label
                    slots[finalSlotIndex]++;
                    slotLabels[finalSlotIndex].setText("Slot " + finalSlotIndex + ": " + slots[finalSlotIndex] + " balls");
                });

                pathTransition.play();
            });

            delay.play();
        }
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

    private void moveSlots(double dx, double dy) {
        for (Line slot : slotLines) {
            slot.setStartX(slot.getStartX() + dx);
            slot.setEndX(slot.getEndX() + dx);
            slot.setStartY(slot.getStartY() + dy);
            slot.setEndY(slot.getEndY() + dy);
        }

        slotBase.setX(slotBase.getX() + dx);
        slotBase.setY(slotBase.getY() + dy);
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

    private Color darkRandomColor() {
        Random random = new Random();
        double red = random.nextDouble() * 0.5;
        double green = random.nextDouble() * 0.5;
        double blue = random.nextDouble() * 0.5;
        return Color.color(red, green, blue);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
