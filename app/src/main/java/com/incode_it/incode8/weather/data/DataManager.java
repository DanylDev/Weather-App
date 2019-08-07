package com.incode_it.incode8.weather.data;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.incode_it.incode8.weather.data.api.WeatherApi;
import com.incode_it.incode8.weather.data.user_setting.IUserSetting;
import com.incode_it.incode8.weather.mapper.DailyMapper;
import com.incode_it.incode8.weather.mapper.ForecastMapper;
import com.incode_it.incode8.weather.mapper.WeatherMapper;
import com.incode_it.incode8.weather.models.daily_model.DailyModel;
import com.incode_it.incode8.weather.models.daily_model.DailyModelUi;
import com.incode_it.incode8.weather.models.forecast_model.ForecastUi;
import com.incode_it.incode8.weather.models.forecast_model.ForecastdData;
import com.incode_it.incode8.weather.models.weather_model.WeatherData;
import com.incode_it.incode8.weather.models.weather_model.WeatherUi;
import com.incode_it.incode8.weather.utils.AppConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by incode8 on 10.08.17.
 */

public class DataManager implements IDataManager, IUserSetting {

    private WeatherApi apiService;
    private String defaultCity;
    private Context mContext;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private Location MobLoc;

    private final String TEMPERATURE = "temperature";
    private final String FREQUENCY = "frequency";
    private final String SPEED = "speed";
    private final String CITY = "city";
    private final String REGION = "region";
    private Handler textViewHandler;

    @Inject
    DataManager(WeatherApi retrofit) {
        this.apiService = retrofit;
    }

    @Override
    public Observable<WeatherUi> getDataWeatherNow(String city) {
        return apiService.getDataWeather(city, AppConstants.UNITS, AppConstants.LANG, AppConstants.APP_ID)
                .map(new Function<WeatherData, WeatherUi>() {
                    @Override
                    public WeatherUi apply(@NonNull WeatherData weatherData) throws Exception {
                        return WeatherMapper.map(weatherData);
                    }
                });

    }

    @Override
    public Observable<ForecastUi> getDataWeatherForecast(String city) {
        return apiService.getDataForecast(city, AppConstants.UNITS, AppConstants.LANG, AppConstants.APP_ID)
                .map(new Function<ForecastdData, ForecastUi>() {
                    @Override
                    public ForecastUi apply(@NonNull ForecastdData weatherData) throws Exception {
                        return ForecastMapper.map(weatherData);
                    }
                });
    }

    @Override
    public Observable<DailyModelUi> getDataWeatherDaily(String city) {
        return apiService.getDataForecastDaily(city, AppConstants.UNITS, AppConstants.LANG, AppConstants.APP_ID)
                .map(new Function<DailyModel, DailyModelUi>() {
                    @Override
                    public DailyModelUi apply(@NonNull DailyModel dailyData) throws Exception {
                        return DailyMapper.map(dailyData);
                    }
                });
    }

    @Override
    public ArrayList<String> getSharedPreferences(Activity context) {
        mContext = context;
        ArrayList<String> userPrefference = new ArrayList<>();
        SharedPreferences sharedPref = context.getSharedPreferences("setting_user", Context.MODE_PRIVATE);
        String defaultTemperature = "c";
        String defaultFrequency = "day";
        String defoultSpeed = "m/s";
        getLocation(context);
        String temperature = sharedPref.getString( TEMPERATURE, defaultTemperature);
        String frequency = sharedPref.getString( FREQUENCY, defaultFrequency);
        String speed = sharedPref.getString(SPEED, defoultSpeed);
        String city = sharedPref.getString(CITY, defaultCity);
        String region = sharedPref.getString(REGION, defaultCity);
        userPrefference.add(temperature);
        userPrefference.add(frequency);
        userPrefference.add(speed);
        userPrefference.add(city);
        userPrefference.add(region);
        return userPrefference;
    }

    private void getLocation(Activity context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
           try {
               Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
               Geocoder gcd = new Geocoder(context, Locale.getDefault());
               String lat = String.valueOf(location.getLatitude());
               String lan = String.valueOf(location.getLongitude());
               Log.d("Tag", "1");
               List<Address> addresses;
               try {
                   addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                   if (addresses.size() > 0) {
                       defaultCity = addresses.get(0).getLocality().toString();

                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }
           } catch (Exception ex){
               defaultCity = "Kiev";
           }
        } else {
            try {
                Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
                Geocoder gcd = new Geocoder(context, Locale.getDefault());
                String lat = String.valueOf(location.getLatitude());
                String lan = String.valueOf(location.getLongitude());
                Log.d("Tag", "1");
                List<Address> addresses;
                try {
                    addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        defaultCity = addresses.get(0).getLocality().toString();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                defaultCity = "Kiev";
            }
        }
    }

    @Override
    public void setUserTemperature(Activity context, String temperature) {
        SharedPreferences sharedPref = context.getSharedPreferences("setting_user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TEMPERATURE, temperature);
        editor.apply();
    }

    @Override
    public void setUserFrequency(Activity context, String frequency) {
        SharedPreferences sharedPref = context.getSharedPreferences("setting_user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(FREQUENCY, frequency);
        editor.apply();
    }

    @Override
    public void setUserSpeed(Activity context, String speed) {
        SharedPreferences sharedPref = context.getSharedPreferences("setting_user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SPEED, speed);
        editor.apply();
    }

    @Override
    public void setUserCity(Activity context, String city) {
        SharedPreferences sharedPref = context.getSharedPreferences("setting_user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(CITY, city);
        editor.apply();
    }

    @Override
    public void setUserRegion(Activity context, String region) {
        SharedPreferences sharedPref = context.getSharedPreferences("setting_user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(REGION, region);
        editor.apply();
    }


}
