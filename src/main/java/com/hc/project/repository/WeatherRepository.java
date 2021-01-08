package com.hc.project.repository;

import com.hc.project.entity.WeatherLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherLog, Long> {
    List<WeatherLog> findFirst5ByOrderByIdDesc();
}
