package com.ziv.weatherapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.ziv.weatherapp.Forcast.ForecastListener;
import com.ziv.weatherapp.Forcast.ForecastService;
import com.ziv.weatherapp.models.Forecast;
import com.ziv.weatherapp.models.ForecastIo;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import io.realm.Realm;


public class CurrentConditionService extends Service
{

    private static final String TAG = CurrentConditionService.class.getSimpleName();

    @Inject ForecastService forecastService;

    @Override
    public void onCreate() {
        super.onCreate();
        ((WeatherApplication)getApplication()).getComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Attempting to get current conditions.");

        // Forecast service is an ASYNC call.
        // hard coded to NYC. In prod: Use a location listener to get the GPS coords of the user.
        forecastService.getForecastFor("40.7146", "-74.0072", new ForecastListener() {
            @Override
            public void onForecastLoaded(Forecast forecast) {

                ForecastIo forecastio = (ForecastIo) forecast;
                if(forecast != null) {
                    Log.d(TAG, "Forecast loaded.");
                }

                if(forecastio != null && forecastio.getCurrentData() != null && forecastio.getCurrentData().getIcon() != null) {
                    // icon options: clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
                    String icon = forecastio.getCurrentData().getIcon();

                    String dateFormat = "MM/dd/yyyy";
                    SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat, Locale.US);
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    ForcastResults forecastResults = realm.createObject(ForcastResults.class);
                    forecastResults.setDesc(forecastio.getCurrentData().getIcon());
                    realm.commitTransaction();
                    Intent intent = new Intent("custom-event-name");
                    // You can also include some extra data.
                    intent.putExtra("message", "This is my message!");
                    LocalBroadcastManager.getInstance(CurrentConditionService.this).sendBroadcast(intent);

                }

                stopSelf();
            }

            @Override
            public void onForecastFailed(Exception e) {
                Log.e(TAG, e.getMessage(), e);
                stopSelf();
            }
        });

        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
