package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;

public class Main extends Application {
    // Global text field
    private TextField textField = new TextField();
    private long num1 = 0;
    private String op = "";
    private boolean start = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        textField.setFont(Font.font(20));
        textField.setPrefHeight(50);
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.setEditable(false); // User cannot input text directly

        StackPane stackpane = new StackPane();
        stackpane.setPadding(new Insets(10));
        stackpane.getChildren().add(textField);

        // Add buttons in a grid format (TilePane)
        TilePane tile = new TilePane();
        tile.setHgap(10);
        tile.setVgap(10);
        tile.setAlignment(Pos.TOP_CENTER);

        // Add all buttons, including "C" using createButtonForClear
        tile.getChildren().addAll(
                createButtonForNumber("7"),
                createButtonForNumber("8"),
                createButtonForNumber("9"),
                createButtonForOperator("/"),

                createButtonForNumber("4"),
                createButtonForNumber("5"),
                createButtonForNumber("6"),
                createButtonForOperator("X"),

                createButtonForNumber("1"),
                createButtonForNumber("2"),
                createButtonForNumber("3"),
                createButtonForOperator("-"),

                createButtonForNumber("0"),
                createButtonForClear("C"), // Use createButtonForClear for "C" button
                createButtonForOperator("="),
                createButtonForOperator("+")
        );

        BorderPane root = new BorderPane();
        root.setTop(stackpane);
        root.setCenter(tile); // Add TilePane to the center of BorderPane

        Scene scene = new Scene(root, 250, 320);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Calculator");
        primaryStage.setResizable(false); // Prevent resizing of the calculator window
        primaryStage.show();
    }

    // Method to create number buttons
    private Button createButtonForNumber(String ch) {
        Button button = new Button(ch);
        button.setFont(Font.font(18));
        button.setPrefSize(50, 50);
        button.setOnAction(this::processNumber); // Link button to processNumber
        return button;
    }

    // Method to create operator buttons
    private Button createButtonForOperator(String ch) {
        Button button = new Button(ch);
        
     // Set a larger font size only for the "-" button
        if (ch.equals("-")) {
            button.setFont(Font.font(25)); // Larger font size for "-" button
        } else {
            button.setFont(Font.font(18)); // Default font size for other operator buttons
        }
        
        // Set a larger size only for the "-" button
        if (ch.equals("-")) {
            button.setPrefSize(50, 50); // Larger size for "-" button
        } else {
            button.setPrefSize(50, 50); // Default size for other operator buttons
        }
        
        button.setOnAction(this::processOperator);
        return button;
    }

    // Method to create the clear button
    private Button createButtonForClear(String ch) {
        Button button = new Button(ch);
        button.setFont(Font.font(18));
        button.setPrefSize(50, 50);

        // Set action for clear button to reset text field and variables
        button.setOnAction(e -> {
            textField.setText(""); // Clear the text field
            op = "";               // Reset operator
            num1 = 0;              // Reset first number
            start = true;          // Set start flag to true
        });
        return button;
    }

    // Method to process number button clicks
    private void processNumber(ActionEvent e) {
        if (start) { // Clear text field on the first digit
            textField.setText("");
            start = false;
        }
        String value = ((Button) e.getSource()).getText();
        textField.setText(textField.getText() + value); // Append number to text field
    }

    // Method to process operator button clicks
    private void processOperator(ActionEvent e) {
        String value = ((Button) e.getSource()).getText();

        // If "=" is pressed, calculate the result
        if (!value.equals("=")) {
            if (!op.isEmpty()) return; // Return if operator already selected

            num1 = Long.parseLong(textField.getText()); // Store first number
            op = value; // Store operator
            textField.setText(""); // Clear text field for the next number
        } else { // If "=" is pressed, perform calculation
            if (op.isEmpty()) return;

            long num2 = Long.parseLong(textField.getText()); // Get second number
            float result = calculate(num1, num2, op); // Perform calculation
            textField.setText(String.valueOf(result)); // Display result

            start = true; // Reset for new calculation
            op = ""; // Clear operator
        }
    }

    // Method to perform calculations
    private float calculate(long num1, long num2, String operator) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "X" -> num1 * num2;
            case "/" -> (num2 == 0) ? 0 : (float) num1 / num2; // Handle divide by zero
            default -> 0;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
