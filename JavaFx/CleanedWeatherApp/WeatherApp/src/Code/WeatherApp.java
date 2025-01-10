package Code;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This class is the entry point of the application.
 * The JavaFX stages are configured here.
 * The event handlers are specified in this class.
 *
 * @author Pritesh Parmar
 */
public class WeatherApp extends Application {

    private void configurePrimaryStage(Stage primaryStage, HomeUI homeUI) {
        primaryStage.setTitle("Weather Forecast");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(WeatherApp.class.getResourceAsStream("/Resources/Images/logo.png")));
        Scene scene = new Scene(homeUI.getMainWindow(),1000, 600);
        scene.getStylesheets().add("/Resources/css/styles.css");
        homeUI.getMainWindow().getStyleClass().add("grid");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void configureSecondaryStage(Stage secondaryStage, ForecastUI forecastUI) {
        secondaryStage.setTitle("Weather Forecast");
        secondaryStage.setResizable(false);
        secondaryStage.getIcons().add(new Image(WeatherApp.class.getResourceAsStream("/Resources/Images/logo.png")));
        Scene scene = new Scene(forecastUI.getDetailedForecastWindow(), 1000, 600);
        scene.getStylesheets().add("/Resources/css/styles.css");
        forecastUI.getDetailedForecastWindow().getStyleClass().add("grid");
        secondaryStage.setScene(scene);
    }

    public void configureRefreshBtnEvent(HomeUI homeUI, Stage primaryStage) {
        homeUI.getRefreshButton().setOnMouseClicked(refreshBtnClickEvent -> {
            try {
                loadApplication(primaryStage);
            } catch (IOException ioException) {
                configureRefreshBtnEvent(homeUI, primaryStage);
                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                try {
                    errorMessageDialog.showMessageDialog();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void configurePrimaryEventHandlers(List<ForecastMapping> forecastMapping, HomeUI homeUI, ForecastUI forecastUI, Stage primaryStage, Stage secondaryStage,
                                               DetailedWeatherForecast detailedWeatherForecast) {
        for(ForecastMapping mapping : forecastMapping) {
            mapping.getSparseWidget().setOnMouseClicked(widgetClickEvent -> {
                homeUI.handleSparseWidgetClicked(widgetClickEvent, forecastUI,mapping,secondaryStage, detailedWeatherForecast);
            });
        }
        configureRefreshBtnEvent(homeUI, primaryStage);
    }
    private void configureSecondaryEventHandlers(ForecastUI uI, Stage stage) {
        uI.getBackButton().setOnMouseClicked(btnClickEvent -> {
            uI.handleBackButtonClicked(btnClickEvent, stage);
        });
    }

    private void loadApplication(Stage stage) throws IOException {
        getLocation();
        loadWidgets(stage, getDateTime());
    }
    private void getLocation() throws IOException {
        Location userLocation = new Location();
        userLocation.getLocation();
    }
    private String getDateTime() throws IOException {
        String dateTime = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        FileUtilities.saveTimestamp(dateTime);
        return dateTime;
    }

    private void loadWidgets(Stage primaryStage, String dateTime) throws IOException {
        SparseForecastWidgets sparseForecastWidgets = new SparseForecastWidgets();
        DetailedWeatherForecast detailedWeatherForecast = new DetailedWeatherForecast();
        List<ForecastMapping> forecastMapping = sparseForecastWidgets.getSparseWidgets();
        HomeUI homeUI = new HomeUI(sparseForecastWidgets.getSparseWidgets(), dateTime);
        ForecastUI forecastUI = new ForecastUI();
        Stage secondaryStage = new Stage();
        configurePrimaryStage(primaryStage, homeUI);
        configureSecondaryStage(secondaryStage, forecastUI);
        configurePrimaryEventHandlers(forecastMapping, homeUI, forecastUI, primaryStage,secondaryStage, detailedWeatherForecast);
        configureSecondaryEventHandlers(forecastUI, primaryStage);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            loadApplication(primaryStage);
        }
        catch(Exception exception) {
            try {
                loadWidgets(primaryStage, FileUtilities.getLastUpdatedTimestamp());
            }
            catch(Exception exception2) {
                try {
                    HomeUI homeUI = new HomeUI();
                    configurePrimaryStage(primaryStage, homeUI);
                    configureRefreshBtnEvent(homeUI, primaryStage);
                    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                    errorMessageDialog.showMessageDialog();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
