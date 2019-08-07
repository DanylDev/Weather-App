package com.incode_it.incode8.weather.di.component;

import com.incode_it.incode8.weather.di.ActivityScope;
import com.incode_it.incode8.weather.di.module.ActivityModule;
import com.incode_it.incode8.weather.ui.day_fragment.DayFragment;
import com.incode_it.incode8.weather.ui.hour_weather_fragment.HourWeatherFragment;
import com.incode_it.incode8.weather.ui.setting.SettingActivity;
import com.incode_it.incode8.weather.ui.splash.SplashActivity;
import com.incode_it.incode8.weather.ui.three_days.ThreeDaysFragment;
import com.incode_it.incode8.weather.ui.weather.WeatherActivity;
import com.incode_it.incode8.weather.ui.week_fragment.WeekFragment;

import dagger.Component;

/**
 * Created by incode8 on 08.08.17.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(WeatherActivity activity);

    void inject(SettingActivity activity);

    void inject(DayFragment fragment);

    void inject(WeekFragment fragment);

    void inject(ThreeDaysFragment fragment);

    void inject(HourWeatherFragment fragment);

}
