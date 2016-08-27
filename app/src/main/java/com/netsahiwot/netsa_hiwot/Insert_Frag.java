package com.netsahiwot.netsa_hiwot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sammie on 8/20/2016.
 */
public class Insert_Frag extends Fragment {

    EditText quo, loc;
    Button add;
    String q, l;
    TemptedDatabaseHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.insert, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quo = (EditText) view.findViewById(R.id.editText2);
        loc = (EditText) view.findViewById(R.id.editText3);
        helper = new TemptedDatabaseHelper(getContext());
        add = (Button) view.findViewById(R.id.button2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddQuote();
            }
        });
    }

    void AddQuote() {
        Log.d("Hi Sammie!!!", "AddQuote has been called.");
        q = quo.getText().toString();
        l = loc.getText().toString();

        if (!q.matches("")) {
            if (!l.matches("")) {
                if (helper.addQuote(q, l)) {
                    Toast.makeText(getContext(), "Quote Inserted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Quote Inserted!", Toast.LENGTH_SHORT).show();
                }
            } else {
                loc.setError("Field cannot be left empty.");
            }
        } else {
            quo.setError("Field cannot be left empty.");
        }
        Log.d("Hi Sammie!!!", "AddQuote has finished executing.");
        quo.setText("");
        loc.setText("");
    }

}