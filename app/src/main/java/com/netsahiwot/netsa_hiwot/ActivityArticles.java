package com.netsahiwot.netsa_hiwot;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityArticles extends Activity {
    TextView text,ti;
    ImageView iv;
    Bundle bundle;
    String img_loc, txt,title;
    Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ActionBar bar = getActionBar();
        //bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Articles");

        Btn=(Button)findViewById(R.id.buttonn);

        bundle = getIntent().getExtras();
        text = (TextView) findViewById(R.id.AmansText);
        iv = (ImageView) findViewById(R.id.AmansImage);
        ti=(TextView)findViewById(R.id.title);

        txt = bundle.getString("Txt");
        img_loc = bundle.getString("Image");
        title= bundle.getString("Title");

        int id = getResources().getIdentifier(txt, "string", getPackageName());
        text.setText(id);
        int id2 = getResources().getIdentifier(img_loc, "drawable", getPackageName());
        iv.setImageResource(id2);
        ti.setText(title);


        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityArticles.this,MainActivity.class);
                intent.putExtra("IsBack",true);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
