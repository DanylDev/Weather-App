package com.incode_it.incode8.weather.ui.weather;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.incode8.weather.R;
import com.incode_it.incode8.weather.adapter.Weather.ParentViewHolder;
import com.incode_it.incode8.weather.data.ConverterParametrs;
import com.incode_it.incode8.weather.mapper.ConditionMapper;
import com.incode_it.incode8.weather.mapper.ImageMapper;
import com.incode_it.incode8.weather.models.weather_model.WeatherUi;
import com.incode_it.incode8.weather.ui.base.BaseActivity;
import com.incode_it.incode8.weather.ui.day_fragment.DayFragment;
import com.incode_it.incode8.weather.ui.setting.SettingActivity;
import com.incode_it.incode8.weather.ui.splash.SplashActivity;
import com.incode_it.incode8.weather.ui.three_days.ThreeDaysFragment;
import com.incode_it.incode8.weather.ui.week_fragment.WeekFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.incode_it.incode8.weather.utils.GetTimeNow.getTime;

public class WeatherActivity extends BaseActivity implements IWeatherView{

    @BindView(R.id.cities_name)
    TextView citiesNameTextView;

    @BindView(R.id.temperature)
    TextView temperatureTextView;

    @BindView(R.id.clouds)
    TextView cloudsTextView;

    @BindView(R.id.cloudsIcon)
    ImageView cloudsImageView;

    @BindView(R.id.banerView)
    ImageView baner;

    @BindView(R.id.more_setting)
    ImageButton moreImageButton;

    @BindView(R.id.card)
    CardView weather_cardView;

    @Inject
    IWeatherPresenter<IWeatherView> mPresenter;

    private Typeface typeface;
    private AdView mAdView;
    private static long back_pressed;
    public static ArrayList<String> pref = new ArrayList<>();

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, WeatherActivity.class);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getActivityComponents().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(WeatherActivity.this);
        pref.clear();
        pref = mPresenter.getUserPreference(this);
        initAds();
        applyIsPremium();
        initComponent();
    }

    private void applyIsPremium() {
        if (mAdView != null) {
                AdRequest adRequest = new AdRequest.Builder()
                    /*.addTestDevice("1F5AEDC14306AF09C9312AF0FE268B3C")
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)*/
                        .build();
                mAdView.loadAd(adRequest);
        }
    }

    private void initAds() {
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-5883565179852543~3565045732");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                Log.i("Ads", "onAdClosed");
            }
        });
    }



    @Override
    public void initComponent() {
        ImageMapper imageMapper = new ImageMapper(getTime());
        String fontPath = "fonts/CenturyGothicRegular.ttf";
        typeface = Typeface.createFromAsset(getAssets(), fontPath);
        citiesNameTextView.setText(SplashActivity.pref.get(3));
        temperatureTextView.setTypeface(typeface);
        cloudsTextView.setTypeface(typeface);
        if(pref.get(0).equals("c")){
            temperatureTextView.setText(WeatherUi.temperature + "\u00B0" + "C");
        }
        else {
            temperatureTextView.setText(ConverterParametrs.getFar(WeatherUi.temperature) + "\u00B0" + "F");
        }
        ConditionMapper conditionMapper = new ConditionMapper(getBaseContext());
        cloudsTextView.setText(conditionMapper.setCondition(WeatherUi.icon));
        cloudsImageView.setImageResource(imageMapper.setImage(WeatherUi.icon));
        switch (pref.get(1)){
            case "day":
                showDayFragment();
                break;
            case "threeDay":
                showThreeDayskFragment();
                ParentViewHolder.position = 0;
                break;
            case "week":
                showWeekFragment();
                ParentViewHolder.position = 0;
                break;
        }
        if(getTime() < 19 && getTime() > 6){
            baner.setImageResource(R.drawable.header_day);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(getBaseContext(), R.color.dayPrimary));
            }
        }
        else{
            baner.setImageResource(R.drawable.header_night);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                weather_cardView.setCardBackgroundColor(Color.parseColor("#274356"));
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public void showDayFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.weather_frame, DayFragment.newInstance(), DayFragment.TAG)
                .commit();
    }

    @Override
    public void showWeekFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.weather_frame, WeekFragment.newInstance(), WeekFragment.TAG)
                .commit();
    }

    @Override
    public void showThreeDayskFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.weather_frame, ThreeDaysFragment.newInstance(), ThreeDaysFragment.TAG)
                .commit();
    }

    @OnClick(R.id.more_setting)
    void onMoreSetting(View v){
        Intent intent = SettingActivity.getStartIntent(WeatherActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                finish();
                moveTaskToBack(true);
            } else {
                Toast.makeText(getBaseContext(), R.string.press_once, Toast.LENGTH_SHORT).show();
                back_pressed = System.currentTimeMillis();
            }
        } else {
            getSupportFragmentManager().popBackStack();
            ParentViewHolder.position = 0;
        }
    }

    @Override
    public void onFragmentDetached(String tag) {

    }

}
