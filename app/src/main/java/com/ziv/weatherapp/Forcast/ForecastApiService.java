package com.ziv.weatherapp.Forcast;


import com.ziv.weatherapp.models.ForecastIoResponse;

import retrofit.http.GET;
import retrofit.http.Path;

public interface ForecastApiService {
    @GET("/{latitude},{longitude}")
    ForecastIoResponse getForecast(@Path("latitude") String latitude, @Path("longitude") String longitude);
}
