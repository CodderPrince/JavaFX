package Code;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SparseForecastWidget {

    private final String DEGREE_SYMBOL = "\u00B0";
    private HBox dayDate;
    private Label dayOfMonthLabel;
    private Label minTemperatureLabel;
    private Label maxTemperatureLabel;
    private ImageView weatherIcon;
    private Label dayOfWeekLabel;
    private VBox sparseForecast;
    private String temperatureUnit;

    public SparseForecastWidget(int dayOfMonth, int minTemperature, int maxTemperature, String weatherIcon, String dayOfWeek) {
        setTemperatureUnit();
        setDayOfMonth(dayOfMonth);
        setMinTemperature(minTemperature);
        setMaxTemperature(maxTemperature);
        setWeatherIcon(weatherIcon);
        setDayOfWeekLabel(dayOfWeek);
        setDayDate();
        setSparseForecastWidget();
    }
    private void setTemperatureUnit() {
        this.temperatureUnit = "C";
    }
    private Image getWeatherImage(String iconName) {
        String iconPath = "/Resources/Images/"+iconName+".png";
        Image image = new Image(getClass().getResourceAsStream(iconPath));
        return image;
    }
    private void setDayOfMonth(int dayOfMonth) {
        dayOfMonthLabel = new Label(Integer.toString(dayOfMonth));
        dayOfMonthLabel.setId("sparse-forecast-date");
    }
    private void setDayOfWeekLabel(String dayOfWeek) {
        String initial = DateTimeUtilities.dayOfWeek(DateTimeUtilities.getCurrentDate());
        if (dayOfWeek.equals(initial)) {
            String text = "TODAY";
            dayOfWeekLabel = new Label(text);
        }
        else {
            dayOfWeekLabel = new Label(DateTimeUtilities.getDayOfWeekInitial(dayOfWeek));
        }
        dayOfWeekLabel.setId("sparse-forecast-date");
    }
    private void setDayDate() {
        dayDate = new HBox(05, dayOfWeekLabel, dayOfMonthLabel);
        dayDate.setAlignment(Pos.TOP_CENTER);
    }
    public void setWeatherIcon(String icon) {
        Image image = getWeatherImage(icon);
        weatherIcon = new ImageView();
        weatherIcon.setImage(image);
        weatherIcon.setX(10);
        weatherIcon.setY(10);
        weatherIcon.setFitWidth(60);
        weatherIcon.setPreserveRatio(true);
    }
    public void setMaxTemperature(int maxTemperature) {
        String maxTemperatureString = "max : " + maxTemperature + DEGREE_SYMBOL + temperatureUnit;
        maxTemperatureLabel = new Label(maxTemperatureString);
        maxTemperatureLabel.setId("min-max-temp-label");
    }
    public void setMinTemperature(int minTemperature) {
        String minTemperatureString = "min : " + minTemperature + DEGREE_SYMBOL + temperatureUnit;
        minTemperatureLabel = new Label(minTemperatureString);
        minTemperatureLabel.setId("min-max-temp-label");
    }
    private void setSparseForecastWidget() {
        sparseForecast = new VBox(dayDate,weatherIcon,maxTemperatureLabel,minTemperatureLabel);
        sparseForecast.setAlignment(Pos.BASELINE_CENTER);
        sparseForecast.setId("sparse-forecast-widget");
    }
    public VBox getSparseForecastWidget() {
        return this.sparseForecast;
    }

}
