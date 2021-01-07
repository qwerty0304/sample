package com.hc.project.repository;

import com.hc.project.entity.WeatherLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherLog, Long> {
}
