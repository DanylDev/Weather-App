package com.incode_it.incode8.weather.data.api;

import com.incode_it.incode8.weather.models.daily_model.DailyModel;
import com.incode_it.incode8.weather.models.forecast_model.ForecastdData;
import com.incode_it.incode8.weather.models.weather_model.WeatherData;
import com.incode_it.incode8.weather.utils.AppConstants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by incode8 on 09.08.17.
 */

public interface WeatherApi {

    @GET(AppConstants.QEURY_WEATHER)
    Observable<WeatherData> getDataWeather(@Query(AppConstants.CITY_NAME) String resourceName,
                                           @Query(AppConstants.UNITS_QEURY) String units,
                                           @Query(AppConstants.LANG_QEURY) String lang,
                                           @Query(AppConstants.APP_ID_QEURY) String appid);

    @GET(AppConstants.QUERY_FORECAST)
    Observable<ForecastdData> getDataForecast(@Query(AppConstants.CITY_NAME) String cityName,
                                              @Query(AppConstants.UNITS_QEURY) String units,
                                              @Query(AppConstants.LANG_QEURY) String lang,
                                              @Query(AppConstants.APP_ID_QEURY) String appid);

    @GET(AppConstants.QUERY_FORECAST_DAILY)
    Observable<DailyModel> getDataForecastDaily(@Query(AppConstants.CITY_NAME) String cityName,
                                                @Query(AppConstants.UNITS_QEURY) String units,
                                                @Query(AppConstants.LANG_QEURY) String lang,
                                                @Query(AppConstants.APP_ID_QEURY) String appid);
}
