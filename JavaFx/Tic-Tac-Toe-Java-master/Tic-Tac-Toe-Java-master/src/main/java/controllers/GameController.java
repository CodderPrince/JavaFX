package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;

public class GameController {

    Player player1 = new Player("Player 1", new XSymbol());

    Player player2 = new Player("Player 2", new OSymbol());

    Bot bot = new Bot("Pascal", new OSymbol());

    Game game;

    Settings settings = new Settings(player1);

    public int equalScore = 0;

    @FXML
    private GridPane gameBoard;
    @FXML
    private Text player1Score;
    @FXML
    private Text player2Score;
    @FXML
    private Text equalityScore;
    @FXML
    private VBox msgEndGame;
    @FXML
    private Text winnerText;
    @FXML
    private VBox player1current;
    @FXML
    private VBox player2current;
    @FXML
    private VBox equalityVBox;
    @FXML
    private Text player2Text;
    @FXML
    private Text player1Text;

    // When the controller is created, initialize the game
    @FXML
    public void initialize() {
        createGame();
    }

    // Creates a new game
    private void createGame() {
        // Create the game according to the settings
        if (settings.getIsOnePlayerMode() && settings.getFirstPlayerIsRandom()) {
            game = new Game(player1, bot);
        } else if (settings.getIsOnePlayerMode()) {
            game = new Game(player1, bot, settings.getFirstPlayer());
        } else if (settings.getFirstPlayerIsRandom()) {
            game = new Game(player1, player2);
        } else {
            game = new Game(player1, player2, settings.getFirstPlayer());
        }
        // Reset the game board
        for (int i = 0; i < gameBoard.getChildren().size(); i++) {
            if (gameBoard.getChildren().get(i) instanceof Button) {
                ((Button) gameBoard.getChildren().get(i)).setText("");
                gameBoard.getChildren().get(i).getStyleClass().remove("gray");
                gameBoard.getChildren().get(i).setStyle("");
            }
        }
        // Reset end-game messages and scores
        player1current.getStyleClass().remove("gray");
        player2current.getStyleClass().remove("gray");
        msgEndGame.setVisible(false);
        msgEndGame.setDisable(true);
        msgEndGame.setManaged(false);
        indicationCurrentPlayer();
        equalityVBox.getStyleClass().add("gray");
        updateScore();
        // If the starting player is a bot, make the bot play
        if (game.getCurrentPlayer() instanceof Bot) {
            int[] botMove = bot.play(game.grid, player1.getSymbol(), settings.getDifficultyLevel());
            game.makeMove(botMove[0], botMove[1]);
            Button botButton = getNodeByCoordinate(botMove[0], botMove[1]);
            assert botButton != null;
            placeSymbol(botButton, bot.getSymbol());
        }
    }

    // If the player clicks on the grid
    @FXML
    void handleButtonClick(ActionEvent event) {
        // When a player clicks on a grid button
        Button button = (Button) event.getSource();
        int row = gameBoard.getRowIndex(button) / 2;
        int col = gameBoard.getColumnIndex(button) / 2;
        if (game.makeMove(row, col)) {
            placeSymbol(button, game.getCurrentPlayer().getSymbol());
        }
        // If there's a bot, make the bot play
        if (game.getCurrentPlayer() instanceof Bot && !game.isOver) {
            int[] botMove = bot.play(game.grid, player1.getSymbol(), settings.getDifficultyLevel());
            game.makeMove(botMove[0], botMove[1]);
            Button botButton = getNodeByCoordinate(botMove[0], botMove[1]);
            assert botButton != null;
            placeSymbol(botButton, bot.getSymbol());
        }
    }

    // Place the symbol on the button
    private void placeSymbol(Button button, Symbol symbol) {
        button.setText(symbol.toString());
        int[] rgbSymbol = symbol.getRGB();
        button.setStyle("-fx-text-fill: rgb(" + rgbSymbol[0] + "," + rgbSymbol[1] + "," + rgbSymbol[2] + ");");
        checkWin();
    }

    // Indicate the current player by adding a CSS class
    private void indicationCurrentPlayer() {
        if (game.getCurrentPlayer().getSymbol() == player1.getSymbol()) {
            player2current.getStyleClass().add("gray");
            player1current.getStyleClass().remove("gray");
        } else {
            player1current.getStyleClass().add("gray");
            player2current.getStyleClass().remove("gray");
        }
    }

    // Returns the button based on its coordinates in the grid
    private Button getNodeByCoordinate(Integer row, Integer column) {
        for (Node node : gameBoard.getChildren()) {
            if (node instanceof Button) {
                if (GridPane.getRowIndex(node) == (row * 2) && GridPane.getColumnIndex(node) == (column * 2)) {
                    return (Button) node;
                }
            }
        }
        return null;
    }

    // Check if the game is over
    private void checkWin() {
        if (game.grid.checkWin()) {
            equalityVBox.getStyleClass().remove("gray");
            game.getCurrentPlayer().incrementScore();
            game.isOver = true;
            updateScore();
            showLine();
        } else if (game.grid.isFull()) {
            equalityVBox.getStyleClass().remove("gray");
            equalScore++;
            game.isOver = true;
            updateScore();
        } else {
            game.switchPlayer();
            indicationCurrentPlayer();
        }
    }

    // Updates scores and end-game messages
    void updateScore() {
        player1Score.setText(String.valueOf(player1.getScore()));
        if (settings.getIsOnePlayerMode()) {
            player2Score.setText(String.valueOf(bot.getScore()));
        } else {
            player2Score.setText(String.valueOf(player2.getScore()));
        }
        equalityScore.setText(String.valueOf(equalScore));
        if (settings.getIsOnePlayerMode()) {
            player2Text.setText(bot.getName());
        } else {
            player2Text.setText(player2.getName());
        }
        // If the game is over, display the end-game message
        if (game.isOver) {
            msgEndGame.setVisible(true);
            msgEndGame.setDisable(false);
            msgEndGame.setManaged(true);
            if (game.grid.checkWin()) {
                winnerText.setText(game.getCurrentPlayer().getName() + " won!");
            } else {
                winnerText.setText("Draw!");
            }
        }
    }

    // Display the winning line
    void showLine() {
        int[] res = game.grid.getWinningCases();
        for (int i = 0; i < res.length; i++) {
            res[i] *= 2;
        }
        for (int i = 0; i < gameBoard.getChildren().size(); i++) {
            if (gameBoard.getChildren().get(i) instanceof Button) {
                int row = gameBoard.getRowIndex(gameBoard.getChildren().get(i));
                int col = gameBoard.getColumnIndex(gameBoard.getChildren().get(i));
                if (row == res[0] && col == res[1] || row == res[2] && col == res[3] || row == res[4] && col == res[5]) {
                } else {
                    gameBoard.getChildren().get(i).getStyleClass().add("gray");
                }
            }
        }
    }

    @FXML
    void handleQuitterClick(ActionEvent event) {
        // Close the application
        System.exit(0);
    }

    @FXML
    void ResetClick(ActionEvent event) {
        // Reset the game
        createGame();
    }

    @FXML
    public void openRules() throws IOException {
        // Load the game rules view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Rules.fxml"));
        Parent root = loader.load();

        Stage rulesStage = new Stage();
        rulesStage.initModality(Modality.APPLICATION_MODAL);
        rulesStage.setTitle("Game Rules");
        rulesStage.setScene(new Scene(root));
        rulesStage.setResizable(false);
        rulesStage.showAndWait();
        updateScore();
    }

    @FXML
    void openSettings() throws IOException {
        // Load the game settings view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Settings.fxml"));
        Parent root = loader.load();
        SettingsController settingsController = loader.getController();

        Stage settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsController.setGameController(this);
        settingsStage.setTitle("Settings");
        settingsStage.setScene(new Scene(root));
        settingsStage.setResizable(false);

        settingsStage.showAndWait();

        if (settings.getIsOnePlayerMode()) {
            player2Text.setText(bot.getName());
        } else {
            player2Text.setText(player2.getName());
        }
        player1Text.setText(player1.getName());
        updateScore();
    }

    // Set the first player from the settings
    public void setFirstPlayer(String selectedFirstPlayer) {
        switch (selectedFirstPlayer) {
            case "Player 1" -> {
                settings.setFirstPlayer(player1);
                settings.setFirstPlayerIsRandom(false);
            }
            case "Player 2" -> {
                if (settings.getIsOnePlayerMode()) {
                    settings.setFirstPlayer(bot);
                } else {
                    settings.setFirstPlayer(player2);
                }
                settings.setFirstPlayerIsRandom(false);
            }
            case "Random" -> settings.setFirstPlayerIsRandom(true);
        }
    }

    @FXML
    void replayGame(ActionEvent event) {
        createGame();
    }

    // Reset the scores
    public void resetScore() {
        player1.resetScore();
        player2.resetScore();
        bot.resetScore();
        equalScore = 0;
        updateScore();
    }
}
