package hello.service;

import hello.configuration.WeatherAppProperties;
import hello.web.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class WeatherService {

    private static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";

    private RestTemplate restTemplate;

    private final String apiKey;

    @Autowired
    public WeatherService(RestTemplateBuilder restTemplateBuilder, WeatherAppProperties properties) {
        restTemplate = restTemplateBuilder.build();

        this.apiKey = properties.getApi().getKey();
    }

    public Weather getWeather(String country, String city) {
        URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
        return restTemplate.getForObject(url, Weather.class);
    }
}
