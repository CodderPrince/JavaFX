package Code;

public class Weather {

    private int currTemp;
    private int currTempFeel;
    private int minTemp;
    private int maxTemp;
    private int pressure;
    private int humidity;
    private int windSpeed;
    private int windDegree;
    private int visibility;
    private String weatherDesc;
    private String weatherIcon;

    public int getCurrTemp() {
        return this.currTemp;
    }

    public int getCurrTempFeel() {
        return currTempFeel;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getWindDegree() {
        return windDegree;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getVisibility() {
        return visibility;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setCurrTemp(int currTemp) {
        this.currTemp = currTemp;
    }

    public void setCurrTempFeel(int currTempFeel) {
        this.currTempFeel = currTempFeel;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public void setWindDegree(int windDegree) {
        this.windDegree = windDegree;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}
