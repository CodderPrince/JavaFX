package application;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class BeanMachine extends Application {

    private static final int BALL_RADIUS = 10;
    private static final int PINS = 7;
    private static final int BALL_COUNT = 10;
    private int[] slots = new int[PINS + 1];

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // Draw the machine
        drawMachine(pane);

        // Drop the balls
        dropBalls(pane);

        Scene scene = new Scene(pane, 500, 600); // Adjusted size
        primaryStage.setTitle("Bean Machine Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawMachine(Pane pane) {
        // Draw the pins
        int rows = PINS;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= i; j++) {
                Circle pin = new Circle(250 - i * 20 + j * 40, 150 + i * 50, 5, Color.BLACK);
                pane.getChildren().add(pin);
            }
        }

        // Adjusted slot drawing
        int slotStartX = 110;  // X starting point
        int slotTopY = 440;    // Top of the slot
        int slotBottomY = 570; // Bottom of the slot

        for (int i = 0; i <= PINS; i++) {
            Line slot = new Line(slotStartX + i * 40, slotTopY, slotStartX + i * 40, slotBottomY);
            pane.getChildren().add(slot);
        }

        Rectangle base = new Rectangle(slotStartX, slotBottomY, 40 * (PINS + 1), 20);
        base.setFill(Color.GRAY);
        pane.getChildren().add(base);
    }


    private void dropBalls(Pane pane) {
        Random random = new Random();
        int ballDropHeight = 350; // Adjust this to match the new slot height

        for (int i = 0; i < BALL_COUNT; i++) {
            Circle ball = new Circle(250, 120, BALL_RADIUS);
            ball.setFill(randomColor());
            pane.getChildren().add(ball);

            int position = simulateBallPath();
            slots[position]++;

            TranslateTransition transition = new TranslateTransition(Duration.seconds(3), ball);
            transition.setByY(ballDropHeight + slots[position] * 20 - 20); // Adjusted to match slot height
            transition.setByX((position - PINS / 2.0) * 40); // Keep aligned with slot width
            transition.setOnFinished(e -> ball.toFront());
            transition.play();
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
