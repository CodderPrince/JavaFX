package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BeanMachineSimulation extends Application {

    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 400;
    private static final int BALL_RADIUS = 5;
    private static final int SLOT_WIDTH = 50;

    private int numBalls = 10;
    private int numSlots = 8;
    private int[] slots;

    private List<String> ballPaths = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        slots = new int[numSlots];

        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawBeanMachine(gc);
        simulateBalls(gc);

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Bean Machine Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawBeanMachine(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        // Draw slots
        for (int i = 0; i < numSlots; i++) {
            double x = (i + 1) * SLOT_WIDTH;
            double y = CANVAS_HEIGHT - 20;
            gc.strokeRect(x, y, SLOT_WIDTH, 20);
        }

        // Draw nails
        for (int row = 0; row < numSlots - 1; row++) {
            for (int col = 0; col <= row; col++) {
                double x = (CANVAS_WIDTH / 2.0) + (col - row / 2.0) * SLOT_WIDTH / 2.0;
                double y = 50 + row * 30;
                gc.fillOval(x, y, 5, 5);
            }
        }
    }

    private void simulateBalls(GraphicsContext gc) {
        Random random = new Random();

        for (int ball = 0; ball < numBalls; ball++) {
            int position = 0;
            StringBuilder path = new StringBuilder();

            double x = CANVAS_WIDTH / 2.0;
            double y = 20;

            for (int row = 0; row < numSlots - 1; row++) {
                gc.setFill(Color.RED);
                gc.fillOval(x, y, BALL_RADIUS, BALL_RADIUS);

                boolean goRight = random.nextBoolean();
                path.append(goRight ? "R" : "L");
                position += goRight ? 1 : 0;

                x += goRight ? SLOT_WIDTH / 4.0 : -SLOT_WIDTH / 4.0;
                y += 30;

                pause(100);
            }

            slots[position]++;
            ballPaths.add(path.toString());

            drawHistogram(gc);
        }
    }

    private void drawHistogram(GraphicsContext gc) {
        gc.setFill(Color.BLUE);

        for (int i = 0; i < numSlots; i++) {
            double x = (i + 1) * SLOT_WIDTH + SLOT_WIDTH / 4.0;
            double y = CANVAS_HEIGHT - 40 - slots[i] * 10;
            gc.fillOval(x, y, SLOT_WIDTH / 2.0, 10);
        }
    }

    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
