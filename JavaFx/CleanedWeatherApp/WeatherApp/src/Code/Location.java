package Code;

import java.io.IOException;
import java.util.Map;

public class Location {

    private String city;
    private String country;
    private double latitude;
    private double longitude;

    public void getLocation() throws IOException {
        Map<String, String> locationMap = LocationUtilities.getLocationDetails();
        this.latitude = FileUtilities.getLatitude(locationMap.get("loc"));
        this.longitude = FileUtilities.getLongitude(locationMap.get("loc"));
        this.city = locationMap.get("city");
        this.country = FileUtilities.getCountry(locationMap.get("country"));
        String location = "location.json";
        FileUtilities.toJson(locationMap,location);
    }



}
