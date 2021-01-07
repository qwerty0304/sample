package com.hc.project.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class WeatherLog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private String responseId;
    private String location;
    private String actualWeather;
    private String temperature;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTimeInserted;
}
