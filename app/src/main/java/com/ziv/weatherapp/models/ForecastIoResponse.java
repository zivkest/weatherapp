package com.ziv.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class ForecastIoResponse extends Forecast
{
    @SerializedName("latitude")
    private double mLat;

    @SerializedName("longitude")
    private double mLon;

    @SerializedName("offset")
    private int mOffset;

    @SerializedName("timezone")
    private String mTimeZone;

    @SerializedName("currently")
    private Data mCurrentData;

    @SerializedName("minutely")
    private Timed mMinutly;

    @SerializedName("hourly")
    private Timed mHourly;

    @SerializedName("daily")
    private Timed mDaily;

    public Data getCurrentData()
    {
        return mCurrentData;
    }

    public Timed getMinutly()
    {
        return mMinutly;
    }

    public Timed getHourly()
    {
        return mHourly;
    }

    public Timed getDaily()
    {
        return mDaily;
    }

    public double getLat()
    {
        return mLat;
    }

    public double getLon()
    {
        return mLon;
    }

    public int getOffset()
    {
        return mOffset;
    }

    public String getTimeZone()
    {
        return mTimeZone;
    }
}
