package com.futuzon.opccounter.view.opc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futuzon.opccounter.R;


public class OpcCounterFragment extends Fragment {

    public OpcCounterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO: start here
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_opc_counter, container, false);
    }
}
