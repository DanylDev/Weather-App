package com.incode_it.incode8.weather.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by incode8 on 01.09.17.
 */

public class GetTimeNow {
    public static int getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH");
        return Integer.parseInt(mdformat.format(calendar.getTime()));
    }
}
