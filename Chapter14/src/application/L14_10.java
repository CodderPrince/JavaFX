package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class L14_10 extends Application {

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a FlowPane and set its properties
		FlowPane pane = new FlowPane();
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setHgap(5);  // Horizontal gap between nodes
		pane.setVgap(5);  // Vertical gap between nodes

		// Place nodes in the pane
		pane.getChildren().addAll(new Label("First Name:"), new TextField());
		TextField tfMi = new TextField();
		tfMi.setPrefColumnCount(1);  // Set preferred number of columns for the text field
		pane.getChildren().addAll(new Label("MI:"), tfMi);

		pane.getChildren().addAll(new Label("Last Name:"), new TextField());

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 200, 250);
		primaryStage.setTitle("ShowFlowPane"); // Set the stage title
		primaryStage.setScene(scene);            // Place the scene in the stage
		primaryStage.show();                   // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}