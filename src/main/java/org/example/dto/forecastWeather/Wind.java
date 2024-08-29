package org.example.dto.forecastWeather;

import lombok.Data;

@Data
public class Wind{
	private int deg;
	private double speed;
	private double gust;
}