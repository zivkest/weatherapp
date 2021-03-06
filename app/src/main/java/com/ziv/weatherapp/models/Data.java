package com.ziv.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import io.realm.annotations.PrimaryKey;

public class Data
{

    @SerializedName("apparentTemperature")
    private float mApparentTemperature;

    @SerializedName("cloudCover")
    private float mCloudCover;

    @SerializedName("dewPoint")
    private float mDewPoint;

    @SerializedName("humidity")
    private float mHumidity;

    @SerializedName("icon")
    private String mIcon;

    @SerializedName("nearestStormBearing")
    private int mNearestStormBearing;

    @SerializedName("nearestStormDistance")
    private int mNearestStormDistance;

    @SerializedName("ozone")
    private float mOzone;

    @SerializedName("precipIntensity")
    private float mPrecipIntensity;

    @SerializedName("precipProbability")
    private float mPrecipProbability;

    @SerializedName("pressure")
    private float mPressure;

    @SerializedName("summary")
    private String mSummary;

    @SerializedName("temperature")
    private float mTemperature;

    @SerializedName("temperatureMax")
    private float mTemperatureMax;

    @SerializedName("temperatureMin")
    private float mTemperatureMin;

    @PrimaryKey
    @SerializedName("time")
    private long mTime;

    @SerializedName("visibility")
    private float mVisibility;

    @SerializedName("windBearing")
    private int mWindBearing;

    @SerializedName("windSpeed")
    private float mWindSpeed;

    public float getTemperatureMax()
    {
        return mTemperatureMax;
    }

    public float getTemperatureMin()
    {
        return mTemperatureMin;
    }

    public String getIcon()
    {
        return mIcon;
    }

    public float getApparentTemperature()
    {
        return mApparentTemperature;
    }

    public float getCloudCover()
    {
        return mCloudCover;
    }

    public float getDewPoint()
    {
        return mDewPoint;
    }

    public float getHumidity()
    {
        return mHumidity;
    }


    public int getNearestStormBearing()
    {
        return mNearestStormBearing;
    }

    public int getNearestStormDistance()
    {
        return mNearestStormDistance;
    }

    public float getOzone()
    {
        return mOzone;
    }

    public float getPrecipIntensity()
    {
        return mPrecipIntensity;
    }

    public float getPrecipProbability()
    {
        return mPrecipProbability;
    }

    public float getPressure()
    {
        return mPressure;
    }

    public String getSummary()
    {
        return mSummary;
    }

    public float getTemperature()
    {
        return mTemperature;
    }

    public long getTime()
    {
        return mTime;
    }

    public float getVisibility()
    {
        return mVisibility;
    }

    public int getWindBearing()
    {
        return mWindBearing;
    }

    public float getWindSpeed()
    {
        return mWindSpeed;
    }
}
