package com.anuragroy.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WeatherManager {
    private String city;
    private String day;
    private Integer temperature;
    private String icon;
    private String description;
    private String windSpeed;
    private String cloudiness;
    private String pressure;
    private String humidity;

    public WeatherManager(String city) {
        this.city = city;
    }

    //Build a String from the read Json file
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    //Reads and returns the JsonObject
    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    //method to get the weather of the selected city
    public void getWeather(){
        int d = 0;

        JSONObject json;
        JSONObject json_specific; //get specific data in jsonobject variable

        SimpleDateFormat df2 = new SimpleDateFormat("EEEE", Locale.ENGLISH); //Entire word/day as output
        Calendar c = Calendar.getInstance();

        //connects and asks the api to sen the json file
        try {
            json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid={YOUR_API_KEY}&lang=eng&units=metric");
        } catch (IOException e) {
            return;
        }

        //receives the particular data in the read Json File
        json_specific = json.getJSONObject("main");
        this.temperature = json_specific.getInt("temp");
        this.pressure = json_specific.get("pressure").toString();
        this.humidity = json_specific.get("humidity").toString();
        json_specific = json.getJSONObject("wind");
        this.windSpeed = json_specific.get("speed").toString();
        json_specific = json.getJSONObject("clouds");
        this.cloudiness = json_specific.get("all").toString();

        c.add(Calendar.DATE, d);
        this.day = df2.format(c.getTime());

        json_specific = json.getJSONArray("weather").getJSONObject(0);
        this.description = json_specific.get("description").toString();
        this.icon = json_specific.get("icon").toString();
    }


    //Setters for all the private fields
    public String getCity() {
        return city;
    }

    public String getDay() {
        return day;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }
}
