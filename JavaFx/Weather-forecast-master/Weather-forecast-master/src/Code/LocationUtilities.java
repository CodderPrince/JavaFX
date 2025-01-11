package Code;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LocationUtilities {
    public static String getUserPublicIP() throws IOException {
        String ipCheckURL = "https://checkip.amazonaws.com";
        HttpsURLConnection connection = Connection.createConnection(ipCheckURL);
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            String userPublicIP = scanner.nextLine();
            return userPublicIP;
        }
        return null;
    }
    public static Map getLocationDetails() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String userPublicIP = LocationUtilities.getUserPublicIP();
        String APIKey = FileUtilities.getAPIKey("IpInfo_API_Key");
        String locationUrl = "https://ipinfo.io/" + userPublicIP + "?token=" + APIKey;
        HttpsURLConnection connection = Connection.createConnection(locationUrl);
        Map<String, String> locationMap = mapper.readValue(connection.getInputStream(), HashMap.class);
        return locationMap;
    }
}
