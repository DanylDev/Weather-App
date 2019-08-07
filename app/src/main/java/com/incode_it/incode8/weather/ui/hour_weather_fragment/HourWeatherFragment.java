package com.incode_it.incode8.weather.ui.hour_weather_fragment;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.incode8.weather.R;
import com.incode_it.incode8.weather.adapter.HourWeather.HourWeatherRecyclerAdpter;
import com.incode_it.incode8.weather.data.ConverterParametrs;
import com.incode_it.incode8.weather.di.component.ActivityComponent;
import com.incode_it.incode8.weather.models.daily_model.Daily;
import com.incode_it.incode8.weather.models.daily_model.DailyModelUi;
import com.incode_it.incode8.weather.models.daily_model.WeatherDailyParametr;
import com.incode_it.incode8.weather.ui.base.BaseFragment;
import com.incode_it.incode8.weather.ui.weather.WeatherActivity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourWeatherFragment extends BaseFragment implements IHourWeatherView {

    public static String TAG = "HourWeatherFragment";
    private static int position;
    private HourWeatherRecyclerAdpter hourWeatherRecyclerAdpter;
    private Daily daily = new Daily();
    private WeatherDailyParametr weatherDailyParametr = new WeatherDailyParametr();

    @BindView(R.id.recyclerViewHourWeather)
    RecyclerView recyclerViewHour;

    @BindView(R.id.pressure_data_daily)
    TextView pressureDataDaily;

    @BindView(R.id.humidity_data_daily)
    TextView humidityDataDaily;

    @BindView(R.id.wind_data_daily)
    TextView windDataDaily;

    @BindView(R.id.cloudiness_data_daily)
    TextView cloudinessDataDaily;

    @BindView(R.id.dateDay)
    TextView dateDayTextView;

    @Inject
    IHourWeatherPresenter<IHourWeatherView> mPresenter;

    public HourWeatherFragment() {
        // Required empty public constructor
    }

    public static HourWeatherFragment newInstance(int positionItem) {
        Bundle args = new Bundle();
        position = positionItem;
        HourWeatherFragment fragment = new HourWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hour_weather, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        daily = DailyModelUi.listDaily.get(position);
        DateFormat originalFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        DateFormat targetFormat = new SimpleDateFormat("EEE, d MMMM yyyy");
        try {
            dateDayTextView.setText(targetFormat.format(originalFormat.parse(daily.dateDaily)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        weatherDailyParametr = daily.dailyParametrs.get(0);
        recyclerViewHour.setLayoutManager(new LinearLayoutManager(view.getContext()));

        hourWeatherRecyclerAdpter = new HourWeatherRecyclerAdpter(getContext(), daily.forecastParametrs);
        recyclerViewHour.setAdapter(hourWeatherRecyclerAdpter);
        pressureDataDaily.setText(weatherDailyParametr.pressureDaily + " hPa");
        humidityDataDaily.setText(weatherDailyParametr.humidityDaily + " %");
        if(WeatherActivity.pref.get(2).equals("m/s")) {
            windDataDaily.setText(weatherDailyParametr.windDaily + " " + getString(R.string.km));
        } else {
            windDataDaily.setText(new DecimalFormat("#0.00").format(ConverterParametrs.getMilles(weatherDailyParametr.windDaily)) + " " + getString(R.string.mi));
        }
        cloudinessDataDaily.setText(weatherDailyParametr.cloudinessDaily + " %");
        return view;
    }

    @Override
    public void onError(String errorString) {

    }

    @Override
    public void onError(@StringRes int idRes) {

    }

    @Override
    public boolean networkConnected() {
        return false;
    }

    @Override
    protected void setUp(View view) {

    }

}
