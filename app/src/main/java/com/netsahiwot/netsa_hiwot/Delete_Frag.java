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
import android.widget.TextView;

/**
 * Created by Sammie on 8/22/2016.
 */

public class Delete_Frag extends Fragment {

    EditText id;
    Button delete;
    TextView stat;
    TemptedDatabaseHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.delete, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = (EditText) view.findViewById(R.id.editText2);
        helper = new TemptedDatabaseHelper(getContext());
        delete = (Button) view.findViewById(R.id.button2);
        stat = (TextView) view.findViewById(R.id.stat);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteQuote();
            }
        });
    }

    void DeleteQuote() {
        Log.d("Hi Sammie!!!", "DeleteQuote has been called.");
        if (!id.getText().toString().matches("")) {
            int i = Integer.parseInt(id.getText().toString());
            if (helper.rmvVerse(i) != 0) {
                stat.setText("Status: Success");
            } else {
                stat.setText("Status: No quote found");
            }
        } else {
            id.setError("Field cannot be left empty.");
        }
        Log.d("Hi Sammie!!!", "DeleteQuote has finished executing.");
        id.setText("");
    }
}