package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Random;

public class GaltonBoard extends Application {

    private static final int SLOT_COUNT = 7;
    private static final int ROW_COUNT = 6;
    private static final double PEG_RADIUS = 5;
    private static final double BALL_RADIUS = 8;
    private static final double SLOT_WIDTH = 80;
    private static final double BOARD_WIDTH = SLOT_COUNT * SLOT_WIDTH;
    private static final double BOARD_HEIGHT = 400;
    private static final double START_X = BOARD_WIDTH / 2;
    private static final double START_Y = 50;

    private int[] slotCounts = new int[SLOT_COUNT];
    private Label[] slotLabels = new Label[SLOT_COUNT];

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Input field and button
        Label inputLabel = new Label("Enter number of balls:");
        inputLabel.setLayoutX(10);
        inputLabel.setLayoutY(10);

        TextField inputField = new TextField();
        inputField.setLayoutX(150);
        inputField.setLayoutY(10);

        Button startButton = new Button("Start Game");
        startButton.setLayoutX(320);
        startButton.setLayoutY(10);

        root.getChildren().addAll(inputLabel, inputField, startButton);

        // Draw pegs
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col <= row; col++) {
                double x = START_X + (col - row / 2.0) * SLOT_WIDTH / 2;
                double y = START_Y + row * 50;
                Circle peg = new Circle(x, y, PEG_RADIUS, Color.BLACK);
                root.getChildren().add(peg);
            }
        }

        // Draw slots and slot labels
        for (int i = 0; i <= SLOT_COUNT; i++) {
            double x = i * SLOT_WIDTH;
            Line line = new Line(x, BOARD_HEIGHT, x, BOARD_HEIGHT + 100);
            root.getChildren().add(line);
        }

        for (int i = 0; i < SLOT_COUNT; i++) {
            Label slotLabel = new Label("Slot " + i + ": 0 balls");
            slotLabel.setLayoutX(i * SLOT_WIDTH + 20);
            slotLabel.setLayoutY(BOARD_HEIGHT + 110);
            slotLabel.setTextFill(Color.BLUE);
            root.getChildren().add(slotLabel);
            slotLabels[i] = slotLabel;
        }

        // Start button action
        startButton.setOnAction(e -> {
            try {
                int ballCount = Integer.parseInt(inputField.getText());
                new Thread(() -> {
                    for (int b = 0; b < ballCount; b++) {
                        try {
                            int slot = simulateBall(root);
                            slotCounts[slot]++;
                            int finalSlot = slot;
                            Platform.runLater(() -> slotLabels[finalSlot].setText("Slot " + finalSlot + ": " + slotCounts[finalSlot] + " balls"));
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            } catch (NumberFormatException ex) {
                inputField.setText("Enter a valid number!");
            }
        });

        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT + 150);
        primaryStage.setTitle("Galton Board Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int simulateBall(Pane root) throws InterruptedException {
        Circle ball = new Circle(START_X, START_Y, BALL_RADIUS, Color.RED);

        // Add the ball to the UI using Platform.runLater
        Platform.runLater(() -> root.getChildren().add(ball));

        Random random = new Random();
        double x = START_X;
        double y = START_Y;

        for (int row = 0; row < ROW_COUNT; row++) {
            Thread.sleep(500); // Delay for each row to simulate falling
            x += random.nextBoolean() ? SLOT_WIDTH / 4 : -SLOT_WIDTH / 4;
            y += 50;

            // Update the ball's position on the UI thread
            double finalX = x;
            double finalY = y;
            Platform.runLater(() -> {
                ball.setCenterX(finalX);
                ball.setCenterY(finalY);
            });
        }

        int slot = (int) ((x + SLOT_WIDTH / 2) / SLOT_WIDTH);
        slot = Math.max(0, Math.min(slot, SLOT_COUNT - 1));

        // Move ball to its final position in the slot
        double finalX = x;
        Platform.runLater(() -> ball.setCenterY(BOARD_HEIGHT - BALL_RADIUS));

        Thread.sleep(500); // Small delay before the next ball
        return slot;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
