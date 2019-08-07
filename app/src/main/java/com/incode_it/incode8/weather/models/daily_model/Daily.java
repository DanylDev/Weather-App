package com.incode_it.incode8.weather.models.daily_model;

import com.incode_it.incode8.weather.adapter.Weather.RecyclerItemType;
import com.incode_it.incode8.weather.models.forecast_model.Forecast;

import java.util.ArrayList;

import zlc.season.practicalrecyclerview.ItemType;

/**
 * Created by incode8 on 13.08.17.
 */

public class Daily implements ItemType {

    public String cloudsDaily;

    public String temperatureDaily;

    public String iconDaily;

    public String dateDaily;

    public ArrayList<WeatherDailyParametr> dailyParametrs;

    public ArrayList<Forecast> forecastParametrs;

    public boolean isExpand;

    @Override
    public int itemType() {
        return RecyclerItemType.PARENT.getValue();
    }
}
