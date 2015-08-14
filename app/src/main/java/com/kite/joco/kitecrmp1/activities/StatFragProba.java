package com.kite.joco.kitecrmp1.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.db.entites.Contact;
import com.kite.joco.kitecrmp1.db.entites.Contact$Table;
import com.kite.joco.kitecrmp1.db.entites.Partner;
import com.kite.joco.kitecrmp1.db.entites.Partner$Table;
import com.kite.joco.kitecrmp1.fragmentinterfaces.PartnerSearchInterface;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class StatFragProba extends CrmLevelActivity implements PartnerSearchInterface {

    public static final String LOGTAG = "CRMDEMO:StatFragProba";

    List<Partner> talaltPartnerek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_frag_proba);
    }

    @Override
    public void PartnerSearch(String searchparam) {
        // A másik fragmenten beírt név alapján keresem a partner táblában a partnert
        talaltPartnerek = new Select().from(Partner.class).where(Condition.column(Partner$Table.NEV).like(searchparam)).queryList();

        List<Contact> talaltContactokbyVezeteknev = new Select().from(Contact.class).where(Condition.column(Contact$Table.CONTACT_VEZETEKNEV).like(searchparam)).queryList();
        List<Contact> talaltContactok = new Select().from(Contact.class).where(Condition.column(Contact$Table.CONTACT_KERESZTNEV).like(searchparam)).queryList();
        talaltContactok.addAll(talaltContactokbyVezeteknev);

        for (Contact c: talaltContactok){
            talaltPartnerek.add(c.getPartner());
            Log.d(LOGTAG, "Talált partner neve: " + c.getPartner().getNev());
        }

    }
}
