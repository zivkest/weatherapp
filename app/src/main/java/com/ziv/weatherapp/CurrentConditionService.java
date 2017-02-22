package com.ziv.weatherapp;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.ziv.weatherapp.Forcast.ForecastListener;
import com.ziv.weatherapp.Forcast.ForecastService;
import com.ziv.weatherapp.models.Forecast;
import com.ziv.weatherapp.models.ForecastIoResponse;
import com.ziv.weatherapp.models.realmObjects.RealmForecast;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import io.realm.Realm;
import timber.log.Timber;


public class CurrentConditionService extends Service
{

    private static final String TAG = CurrentConditionService.class.getSimpleName();
    private static final String LAT = "latitude";
    private static final String LON = "longitude";

    @Inject ForecastService forecastService;

    @Override
    public void onCreate() {
        super.onCreate();
        ((WeatherApplication)getApplication()).getComponent().inject(this);

    }

    public static Intent getForcastIntent(Activity activity, double latitude, double longitude)
    {
        Intent intent =  new Intent(activity, CurrentConditionService.class);
        intent.putExtra(LAT, latitude);
        intent.putExtra(LON, longitude);
        return intent;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("Attempting to get current conditions.");
        double latitude = intent.getDoubleExtra(LAT,0);
        double lontitude = intent.getDoubleExtra(LON,0);
        // Forecast service is an ASYNC call.
        // hard coded to NYC. In prod: Use a location listener to get the GPS coords of the user.
        forecastService.getForecastFor(String.valueOf(latitude), String.valueOf(lontitude), new ForecastListener() {
            @Override
            public void onForecastLoaded(Forecast forecast) {

                ForecastIoResponse forecastio = (ForecastIoResponse) forecast;
                if(forecast != null) {
                    Timber.d("Forecast loaded.");
                }

                if(forecastio != null && forecastio.getCurrentData() != null && forecastio.getCurrentData().getIcon() != null) {
                    String dateFormat = "MM/dd/yyyy";
                    SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat, Locale.US);
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    RealmForecast forecastResults = realm.createObject(RealmForecast.class);
                    forecastResults.setForecast(forecastio, forecastio.getCurrentData().getTime());
                    realm.commitTransaction();
                    Intent intent = new Intent(Constants.IntentFilters.FORECAST_RECEIVED);
                    LocalBroadcastManager.getInstance(CurrentConditionService.this).sendBroadcast(intent);

                }

                stopSelf();
            }

            @Override
            public void onForecastFailed(Exception e) {
                Timber.e(e.getMessage(), e);
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
