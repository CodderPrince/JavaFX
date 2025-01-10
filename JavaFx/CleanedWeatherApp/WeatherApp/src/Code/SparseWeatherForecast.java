package Code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SparseWeatherForecast {

    Map<String, SparseWeather> sparseWeatherMap;

    public SparseWeatherForecast() {
        sparseWeatherMap = new TreeMap<>();
        try {
            JsonNode rawForecast = fetchLiveForecast();
            cleanSparseForecast(rawForecast);
            saveLiveForecast();
        }
        catch(IOException ioException) {
            try {
                sparseWeatherMap = fetchLocalForecast();
            }
            catch(Exception exception) {
                System.out.println("No connection SparseWeatherForecast.java");
            }
        }
    }

    private JsonNode fetchLiveForecast() throws IOException {
        String forecastApiKey = FileUtilities.getAPIKey("owm_API_Key");
        String latitude = Double.toString(FileUtilities.getLatitude());
        String longitude = Double.toString(FileUtilities.getLongitude());
        ObjectMapper mapper = new ObjectMapper();
        String forecastUrl = "https://api.openweathermap.org/data/2.5/onecall?lat="+latitude+"&lon="+longitude+"&exclude=current,minutely,hourly,alerts&units=metric&appid="+forecastApiKey;
        HttpsURLConnection connection = Connection.createConnection(forecastUrl);
        JsonNode rawForecast = mapper.readValue(connection.getInputStream(), JsonNode.class);
        return  rawForecast;
    }
    private void saveLiveForecast() throws IOException {
        String fileName="sparse_forecast.json";
        FileUtilities.toJson(sparseWeatherMap, fileName);
    }
    private void cleanSparseForecast(JsonNode rawForecast) throws IOException {
        rawForecast = rawForecast.get("daily");
        for(JsonNode item : rawForecast) {
            SparseWeather sparseWeather = new SparseWeather();
            JsonNode extractor;
            String date = DateTimeUtilities.formatDate(item.get("dt").asLong(), FileUtilities.getTimezone());
            sparseWeather.setDayOfWeek(DateTimeUtilities.dayOfWeek(date));
            sparseWeather.setDayOfMonth(DateTimeUtilities.getDay(date));
            extractor = item.get("temp");
            sparseWeather.setMinTemperature(Math.round(extractor.get("min").floatValue()));
            sparseWeather.setMaxTemperature(Math.round(extractor.get("max").floatValue()));
            extractor = item.get("weather").get(0);
            sparseWeather.setWeatherIcon(extractor.get("icon").textValue());
            sparseWeatherMap.put(date, sparseWeather);
        }
    }
    private Map fetchLocalForecast() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String fileName = "sparse_forecast.json";
            InputStream inputStream = new FileInputStream(FileUtilities.getProjectPath()+"\\Resources\\" + fileName);
            Map<String, SparseWeather> rawForecast = mapper.readValue(inputStream, new TypeReference<>() {
            });
            return rawForecast;
        }
        catch(IOException ioException) {
            return null;
        }
    }
    public Set<String> getSparseForecastKeys() {
        return sparseWeatherMap.keySet();
    }
    public SparseWeather getSparseForecast(String key) {
        return sparseWeatherMap.get(key);
    }
}
