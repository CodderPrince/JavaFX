package Code;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.util.List;
import java.util.Map;

/**
 * Models the detailed forecast widget user interface.
 * Defines sub-components used to build the widget and methods to manipulate them.
 */
public class DetailedForecastWidget extends Node {

    /*******************************************************************************************************************
     * Instance variables.
     */
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private LineChart<String,Number> forecastChart;
    private XYChart.Series<String, Number> minTempSeries;
    private XYChart.Series<String, Number> maxTempSeries;

    /**
     * A parameterized constructor that configures the detailed forecast widget with same maximum and minimum temperatures.
     * @param chartTitle The title for the forecast chart graph.
     * @param xAxisTitle The title for the x-axis of the forecast graph.
     * @param yAxisTitle The title for the y-axis of the forecast graph.
     * @param maxTempSeriesTitle The title for a series for the graph legend.
     * @param maxTempValues A list of maximum temperature values for a particular day.
     * @param minTemperature The smallest temperature of a particular day.
     * @param maxTemperature The largest temperature of a particular day.
     */
    public DetailedForecastWidget(String chartTitle, String xAxisTitle, String yAxisTitle, String maxTempSeriesTitle,
                                  List<TemperatureHours> maxTempValues, int minTemperature, int maxTemperature) {
        setxAxis(xAxisTitle);
        setyAxis(yAxisTitle, minTemperature, maxTemperature);
        forecastChart = new LineChart<>(xAxis, yAxis);
        setChartDimensions();
        setChartTitle(chartTitle);
        setMaxTempSeries(maxTempValues);
        setMaxTempSeriesTitle(maxTempSeriesTitle);
        forecastChart.getData().add(maxTempSeries);
        setMinMaxCss();
    }

    /**
     * A parameterized constructor that configures the detailed forecast widget with different maximum and minimum temperatures.
     * @param chartTitle The title for the forecast chart graph.
     * @param xAxisTitle The title for the x-axis of the forecast graph.
     * @param yAxisTitle The title for the y-axis of the forecast graph.
     * @param minTempSeriesTitle The title for the minimum temperature series for the graph legend.
     * @param maxTempSeriesTitle The title for the maximum temperature series for the graph legend.
     * @param minTempValues A list of minimum temperature values for a particular day.
     * @param maxTempValues A list of maximum temperature values for a particular day.
     * @param minTemperature The smallest temperature of a particular day.
     * @param maxTemperature The smallest temperature of a particular day.
     */
    public DetailedForecastWidget(String chartTitle, String xAxisTitle, String yAxisTitle, String minTempSeriesTitle, String maxTempSeriesTitle,
            List<TemperatureHours> minTempValues, List<TemperatureHours> maxTempValues, int minTemperature, int maxTemperature) {
        setxAxis(xAxisTitle);
        setyAxis(yAxisTitle, minTemperature, maxTemperature);
        forecastChart = new LineChart<>(xAxis, yAxis);
        setChartDimensions();
        setChartTitle(chartTitle);
        setMinTempSeries(minTempValues);
        setMinTempSeriesTitle(minTempSeriesTitle);
        setMaxTempSeries(maxTempValues);
        setMaxTempSeriesTitle(maxTempSeriesTitle);
        forecastChart.getData().addAll(minTempSeries,maxTempSeries);
    }

    /*******************************************************************************************************************
     * Private methods.
     ******************************************************************************************************************/

    /**
     * Sets the size of the graph on the respective window.
     */
    private void setChartDimensions() {
        forecastChart.setPrefSize(930, 540);
        forecastChart.setMinSize(930, 540);
        forecastChart.setMaxSize(930, 540);
    }

    /**
     * Sets the graph title.
     * @param title
     */
    private void setChartTitle(String title) {
        forecastChart.setTitle(title);
    }

    /**
     * Creates, configures and populates tooltips for each plot point on the graph.
     * The tooltips contain detailed weather information.
     * @param datKey The key to identify the forecast date.
     * @param weatherData A list of three hour/day forecast details.
     */
    public void setForecastChartTooltips(String datKey, Map<String, Weather> weatherData) {
        for (XYChart.Series<String, Number> d : forecastChart.getData()) {
            for (XYChart.Data<String, Number> s : d.getData()) {
                String dateTimeKey = datKey + " " +DateTimeUtilities.getFormattedTime(s.getXValue());
                Weather weather = weatherData.get(dateTimeKey);
                Tooltip tooltip = new Tooltip("Description : " + weather.getWeatherDesc() + "\nHumidity : " + weather.getHumidity()+"%" +
                        "\nPressure : " + weather.getPressure()+"hPa" + "\nTemperature : " + s.getYValue()+"\u00B0C" +
                        "\nVisibility : " + weather.getVisibility()/1000+"km" + "\nWind Degree : " + weather.getWindDegree() +
                        "\nWind Speed : " + weather.getWindSpeed()+"m/s");
                tooltip.setId("tooltip");
                tooltip.setShowDelay(Duration.millis(0));
                tooltip.setShowDuration(Duration.hours(1));
                tooltip.setHideDelay(Duration.millis(0));
                Tooltip.install(s.getNode(), tooltip);
            }
        }
    }

    /**
     * Configures and populates the maximum temperature series.
     * @param maxTempValues A list of maximum temperatures and their respective hours.
     */
    private void setMaxTempSeries(List<TemperatureHours> maxTempValues) {
        maxTempSeries = new XYChart.Series();
        for(TemperatureHours item : maxTempValues) {
            maxTempSeries.getData().add(new XYChart.Data(item.getHour(), item.getTemperature()));
        }
    }

    /**
     * Sets the legend name of the maximum temperature series.
     * @param title The legend name.
     */
    private void setMaxTempSeriesTitle(String title) {
        maxTempSeries.setName(title);
    }

    /**
     * Styles the graph when the all minimum and their corresponding maximum temperatures are equal.
     * Changes the forecast series colour and the plot points.
     * Changes the colour of the legend.
     */
    private void setMinMaxCss() {
        Platform.runLater(() -> {
            Node chartSeriesLine = forecastChart.lookup(".default-color0.chart-series-line");
            for(XYChart.Data<String, Number> item : maxTempSeries.getData()) {
                Node symbol = item.getNode().lookup(".chart-line-symbol");
                symbol.setStyle("-fx-background-color: green, white;");
            }
            Node chartLegendSymbol = forecastChart.lookup(".default-color0.chart-legend-item-symbol");
            chartSeriesLine.setStyle("-fx-stroke: green;");
            chartLegendSymbol.setStyle("-fx-background-color: green, white;");
        });
    }

    /**
     * Configures and populates the minimum temperature series.
     * @param minTempValues A list of temperatures and their respective hours.
     */
    private void setMinTempSeries(List<TemperatureHours> minTempValues) {
        minTempSeries = new XYChart.Series();
        for(TemperatureHours item : minTempValues) {
            minTempSeries.getData().add(new XYChart.Data(item.getHour(), item.getTemperature()));
        }
    }

    /**
     * Sets the legend name of the minimum temperature series.
     * @param title The legend name.
     */
    private void setMinTempSeriesTitle(String title) {
        minTempSeries.setName(title);
    }

    /**
     * Configures the x-axis of the graph by creating a category axis and names it.
     * @param labelName The name of the x-axis.
     */
    private void setxAxis(String labelName) {
        xAxis = new CategoryAxis();
        xAxis.setLabel(labelName);
    }

    /**
     * Configures the y-axis of the graph by creating a number axis and names it.
     * @param labelName The name of the axis.
     * @param minTemperature The smallest temperature of the day.
     * @param maxTemperature The largest temperature of the day.
     */
    private void setyAxis(String labelName, int minTemperature, int maxTemperature) {
        yAxis = new NumberAxis(labelName,minTemperature-1,maxTemperature+1,1);
    }

    /*******************************************************************************************************************
     * Public methods.
     ******************************************************************************************************************/

    /**
     * A getter that is used to retrieve the forecast chart.
     * @return
     */
    public LineChart getForecastChart() {
        return forecastChart;
    }

    @Override
    public Node getStyleableNode() {
        return null;
    }
}
