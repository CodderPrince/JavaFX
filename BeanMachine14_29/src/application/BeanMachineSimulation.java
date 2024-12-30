package application;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class BeanMachineSimulation extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

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

        pane.getChildren().addAll(leftLine, rightLine, bottomLine);

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
                pane.getChildren().add(peg);
            }
        }

        // Draw vertical slots at the bottom
        for (int i = 0; i <= ROWS + 1; i++) {
            double x = startX - (ROWS * SLOT_WIDTH) / 2.0 + i * SLOT_WIDTH;
            Line slot = new Line(x, MACHINE_HEIGHT - 100, x, MACHINE_HEIGHT);
            pane.getChildren().add(slot);
        }

        // Function to simulate a ball falling
        Runnable dropBall = () -> {
            Circle ball = new Circle(startX, startY - BALL_RADIUS, BALL_RADIUS);
            ball.setFill(Color.BLUE);
            pane.getChildren().add(ball);

            // Create the path for the ball to follow
            Polyline path = new Polyline();
            path.getPoints().addAll(startX, startY); // Start at the top
            Random random = new Random();

            double currentX = startX;
            double currentY = startY;

            for (int row = 0; row < ROWS; row++) {
                currentY += 30; // Move down one row
                if (random.nextBoolean()) {
                    currentX -= SLOT_WIDTH / 2.0; // Move left
                } else {
                    currentX += SLOT_WIDTH / 2.0; // Move right
                }
                path.getPoints().addAll(currentX, currentY);
            }

            // Determine the slot where the ball lands
            double finalX = currentX;
            double finalY = MACHINE_HEIGHT - BALL_RADIUS - 50; // Bottom of the slot
            path.getPoints().addAll(finalX, finalY);

            // Animate the ball along the path
            PathTransition pathTransition = new PathTransition();
            pathTransition.setNode(ball);
            pathTransition.setDuration(Duration.seconds(2));
            pathTransition.setPath(path);
            pathTransition.setOnFinished(event -> {
                ball.setCenterX(finalX);
                ball.setCenterY(finalY);
            });
            pathTransition.play();
        };

        // Add balls dropping periodically
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) { // Drop 10 balls
                    Thread.sleep(1000); // 1-second interval
                    javafx.application.Platform.runLater(dropBall);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Add the scene and stage
        Scene scene = new Scene(pane, MACHINE_WIDTH, MACHINE_HEIGHT);
        primaryStage.setTitle("Bean Machine Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
