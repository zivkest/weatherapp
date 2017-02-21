package com.ziv.weatherapp.Forcast;


import com.ziv.weatherapp.models.ForecastIo;

import retrofit.http.GET;
import retrofit.http.Path;

public interface ForecastApiService {
    @GET("/{latitude},{longitude}")
    ForecastIo getForecast(@Path("latitude") String latitude, @Path("longitude") String longitude);
}
