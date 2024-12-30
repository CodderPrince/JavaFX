package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.text.Font; // Importing Font class
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;



public class Main extends Application {

    TextField inputField; // Input field for operations
    TextField resultField; // Display field for results
    int openParenthesesCount = 0; // Track the number of unclosed parentheses

    @Override
    public void start(Stage primaryStage) {
        // Create the main layout
        BorderPane mainLayout = new BorderPane();

     // Create a custom title bar with gradient background, rounded corners, and a black border
        HBox titleBar = new HBox();
        titleBar.setStyle(
            "-fx-background-color: linear-gradient(to right, #004D40, #00251A);" + // Darker teal gradient
            "-fx-border-color: black;" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 10;"
        );
        titleBar.setAlignment(Pos.CENTER_LEFT);

        // Create title label with white text and bold font
        Label titleLabel = new Label("Desktop Calculator\nDeveloped by PRINCE");
        titleLabel.setTextFill(Color.WHITE); // White font color for title
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18)); // Bold font with size 18
        titleBar.getChildren().add(titleLabel);

        // Add a spacer region to push the close button to the right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // Allows spacer to grow and push close button to the right
        titleBar.getChildren().add(spacer);

        // Add close button aligned near the right side
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: RED; -fx-font-size: 28; -fx-padding: 5;");
        closeButton.setOnAction(e -> primaryStage.close());
        titleBar.getChildren().add(closeButton);
        
        // Enable dragging the window
        final Delta dragDelta = new Delta();
        titleBar.setOnMousePressed(event -> {
            // Store the initial mouse position
            dragDelta.x = event.getScreenX() - primaryStage.getX();
            dragDelta.y = event.getScreenY() - primaryStage.getY();
        });
        titleBar.setOnMouseDragged(event -> {
            // Update the stage position based on mouse movement
            primaryStage.setX(event.getScreenX() - dragDelta.x);
            primaryStage.setY(event.getScreenY() - dragDelta.y);
        });

        // Set up the input field, disable editing, and style it
        inputField = new TextField();
        inputField.setEditable(false);
        inputField.setAlignment(Pos.BASELINE_RIGHT);
        inputField.setStyle("-fx-background-color: darkblue; -fx-text-fill: white;");
        inputField.setFont(Font.font(45));

        // Set up the result field, disable editing, and style it
        resultField = new TextField();
        resultField.setEditable(false);
        resultField.setAlignment(Pos.BASELINE_RIGHT);
        resultField.setStyle("-fx-background-color: #424242; -fx-text-fill: white;");

        resultField.setFont(Font.font(45)); // Increased font size for better visibility

        // Set up the layout grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20)); // Padding
        grid.setHgap(15); // Horizontal gap between cells
        grid.setVgap(10); // Adjusted vertical gap
        grid.setStyle("-fx-background-color: #FFFDD0; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 0; -fx-background-radius: 10;");

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

        // Set the scene with the custom title bar
        mainLayout.setTop(titleBar);
        mainLayout.setCenter(grid);

        Scene scene = new Scene(mainLayout, 320, 550);
        primaryStage.initStyle(StageStyle.UNDECORATED); // Remove default title bar
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
            button.setStyle("-fx-background-color: #FF6347; -fx-text-fill: #ffffff; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 22; -fx-padding: 5;"); // Tomato color for clear and delete
        } else if ("÷".equals(text)) {
            // Division button
            button.setStyle("-fx-background-color: #4682B4; -fx-text-fill: #ffffff; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 24; -fx-padding: 5;"); // Steel blue for division
        } else if ("×".equals(text)) {
            // Multiplication button
            button.setStyle("-fx-background-color: #4682B4; -fx-text-fill: #ffffff; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 24; -fx-padding: 5;"); // Steel blue for multiplication
        } else if ("-".equals(text)) {
            // Subtraction button
            button.setStyle("-fx-background-color: #4682B4; -fx-text-fill: #ffffff; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 22; -fx-padding: 5;"); // Steel blue for subtraction
        } else if ("+".equals(text)) {
            // Addition button
            button.setStyle("-fx-background-color: #4682B4; -fx-text-fill: #ffffff; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 22; -fx-padding: 5;"); // Steel blue for addition
        } else if ("()".equals(text)) {
            // Parentheses button
            button.setStyle("-fx-background-color: #4682B4; -fx-text-fill: #ffffff; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 22; -fx-padding: 5;"); // Steel blue for parentheses
        } else if ("%".equals(text)) {
            // Percentage button
            button.setStyle("-fx-background-color: #4682B4; -fx-text-fill: #ffffff; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 22; -fx-padding: 5;"); // Steel blue for percentage
        } else if ("=".equals(text)) {
            // Equals button
            button.setStyle("-fx-background-color: #006400; -fx-text-fill: #ffffff; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 22; -fx-padding: 5;"); // Dark green for equals
        } else {
            // Number buttons and decimal
            button.setStyle("-fx-background-color: #B8860B; -fx-text-fill: #FFFFFF; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 22; -fx-padding: 5;"); // Darker gold color with white text
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Inner class to hold the delta values for dragging
    private class Delta {
        double x, y;
    }
}
