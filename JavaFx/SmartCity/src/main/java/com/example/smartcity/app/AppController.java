package com.example.smartcity.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {

    private Stage primaryStage;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Initializes the application and loads the default view.
     */
    public void initializeApp() {
        loadView("views/dashboard.fxml", "SmartCity Dashboard");
    }

    /**
     * Loads a specified FXML view and sets it as the primary scene.
     *
     * @param fxmlPath Path to the FXML file within the resources.
     * @param title    Title of the application window.
     */
    public void loadView(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/smartcity/" + fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load view: " + fxmlPath);
        }
    }

    /**
     * Navigates to the Dashboard view.
     */
    public void navigateToDashboard() {
        loadView("views/dashboard.fxml", "SmartCity Dashboard");
    }

    /**
     * Navigates to the City Simulation view.
     */
    public void navigateToCityView() {
        loadView("views/city-view.fxml", "SmartCity Simulation");
    }

    /**
     * Navigates to the Settings view.
     */
    public void navigateToSettings() {
        loadView("views/settings.fxml", "SmartCity Settings");
    }
}
