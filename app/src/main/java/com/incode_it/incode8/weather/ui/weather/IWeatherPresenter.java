package com.incode_it.incode8.weather.ui.weather;

import android.app.Activity;

import com.incode_it.incode8.weather.di.ActivityScope;
import com.incode_it.incode8.weather.ui.base.IBasePresenter;

import java.util.ArrayList;

/**
 * Created by incode8 on 08.08.17.
 */
@ActivityScope
public interface IWeatherPresenter<V extends IWeatherView> extends IBasePresenter<V> {

    ArrayList<String> getUserPreference(Activity activity);

}
