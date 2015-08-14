package com.kite.joco.kitecrmp1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kite.joco.kitecrmp1.R;

/**
 * Created by Joco on 2015.08.14..
 */
public class PartnerSearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.partnersearch_fragment,null);
        return root;

    }
}
