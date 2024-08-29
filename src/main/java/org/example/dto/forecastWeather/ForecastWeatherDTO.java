package org.example.dto.forecastWeather;

import java.util.List;
import lombok.Data;

@Data
public class ForecastWeatherDTO{
	private City city;
	private int cnt;
	private String cod;
	private int message;
	private List<ListItem> list;
}