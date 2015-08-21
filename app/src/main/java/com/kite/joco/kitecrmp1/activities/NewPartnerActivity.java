package com.kite.joco.kitecrmp1.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.db.entites.Partner;

public class NewPartnerActivity extends CrmLevelActivity {

    public final String LOGTAG="KITECRMLOG-NEWPARTNER";
    EditText etPartnerNev,etPartnerIrsz,etPartnerTelepules,etPartnerAdo,etPartnerCim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_partner);
        etPartnerIrsz = (EditText) findViewById(R.id.etPartnerIrsz);
        etPartnerNev = (EditText) findViewById(R.id.etPartnerNev);
        etPartnerTelepules = (EditText) findViewById(R.id.etPartnerTelepules);
        etPartnerAdo = (EditText) findViewById(R.id.etPartnerAdo);
        etPartnerCim = (EditText) findViewById(R.id.etPartnerCim);
    }

    public void savePs(View v){
        Partner p = new Partner();
        // Ellenőrízni, hogy van e már ilyen.
        // adószám, egyértelmű
        // név , cím megegyezik , apa fia sorszámozás javasolása
        p.setAdoszam(etPartnerAdo.getText().toString());
        p.setIrsz(etPartnerIrsz.getText().toString());
        p.setNev(etPartnerNev.getText().toString());
       // p.setSearchnev(etPartnerNev.getText().toString());
        p.setTelepules(etPartnerTelepules.getText().toString());
        p.setUtca(etPartnerCim.getText().toString());
       try {
           p.save();
           finish();
           Log.d(LOGTAG, " Partner saved: " + p.getNev());
       }
       catch (Exception ex){
           Log.d(LOGTAG,"Hiba történt a mentés során: " + ex.getLocalizedMessage());
       }

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_partner, menu);
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
    }*/
}
