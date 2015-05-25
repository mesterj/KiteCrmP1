package com.kite.joco.kitecrmp1.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.db.entites.Partner;
import com.kite.joco.kitecrmp1.db.entites.Partner$Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

/**
 * Created by Joco on 2015.05.25..
 */
public class PartnerRecAdapter extends RecyclerView.Adapter<PartnerRecAdapter.PartnerViewHolder> {


    @Override
    public PartnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View partnerLayoutView;
        partnerLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.partner_item, null);

        PartnerViewHolder partnerViewHolder = new PartnerViewHolder(partnerLayoutView);

        return partnerViewHolder;

    }

    @Override
    public void onBindViewHolder(PartnerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
                    break;
                case (R.id.btnHivas):
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    //callIntent.setData();
                    v.getContext().startActivity(callIntent);
                    break;
                case (R.id.tvEKapcsNev):
                    break;
                case (R.id.tvPartnernev):
                    break;

                default:

            }
        }
    }
}
