/*
 * Bean Machine Image Show
 * Ex: 14.29
 */

package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class BeanMachine extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // Define constants for the bean machine
        final double MACHINE_WIDTH = 300;
        final double MACHINE_HEIGHT = 400;
        final double PEG_RADIUS = 5;
        final int ROWS = 7;

        // Starting position of the top of the machine
        double startX = MACHINE_WIDTH / 2;
        double startY = 50;

        // Draw the triangle outline for the machine
        Line leftLine = new Line(startX, startY, startX - MACHINE_WIDTH / 2, MACHINE_HEIGHT - 100);
        Line rightLine = new Line(startX, startY, startX + MACHINE_WIDTH / 2, MACHINE_HEIGHT - 100);
        Line bottomLine = new Line(startX - MACHINE_WIDTH / 2, MACHINE_HEIGHT - 100, startX + MACHINE_WIDTH / 2, MACHINE_HEIGHT - 100);

        pane.getChildren().addAll(leftLine, rightLine, bottomLine);

        // Draw pegs in a triangular formation
        for (int row = 0; row < ROWS; row++) {
            for (int i = 0; i <= row; i++) {
                double x = startX - row * 20 / 2.0 + i * 20; // Center the pegs in each row
                double y = startY + row * 30; // Space rows vertically
                Circle peg = new Circle(x, y, PEG_RADIUS);
                peg.setFill(Color.BLACK);
                pane.getChildren().add(peg);
            }
        }

        // Draw vertical slots at the bottom
        for (int i = 0; i <= ROWS + 1; i++) {
            double x = startX - (ROWS * 20) / 2.0 + i * 20;
            Line slot = new Line(x, MACHINE_HEIGHT - 100, x, MACHINE_HEIGHT);
            pane.getChildren().add(slot);
        }

        // Add the scene and stage
        Scene scene = new Scene(pane, MACHINE_WIDTH, MACHINE_HEIGHT);
        primaryStage.setTitle("Bean Machine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
