package com.kite.joco.kitecrmp1.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.db.entites.Contact;
import com.kite.joco.kitecrmp1.db.entites.Partner;
import com.kite.joco.kitecrmp1.db.entites.Partner$Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joco on 2015.05.25..
 */
public class PartnerRecAdapter extends RecyclerView.Adapter<PartnerRecAdapter.PartnerViewHolder> {

    List<Partner> psData;
    public static final String TAG="CRMDEMO_PS_ADAPTER";

    public PartnerRecAdapter(List<Partner> psData){
        this.psData = psData;
    }

    @Override
    public PartnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View partnerLayoutView;
        partnerLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.partner_item, null);

        PartnerViewHolder partnerViewHolder = new PartnerViewHolder(partnerLayoutView);

        return partnerViewHolder;

    }

    @Override
    public void onBindViewHolder(PartnerViewHolder holder, int position) {
        Partner aktPartner = psData.get(position);
        Log.i(TAG, "Akt partner neve: " + aktPartner.getNev());

        holder.tvPartnerNev.setText(aktPartner.getNev().toString());
       // Log.i(TAG,"psData mérete" + psData.size());

        try {
            List<Contact> contacts = new Select().from(Contact.class).where(Condition.column("partner_id").is(aktPartner.getId())).queryList();
            int contsize = contacts.size();
            if (contsize != 0 ) {
                Log.i(TAG, "Ennyi elemből áll a contact lista: " + contsize);
                Contact item_contact = contacts.get(0);
                String kapcsnev = item_contact.getContact_vezeteknev() + " " + item_contact.getContact_keresztnev();
                holder.tvKapcsolatNev.setText(kapcsnev);
                Log.d(TAG,"A kapcsolat teljes neve: " + kapcsnev);
                holder.btnEmail.setClickable(true);
                holder.btnHivas.setClickable(true);
            } else {
                Log.d(TAG,"Nincs rögzítve kapcsolat");
                holder.tvKapcsolatNev.setText("Nincs rögzítve kapcsolat");
                holder.btnEmail.setClickable(false);
                holder.btnHivas.setClickable(false);
            }
        }
        catch (Exception ex){
            Log.d(TAG,ex.getLocalizedMessage());
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG," PSDATA mérete: "+psData.size());
        return psData.size();
    }

    public static class PartnerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvPartnerNev;
        public TextView tvKapcsolatNev;
        public Button btnHivas;
        public Button btnEmail;

        public PartnerViewHolder(View itemView) {
            super(itemView);
            tvPartnerNev = (TextView) itemView.findViewById(R.id.tvPartnernev);
            tvPartnerNev.setOnClickListener(this);
            tvKapcsolatNev = (TextView) itemView.findViewById(R.id.tvEKapcsNev);
            tvKapcsolatNev.setOnClickListener(this);
            btnHivas = (Button) itemView.findViewById(R.id.btnHivas);
            btnHivas.setOnClickListener(this);
            btnEmail = (Button) itemView.findViewById(R.id.btnEmail);
            btnEmail.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            long partnerposition =getAdapterPosition();
            Partner p = new Select().from(Partner.class).where(Condition.column(Partner$Table.ID).is(partnerposition)).querySingle();
                    p.getId();
            switch (v.getId()) {
                case (R.id.btnEmail):
                   // sendmail();
                    Toast.makeText(v.getContext(),"Email küldés lesz",Toast.LENGTH_LONG).show();
                    break;
                case (R.id.btnHivas):
                    //Intent callIntent = new Intent(Intent.ACTION_CALL);
                    //callIntent.setData();
                    //v.getContext().startActivity(callIntent);
                    Toast.makeText(v.getContext(),"Hívás lesz",Toast.LENGTH_LONG).show();
                    break;
                case (R.id.tvEKapcsNev):
                    Toast.makeText(v.getContext(),"Kapcsolat",Toast.LENGTH_LONG).show();
                    break;
                case (R.id.tvPartnernev):
                    Toast.makeText(v.getContext(),"Partner név",Toast.LENGTH_LONG).show();
                    break;

                default:

            }
        }
    }
}
