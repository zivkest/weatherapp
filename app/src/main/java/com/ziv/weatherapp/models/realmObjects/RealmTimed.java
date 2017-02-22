package com.ziv.weatherapp.models.realmObjects;

import com.ziv.weatherapp.models.Timed;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmTimed extends RealmObject
{
    @PrimaryKey
    private String TimedId;
    private String mSummery;
    private String mIcon;
    private RealmList<RealmData> mData;


    public void setTimed(Timed timed, String id)
    {
        TimedId = id;
        if(timed!=null)
        {
            mSummery = timed.getSummery();
            mIcon = timed.getIcon();
            mData = timed.getRealmData();
        }
    }

    public String getTimedId()
    {
        return TimedId;
    }

    public String getSummery()
    {
        return mSummery;
    }

    public String getIcon()
    {
        return mIcon;
    }

    public RealmList<RealmData> getData()
    {
        return mData;
    }
}
