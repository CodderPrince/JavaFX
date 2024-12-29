package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HilbertCurve1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the main pane
        HilbertPane hilbertPane = new HilbertPane();

        // Input field for order
        TextField inputField = new TextField("1");
        inputField.setPromptText("Enter order");
        inputField.setOnAction(e -> {
            try {
                int order = Integer.parseInt(inputField.getText());
                if (order < 0) {
                    inputField.setText("0");
                    order = 0;
                }
                hilbertPane.setOrder(order);
            } catch (NumberFormatException ex) {
                inputField.setText("0");
                hilbertPane.setOrder(0);
            }
        });

        BorderPane root = new BorderPane();
        root.setTop(inputField);
        root.setCenter(hilbertPane);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Hilbert Curve Drawer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Custom Canvas to Draw Hilbert Curve
    static class HilbertPane extends Canvas {
        private int order = 1;

        public HilbertPane() {
            setWidth(600);
            setHeight(600);
            drawCurve();
        }

        public void setOrder(int order) {
            this.order = order;
            drawCurve();
        }

        private void drawCurve() {
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());
            gc.setStroke(Color.BLACK);

            double canvasSize = Math.min(getWidth(), getHeight()) - 40; // Margin of 20px
            double step = canvasSize / Math.pow(2, order);

            // Start position at the center
            double startX = 20;
            double startY = 20;

            drawHilbert(gc, order, startX, startY, step, 0);
        }

        private void drawHilbert(GraphicsContext gc, int order, double x, double y, double step, int direction) {
            if (order == 0) return;

            switch (direction) {
                case 0: // Facing up
                    drawHilbert(gc, order - 1, x, y, step, 3);
                    drawLine(gc, x, y, x, y + step);
                    y += step;
                    drawHilbert(gc, order - 1, x, y, step, 0);
                    drawLine(gc, x, y, x + step, y);
                    x += step;
                    drawHilbert(gc, order - 1, x, y, step, 0);
                    drawLine(gc, x, y, x, y - step);
                    y -= step;
                    drawHilbert(gc, order - 1, x, y, step, 1);
                    break;
                case 1: // Facing down
                    drawHilbert(gc, order - 1, x, y, step, 0);
                    drawLine(gc, x, y, x, y - step);
                    y -= step;
                    drawHilbert(gc, order - 1, x, y, step, 1);
                    drawLine(gc, x, y, x - step, y);
                    x -= step;
                    drawHilbert(gc, order - 1, x, y, step, 1);
                    drawLine(gc, x, y, x, y + step);
                    y += step;
                    drawHilbert(gc, order - 1, x, y, step, 2);
                    break;
                case 2: // Facing left
                    drawHilbert(gc, order - 1, x, y, step, 1);
                    drawLine(gc, x, y, x - step, y);
                    x -= step;
                    drawHilbert(gc, order - 1, x, y, step, 2);
                    drawLine(gc, x, y, x, y - step);
                    y -= step;
                    drawHilbert(gc, order - 1, x, y, step, 2);
                    drawLine(gc, x, y, x + step, y);
                    x += step;
                    drawHilbert(gc, order - 1, x, y, step, 3);
                    break;
                case 3: // Facing right
                    drawHilbert(gc, order - 1, x, y, step, 2);
                    drawLine(gc, x, y, x + step, y);
                    x += step;
                    drawHilbert(gc, order - 1, x, y, step, 3);
                    drawLine(gc, x, y, x, y + step);
                    y += step;
                    drawHilbert(gc, order - 1, x, y, step, 3);
                    drawLine(gc, x, y, x - step, y);
                    x -= step;
                    drawHilbert(gc, order - 1, x, y, step, 0);
                    break;
            }
        }


        private void drawLine(GraphicsContext gc, double x1, double y1, double x2, double y2) {
            gc.strokeLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
