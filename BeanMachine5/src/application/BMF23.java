/************************************
 * Galton Game | Bean Machine Game
 * Exercise: 7.21, 14.29, 15.33
 * Language: JavaFx
 * Developed: Md. An Nahian Prince
 * ID: 12105007
 ************************************/

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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class BMF23 extends Application {

    private static final int BALL_RADIUS = 10;
    private static final int PINS = 7;
    private static final int SLOT_WIDTH = 80;
    private static final int SLOT_COUNT = 8; // Updated to 8 for Slot 1 to Slot 7
    private int[] slots = new int[SLOT_COUNT];
    private Label[] slotLabels = new Label[SLOT_COUNT];
    private int ballCount = 10;
    private ArrayList<Circle> pegs; // Store pegs for reuse

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, peachpuff, ivory);");

        VBox controlBox = new VBox(10);
        VBox slotInfoBox = new VBox(10);
        controlBox.setLayoutX(20);
        controlBox.setLayoutY(20);
        slotInfoBox.setLayoutX(700);
        slotInfoBox.setLayoutY(100);

        TextField ballInputField = new TextField();
        ballInputField.setPromptText("Enter number of balls here:");
        ballInputField.setStyle("-fx-background-color: lightblue;" + "-fx-text-fill: black;" + "-fx-prompt-text-fill: blue;"
                + "-fx-font-family: 'Arial'; " + "-fx-font-size: 16px;" + "-fx-font-weight: bold;");
        ballInputField.setPrefWidth(222);

        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-background-color: darkgreen; " + "-fx-text-fill: white; "
                + "-fx-font-family: 'Arial'; " + "-fx-font-size: 18px; " + "-fx-font-weight: bold;");

        controlBox.getChildren().addAll(new Label("Galton Game | Developed by PRINCE"), ballInputField, startButton);

        for (int i = 1; i < SLOT_COUNT; i++) { // Start from Slot 1
            slotLabels[i] = new Label("Slot " + i + ": 0 balls");
            slotLabels[i].setStyle("-fx-text-fill: blue;" + "-fx-font-size: 20px;" + "-fx-font-family: 'Verdana';"
                    + "-fx-font-weight: bold;");
            slotInfoBox.getChildren().add(slotLabels[i]);
        }

        pegs = drawMachine(pane);

        startButton.setOnAction(e -> {
            pane.getChildren().removeIf(node -> node instanceof Circle && !pegs.contains(node));

            for (int i = 1; i < SLOT_COUNT; i++) { // Start from Slot 1
                slots[i] = 0;
                slotLabels[i].setText("Slot " + i + ": 0 balls");
            }

            try {
                ballCount = Integer.parseInt(ballInputField.getText());
                if (ballCount <= 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                ballInputField.setText("");
                ballInputField.setPromptText("Please enter a valid number!");
                ballInputField.setStyle(
                        "-fx-background-color: lightblue;" + "-fx-text-fill: black;" + "-fx-prompt-text-fill: darkred;"
                                + "-fx-font-family: 'Arial'; " + "-fx-font-size: 15px;" + "-fx-font-weight: bold;");
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

        for (int i = 0; i < SLOT_COUNT; i++) {
            // Create a Line for the slot
            Line slot = new Line(slotStartX + i * SLOT_WIDTH, slotTopY, slotStartX + i * SLOT_WIDTH, slotBottomY);

            // Set the slot line width
            slot.setStrokeWidth(18.0);

            // Set rounded line ends
            slot.setStrokeLineCap(StrokeLineCap.ROUND);

            // Generate a dark gradient color
            Color color1 = darkRandomColor();
            Color color2 = darkRandomColor();
            LinearGradient gradient = new LinearGradient(
                    0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0, color1), new Stop(1, color2)
            );
            slot.setStroke(gradient);

            // Add the slot to the pane
            pane.getChildren().add(slot);
        }

        



        double baseHeight = 20; // Height of the rectangle (unchanged)
        double additionalWidth = 15; // Additional width (10 on each side)
        double baseWidth = SLOT_WIDTH * (SLOT_COUNT - 1) + additionalWidth;

        // Adjust starting X to move the base left by half of the additional width
        Rectangle base = new Rectangle(slotStartX - additionalWidth / 2, slotBottomY, baseWidth, baseHeight);
        base.setArcWidth(15); // Rounded corners
        base.setArcHeight(15);

        // Generate a random gradient color for the base
        Color baseColor1 = darkRandomColor();
        Color baseColor2 = darkRandomColor();
        LinearGradient baseGradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, baseColor1), new Stop(1, baseColor2)
        );
        base.setFill(baseGradient);

        // Add the base to the pane
        pane.getChildren().add(base);




        for (int i = 1; i < SLOT_COUNT; i++) { // Start from Slot 1
            Label slotNumber = new Label("Slot " + i);
            slotNumber.setStyle("-fx-text-fill: darkgreen;" + "-fx-font-size: 14px;" + "-fx-font-family: 'Arial';"
                    + "-fx-font-weight: bold;");
            double slotCenterX = slotStartX + (i - 1 + 0.5) * SLOT_WIDTH;
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

            PauseTransition delay = new PauseTransition(Duration.seconds(i * 1.8));
            delay.setOnFinished(event -> {
                Circle ball = new Circle(450, 100, BALL_RADIUS);
                ball.setFill(randomColor());
                ball.setId("ball" + ballIndex);
                pane.getChildren().add(ball);

                Path path = new Path();
                path.getElements().add(new MoveTo(ball.getCenterX(), ball.getCenterY()));

                double currentX = ball.getCenterX();
                double currentY = ball.getCenterY();

                // Define boundary limits
                double minSlotX = 450 - (SLOT_WIDTH * (SLOT_COUNT - 1) / 2);
                double maxSlotX = 450 + (SLOT_WIDTH * (SLOT_COUNT - 1) / 2);

                for (Circle peg : pegs) {
                    double pegX = peg.getCenterX();
                    double pegY = peg.getCenterY();

                    // Calculate bounce direction
                    double bounceDirection = random.nextBoolean() ? -30 : 30;
                    double newX = currentX + bounceDirection;

                    // Correctly reflect the ball if it crosses the boundaries
                    if (newX < minSlotX) {
                        newX = minSlotX + (minSlotX - newX); // Reflect the ball's position
                    } else if (newX > maxSlotX) {
                        newX = maxSlotX - (newX - maxSlotX); // Reflect the ball's position
                    }

                    // Add the bouncing path
                    path.getElements().add(new QuadCurveTo(pegX, pegY, newX, pegY + 20));

                    currentX = newX; // Update ball position
                    currentY = pegY + 20; // Move downward
                }

                // Determine the final slot index
                final int[] finalSlotIndexHolder = {
                    Math.max(1, Math.min(
                        (int) ((currentX - minSlotX) / SLOT_WIDTH) + 1,
                        SLOT_COUNT
                    ))
                };

                int minBalls = findMinBalls();

                // Ensure the ball is dropped only into slots with minimum balls
                while (slots[finalSlotIndexHolder[0]] > minBalls) {
                    finalSlotIndexHolder[0] = findNextSlot(finalSlotIndexHolder[0], minBalls, minSlotX, maxSlotX);
                }

                double finalX = minSlotX + (finalSlotIndexHolder[0] - 1) * SLOT_WIDTH;
                path.getElements().add(new LineTo(finalX, 780 - BALL_RADIUS));

                PathTransition pathTransition = new PathTransition(Duration.seconds(9), path, ball);
                pathTransition.setCycleCount(1);
                pathTransition.setOnFinished(ev -> {
                    int finalSlotIndex = finalSlotIndexHolder[0]; // Retrieve from array

                    double finalY = 780 - slots[finalSlotIndex] * (BALL_RADIUS * 2 + 5);

                    ball.setTranslateX(finalX - ball.getCenterX());
                    ball.setTranslateY(finalY - ball.getCenterY());

                    slots[finalSlotIndex]++;
                    slotLabels[finalSlotIndex].setText("Slot " + finalSlotIndex + ": " + slots[finalSlotIndex] + " balls");
                });

                pathTransition.play();
            });

            delay.play();
        }
    }







    private int findMinBalls() {
        int min = slots[1];
        for (int i = 2; i < SLOT_COUNT; i++) {
            if (slots[i] < min) {
                min = slots[i];
            }
        }
        return min;
    }

    private int findNextSlot(int currentSlot, int minBalls, double minSlotX, double maxSlotX) {
        int left = currentSlot, right = currentSlot;

        while (true) {
            // Move left and wrap around if needed
            left = left > 1 ? left - 1 : 1; // Wrap around to Slot 1
            // Move right and wrap around if needed
            right = right < SLOT_COUNT - 1 ? right + 1 : SLOT_COUNT - 1; // Wrap around to Slot 7

            // Check both directions for slots with minimum balls
            if (slots[left] <= minBalls) {
                double leftX = 450 + (left - 1) * SLOT_WIDTH - (SLOT_WIDTH * (SLOT_COUNT - 1)) / 2;
                if (leftX >= minSlotX) {
                    return left;
                }
            }
            if (slots[right] <= minBalls) {
                double rightX = 450 + (right - 1) * SLOT_WIDTH - (SLOT_WIDTH * (SLOT_COUNT - 1)) / 2;
                if (rightX <= maxSlotX) {
                    return right;
                }
            }

            // Break condition: If no slots with `minBalls` are found
            if (left == 1 && right == SLOT_COUNT - 1) {
                break;
            }
        }

        // Fallback: Return the current slot if no other slot is found
        return currentSlot;
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
 // Add the darkRandomColor method here
    private Color darkRandomColor() {
        Random random = new Random();
        // Generate dark colors by scaling the RGB values down
        double red = random.nextDouble() * 0.5; // Scale between 0 and 0.5 for darker shades
        double green = random.nextDouble() * 0.5;
        double blue = random.nextDouble() * 0.5;
        return Color.color(red, green, blue);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
