package com.ziv.weatherapp;

import android.app.Application;
import android.content.Intent;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

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

        Timber.plant(new Timber.DebugTree());
        // Configure Realm for the application
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .name("weatherApp.realm")
                .build();
        Realm.deleteRealm(realmConfiguration); // Clean slate
        Realm.setDefaultConfiguration(realmConfiguration); // Make this Realm the default
        // Check the current conditions
        startService(new Intent(this, CurrentConditionService.class));
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
