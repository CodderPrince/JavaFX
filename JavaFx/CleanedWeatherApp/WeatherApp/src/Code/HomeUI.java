package Code;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


public class HomeUI {

    private GridPane mainWindow;
    private DetailedWeatherWidget detailedWeatherWidget;
    private HBox sparseWidgets;
    private BorderPane timeLocationDisplay;
    private Button refreshButton;
    private Label locationLabel;
    private Label lastUpdatedTime;

    public HomeUI() {
        mainWindow = new GridPane();
        refreshButton = new Button();
        setMainWindowProperties();
        configureRefreshButton();
        addRefreshButton();
    }

    public HomeUI(List<ForecastMapping> forecastMapping, String dateTime) throws IOException {
        mainWindow = new GridPane();
        sparseWidgets = new HBox();
        refreshButton = new Button();
        locationLabel = new Label();
        lastUpdatedTime = new Label();
        setMainWindowProperties();
        configureDetailedWeatherWidget();
        configureSparseWidgets(forecastMapping);
        configureRefreshButton();
        configureLocationLabel();
        configureTimeLocationDisplay();
        configureLastUpdatedTime(dateTime);
        addDetailedWeatherWidget();
        addSparseWidgets();
        addTimeLocationDisplay();
    }
    private void configureLocationLabel() throws IOException {
        String city = FileUtilities.getCity();
        String country = FileUtilities.getCountry(FileUtilities.getAlphaTwoCode());
        if(city != null  && country != null) {
            String labelName = city + ", " + country;
            locationLabel.setText(labelName);
            locationLabel.setId("location-label");
        }
    }
    private void configureTimeLocationDisplay() {
        timeLocationDisplay = new BorderPane();
        timeLocationDisplay.setLeft(new HBox(lastUpdatedTime));
        timeLocationDisplay.setRight(new HBox(10,locationLabel,refreshButton));
    }
    private void setMainWindowProperties() {
        mainWindow.setHgap(20);
        mainWindow.setVgap(20);
        mainWindow.setPadding(new Insets(25, 25, 25, 25));
    }
    private void configureDetailedWeatherWidget() {
        CurrentWeather w = new CurrentWeather();
        detailedWeatherWidget= new DetailedWeatherWidget(w.getWeather().getWeatherIcon(),w.getWeather().getCurrTemp(),w.getWeather().getWeatherDesc(),
                w.getWeather().getMaxTemp(),w.getWeather().getCurrTempFeel(),w.getWeather().getMinTemp(),w.getWeather().getHumidity(),w.getWeather().getPressure(),
                w.getWeather().getWindDegree(),w.getWeather().getWindSpeed(), w.getWeather().getVisibility());
    }

    private void addDetailedWeatherWidget() {
        try {
            mainWindow.add(detailedWeatherWidget.getDetailedWeatherWidget(), 0, 1);
        }
        catch(Exception exception) {
            System.out.println("Widget not found HomeUI.java");
        }
    }

    private void configureLastUpdatedTime(String currentTimestamp) {
        lastUpdatedTime.setText("Last updated as of : " + currentTimestamp);
        lastUpdatedTime.setId("last-update-label");
    }
    private void configureSparseWidgets(List<ForecastMapping> forecastMapping) {
        sparseWidgets.setSpacing(58);
        for (ForecastMapping mapping : forecastMapping) {
            sparseWidgets.getChildren().add(mapping.getSparseWidget());
        }
    }
    private void addSparseWidgets() {
        mainWindow.add(sparseWidgets,0,2);
    }
    public GridPane getMainWindow() {
        return mainWindow;
    }
    public void handleSparseWidgetClicked(Event event, ForecastUI uI,ForecastMapping mapping,Stage stage, DetailedWeatherForecast detailedWeatherForecast) {
        Map<String, Weather> forecast = detailedWeatherForecast.getWeatherForecast(mapping.getDate());

        List<TemperatureHours> minTempValues = new ArrayList<>();
        List<TemperatureHours> maxTempValues = new ArrayList<>();
        for(String item : forecast.keySet()) {
            Weather weather = forecast.get(item);
            minTempValues.add(new TemperatureHours(weather.getMinTemp(),DateTimeUtilities.getTwelveHourFormat(DateTimeUtilities.extractTime(item))));
            maxTempValues.add(new TemperatureHours(weather.getMaxTemp(),DateTimeUtilities.getTwelveHourFormat(DateTimeUtilities.extractTime(item))));
        }
        String chartTitle = "3 Hourly Day Forecast";
        String xAxisTitle = "3 Hour Interval";
        String yAxisTitle = "Temperature";
        String series1Title = "Minimum Temperature";
        String series2Title = "Maximum Temperature";
        String series3Title = "Maximum and Minimum Temperature";
        TemperatureHours minTemperatureHours = minTempValues.stream().min(Comparator.comparing(TemperatureHours::getTemperature))
                .orElseThrow(NoSuchElementException::new);
        int minTemperature = minTemperatureHours.getTemperature();
        TemperatureHours maxTemperatureHours = maxTempValues.stream().max(Comparator.comparing(TemperatureHours::getTemperature))
                .orElseThrow(NoSuchElementException::new);
        int maxTemperature = maxTemperatureHours.getTemperature();
        List<Integer> minTemp = new ArrayList<>();
        List<Integer> maxTemp = new ArrayList<>();
        for(TemperatureHours item : maxTempValues) {
            maxTemp.add(item.getTemperature());
        }
        for(TemperatureHours item : minTempValues) {
            minTemp.add(item.getTemperature());
        }
        if(maxTemp.equals(minTemp)) {
            uI.setDetailedForecastWidget(new DetailedForecastWidget(chartTitle, xAxisTitle, yAxisTitle, series3Title
                    , maxTempValues, minTemperature, maxTemperature));
        }
        else {
            uI.setDetailedForecastWidget(new DetailedForecastWidget(chartTitle, xAxisTitle, yAxisTitle, series1Title, series2Title,
                    minTempValues, maxTempValues, minTemperature, maxTemperature));
        }
        uI.getDetailedForecastWidget().setForecastChartTooltips(mapping.getDate(), forecast);
        uI.addDetailedForecastWidget();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    private Image getRefreshButtonImage() {
        String iconPath = "/Resources/Images/refresh-icon.png";
        InputStream stream = getClass().getResourceAsStream(iconPath);
        Image image = new Image(stream);
        return image;
    }
    private void configureRefreshButton() {
        Image refreshButtonImage = getRefreshButtonImage();
        ImageView refreshButtonIcon = new ImageView();
        refreshButtonIcon.setImage(refreshButtonImage);
        refreshButtonIcon.setX(10);
        refreshButtonIcon.setY(10);
        refreshButtonIcon.setFitWidth(20);
        refreshButtonIcon.setPreserveRatio(true);
        refreshButton.setGraphic(refreshButtonIcon);
        refreshButton.setId("refresh-button");
        Tooltip tooltip = new Tooltip("Refresh forecast");
        tooltip.setId("tooltip");
        tooltip.setShowDelay(Duration.millis(0));
        tooltip.setShowDuration(Duration.hours(1));
        tooltip.setHideDelay(Duration.millis(0));
        refreshButton.setTooltip(tooltip);
    }
    private void addTimeLocationDisplay() {
        mainWindow.add(timeLocationDisplay, 0,0);
    }
    private void addRefreshButton() {
        mainWindow.add(refreshButton, 0, 0);
    }
    public Button getRefreshButton() {
        return refreshButton;
    }

}
