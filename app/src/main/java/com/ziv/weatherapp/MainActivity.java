package com.ziv.weatherapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.ziv.weatherapp.models.realmObjects.RealmForecast;
import com.ziv.weatherapp.utils.ColorUtils;
import com.ziv.weatherapp.views.DegreesCard;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    public static final int REQUEST_CODE_PERMISSION = 100;
    private Realm mRealm;
    private FloatingActionButton mFabAdd;
    private FloatingActionButton mFabDelete;
    private FloatingActionMenu fam;
    private DegreesCard mDegreesCard;
    private LocationManager mLocationManager;
    private Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        mRealm = Realm.getDefaultInstance();
        mFabAdd = (FloatingActionButton) findViewById(R.id.fab2);
        mFabDelete = (FloatingActionButton) findViewById(R.id.fab3);
        FloatingActionButton fabRefresh = (FloatingActionButton) findViewById(R.id.refresh);
        mDegreesCard = (DegreesCard) findViewById(R.id.degrees_card);
        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);

        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener()
        {
            @Override
            public void onMenuToggle(boolean opened)
            {
                if (opened)
                {
                    showToast("Menu is opened");
                }
                else
                {
                    showToast("Menu is closed");
                }
            }
        });


        //handling each floating action button clicked
        mFabDelete.setOnClickListener(onButtonClick());
        fabRefresh.setOnClickListener(onButtonClick());
        mFabAdd.setOnClickListener(onButtonClick());

        fam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (fam.isOpened())
                {
                    fam.close(true);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LocalBroadcastManager.getInstance(this).registerReceiver(new ForcastReceiver(), new IntentFilter(Constants.IntentFilters.FORECAST_RECEIVED));
        RelativeLayout background = (RelativeLayout) findViewById(R.id.content_main);
        background.setBackgroundColor(ColorUtils.getMatColor("500", getApplicationContext()));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        getLocation();
    }

    private void getLocation()
    {
        boolean hasPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        if (hasPermission)
        {
            askPermissions();
        }
        else
        {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, mLocationListener);
        }
    }

    private void askPermissions()
    {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSION);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        Timber.d("onRequestPermissionsResult");
        switch (requestCode)
        {
            case 100:
            {
                for (int grantResult : grantResults)
                {
                    if (grantResult == PackageManager.PERMISSION_DENIED)
                    {
                        showRationaleDialog();
                        return;
                    }
                }
            }
        }
    }

    private void showRationaleDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setMessage(R.string.rational_dialog_message).setCancelable(false);
        builder.setPositiveButton(R.string.ask_me_again, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.i_dont_care, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        AlertDialog rationaleDialog = builder.create();
        rationaleDialog.show();
    }

    private Location getLastKnownLocation() {
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            mLocation = location;
            refreshForecast();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            Timber.d("provider - "+provider+", status - "+status+", extras: "+extras.toString());
        }

        @Override
        public void onProviderEnabled(String provider)
        {
            Timber.d("onProviderEnabled, provider - "+provider);
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Timber.d("onProviderDisabled, provider - "+provider);
        }
    };

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == mFabAdd) {
                    showToast("Button Add clicked");
                } else if (view == mFabDelete) {
                    showToast("Button Delete clicked");
                } else {
                    refreshForecast();
                }
                fam.close(true);
            }
        };
    }

    private void refreshForecast()
    {
        if(mLocation!=null)
        {
            getForcast();
        }
        else if(getLastKnownLocation()!=null)
        {
            mLocation = getLastKnownLocation();
            getForcast();
        }
        else
        {
            Snackbar.make(findViewById(R.id.content_main), "No location, is GPS on?", Snackbar.LENGTH_LONG).show();
        }
    }

    private void getForcast()
    {
        startService(CurrentConditionService.getForcastIntent(this, mLocation.getLatitude(), mLocation.getLongitude()));
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private void updateCards()
    {
        RealmResults<RealmForecast> listOfResults = mRealm.where(RealmForecast.class).findAll();
        if(listOfResults.size()>0)
        {
           mDegreesCard.updateData(listOfResults.get(0));
        }
    }

    private class ForcastReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            updateCards();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
        }
        else if (id == R.id.nav_gallery)
        {

        }
        else if (id == R.id.nav_slideshow)
        {

        }
        else if (id == R.id.nav_manage)
        {

        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
