package application;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class BeanMachineGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane gamePane = new Pane();

        // Define constants for the bean machine
        final double MACHINE_WIDTH = 300;
        final double MACHINE_HEIGHT = 400;
        final double PEG_RADIUS = 5;
        final int ROWS = 7;
        final int BALL_RADIUS = 6;
        final double SLOT_WIDTH = 20;

        // Starting position of the top of the machine
        double startX = MACHINE_WIDTH / 2;
        double startY = 50;

        // Draw the triangle outline for the machine
        Line leftLine = new Line(startX, startY, startX - MACHINE_WIDTH / 2, MACHINE_HEIGHT - 100);
        Line rightLine = new Line(startX, startY, startX + MACHINE_WIDTH / 2, MACHINE_HEIGHT - 100);
        Line bottomLine = new Line(startX - MACHINE_WIDTH / 2, MACHINE_HEIGHT - 100, startX + MACHINE_WIDTH / 2, MACHINE_HEIGHT - 100);

        gamePane.getChildren().addAll(leftLine, rightLine, bottomLine);

        // Draw pegs in a triangular formation
        Circle[][] pegs = new Circle[ROWS][];
        for (int row = 0; row < ROWS; row++) {
            pegs[row] = new Circle[row + 1];
            for (int i = 0; i <= row; i++) {
                double x = startX - row * SLOT_WIDTH / 2.0 + i * SLOT_WIDTH; // Center the pegs in each row
                double y = startY + row * 30; // Space rows vertically
                Circle peg = new Circle(x, y, PEG_RADIUS);
                peg.setFill(Color.BLACK);
                pegs[row][i] = peg;
                gamePane.getChildren().add(peg);
            }
        }

        // Draw vertical slots at the bottom
        int[] slots = new int[ROWS + 1];
        for (int i = 0; i <= ROWS + 1; i++) {
            double x = startX - (ROWS * SLOT_WIDTH) / 2.0 + i * SLOT_WIDTH;
            Line slot = new Line(x, MACHINE_HEIGHT - 100, x, MACHINE_HEIGHT);
            gamePane.getChildren().add(slot);
        }

        // User input controls
        TextField numBallsField = new TextField();
        numBallsField.setPromptText("Enter number of balls");
        TextField numSlotsField = new TextField("Number of slots: " + (ROWS + 1));
        numSlotsField.setEditable(false);
        Button startGameButton = new Button("Start Game");

        VBox controlBox = new VBox(10, numBallsField, numSlotsField, startGameButton);
        controlBox.setLayoutX(10);
        controlBox.setLayoutY(10);

        Pane rootPane = new Pane();
        rootPane.getChildren().addAll(gamePane, controlBox);

        // Function to simulate a ball falling
        startGameButton.setOnAction(event -> {
            try {
                int numBalls = Integer.parseInt(numBallsField.getText());
                for (int i = 0; i < numBalls; i++) {
                    int currentBallIndex = i;
                    javafx.application.Platform.runLater(() -> dropBall(gamePane, startX, startY, ROWS, SLOT_WIDTH, MACHINE_HEIGHT, slots));
                    Thread.sleep(1000); // Delay between dropping balls
                }

                // Display the slots after all balls drop
                javafx.application.Platform.runLater(() -> {
                    for (int i = 0; i < slots.length; i++) {
                        TextField slotDisplay = new TextField("Slot " + i + ": " + slots[i] + " balls");
                        slotDisplay.setLayoutX(10);
                        slotDisplay.setLayoutY(50 + i * 30);
                        slotDisplay.setEditable(false);
                        rootPane.getChildren().add(slotDisplay);
                    }
                });
            } catch (NumberFormatException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(rootPane, MACHINE_WIDTH, MACHINE_HEIGHT + 200);
        primaryStage.setTitle("Interactive Bean Machine Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void dropBall(Pane pane, double startX, double startY, int rows, double slotWidth, double machineHeight, int[] slots) {
        Circle ball = new Circle(startX, startY, 6);
        ball.setFill(Color.BLUE);
        pane.getChildren().add(ball);

        Polyline path = new Polyline();
        path.getPoints().addAll(startX, startY);

        Random random = new Random();
        double[] currentX = {startX}; // Use an array for currentX
        double currentY = startY;
        int[] slotIndex = {0}; // Use an array for slotIndex

        // Simulate the ball path
        for (int i = 0; i < rows; i++) {
            currentY += 30; // Move vertically
            if (random.nextBoolean()) {
                currentX[0] -= slotWidth / 2;
            } else {
                currentX[0] += slotWidth / 2;
                slotIndex[0]++;
            }
            path.getPoints().addAll(currentX[0], currentY);
        }

        // Add ball to final slot
        slots[slotIndex[0]]++;

        // Animate the ball falling
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(ball);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.seconds(2));
        pathTransition.setOnFinished(e -> {
            ball.setCenterX(currentX[0]);
            ball.setCenterY(machineHeight - 50 - slots[slotIndex[0]] * 10);
        });
        pathTransition.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
