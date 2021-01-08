package com.hc.project.controller;

import com.hc.project.constants.Constants;
import com.hc.project.entity.WeatherLog;
import com.hc.project.feign.OpenWeatherFeignClient;
import com.hc.project.model.WeatherPayload;
import com.hc.project.repository.WeatherRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class WeatherController {

    @Autowired
    private OpenWeatherFeignClient feignClient;

    @Autowired
    private WeatherRepository repository;

    @GetMapping(value = "/weather/city")
    public WeatherPayload getWeather(@RequestParam String city) {
        WeatherPayload weatherPayload = new WeatherPayload();
        String json = feignClient.getWeatherByCity(city, Constants.APP_ID);
        JSONObject obj = new JSONObject(json);
        String name = obj.getString(Constants.NAME);
        int temp = obj.getJSONObject(Constants.MAIN).getInt(Constants.TEMP);
        String main = null;
        JSONArray arr = obj.getJSONArray(Constants.WEATHER);
        for (int i = 0; i < arr.length(); i++) {
            main = arr.getJSONObject(i).getString(Constants.MAIN);
        }
        weatherPayload.setActualWeather(main);
        weatherPayload.setLocation(name);
        weatherPayload.setTemperature(Integer.toString(temp));

        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        WeatherLog db = new WeatherLog();
        db.setActualWeather(main);
        db.setLocation(name);
        db.setTemperature(Integer.toString(temp));
        db.setResponseId(randomUUIDString);
        repository.save(db);
        return weatherPayload;
    }

    @GetMapping(value = "/weather")
    public List<WeatherLog> getWeather() {
    return repository.findFirst5ByOrderByIdDesc();
    }

}
