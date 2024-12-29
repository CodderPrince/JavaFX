package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HilbertCurve3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int level = 5; // Adjust the level for complexity
        double size = 600; // The size of the square containing the curve
        double startX = 100; // Starting x-coordinate
        double startY = 100; // Starting y-coordinate

        gc.setStroke(Color.RED);
        gc.setLineWidth(1.0);

        drawHilbertCurve(gc, level, startX, startY, size, 0);
        root.getChildren().add(canvas);

        primaryStage.setTitle("Hilbert Curve");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    private void drawHilbertCurve(GraphicsContext gc, int level, double x, double y, double size, int rotation) {
        if (level == 0) {
            return;
        }

        double half = size / 2;

        switch (rotation) {
            case 0:
                drawHilbertCurve(gc, level - 1, x, y, half, 1);
                drawLine(gc, x, y + half, x + half, y + half);
                drawHilbertCurve(gc, level - 1, x, y + half, half, 0);
                drawLine(gc, x + half, y + half, x + half, y);
                drawHilbertCurve(gc, level - 1, x + half, y, half, 0);
                drawLine(gc, x + half, y, x + size, y);
                drawHilbertCurve(gc, level - 1, x + size - half, y, half, 3);
                break;
            case 1:
                drawHilbertCurve(gc, level - 1, x, y, half, 2);
                drawLine(gc, x + half, y, x + size, y);
                drawHilbertCurve(gc, level - 1, x + half, y, half, 1);
                drawLine(gc, x + size, y + half, x + size, y + size);
                drawHilbertCurve(gc, level - 1, x + size - half, y + half, half, 1);
                drawLine(gc, x + half, y + size, x, y + size);
                drawHilbertCurve(gc, level - 1, x, y + half, half, 0);
                break;
            case 2:
                drawHilbertCurve(gc, level - 1, x, y + size - half, half, 3);
                drawLine(gc, x + half, y + size, x + size, y + size);
                drawHilbertCurve(gc, level - 1, x + size - half, y + size - half, half, 2);
                drawLine(gc, x + size, y + half, x + size, y);
                drawHilbertCurve(gc, level - 1, x + size - half, y, half, 2);
                drawLine(gc, x + half, y, x, y);
                drawHilbertCurve(gc, level - 1, x, y, half, 1);
                break;
            case 3:
                drawHilbertCurve(gc, level - 1, x + size - half, y, half, 0);
                drawLine(gc, x + size, y + half, x + size, y + size);
                drawHilbertCurve(gc, level - 1, x + size - half, y + size - half, half, 3);
                drawLine(gc, x + half, y + size, x, y + size);
                drawHilbertCurve(gc, level - 1, x, y + size - half, half, 3);
                drawLine(gc, x, y + half, x, y);
                drawHilbertCurve(gc, level - 1, x, y, half, 2);
                break;
        }
    }

    private void drawLine(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        gc.strokeLine(x1, y1, x2, y2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
