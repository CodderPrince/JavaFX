package Code;

public class SparseWeather {

    private int dayOfMonth;
    private int maxTemperature;
    private int minTemperature;
    private String dayOfWeek;
    private String weatherIcon;

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }
}
