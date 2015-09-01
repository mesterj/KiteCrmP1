package com.kite.joco.kitecrmp1;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.kite.joco.kitecrmp1.activities.NewPartnerActivity;
import com.kite.joco.kitecrmp1.activities.PartnerListActivity;
import com.kite.joco.kitecrmp1.db.entites.Beosztas;
import com.kite.joco.kitecrmp1.db.entites.Contact;
import com.kite.joco.kitecrmp1.db.entites.Elerhetoseg;
import com.kite.joco.kitecrmp1.db.entites.Elerhetoseg_tipus;
import com.kite.joco.kitecrmp1.db.entites.Partner;

import java.util.ArrayList;
import java.util.Locale;


public class CrmMainActivity extends Activity {

    public static final String TAG="MAINACTIVITYTAG";
    @SuppressLint("NewApi")
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

        // Magyar locale beállítása a programnak
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale huloc = new Locale("hu");
        Locale.setDefault(huloc);
        //config.locale = huloc;
        //getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        config.setLocale(huloc);
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

        // Ezt minden módosítás után másolni kell a CrmLevelActivity-be, hogy mindenhol egyforma legyen a menű , de ez még majd kiderül.

        switch (id){
            case (R.id.action_settings):
                break;
            case (R.id.home):
                finish();
                break;
            case (R.id.new_contact):
                // itt fogom a new contact activitytt hívni
                break;
            case (R.id.new_partner):
                Intent partnerIntent = new Intent(getApplicationContext(),NewPartnerActivity.class);
                startActivity(partnerIntent);
                break;
            case (R.id.about):
                Toast.makeText(this, "Ezt a programot Józsi csinálta", Toast.LENGTH_LONG).show();
            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        switch (v.getId()){
            case (R.id.btnUgyfel):
                Intent ugyfelIntent = new Intent(this, PartnerListActivity.class);
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
        Beosztas b1 = new Beosztas();
        b1.setBeosztas("igazgató");
        b1.save();
        Beosztas b2 = new Beosztas();
        b2.setBeosztas("agronómus");
        b2.save();
        Elerhetoseg_tipus etip = new Elerhetoseg_tipus();
        etip.setTipus("Telefon");
        etip.save();
        Elerhetoseg_tipus etipemail = new Elerhetoseg_tipus();
        etipemail.setTipus("Email");
        etipemail.save();
        Elerhetoseg e = new Elerhetoseg();
        e.setTipus(etip);
        e.setElerhetosegadat("+36202375472");
        e.setCeges(true);

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
       // p.setSearchnev("KITE Zrt");
        p.setPs("106913");
        p.setTelepules("Nádudvar");
        p.setUtca("Bem J. u. 1-3");
        p.save();

        c.setPartner_id(p.getId());
        c.save();
        Log.i(TAG,"Data load finished");
        e.addToContact(c);
        e.save();

    }


}
