package com.ziv.weatherapp.utils;

public class TemperatureConverter
{
    public static int convertFarToCel(int tempInFarenhight) {
        return ((tempInFarenhight- 32) * 5 / 9);
    }

    public static int convertcelToFar(int tempInCelcius) {
        return 9 * (tempInCelcius / 5) + 32;
    }
}