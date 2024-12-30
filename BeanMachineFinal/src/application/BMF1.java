/************************************
 * Galton Game | Bean Machine Game
 ************************************/

package application;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BMF1 extends Application {

    private static final int BALL_RADIUS = 10;
    private static final int PINS = 7;
    private static final int SLOT_WIDTH = 80;
    private static final int SLOT_COUNT = PINS;
    private static final int BALL_SPEED = 50; // Lower is faster
    private int[] slots = new int[SLOT_COUNT];
    private Label[] slotLabels = new Label[SLOT_COUNT];
    private int ballCount = 10;

    private List<Circle> pegs = new ArrayList<>(); // Store all pegs for collision detection

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        VBox controlBox = new VBox(10);
        VBox slotInfoBox = new VBox(10);
        controlBox.setLayoutX(20);
        controlBox.setLayoutY(20);
        slotInfoBox.setLayoutX(700);
        slotInfoBox.setLayoutY(100);

        TextField ballInputField = new TextField();
        ballInputField.setPromptText("Enter number of balls here:");
        ballInputField.setStyle(
                "-fx-background-color: lightblue;" +
                        "-fx-text-fill: black;" +
                        "-fx-prompt-text-fill: blue;" +
                        "-fx-font-family: 'Arital Rounded MT Bold'; " +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;");
        ballInputField.setPrefWidth(222);

        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-background-color: darkgreen; " +
                "-fx-text-fill: white; " +
                "-fx-font-family: 'Arital Rounded MT Bold'; " +
                "-fx-font-size: 18px; " +
                "-fx-font-weight: bold;");

        controlBox.getChildren().addAll(new Label("Galton Game | Developed by PRINCE"), ballInputField, startButton);

        for (int i = 0; i < SLOT_COUNT; i++) {
            slotLabels[i] = new Label("Slot " + i + ": 0 balls");
            slotLabels[i].setStyle("-fx-text-fill: blue; -fx-font-size: 20px; -fx-font-family: 'Verdana'; -fx-font-weight: bold;");
            slotInfoBox.getChildren().add(slotLabels[i]);
        }

        drawMachine(pane);

        startButton.setOnAction(e -> {
            pane.getChildren().removeIf(node -> node instanceof Circle && "ball".equals(node.getId()));
            for (int i = 0; i < SLOT_COUNT; i++) {
                slots[i] = 0;
                slotLabels[i].setText("Slot " + i + ": 0 balls");
            }

            try {
                ballCount = Integer.parseInt(ballInputField.getText());
                if (ballCount <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                ballInputField.setText("");
                ballInputField.setPromptText("Please enter a valid number!");
                ballInputField.setStyle(
                        "-fx-background-color: lightblue;" +
                                "-fx-text-fill: black;" +
                                "-fx-prompt-text-fill: darkred;" +
                                "-fx-font-family: 'Arital Rounded MT Bold'; " +
                                "-fx-font-size: 15px;" +
                                "-fx-font-weight: bold;");
                return;
            }

            dropBalls(pane);
        });

        pane.getChildren().addAll(controlBox, slotInfoBox);
        Scene scene = new Scene(pane, 900, 850);
        primaryStage.setTitle("Bean Machine Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawMachine(Pane pane) {
        int paneCenterX = 450;
        int machineWidth = SLOT_WIDTH * SLOT_COUNT;
        int slotStartX = paneCenterX - machineWidth / 2;

        for (int i = 0; i < PINS; i++) {
            for (int j = 0; j <= i; j++) {
                Circle pin = new Circle(paneCenterX - i * (SLOT_WIDTH / 2) + j * SLOT_WIDTH, 150 + i * 50, 8, Color.BLACK);
                pegs.add(pin);
                pane.getChildren().add(pin);
            }
        }

        int slotTopY = 500;
        int slotBottomY = 780;

        for (int i = 0; i <= SLOT_COUNT; i++) {
            Line slot = new Line(slotStartX + i * SLOT_WIDTH, slotTopY, slotStartX + i * SLOT_WIDTH, slotBottomY);
            pane.getChildren().add(slot);
        }

        Rectangle base = new Rectangle(slotStartX, slotBottomY, SLOT_WIDTH * SLOT_COUNT, 15);
        base.setFill(Color.GRAY);
        pane.getChildren().add(base);

        for (int i = 0; i < SLOT_COUNT; i++) {
            Label slotNumber = new Label("Slot " + i);
            slotNumber.setStyle("-fx-text-fill: darkgreen; -fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-font-weight: bold;");
            double slotCenterX = slotStartX + (i + 0.5) * SLOT_WIDTH;
            slotNumber.setLayoutX(slotCenterX - 20);
            slotNumber.setLayoutY(slotBottomY + 25);
            pane.getChildren().add(slotNumber);
        }
    }

    private void dropBalls(Pane pane) {
        Random random = new Random();

        for (int i = 0; i < ballCount; i++) {
            Circle ball = new Circle(450, 100, BALL_RADIUS); // Start from the top center
            ball.setFill(randomColor());
            ball.setId("ball"); // Assign a unique ID to identify balls
            pane.getChildren().add(ball);

            // Simulate ball path with collisions
            int position = simulateBallBounce(ball, pane);

            slots[position]++;

            // Calculate slot center
            int paneCenterX = 450; // Center of the pane
            int machineWidth = SLOT_WIDTH * SLOT_COUNT; // Total width of the slots
            int slotStartX = paneCenterX - machineWidth / 2; // Start position of the first slot
            double slotCenterX = slotStartX + (position + 0.5) * SLOT_WIDTH; // Center of the target slot

            // Set the ball's final position
            double deltaX = slotCenterX - 450; // Adjusted X translation relative to initial position
            double deltaY = 400 + slots[position] * 25; // Adjusted Y translation (stack balls in each slot)

            TranslateTransition transition = new TranslateTransition(Duration.seconds(1), ball);
            transition.setByX(deltaX);
            transition.setByY(deltaY);
            transition.setOnFinished(e -> ball.toFront());
            transition.play();
        }

        // Add a delay to update and display slot results
        PauseTransition pause = new PauseTransition(Duration.seconds(4)); // Wait for all animations to complete
        pause.setOnFinished(e -> updateSlotLabels());
        pause.play();
    }
    
    private int simulateBallBounce(Circle ball, Pane pane) {
        Random random = new Random();
        int position = 0;

        for (int row = 0; row < PINS; row++) {
            for (int i = 0; i <= row; i++) {
                double pegX = 450 - row * (SLOT_WIDTH / 2.0) + i * SLOT_WIDTH; // X position of the peg
                double pegY = 150 + row * 50; // Y position of the peg

                // Check if ball "hits" the peg
                if (Math.abs(ball.getTranslateX() - pegX) <= 20 && Math.abs(ball.getTranslateY() - pegY) <= 20) {
                    // Random bounce: left (-1) or right (+1)
                    position += random.nextBoolean() ? 1 : 0;

                    // Simulate the bounce visually
                    TranslateTransition bounce = new TranslateTransition(Duration.millis(200), ball);
                    bounce.setByX(random.nextBoolean() ? -10 : 10);
                    bounce.setByY(50); // Move down after bounce
                    bounce.play();
                }
            }
        }

        return position;
    }


    private void updateSlotLabels() {
        for (int i = 0; i < SLOT_COUNT; i++) {
            slotLabels[i].setText("Slot " + i + ": " + slots[i] + " balls");
        }
    }

    private int simulateBallPath() {
        Random random = new Random();
        int position = 0;
        for (int i = 0; i < SLOT_COUNT - 1; i++) {
            if (random.nextBoolean()) {
                position++;
            }
        }
        return position;
    }

    private Color randomColor() {
        Random random = new Random();
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
