package application;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class InteractiveBeanMachine extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane gamePane = new Pane();

        // Define constants for the bean machine
        final double MACHINE_WIDTH = 400;
        final double MACHINE_HEIGHT = 500;
        final double PEG_RADIUS = 5;
        final int ROWS = 7;
        final int BALL_RADIUS = 6;
        final double SLOT_WIDTH = 40;

        // Starting position of the top of the machine
        double startX = MACHINE_WIDTH / 2;
        double startY = 100;

        // Draw the triangle outline for the machine
        Line leftLine = new Line(startX, startY, startX - MACHINE_WIDTH / 2, MACHINE_HEIGHT - 100);
        Line rightLine = new Line(startX, startY, startX + MACHINE_WIDTH / 2, MACHINE_HEIGHT - 100);

        // Remove the bottom line and draw closed rectangles to hold the balls
        gamePane.getChildren().addAll(leftLine, rightLine);

        // Draw pegs in a triangular formation
        Circle[][] pegs = new Circle[ROWS][];
        for (int row = 0; row < ROWS; row++) {
            pegs[row] = new Circle[row + 1];
            for (int i = 0; i <= row; i++) {
                double x = startX - row * SLOT_WIDTH / 2.0 + i * SLOT_WIDTH; // Center the pegs in each row
                double y = startY + row * 40; // Space rows vertically
                Circle peg = new Circle(x, y, PEG_RADIUS, Color.DARKRED);
                pegs[row][i] = peg;
                gamePane.getChildren().add(peg);
            }
        }

        // Draw vertical slots at the bottom to store balls
        Rectangle[] slots = new Rectangle[ROWS + 1];
        for (int i = 0; i <= ROWS; i++) {
            double x = startX - (ROWS * SLOT_WIDTH) / 2.0 + i * SLOT_WIDTH;
            slots[i] = new Rectangle(x - SLOT_WIDTH / 2, MACHINE_HEIGHT - 20, SLOT_WIDTH, 20);
            slots[i].setFill(Color.DARKBLUE);
            gamePane.getChildren().add(slots[i]);
        }

        // Create user input controls
        TextField numBallsField = new TextField();
        numBallsField.setPromptText("Enter number of balls");
        Button startGameButton = new Button("Start Game");

        VBox controls = new VBox(10, numBallsField, startGameButton);
        controls.setAlignment(Pos.CENTER);

        // Container layout
        HBox container = new HBox(gamePane);
        container.setAlignment(Pos.CENTER);
        VBox root = new VBox(20, controls, container);
        root.setAlignment(Pos.TOP_CENTER);

        // Function to simulate a ball falling
        startGameButton.setOnAction(event -> {
            try {
                int numBalls = Integer.parseInt(numBallsField.getText());
                for (int i = 0; i < numBalls; i++) {
                    dropBall(gamePane, startX, startY, ROWS, SLOT_WIDTH, MACHINE_HEIGHT);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(root, MACHINE_WIDTH, MACHINE_HEIGHT + 100);
        primaryStage.setTitle("Interactive Bean Machine Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void dropBall(Pane pane, double startX, double startY, int rows, double slotWidth, double machineHeight) {
        Circle ball = new Circle(startX, startY, 6, Color.BLUE);
        pane.getChildren().add(ball);

        Polyline path = new Polyline();
        path.getPoints().addAll(startX, startY);

        Random random = new Random();
        double[] currentX = {startX}; // Use an array for currentX
        double currentY = startY;
        int[] slotIndex = {0}; // Use an array for slotIndex

        // Simulate the ball path
        for (int i = 0; i < rows; i++) {
            currentY += 40; // Move vertically
            if (random.nextBoolean()) {
                currentX[0] -= slotWidth / 2;
            } else {
                currentX[0] += slotWidth / 2;
                slotIndex[0]++;
            }
            path.getPoints().addAll(currentX[0], currentY);
        }

        // Animate the ball falling
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(ball);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.seconds(2));
        pathTransition.setOnFinished(e -> {
            ball.setCenterX(currentX[0]);
            ball.setCenterY(machineHeight - 100);
        });
        pathTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
