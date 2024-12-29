package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;
import java.util.Random;

public class GaltonBoard extends Application {

    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 800;
    private static final int PEG_RADIUS = 5;
    private static final int BALL_RADIUS = 8;
    private static final int SLOT_COUNT = 10;
    private static final int PEG_ROWS = 8;
    private static final int BALL_SPEED = 3;

    private Pane root = new Pane();
    private Random random = new Random();
    private int[] slotCounts = new int[SLOT_COUNT];
    private int ballCount;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Prompt user for the number of balls
        ballCount = getUserInput();

        // Create the pegboard
        createPegboard();

        // Add slots
        createSlots();

        // Add balls and animate them
        for (int i = 0; i < ballCount; i++) {
            Circle ball = createBall();
            animateBall(ball, i);
        }

        // Show slot statistics
        updateSlotStatistics();

        // Set up the stage
        Scene scene = new Scene(root, BOARD_WIDTH + 200, BOARD_HEIGHT); // Extra width for statistics
        primaryStage.setTitle("Galton Board Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int getUserInput() {
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Input Number of Balls");
        dialog.setHeaderText("Set the Number of Balls");
        dialog.setContentText("Enter the number of balls to drop:");

        Optional<String> result = dialog.showAndWait();
        return result.map(Integer::parseInt).orElse(10); // Default to 10 if no input
    }

    private void createPegboard() {
        int pegSpacing = BOARD_WIDTH / (SLOT_COUNT + 1);
        for (int row = 0; row < PEG_ROWS; row++) {
            int offset = (row % 2 == 0) ? pegSpacing / 2 : 0;
            for (int col = 0; col < SLOT_COUNT; col++) {
                Circle peg = new Circle(pegSpacing * col + offset, 100 + row * 50, PEG_RADIUS);
                peg.setFill(Color.BLACK);
                root.getChildren().add(peg);
            }
        }
    }

    private void createSlots() {
        int slotWidth = BOARD_WIDTH / SLOT_COUNT;
        for (int i = 0; i <= SLOT_COUNT; i++) {
            Line line = new Line(i * slotWidth, BOARD_HEIGHT - 100, i * slotWidth, BOARD_HEIGHT);
            root.getChildren().add(line);
        }
    }

    private Circle createBall() {
        Circle ball = new Circle(BOARD_WIDTH / 2.0, BALL_RADIUS, BALL_RADIUS);
        ball.setFill(Color.BLUE);
        root.getChildren().add(ball);
        return ball;
    }

    private void animateBall(Circle ball, int ballIndex) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            double dx = BALL_SPEED * (random.nextBoolean() ? 1 : -1);
            double dy = BALL_SPEED;
            ball.setTranslateX(ball.getTranslateX() + dx);
            ball.setTranslateY(ball.getTranslateY() + dy);

            // Check for collision with bottom
            if (ball.getTranslateY() + ball.getCenterY() >= BOARD_HEIGHT - 100) {
                int slot = getSlot(ball.getTranslateX() + BOARD_WIDTH / 2.0);
                slotCounts[slot]++;
                updateSlotStatistics();
                ball.setVisible(false); // Remove ball from view
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private int getSlot(double xPosition) {
        int slotWidth = BOARD_WIDTH / SLOT_COUNT;
        return (int) Math.min(Math.max(xPosition / slotWidth, 0), SLOT_COUNT - 1);
    }

    private void updateSlotStatistics() {
        root.getChildren().removeIf(node -> node instanceof Label); // Clear previous statistics

        int xOffset = BOARD_WIDTH + 20; // Offset to the right of the board
        int yOffset = 50;

        for (int i = 0; i < SLOT_COUNT; i++) {
            Label label = new Label("Slot " + i + ": " + slotCounts[i]);
            label.setLayoutX(xOffset);
            label.setLayoutY(yOffset + i * 20);
            root.getChildren().add(label);
        }
    }
}
