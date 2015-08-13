package com.kite.joco.kitecrmp1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.kite.joco.kitecrmp1.activities.UgyfelActivity;
import com.kite.joco.kitecrmp1.db.entites.Beosztas;
import com.kite.joco.kitecrmp1.db.entites.Contact;
import com.kite.joco.kitecrmp1.db.entites.Elerhetoseg;
import com.kite.joco.kitecrmp1.db.entites.Elerhetoseg_tipus;
import com.kite.joco.kitecrmp1.db.entites.Partner;

import java.util.ArrayList;


public class CrmMainActivity extends Activity {

    public static final String TAG="MAINACTIVITYTAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crm_main);
        try {
            ActionBar actionBar = getActionBar();
            // Ez a home gomb ha lecserélem az ikonját akkor visszarak az alk. főoldalra (eggyel visszább)
            actionBar.setDisplayShowHomeEnabled(true);
            // Engedélyezi a feliratot ha saját layoutból fújom fel akkor kikapcs
            actionBar.setDisplayShowTitleEnabled(false);
            // Ez ad olyan vissza jelet amivel vissza lehet menni egy képernyőt. IOS-hez szokott userek hálásak lesznek érte
            //actionBar.setDisplayHomeAsUpEnabled(true);
            // Itt fújom fel a saját xml
            LayoutInflater inflater = LayoutInflater.from(this);
            View myactionbar = inflater.inflate(R.layout.actbarlayout, null);

            // itt állítom be a custom layoutot az actionbarhoz
            actionBar.setCustomView(myactionbar);
            // itt történik a costum actionbar megjelenítése
            actionBar.setDisplayShowCustomEnabled(true);
        } catch (Exception nex) {
            Log.d("ACTIONBARLOG", " A jó édes anyjáért nincs actionbar: " + nex.getLocalizedMessage());
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        switch (v.getId()){
            case (R.id.btnUgyfel):
                Intent ugyfelIntent = new Intent(this,UgyfelActivity.class);
                startActivity(ugyfelIntent);
                break;
            case (R.id.btnLoadData):
                loaddata();
                break;
            default:
                Toast.makeText(this, "A kapott id" + v.getId(), Toast.LENGTH_LONG).show();
                }
        }

    public void loaddata(){
        Log.i(TAG, "Data load started");
        Beosztas b = new Beosztas();
        b.setBeosztas("vérszívó");
        b.save();
        Elerhetoseg_tipus etip = new Elerhetoseg_tipus();
        etip.setTipus("Telefon");
        etip.save();
        Elerhetoseg_tipus etipemail = new Elerhetoseg_tipus();
        etipemail.setTipus("Email");
        etipemail.save();
        Elerhetoseg e = new Elerhetoseg();
        e.setTipus(etip);
        e.setElerhetosegadat("+36305702290");
        e.setCeges(true);
        e.save();
        Elerhetoseg eemail = new Elerhetoseg();
        eemail.setCeges(true);
        eemail.setElerhetosegadat("budaicsaba@kite.hu");
        eemail.setTipus(etipemail);

        Contact c = new Contact();

        c.setContact_vezeteknev("Budai");
        c.setContact_keresztnev("Csaba");
        ArrayList<Elerhetoseg> addeler = new ArrayList<Elerhetoseg>();
        addeler.add(e);
        addeler.add(eemail);
        c.setElerhetosegList(addeler);

        //eemail.setContact_id(c.getId());
        eemail.save();
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(c);
        Partner p = new Partner();
        p.setIrsz("4183");
        p.setNev("KITE Zrt");
        p.setPs("106913");
        p.setTelepules("Nádudvar");
        p.setUtca("Bem J. u. 1-3");
        p.save();

        c.addToPartner(p);
        c.save();
        Log.i(TAG,"Data load finished");
    }

}
