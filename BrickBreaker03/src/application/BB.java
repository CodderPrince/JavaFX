package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class BB extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox mainLayout = new VBox(15); // Use VBox for vertical layout with 15 pixel spacing
        mainLayout.setStyle("-fx-background-color: #1A1A50;"); // Set background color
        mainLayout.setAlignment(Pos.CENTER);

        // Create the first two rows
        HBox row1 = createRow();
        HBox row2 = createRow();

        // Add the first two rows directly to the main layout
        mainLayout.getChildren().addAll(row1, row2);

        // Create new rows for duplication
        HBox row3 = createRow();
        HBox row4 = createRow();

        // Add the duplicated rows to the main layout
        mainLayout.getChildren().addAll(row3, row4);

        // Create the scene
        Scene scene = new Scene(mainLayout, 800, 400);

        primaryStage.setTitle("Color Grid Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create a single row of grids
    private HBox createRow() {
        int patternCols = 8;
        int patternRows = 5;

        HBox row = new HBox(15);
        for (int k = 0; k < 10; k++) {
            GridPane gridPane = new GridPane();
            gridPane.setHgap(5);
            gridPane.setVgap(5);

            for (int i = 0; i < patternRows; i++) {
                for (int j = 0; j < patternCols; j++) {
                    Rectangle rect = new Rectangle(4, 4);
                    rect.setFill((i + j) % 2 == 0 ? Color.ORANGE : Color.LIMEGREEN);
                    gridPane.add(rect, j, i);
                }
            }

            row.getChildren().add(gridPane);
        }
        return row;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
