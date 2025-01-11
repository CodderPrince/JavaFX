package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsController {

    private GameController gameController;

    @FXML
    private ComboBox<String> firstPlayerComboBox;
    @FXML
    private Label labelDifficultyLevel;
    @FXML
    private Label labelPlayerCount;
    @FXML
    private ColorPicker player1Color;
    @FXML
    private ColorPicker player2Color;
    @FXML
    private VBox boxBotLevel;
    @FXML
    private Slider sliderDifficultyLevel;
    @FXML
    private Slider sliderPlayerCount;
    @FXML
    private Text player2Name;
    @FXML
    private TextField player1Pseudo;
    @FXML
    private TextField player2Pseudo;
    @FXML
    private HBox player2PseudoHBox;

    // Link the main view controller
    public void setGameController(GameController gameController) {
        // Retrieve the main view controller
        this.gameController = gameController;
        if (gameController.settings.getFirstPlayerIsRandom()) {
            firstPlayerComboBox.setValue("Random");
        } else {
            firstPlayerComboBox.setValue(gameController.settings.getFirstPlayer().getName());
        }
        init();
    }

    // Close the modal window without applying changes
    @FXML
    public void handleCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) firstPlayerComboBox.getScene().getWindow();
        stage.close();
    }

    // Close the modal window and apply changes
    @FXML
    public void handleApplyButton(ActionEvent actionEvent) {
        boolean checkIfResetScore = false;

        // Apply changes to the first player
        gameController.setFirstPlayer(firstPlayerComboBox.getValue());

        // Apply changes to the difficulty level
        int sliderDifficultyLevelValue = (int) sliderDifficultyLevel.getValue() / 10;
        if (sliderDifficultyLevelValue < 10 != (gameController.settings.getDifficultyLevel() == 1))
            checkIfResetScore = true;
        gameController.settings.setDifficultyLevel(sliderDifficultyLevelValue < 10 ? 1 : 2);

        // Apply changes to the player count
        int sliderPlayerCountValue = (int) sliderPlayerCount.getValue() / 10;
        if (sliderPlayerCountValue < 10 != gameController.settings.getIsOnePlayerMode())
            checkIfResetScore = true;
        gameController.settings.setIsOnePlayerMode(sliderPlayerCountValue < 10);

        // Reset score if the player count or difficulty level has changed
        if (checkIfResetScore) {
            gameController.resetScore();
        }

        // Apply color changes for players
        gameController.player1.symbol.setColor(player1Color.getValue());
        gameController.player2.symbol.setColor(player2Color.getValue());
        gameController.bot.symbol.setColor(player2Color.getValue());

        // Apply changes to player names
        gameController.player1.setName(player1Pseudo.getText());
        if (gameController.settings.getIsOnePlayerMode()) {
            if (gameController.settings.getDifficultyLevel() == 1) {
                gameController.bot.setName("Mattéo");
            } else {
                gameController.bot.setName("Pascal");
            }
            gameController.bot.setName(player2Pseudo.getText());
        } else {
            gameController.player2.setName(player2Pseudo.getText());
        }

        // Close the modal window after applying changes
        Stage stage = (Stage) firstPlayerComboBox.getScene().getWindow();
        stage.close();
    }

    // Initialize settings when the modal window is loaded
    private void init() {
        // Initialize the player count
        if (gameController.settings.getIsOnePlayerMode()) {
            sliderPlayerCount.setValue(sliderPlayerCount.getMin() + 10);
            boxBotLevel.setDisable(false);
        } else {
            sliderPlayerCount.setValue(sliderPlayerCount.getMax() - 10);
            boxBotLevel.setDisable(true);
        }
        // Set Player 2 name to "Bot" if in single-player mode, otherwise "Player 2"
        if ((int) sliderPlayerCount.getValue() / 10 > 10) {
            player2Name.setText("Player 2");
        } else {
            player2Name.setText("Bot");
        }
        // Initialize the difficulty level
        if (gameController.settings.getDifficultyLevel() == 1) {
            sliderDifficultyLevel.setValue(sliderDifficultyLevel.getMin() + 10);
        } else {
            sliderDifficultyLevel.setValue(sliderDifficultyLevel.getMax() - 10);
        }
        // Initialize player names
        int sliderValue1 = (int) sliderDifficultyLevel.getValue() / 10;
        labelDifficultyLevel.setText(String.valueOf(sliderValue1 < 10 ? 1 : 2));
        int sliderValue2 = (int) sliderPlayerCount.getValue() / 10;
        labelPlayerCount.setText(String.valueOf(sliderValue2 < 10 ? 1 : 2));

        // Change Player 2's name based on difficulty level
        sliderDifficultyLevel.addEventHandler(MouseEvent.MOUSE_RELEASED, (e) -> {
            int sliderValue = (int) sliderDifficultyLevel.getValue() / 10;
            labelDifficultyLevel.setText(String.valueOf(sliderValue < 10 ? 1 : 2));
            if (sliderValue < 10) {
                player2Pseudo.setText("Mattéo");
            } else {
                player2Pseudo.setText("Pascal");
            }
        });

        // Change Player 2's name based on player count
        sliderPlayerCount.addEventHandler(MouseEvent.MOUSE_RELEASED, (e) -> {
            int sliderValue = (int) sliderPlayerCount.getValue() / 10;
            labelPlayerCount.setText(String.valueOf(sliderValue < 10 ? 1 : 2));
            boxBotLevel.setDisable(sliderValue > 10);
            if (sliderValue > 10) {
                player2Name.setText("Player 2");
                player2Pseudo.setText(gameController.player2.getName());
                player2PseudoHBox.setDisable(false);
            } else {
                player2Name.setText("Bot");
                if (gameController.settings.getDifficultyLevel() == 1) {
                    player2Pseudo.setText("Mattéo");
                } else {
                    player2Pseudo.setText("Pascal");
                }
                player2PseudoHBox.setDisable(true);
            }
        });

        // Initialize player colors
        player1Color.setValue(gameController.player1.symbol.getColor());
        player2Color.setValue(gameController.player2.symbol.getColor());

        // Initialize player names
        player1Pseudo.setText(gameController.player1.getName());
        if (gameController.settings.getIsOnePlayerMode()) {
            if (gameController.settings.getDifficultyLevel() == 1) {
                player2Pseudo.setText("Mattéo");
            } else {
                player2Pseudo.setText("Pascal");
            }
            player2PseudoHBox.setDisable(true);
        } else {
            player2Pseudo.setText(gameController.player2.getName());
            player2PseudoHBox.setDisable(false);
        }
    }
}
