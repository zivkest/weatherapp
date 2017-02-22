package com.ziv.weatherapp.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziv.weatherapp.MainActivity;
import com.ziv.weatherapp.R;
import com.ziv.weatherapp.WeatherApplication;
import com.ziv.weatherapp.models.realmObjects.RealmData;
import com.ziv.weatherapp.models.realmObjects.RealmForecast;
import com.ziv.weatherapp.utils.DateUtils;
import com.ziv.weatherapp.utils.IconUtils;
import com.ziv.weatherapp.utils.Preferences;
import com.ziv.weatherapp.utils.TemperatureConverter;

import java.util.Date;

import javax.inject.Inject;


public class DegreesCard extends CardView
{
    private ImageView mIcon;
    private TextView mIconText;
    private TextView mTemp;
    private TextView mTempMax;
    private TextView mTempMin;
    @Inject public Preferences mPrefs;


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
        View view = inflate(getContext(), R.layout.card_degrees, this);
        mIcon = (ImageView) view.findViewById(R.id.degrees_card_icon);
        mIconText = (TextView) view.findViewById(R.id.icon_desc);
        mTemp = (TextView) view.findViewById(R.id.temperature);
        mTempMax = (TextView) view.findViewById(R.id.temperature_max);
        mTempMin = (TextView) view.findViewById(R.id.temperature_min);
        MainActivity app = (MainActivity) getContext();
        ((WeatherApplication) app.getApplication()).getComponent().inject(this);
        setBackgroundColor(Color.parseColor("#80ffffff"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            setElevation(10f);
        }


    }

    public void updateData(RealmForecast forcastResults)
    {

        String icon = forcastResults.getCurrentData().getIcon();
        mIcon.setImageResource(IconUtils.getIcon(icon));
        mIconText.setText(icon);
        mTemp.setText(getDegreesByState((int) forcastResults.getCurrentData().getTemperature()));
        Date today = new Date(System.currentTimeMillis());
        RealmData todayData = null;
        for (RealmData data : forcastResults.getDaily().getData())
        {
            if (DateUtils.isSameDay(today, new Date((data.getTime() * 1000))))
            {
                todayData = data;
            }
        }
        if (todayData != null)
        {
            mTempMax.setText(getDegreesByState((int) todayData.getTemperatureMax()));
            mTempMin.setText(getDegreesByState((int) todayData.getTemperatureMin()));
        }
    }

    private String getDegreesByState(int temperature)
    {
        char degreeSymbol = (char) 0x00B0;
        return (mPrefs.isCelcius() ? String.valueOf(TemperatureConverter.convertFarToCel(temperature)) : String.valueOf(temperature)) + degreeSymbol;
    }


}
