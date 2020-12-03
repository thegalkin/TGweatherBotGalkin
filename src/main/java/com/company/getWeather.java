package com.company;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class getWeather {
    public static String getForecast(String longitude, String latitude, String weatherToken) throws IOException {

        String url = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&id=524901&lang=ru&appid=%s=metric", latitude, longitude, weatherToken);

        URL obj = new URL(url);

        Map<String, Map<String, Object>> map = new ObjectMapper().readValue(obj, HashMap.class);
        System.out.println(map);

        String temp = map.get("main").get("temp").toString() + "°C";
        String pressure = map.get("main").get("pressure").toString() + " мбар";
        String wet = map.get("main").get("humidity").toString() + " %";
        return String.format("%s \n%s \n%s \n", temp, pressure, wet);
    }

    //String url = "http://api.openweathermap.org/data/2.5/weather?lat=53.515214&lon=49.410492&id=524901&lang=ru&appid=5ceac6d2df7a14bffac5f541c8b422a6&units=metric";

    //URL obj = new URL(url);

    //Map<String, Map<String, Object>> map = new ObjectMapper().readValue(obj, HashMap.class);


    //String temp = map.get("main").get("temp").toString() + "°C";
   // String pressure = map.get("main").get("pressure").toString() + " мбар";
   // String wet = map.get("main").get("humidity").toString() + " %";
    //printForecast(temp, pressure, wet, "OpenWeatherMap");

}
