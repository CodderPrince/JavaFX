package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class L14_16 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane
        Pane pane = new Pane();

        // Create rectangles and add to pane
        Rectangle r1 = new Rectangle(25, 10, 60, 30);
        r1.setStroke(Color.BLACK);
        r1.setFill(Color.WHITE);
        pane.getChildren().addAll(new Text(10, 27, "r1"), r1);  // Add label and rectangle

        Rectangle r2 = new Rectangle(25, 50, 60, 30);
        pane.getChildren().addAll(new Text(10, 67, "r2"), r2);

        Rectangle r3 = new Rectangle(25, 90, 60, 30);
        r3.setArcWidth(15);
        r3.setArcHeight(25);
        pane.getChildren().addAll(new Text(10, 107, "r3"), r3);


        // Rotate rectangles and add to pane (with random colors)
        for (int i = 0; i < 4; i++) {
            Rectangle r = new Rectangle(100, 50, 100, 30);
            r.setRotate(i * 360 / 8); // Rotate by increments
            r.setStroke(Color.color(Math.random(), Math.random(), Math.random())); // Random stroke color
            r.setFill(Color.WHITE);
            pane.getChildren().add(r);
        }



        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 150);
        primaryStage.setTitle("ShowRectangle"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}