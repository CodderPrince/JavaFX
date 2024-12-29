package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ColorfulCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a grid pane for the layout
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);

        // Create labels and text fields
        Label lblNumber1 = new Label("Number 1:");
        TextField tfNumber1 = new TextField();
        tfNumber1.setPrefWidth(60);

        Label lblNumber2 = new Label("Number 2:");
        TextField tfNumber2 = new TextField();
        tfNumber2.setPrefWidth(60);

        Label lblResult = new Label("Result:");
        TextField tfResult = new TextField();
        tfResult.setEditable(false); // Result field should not be editable
        tfResult.setPrefWidth(80);

        // Create colorful buttons
        Button btnAdd = createColorfulButton("Add", "#28a745");       // Green
        Button btnSubtract = createColorfulButton("Subtract", "#ffc107"); // Yellow
        Button btnMultiply = createColorfulButton("Multiply", "#007bff"); // Blue
        Button btnDivide = createColorfulButton("Divide", "#dc3545");    // Red

        // Add elements to the grid pane
        pane.add(lblNumber1, 0, 0);
        pane.add(tfNumber1, 1, 0);
        pane.add(lblNumber2, 2, 0);
        pane.add(tfNumber2, 3, 0);
        pane.add(lblResult, 4, 0);
        pane.add(tfResult, 5, 0);

        pane.add(btnAdd, 0, 1);
        pane.add(btnSubtract, 1, 1);
        pane.add(btnMultiply, 2, 1);
        pane.add(btnDivide, 3, 1);

        // Button event handlers
        btnAdd.setOnAction(e -> calculate(tfNumber1, tfNumber2, tfResult, "add"));
        btnSubtract.setOnAction(e -> calculate(tfNumber1, tfNumber2, tfResult, "subtract"));
        btnMultiply.setOnAction(e -> calculate(tfNumber1, tfNumber2, tfResult, "multiply"));
        btnDivide.setOnAction(e -> calculate(tfNumber1, tfNumber2, tfResult, "divide"));

        // Create the scene with a background color
        Scene scene = new Scene(pane, 600, 200);
        pane.setStyle("-fx-background-color: #f0f8ff;"); // Light blue background

        // Set up the stage
        primaryStage.setTitle("Colorful Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create a colorful button with hover effects
    private Button createColorfulButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #444444; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;"));
        button.setPrefWidth(100);
        return button;
    }

    // Method to perform the calculation
    private void calculate(TextField tfNumber1, TextField tfNumber2, TextField tfResult, String operation) {
        try {
            double number1 = Double.parseDouble(tfNumber1.getText());
            double number2 = Double.parseDouble(tfNumber2.getText());
            double result = 0;

            switch (operation) {
                case "add":
                    result = number1 + number2;
                    break;
                case "subtract":
                    result = number1 - number2;
                    break;
                case "multiply":
                    result = number1 * number2;
                    break;
                case "divide":
                    if (number2 != 0) {
                        result = number1 / number2;
                    } else {
                        tfResult.setText("Error");
                        return;
                    }
                    break;
            }

            tfResult.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            tfResult.setText("Error");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
