package com.ziv.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private Realm mRealem;
    private TextView mTextView;
    private FloatingActionButton mFabAdd;
    private FloatingActionButton mFabDelete;
    private FloatingActionMenu fam;
    private FloatingActionButton mFabRedfresh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRealem = Realm.getDefaultInstance();
        mFabAdd = (FloatingActionButton) findViewById(R.id.fab2);
        mFabDelete = (FloatingActionButton) findViewById(R.id.fab3);
        mFabRedfresh = (FloatingActionButton) findViewById(R.id.fab1);
        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);

        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    showToast("Menu is opened");
                } else {
                    showToast("Menu is closed");
                }
            }
        });


        //handling each floating action button clicked
        mFabDelete.setOnClickListener(onButtonClick());
        mFabRedfresh.setOnClickListener(onButtonClick());
        mFabAdd.setOnClickListener(onButtonClick());

        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened()) {
                    fam.close(true);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mTextView = (TextView) findViewById(R.id.main_text);
        updtaeText();
        LocalBroadcastManager.getInstance(this).registerReceiver(new ForcastReceiver(),
                new IntentFilter("custom-event-name"));
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == mFabAdd) {
                    showToast("Button Add clicked");
                } else if (view == mFabDelete) {
                    showToast("Button Delete clicked");
                } else {
                    showToast("Button Edit clicked");
                }
                fam.close(true);
            }
        };
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void updtaeText()
    {
        mTextView.animate().alpha(0).start();
        RealmResults<ForcastResults> listOfResults = mRealem.allObjects(ForcastResults.class);
        if(listOfResults!=null && listOfResults.size()>0)
        {
            mTextView.setText((listOfResults.get(listOfResults.size()-1).getDesc()));
            mTextView.animate().alpha(1).start();
        }
    }

    private class ForcastReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            updtaeText();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealem.close();
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
    public boolean onNavigationItemSelected(MenuItem item)
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
