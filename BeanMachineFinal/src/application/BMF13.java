package application;

import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class BMF13 extends Application {

    private static final int BALL_RADIUS = 10;
    private static final int PINS = 7;
    private static final int SLOT_WIDTH = 80;
    private static final int SLOT_COUNT = PINS;
    private int[] slots = new int[SLOT_COUNT];
    private Label[] slotLabels = new Label[SLOT_COUNT];
    private int ballCount = 10;
    private ArrayList<Circle> pegs; // Store pegs for reuse

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        VBox controlBox = new VBox(10);
        VBox slotInfoBox = new VBox(10);
        controlBox.setLayoutX(20);
        controlBox.setLayoutY(20);
        slotInfoBox.setLayoutX(700);
        slotInfoBox.setLayoutY(100);

        // User input for the number of balls
        TextField ballInputField = new TextField();
        ballInputField.setPromptText("Enter number of balls here:");
        ballInputField.setStyle(
                "-fx-background-color: lightblue;" +
                        "-fx-text-fill: black;" +
                        "-fx-prompt-text-fill: blue;" +
                        "-fx-font-family: 'Arial'; " +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;");
        ballInputField.setPrefWidth(222);

        // Start game button
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-background-color: darkgreen; " +
                "-fx-text-fill: white; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-size: 18px; " +
                "-fx-font-weight: bold;");

        controlBox.getChildren().addAll(new Label("Galton Game | Developed by PRINCE"), ballInputField, startButton);

        // Initialize slot labels
        for (int i = 0; i < SLOT_COUNT; i++) {
            slotLabels[i] = new Label("Slot " + i + ": 0 balls");
            slotLabels[i].setStyle(
                    "-fx-text-fill: blue;" +
                            "-fx-font-size: 20px;" +
                            "-fx-font-family: 'Verdana';" +
                            "-fx-font-weight: bold;");
            slotInfoBox.getChildren().add(slotLabels[i]);
        }

        // Draw the machine
        pegs = drawMachine(pane);

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
                                "-fx-font-family: 'Arial'; " +
                                "-fx-font-size: 15px;" +
                                "-fx-font-weight: bold;");
                return;
            }

            dropBallsSequentially(pane);
        });

        pane.getChildren().addAll(controlBox, slotInfoBox);

        Scene scene = new Scene(pane, 900, 850);
        primaryStage.setTitle("Bean Machine Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ArrayList<Circle> drawMachine(Pane pane) {
        ArrayList<Circle> pegList = new ArrayList<>();
        int paneCenterX = 450;
        int machineWidth = SLOT_WIDTH * SLOT_COUNT;
        int slotStartX = paneCenterX - machineWidth / 2;

        // Draw the pins
        for (int i = 0; i < PINS; i++) {
            for (int j = 0; j <= i; j++) {
                double x = paneCenterX - i * (SLOT_WIDTH / 2) + j * SLOT_WIDTH;
                double y = 150 + i * 50;
                Circle pin = new Circle(x, y, 5, Color.BLACK);
                pegList.add(pin);
                pane.getChildren().add(pin);
            }
        }

        // Draw slots
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
            slotNumber.setStyle(
                    "-fx-text-fill: darkgreen;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-family: 'Arial';" +
                            "-fx-font-weight: bold;");
            double slotCenterX = slotStartX + (i + 0.5) * SLOT_WIDTH;
            slotNumber.setLayoutX(slotCenterX - 20);
            slotNumber.setLayoutY(slotBottomY + 25);
            pane.getChildren().add(slotNumber);
        }

        return pegList;
    }

    private void dropBallsSequentially(Pane pane) {
        Random random = new Random();

        for (int i = 0; i < ballCount; i++) {
            final int ballIndex = i;

            PauseTransition delay = new PauseTransition(Duration.seconds(i * 1.5));
            delay.setOnFinished(event -> {
                Circle ball = new Circle(450, 100, BALL_RADIUS);
                ball.setFill(randomColor());
                ball.setId("ball" + ballIndex);
                pane.getChildren().add(ball);

                Path path = new Path();
                path.getElements().add(new MoveTo(ball.getCenterX(), ball.getCenterY()));

                // Create the bouncing effect over pegs
                for (Circle peg : pegs) {
                    double bounceDirection = random.nextBoolean() ? -15 : 15;
                    path.getElements().add(new QuadCurveTo(
                            peg.getCenterX(), peg.getCenterY(),
                            peg.getCenterX() + bounceDirection, peg.getCenterY() + 20
                    ));
                }

                int slotsCount = SLOT_COUNT;
                double slotWidth = SLOT_WIDTH;
                double finalX = 450 - (slotsCount / 2) * SLOT_WIDTH + random.nextInt(slotsCount) * slotWidth + slotWidth / 2;

                int finalSlotIndex = (int) ((finalX - 450 + (SLOT_COUNT / 2) * SLOT_WIDTH) / SLOT_WIDTH);

                // Calculate finalY based on the number of balls already in the slot to prevent overlap
                double finalY = 780 - slots[finalSlotIndex] * (BALL_RADIUS * 2 + 10);

                double controlY = finalY - 80;
                path.getElements().add(new QuadCurveTo(
                        (finalX + ball.getCenterX()) / 2, controlY,
                        finalX, finalY
                ));

                PathTransition pathTransition = new PathTransition(Duration.seconds(6), path, ball);
                pathTransition.setCycleCount(1);

                // Update slot label in real-time during the animation
                pathTransition.setOnFinished(ev -> {
                    slots[finalSlotIndex]++;
                    slotLabels[finalSlotIndex].setText("Slot " + finalSlotIndex + ": " + slots[finalSlotIndex] + " balls");
                });

                pathTransition.play();
            });

            delay.play();
        }
    }


    private Color randomColor() {
        Random random = new Random();
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
