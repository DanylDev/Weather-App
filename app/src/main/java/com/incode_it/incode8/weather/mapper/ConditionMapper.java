package com.incode_it.incode8.weather.mapper;

import android.content.Context;

import com.example.incode8.weather.R;

/**
 * Created by incode8 on 16.08.17.
 */

public class ConditionMapper {

    private String conditionWeather;
    private Context context;

    public ConditionMapper(Context context){
        this.context = context;
    }

    public String setCondition(String clouds) {
        switch (clouds){
            case "01d":
                conditionWeather = context.getString(R.string.clear);
                break;
            case "01n":
                conditionWeather = context.getString(R.string.clear);
                break;
            case "02d":
                conditionWeather = context.getString(R.string.clouds);
                break;
            case "02n":
                conditionWeather = context.getString(R.string.clouds);
                break;
            case "03d":
                conditionWeather = context.getString(R.string.clouds);
                break;
            case "03n":
                conditionWeather = context.getString(R.string.clouds);
                break;
            case "04d":
                conditionWeather = context.getString(R.string.clouds);
                break;
            case "04n":
                conditionWeather = context.getString(R.string.clouds);
                break;
            case "09d":
                conditionWeather = context.getString(R.string.rain);
                break;
            case "09n":
                conditionWeather = context.getString(R.string.rain);
                break;
            case "10d":
                conditionWeather = context.getString(R.string.rain);
                break;
            case "10n":
                conditionWeather = context.getString(R.string.rain);
                break;
            case "11d":
                conditionWeather = context.getString(R.string.thunderstorm);
                break;
            case "11n":
                conditionWeather = context.getString(R.string.thunderstorm);
                break;
            case "13d":
                conditionWeather = context.getString(R.string.snow);
                break;
            case "13n":
                conditionWeather = context.getString(R.string.snow);
                break;
            case "50d":
                conditionWeather = context.getString(R.string.mist);
                break;
            case "50n":
                conditionWeather = context.getString(R.string.mist);
                break;
        }
        return conditionWeather;
    }
}
