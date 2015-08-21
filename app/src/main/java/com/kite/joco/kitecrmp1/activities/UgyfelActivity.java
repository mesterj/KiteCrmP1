package com.kite.joco.kitecrmp1.activities;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        partnerAdapter = filladapterdata();
        rcvPartner.setAdapter(partnerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        partnerAdapter = filladapterdata();
        rcvPartner.setAdapter(partnerAdapter);
        partnerAdapter.notifyDataSetChanged();
    }

    public RecyclerView.Adapter filladapterdata (){
        List<Partner> partnerList = new Select().from(Partner.class).queryList();
        partnerAdapter = new PartnerRecAdapter(this,partnerList);
        return  partnerAdapter;
    }
}
