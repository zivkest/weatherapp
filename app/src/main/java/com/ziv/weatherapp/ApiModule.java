package com.ziv.weatherapp;

import android.content.Context;

import com.ziv.weatherapp.Forcast.ForecastIOApiEndpoint;
import com.ziv.weatherapp.HebreCal.HebrewApiEndPoint;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.RestAdapter;

@Module
public class ApiModule {

    @Provides
    @Named(Constants.Injection.Named.FORECAST_API_KEY)
    public String provideForecastIOApiKey(Context context) {
        return context.getString(R.string.forecast_io_api_key);
    }

    @Provides
    @Named(Constants.Injection.Named.FORECAST_IO_ENDPOINT)
    public Endpoint provideForcastEndpoint(@Named(Constants.Injection.Named.FORECAST_API_KEY) String apiKey) {
        return new ForecastIOApiEndpoint().setApiKey(apiKey);
    }

    @Provides
    @Named(Constants.Injection.Named.HEBREW_CAL_ENDPOINT)
    public Endpoint provideHebrewCalEndpoint() {
        return new HebrewApiEndPoint();
    }

    @Provides
    @Singleton
    @Named(Constants.Injection.Named.FORECAST_IO_REST)
    public RestAdapter provideForcastRestAdapter(@Named(Constants.Injection.Named.FORECAST_IO_ENDPOINT)Endpoint endpoint) {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(endpoint)
                .build();
    }

    @Provides
    @Singleton
    @Named(Constants.Injection.Named.HEBREW_CAL_REST)
    public RestAdapter provideHebrewRestAdapter(@Named(Constants.Injection.Named.HEBREW_CAL_ENDPOINT)Endpoint endpoint) {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(endpoint)
                .build();
    }
}
