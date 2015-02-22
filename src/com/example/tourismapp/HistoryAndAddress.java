package com.example.tourismapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class HistoryAndAddress extends Activity {
	TextView history, address;
	String historyOfMonument;
	String addressOfMonument;
	Dialog d;
	AutoCompleteTextView lookUp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// code that displays the content in full screen mode
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.historyandaddress);
		history = (TextView) findViewById(R.id.history);
		address = (TextView) findViewById(R.id.address);
		historyOfMonument = getIntent().getExtras().getString("history");
		addressOfMonument = getIntent().getExtras().getString("address");
		TabHost th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("History");
		th.addTab(specs);
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Address");
		th.addTab(specs);

		history.setText(historyOfMonument);
		address.setText(addressOfMonument);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_for_others, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.gotomain:
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
			break;
		case R.id.exit:
			AlertDialog.Builder alert = new AlertDialog.Builder(
					HistoryAndAddress.this);
			alert.setMessage("Are you sure");
			alert.setCancelable(true); // this makes the dialog box to close
										// when the back button is pressed //
			alert.setTitle("Exit");
			alert.create();
			alert.setPositiveButton("ok", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					HistoryAndAddress.this.finish();
				}
			});
			alert.setNegativeButton("cancel", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					arg0.dismiss(); // closes the dialog box

				}
			});
			alert.show();
			break;
		case R.id.lookupdirectly:
			String[] places = { "Red-Fort", "Jantar-Mantar (Connaught Place)",
					"Lal Bangla", "Lal Gumbad", "Ashoka's Pillar",
					"Delhi Gate(India Gate)", "Humayun's Tomb",
					"Akshardham-Temple", "LakshmiNarayan Temple",
					"Cathedral Church Of Redemption", "Gurudwara Bangla Sahib",
					"ISKCON Temple", "Jama Masjid", "Lotus Temple",
					"St. James Church", "Kalka Ji Mandir", "Nizamuddin Dargah",
					"Funland", "HangOut", "Adventure-Island",
					"Indian Mountaineering Foundation", "SurajKund Lake",
					"FerozShahKotla", "Indira Gandhi Sports Complex",
					"Major DhyanChand Stadium", "Talkatora Indoor Stadium",
					"JawaharLal Nehru Stadium", "National Museum",
					"National Rail Museum", "National Zoological Park" };
			d = new Dialog(HistoryAndAddress.this);
			d.setContentView(R.layout.newlayout);
			d.setTitle("Enter The Location");
			d.setCancelable(true);
			d.show();
			Button b1 = (Button) d.findViewById(R.id.dbutton1);
			Button b2 = (Button) d.findViewById(R.id.dbutton2);

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.select_dialog_item, places);
			lookUp = (AutoCompleteTextView) d
					.findViewById(R.id.autoCompleteTextView1);
			lookUp.setThreshold(1);
			lookUp.setTextColor(Color.BLUE);
			lookUp.setAdapter(adapter);
			b1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					String str = lookUp.getText().toString();
					Toast.makeText(HistoryAndAddress.this, str,
							Toast.LENGTH_SHORT).show();
					d.dismiss();
				}
			});
			b2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					d.dismiss();
				}
			});
			break;
		}
		return super.onOptionsItemSelected(item);

	}

}
