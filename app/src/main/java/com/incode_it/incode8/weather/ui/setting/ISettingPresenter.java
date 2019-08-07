package com.incode_it.incode8.weather.ui.setting;

import android.app.Activity;

import com.incode_it.incode8.weather.ui.base.IBasePresenter;

/**
 * Created by incode8 on 09.08.17.
 */

public interface ISettingPresenter<V extends ISettingView> extends IBasePresenter<V> {

    void setUserFrequency(Activity activity, String frequency);

    void setUserTemperature(Activity activity, String temperature);

    void setUserSpeed(Activity activity, String speed);

    void setUserCity(Activity activity, String city);

    void setUserRegion(Activity activity, String region);

}
