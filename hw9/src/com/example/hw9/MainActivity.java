package com.example.hw9;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnClickListener, OnItemSelectedListener {

	String type,title;
	EditText titletext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		titletext = (EditText)findViewById(R.id.editText1);

		View searchButton = findViewById(R.id.button1);
		searchButton.setOnClickListener(this);

		Spinner type = (Spinner)findViewById(R.id.spinner1);
		type.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		Intent i = new Intent(this,RecordActivity.class);
		title = titletext.getText().toString();
		if(title.trim().matches("^[a-zA-Z0-9 ]*")){
			i.putExtra("title", title.trim());
			i.putExtra("type", type.toLowerCase());
			startActivity(i);
		}else{
			new AlertDialog.Builder(this).setTitle("Error").setItems(R.array.text, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int i) {
					arg0.cancel();
				}
			}).show();
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		type = (String) arg0.getItemAtPosition(arg2);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
