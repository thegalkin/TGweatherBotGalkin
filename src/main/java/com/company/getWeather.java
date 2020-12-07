package com.company;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class getWeather {
    public static String getForecast(String longitude, String latitude, String weatherToken) throws IOException {

        String url = String.format("http://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&id=524901&lang=ru&appid=%s=metric", latitude, longitude, weatherToken);

        URL obj = new URL(url);
        ArrayList<Map<String, Map<String, Object>>> hourlyArray = (ArrayList) new ObjectMapper().readValue(obj, HashMap.class).get("list");
        ArrayList<Map<String, String>> timeHash = (ArrayList) new ObjectMapper().readValue(obj, HashMap.class).get("list");
        ArrayList<Map<String, ArrayList<Map<String, Object>>>> weatherHash = (ArrayList) new ObjectMapper().readValue(obj, HashMap.class).get("list");
        String outputString = "";
        for (int i = 0; i < 8; i++) {

            String time = "⏰" + timeHash.get(i).get("dt_txt").toString().substring(11, 16);
            String weather = "🌦" + weatherHash.get(i).get("weather").get(0).get("description").toString();

            Map<String, Object> currentHour = hourlyArray.get(i).get("main");
            String temp = "🌡" + currentHour.get("temp").toString() + "°C";
            String pressure = "🧠" + currentHour.get("pressure").toString() + " мбар";
            String humidity = "💧" + currentHour.get("humidity").toString() + " %";

            outputString += String.format("%s\n%s\n%s\n%s\n%s", time, weather, temp, pressure, humidity) + "\n\n";

        }

        return outputString;
    }
}
