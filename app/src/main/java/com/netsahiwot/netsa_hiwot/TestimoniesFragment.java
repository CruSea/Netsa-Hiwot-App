package com.netsahiwot.netsa_hiwot;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by Sammie on 8/15/2016.
 */

public class TestimoniesFragment extends Fragment {

    GridView tgv;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.gridview_fragment, container, false);
        tgv = (GridView) view.findViewById(R.id.gv);
        tgv.setAdapter(new TestimoniesGridViewAdapter(getActivity()));
        return view;
    }

}