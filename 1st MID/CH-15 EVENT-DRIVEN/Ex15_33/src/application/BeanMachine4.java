package application;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class BeanMachine4 extends Application {

    private static final int BALL_RADIUS = 10;
    private static final int PINS = 7;
    private static final int SLOT_WIDTH = 80; // Adjust slot width here
    private int[] slots = new int[PINS + 1];
    private Label[] slotLabels = new Label[PINS + 1];
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

        // User input for the number of balls
        TextField ballInputField = new TextField();
        ballInputField.setPromptText("Enter number of balls");
        Button startButton = new Button("Start Game");

        controlBox.getChildren().addAll(new Label("Bean Machine Game"), ballInputField, startButton);

        // Initialize slot labels
        for (int i = 0; i <= PINS; i++) {
            slotLabels[i] = new Label("Slot " + i + ": 0 balls");
            slotLabels[i].setTextFill(Color.DARKBLUE);
            slotInfoBox.getChildren().add(slotLabels[i]);
        }

        // Draw the machine
        drawMachine(pane);

        startButton.setOnAction(e -> {
            // Reset slots and remove balls only
            pane.getChildren().removeIf(node -> node instanceof Circle && "ball".equals(node.getId()));
            for (int i = 0; i <= PINS; i++) {
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
        // Draw the pins
        int rows = PINS;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= i; j++) {
                Circle pin = new Circle(400 - i * (SLOT_WIDTH / 2) + j * SLOT_WIDTH, 150 + i * 50, 5, Color.BLACK);
                pane.getChildren().add(pin);
            }
        }

        // Adjusted slot drawing
        int slotStartX = 105;  // Centered X starting point
        int slotTopY = 500;    // Top of the slot
        int slotBottomY = 650; // Bottom of the slot

        for (int i = 0; i <= PINS; i++) {
            Line slot = new Line(slotStartX + i * SLOT_WIDTH, slotTopY, slotStartX + i * SLOT_WIDTH, slotBottomY);
            pane.getChildren().add(slot);
        }

        // Base for slots
        Rectangle base = new Rectangle(slotStartX, slotBottomY, SLOT_WIDTH * (PINS), 20);
        base.setFill(Color.GRAY);
        pane.getChildren().add(base);
    }

    private void dropBalls(Pane pane) {
        Random random = new Random();

        for (int i = 0; i < ballCount; i++) {
            Circle ball = new Circle(400, 100, BALL_RADIUS); // Start from the top center
            ball.setFill(randomColor());
            ball.setId("ball"); // Assign a unique ID to identify balls
            pane.getChildren().add(ball);

            int position = simulateBallPath();
            slots[position]++;

            // Calculate slot center
            double slotCenterX = 105 + (position + 0.5) * SLOT_WIDTH; // Adjusted for slot centering
            double deltaX = slotCenterX - 400; // Adjusted X translation relative to initial position
            double deltaY = 400 + slots[position] * 20; // Adjusted Y translation

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
        for (int i = 0; i <= PINS; i++) {
            slotLabels[i].setText("Slot " + i + ": " + slots[i] + " balls");
        }
    }

    private int simulateBallPath() {
        Random random = new Random();
        int position = 0;
        for (int i = 0; i < PINS; i++) {
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
