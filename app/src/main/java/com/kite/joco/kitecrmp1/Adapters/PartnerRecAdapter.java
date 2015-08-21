package com.kite.joco.kitecrmp1.Adapters;

import android.content.Context;
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
import com.kite.joco.kitecrmp1.activities.PartnerInfoActivity;
import com.kite.joco.kitecrmp1.db.entites.Contact;
import com.kite.joco.kitecrmp1.db.entites.Partner;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

/**
 * Created by Joco on 2015.05.25..
 */
public class PartnerRecAdapter extends RecyclerView.Adapter<PartnerRecAdapter.PartnerViewHolder> {

    public List<Partner> psData;
    public static final String LOGTAG = "CRMDEMO_PS_ADAPTER";
    Context context;

    public PartnerRecAdapter(Context context, List<Partner> psData) {
        this.context = context;
        this.psData = psData;
    }

    @Override
    public PartnerViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View partnerLayoutView = LayoutInflater.from(context).inflate(R.layout.partner_item, parent, false);

        //PartnerViewHolder partnerViewHolder = new PartnerViewHolder(partnerLayoutView, new PartnerViewHolder.PartnerViewHolderClicks() {
        return new PartnerViewHolder(partnerLayoutView, new PartnerViewHolder.PartnerViewHolderClicks() {
            @Override
            public void onPartner(View caller, int position) {
               // Toast.makeText(context, "Partner obj települése: " + psData.get(position).getTelepules(), Toast.LENGTH_SHORT).show();
                Intent psIntent = new Intent(context, PartnerInfoActivity.class);
                psIntent.putExtra(PartnerInfoActivity.KEY_PS_ID,psData.get(position).getId());
                context.startActivity(psIntent);
            }

            @Override
            public void onPartnerNev(TextView textView, int position) {
                Toast.makeText(context, "Partner név: " + textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCallButton(Button button, int position) {
                Toast.makeText(context, "Hívás lesz", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEmailButton(Button button, int position) {
                Toast.makeText(context, "Email küldés lesz", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConnectName(TextView textView, int position) {
                Toast.makeText(context, " A kapcsolat neve: " + psData.get(position).getKapcsolatok().get(0).getContact_vezeteknev(), Toast.LENGTH_SHORT).show();
            }
        });

        //return partnerViewHolder;

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
            if (contsize != 0) {
                Log.i(LOGTAG, "Ennyi elemből áll a contact lista: " + contsize);
                Contact item_contact = contacts.get(0);
                String kapcsnev = item_contact.getContact_vezeteknev() + " " + item_contact.getContact_keresztnev();
                holder.tvKapcsolatNev.setText(kapcsnev);
                Log.d(LOGTAG, "A kapcsolat teljes neve: " + kapcsnev);
                holder.btnEmail.setClickable(true);
                holder.btnHivas.setClickable(true);
            } else {
                Log.d(LOGTAG, "Nincs rögzítve kapcsolat");
                holder.tvKapcsolatNev.setText("Nincs rögzítve kapcsolat");
                holder.btnEmail.setClickable(false);
                holder.btnHivas.setClickable(false);
            }
        } catch (Exception ex) {
            Log.d(LOGTAG, ex.getLocalizedMessage());
        }
    }

    @Override
    public int getItemCount() {
        Log.d(LOGTAG, " PSDATA mérete: " + psData.size());
        return psData.size();
    }

    public static class PartnerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String LOGTAG = "CRMDEMO:PartnerViewHolder";
        TextView tvPartnerNev;
        TextView tvKapcsolatNev;
        Button btnHivas;
        Button btnEmail;
        PartnerViewHolderClicks partnerClicklistener;


        public PartnerViewHolder(View itemView, PartnerViewHolderClicks partnerClicklistener) {
            super(itemView);
            this.partnerClicklistener = partnerClicklistener;
            tvPartnerNev = (TextView) itemView.findViewById(R.id.tvPartnernev);
            tvPartnerNev.setOnClickListener(this);
            tvKapcsolatNev = (TextView) itemView.findViewById(R.id.tvEKapcsNev);
            tvKapcsolatNev.setOnClickListener(this);
            btnHivas = (Button) itemView.findViewById(R.id.btnHivas);
            btnHivas.setOnClickListener(this);
            btnEmail = (Button) itemView.findViewById(R.id.btnEmail);
            btnEmail.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case (R.id.btnEmail):
                    partnerClicklistener.onEmailButton((Button) v, getAdapterPosition());
                    break;
                case (R.id.btnHivas):
                    partnerClicklistener.onCallButton((Button) v, getAdapterPosition());
                    break;
                case (R.id.tvEKapcsNev):
                    try {
                        partnerClicklistener.onConnectName((TextView) v, getAdapterPosition());
                    } catch (Exception ex) {
                        Log.d(LOGTAG, "Hiba (kapcs név): " + ex.getMessage() + " pozíció: " + getAdapterPosition());
                    }
                    break;
                case (R.id.tvPartnernev):
                    partnerClicklistener.onPartnerNev((TextView) v, getAdapterPosition());
                    break;
                default:
                    partnerClicklistener.onPartner(v, getAdapterPosition());
                    Log.d(LOGTAG, "default eset.");

            }
        }

        public interface PartnerViewHolderClicks {
            void onPartner(View caller, int position);
            void onPartnerNev(TextView textView, int position);
            void onCallButton(Button button, int position);
            void onEmailButton(Button button, int position);
            void onConnectName(TextView v, int position);
        }
    }
}

