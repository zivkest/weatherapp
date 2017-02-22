package com.ziv.weatherapp;

import android.app.Application;
import android.content.Context;

import com.ziv.weatherapp.Forcast.ForecastService;
import com.ziv.weatherapp.Forcast.ForecastServiceImpl;
import com.ziv.weatherapp.utils.Preferences;

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
    public Preferences provideSharedPreferences(Context context) {
        return Preferences.from(context);
    }

    @Provides
    public ForecastService provideForecastService(@Named(Constants.Injection.Named.FORECAST_IO_REST)RestAdapter restAdapter) {
        return new ForecastServiceImpl(restAdapter);
    }

}
