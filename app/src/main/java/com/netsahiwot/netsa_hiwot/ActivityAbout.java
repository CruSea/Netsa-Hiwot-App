package com.netsahiwot.netsa_hiwot;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivityAbout extends Activity {
	/** Called when the activity is first created. */
	ImageButton facebook_button,web_button;
	TextView facebook_link,web_link;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setTitle("About US");
        bar.setDisplayHomeAsUpEnabled(true);

		facebook_button=(ImageButton)findViewById(R.id.facebookbutton);
		web_button=(ImageButton)findViewById(R.id.websitebutton);
		facebook_link=(TextView)findViewById(R.id.facebooklink);
		web_link=(TextView)findViewById(R.id.websitelink);



		web_button.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
			Web_link();}});



		facebook_button.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
          Facebook_link();}});



		facebook_link.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v){
				Facebook_link();}});




		web_link.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v){
				Web_link();}});

	}
	public void Facebook_link (){
		Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/netsahiwot"));
		startActivity(facebook);

	}
	public void Web_link(){
		Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.netsahiwot.com/"));
		startActivity(website);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.open_main, R.anim.close_next);
	}
}