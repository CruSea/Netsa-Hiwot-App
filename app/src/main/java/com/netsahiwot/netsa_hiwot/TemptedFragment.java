package com.netsahiwot.netsa_hiwot;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sammie on 8/26/2016.
 */

public class TemptedFragment extends Fragment {

    private static TemptedDatabaseHelper tdh;
    private static ImageView topImg;
    private static TextView txtQuote, txtAuth;
    private static ImageButton prev, nxt;
    private static int total;
    private static ArrayList<ArrayList<String>> current;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tempted, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        //This next code is used to check if a database has been created from the assets folder
        //and if so it open a connection with it.
        Log.d(getClass().getName(), "TemptedDatabaseHelper is about to be initialized...");
        tdh.openDataBase();
        Log.d(getClass().getName(), "TemptedDatabaseHelper has successfully been initialized...");

        //gets the total number of quotes available in the database
        total = tdh.numberOfRows();
        //randomly selects a number from 0 to total available quotes
        int rand = (int) (Math.random() * total);
        Log.d("Hi Sammie", "The generated random no is " + rand);
        //calls a method in TemptedDatabaseHelper that returns an ArrayList of Arraylists
        current = tdh.getAllVerses();

        txtQuote.setText("\"" + current.get(rand).get(0) + "\"");
        txtAuth.setText(current.get(rand).get(1));

    }

    void init(View view) {
        Log.d(getClass().getName(), "Started the init method...");

        topImg = (ImageView) view.findViewById(R.id.topImg);
        txtQuote = (TextView) view.findViewById(R.id.textQuote);
        txtAuth = (TextView) view.findViewById(R.id.textAuth);
        tdh = new TemptedDatabaseHelper(getContext());
        nxt = (ImageButton) view.findViewById(R.id.btn_next);
        prev = (ImageButton) view.findViewById(R.id.btn_prev);

        Log.d(getClass().getName(), "Started the init method...");
    }

}
