package com.kite.joco.kitecrmp1.activities;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kite.joco.kitecrmp1.R;

public class ContactListActivity extends CrmLevelActivity implements SearchView.OnQueryTextListener {

    private static final String LOGTAG ="CRM:ContactListActivity:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        /*Toolbar tbContactSearch = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(tbContactSearch);
        tbContactSearch.inflateMenu(R.menu.menu_contact_list);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crm_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.actbarcontactsearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Ez akkor kell ha el akarom küldetni a keresés eredményét.
        Toast.makeText(this," a kereső szöveg:" + query,Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // Ez az "okos" keresés .
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(LOGTAG, "oncreate");
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d(LOGTAG, " Search fired with : " + query);
            //showsearchString(query);
        }
        else {
            Log.d("TOOLBARSEARCH"," Nem kereséssel lett az Activity meghívva");
        }
    }
}
