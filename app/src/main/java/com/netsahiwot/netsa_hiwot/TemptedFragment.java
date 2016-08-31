package com.netsahiwot.netsa_hiwot;

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
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Sammie on 8/26/2016.
 */

public class TemptedFragment extends Fragment {

    private static TemptedDatabaseHelper tdh;
    private static ImageView topImg;
    private static TextView txtQuote, txtAuth;
    private static ImageButton prev, nxt;
    private static int total, rand, back, id;
    private static ArrayList<ArrayList<String>> current;
    private static LinkedHashSet<Integer> lhs;
    private static LinkedList<Integer> ll;
    private static ListIterator desIter;
    private static String mode = null;
    private static Bundle bun;
    static String txt2copy;

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
        bun = getArguments();

        mode = bun.getString("mode");
        // Checks in what mode that the intent that created the activity wants to open this fragment
        if (mode.equals("NotifTempted")) {
            Log.d("Hi Sammie!!!", "I got the gift from the pending intent!!!!!!");

            id = bun.getInt("id");
            current = tdh.getAllVerses();
            setQuote(id);
            // Disables the previous and next buttons to inhibit navigation since this fragment has
            // been created from the notification
            prev.setVisibility(View.GONE);
            nxt.setVisibility(View.GONE);

        } else if (mode.equals("normal")) {
            Log.d("Hi Sammie!!!", "I didn't get the mode from the pending intent :-(");
            //gets the total number of quotes available in the database
            total = tdh.numberOfRows();
            //randomly selects a number from 0 to total available quotes
            rand = (int) (Math.random() * total);
            lhs.add(rand);
            Log.d("Hi Sammie", "The generated random no is " + rand);
            //calls a method in TemptedDatabaseHelper that returns an ArrayList of Arraylists
            current = tdh.getAllVerses();
            setQuote(rand);
            // Disables the prev button cause we can't go backwards initially
            prev.setEnabled(false);
            ll = new LinkedList<Integer>();
            desIter = ll.listIterator();
            //refreshLinkedList();
        }
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checks if there is an element in the LinkedList for us to go back
                if (desIter.hasNext() && desIter != null) {
                    //Gets the int value from the next object
                    back = (int) desIter.next();
                    // Conditional inserted inorder not to display the currently active quote
                    // again when the user navigates backward
                    if (back == rand) {
                        if (desIter.hasNext()) {
                            // Skips the current value and jumps to next value
                            back = (int) desIter.next();
                            setQuote(back);
                        }
                    } else {
                        setQuote(back);
                    }
                }
            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rand = (int) (Math.random() * total);
                // Removes an occurance of a value same as the random number to make the value of the
                // random number at the last of the LinkedHashSet. This is useful for the naavigation
                // to the previous quote
                lhs.remove(rand);
                // Adds the value of the random number to the last of the LinkedHashSet
                lhs.add(rand);
                setQuote(rand);
                refreshLinkedList();
                // Enables the prev button since we moved forward and is possible for us to
                // navigate forward
                if (!prev.isEnabled())
                    prev.setEnabled(true);
            }
        });
    }

    // Copies all the contents of the LinkedHashSet into the LinkedList
    private void refreshLinkedList() {
        ll = new LinkedList<Integer>();
        for (int temp : lhs) {
            ll.add(temp);
        }
        desIter = ll.listIterator();
        // Reverses the order of the items in the LinkedList for them to be navigated backwards using
        // the prev buttom
        Collections.reverse(ll);
    }

    // Sets the quote and verse chosen on the textviews
    private void setQuote(int rand) {
        txtQuote.setText("\"" + current.get(rand).get(0) + "\"");
        txtAuth.setText(current.get(rand).get(1));
        txt2copy = current.get(rand).get(0) + ".\n" + current.get(rand).get(1);
    }

    // Initializes  all the variables and objects to be used in the fragment
    void init(View view) {
        Log.d(getClass().getName(), "Started the init method...");

        lhs = new LinkedHashSet<Integer>();
        topImg = (ImageView) view.findViewById(R.id.topImg);
        txtQuote = (TextView) view.findViewById(R.id.textQuote);
        txtAuth = (TextView) view.findViewById(R.id.textAuth);
        tdh = new TemptedDatabaseHelper(getContext());
        nxt = (ImageButton) view.findViewById(R.id.btn_next);
        prev = (ImageButton) view.findViewById(R.id.btn_prev);
        bun = new Bundle();

        Log.d(getClass().getName(), "Started the init method...");
    }

}