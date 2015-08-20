package com.kite.joco.kitecrmp1.Adapters;

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

import java.util.List;

/**
 * Created by Joco on 2015.05.25..
 */
public class PartnerRecAdapter extends RecyclerView.Adapter<PartnerRecAdapter.PartnerViewHolder> implements View.OnClickListener, View.OnLongClickListener{

    public List<Partner> psData;
    public static final String LOGTAG="CRMDEMO_PS_ADAPTER";

    public PartnerRecAdapter(List<Partner> psData){
        this.psData = psData;
    }

    View partnerLayoutView;

    @Override
    public PartnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        partnerLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.partner_item, null);

        PartnerViewHolder partnerViewHolder = new PartnerViewHolder(partnerLayoutView);


        partnerLayoutView.setOnClickListener(this);
        partnerLayoutView.setOnLongClickListener(this);
        partnerViewHolder.tvPartnerNev.setOnClickListener(this);
        partnerViewHolder.tvPartnerNev.setOnLongClickListener(this);
        partnerViewHolder.tvPartnerNev.setTag(partnerViewHolder);
        partnerLayoutView.setTag(partnerViewHolder);



        return partnerViewHolder;

    }

    @Override
    public void onBindViewHolder(PartnerViewHolder holder, int position) {
        Partner aktPartner = psData.get(position);
        Log.i(LOGTAG, "Akt partner neve: " + aktPartner.getNev());

        holder.tvPartnerNev.setText(aktPartner.getNev().toString());
       // Log.i(TAG,"psData mérete" + psData.size());

        try {
            List<Contact> contacts = new Select().from(Contact.class).where(Condition.column("partner_id").is(aktPartner.getId())).queryList();
            int contsize = contacts.size();
            if (contsize != 0 ) {
                Log.i(LOGTAG, "Ennyi elemből áll a contact lista: " + contsize);
                Contact item_contact = contacts.get(0);
                String kapcsnev = item_contact.getContact_vezeteknev() + " " + item_contact.getContact_keresztnev();
                holder.tvKapcsolatNev.setText(kapcsnev);
                Log.d(LOGTAG,"A kapcsolat teljes neve: " + kapcsnev);
                holder.btnEmail.setClickable(true);
                holder.btnHivas.setClickable(true);
            } else {
                Log.d(LOGTAG,"Nincs rögzítve kapcsolat");
                holder.tvKapcsolatNev.setText("Nincs rögzítve kapcsolat");
                holder.btnEmail.setClickable(false);
                holder.btnHivas.setClickable(false);
            }
        }
        catch (Exception ex){
            Log.d(LOGTAG,ex.getLocalizedMessage());
        }
    }

    @Override
    public int getItemCount() {
        Log.d(LOGTAG, " PSDATA mérete: " + psData.size());
        return psData.size();
    }

    @Override
    public void onClick(View v) {
        Log.d(LOGTAG,"Újfajta onClick meghívva");
        try {
            Log.d(LOGTAG, "Partner: " + getItemId(1));
        }
        catch (Exception ex){
            Log.d(LOGTAG,ex.getMessage());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Log.d(LOGTAG,"Újfajta onLongClick meghívva");
        return false;
    }

    public Partner getItem(int partnerpos){
        return psData.get(partnerpos);
    }

    public static class PartnerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String LOGTAG = "CRMDEMO:PartnerViewHolder" ;
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


            // A recyclerView 0-tól kezd, a dbflow 1-től ezért kell a +1
            partnerposition++;

            try {
                Partner p = new Select().from(Partner.class).where(Condition.column(Partner$Table.ID).is(partnerposition)).querySingle();
             //   Log.d(LOGTAG, "Partner neve:" + psData.get(getAdapterPosition()).getNev());

             //   p.getId();
                Log.d(LOGTAG,"Kiválasztott partner: " + p.getNev());
            }
            catch (Exception ex) {
                Log.d(LOGTAG,"Nem sikerült a partner kiválasztása: " + ex.getLocalizedMessage());
            }

            switch (v.getId()) {
                case (R.id.btnEmail):
                   // sendmail();
                    Toast.makeText(v.getContext(), "Email küldés lesz", Toast.LENGTH_LONG).show();
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
