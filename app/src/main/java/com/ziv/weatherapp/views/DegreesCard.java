package com.ziv.weatherapp.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.ziv.weatherapp.R;

public class DegreesCard extends CardView
{
    public DegreesCard(Context context)
    {
        super(context);
        init();

    }

    public DegreesCard(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public DegreesCard(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init()
    {
        inflate(getContext(), R.layout.card_degrees, null);
    }
}
