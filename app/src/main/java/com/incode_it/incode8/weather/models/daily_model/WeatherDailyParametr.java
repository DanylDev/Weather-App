package com.incode_it.incode8.weather.models.daily_model;

import com.incode_it.incode8.weather.adapter.Weather.RecyclerItemType;

import zlc.season.practicalrecyclerview.ItemType;

/**
 * Created by incode8 on 14.08.17.
 */

public class WeatherDailyParametr implements ItemType {

    public  String pressureDaily;

    public  String humidityDaily;

    public  String windDaily;

    public  String cloudinessDaily;

    @Override
    public int itemType() {
        return RecyclerItemType.CHILD.getValue();
    }
}
