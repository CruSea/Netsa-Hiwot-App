package com.netsahiwot.netsa_hiwot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sammie on 8/22/2016.
 */

public class Update_Frag extends Fragment {

    EditText quo, loc, id;
    Button update;
    String q, l;
    TemptedDatabaseHelper helper;
    int id2d = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.update, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quo = (EditText) view.findViewById(R.id.editText2);
        loc = (EditText) view.findViewById(R.id.editText3);
        id = (EditText) view.findViewById(R.id.editText4);
        helper = new TemptedDatabaseHelper(getContext());
        update = (Button) view.findViewById(R.id.button2);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateQuote();
            }
        });
    }

    void UpdateQuote() {
        Log.d("Hi Sammie!!!", "UpdateQuote has been called.");
        id2d = Integer.parseInt(id.getText().toString());
        q = quo.getText().toString();
        l = loc.getText().toString();

        if (!id.getText().toString().matches("")) {
            if (!q.matches("")) {
                if (!l.matches("")) {
                    if (helper.updateQuote(id2d, q, l)) {
                        Toast.makeText(getContext(), "Quote Updated!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Quote Update Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    loc.setError("Field cannot be left empty.");
                }
            } else {
                quo.setError("Field cannot be left empty.");
            }
            Log.d("Hi Sammie!!!", "UpdateQuote has finished executing.");
            id.setText("");
            quo.setText("");
            loc.setText("");
        } else {
            id.setError("ID can't be left empty");
        }
    }
}