package Code;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Models the user interface that displays the detailed weather widget.
 * Contains methods that configure, populate and manipulate components in the UI.
 */
public class ForecastUI {

    /*******************************************************************************************************************
     * Instance variables.
     ******************************************************************************************************************/
    private GridPane detailedForecastWindow;
    private Button backButton;
    private DetailedForecastWidget detailedForecastWidget;

    /**
     * A non-parameterized constructor that invokes configuration methods.
     */
    public ForecastUI() {
        configureDetailedForecastWindow();
        configureBackButton();
        addBackButton();
    }

    /*******************************************************************************************************************
     * Private methods.
     ******************************************************************************************************************/

    /**
     * Adds the back button to the user interface.
     */
    private void addBackButton() {
        detailedForecastWindow.add(backButton,0,0);
    }

    /**
     * Configures a button used to navigate back to the main window.
     */
    private void configureBackButton() {
        String buttonName = "Back";
        backButton = new Button(buttonName);
        backButton.setId("back-button");
    }

    /**
     * Configures the main window of the user interface.
     */
    private void configureDetailedForecastWindow() {
        detailedForecastWindow = new GridPane();
        detailedForecastWindow.setPadding(new Insets(25, 25, 25, 25));
    }

    /*******************************************************************************************************************
     * Public methods.
     ******************************************************************************************************************/

    /**
     * Adds the detailed forecast widget to the user interface.
     */
    public void addDetailedForecastWidget() {
        detailedForecastWindow.add(detailedForecastWidget.getForecastChart(),0,1);
        GridPane.setRowIndex(detailedForecastWidget, 1);
    }

    /**
     * A getter that returns the back button.
     * @return The back button.
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * A getter that returns the detailed forecast widget.
     * @return Returns the detailed forecast widget.
     */
    public DetailedForecastWidget getDetailedForecastWidget() {
        return detailedForecastWidget;
    }

    /**
     * A getter that returns the user interface.
     * @return The user interface.
     */
    public GridPane getDetailedForecastWindow() {
        return detailedForecastWindow;
    }

    /**
     * Handles the back button click event.
     * Hides the current user interface and displays the main user interface.
     * @param event The button clicked event.
     * @param stage The main user interface.
     */
    public void handleBackButtonClicked(Event event, Stage stage) {
        backButton.setOnMouseClicked(btnClickEvent -> {
            removeDetailedForecastWidget();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            stage.show();
        });
    }

    /**
     * Removes the detailed forecast widget from the user interface.
     */
    public void removeDetailedForecastWidget() {
        detailedForecastWindow.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
    }

    /**
     * Configures the detailed forecast widget from the user interface.
     * @param detailedForecastWidget
     */
    public void setDetailedForecastWidget(DetailedForecastWidget detailedForecastWidget) {
        this.detailedForecastWidget = detailedForecastWidget;
    }
}
