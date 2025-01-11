package Code;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.io.IOException;

/**
 * Models the alert message pane to display application errors.
 * Uses the Alert class of JavaFX.
 */
public class ErrorMessageDialog {

    /*******************************************************************************************************************
     * Instance variables.
     ******************************************************************************************************************/
    private Alert messageDialog;

    /**
     * A non-parameterized constructor.
     * Invokes the message dialog configuration method.
     */
    public ErrorMessageDialog() {
        configureMessageDialog();
    }

    /*******************************************************************************************************************
     * Private methods.
     ******************************************************************************************************************/

    /**
     * Configures the message dialog and populates it with the relevant message, controls and styling.
     */
    private void configureMessageDialog() {
        messageDialog = new Alert(Alert.AlertType.ERROR,"", ButtonType.OK);
        DialogPane dialogPane = messageDialog.getDialogPane();
        dialogPane.getStylesheets().add("/Resources/css/styles.css");
        messageDialog.setHeaderText("The application is unable to connect to the server. Please check your internet connection.");
        messageDialog.setTitle("Weather Forecast");
        messageDialog.getButtonTypes().set(0, new ButtonType("OK", ButtonBar.ButtonData.LEFT));
    }

    /*******************************************************************************************************************
     * Public methods.
     ******************************************************************************************************************/

    /**
     * Displays the message dialog.
     * @throws IOException
     */
    public void showMessageDialog() throws IOException {
        messageDialog.showAndWait();
    }

}
