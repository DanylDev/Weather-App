package com.incode_it.incode8.weather.adapter.Weather;

import android.view.ViewGroup;

import com.incode_it.incode8.weather.models.daily_model.Daily;
import com.incode_it.incode8.weather.models.daily_model.WeatherDailyParametr;

import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.AbstractViewHolder;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Created by incode8 on 14.08.17.
 */

public class DailyWeatherRecyclerAdapter extends AbstractAdapter<ItemType, AbstractViewHolder> {

    @Override
    protected AbstractViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerItemType.PARENT.getValue()) {
            return new ParentViewHolder(this, parent);
        } else if (viewType == RecyclerItemType.CHILD.getValue()) {
            return new ChildViewHolder(parent);
        }
        return null;
    }

    @Override
    protected void onNewBindViewHolder(AbstractViewHolder holder, int position) {
        if (holder instanceof ParentViewHolder) {
            ((ParentViewHolder) holder).setData((Daily) get(position));
        } else if (holder instanceof ChildViewHolder) {
            ((ChildViewHolder) holder).setData((WeatherDailyParametr) get(position));
        }
    }

}
