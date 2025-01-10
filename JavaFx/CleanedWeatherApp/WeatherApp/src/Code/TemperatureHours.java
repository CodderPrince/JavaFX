package Code;

public class TemperatureHours {
    private int temperature;
    private String hour;

    public TemperatureHours(int temperature, String hour) {
        this.temperature = temperature;
        this.hour = hour;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getHour() {
        return hour;
    }
}
