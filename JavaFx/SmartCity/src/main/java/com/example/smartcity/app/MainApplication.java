package com.example.smartcity.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the initial view (traffic-view.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/smartcity/views/traffic-view.fxml"));
            Parent root = loader.load();

            // Set the title and scene for the primary stage
            primaryStage.setTitle("SmartCity Simulation");
            primaryStage.setScene(new Scene(root, 800, 600)); // Adjust dimensions as needed
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
