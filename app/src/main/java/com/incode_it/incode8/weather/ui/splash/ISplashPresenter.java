package com.incode_it.incode8.weather.ui.splash;

import android.app.Activity;

import com.incode_it.incode8.weather.di.ActivityScope;
import com.incode_it.incode8.weather.ui.base.IBasePresenter;

import java.util.ArrayList;

/**
 * Created by incode8 on 10.08.17.
 */

@ActivityScope
public interface ISplashPresenter<V extends ISplashView> extends IBasePresenter<V> {

    void getWeatherNow(String city);

    void getWeatherForecast(String city);

    void getWeatherForecastDaily(String city);

    ArrayList<String> getUserPreference(Activity activity);

}
