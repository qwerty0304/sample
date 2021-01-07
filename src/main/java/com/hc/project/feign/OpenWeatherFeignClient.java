package com.hc.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather", url = "http://api.openweathermap.org/data/2.5/")
public interface OpenWeatherFeignClient {

    @GetMapping(value = "/weather", produces = "application/json")
    String getWeatherByCity(@RequestParam("q") String q,
                            @RequestParam("appid") String appid);
}