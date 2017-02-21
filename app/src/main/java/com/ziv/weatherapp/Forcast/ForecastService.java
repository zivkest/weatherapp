package com.ziv.weatherapp.Forcast;

public interface ForecastService {
    void getForecastFor(String latitude, String longitude, ForecastListener forecastListener);
}
