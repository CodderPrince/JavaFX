package Code;

import javafx.scene.layout.VBox;

/**
 * Maps a sparse weather widget to its corresponding date.
 */
public class ForecastMapping {

    /*******************************************************************************************************************
     * Instance variables.
     ******************************************************************************************************************/
    private final VBox sparseWidget;
    private final String date;

    /**
     * A parameterized constructor that configures the data type.
     * @param sparseWidget The sparse weather widget.
     * @param date The weather forecast date,
     */
    public ForecastMapping(VBox sparseWidget, String date) {
        this.sparseWidget = sparseWidget;
        this.date = date;
    }

    /**
     * A getter that returns the date.
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * A getter that returns the sparse widget.
     * @return
     */
    public VBox getSparseWidget() {
        return sparseWidget;
    }
}
