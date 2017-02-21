package com.ziv.weatherapp.models;

import com.google.gson.annotations.SerializedName;

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

    @SerializedName("time")
    private long mTime;

    @SerializedName("visibility")
    private float mVisibility;

    @SerializedName("windBearing")
    private int mWindBearing;

    @SerializedName("windSpeed")
    private float mWindSpeed;

    public String getIcon()
    {
        return mIcon;
    }
}
