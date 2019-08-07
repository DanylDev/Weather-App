package com.incode_it.incode8.weather.data;

import android.app.Activity;

import com.incode_it.incode8.weather.data.user_setting.IUserSetting;
import com.incode_it.incode8.weather.models.daily_model.DailyModelUi;
import com.incode_it.incode8.weather.models.forecast_model.ForecastUi;
import com.incode_it.incode8.weather.models.weather_model.WeatherUi;

import java.util.ArrayList;

import io.reactivex.Observable;


/**
 * Created by incode8 on 10.08.17.
 */

public interface IDataManager extends IUserSetting {

    Observable<WeatherUi> getDataWeatherNow(String city);

    Observable<ForecastUi>  getDataWeatherForecast(String city);

    Observable<DailyModelUi> getDataWeatherDaily(String city);

    @Override
    ArrayList<String> getSharedPreferences(Activity context);


}
