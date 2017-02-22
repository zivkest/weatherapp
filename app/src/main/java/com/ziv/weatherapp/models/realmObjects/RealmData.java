package com.ziv.weatherapp.models.realmObjects;

import com.ziv.weatherapp.models.Data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class RealmData extends RealmObject
{
    @PrimaryKey
    private String mId;
    private long mTime;
    private float mApparentTemperature;
    private float mCloudCover;
    private float mDewPoint;
    private float mHumidity;
    private String mIcon;
    private int mNearestStormBearing;
    private int mNearestStormDistance;
    private float mOzone;
    private float mPrecipIntensity;
    private float mPrecipProbability;
    private float mPressure;
    private String mSummary;
    private float mTemperature;
    private float mTemperatureMax;
    private float mTemperatureMin;
    private float mVisibility;
    private int mWindBearing;
    private float mWindSpeed;


    public void setData(Data data, String id)
    {
        this.mApparentTemperature = data.getApparentTemperature();
        this.mCloudCover = data.getCloudCover();
        this.mDewPoint = data.getDewPoint();
        this.mHumidity = data.getHumidity();
        this.mIcon = data.getIcon();
        this.mNearestStormBearing = data.getNearestStormBearing();
        this.mNearestStormDistance = data. getNearestStormDistance();
        this.mOzone = data.getOzone();
        this.mPrecipIntensity = data.getPrecipIntensity();
        this.mPrecipProbability = data.getPrecipProbability();
        this.mPressure = data.getPressure();
        this.mSummary = data.getSummary();
        this.mTemperature = data.getTemperature();
        this.mTemperatureMax = data.getTemperatureMax();
        this.mTemperatureMin = data. getTemperatureMin();
        this.mTime = data. getTime();
        this.mVisibility = data.getVisibility();
        this.mWindBearing = data.getWindBearing();
        this.mWindSpeed = data.getWindSpeed();
    }


    public long getTime()
    {
        return mTime;
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

    public String getIcon()
    {
        return mIcon;
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

    public float getTemperatureMax()
    {
        return mTemperatureMax;
    }

    public float getTemperatureMin()
    {
        return mTemperatureMin;
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
