package com.incode_it.incode8.weather.mapper;

import com.incode_it.incode8.weather.models.weather_model.Clouds;
import com.incode_it.incode8.weather.models.weather_model.Main;
import com.incode_it.incode8.weather.models.weather_model.Weather;
import com.incode_it.incode8.weather.models.weather_model.WeatherData;
import com.incode_it.incode8.weather.models.weather_model.WeatherUi;
import com.incode_it.incode8.weather.models.weather_model.Wind;

import java.util.List;

/**
 * Created by incode8 on 10.08.17.
 */

public class WeatherMapper {

    public static WeatherUi weatherUi = new WeatherUi();

    public static WeatherUi map(WeatherData weatherData){
        Main main = weatherData.getMain();
        List<Weather> weatherList = weatherData.getWeather();
        weatherUi.temperature = String.valueOf(main.getTemp().intValue());
        Weather weather = weatherList.get(0);
        weatherUi.clouds = weather.getMain();
        weatherUi.icon = weather.getIcon();
        weatherUi.pressure = String.valueOf(main.getPressure().intValue());
        weatherUi.humidity = String.valueOf(main.getHumidity().intValue());
        Wind wind = weatherData.getWind();
        weatherUi.wind = String.valueOf(wind.getSpeed());
        Clouds clouds = weatherData.getClouds();
        weatherUi.cloudiness = String.valueOf(clouds.getAll().intValue());

        return weatherUi;
    }

}
