package com.example.adm.mobquestnew;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Settings extends Fragment {

    View v;
    MainActivity ActivMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.create_map, container, false);
        //Bundle bundle = getArguments();
        // Gets the MapView from the XML layout and creates it
        ActivMain = ((MainActivity)getActivity());
        ActivMain.getSupportActionBar().setTitle(R.string.toolbar_group);
        ActivMain.new_fragment(R.id.gotomap);
        return v;
    }
}