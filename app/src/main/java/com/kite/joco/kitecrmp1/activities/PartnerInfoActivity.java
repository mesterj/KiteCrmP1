package com.kite.joco.kitecrmp1.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.db.entites.Partner;
import com.kite.joco.kitecrmp1.db.entites.Partner$Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

public class PartnerInfoActivity extends CrmLevelActivity {

    public static final String KEY_PS_ID = "KEY_PS_ID";

    private final String LOGTAG="CRMDEMO:PSINFOAC ";

    TextView tvPsKod ;
    TextView tvPsNev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_info);
        long id = getIntent().getLongExtra(PartnerInfoActivity.KEY_PS_ID,0l);
        Log.d(LOGTAG,"Az id: " + id);
          Partner p;
            try {
                p = new Select().from(Partner.class).where(Condition.column(Partner$Table.ID).is(id)).querySingle();
                Log.d(LOGTAG,"Talált partner: " +p.getNev());
             //   tvPsKod = (TextView) findViewById(R.id.tvPsKod);
                tvPsNev = (TextView) findViewById(R.id.tvPsNev);
            //    tvPsKod.setText(p.getPs());
                tvPsNev.setText(p.getNev());
            } catch (Exception ex) {
                Log.d(LOGTAG, " Nem sikerült az id alapú lekérés, ez nagy baj! " + ex.getMessage());
                p = null;
                finish();
            }


    }

    public void onClick(View v){
        switch (v.getId()){
            case (R.id.btnClose):
                finish();
                break;
        }
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_partner_info, menu);
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
