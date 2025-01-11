package Code;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.FileNotFoundException;

/**
 * Models a widget that displays the detailed current weather parameters.
 * Creates sub-components and methods to manipulate them.
 */
public class DetailedWeatherWidget {

    /*******************************************************************************************************************
     * Instance variables.
     ******************************************************************************************************************/
    private final String DEGREE_SYMBOL = "\u00B0";
    private FlowPane additionalWeatherGroup;
    private HBox partialTemperatureGroup;
    private HBox mainWeatherGroup;
    private ImageView weatherIcon;
    private Label windDegreeLabel;
    private Label currentTemperatureLabel;
    private Label pressureLabel;
    private Label windSpeedLabel;
    private Label humidityLabel;
    private Label visibilityLabel;
    private Label weatherDescriptionLabel;
    private Label minTemperatureLabel;
    private Label maxTemperatureLabel;
    private Label feelsLikeTempLabel;
    private String temperatureUnit;
    private VBox mainTemperatureGroup;
    private VBox additionalTempGroup;
    private VBox detailedWeatherWidget;

    /**
     * A parameterized constructor that configures controls in the widget.
     * @param iconName Name of the weather icon.
     * @param currentTemperature Current temperature in Celsius.
     * @param weatherDescription Description of the current weather.
     * @param maxTemperature Maximum temperature in Celsius.
     * @param feelsLikeTemperature Temperature feel in Celsius.
     * @param minTemperature Minimum temperature in Celsius.
     * @param humidity Humidity in %.
     * @param pressure Pressure in hPa.
     * @param windDegree Wind degree in meteorological degrees.
     * @param windSpeed  Wind speed in m/s.
     * @param visibility Visibility in metres.
     */
    public DetailedWeatherWidget(String iconName, int currentTemperature, String weatherDescription,
    int maxTemperature, int feelsLikeTemperature, int minTemperature, int humidity, int pressure, int windDegree,
    int windSpeed, int visibility) {
        try {
            this.temperatureUnit = "C";
            setWeatherIcon(iconName);
            setCurrentTemperature(currentTemperature);
            setWeatherDescription(weatherDescription);
            setMaxTemperature(maxTemperature);
            setFeelsLikeTemp(feelsLikeTemperature);
            setMinTemperature(minTemperature);
            setCurrentTempGroup();
            setAdditionalTempGroup();
            setCurrentTempWidget();
            setHumidity(humidity);
            setPressure(pressure);
            setWindDegree(windDegree);
            setWindSpeed(windSpeed);
            setVisibility(visibility);
            setAdditionalWeatherGroup();
            setDetailedWeatherWidget();
        }
        catch(FileNotFoundException fileNotFoundException) {
            System.out.println("File not found DetailedWeatherWidget.java");
        }
    }

    /*******************************************************************************************************************
     * Private methods.
     ******************************************************************************************************************/

    /**
     * Retrieves a weather icon from the local system.
     * @param iconName
     * @return
     */
    private Image getWeatherImage(String iconName) {
        String iconPath = "/Resources/Images/"+iconName+".png";
        Image image = new Image(getClass().getResourceAsStream(iconPath));
        return image;
    }

     /*******************************************************************************************************************
     * Public methods.
     ******************************************************************************************************************/

    /**
     * A getter for the detailed weather widget.
     * @return Returns the detailed weather widget.
     */
     public VBox getDetailedWeatherWidget() {
         return this.detailedWeatherWidget;
     }

    /**
     * Configures a group of temperature items namely, maximum, minimum and feels like temperature.
     */
    public void setAdditionalTempGroup() {
        additionalTempGroup = new VBox(maxTemperatureLabel, feelsLikeTempLabel, minTemperatureLabel);
        additionalTempGroup.setId("temperature-widget");
    }

    /**
     * Configures the additional weather parameter widget.
     */
    public void setAdditionalWeatherGroup() {
        additionalWeatherGroup = new FlowPane();
        additionalWeatherGroup.setHgap(20);
        additionalWeatherGroup.setVgap(10);
        additionalWeatherGroup.getChildren().add(pressureLabel);
        additionalWeatherGroup.getChildren().add(humidityLabel);
        additionalWeatherGroup.getChildren().add(windSpeedLabel);
        additionalWeatherGroup.getChildren().add(windDegreeLabel);
        additionalWeatherGroup.getChildren().add(visibilityLabel);
    }

    /**
     * Configures the main widget with other sub-components.
     * @throws FileNotFoundException
     */
    public void setCurrentTempGroup() throws FileNotFoundException {
        partialTemperatureGroup = new HBox(20,weatherIcon, currentTemperatureLabel);
        mainTemperatureGroup = new VBox(-20,partialTemperatureGroup,weatherDescriptionLabel);
        mainTemperatureGroup.setAlignment(Pos.BASELINE_CENTER);
    }

    /**
     * Sets the complete widget.
     * @throws FileNotFoundException
     */
    public void setCurrentTempWidget() throws FileNotFoundException {
        mainWeatherGroup = new HBox(20,mainTemperatureGroup,additionalTempGroup);
    }

    /**
     * Sets the current temperature label with relevant values.
     * @param currentTemperature The current temperature in Celsius.
     */
    public void setCurrentTemperature(int currentTemperature) {
        String currentTemperatureString = currentTemperature + DEGREE_SYMBOL + temperatureUnit;
        currentTemperatureLabel = new Label(currentTemperatureString);
        currentTemperatureLabel.setId("temp-label");
    }

    /**
     * Configures the final weather widget.
     */
    public void setDetailedWeatherWidget() {
        detailedWeatherWidget = new VBox(30, mainWeatherGroup, additionalWeatherGroup);
        detailedWeatherWidget.setId("detailed-weather-widget");
    }

    /**
     * Sets the feels like temperature label with relevant values.
     * @param feelsLikeTemperature The feels like temperature in Celsius.
     */
    public void setFeelsLikeTemp(int feelsLikeTemperature) {
        String feelsLikeTempString = "Feels like : " + feelsLikeTemperature + DEGREE_SYMBOL + temperatureUnit;
        feelsLikeTempLabel = new Label(feelsLikeTempString);
        feelsLikeTempLabel.setId("min-max-temp-label");
    }

    /**
     * Sets the humidity label with relevant data.
     * @param humidity The current humidity.
     */
    public void setHumidity(int humidity) {
        String humidityString = "Humidity : " + humidity + "%";
        humidityLabel = new Label(humidityString);
        humidityLabel.setId("additional-weather-parameters");
    }

    /**
     * Sets the maximum temperature label with relevant values.
     * @param maxTemperature The maximum temperature in Celsius.
     */
    public void setMaxTemperature(int maxTemperature) {
        String maxTemperatureString = "max : " + maxTemperature + DEGREE_SYMBOL + temperatureUnit;
        maxTemperatureLabel = new Label(maxTemperatureString);
        maxTemperatureLabel.setId("min-max-temp-label");
    }

    /**
     * Sets the minimum temperature label with relevant values.
     * @param minTemperature The minimum temperature in Celsius.
     */
    public void setMinTemperature(int minTemperature) {
        String minTemperatureString = "min : " + minTemperature + DEGREE_SYMBOL + temperatureUnit;
        minTemperatureLabel = new Label(minTemperatureString);
        minTemperatureLabel.setId("min-max-temp-label");
    }

    /**
     * Sets the pressure label with relevant data.
     * @param pressure The pressure in hPa.
     */
    public void setPressure(int pressure) {
        String pressureString = "Pressure : " + pressure + "hPa";
        pressureLabel = new Label(pressureString);
        pressureLabel.setId("additional-weather-parameters");
    }

    /**
     * Sets the weather description label with relevant data.
     * @param weatherDescription The weather description.
     */
    public void setWeatherDescription(String weatherDescription) {
        String weatherDescriptionString = weatherDescription;
        weatherDescriptionLabel = new Label(weatherDescriptionString);
        weatherDescriptionLabel.setId("temp-desc-label");
    }

    /**
     * Sets the weather icon.
     * @param icon The name of the weather icon file.
     * @throws FileNotFoundException
     */
    public void setWeatherIcon(String icon) throws FileNotFoundException {
        Image image = getWeatherImage(icon);
        weatherIcon = new ImageView();
        weatherIcon.setImage(image);
        weatherIcon.setX(10);
        weatherIcon.setY(10);
        weatherIcon.setFitWidth(150);
        weatherIcon.setPreserveRatio(true);
    }

    /**
     * Sets the wind degree label with the relevant data.
     * @param windDegree The wind degree.
     */
    public void setWindDegree(int windDegree) {
        String windDegreeString = "Wind Degree : " + windDegree + DEGREE_SYMBOL;
        windDegreeLabel = new Label(windDegreeString);
        windDegreeLabel.setId("additional-weather-parameters");
    }

    /**
     * Sets the wind speed label with the relevant data.
     * @param windSpeed The wind speed in m/s.
     */
    public void setWindSpeed(int windSpeed) {
        String windSpeedString = "Wind Speed : " + windSpeed + "m/s";
        windSpeedLabel = new Label(windSpeedString);
        windSpeedLabel.setId("additional-weather-parameters");
    }

    /**
     * Sets the visibility label with relevant data.
     * @param visibility The visibility in metres.
     */
    public void setVisibility(int visibility) {
        String visibilityString = "Visibility : " + visibility/1000 +"km/h";
        visibilityLabel = new Label(visibilityString);
        visibilityLabel.setId("additional-weather-parameters");
    }
}
