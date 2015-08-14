package com.kite.joco.kitecrmp1.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.kite.joco.kitecrmp1.R;
import com.kite.joco.kitecrmp1.fragmentinterfaces.PartnerSearchInterface;

/**
 * Created by Joco on 2015.08.14..
 */
public class PartnerSearchFragment extends Fragment implements View.OnClickListener {


    PartnerSearchInterface partnerSearchInterface;

    EditText etPartnerSearch;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.partnersearch_fragment, null);
        //etPartnerSearch = (EditText) root.findViewById(R.id.etPsSearchNev);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        partnerSearchInterface = (PartnerSearchInterface) getActivity();
        etPartnerSearch = (EditText) getActivity().findViewById(R.id.etPsSearchNev);
    }

    public void onClick(View v) {
        partnerSearchInterface.PartnerSearch(etPartnerSearch.getText().toString());
    }
}
