import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    private boolean isXTurn = true;
    private Button[][] board = new Button[3][3];
    private Text status = new Text("X's Turn");

    @Override
    public void start(Stage primaryStage) {
        // Create grid for Tic-Tac-Toe board
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);

        // Initialize the board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setFont(new Font(24));
                button.setMinSize(100, 100);
                button.setOnAction(e -> makeMove(button));
                board[row][col] = button;
                grid.add(button, col, row);
            }
        }

        // Main layout
        StackPane root = new StackPane(grid);
        root.setAlignment(Pos.TOP_CENTER);

        // Scene setup
        Scene scene = new Scene(root, 320, 370);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void makeMove(Button button) {
        // Check if the cell is already occupied
        if (button.getText().isEmpty()) {
            // Set button text to X or O based on current turn
            button.setText(isXTurn ? "X" : "O");

            // Check if the game has been won or if it’s a draw
            if (checkWin()) {
                status.setText((isXTurn ? "X" : "O") + " Wins!");
                disableBoard();
            } else if (isBoardFull()) {
                status.setText("It's a Draw!");
            } else {
                isXTurn = !isXTurn;
                status.setText((isXTurn ? "X" : "O") + "'s Turn");
            }
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2]) ||  // rows
                    checkLine(board[0][i], board[1][i], board[2][i]))    // columns
                return true;
        }
        return checkLine(board[0][0], board[1][1], board[2][2]) ||   // main diagonal
                checkLine(board[0][2], board[1][1], board[2][0]);     // anti-diagonal
    }

    private boolean checkLine(Button b1, Button b2, Button b3) {
        // Check if all three buttons in a line have the same text and are not empty
        return !b1.getText().isEmpty() && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private boolean isBoardFull() {
        // Check if every cell on the board is occupied
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableBoard() {
        // Disable all buttons on the board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setDisable(true);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
