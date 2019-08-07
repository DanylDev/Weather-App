package com.incode_it.incode8.weather.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.incode8.weather.R;
import com.incode_it.incode8.weather.models.daily_model.DailyModelUi;
import com.incode_it.incode8.weather.models.forecast_model.ForecastUi;
import com.incode_it.incode8.weather.models.weather_model.WeatherUi;
import com.incode_it.incode8.weather.ui.base.BaseActivity;
import com.incode_it.incode8.weather.ui.weather.WeatherActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements ISplashView {

    public static ArrayList<String> pref = new ArrayList<>();

    @Inject
    public ISplashPresenter<ISplashView> mPresenter;

    public SplashActivity() {
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActivityComponents().inject(this);
        setUnBinder(ButterKnife.bind(this));
        pref.clear();
        pref = mPresenter.getUserPreference(this);
        mPresenter.onAttach(SplashActivity.this);
    }

    @Override
    public void successWeather(WeatherUi weatherUi) {

    }

    @Override
    public void successForecast(ForecastUi forecastUi) {
        mPresenter.getWeatherForecastDaily(pref.get(4));
    }

    @Override
    public void successDaily(DailyModelUi dailyModelUi) {
        Intent intent = WeatherActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFragmentDetached(String tag) {

    }

}
