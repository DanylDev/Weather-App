package com.incode_it.incode8.weather.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.incode_it.incode8.weather.di.ActivityScope;
import com.incode_it.incode8.weather.ui.day_fragment.DayWeatherPresenter;
import com.incode_it.incode8.weather.ui.day_fragment.IDayWeatherPresenter;
import com.incode_it.incode8.weather.ui.day_fragment.IDayWeatherView;
import com.incode_it.incode8.weather.ui.hour_weather_fragment.HourWeatherPresenter;
import com.incode_it.incode8.weather.ui.hour_weather_fragment.IHourWeatherPresenter;
import com.incode_it.incode8.weather.ui.hour_weather_fragment.IHourWeatherView;
import com.incode_it.incode8.weather.ui.setting.ISettingPresenter;
import com.incode_it.incode8.weather.ui.setting.ISettingView;
import com.incode_it.incode8.weather.ui.setting.SettingPresenter;
import com.incode_it.incode8.weather.ui.splash.ISplashPresenter;
import com.incode_it.incode8.weather.ui.splash.ISplashView;
import com.incode_it.incode8.weather.ui.splash.SplashPresenter;
import com.incode_it.incode8.weather.ui.three_days.IThreeDaysPresenter;
import com.incode_it.incode8.weather.ui.three_days.IThreeDaysView;
import com.incode_it.incode8.weather.ui.three_days.ThreeDaysPresenter;
import com.incode_it.incode8.weather.ui.weather.IWeatherPresenter;
import com.incode_it.incode8.weather.ui.weather.IWeatherView;
import com.incode_it.incode8.weather.ui.weather.WeatherPresenter;
import com.incode_it.incode8.weather.ui.week_fragment.IWeekPresenter;
import com.incode_it.incode8.weather.ui.week_fragment.IWeekView;
import com.incode_it.incode8.weather.ui.week_fragment.WeekPresenter;
import com.incode_it.incode8.weather.utils.rx.AppSchedulerProvider;
import com.incode_it.incode8.weather.utils.rx.ISchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by incode8 on 08.08.17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return  mActivity;
    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() { return new CompositeDisposable(); }

    @Provides
    @ActivityScope
    ISchedulerProvider provideSchedulerProvide() {
        return new AppSchedulerProvider();
    }

    @Provides
    @ActivityScope
    IWeatherPresenter<IWeatherView> provideWeatherPresenter(
            WeatherPresenter<IWeatherView> presenter ){
        return presenter;
    }

    @Provides
    @ActivityScope
    ISplashPresenter<ISplashView> provideSplashPresenter(
            SplashPresenter<ISplashView> presenter ){
        return presenter;
    }

    @Provides
    @ActivityScope
    ISettingPresenter<ISettingView> provideSettingPresenter(
            SettingPresenter<ISettingView> presenter ){
        return presenter;
    }

    @Provides
    IDayWeatherPresenter<IDayWeatherView> provideDayPresenter(
            DayWeatherPresenter<IDayWeatherView> presenter) {
        return presenter;
    }

    @Provides
    IWeekPresenter<IWeekView> provideWeekPresenter(
            WeekPresenter<IWeekView> presenter) {
        return presenter;
    }

    @Provides
    IThreeDaysPresenter<IThreeDaysView> provideThreeDaysPresenter(
            ThreeDaysPresenter<IThreeDaysView> presenter){
        return presenter;
    }

    @Provides
    IHourWeatherPresenter<IHourWeatherView> provideHourWeatherPresenter(
            HourWeatherPresenter<IHourWeatherView> presenter){
        return presenter;
    }

}
