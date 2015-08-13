package com.kite.joco.kitecrmp1.activities;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.kite.joco.kitecrmp1.Adapters.PartnerRecAdapter;
import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.db.entites.Partner;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class UgyfelActivity extends CrmLevelActivity {

    private RecyclerView rcvPartner;

    private RecyclerView.LayoutManager partnerLayoutManager;
    private RecyclerView.Adapter partnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ugyfel);
        //getActionBar().hide();
        rcvPartner = (RecyclerView) findViewById(R.id.rcvPartner);
        partnerLayoutManager = new LinearLayoutManager(this);
        rcvPartner.setLayoutManager(partnerLayoutManager);
        List<Partner> partnerList = new Select().from(Partner.class).queryList();
        partnerAdapter = new PartnerRecAdapter(partnerList);
        rcvPartner.setAdapter(partnerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ugyfel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
