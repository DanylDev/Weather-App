package com.incode_it.incode8.weather.ui.splash;

import com.incode_it.incode8.weather.models.daily_model.DailyModelUi;
import com.incode_it.incode8.weather.models.forecast_model.ForecastUi;
import com.incode_it.incode8.weather.models.weather_model.WeatherUi;
import com.incode_it.incode8.weather.ui.base.IBaseView;

/**
 * Created by incode8 on 10.08.17.
 */

public interface ISplashView extends IBaseView {
    void successWeather(WeatherUi weatherUi);

    void successForecast(ForecastUi forecastUi);

    void successDaily(DailyModelUi dailyModelUi);
}
