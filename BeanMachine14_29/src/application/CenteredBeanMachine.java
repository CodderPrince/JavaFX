/******************************
 * 
 * Ex: 14.29
 * Bean Machine | Balton Game
 * Modified By PRINCE
 * ID: 12105007
 * 
 ******************************/

package application;

// Importing necessary libraries for JavaFX application
import javafx.animation.PathTransition; // For animating the ball's path
import javafx.application.Application; // Base class for JavaFX applications
import javafx.geometry.Pos; // For aligning UI components
import javafx.scene.Scene; // Represents the main content container for the UI
import javafx.scene.control.Button; // For the "Start Game" button
import javafx.scene.control.TextField; // For user input of number of balls
import javafx.scene.layout.HBox; // A horizontal layout container
import javafx.scene.layout.Pane; // A generic container for graphical elements
import javafx.scene.layout.StackPane; // A layout that stacks children on top of each other
import javafx.scene.layout.VBox; // A vertical layout container
import javafx.scene.paint.Color; // To set colors for shapes and text
import javafx.scene.shape.Circle; // To draw circular elements (pegs, balls)
import javafx.scene.shape.Line; // To draw straight lines
import javafx.scene.shape.Polyline; // For drawing the path of the ball
import javafx.scene.text.Font; // For setting text font
import javafx.scene.text.FontWeight; // For bold font styling
import javafx.scene.text.Text; // To display text on the screen
import javafx.stage.Stage; // Represents the primary window for the application
import javafx.util.Duration; // For setting animation durations

import java.util.Random; // To randomly determine ball movement direction

public class CenteredBeanMachine extends Application { // Main class extending JavaFX Application

    // Constants for the machine dimensions and ball/peg properties
    private static final double MACHINE_WIDTH = 400; // Width of the bean machine
    private static final double MACHINE_HEIGHT = 400; // Height of the bean machine
    private static final double PEG_RADIUS = 5; // Radius of each peg
    private static final int ROWS = 9; // Number of rows of pegs
    private static final double BALL_RADIUS = 6; // Radius of the balls
    private static final double SLOT_WIDTH = 40; // Width of each slot at the bottom

    @Override
    public void start(Stage primaryStage) { // Entry point for the JavaFX application
        // Pane to hold the bean machine graphics
        Pane machinePane = new Pane();
        machinePane.setPrefSize(MACHINE_WIDTH, MACHINE_HEIGHT); // Setting pane dimensions

        // Center position for the machine and starting/ending Y positions
        double centerX = MACHINE_WIDTH / .6; // Center of the machine (intentionally off for error illustration)
        double startY = 50; // Top Y position for the machine
        double bottomY = MACHINE_HEIGHT - 50; // Bottom Y position for the machine

        // Draw the machine's triangular outline
        Line leftLine = new Line(centerX, startY, centerX - MACHINE_WIDTH / 2, bottomY); // Left side
        Line rightLine = new Line(centerX, startY, centerX + MACHINE_WIDTH / 2, bottomY); // Right side
        Line bottomLine = new Line(centerX - MACHINE_WIDTH / 2, bottomY, centerX + MACHINE_WIDTH / 2, bottomY); // Bottom

        // Styling the outline
        leftLine.setStroke(Color.DARKBLUE);
        rightLine.setStroke(Color.DARKBLUE);
        bottomLine.setStroke(Color.DARKBLUE);

        // Adding the lines to the pane
        machinePane.getChildren().addAll(leftLine, rightLine, bottomLine);

        // Draw pegs for the machine
        for (int row = 0; row < ROWS; row++) { // Loop through each row of pegs
            for (int i = 0; i <= row; i++) { // Loop through pegs in the current row
                double x = centerX - row * SLOT_WIDTH / 2.0 + i * SLOT_WIDTH; // Calculate X position
                double y = startY + row * 30; // Calculate Y position
                Circle peg = new Circle(x, y, PEG_RADIUS); // Create a peg
                peg.setFill(Color.DARKRED); // Set peg color
                machinePane.getChildren().add(peg); // Add peg to the pane
            }
        }

        // Draw slots at the bottom
        int[] slots = new int[ROWS + 1]; // Array to count balls in each slot
        for (int i = 0; i <= ROWS; i++) {
            double x = centerX - (ROWS * SLOT_WIDTH) / 2.0 + i * SLOT_WIDTH; // Calculate slot line X position
            Line slotLine = new Line(x, bottomY, x, bottomY + 310); // Create slot line
            machinePane.getChildren().add(slotLine); // Add slot line to the pane

            // Add labels to the slots
            Text slotLabel = new Text(x - 10, bottomY + 330, "Slot " + i); // Label text
            slotLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12)); // Set font style
            slotLabel.setFill(Color.DARKRED); // Set font color
            machinePane.getChildren().add(slotLabel); // Add label to the pane
        }

        // Create user input controls
        Text enterLabel = new Text("Enter a number between 1 and 100"); // Prompt label
        enterLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16)); // Font style for the label
        enterLabel.setFill(Color.DARKBLUE); // Font color

        TextField numBallsField = new TextField(); // Input field for number of balls
        numBallsField.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 20px;"); // Styling

        Button startGameButton = new Button("Start Game"); // Button to start the game
        startGameButton.setFont(Font.font("Verdana", FontWeight.BOLD, 16)); // Font style for the button
        startGameButton.setStyle("-fx-background-color: darkblue; -fx-text-fill: white;"); // Styling

        VBox controls = new VBox(10, enterLabel, numBallsField, startGameButton); // Controls in a vertical box
        controls.setAlignment(Pos.CENTER); // Center align the controls

        // Output display for results
        VBox outputBox = new VBox(10); // Vertical box for results

        // Main layout for machine and output
        HBox mainContainer = new HBox(20, new StackPane(machinePane), outputBox); // Horizontal box for layout
        VBox root = new VBox(20, controls, mainContainer); // Root layout
        root.setAlignment(Pos.TOP_CENTER); // Align root content to the top-center

        // Ball drop simulation logic
        startGameButton.setOnAction(event -> {
            try {
                int numBalls = Integer.parseInt(numBallsField.getText()); // Get number of balls
                outputBox.getChildren().clear(); // Clear previous results

                int[] finishedBalls = {0}; // Counter for completed balls

                for (int i = 0; i < numBalls; i++) {
                    javafx.application.Platform.runLater(() -> {
                        dropBall(machinePane, centerX, startY, ROWS, SLOT_WIDTH, bottomY, slots, finishedBalls, numBalls, outputBox);
                    });
                    Thread.sleep(500); // Pause between ball drops
                }
            } catch (NumberFormatException | InterruptedException e) { // Handle errors
                e.printStackTrace();
            }
        });

        // Create scene and stage
        Scene scene = new Scene(root, MACHINE_WIDTH + 300, MACHINE_HEIGHT + 200); // Main application window
        primaryStage.setTitle("Bean Machine | Galton Game"); // Set window title
        primaryStage.setScene(scene); // Add scene to the stage
        primaryStage.show(); // Display the stage
    }

    // Method to simulate the ball dropping
    private void dropBall(Pane pane, double startX, double startY, int rows, double slotWidth, double bottomY, int[] slots, int[] finishedBalls, int totalBalls, VBox outputBox) {
        Circle ball = new Circle(startX, startY, BALL_RADIUS); // Create a ball
        ball.setFill(Color.BLUE); // Ball color
        pane.getChildren().add(ball); // Add ball to the pane

        Polyline path = new Polyline(); // Path for the ball
        path.getPoints().addAll(startX, startY); // Add starting point to the path

        Random random = new Random(); // Random generator for ball movement
        double[] currentX = {startX}; // Ball's current X position
        double currentY = startY; // Ball's current Y position
        int[] slotIndex = {0}; // Slot index tracker

        // Simulate ball's movement through pegs
        for (int i = 0; i < rows; i++) {
            currentY += 30; // Move down a row
            if (random.nextBoolean()) {
                currentX[0] -= slotWidth / 2; // Move left
            } else {
                currentX[0] += slotWidth / 2; // Move right
                slotIndex[0]++; // Increment slot index
            }
            path.getPoints().addAll(currentX[0], currentY); // Add movement to path
        }

        // Add ball to slot
        slots[slotIndex[0]]++; // Increment slot count

        // Correct ball's position in the slot
        double finalX = currentX[0];
        double finalY = bottomY - (slots[slotIndex[0]] - 1) * (BALL_RADIUS * 1); // Stack balls
        path.getPoints().addAll(finalX, finalY); // Final path adjustment

        // Animate ball dropping
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(ball); // Set ball for animation
        pathTransition.setPath(path); // Set path for animation
        pathTransition.setDuration(Duration.seconds(3)); // Animation duration
        pathTransition.setOnFinished(e -> {
            ball.setCenterX(finalX);
            ball.setCenterY(finalY);

            // Check if all balls have finished dropping
            synchronized (finishedBalls) {
                finishedBalls[0]++;
                if (finishedBalls[0] == totalBalls) { // When all balls are done
                    // Display results
                    javafx.application.Platform.runLater(() -> {
                        for (int i = 0; i < slots.length; i++) {
                            Text slotDisplay = new Text("Slot " + i + ": " + slots[i] + " balls");
                            slotDisplay.setFill(Color.DARKBLUE);
                            outputBox.getChildren().add(slotDisplay);
                        }
                    });
                }
            }
        });
        pathTransition.play(); // Start animation
    }

    public static void main(String[] args) {
        launch(args); // Launch JavaFX application
    }
}
