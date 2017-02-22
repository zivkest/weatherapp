package com.ziv.weatherapp.Forcast;

import android.os.AsyncTask;

import com.ziv.weatherapp.models.ForecastIoResponse;

import retrofit.RestAdapter;

public class ForecastServiceImpl implements ForecastService {

    private RestAdapter restAdapter;

    public ForecastServiceImpl(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }

    @Override
    public void getForecastFor(final String latitude, final String longitude, final ForecastListener forecastListener) {
        new AsyncTask<Void, Void, ForecastIoResponse>() {

            public Exception e;

            @Override
            protected ForecastIoResponse doInBackground(Void... params) {
                try {
                    return restAdapter.create(ForecastApiService.class).getForecast(latitude, longitude);
                } catch (Exception e) {
                    this.e = e;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(ForecastIoResponse forecastIoResponse) {
                super.onPostExecute(forecastIoResponse);
                if(forecastListener != null) {
                    if(e != null) {
                        forecastListener.onForecastFailed(e);
                    } else {
                        forecastListener.onForecastLoaded(forecastIoResponse);
                    }
                }
            }
        }.execute();
    }
}
