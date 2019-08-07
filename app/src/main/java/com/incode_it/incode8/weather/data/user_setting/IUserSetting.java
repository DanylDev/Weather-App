package com.incode_it.incode8.weather.data.user_setting;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by incode8 on 13.08.17.
 */

public interface IUserSetting {

    ArrayList<String> getSharedPreferences(Activity context);

    void setUserTemperature(Activity context, String temperature);

    void setUserFrequency(Activity context, String frequency);

    void setUserSpeed(Activity context, String speed);

    void setUserCity(Activity context, String city);

    void setUserRegion(Activity context, String region);

}
