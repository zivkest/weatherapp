package com.ziv.weatherapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class WeatherApplication extends Application
{
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule())
                .build();

        // Configure Realm for the application
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("weatherApp.realm")
                .build();
        //Realm.deleteRealm(realmConfiguration); // Clean slate
        Realm.setDefaultConfiguration(realmConfiguration); // Make this Realm the default
        Realm.getDefaultInstance().where(ForcastResults.class);
        // Check the current conditions
        //startService(new Intent(this, CurrentConditionService.class));
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
