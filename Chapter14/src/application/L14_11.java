package application;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class L14_11 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a GridPane and set its properties
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);  // Align the whole grid to center
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5)); // Padding around grid
        pane.setHgap(5.5);  // Horizontal gap between columns
        pane.setVgap(5.5);  // Vertical gap between rows


        // Place nodes in the pane
        pane.add(new Label("First Name:"), 0, 0); // Column 0, Row 0
        pane.add(new TextField(), 1, 0);          // Column 1, Row 0

        pane.add(new Label("MI:"), 0, 1);         // Column 0, Row 1
        pane.add(new TextField(), 1, 1);          // Column 1, Row 1

        pane.add(new Label("Last Name:"), 0, 2);  // Column 0, Row 2
        pane.add(new TextField(), 1, 2);          // Column 1, Row 2

        Button btAdd = new Button("Add Name");
        pane.add(btAdd, 1, 3);                   // Column 1, Row 3

        // Right-align the button within its cell.
        GridPane.setHalignment(btAdd, HPos.RIGHT);


        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("ShowGridPane"); // Set the stage title
        primaryStage.setScene(scene);           // Place the scene in the stage
        primaryStage.show();                  // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}