package com.netsahiwot.netsa_hiwot;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by Sammie on 8/25/2016.
 */

public class Tempted_CRUD extends FragmentActivity {

    ViewPager pager;
    PagerSlidingTabStrip tabStrip;
    TemptedDatabaseHelper tdh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tempted_crud);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setTitle("Tempted");
        bar.setDisplayHomeAsUpEnabled(true);

        Log.d(getClass().getName(), "TemptedDatabaseHelper is about to be initialized...");
        tdh = new TemptedDatabaseHelper(this);
        tdh.openDataBase();
        Log.d(getClass().getName(), "TemptedDatabaseHelper has successfully been initialized...");

        pager = (ViewPager) findViewById(R.id.pager);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager.setAdapter(new VPAdapter(getSupportFragmentManager(), getApplicationContext()));
        tabStrip.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                overridePendingTransition(R.anim.open_main, R.anim.close_next);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // when back button pressed close database and back to previous page
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.open_main, R.anim.close_next);
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        // Ignore orientation change to keep activity from restarting
        super.onConfigurationChanged(newConfig);
    }

}