package com.kite.joco.kitecrmp1.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.db.entites.Beosztas;
import com.kite.joco.kitecrmp1.db.entites.Contact;
import com.kite.joco.kitecrmp1.db.entites.Contact$Table;
import com.kite.joco.kitecrmp1.db.entites.Partner;
import com.kite.joco.kitecrmp1.db.entites.Partner$Table;
import com.kite.joco.kitecrmp1.fragmentinterfaces.PartnerSearchInterface;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class NewContactActivity extends CrmLevelActivity implements PartnerSearchInterface{

    public static final String LOGTAG = "KITCRM:NEWCONTACT";
    private Spinner spnContactBeosztas;
    private ArrayAdapter<Beosztas> beosztasArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        // Ezek azért kellenek, hogy ne kérjen PIN kódot
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        //,setContentView(R.layout.activity_new_contact);

        spnContactBeosztas = (Spinner) findViewById(R.id.spnKapcsolatBeosztas);

        List<Beosztas> beosztasList = new Select().from(Beosztas.class).queryList();
        beosztasArrayAdapter = new ArrayAdapter<Beosztas>(this,android.R.layout.simple_spinner_item,beosztasList);
        spnContactBeosztas.setAdapter(beosztasArrayAdapter);


     }

    @Override
    public void PartnerSearch(String searchparam) {
        // A másik fragmenten beírt név alapján keresem a partner táblában a partnert
        List<Partner> talaltPartnerek = new Select().from(Partner.class).where(Condition.column(Partner$Table.NEV).like(searchparam)).queryList();

        List<Contact> talaltContactokbyVezeteknev = new Select().from(Contact.class).where(Condition.column(Contact$Table.CONTACT_VEZETEKNEV).like(searchparam)).queryList();
        List<Contact> talaltContactok = new Select().from(Contact.class).where(Condition.column(Contact$Table.CONTACT_KERESZTNEV).like(searchparam)).queryList();
        talaltContactok.addAll(talaltContactokbyVezeteknev);

        for (Contact c: talaltContactok){
            talaltPartnerek.add(c.getPartner());
            Log.d(LOGTAG,"Talált partner neve: " + c.getPartner().getNev());
        }


    }

    @Override
    public void PartnerSearchbyNevandTelepules(String nevparam, String telepulesparam) {

    }
/*
ants = new Select()
              .from(Ant.class)
              .where(Condition.column(Ant$Table.QUEENFOREIGNKEYCONTAINER_QUEEN_ID).is(id))
              .queryList();
 */
/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crm_contact, menu);
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
