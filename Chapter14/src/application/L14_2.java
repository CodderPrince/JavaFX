// multiple stage

package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class L14_2 extends Application {

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a scene and place a button in the scene for the primary stage
		Scene scene = new Scene(new Button("OK"), 300, 350);
		primaryStage.setTitle("MyJavaFX"); // Set the primary stage title
		primaryStage.setScene(scene);      // Place the scene in the primary stage
		primaryStage.show();             // Display the primary stage

		// Create a new stage (secondary stage)
		Stage stage = new Stage();
		stage.setTitle("Second Stage"); // Set the secondary stage title

		// Set a scene with a button in the secondary stage
		stage.setScene(new Scene(new Button("New Stage"), 50, 50));
		stage.show();                 // Display the secondary stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}