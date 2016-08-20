package com.netsahiwot.netsa_hiwot;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class ActivityGetHelp extends Activity implements AdapterView.OnItemSelectedListener {
    Button sendEmail;
    EditText situation, first_name, last_name;
    RadioButton male, female;
    private String m;
    private Spinner spinner;

    public String age;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        age=parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_help);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setTitle("Get Help");
        bar.setDisplayHomeAsUpEnabled(true);
        sendEmail = (Button) findViewById(R.id.button);
        situation = (EditText) findViewById(R.id.situation);
        first_name = (EditText) findViewById(R.id.firstname);
        last_name = (EditText) findViewById(R.id.secondname);

        male = (RadioButton) findViewById(R.id.M);
        female = (RadioButton) findViewById(R.id.F);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Age,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);




        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = "Full Name:" + first_name.getText().toString()+" " + last_name.getText().toString() + "\n" +
                        "Sex:" + m + "\n" + "Age:" +age+ "\n" + "My Situation is explained below:-"+"\n"
                        + situation.getText().toString();

                sendEmail(message);

            }


        });
    }
    public void onRadioButtonClicked(View v) {


        boolean checked = ((RadioButton) v).isChecked();


        switch (v.getId()) {
            case R.id.M:
                if (checked)
                    m= male.getText().toString();

                break;
            case R.id.F:
                if (checked)
                    m = female.getText().toString();

                break;
        }
    }



    protected void sendEmail(String message) {
        String[] array = new String[]{"azewdu87@gmail.com"};
        String subject = ("Hi Netsa Hiwot, can u help me");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, array);//reciver
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);// message subject
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);//
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                overridePendingTransition(R.anim.open_main, R.anim.close_next);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // when back button pressed close database and back to previous page
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.open_main, R.anim.close_next);
    }


    @Override
    public void onConfigurationChanged(final Configuration newConfig)
    {
        // Ignore orientation change to keep activity from restarting
        super.onConfigurationChanged(newConfig);
    }
}