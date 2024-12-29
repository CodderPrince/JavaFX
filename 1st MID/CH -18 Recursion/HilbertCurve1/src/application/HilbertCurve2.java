package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HilbertCurve2 extends Application {

    private static final int SIZE = 600; // Canvas size
    private static final int PADDING = 20; // Padding around the curve

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(SIZE, SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        TextField orderInput = new TextField();
        orderInput.setPromptText("Enter an order:");
        
        orderInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                int order = 1;
                try {
                    order = Integer.parseInt(orderInput.getText());
                } catch (NumberFormatException ignored) {
                }
                gc.clearRect(0, 0, SIZE, SIZE);
                drawHilbert(gc, order, SIZE - 2 * PADDING, PADDING, PADDING);
            }
        });

        root.setCenter(canvas);
        root.setBottom(orderInput);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Hilbert Curve");
        primaryStage.show();
    }

    private void drawHilbert(GraphicsContext gc, int order, double size, double x, double y) {
        if (order == 0) return;

        size /= 2;

        // Recursive draw
        drawHilbert(gc, order - 1, size, x, y);
        drawLine(gc, x, y + size, x, y + size + size);
        x += size;

        drawHilbert(gc, order - 1, size, x, y + size);
        drawLine(gc, x - size, y + size + size, x + size, y + size + size);
        y += size;

        drawHilbert(gc, order - 1, size, x, y);
        drawLine(gc, x, y + size, x, y + size - size);
        x -= size;

        drawHilbert(gc, order - 1, size, x, y - size);
    }

    private void drawLine(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        gc.strokeLine(x1, y1, x2, y2);
    }
}
