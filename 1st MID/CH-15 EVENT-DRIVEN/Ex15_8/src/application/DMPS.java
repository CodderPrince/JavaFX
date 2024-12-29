package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DMPS extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a pane to hold the text
        Pane pane = new Pane();
        Text text = new Text();
        text.setFill(Color.DODGERBLUE); // Colorful text
        pane.getChildren().add(text);

        // Set mouse click event
        pane.setOnMouseClicked(e -> {
            text.setX(e.getX());
            text.setY(e.getY());
            text.setText("(" + e.getX() + ", " + e.getY() + ")");
        });

        // Create a scene with a light background color
        Scene scene = new Scene(pane, 400, 300, Color.LIGHTYELLOW);

        // Set up the stage
        primaryStage.setTitle("Display Mouse Position on Click");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
