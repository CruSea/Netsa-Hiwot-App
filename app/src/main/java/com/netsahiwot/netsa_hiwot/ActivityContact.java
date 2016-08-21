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
import android.widget.Button;
import android.widget.EditText;

public class ActivityContact extends Activity {
    EditText Name,Email,Message;
    Button Send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setTitle("Contact Us");
        bar.setDisplayHomeAsUpEnabled(true);

        Name=(EditText)findViewById(R.id.FullName);
        Email=(EditText)findViewById(R.id.CEail);
        Message=(EditText)findViewById(R.id.textbody);
        Send=(Button)findViewById(R.id.send);
    Send.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String message= "Full Name:-" + Name.getText().toString() +"\n"+ "Message:-"  + Message .getText().toString();
            sendEmail(message);
        }
    });

    }
    protected void sendEmail(String message) {
        String[] array = new String[]{"azewdu87@gmail.com"};
        String subject = ("Hi Netsa Hiwot ");
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