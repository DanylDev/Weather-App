package com.incode_it.incode8.weather.adapter.HourWeather;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.incode8.weather.R;
import com.incode_it.incode8.weather.data.ConverterParametrs;
import com.incode_it.incode8.weather.mapper.ImageMapper;
import com.incode_it.incode8.weather.models.forecast_model.Forecast;
import com.incode_it.incode8.weather.ui.weather.WeatherActivity;

import java.util.ArrayList;

import static com.incode_it.incode8.weather.utils.GetTimeNow.getTime;

/**
 * Created by incode8 on 15.08.17.
 */

public class HourWeatherRecyclerAdpter extends RecyclerView.Adapter<HourWeatherHolder>{

    private Context context;
    private ArrayList<Forecast> forecasts;
    private Forecast forecastItem = new Forecast();

    public HourWeatherRecyclerAdpter(Context context, ArrayList<Forecast> forecast){
        this.context = context;
        this.forecasts = forecast;
    }

    @Override
    public HourWeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_row, parent, false);
        return new HourWeatherHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(HourWeatherHolder holder, int position) {
        forecastItem = forecasts.get(position);
        if(WeatherActivity.pref.get(0).equals("c")) {
            holder.weatherTemp.setText(forecastItem.temperatureForecast + "\u00B0" + "C");
        } else {
            holder.weatherTemp.setText(ConverterParametrs.getFar(forecastItem.temperatureForecast) + "\u00B0" + "F");
        }
        holder.weatherImage.setImageResource(new ImageMapper(getTime()).setImage(forecastItem.iconForecast));
        holder.weatherDiscription.setText(forecastItem.dateForecast);
        if(getTime() > 19 && getTime() < 6)
            holder.weatherCard.setCardBackgroundColor(Color.parseColor("#274356"));
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }
}
