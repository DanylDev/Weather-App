package com.incode_it.incode8.weather.adapter.Weather;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.incode8.weather.R;
import com.incode_it.incode8.weather.data.ConverterParametrs;
import com.incode_it.incode8.weather.mapper.ImageMapper;
import com.incode_it.incode8.weather.models.daily_model.Daily;
import com.incode_it.incode8.weather.models.daily_model.WeatherDailyParametr;
import com.incode_it.incode8.weather.ui.hour_weather_fragment.HourWeatherFragment;
import com.incode_it.incode8.weather.ui.weather.WeatherActivity;
import com.incode_it.incode8.weather.utils.GetTimeNow;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Created by incode8 on 14.08.17.
 */

public class ParentViewHolder extends AbstractViewHolder<Daily> {

    @BindView(R.id.item_description)
    TextView itemDescriptionTextView;

    @BindView(R.id.item_image)
    ImageView itemImageView;

    @BindView(R.id.item_temp)
    TextView itemTempTextView;

    @BindView(R.id.weather_card)
    CardView weatherCard;

    private Daily parent;
    private List<WeatherDailyParametr> child;
    private Context mContext;
    private DailyWeatherRecyclerAdapter mAdapter;
    private ImageMapper imageMapper;
    public static int position;

    ParentViewHolder(AbstractAdapter adapter, ViewGroup parent) {
        super(parent, R.layout.recyclerview_item_row);
        ButterKnife.bind(this, itemView);
        mContext = parent.getContext();
        mAdapter = (DailyWeatherRecyclerAdapter) adapter;
    }

    @Override
    public void setData(Daily data) {
        if (position == 0) {
            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) weatherCard.getLayoutParams();
            layoutParams.setMargins(30, 10, 30, 10);
            weatherCard.setCardElevation(20);
            itemTempTextView.setTypeface(null, Typeface.BOLD);
            itemDescriptionTextView.setTypeface(null, Typeface.BOLD);
            createHolder(data);
            position++;
        } else {
            createHolder(data);
        }
    }

    private void createHolder(Daily data) {
        imageMapper = new ImageMapper(GetTimeNow.getTime());
        if(GetTimeNow.getTime() > 19 && GetTimeNow.getTime() < 6)
            weatherCard.setCardBackgroundColor(Color.parseColor("#274356"));
        DateFormat originalFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        DateFormat targetFormat = new SimpleDateFormat("EEE, MMM d");
        try {
            itemDescriptionTextView.setText(String.valueOf(targetFormat.format(originalFormat.parse(data.dateDaily))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemImageView.setImageResource(imageMapper.setImage(data.iconDaily));
        if (WeatherActivity.pref.get(0).equals("c")) {
            itemTempTextView.setText(data.temperatureDaily + "\u00B0" + "C");
        } else {
            itemTempTextView.setText(ConverterParametrs.getFar(data.temperatureDaily) + "\u00B0" + "F");
        }
        child = data.dailyParametrs;
        parent = data;
    }

    @OnClick(R.id.linearWeather)
    void onClickLinear() {
        Transaction();
    }


    @OnClick(R.id.item_description)
    void onClick() {
        Transaction();
    }

    private void Transaction() {
        ((FragmentActivity) mContext).getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(HourWeatherFragment.TAG)
                .replace(R.id.weather_frame, HourWeatherFragment.newInstance(getAdapterPosition()), HourWeatherFragment.TAG)
                .commit();
    }
}
