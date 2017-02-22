package com.ziv.weatherapp.models.realmObjects;

import com.ziv.weatherapp.models.ForecastIoResponse;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmForecast extends RealmObject
{

    @PrimaryKey
    private long forecastId;
    private double mLat;
    private double mLon;
    private int mOffset;
    private String mTimeZone;
    private RealmData mCurrentData;
    private RealmTimed mMinutly;
    private RealmTimed mHourly;
    private RealmTimed mDaily;

    public RealmData getCurrentData()
    {
        return mCurrentData;
    }

    public RealmTimed getMinutly()
    {
        return mMinutly;
    }

    public RealmTimed getHourly()
    {
        return mHourly;
    }

    public RealmTimed getDaily()
    {
        return mDaily;
    }

    public void setForecast(ForecastIoResponse forecast, long id)
    {
        Realm realm = Realm.getDefaultInstance();
        forecastId = id;
        mLat = forecast.getLat();
        mLon = forecast.getLon();
        mOffset = forecast.getOffset();
        mTimeZone = forecast.getTimeZone();
        mCurrentData = realm.createObject(RealmData.class);
        mCurrentData.setData(forecast.getCurrentData(), UUID.randomUUID().toString());
        mMinutly = realm.createObject(RealmTimed.class);
        mHourly = realm.createObject(RealmTimed.class);
        mDaily = realm.createObject(RealmTimed.class);
        if(forecast.getMinutly()!=null)
        {
            mMinutly.setTimed(forecast.getMinutly(), UUID.randomUUID().toString());
        }
        if(forecast.getHourly()!=null)
        {
            mHourly.setTimed(forecast.getHourly(), UUID.randomUUID().toString());
        }
        if(forecast.getDaily()!=null)
        {
            mDaily.setTimed(forecast.getDaily(), UUID.randomUUID().toString());
        }
    }
}
