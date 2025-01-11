package Code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Models the detailed weather forecast.
 * Contains a data structure that can store n-day detailed forecast data.
 * Contains methods to populate and manipulate the data structure.
 */
public class DetailedWeatherForecast {

    /*******************************************************************************************************************
     * Instance variables.
     ******************************************************************************************************************/
    private Map<String, Map<String, Weather>> weatherForecast;

    /**
     * A non-parameterized constructor that configures instance variables and populates them.
     * Attempts to first fetch live data from the API server otherwise loads saved local data.
     * If local data is not available an exception is thrown.
     */
    public DetailedWeatherForecast() {
        weatherForecast = new HashMap<>();
        try {
            JsonNode rawForecast = fetchLiveForecast();
            cleanDetailedForecast(rawForecast);
            saveLiveForecast();
        }
        catch(IOException ioException) {
            try {
                weatherForecast = fetchLocalForecast();
            }
            catch (Exception exception) {
                System.out.println("No connection DetailedWeatherForecast.java");
            }
        }
        catch (InterruptedException interruptedException) {
            System.out.println("Interrupted at DetailedWeatherForecast.java");
        }
    }

    /*******************************************************************************************************************
     * Private methods.
     ******************************************************************************************************************/

    /**
     * Formats the fetched weather data to the required format.
     * @param rawForecast Fetched raw weather data.
     */
    private void cleanDetailedForecast(JsonNode rawForecast) {
        Map<String, Weather> temp = new TreeMap<>();
        rawForecast = rawForecast.get("list");
        for(JsonNode item : rawForecast) {
            Weather weather = new Weather();
            JsonNode extractor = item.get("main");
            weather.setCurrTemp(Math.round(extractor.get("temp").floatValue()));
            weather.setCurrTempFeel(Math.round(extractor.get("feels_like").floatValue()));
            weather.setHumidity(Math.round(extractor.get("humidity").floatValue()));
            weather.setMinTemp(Math.round(extractor.get("temp_min").floatValue()));
            weather.setMaxTemp(Math.round(extractor.get("temp_max").floatValue()));
            weather.setPressure(Math.round(extractor.get("pressure").floatValue()));
            extractor = item.get("weather").get(0);
            weather.setWeatherDesc(extractor.get("description").asText());
            weather.setWeatherIcon(extractor.get("icon").asText());
            extractor = item.get("wind");
            weather.setWindDegree(Math.round(extractor.get("deg").floatValue()));
            weather.setWindSpeed(Math.round(extractor.get("speed").floatValue()));
            weather.setVisibility(Math.round(item.get("visibility").floatValue()));
            temp.put(item.get("dt_txt").asText(), weather);
        }
        String date = DateTimeUtilities.extractDate(temp.keySet().toArray()[0].toString());
        Map<String, Weather> hourlyForecast = new TreeMap<>();
        for(String key : temp.keySet()) {
            if(DateTimeUtilities.extractDate(key).equals(date)) {
                hourlyForecast.put(key, temp.get(key));
            }
            else {
                weatherForecast.put(date, hourlyForecast);
                hourlyForecast = new TreeMap<>();
                date = DateTimeUtilities.extractDate(key);
                hourlyForecast.put(key, temp.get(key));
            }
        }
    }

    /**
     * Fetches live detailed weather data from the API server.
     * @return Returns the fetched raw forecast.
     * @throws IOException
     */
    private JsonNode fetchLiveForecast() throws IOException {
        String forecastApiKey = FileUtilities.getAPIKey("owm_API_Key");
        double latitude = FileUtilities.getLatitude();
        double longitude = FileUtilities.getLongitude();
        ObjectMapper mapper = new ObjectMapper();
        String forecastUrl = "https://api.openweathermap.org/data/2.5/forecast?lat="+latitude+"&lon="+longitude+"&units=metric&appid="+forecastApiKey;
        HttpsURLConnection connection = Connection.createConnection(forecastUrl);
        JsonNode rawForecast = mapper.readValue(connection.getInputStream(), JsonNode.class);
        return  rawForecast;
    }

    /**
     * Fetches saved detailed weather data stored on the local system.
     * @return Returns the fetched detailed weather data.
     * @throws IOException
     */
    private Map fetchLocalForecast() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String fileName = "detailed_forecast.json";
            InputStream inputStream = new FileInputStream(FileUtilities.getProjectPath() +"\\Resources\\" + fileName);
            Map<String, Map<String, Weather>> detailedForecast = mapper.readValue(inputStream, new TypeReference<>() {
            });
            return detailedForecast;
        }
        catch(IOException ioException) {
            return null;
        }
    }

    /**
     * Saves the fetched detailed weather data to a file on the local system.
     * @throws IOException
     * @throws InterruptedException
     */
    private void saveLiveForecast() throws IOException, InterruptedException {
        String fileName="detailed_forecast.json";
        FileUtilities.toJson(weatherForecast, fileName);
    }

    /*******************************************************************************************************************
     * Public methods.
     ******************************************************************************************************************/

    /**
     * A getter that returns a specific detailed weather data instance.
     * @param key A key to retrieve a specific day's data.
     * @return Returns the detailed weather forecast data.
     */
    public Map getWeatherForecast(String key) {
        return weatherForecast.get(key);
    }
}
