package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class L14_18 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane
        Pane pane = new Pane();


        // Create and configure arcs
        Arc arc1 = new Arc(150, 100, 80, 80, 30, 35); // centerX, centerY, radiusX, radiusY, startAngle, length
        arc1.setFill(Color.RED); // Set fill color
        arc1.setType(ArcType.ROUND); // Set arc type
        pane.getChildren().addAll(new Text(210, 40, "arc1: round"), arc1); // Add label and arc


        Arc arc2 = new Arc(150, 100, 80, 80, 30 + 90, 35);
        arc2.setFill(Color.WHITE);
        arc2.setType(ArcType.OPEN); // Set arc type
        arc2.setStroke(Color.BLACK);
        pane.getChildren().addAll(new Text(20, 40, "arc2: open"), arc2);


        Arc arc3 = new Arc(150, 100, 80, 80, 30 + 180, 35);
        arc3.setFill(Color.WHITE);
        arc3.setType(ArcType.CHORD);  // Set arc type
        arc3.setStroke(Color.BLACK);
        pane.getChildren().addAll(new Text(20, 170, "arc3: chord"), arc3);


        Arc arc4 = new Arc(150, 100, 80, 80, 30 + 270, 35);
        arc4.setFill(Color.GREEN);
        arc4.setType(ArcType.CHORD);  // Set arc type
        arc4.setStroke(Color.BLACK);
        pane.getChildren().addAll(new Text(210, 170, "arc4: chord"), arc4);


        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("ShowArc"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}