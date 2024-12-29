package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class DirectorySize2 extends Application {
    @Override
    public void start(Stage primaryStage) {
        // UI Components
        Label resultLabel = new Label("Select a file or directory to calculate size.");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: darkblue;");

        Button fileButton = new Button("Choose File");
        Button dirButton = new Button("Choose Directory");

        // Adjusting buttons' size and spacing
        fileButton.setMaxWidth(Double.MAX_VALUE);
        dirButton.setMaxWidth(Double.MAX_VALUE);
        fileButton.setMinHeight(40);
        dirButton.setMinHeight(40);
        fileButton.setStyle("-fx-font-size: 14px; -fx-background-color: darkgreen; -fx-text-fill: white;");
        dirButton.setStyle("-fx-font-size: 14px; -fx-background-color: darkorange; -fx-text-fill: black;");

        // Action for File Chooser
        fileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                long size = getSize(file);
                resultLabel.setText("File size: " + size + " bytes");
            } else {
                resultLabel.setText("No file selected.");
            }
        });

        // Action for Directory Chooser
        dirButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File directory = directoryChooser.showDialog(primaryStage);
            if (directory != null) {
                long size = getSize(directory);
                resultLabel.setText("Directory size: " + size + " bytes");
            } else {
                resultLabel.setText("No directory selected.");
            }
        });

        // Layout
        VBox layout = new VBox(20); // Increase spacing between elements
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER); // Center the elements
        layout.getChildren().addAll(resultLabel, fileButton, dirButton);

        // Set gradient background
        Stop[] stops = new Stop[] { new Stop(0, Color.LIGHTBLUE), new Stop(1, Color.LIGHTCYAN) };
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        layout.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(layout, 400, 300);

        // Stage Configuration
        primaryStage.setTitle("Directory Size Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Recursive method to calculate the size of files and directories
    public static long getSize(File file) {
        long size = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    size += getSize(f);
                }
            }
        } else {
            size += file.length();
        }
        return size;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
