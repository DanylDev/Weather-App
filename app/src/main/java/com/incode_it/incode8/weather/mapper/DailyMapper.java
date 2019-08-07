package com.incode_it.incode8.weather.mapper;

import android.util.Log;

import com.incode_it.incode8.weather.models.daily_model.Daily;
import com.incode_it.incode8.weather.models.daily_model.DailyModel;
import com.incode_it.incode8.weather.models.daily_model.DailyModelUi;
import com.incode_it.incode8.weather.models.daily_model.List;
import com.incode_it.incode8.weather.models.daily_model.Temp;
import com.incode_it.incode8.weather.models.daily_model.Weather;
import com.incode_it.incode8.weather.models.daily_model.WeatherDailyParametr;
import com.incode_it.incode8.weather.models.forecast_model.Forecast;
import com.incode_it.incode8.weather.models.forecast_model.ForecastUi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by incode8 on 13.08.17.
 */

public class DailyMapper {

    private static DailyModelUi dailyModelUi = new DailyModelUi();
    private static Daily daily;
    private static Forecast forecast;

    public static DailyModelUi map(DailyModel dailyData) throws ParseException {
        dailyModelUi.listDaily = new ArrayList<>();
        java.util.List<List> dailyList = dailyData.getList();
        for(List dailyItem : dailyList){
            daily = new Daily();
            Weather daileWeather = dailyItem.getWeather().get(0);
            daily.cloudsDaily = daileWeather.getMain();
            daily.iconDaily = daileWeather.getIcon();
            Temp tempDaily = dailyItem.getTemp();
            daily.temperatureDaily = String.valueOf(tempDaily.getDay().intValue());
            long dv = Long.valueOf(String.valueOf(dailyItem.getDt().intValue()))*1000;// its need to be in milisecond
            Date df = new java.util.Date(dv);
            daily.dateDaily = new SimpleDateFormat("EEE, MMM d, ''yy").format(df);
            daily.dailyParametrs = new ArrayList<>();
            daily.forecastParametrs = new ArrayList<>();
            WeatherDailyParametr weatherDailyParametr = new WeatherDailyParametr();
            weatherDailyParametr.pressureDaily = String.valueOf(dailyItem.getPressure().intValue());
            weatherDailyParametr.humidityDaily = String.valueOf(dailyItem.getHumidity().intValue());
            weatherDailyParametr.windDaily = String.valueOf(dailyItem.getSpeed());
            weatherDailyParametr.cloudinessDaily = String.valueOf(dailyItem.getClouds().intValue());
            daily.dailyParametrs.add(weatherDailyParametr);
            for (Forecast item : ForecastUi.listForecast){
                if(item.dateForecast.contains(new SimpleDateFormat("yyyy-MM-dd").format(df))){
                    String date = item.dateForecast.substring(item.dateForecast.length() - 8, item.dateForecast.length() - 3);
                    if (!date.equals("00:00") && !date.equals("03:00")) {
                        forecast = new Forecast();
                        forecast.dateForecast = date;
                        forecast.temperatureForecast = item.temperatureForecast;
                        forecast.iconForecast = item.iconForecast;
                        forecast.cloudsForecast = item.cloudsForecast;
                        daily.forecastParametrs.add(forecast);
                    }
                }
            }
            if (daily.forecastParametrs.size() == 0){
                Log.d("Skip item","forecast parametrs null");
            } else {
                dailyModelUi.listDaily.add(daily);
            }
        }
        dailyModelUi.listDaily.remove(dailyModelUi.listDaily.size() - 1);
        return dailyModelUi;
    }

}
