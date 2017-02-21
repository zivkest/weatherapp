package com.ziv.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ForecastIo extends Forecast
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
    private ArrayList<Data> mMinutly;

    @SerializedName("hourly")
    private ArrayList<Data> mHourly;

    @SerializedName("daily")
    private ArrayList<Data> mDaily;

    public Data getCurrentData()
    {
        return mCurrentData;
    }
}
