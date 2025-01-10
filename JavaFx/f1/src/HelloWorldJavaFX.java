import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloWorldJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a Label with "Hello, World!" text
        Label helloLabel = new Label("Hello, World!");

        // Create a scene with the label
        Scene scene = new Scene(helloLabel, 200, 100); // width 200, height 100

        // Set the title of the stage
        primaryStage.setTitle("Hello World in JavaFX");

        // Add the scene to the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
