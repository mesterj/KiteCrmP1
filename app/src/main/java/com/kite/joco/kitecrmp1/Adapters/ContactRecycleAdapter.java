package com.kite.joco.kitecrmp1.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.db.entites.Contact;

import java.util.List;

/**
 * Created by Mester József on 2015.08.24..
 */
public class ContactRecycleAdapter extends RecyclerView.Adapter<ContactRecycleAdapter.ContactViewHolder> {

    List<Contact> contactList;
    Context context;

    public ContactRecycleAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO Amikor rábök a júzer a kontatkra akkor jön fel a kontakt adatai részletesen egy "rendes" layoutban
        // amit már fel tudok fújni kódból rendesen
        // Ha a hívás gombra nyom és csak egy telefonszáma van a kontaktnak akkor azt hívja ha több akkor dialógfregmensbe
        // rakom a több számot és onnan tudja hívni amelyiket akarja.

        View contactItemView = LayoutInflater.from(context).inflate(R.layout.contact_item,parent,false);


        return new ContactViewHolder(contactItemView, new ContactViewHolder.ContactClickInterface() {
            @Override
            public void getContact(View callerview, int position) {

            }
        });

    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
         Contact c = contactList.get(position);

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ContactClickInterface contactClickInterface;
        TextView tvContactNev;

        public ContactViewHolder(View itemView, ContactClickInterface clickInterface) {
            super(itemView);
            clickInterface = contactClickInterface;
            tvContactNev = (TextView) itemView.findViewWithTag("ContactNevText");

            //tvContactNev.setOnClickListener(this);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            contactClickInterface.getContact(v,getAdapterPosition());
        }

        public interface ContactClickInterface{
            void getContact(View callerview,int position);
        }
    }
}
