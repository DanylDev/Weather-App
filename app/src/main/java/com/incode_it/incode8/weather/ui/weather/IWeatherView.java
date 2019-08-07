package com.incode_it.incode8.weather.ui.weather;

import com.incode_it.incode8.weather.ui.base.IBaseView;

/**
 * Created by incode8 on 08.08.17.
 */

public interface IWeatherView extends IBaseView {

    void showDayFragment();

    void showWeekFragment();

    void showThreeDayskFragment();

    void initComponent();

}
