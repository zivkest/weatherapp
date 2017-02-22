package com.ziv.weatherapp;

import com.ziv.weatherapp.views.DegreesCard;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, ApiModule.class })
public interface ApplicationComponent {
    void inject(WeatherApplication target);
    void inject(MainActivity target);
    void inject(CurrentConditionService target);
    void inject(DegreesCard target);
}
