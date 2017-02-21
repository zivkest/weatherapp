package com.ziv.weatherapp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ForcastResults extends RealmObject
{

    @PrimaryKey
    private String uuid;
    private String desc;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }
}

