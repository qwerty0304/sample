package com.hc.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data

public class WeatherLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String responseId;
    private String location;
    private String actualWeather;
    private String temperature;
    @Column(name = "d_time_inserted", columnDefinition = "TIMESTAMP")
    private LocalDateTime dTimeInserted;

}
