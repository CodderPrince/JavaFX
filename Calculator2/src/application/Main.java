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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Application {
    // Global text fields
    private TextField expressionField = new TextField();
    private TextField resultField = new TextField();
    private double num1 = 0;
    private String op = "";
    private boolean start = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Configure the expression field with a dark background and lighter text
        expressionField.setFont(Font.font("Arial", 16));
        expressionField.setPrefHeight(30);
        expressionField.setAlignment(Pos.CENTER_RIGHT);
        expressionField.setEditable(false);
        expressionField.setStyle("-fx-text-fill: #B0B0B0; -fx-background-color: #2E2E2E;"); // Light grey text on dark grey

        // Configure the result field with larger font and white text
        resultField.setFont(Font.font("Arial", 28));
        resultField.setPrefHeight(50);
        resultField.setAlignment(Pos.CENTER_RIGHT);
        resultField.setEditable(false);
        resultField.setStyle("-fx-background-color: #1E1E1E; -fx-text-fill: #FFFFFF;"); // White text on darker grey

        // Use a VBox to stack expression and result fields
        VBox displayBox = new VBox(5, expressionField, resultField);
        displayBox.setPadding(new Insets(10));

        // Create GridPane for button layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.TOP_CENTER);

        // First row
        grid.add(createButtonForClear("C"), 0, 0);
        grid.add(createButtonForOperator("/"), 1, 0);
        grid.add(createButtonForOperator("X"), 2, 0);
        grid.add(createButtonForDelete("D"), 3, 0);

        // Second row
        grid.add(createButtonForNumber("7"), 0, 1);
        grid.add(createButtonForNumber("8"), 1, 1);
        grid.add(createButtonForNumber("9"), 2, 1);
        grid.add(createButtonForOperator("-"), 3, 1);

        // Third row
        grid.add(createButtonForNumber("4"), 0, 2);
        grid.add(createButtonForNumber("5"), 1, 2);
        grid.add(createButtonForNumber("6"), 2, 2);
        grid.add(createButtonForOperator("+"), 3, 2);

        // Fourth row
        grid.add(createButtonForNumber("1"), 0, 3);
        grid.add(createButtonForNumber("2"), 1, 3);
        grid.add(createButtonForNumber("3"), 2, 3);
        grid.add(createButtonForOperator("="), 3, 3, 1, 2); // Span "=" button over two rows

        // Fifth row
        grid.add(createButtonForOperator("%"), 0, 4);
        grid.add(createButtonForNumber("0"), 1, 4);
        grid.add(createButtonForOperator("."), 2, 4);

        BorderPane root = new BorderPane();
        root.setTop(displayBox); // Use VBox as the top section
        root.setCenter(grid); // Add GridPane to the center of BorderPane

        Scene scene = new Scene(root, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Calculator");
        primaryStage.setResizable(false); // Prevent resizing of the calculator window
        primaryStage.show();
    }

    // Method to create number buttons
    private Button createButtonForNumber(String ch) {
        Button button = new Button(ch);
        button.setFont(Font.font("Arial", 18));
        button.setPrefSize(50, 50);
        button.setStyle("-fx-background-color: #4E4E4E; -fx-text-fill: #FFFFFF;"); // Grey background, white text
        button.setOnAction(this::processNumber); // Link button to processNumber
        return button;
    }

    // Method to create operator buttons
    private Button createButtonForOperator(String ch) {
        Button button = new Button(ch);
        button.setFont(Font.font("Arial", 18));
        button.setPrefSize(50, 50);

        // Set color styles for operator buttons
        button.setStyle("-fx-background-color: #FF9500; -fx-text-fill: #FFFFFF;"); // Orange background, white text
        if (ch.equals("=")) {
            button.setPrefSize(50, 110); // Larger size for "=" button, spans two rows
            button.setStyle("-fx-background-color: #FF6D00; -fx-text-fill: #FFFFFF;"); // Darker orange for "="
        }

        button.setOnAction(this::processOperator);
        return button;
    }

    // Method to create the clear button
    private Button createButtonForClear(String ch) {
        Button button = new Button(ch);
        button.setFont(Font.font("Arial", 18));
        button.setPrefSize(50, 50);
        button.setStyle("-fx-background-color: #D9534F; -fx-text-fill: #FFFFFF;"); // Red background, white text

        // Set action for clear button to reset text fields and variables
        button.setOnAction(e -> {
            expressionField.setText(""); // Clear the expression field
            resultField.setText("");     // Clear the result field
            op = "";                     // Reset operator
            num1 = 0;                    // Reset first number
            start = true;                // Set start flag to true
        });
        return button;
    }

    // Method to create the delete button
    private Button createButtonForDelete(String ch) {
        Button button = new Button(ch);
        button.setFont(Font.font("Arial", 18));
        button.setPrefSize(50, 50);
        button.setStyle("-fx-background-color: #FF9500; -fx-text-fill: #FFFFFF;"); // Orange background, white text

        // Set action for delete button to remove last character
        button.setOnAction(e -> {
            String currentText = resultField.getText();
            if (!currentText.isEmpty()) {
                resultField.setText(currentText.substring(0, currentText.length() - 1));
            }
        });
        return button;
    }

    private void processNumber(ActionEvent e) {
        String value = ((Button) e.getSource()).getText();

        if (start) { 
            resultField.setText("");
            start = false;
        }

        // Handle decimal point logic
        if (value.equals(".")) {
            if (resultField.getText().isEmpty()) {
                resultField.setText("0.");
            } else if (!resultField.getText().contains(".")) {
                resultField.setText(resultField.getText() + value);
            }
        } else {
            resultField.setText(resultField.getText() + value);
        }
    }

    // Method to process operator button clicks
    private void processOperator(ActionEvent e) {
        String value = ((Button) e.getSource()).getText();

        if (!value.equals("=")) {
            if (!op.isEmpty()) return; // Return if operator already selected

            num1 = Double.parseDouble(resultField.getText());
            op = value;
            expressionField.setText(resultField.getText() + " " + op);
            resultField.setText("");
        } else {
            if (op.isEmpty()) return;

            double num2 = Double.parseDouble(resultField.getText());
            double result = calculate(num1, num2, op);
            expressionField.setText(expressionField.getText() + " " + num2 + " =");
            resultField.setText(String.valueOf(result));

            start = true;
            op = "";
        }
    }

    // Method to perform calculations
    private double calculate(double num1, double num2, String operator) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "X" -> num1 * num2;
            case "/" -> (num2 == 0) ? 0 : num1 / num2;
            case "%" -> num1 % num2;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
