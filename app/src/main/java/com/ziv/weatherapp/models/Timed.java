package com.ziv.weatherapp.models;

import com.google.gson.annotations.SerializedName;
import com.ziv.weatherapp.models.realmObjects.RealmData;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;

public class Timed
{

    @PrimaryKey
    @SerializedName("summary")
    private String mSummery;

    @SerializedName("icon")
    private String mIcon;

    @SerializedName("data")
    private ArrayList<Data> mData;


    public String getSummery()
    {
        return mSummery;
    }

    public String getIcon()
    {
        return mIcon;
    }

    public ArrayList<Data> getData()
    {
        return mData;
    }

    public RealmList<RealmData> getRealmData()
    {
        Realm realm = Realm.getDefaultInstance();
        RealmList<RealmData> realmDatas = new RealmList<>();
        for(Data data : getData())
        {
            RealmData realmData = realm.createObject(RealmData.class);
            realmData.setData(data, UUID.randomUUID().toString());
            realmDatas.add(realmData);
        }
        return realmDatas;
    }
}
