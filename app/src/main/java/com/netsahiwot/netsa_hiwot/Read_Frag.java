package com.netsahiwot.netsa_hiwot;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.netsahiwot.netsa_hiwot.TemptedDatabaseHelper.ID;
import static com.netsahiwot.netsa_hiwot.TemptedDatabaseHelper.LOC_AUTH;
import static com.netsahiwot.netsa_hiwot.TemptedDatabaseHelper.VERSE;

/**
 * Created by Sammie on 8/20/2016.
 */
public class Read_Frag extends Fragment {
    TemptedDatabaseHelper helper;
    Button dispAll;
    Cursor cursor;
    TextView display;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.read, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        helper = new TemptedDatabaseHelper(getContext());
        dispAll = (Button) view.findViewById(R.id.button2);
        display = (TextView) view.findViewById(R.id.display);
        dispAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayAll();
            }
        });
    }

    void DisplayAll() {
        String[] s = new String[2];
        int id;
        Log.d("Hi Sammie!!!", "AddQuote has been called.");
        cursor = helper.getAllQuote();
        cursor.moveToFirst();
        Log.d("Hi Sammie!!! ---", "Moved to the first of the cursor...");

        display.setText("All Items\n");
        while (!cursor.isAfterLast()) {
            id = cursor.getInt(cursor.getColumnIndex(ID));
            s[0] = cursor.getString(cursor.getColumnIndex(VERSE));
            s[1] = cursor.getString(cursor.getColumnIndex(LOC_AUTH));
            display.append("\n" + id + ". " + s[0] + "; " + s[1]);
            cursor.moveToNext();
        }
        Log.d("Hi Sammie!!!", "DisplayAll has finished executing.");
    }

}