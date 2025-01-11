package Code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to fetch, process, save and retrieve(locally) current weather details.
 * It also initializes the Weather object for use.
 * Methods for fetching live data as well as methods for fetching local data are specified.
 */
public class CurrentWeather {

    /*******************************************************************************************************************
     * Instance variables.
     ******************************************************************************************************************/
    private Weather weather;

    /**
     * A class constructor that initializes the 'Weather' instance.
     * The constructor first invokes methods to initialize the class with live data.
     * If a connection fails to establish, then methods are invoked to retrieve local data.
     * If local data is not available, an IOException is thrown.
     */
    public CurrentWeather() {
        weather = new Weather();
        try {
            JsonNode rawWeather = getCurrentWeatherData();
            cleanCurrentWeatherData(rawWeather);
            saveCurrentWeather();
        }
        catch(IOException ioException) {
            try {
                weather = getLocalCurrentWeather();
            }
            catch(Exception Exception) {
                System.out.println("No connection CurrentWeather.java");
            }
        }
        catch (InterruptedException interruptedException) {
                System.out.println("Interrupted CurrentWeather.java");
        }
    }

    /*******************************************************************************************************************
     * Private methods.
     ******************************************************************************************************************/

    /**
     * Cleans current weather json data fetched from the API server.
     * Non relevant data is removed by filtering keys read from a text file. The text file contains keys of unwanted fields.
     * @param weather Contains raw current weather json data.
     * @throws IOException
     */
    private void cleanCurrentWeatherData(JsonNode weather) throws IOException {
        String textFile = "unwanted.txt";
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("Resources/textfiles/" + textFile);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Set<String> keys = new HashSet<>();
        String string;
        while((string = bufferedReader.readLine()) != null) {
            keys.add(string);
        }
        for(String item : keys) {
            if (weather.has(item)) {
                ((ObjectNode)weather).remove(item);
            }
        }
        JsonNode extractor = weather.get("weather");
        extractor = extractor.get(0);
        String weatherDesc = extractor.get("description").asText();
        this.weather.setWeatherDesc(weatherDesc);
        String weatherIcon = extractor.get("icon").asText();
        this.weather.setWeatherIcon(weatherIcon);
        extractor = weather.get("main");
        int currTemp = Math.round(extractor.get("temp").floatValue());
        this.weather.setCurrTemp(currTemp);
        int currTempFeel = Math.round(extractor.get("feels_like").floatValue());
        this.weather.setCurrTempFeel(currTempFeel);
        int minTemp = Math.round(extractor.get("temp_min").floatValue());
        this.weather.setMinTemp(minTemp);
        int maxTemp = Math.round(extractor.get("temp_max").floatValue());
        this.weather.setMaxTemp(maxTemp);
        int pressure = Math.round(extractor.get("pressure").floatValue());
        this.weather.setPressure(pressure);
        int humidity = Math.round(extractor.get("humidity").floatValue());
        this.weather.setHumidity(humidity);
        int visibility = weather.get("visibility").asInt();
        this.weather.setVisibility(visibility);
        extractor = weather.get("wind");
        int windSpeed = Math.round(extractor.get("speed").floatValue());
        this.weather.setWindSpeed(windSpeed);
        int windDegree = Math.round(extractor.get("deg").floatValue());
        this.weather.setWindDegree(windDegree);
    }

    /**
     * Fetches live current weather data from the API server.
     * @return Returns a 'Weather' instance.
     * @throws IOException
     */
    private JsonNode getCurrentWeatherData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String APIKey = FileUtilities.getAPIKey("owm_API_Key");
        String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?lat="+ FileUtilities.getLatitude()+"&lon="+ FileUtilities.getLongitude()+"&units=metric"+"&appid="+APIKey;
        HttpsURLConnection connection = Connection.createConnection(weatherUrl);
        JsonNode weather = mapper.readValue(connection.getInputStream(), JsonNode.class);
        return weather;
    }

    /**
     * Retrieves a local copy of the current weather.
     * @return Returns a 'Weather' instance.
     */
    private Weather getLocalCurrentWeather() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String fileName = "current_weather.json";
            InputStream inputStream = new FileInputStream(FileUtilities.getProjectPath()+"\\Resources\\" + fileName);
            Weather currentWeather = mapper.readValue(inputStream, Weather.class);
            return currentWeather;
        }
        catch(IOException ioException) {
            return null;
        }
    }

    /**
     * Writes the processed weather data to a file for future use in case live data is unavailable.
     * @throws IOException
     * @throws InterruptedException
     */
    private void saveCurrentWeather() throws IOException, InterruptedException {
        Map<String, String> currentWeather = new HashMap<>();
        String fileName="current_weather.json";
        currentWeather.put(CurrentWeatherKeys.valueOf("currTemp").toString(),Integer.toString(weather.getCurrTemp()));
        currentWeather.put(CurrentWeatherKeys.valueOf("currTempFeel").toString(),Integer.toString(weather.getCurrTempFeel()));
        currentWeather.put(CurrentWeatherKeys.valueOf("humidity").toString(),Integer.toString(weather.getHumidity()));
        currentWeather.put(CurrentWeatherKeys.valueOf("maxTemp").toString(),Integer.toString(weather.getMaxTemp()));
        currentWeather.put(CurrentWeatherKeys.valueOf("minTemp").toString(),Integer.toString(weather.getMinTemp()));
        currentWeather.put(CurrentWeatherKeys.valueOf("pressure").toString(),Integer.toString(weather.getPressure()));
        currentWeather.put(CurrentWeatherKeys.valueOf("visibility").toString(),Integer.toString(weather.getVisibility()));
        currentWeather.put(CurrentWeatherKeys.valueOf("weatherDesc").toString(),weather.getWeatherDesc());
        currentWeather.put(CurrentWeatherKeys.valueOf("weatherIcon").toString(),weather.getWeatherIcon());
        currentWeather.put(CurrentWeatherKeys.valueOf("windDegree").toString(),Integer.toString(weather.getWindDegree()));
        currentWeather.put(CurrentWeatherKeys.valueOf("windSpeed").toString(),Integer.toString(weather.getWindSpeed()));
        FileUtilities.toJson(currentWeather, fileName);
    }

    /*******************************************************************************************************************
     * Public methods.
    *******************************************************************************************************************/

    /**
     * A getter that returns a 'Weather' instance.
     * @return Returns a 'Weather' instance.
     */
    public Weather getWeather() {
        return weather;
    }
}
