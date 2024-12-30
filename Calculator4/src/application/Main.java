package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField display;  // Display for the calculation and result

    @Override
    public void start(Stage primaryStage) {
        display = new TextField();
        display.setEditable(false);
        display.setAlignment(Pos.BASELINE_RIGHT);
        display.setStyle("-fx-background-color: black; -fx-text-fill: lime; -fx-font-size: 20;");
        display.setMaxSize(300, 50);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: black;");

        String[] labels = {
            "C", "(", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
        };

        Button[] buttons = new Button[labels.length];
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[index] = new Button(labels[index]);
                buttons[index].setFont(Font.font("Verdana", 18));
                buttons[index].setStyle("-fx-base: #333; -fx-text-fill: #FFF; -fx-background-radius: 30;");
                buttons[index].setPrefWidth(70);
                buttons[index].setPrefHeight(70);
                GridPane.setConstraints(buttons[index], j, i + 1);
                int finalIndex = index;
                buttons[index].setOnAction(e -> buttonClicked(labels[finalIndex]));
                grid.getChildren().add(buttons[index]);
                index++;
            }
        }

        grid.add(display, 0, 0, 4, 1);

        Scene scene = new Scene(grid, 320, 480);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buttonClicked(String value) {
        switch (value) {
            case "=":
                try {
                    display.setText(String.valueOf(eval(display.getText())));
                } catch (Exception e) {
                    display.setText("Error");
                }
                break;
            case "C":
                display.setText("");
                break;
            case "+/-":
                if (!display.getText().isEmpty() && !display.getText().equals("Error")) {
                    display.setText(String.valueOf(-1 * Double.parseDouble(display.getText())));
                }
                break;
            default:
                if (display.getText().equals("Error")) {
                    display.setText("");
                }
                display.setText(display.getText() + value);
                break;
        }
    }

    private double eval(final String str) {
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

    public static void main(String[] args) {
        launch(args);
    }
}
