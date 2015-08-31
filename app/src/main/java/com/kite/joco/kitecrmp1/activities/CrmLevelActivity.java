package com.kite.joco.kitecrmp1.activities;

/**
 * Created by Joco on 2015.08.13..
 */

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.kite.joco.kitecrmp1.R;


public class CrmLevelActivity extends Activity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_crm_level);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeActionContentDescription("ez a home");
        LayoutInflater inflater = LayoutInflater.from(this);
        View myactionbar = inflater.inflate(R.layout.actbarlayout,null);

        // itt állítom be a custom layoutot az actionbarhoz
  //      actionBar.setCustomView(myactionbar);
        // itt történik a costum actionbar megjelenítése
        actionBar.setDisplayShowCustomEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crm_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        switch (id){
            case (R.id.action_settings):
                break;
            case (R.id.home):
                finish();
                break;
            case (R.id.new_contact):
                Intent contactIntent = new Intent(getApplicationContext(),NewContactActivity.class);
                startActivity(contactIntent);
                break;
            case (R.id.new_partner):
                Intent partnerIntent = new Intent(getApplicationContext(),NewPartnerActivity.class);
                startActivity(partnerIntent);
                break;
            case (R.id.partnerList):
                Intent partnerlistIntent = new Intent(getApplicationContext(),PartnerListActivity.class);
                startActivity(partnerlistIntent);
                break;
            case (R.id.about):
                Toast.makeText(this, "Ezt a programot Józsi csinálta", Toast.LENGTH_LONG).show();
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
