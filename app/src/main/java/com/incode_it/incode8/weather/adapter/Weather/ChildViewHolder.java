package com.incode_it.incode8.weather.adapter.Weather;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.incode8.weather.R;
import com.incode_it.incode8.weather.models.daily_model.WeatherDailyParametr;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Created by incode8 on 14.08.17.
 */

public class ChildViewHolder extends AbstractViewHolder<WeatherDailyParametr> {

    @BindView(R.id.pressure_data_daily)
    TextView pressureDataDaily;

    @BindView(R.id.humidity_data_daily)
    TextView humidityDataDaily;

    @BindView(R.id.wind_data_daily)
    TextView windDataDaily;

    @BindView(R.id.cloudiness_data_daily)
    TextView cloudinessDataDaily;

    private Context mContext;

    ChildViewHolder(ViewGroup parent) {
        super(parent, R.layout.list_item_recycler_child);
        ButterKnife.bind(this, itemView);
        mContext = parent.getContext();
    }

    @Override
    public void setData(WeatherDailyParametr data) {
        pressureDataDaily.setText(data.pressureDaily + " hPa");
        humidityDataDaily.setText(data.humidityDaily + " %");
        windDataDaily.setText(data.windDaily + mContext.getString(R.string.km));
        cloudinessDataDaily.setText(data.cloudinessDaily + " %");
    }

}
