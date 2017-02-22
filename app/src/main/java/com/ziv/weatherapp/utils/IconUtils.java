package com.ziv.weatherapp.utils;

import android.support.annotation.DrawableRes;

import com.ziv.weatherapp.R;

public class IconUtils
{
    @DrawableRes
    public static int getIcon(String iconDesc)
    {
        final String CLEAR_DAY = "clear-day";
        final String CLEAR_NIGHT = "clear-night";
        final String RAIN = "rain";
        final String SNOW = "snow";
        final String SLEET = "sleet";
        final String WIND = "wind";
        final String FOG = "fog";
        final String CLOUDY = "cloudy";
        final String PARTLY_CLOUDY_DAY = "partly-cloudy-day";
        final String PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";

        switch (iconDesc)
        {
            case CLEAR_DAY:
                return R.drawable.clear_day;
            case CLEAR_NIGHT:
                return R.drawable.clear_night;
            case CLOUDY:
                return R.drawable.cloudy;
            case PARTLY_CLOUDY_DAY:
                return R.drawable.partly_cloudy_day;
            case PARTLY_CLOUDY_NIGHT:
                return R.drawable.partly_cloudy_night;
            case FOG:
                return R.drawable.fog;
            case RAIN:
                return R.drawable.rain;
            case SLEET:
                return R.drawable.sleet;
            case SNOW:
                return R.drawable.snow;
            case WIND:
                return R.drawable.wind;
            default:
                return R.drawable.na;
        }
    }
}
