package com.kite.joco.kitecrmp1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kite.joco.kitecrmp1.Adapters.PartnerRecAdapter;
import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.db.entites.Partner;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

/**
 * Created by József on 2015.08.14..
 */
public class PartnerListFragment extends Fragment {

    public final String LOGTAG = "CRMDEMO:PartnerListFragment";

    RecyclerView recyclerViewPs;

    private RecyclerView.LayoutManager partnerLayoutManager;
    private RecyclerView.Adapter partnerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.partner_recycle_fragment,null);
        recyclerViewPs = (RecyclerView) root.findViewById(R.id.rcvPartnerListFrag);
        partnerLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewPs.setLayoutManager(partnerLayoutManager);
        List<Partner> partnerList = new Select().from(Partner.class).queryList();
        partnerAdapter = new PartnerRecAdapter(partnerList);
        recyclerViewPs.setAdapter(partnerAdapter);
        return root;
    }

    public void refreshRecView(List<Partner> partnerList) {
        Log.d(LOGTAG,"Recycler view refresh called");
        partnerAdapter = new PartnerRecAdapter(partnerList);
        partnerAdapter.notifyDataSetChanged();
        recyclerViewPs.setAdapter(partnerAdapter);
    }

}
