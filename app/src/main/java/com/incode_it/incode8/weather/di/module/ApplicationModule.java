package com.incode_it.incode8.weather.di.module;

import android.app.Application;
import android.content.Context;

import com.example.incode8.weather.R;
import com.incode_it.incode8.weather.data.DataManager;
import com.incode_it.incode8.weather.data.IDataManager;
import com.incode_it.incode8.weather.data.api.WeatherApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.incode_it.incode8.weather.utils.AppConstants.BASE_URL;

/**
 * Created by incode8 on 08.08.17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    IDataManager providesDataManager(DataManager dataManager){
        return dataManager;
    }

    @Provides
    @Singleton
    WeatherApi provideRetrofitDefaultConfig() {
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.create();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();
        return retrofit.create(WeatherApi.class);
    }
}
