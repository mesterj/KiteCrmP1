package com.kite.joco.kitecrmp1.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.db.entites.Partner;
import com.kite.joco.kitecrmp1.db.entites.Partner$Table;
import com.kite.joco.kitecrmp1.fragmentinterfaces.PartnerSearchInterface;
import com.kite.joco.kitecrmp1.fragments.PartnerListFragment;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class PartnerListActivity extends CrmLevelActivity implements PartnerSearchInterface {

    public static final String LOGTAG = "CRMDEMO:PartnerListActivity Activity ";

    List<Partner> talaltPartnerek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_frag_proba);
    }

    @Override
    public void PartnerSearch(String searchparam) {
        searchparam = "%"+searchparam+"%";
        Log.d(LOGTAG, "Az activity partnersearch elindul: " + searchparam);

        // TODO Erre a keresésre még sok helyen szükség lehet , ki kellene szervezni egy util osztályba
        // A másik fragmenten beírt név alapján keresem a partner táblában a partnert
        Log.d(LOGTAG," Keresés nagy betűvel : " + searchparam.toUpperCase());
        List<Partner> talaltPartnerek = new Select().from(Partner.class).where(Condition.column(Partner$Table.SEARCHNEV).like(searchparam.toUpperCase())).queryList();

        // TODO Contact nev adatok alapján is kell a keresés

        // TODO Ezt ki lehet venni a logból
        Log.d(LOGTAG,"Végső találati lista");
        for (Partner p:talaltPartnerek){
            Log.d(LOGTAG," Talált partnerek nevei: " +p.getNev() );
        }

        // A kontaktok között is keressen. Ha partnert keresek.

       /* List<Contact> talaltContactokbyVezeteknev = new Select().from(Contact.class).where(Condition.column(Contact$Table.CONTACT_VEZETEKNEV).like(searchparam)).queryList();
        List<Contact> talaltContactok = new Select().from(Contact.class).where(Condition.column(Contact$Table.CONTACT_KERESZTNEV).like(searchparam)).queryList();
        talaltContactok.addAll(talaltContactokbyVezeteknev);

        for (Contact c: talaltContactok){
            talaltPartnerek.add(c.getPartner());
            Log.d(LOGTAG, "Talált partner neve: " + c.getPartner().getNev());
        }*/

        FragmentManager fragmentManager = getFragmentManager();
        PartnerListFragment f2 = (PartnerListFragment) fragmentManager.findFragmentById(R.id.partnerListFrag);
        f2.refreshRecView(talaltPartnerek);

    }

    @Override
    public void PartnerSearchbyNevandTelepules(String nevparam, String telepulesparam) {
        String knevparam = "%"+ nevparam.toUpperCase()+"%";
        String ktelepulesparam = "%"+ telepulesparam.toUpperCase()+"%";
        Log.d(LOGTAG,"Keresés település szerint");

        List<Partner> nevteleppslista = new Select().from(Partner.class).where(Condition.column(Partner$Table.SEARCHNEV)
                .like(knevparam)).and(Condition.column(Partner$Table.TELEPULES).like(ktelepulesparam)).queryList();

        FragmentManager fragmentManager = getFragmentManager();
        PartnerListFragment f2 = (PartnerListFragment) fragmentManager.findFragmentById(R.id.partnerListFrag);
        f2.refreshRecView(nevteleppslista);
    }

    public void onClick(View v){
        Log.d(LOGTAG,"Onclickje");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
