package Code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

/**
 * Contains file management utilities relevant to the application.
 * Contains functions that read and write to files on the local system.
 */
public class FileUtilities {

    /**
     * Retrieves the alpha two code from the application's file on the local system that contains the user's location details.
     * If the file does not exist, then the data is fetched from the web.
     * If the application cannot connect to the web, then a null is returned.
     * @return Returns the alpha two country code or a null.
     * @throws FileNotFoundException
     */
    public static String getAlphaTwoCode() throws FileNotFoundException {
        String key = "country";
        String fileName = "location.json";
        String alphaTwoCode;
        ObjectMapper mapper = new ObjectMapper();
        String path = getProjectPath();
        InputStream inputStream = new FileInputStream(path+"\\Resources\\"+fileName);
        try {
            JsonNode country = mapper.readTree(inputStream);
            alphaTwoCode = country.get(key).textValue();
            return alphaTwoCode;
        }
        catch(Exception exception1) {
            try {
                Map<String, String> locationMap = LocationUtilities.getLocationDetails();
                alphaTwoCode = locationMap.get(key);
                return alphaTwoCode;
            }
            catch(Exception exception2) {
                return null;
            }
        }
    }

    /**
     * Retrieves the required API key from the API key file.
     * @param key The key to access the API key.
     * @return The required API key.
     * @throws IOException
     */
    public static String getAPIKey(String key) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("Resources/json/APIKeys.json");
        JsonNode APIKeyJSON = mapper.readTree(inputStream);
        String API_KEY = APIKeyJSON.get(key).textValue();
        return API_KEY;
    }

    /**
     * Retrieves the city name from the relevant file that contains the user's current location.
     * If the location file is not present, then the location is fetched from the web.
     * If the application is unable to connect to the web then, an exception is thrown.
     * @return Returns the city name from the user's location.
     * @throws FileNotFoundException
     */
    public static String getCity() throws FileNotFoundException {
        String fileName = "location.json";
        String key = "city";
        String cityName;
        ObjectMapper mapper = new ObjectMapper();
        String path = getProjectPath();
        InputStream inputStream = new FileInputStream(path+"\\Resources\\"+fileName);
        try {
            JsonNode city = mapper.readTree(inputStream);
            cityName = city.get(key).textValue();
            return cityName;
        }
        catch(Exception exception1) {
            try{
                Map<String, String> locationMap = LocationUtilities.getLocationDetails();
                cityName = locationMap.get(key);
                return cityName;
            }
            catch(Exception exception2) {
                return null;
            }
        }
    }

    /**
     * Retrieves the country name from a file that is mapped by the alpha two code of the country.
     * @param alpha2Code The alpha two code of a country. eg. The alpha two code for India is IN, United States is US.
     * @return Returns the country name.
     * @throws IOException
     */
    public static String getCountry(String alpha2Code) throws IOException {
        String fileName = "Countries.json";
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("Resources/json/"+fileName);
        JsonNode APIKeyJSON = mapper.readTree(inputStream);
        String countryName = APIKeyJSON.get(alpha2Code).textValue();
        return countryName;
    }

    /**
     * Retrieves the last updated timestamp from the local system.
     * The timestamp represents the last instant a live forecast was retrieved from the API server.
     * @return Returns the last updated timestamp.
     * @throws FileNotFoundException
     */
    public static String getLastUpdatedTimestamp() throws FileNotFoundException {
        String fileName = "last_updated_timestamp.txt";
        InputStream inputStream = new FileInputStream(FileUtilities.getProjectPath()+"\\Resources\\"+fileName);
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine();
    }

    /**
     * Retrieves the latitude from the application's file on the local system that contains the user's current location.
     * If the file does not exist then, the location is retrieved from the web.
     * If the application is unable to connect to the web, a 0 value is returned.
     * @return Returns the user's location latitude.
     */
    public static double getLatitude() {
        String key = "loc";
        String fileName = "location.json";
        String coordinates;
        String [] coord;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String path = getProjectPath();
            InputStream inputStream = new FileInputStream(path+"\\Resources\\"+fileName);
            JsonNode location = mapper.readTree(inputStream);
            coordinates = location.get(key).textValue();
            coord = coordinates.split(",", 2);
            return Double.parseDouble(coord[0]);
        }
        catch(Exception ioException1) {
            try {
                Map<String, String> locationMap = LocationUtilities.getLocationDetails();
                coordinates = locationMap.get(key);
                coord = coordinates.split(",", 2);
                return Double.parseDouble(coord[0]);
            }
            catch(IOException ioException2) {
                return 0;
            }
        }
    }

    /**
     * Retrieves the latitude from a string that contains both latitude and longitude.
     * @param coordinates Location coordinates.
     * @return Returns the latitude of a location.
     */
    public static double getLatitude(String coordinates) {
        String coord [] = coordinates.split(",", 2);
        return Double.parseDouble(coord[0]);
    }

    /**
     * Retrieves the longitude from the application's file on the local system that contains the user's current location.
     * If the file does not exist then, the location is retrieved from the web.
     * If the application is unable to connect to the web, a 0 value is returned.
     * @return Returns a location longitude.
     * @throws FileNotFoundException
     */
    public static double getLongitude() throws FileNotFoundException {
        String key = "loc";
        String fileName = "location.json";
        String coordinates;
        String[] coord;
        ObjectMapper mapper = new ObjectMapper();
        String path = getProjectPath();
        InputStream inputStream = new FileInputStream(path+"\\Resources\\"+fileName);
        try {
            JsonNode location = mapper.readTree(inputStream);
            coordinates = location.get(key).textValue();
            coord = coordinates.split(",", 2);
            return Double.parseDouble(coord[1]);
        }
        catch(Exception ioException1) {
            try {
                Map<String, String> locationMap = LocationUtilities.getLocationDetails();
                coordinates = locationMap.get(key);
                coord = coordinates.split(",", 2);
                return Double.parseDouble(coord[1]);
            }
            catch(IOException ioException2) {
                return 0;
            }
        }
    }

    /**
     * Retrieves the longitude from a string that contains both latitude and longitude.
     * @param coordinates Location coordinates.
     * @return Returns the location longitude.
     */
    public static double getLongitude(String coordinates) {
        String coord [] = coordinates.split(",", 2);
        return Double.parseDouble(coord[1]);
    }

    /**
     * Computes the project directory path in which the jar is being run from.
     * This utility is necessary only for the packaged jar file.
     * @return The project path directory.
     */
    public static String getProjectPath() {
        File file = new File(System.getProperty("java.class.path"));
        String projectPath = file.getAbsoluteFile().getParentFile().toString();
        return projectPath;
    }

    /**
     * Retrieves the timezone from an application file on the local system that contains the user's location.
     * If the file is unavailable, then the timezone is retrieved from the web.
     * If the application is unable to connect to the web, then a null is returned.
     * @return Returns the timezone of the current user or a null.
     * @throws FileNotFoundException
     */
    public static String getTimezone() throws FileNotFoundException {
        String key = "timezone";
        String fileName = "location.json";
        String timezone;
        ObjectMapper mapper = new ObjectMapper();
        String path = getProjectPath();
        InputStream inputStream = new FileInputStream(path+"\\Resources\\"+fileName);
        try {
            JsonNode location = mapper.readTree(inputStream);
            timezone = location.get(key).textValue();
            return timezone;
        }
        catch(Exception exception1) {
            try {
                Map<String, String> locationMap = LocationUtilities.getLocationDetails();
                timezone = locationMap.get(key);
                return  timezone;
            }
            catch (Exception exception2) {
                return null;
            }
        }
    }

    /**
     * Writes the most recent timestamp to the application's file on the local system.
     * The timestamp represents the most recent timestamp at which the live weather forecast was received.
     * @param timeStamp The most recent timestamp.
     * @throws IOException
     */
    public static void saveTimestamp(String timeStamp) throws IOException {
        String fileName = "last_updated_timestamp.txt";
        Path directoryPath = Paths.get("Resources/");
        Files.createDirectories(directoryPath);
        Files.writeString(Path.of("Resources/"+fileName),timeStamp);
    }

    /**
     * Writes data on a Map to a json file on the local system.
     * @param data The data to be written.
     * @param fileName The name of the file to write data to.
     */
    public static void toJson(Map data, String fileName)  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Path directoryPath = Paths.get("Resources/");
            Files.createDirectories(directoryPath);
            mapper.writeValue(Path.of("Resources/"+fileName).toFile(), data);
        }
        catch(IOException e) {
            System.out.println("Exception in JsonFetch.java");
        }
    }
}
