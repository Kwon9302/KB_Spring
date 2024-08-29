package org.example.controller.weather;

import lombok.RequiredArgsConstructor;
import org.example.dto.forecastWeather.ForecastWeatherDTO;
import org.example.dto.weather.WeatherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(value = "*")
@RequiredArgsConstructor
@RequestMapping("/weather")
@PropertySource({"classpath:/application.properties"})
public class WeatherController {

    @Value("${weather.url}")
    private String weatherUrl;
    @Value("${forecast.url}")
    private String forecastUrl;
    @Value("${weather.api_key}")
    private String API_KEY;
    private RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/{cityname}")
    public ResponseEntity<WeatherDTO> cityWeather(@PathVariable String cityname){
        String url = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                .queryParam("q", cityname)
                .queryParam("units", "metric")
                .queryParam("APPID", API_KEY)
                .queryParam("lang", "kr")
                .toUriString();

        WeatherDTO weather = restTemplate.getForObject(url, WeatherDTO.class);
        System.out.println(weather);
        return ResponseEntity.ok(weather);
    }

    @GetMapping("/forecast/{cityname}")
    private ResponseEntity<ForecastWeatherDTO> forecastWeather(@PathVariable String cityname){
        String url = UriComponentsBuilder.fromHttpUrl(forecastUrl)
                .queryParam("q", cityname)
                .queryParam("APPID", API_KEY)
                .queryParam("units", "metric")
                .queryParam("lang", "kr")
                .toUriString();

        ForecastWeatherDTO weather = restTemplate.getForObject(url, ForecastWeatherDTO.class);
        System.out.println(weather);
        return ResponseEntity.ok(weather);
    }
}
