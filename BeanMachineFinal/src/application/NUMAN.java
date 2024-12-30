package application;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class NUMAN extends Application {

    private static final double WIDTH = 500;
    private static final double HEIGHT = 700;
    private static final double PEG_RADIUS = 5;
    private static final double BALL_RADIUS = 8;
    private static final int ROWS = 7;
    private static final double GAP_BASKET = 30;

    private Pane pane;
    private ArrayList<Circle> pegs;

    @Override
    public void start(Stage primaryStage) {
        pane = new Pane();

        // Add a beautiful background
        addBeautifulBackground();

        // Draw the machine structure
        drawMachine();

        // Add a styled reset button
        Button resetButton = new Button("Reset");
        resetButton.setLayoutX(WIDTH - 100);
        resetButton.setLayoutY(20);
        resetButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px; -fx-border-radius: 10; -fx-background-radius: 10;");
        resetButton.setOnAction(e -> resetMachine());

        // Add a styled drop ball button
        Button dropButton = new Button("Drop Ball");
        dropButton.setLayoutX(WIDTH - 100);
        dropButton.setLayoutY(60);
        dropButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-size: 16px; -fx-border-radius: 10; -fx-background-radius: 10;");
        dropButton.setOnAction(e -> dropNewBall());

        // Add the buttons to the pane
        pane.getChildren().addAll(resetButton, dropButton);

        // Set up the scene
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setTitle("Realistic Bean Machine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addBeautifulBackground() {
        // Add a radial gradient background
        Rectangle background = new Rectangle(0, 0, WIDTH, HEIGHT);
        background.setFill(new RadialGradient(
                0, 0.5, WIDTH / 2, HEIGHT / 2, WIDTH,
                false, null,
                new Stop(0, Color.LIGHTCYAN),
                new Stop(0.5, Color.LIGHTPINK),
                new Stop(1, Color.DARKSLATEBLUE)
        ));
        pane.getChildren().add(background);

        // Add decorative translucent circles
        for (int i = 0; i < 10; i++) {
            double x = Math.random() * WIDTH;
            double y = Math.random() * HEIGHT;
            double radius = 50 + Math.random() * 100;
            Circle circle = new Circle(x, y, radius, Color.color(Math.random(), Math.random(), Math.random(), 0.2));
            pane.getChildren().add(circle);
        }

        // Add diagonal stripes
        for (int i = 0; i < WIDTH; i += 50) {
            Rectangle stripe = new Rectangle(i, 0, 10, HEIGHT);
            stripe.setFill(Color.color(0.9, 0.9, 0.9, 0.1));
            stripe.setRotate(45);
            pane.getChildren().add(stripe);
        }
    }

    private void drawMachine() {
        pane.getChildren().removeIf(node -> node instanceof Line || node instanceof Circle);

        // Draw the colorful walls
        Line leftWall = new Line(WIDTH / 2 - 150, HEIGHT / 4, WIDTH / 2 - 150, HEIGHT - 100);
        Line rightWall = new Line(WIDTH / 2 + 150, HEIGHT / 4, WIDTH / 2 + 150, HEIGHT - 100);
        leftWall.setStroke(LinearGradient.valueOf("to bottom, #8e44ad, #3498db"));
        leftWall.setStrokeWidth(10);
        rightWall.setStroke(LinearGradient.valueOf("to bottom, #8e44ad, #3498db"));
        rightWall.setStrokeWidth(10);

        Line bottom = new Line(WIDTH / 2 - 150, HEIGHT - 50, WIDTH / 2 + 150, HEIGHT - 50);
        bottom.setStroke(Color.DARKRED);
        bottom.setStrokeWidth(10);

        pane.getChildren().addAll(leftWall, rightWall, bottom);

        // Draw the colorful pegs
        double startX = WIDTH / 2;
        double startY = HEIGHT / 4 + 50;
        double gapX = 25;
        double gapY = 60;

        pegs = new ArrayList<>();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= row; col++) {
                double x = startX - row * gapX / 2 + col * gapX;
                double y = startY + row * gapY;

                Circle peg = new Circle(x, y, PEG_RADIUS);
                peg.setFill(Color.color(Math.random(), Math.random(), Math.random())); // Random peg color
                pegs.add(peg);
                pane.getChildren().add(peg);
            }
        }

        // Draw the vertical slots for balls at the bottom
        int slots = ROWS + 1;
        double slotWidth = (2 * 150) / slots;
        for (int i = 0; i <= slots; i++) {
            double x = WIDTH / 2 - 150 + i * slotWidth;
            Line slot = new Line(x, HEIGHT - 50 - GAP_BASKET, x, HEIGHT - 50);
            slot.setStroke(Color.DARKGREEN);
            slot.setStrokeWidth(4);
            pane.getChildren().add(slot);
        }
    }

    private Circle createBall(double x, double y) {
        Circle ball = new Circle(x, y, BALL_RADIUS);
        ball.setFill(Color.color(Math.random(), Math.random(), Math.random())); // Random color for balls
        ball.setStroke(Color.BLACK); // Outline for better visibility
        return ball;
    }

    private void dropBall(Circle ball) {
        Path path = new Path();
        path.getElements().add(new MoveTo(ball.getCenterX(), ball.getCenterY()));

        Random random = new Random();

        for (Circle peg : pegs) {
            double bounceDirection = random.nextBoolean() ? -15 : 15;
            path.getElements().add(new QuadCurveTo(
                    peg.getCenterX(), peg.getCenterY(),
                    peg.getCenterX() + bounceDirection, peg.getCenterY() + 20
            ));
        }

        int slots = ROWS + 1;
        double slotWidth = (2 * 150) / slots;
        double finalX = WIDTH / 2 - 150 + random.nextInt(slots) * slotWidth + slotWidth / 2;
        double finalY = HEIGHT - 50 - (BALL_RADIUS / 2);

        double controlY = finalY - 80;
        path.getElements().add(new QuadCurveTo(
                (finalX + ball.getCenterX()) / 2, controlY,
                finalX, finalY
        ));

        // Slowing the ball fall by increasing the duration to 6 seconds
        PathTransition pathTransition = new PathTransition(Duration.seconds(6), path, ball);
        pathTransition.setCycleCount(1);
        pathTransition.setOnFinished(e -> {
            Circle newBall = createBall(WIDTH / 2, HEIGHT / 4 - 20);
            pane.getChildren().add(newBall);
            dropBall(newBall);
        });
        pathTransition.play();
    }

    // Method to drop a new ball when the drop button is pressed
    private void dropNewBall() {
        Circle ball = createBall(WIDTH / 2, HEIGHT / 4 - 20);
        pane.getChildren().add(ball);
        dropBall(ball);
    }

    private void resetMachine() {
        pane.getChildren().clear();
        addBeautifulBackground();
        drawMachine();
    }

    public static void main(String[] args) {
        launch(args);
    }
}