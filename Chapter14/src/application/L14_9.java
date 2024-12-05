package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;  // Corrected import
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class L14_9 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        // Create an HBox to hold the image views. HBox arranges children horizontally.
        Pane pane = new HBox(10); // 10 is spacing between children
        pane.setPadding(new Insets(5, 5, 5, 5)); // Padding around the edges

        // Load the image
        Image image = new Image(getClass().getResource("/application/image/ban.gif").toExternalForm());

        // Create the first ImageView (original size)
        ImageView imageView1 = new ImageView(image);
        pane.getChildren().add(imageView1);

        // Create the second ImageView (resized)
        ImageView imageView2 = new ImageView(image);
        imageView2.setFitHeight(200);
        imageView2.setFitWidth(200);
        pane.getChildren().add(imageView2);

        // Create the third ImageView (rotated)
        ImageView imageView3 = new ImageView(image);
        imageView3.setRotate(10); // Rotate 10 degrees

        // Set the pivot point for rotation using a Rotate transform
        Rotate rotate = new Rotate(10, image.getWidth() / 2, image.getHeight() / 2); // Rotate around the center
        imageView3.getTransforms().add(rotate); // Apply the rotation

        // Translate (move) the image if needed
        imageView3.setTranslateX(10);
        imageView3.setTranslateY(10);

        pane.getChildren().add(imageView3);

        // Create the fourth ImageView (rotated and resized)
        ImageView imageView4 = new ImageView(image);
        imageView4.setRotate(180); // Rotate 180 degrees
        imageView4.setFitHeight(10); // Adjust the size
        imageView4.setFitWidth(50); // Adjust the size
        pane.getChildren().add(imageView4);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("ShowImage"); // Set the stage title
        primaryStage.setScene(scene);        // Place the scene in the stage
        primaryStage.show();               // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
