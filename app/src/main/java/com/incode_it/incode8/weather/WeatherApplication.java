package com.incode_it.incode8.weather;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.example.incode8.weather.BuildConfig;
import com.incode_it.incode8.weather.di.component.ApplicationComponent;import com.incode_it.incode8.weather.di.component.DaggerApplicationComponent;
import com.incode_it.incode8.weather.di.module.ApplicationModule;
import com.incode_it.incode8.weather.utils.AppLogger;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by incode8 on 08.08.17.
 */

public class WeatherApplication extends Application {

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public ApplicationComponent getComponent(){
        return mApplicationComponent;
    }

}
