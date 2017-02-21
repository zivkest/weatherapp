package com.ziv.weatherapp.HebreCal;

import retrofit.Endpoint;

public class HebrewApiEndPoint implements Endpoint
{
    private static final String BASE = "http://www.hebcal.com/hebcal/?v=1&cfg=json&";

    private String url;

    public HebrewApiEndPoint setApiKey(String apiKey) {
        url = BASE + apiKey;
        return this;
    }

    @Override
    public String getName() {
        return "default hebcal endpoint";
    }

    @Override
    public String getUrl() {
        if (url == null)
        {
            throw new IllegalStateException("API key not set.");
        }
        return url;
    }
}
