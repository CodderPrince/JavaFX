package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    TextField inputField; // Input field for operations
    TextField resultField; // Display field for results
    int openParenthesesCount = 0; // Track the number of unclosed parentheses

    @Override
    public void start(Stage primaryStage) {
        // Set up the input field, disable editing, and style it
        inputField = new TextField();
        inputField.setEditable(false);
        inputField.setAlignment(Pos.BASELINE_RIGHT);
        inputField.setStyle("-fx-background-color: darkblue; -fx-text-fill: white;");
        inputField.setFont(Font.font(35));

        // Set up the result field, disable editing, and style it
        resultField = new TextField();
        resultField.setEditable(false);
        resultField.setAlignment(Pos.BASELINE_RIGHT);
        resultField.setStyle("-fx-background-color: lightgrey; -fx-text-fill: darkred;");
        resultField.setFont(Font.font(40)); // Increased font size for better visibility

        // Set up the layout grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20)); // Padding
        grid.setHgap(15); // Horizontal gap between cells
        grid.setVgap(10); // Adjusted vertical gap
        grid.setStyle("-fx-background-color: #FFFDD0; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Add fields to the grid
        grid.add(inputField, 0, 0, 4, 1);
        grid.add(resultField, 0, 1, 4, 1);

        // Button labels including the toggle for parentheses
        String[] buttonLabels = {
            "C", "D", "()", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "%", "0", ".", "="
        };

        int buttonIdx = 0; // Index for button labels
        for (int i = 2; i < 7; i++) { // Start at row 2 to leave space for text fields
            for (int j = 0; j < 4; j++) {
                String text = buttonLabels[buttonIdx++];
                Button button = new Button(text);
                button.setPrefWidth(70); // Preferred button width
                button.setPrefHeight(50); // Preferred button height
                button.setFont(Font.font(20)); // Font size for button
                styleButton(button, text); // Apply style based on the button type
                button.setOnAction(e -> handleButtonClick(text));
                if (text.isEmpty()) {
                    button.setDisable(true); // Disable empty buttons
                    button.setVisible(false);
                }
                grid.add(button, j, i); // Add button to grid
            }
        }

        // Create a light orange gradient background for the scene
        LinearGradient gradient = new LinearGradient(
            0, 0, 1, 1, true, // proportional
            CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#FFE4B5")), // Light orange color
            new Stop(1, Color.web("#FFD700"))  // Slightly deeper orange color
        );

        // Set the scene with a gradient background
        Scene scene = new Scene(grid, 320, 480, gradient); // Set gradient as background
        primaryStage.setTitle("PRINCE Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(String text) {
        switch (text) {
            case "C":
                inputField.setText("");
                resultField.setText("");
                openParenthesesCount = 0;
                break;
            case "D":
                if (!inputField.getText().isEmpty()) {
                    String currentText = inputField.getText();
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    if (lastChar == '(') {
                        openParenthesesCount--;
                    } else if (lastChar == ')') {
                        openParenthesesCount++;
                    }
                    inputField.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            case "()":
                if (shouldOpenParenthesis()) {
                    inputField.setText(inputField.getText() + "(");
                    openParenthesesCount++;
                } else if (openParenthesesCount > 0) {
                    inputField.setText(inputField.getText() + ")");
                    openParenthesesCount--;
                }
                break;
            case "=":
                calculateResult();
                break;
            default:
                inputField.setText(inputField.getText() + text);
        }
    }

    private boolean shouldOpenParenthesis() {
        String text = inputField.getText();
        return text.isEmpty() || text.endsWith("(") || isOperator(text.charAt(text.length() - 1));
    }

    private boolean isOperator(char ch) {
        return "+-×÷%".indexOf(ch) != -1; // Include modulus operator
    }

    private void calculateResult() {
        String value = inputField.getText();
        value = value.replace('÷', '/').replace('×', '*');
        try {
            double result = eval(value);
            resultField.setText(String.valueOf(result));
        } catch (Exception e) {
            resultField.setText("Error");
        }
    }

    public double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else if (eat('%')) x %= parseFactor(); // Handle modulus operation
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }
        }.parse();
    }

    private void styleButton(Button button, String text) {
        // Set fixed dimensions for all buttons
        button.setPrefWidth(60);  // Set a fixed width for all buttons
        button.setPrefHeight(40);  // Set a fixed height for all buttons

        if ("C".equals(text) || "D".equals(text)) {
            // Clear and delete buttons
            button.setStyle("-fx-background-color: #FF6347;"
            		+ " -fx-text-fill: #ffffff;"
            		+ " -fx-font-family: 'Arial Black';"
            		+ " -fx-font-size: 22; -fx-padding: 5;");
            		// Tomato color for clear and delete
        } else if ("÷".equals(text)) {
            // Division button
            button.setStyle("-fx-background-color: #4682B4;"
            		+ " -fx-text-fill: #ffffff;"
            		+ " -fx-font-family: 'Arial Black';"
            		+ " -fx-font-size: 24; -fx-padding: 5;"); 
            		// Steel blue for division
        } else if ("×".equals(text)) {
            // Multiplication button
            button.setStyle("-fx-background-color: #4682B4;"
            		+ " -fx-text-fill: #ffffff;"
            		+ " -fx-font-family: 'Arial Black';"
            		+ " -fx-font-size: 24; -fx-padding: 5;"); 
            		// Steel blue for multiplication
        } else if ("-".equals(text)) {
            // Subtraction button
            button.setStyle("-fx-background-color: #4682B4;"
            		+ " -fx-text-fill: #ffffff;"
            		+ " -fx-font-family: 'Arial Black';"
            		+ " -fx-font-size: 22; -fx-padding: 5;"); 
            		// Steel blue for subtraction
        } else if ("+".equals(text)) {
            // Addition button
            button.setStyle("-fx-background-color: #4682B4;"
            		+ " -fx-text-fill: #ffffff;"
            		+ " -fx-font-family: 'Arial Black';"
            		+ " -fx-font-size: 22; -fx-padding: 5;");
            		// Steel blue for addition
        } else if ("()".equals(text)) {
            // Parentheses button
            button.setStyle("-fx-background-color: #4682B4;"
            		+ " -fx-text-fill: #ffffff;"
            		+ " -fx-font-family: 'Arial Black';"
            		+ " -fx-font-size: 22; -fx-padding: 5;"); 
            		// Steel blue for parentheses
        } else if ("%".equals(text)) {
            // Percentage button
            button.setStyle("-fx-background-color: #4682B4;"
            		+ " -fx-text-fill: #ffffff;"
            		+ " -fx-font-family: 'Arial Black';"
            		+ " -fx-font-size: 22; -fx-padding: 5;");
            		// Steel blue for percentage
        } else if ("=".equals(text)) {
            // Equals button
            button.setStyle("-fx-background-color: #32CD32;"
            		+ " -fx-text-fill: #ffffff;"
            		+ " -fx-font-family: 'Arial Black';"
            		+ " -fx-font-size: 22; -fx-padding: 5;");
            		// Lime green for equals
        } else {
            // Number buttons and decimal
            button.setStyle("-fx-background-color: #B8860B;"
            		+ " -fx-text-fill: #FFFFFF;"
            		+ " -fx-font-family: 'Arial Rounded MT';"
            		+ " -fx-font-size: 22; -fx-padding: 5;");
            		// Darker gold color with white text
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
