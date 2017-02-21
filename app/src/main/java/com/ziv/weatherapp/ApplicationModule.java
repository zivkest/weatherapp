package com.ziv.weatherapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ziv.weatherapp.Forcast.ForecastService;
import com.ziv.weatherapp.Forcast.ForecastServiceImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    public ForecastService provideForecastService(@Named(Constants.Injection.Named.FORECAST_IO_REST)RestAdapter restAdapter) {
        return new ForecastServiceImpl(restAdapter);
    }

}
