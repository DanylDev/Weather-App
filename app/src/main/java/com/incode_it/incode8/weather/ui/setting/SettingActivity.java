package com.incode_it.incode8.weather.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.incode8.weather.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.incode_it.incode8.weather.ui.base.BaseActivity;
import com.incode_it.incode8.weather.ui.splash.SplashActivity;
import com.incode_it.incode8.weather.ui.weather.WeatherActivity;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.incode8.weather.R.id.cels;
import static com.incode_it.incode8.weather.utils.GetTimeNow.getTime;

public class SettingActivity extends BaseActivity implements ISettingView{

    @BindView(R.id.day_weather)
    TextView dayTextView;

    @BindView(R.id.three_days)
    TextView weekTextView;

    @BindView(R.id.week_weather)
    TextView twoWeekTextView;

    @BindView(R.id.km_weater)
    TextView kmTextView;

    @BindView(R.id.mi_weater)
    TextView miTextView;

    @BindView(R.id.back_button)
    ImageButton backImageButton;

    @BindView(cels)
    ImageView celsTextView;

    @BindView(R.id.far)
    ImageView farTextView;

    @BindView(R.id.city_user)
    TextView cityUser;

    @BindView(R.id.setting_back)
    ImageView backgraundSetting;

    Typeface typeface;

    private boolean newCity = false;

    @Inject
    ISettingPresenter<ISettingView> mPresenter;

    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getActivityComponents().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SettingActivity.this);
        onInitComponent();
        UserPref();
    }

    @Override
    public void onBackPressed() {
        backButton();
    }

    @Override
    public void UserPref() {
        switch (WeatherActivity.pref.get(1)){
            case "day":
                setDay(getWindow().getDecorView().getRootView());
                break;
            case "threeDay":
                setThreeDays(getWindow().getDecorView().getRootView());
                break;
            case "week":
                setWeek(getWindow().getDecorView().getRootView());
                break;
        }
        switch (WeatherActivity.pref.get(0)){
            case "c":
                setCels(getWindow().getDecorView().getRootView());
                break;
            case "f":
                setFar(getWindow().getDecorView().getRootView());
        }
        String speed = WeatherActivity.pref.get(2);
        switch (speed){
            case "m/s":
                setKm(getWindow().getDecorView().getRootView());
                break;
            case "Mi/hour":
                setMi(getWindow().getDecorView().getRootView());
        }
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        return intent;
    }

    @OnClick(R.id.search_btn)
    void setSearch(View v) {
        initSearch();
    }

    @OnClick(R.id.city_user)
    void SetSearchTextView(View v){
        initSearch();
    }

    @Override
    public void initSearch() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        }catch (Exception ex){
            ex.getMessage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String testS = String.valueOf(Locale.getDefault());
                if (testS.contains("ru")) {
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    String[] test = place.getAddress().toString().trim().split(",");
                    cityUser.setText(place.getName().toString());
                    mPresenter.setUserCity(this, place.getName().toString());
                    mPresenter.setUserRegion(this, test[1]);
                    newCity = true;
                }else{
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    cityUser.setText(place.getName().toString());
                    mPresenter.setUserCity(this, place.getName().toString());
                    mPresenter.setUserRegion(this, place.getName().toString());
                    newCity = true;
                }
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i("", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @OnClick(R.id.back_button)
    void onBackButton(View v){
        backButton();
    }

    private void backButton() {
        if(newCity == false) {
            Intent intent = WeatherActivity.getStartIntent(SettingActivity.this);
            startActivity(intent);
            finish();
        } else {
            Intent intent = SplashActivity.getStartIntent(SettingActivity.this);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onInitComponent() {
        String fontPath = "fonts/CenturyGothicRegular.ttf";
        typeface = Typeface.createFromAsset(getAssets(), fontPath);
        cityUser.setText(SplashActivity.pref.get(3));
        weekTextView.setTypeface(typeface);
        dayTextView.setTypeface(typeface);
        twoWeekTextView.setTypeface(typeface);
        kmTextView.setTypeface(typeface);
        miTextView.setTypeface(typeface);
        if(getTime() < 19 && getTime() > 6){
            backgraundSetting.setImageResource(R.drawable.header_day);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(getBaseContext(), R.color.dayPrimary));
            }
        }
        else{
            backgraundSetting.setImageResource(R.drawable.header_night);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary));
            }
        }
    }

    @OnClick(R.id.day_weather)
    void setDay(View v){
        dayTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.dayPrimary));
        weekTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.black));
        twoWeekTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.black));
        mPresenter.setUserFrequency(this, "day");
    }

    @OnClick(R.id.three_days)
    void setThreeDays(View v){
        dayTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.black));
        weekTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.dayPrimary));
        twoWeekTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.black));
        mPresenter.setUserFrequency(this, "threeDay");
    }

    @OnClick(R.id.week_weather)
    void setWeek(View v){
        dayTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.black));
        weekTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.black));
        twoWeekTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.dayPrimary));
        mPresenter.setUserFrequency( this, "week");
    }

    @OnClick(R.id.km_weater)
    void setKm(View v){
        miTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.black));
        kmTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.dayPrimary));
        mPresenter.setUserSpeed(this, "m/s");
    }

    @OnClick(R.id.mi_weater)
    void setMi(View v){
        kmTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.black));
        miTextView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.dayPrimary));
        mPresenter.setUserSpeed(this, "Mi/hour");
    }

    @OnClick(R.id.cels)
    void setCels(View v){
        celsTextView.setImageResource(R.drawable.celsius);
        farTextView.setImageResource(R.drawable.fahrenheit_unselected);
        mPresenter.setUserTemperature(this, "c");
    }

    @OnClick(R.id.far)
    void setFar(View v){
        celsTextView.setImageResource(R.drawable.celsius_unselected);
        farTextView.setImageResource(R.drawable.fahrenheit);
        mPresenter.setUserTemperature(this, "f");
    }

    @Override
    public void onFragmentDetached(String tag) {

    }

}
