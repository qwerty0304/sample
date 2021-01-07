package com.hc.project.model;

import lombok.Data;

@Data
public class WeatherPayload {
    private String location;
    private String actualWeather;
    private String temperature;
}
