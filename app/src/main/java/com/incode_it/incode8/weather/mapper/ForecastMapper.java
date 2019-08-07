package com.incode_it.incode8.weather.mapper;

import com.incode_it.incode8.weather.models.forecast_model.Forecast;
import com.incode_it.incode8.weather.models.forecast_model.ForecastUi;
import com.incode_it.incode8.weather.models.forecast_model.ForecastdData;
import com.incode_it.incode8.weather.models.forecast_model.List;
import com.incode_it.incode8.weather.models.forecast_model.Main;
import com.incode_it.incode8.weather.models.forecast_model.Weather;

import java.util.ArrayList;

/**
 * Created by incode8 on 10.08.17.
 */

public class ForecastMapper {

    private static ForecastUi forecastUi = new ForecastUi();
    private static Forecast forecast;

    public static ForecastUi map(ForecastdData weatherData) {
        java.util.List<List> listforecast = weatherData.getList();
        forecastUi.listForecast = new ArrayList<>();
        for(List list : listforecast){
                forecast = new Forecast();
                forecast.dateForecast = list.getDtTxt();
                Weather weather = list.getWeather().get(0);
                forecast.cloudsForecast = weather.getMain();
                forecast.iconForecast = weather.getIcon();
                forecast.dateForecast = list.getDtTxt();
                Main main = list.getMain();
                forecast.temperatureForecast = String.valueOf(main.getTemp().intValue());
                forecastUi.listForecast.add(forecast);

        }
        return forecastUi;
    }
}
