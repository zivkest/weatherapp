package com.ziv.weatherapp.Forcast;


import com.ziv.weatherapp.models.Forecast;

public interface ForecastListener {
    void onForecastLoaded(Forecast forecastIo);
    void onForecastFailed(Exception e);
}
