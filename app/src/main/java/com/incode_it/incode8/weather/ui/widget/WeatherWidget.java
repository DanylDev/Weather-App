package com.incode_it.incode8.weather.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.incode8.weather.R;
import com.incode_it.incode8.weather.mapper.ImageMapper;
import com.incode_it.incode8.weather.models.weather_model.WeatherUi;


/**
 * Implementation of App Widget functionality.
 */
public class WeatherWidget extends AppWidgetProvider{
    private ImageMapper imageMapper = new ImageMapper();

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int currentWidgetId : appWidgetIds) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
                views.setImageViewResource(R.id.imageView, imageMapper.setImage(WeatherUi.icon));
                views.setTextViewText(R.id.textView, WeatherUi.temperature + "\u00B0" + "C");
        }
    }

}
