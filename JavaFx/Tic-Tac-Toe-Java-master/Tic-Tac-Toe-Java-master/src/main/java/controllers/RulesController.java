package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RulesController {
    @FXML
    private Button closeButton;

    @FXML
    public void closeRules() {
        // Fermer la fenêtre modale
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
