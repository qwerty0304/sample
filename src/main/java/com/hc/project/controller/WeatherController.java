package com.hc.project.controller;

import com.hc.project.feign.OpenWeatherFeignClient;
import com.hc.project.model.WeatherPayload;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private OpenWeatherFeignClient feignClient;

    @GetMapping(value = "/weather/city")
    public WeatherPayload getWeather(@RequestParam String city) {
        WeatherPayload weatherPayload = new WeatherPayload();
        String json = feignClient.getWeatherByCity(city, "74ead9cc2486e7771e94afa0876fdbbd");
        JSONObject obj = new JSONObject(json);
        String name = obj.getString("name");
        int temp = obj.getJSONObject("main").getInt("temp");
        String main = null;
        JSONArray arr = obj.getJSONArray("weather");
        for (int i = 0; i < arr.length(); i++) {
            main = arr.getJSONObject(i).getString("main");
        }
        weatherPayload.setActualWeather(main);
        weatherPayload.setLocation(name);
        weatherPayload.setTemperature(Integer.toString(temp));
        return weatherPayload;
    }

    @GetMapping(value = "/weather")
    public void getWeather() {

    }
}
