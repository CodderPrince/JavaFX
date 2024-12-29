package application;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class BeanMachine5 extends Application {

    private static final int BALL_RADIUS = 10;
    private static final int PINS = 7;
    private static final int SLOT_WIDTH = 80; // Adjust slot width here
    private static final int SLOT_COUNT = PINS; // Total slots (0 to 6)
    private int[] slots = new int[SLOT_COUNT];
    private Label[] slotLabels = new Label[SLOT_COUNT];
    private int ballCount = 10; // Default number of balls

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        VBox controlBox = new VBox(10); // VBox for user input
        VBox slotInfoBox = new VBox(10); // VBox to display slot info
        controlBox.setLayoutX(20); // Position the input box on the left
        controlBox.setLayoutY(20);
        slotInfoBox.setLayoutX(700); // Position the slot info on the right
        slotInfoBox.setLayoutY(100);

        // Add gradient background to the pane
        Background gradientBackground = new Background(new BackgroundFill(
                new LinearGradient(0, 0, 1, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                        new Stop(0, Color.WHITE),
                        new Stop(1, Color.WHITE)),
                CornerRadii.EMPTY,
                null));
        pane.setBackground(gradientBackground);

        // User input for the number of balls
        TextField ballInputField = new TextField();
        ballInputField.setPromptText("Enter number of balls");
        ballInputField.setStyle("-fx-background-color: lightpink; -fx-prompt-text-fill: darkblue; -fx-font-size: 14px;");

        Button startButton = new Button("Start Game");
        controlBox.getChildren().addAll(new Label("Bean Machine Game"), ballInputField, startButton);

        // Initialize slot labels
        for (int i = 0; i < SLOT_COUNT; i++) {
            slotLabels[i] = new Label("Slot " + i + ": 0 balls");
            slotLabels[i].setTextFill(Color.DARKBLUE);
            slotInfoBox.getChildren().add(slotLabels[i]);
        }

        // Draw the machine
        drawMachine(pane);

        startButton.setOnAction(e -> {
            // Reset slots and remove balls only
            pane.getChildren().removeIf(node -> node instanceof Circle && "ball".equals(node.getId()));
            for (int i = 0; i < SLOT_COUNT; i++) {
                slots[i] = 0;
                slotLabels[i].setText("Slot " + i + ": 0 balls");
            }

            // Parse the user input
            try {
                ballCount = Integer.parseInt(ballInputField.getText());
                if (ballCount <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                ballInputField.setText(""); // Clear invalid input
                ballInputField.setPromptText("Please enter a valid number!");
                return;
            }

            // Drop the balls
            dropBalls(pane);
        });

        pane.getChildren().addAll(controlBox, slotInfoBox); // Add both VBoxes to the main pane

        Scene scene = new Scene(pane, 900, 700); // Adjusted size for better viewing
        primaryStage.setTitle("Bean Machine Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawMachine(Pane pane) {
        // Center alignment base for the whole machine
        int paneCenterX = 450; // Assuming pane width of 900
        int machineWidth = SLOT_WIDTH * SLOT_COUNT; // Total width of the slots
        int slotStartX = paneCenterX - machineWidth / 2; // Start slot from the center

        // Draw the pins
        int rows = PINS;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= i; j++) {
                Circle pin = new Circle(paneCenterX - i * (SLOT_WIDTH / 2) + j * SLOT_WIDTH, 150 + i * 50, 5, Color.BLACK);
                pane.getChildren().add(pin);
            }
        }

        // Draw slots
        int slotTopY = 500;    // Top of the slot
        int slotBottomY = 650; // Bottom of the slot

        for (int i = 0; i <= SLOT_COUNT; i++) { // Draw slots from 0 to SLOT_COUNT
            Line slot = new Line(slotStartX + i * SLOT_WIDTH, slotTopY, slotStartX + i * SLOT_WIDTH, slotBottomY);
            pane.getChildren().add(slot);
        }

        // Base for slots
        Rectangle base = new Rectangle(slotStartX, slotBottomY, SLOT_WIDTH * SLOT_COUNT, 20);
        base.setFill(Color.GRAY);
        pane.getChildren().add(base);
    }


    private void dropBalls(Pane pane) {
        Random random = new Random();

        for (int i = 0; i < ballCount; i++) {
            Circle ball = new Circle(450, 100, BALL_RADIUS); // Start from the top center
            ball.setFill(randomColor());
            ball.setId("ball"); // Assign a unique ID to identify balls
            pane.getChildren().add(ball);

            int position = simulateBallPath();
            slots[position]++;

            // Calculate slot center
            int paneCenterX = 450; // Center of the pane
            int machineWidth = SLOT_WIDTH * SLOT_COUNT; // Total width of the slots
            int slotStartX = paneCenterX - machineWidth / 2; // Start position of the first slot
            double slotCenterX = slotStartX + (position + 0.5) * SLOT_WIDTH; // Center of the target slot

            // Set the ball's final position
            double deltaX = slotCenterX - 450; // Adjusted X translation relative to initial position
            double deltaY = 400 + slots[position] * 20; // Adjusted Y translation (stack balls in each slot)

            TranslateTransition transition = new TranslateTransition(Duration.seconds(3), ball);
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



    private void updateSlotLabels() {
        for (int i = 0; i < SLOT_COUNT; i++) { // Only update slots 0 to 6
            slotLabels[i].setText("Slot " + i + ": " + slots[i] + " balls");
        }
    }

    private int simulateBallPath() {
        Random random = new Random();
        int position = 0;
        for (int i = 0; i < SLOT_COUNT - 1; i++) { // Simulate ball path for 6 decisions
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
