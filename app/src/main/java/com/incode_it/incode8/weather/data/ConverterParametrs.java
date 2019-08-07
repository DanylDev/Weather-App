package com.incode_it.incode8.weather.data;

/**
 * Created by incode8 on 16.08.17.
 */

public class ConverterParametrs {

    public static Double getMilles(String windString){
        Double wind = Double.parseDouble(windString) * 0.44704;
        return wind;
    }

    public static int getFar(String temp){
        Integer temperature = Integer.parseInt(temp) * 9 / 5 + 32;
        return temperature;
    }
}
