package com.incode_it.incode8.weather.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.incode_it.incode8.weather.WeatherApplication;
import com.incode_it.incode8.weather.di.component.ActivityComponent;
import com.incode_it.incode8.weather.di.component.DaggerActivityComponent;
import com.incode_it.incode8.weather.di.module.ActivityModule;
import com.incode_it.incode8.weather.utils.NetworkUtils;

import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by incode8 on 08.08.17.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements IBaseView, BaseFragment.Callback {

    private ProgressDialog progressDialog;

    private ActivityComponent activityComponent;

    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((WeatherApplication) getApplication()).getComponent())
                .build();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public ActivityComponent getActivityComponents() {
        return activityComponent;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public boolean networkConnected() {
        return NetworkUtils.networkConnected(getApplicationContext());
    }

    @Override
    public void onError(String errorString) {

    }

    @Override
    public void onError(@StringRes int idRes) {

    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {

    }
}
