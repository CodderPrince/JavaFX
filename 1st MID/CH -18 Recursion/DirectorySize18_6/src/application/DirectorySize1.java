package application;
	
import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.File;

public class DirectorySize1  extends Application {
    @Override
    public void start(Stage primaryStage) {
        // UI Components
        Label resultLabel = new Label("Select a file or directory to calculate size.");
        Button fileButton = new Button("Choose File");
        Button dirButton = new Button("Choose Directory");

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
        VBox layout = new VBox(10);
        layout.getChildren().addAll(resultLabel, fileButton, dirButton);
        Scene scene = new Scene(layout, 400, 200);

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
