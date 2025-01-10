import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Calculator extends Application {
    private Label display;
    private StringBuilder currentInput = new StringBuilder();
    private double result = 0;
    private String currentOperator = "";

    @Override
    public void start(Stage primaryStage) {
        // Initialize display
        display = new Label("0");
        display.setFont(new Font(24));
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setMinSize(200, 50);

        // Create buttons for digits and operations
        GridPane grid = createButtonGrid();

        // Main layout
        VBox root = new VBox(10, display, grid);
        root.setAlignment(Pos.CENTER);

        // Set up scene and stage
        Scene scene = new Scene(root, 250, 350);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createButtonGrid() {
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);

        // Add digit buttons (0-9) and operators
        String[][] buttons = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"0", "C", "=", "+"}
        };

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                String text = buttons[row][col];
                Button button = new Button(text);
                button.setMinSize(50, 50);
                button.setFont(new Font(18));
                button.setOnAction(e -> handleButtonClick(text));
                grid.add(button, col, row);
            }
        }
        return grid;
    }

    private void handleButtonClick(String text) {
        switch (text) {
            case "C":
                clear();
                break;
            case "=":
                calculate();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                handleOperator(text);
                break;
            default:
                handleNumber(text);
                break;
        }
    }

    private void handleNumber(String number) {
        currentInput.append(number);
        display.setText(currentInput.toString());
    }

    private void handleOperator(String operator) {
        if (!currentInput.toString().isEmpty()) {
            double value = Double.parseDouble(currentInput.toString());
            switch (currentOperator) {
                case "+": result += value; break;
                case "-": result -= value; break;
                case "*": result *= value; break;
                case "/": result /= value; break;
                default: result = value;
            }
            currentOperator = operator;
            currentInput.setLength(0);
            display.setText(String.valueOf(result));
        }
    }

    private void calculate() {
        handleOperator(currentOperator);
        currentOperator = "";
        currentInput.setLength(0);
        display.setText(String.valueOf(result));
    }

    private void clear() {
        currentInput.setLength(0);
        result = 0;
        currentOperator = "";
        display.setText("0");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
