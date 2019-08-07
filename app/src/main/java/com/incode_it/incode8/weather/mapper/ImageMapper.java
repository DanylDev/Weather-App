package com.incode_it.incode8.weather.mapper;

import com.example.incode8.weather.R;

/**
 * Created by incode8 on 10.08.17.
 */

public class ImageMapper {

    private int time;
    public int imageID;

    public ImageMapper(int time){
        this.time = time;
    }

    public ImageMapper(){

    }

    public int setImage(String clouds) {
        switch (clouds){
            case "01d":
                imageID = R.drawable.sun;
                break;
            case "01n":
                imageID = R.drawable.night;
                break;
            case "02d":
                imageID = R.drawable.cloudysun;
                break;
            case "02n":
                imageID = R.drawable.cloudnight;
                break;
            case "03d":
                imageID = R.drawable.cloudy;
                break;
            case "03n":
                imageID = R.drawable.cloudy;
                break;
            case "04d":
                imageID = R.drawable.cloudy;
                break;
            case "04n":
                imageID = R.drawable.cloudy;
                break;
            case "09d":
                imageID = R.drawable.rain;
                break;
            case "09n":
                imageID = R.drawable.rain;
                break;
            case "10d":
                imageID = R.drawable.rain;
                break;
            case "10n":
                imageID = R.drawable.rain;
                break;
            case "11d":
                imageID = R.drawable.thunder;
                break;
            case "11n":
                imageID = R.drawable.thunder;
                break;
            case "13d":
                imageID = R.drawable.snow;
                break;
            case "13n":
                imageID = R.drawable.snow;
                break;
            case "50d":
                imageID = R.drawable.fog;
                break;
            case "50n":
                imageID = R.drawable.fog;
                break;
        }
        return imageID;
    }
}
