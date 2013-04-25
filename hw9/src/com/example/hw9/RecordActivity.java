package com.example.hw9;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.facebook.widget.WebDialog;

public class RecordActivity extends Activity {
	private static final String FEED = "feed";
	public static final String app_id = "445667832184675";
	private static final Logger LOGGER = Logger.getLogger(RecordActivity.class.getName());
	private static final String URL = "http://cs-server.usc.edu:27410/servlet/musicsearch";
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TableLayout tableLayout = new TableLayout(this);
//		tableLayout.setShrinkAllColumns(true);
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		@SuppressWarnings("deprecation")
		int width = display.getWidth();  
		tableLayout.setStretchAllColumns(true);
		ScrollView sv = new ScrollView(this);
		sv.setVerticalScrollBarEnabled(true);
		sv.addView(tableLayout);
		setContentView(sv);
		
		String title = getIntent().getStringExtra("title");
		String type = getIntent().getStringExtra("type");
		AsyncTask<String, Void, JSONObject> task = new RetrieveTask().execute(URL,title,type);
		
		try{
			JSONObject json = task.get();
			if(json==null){
				TableRow trow = new TableRow(this);
				TextView ev = new TextView(this);
				ev.setText("No discography found");
				trow.addView(ev);
				tableLayout.addView(trow);
				return;
			}
			if(type.equalsIgnoreCase("artists")){
				if(json.getJSONObject("results").get("result") instanceof JSONArray ){
					JSONArray jarray = (JSONArray) json.getJSONObject("results").get("result");
					LOGGER.info("data count = " + jarray.length());
					for(int i=0;i<jarray.length();i++){
						final TableRow tableRow = new TableRow(this);
						tableRow.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								LOGGER.info("table row clicked");
								Bundle b = new Bundle();
								TextView view = (TextView) tableRow.getChildAt(1);
								String name = (String)view.getText();
								view = (TextView)tableRow.getChildAt(2);
								String genre = (String)view.getText();
								view = (TextView)tableRow.getChildAt(3);
								String year = (String)view.getText();
								view = (TextView)tableRow.getChildAt(4);
								String details = (String)view.getText();
								view = (TextView)tableRow.getChildAt(5);
								String cover=(String) view.getText();
								
								String caption = "I like " + name + " who is active since " + year;
								
								
								b.putString("name", name);
								b.putString("link", details);
								b.putString("caption", caption);
								b.putString("description",genre);
								b.putString("properties", "{\"Look at the Reviews\":{ \"text\": \"here\", \"href\":\""+details + "\"}}");
								if(!cover.equalsIgnoreCase("n/a"))
									b.putString("picture", cover);
								
								new FBDialog(b, RecordActivity.this).show();
							}
						});
						JSONObject jtemp = jarray.getJSONObject(i);
						LOGGER.info("record " + jtemp.toString());
						ImageView img = new ImageView(this);
						TextView imglink = new TextView(this);
						imglink.setWidth(0);
						imglink.setHeight(0);
						if(!jtemp.getString("cover").equals("N/A")){
							img.setImageBitmap(getImageBitmap(jtemp.getString("cover"),""+width/5));
							imglink.setText(jtemp.getString("cover"));
						}
						else{
							img.setImageResource(R.drawable.noartist);
							imglink.setText("n/a");
						}
						TextView name = new TextView(this);
						name.setText("Name: "+jtemp.getString("name"));
						name.setWidth(width/4);
						TextView genre = new TextView(this);
						genre.setText("Genre: " +jtemp.getString("genre"));
						genre.setWidth(width/4);
						TextView year = new TextView(this);
						year.setText("Year: " +jtemp.getString("year"));
						year.setWidth(width/4);
						TextView details = new TextView(this);
						details.setText(jtemp.getString("details"));
						details.setWidth(0);
						details.setHeight(0);
						
						tableRow.addView(img);
						tableRow.addView(name);
						tableRow.addView(genre);
						tableRow.addView(year);
						tableRow.addView(details);
						tableRow.addView(imglink);
						tableLayout.addView(tableRow);
					}
				}else{
					final TableRow tableRow = new TableRow(this);
					tableRow.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							LOGGER.info("table row clicked");
							Bundle b = new Bundle();
							TextView view = (TextView) tableRow.getChildAt(1);
							String name = (String)view.getText();
							view = (TextView)tableRow.getChildAt(2);
							String genre = (String)view.getText();
							view = (TextView)tableRow.getChildAt(3);
							String year = (String)view.getText();
							view = (TextView)tableRow.getChildAt(4);
							String details = (String)view.getText();
							view = (TextView)tableRow.getChildAt(5);
							String cover=(String) view.getText();
							
							String caption = "I like " + name + " who is active since " + year;
							
							
							b.putString("name", name);
							b.putString("link", details);
							b.putString("caption", caption);
							b.putString("description",genre);
							b.putString("properties", "{\"Look at the Reviews\":{ \"text\": \"here\", \"href\":\""+details + "\"}}");
							if(!cover.equalsIgnoreCase("n/a"))
								b.putString("picture", cover);
							
							new FBDialog(b, RecordActivity.this).show();
						}
					});

					JSONObject jtemp = json.getJSONObject("results").getJSONObject("result");
					ImageView img = new ImageView(this);
					TextView imglink = new TextView(this);
					imglink.setWidth(0);
					imglink.setHeight(0);
					if(!jtemp.getString("cover").equals("N/A")){
						img.setImageBitmap(getImageBitmap(jtemp.getString("cover"),""+width/5));
						imglink.setText(jtemp.getString("cover"));
					}
					else{
						img.setImageResource(R.drawable.noartist);
						imglink.setText("n/a");
					}
					TextView name = new TextView(this);
					name.setText("Name: "+jtemp.getString("name"));
					name.setWidth(width/4);
					TextView genre = new TextView(this);
					genre.setText("Genre: "+jtemp.getString("genre"));
					genre.setWidth(width/4);
					TextView year = new TextView(this);
					year.setText("Year: "+jtemp.getString("year"));
					year.setWidth(width/4);
					TextView details = new TextView(this);
					details.setText(jtemp.getString("details"));
					details.setWidth(0);
					details.setHeight(0);
					
					tableRow.addView(img);
					tableRow.addView(name);
					tableRow.addView(genre);
					tableRow.addView(year);
					tableRow.addView(details);
					tableRow.addView(imglink);
					tableLayout.addView(tableRow);
				}

			}else if(type.equalsIgnoreCase("albums")){
				if(json.getJSONObject("results").get("result") instanceof JSONArray ){
					JSONArray jarray = (JSONArray) json.getJSONObject("results").get("result");
					for(int i=0;i<jarray.length();i++){
						JSONObject jtemp = jarray.getJSONObject(i);
						final TableRow tableRow = new TableRow(this);
						tableRow.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								LOGGER.info("table row clicked");
								Bundle b = new Bundle();
								TextView view = (TextView) tableRow.getChildAt(1);
								String name = (String)view.getText();
								view = (TextView)tableRow.getChildAt(2);
								String artist = (String)view.getText();
								view = (TextView)tableRow.getChildAt(3);
								String genre = (String)view.getText();
								view = (TextView)tableRow.getChildAt(4);
								String year = (String)view.getText();
								view = (TextView)tableRow.getChildAt(5);
								String details = (String)view.getText();
								view = (TextView)tableRow.getChildAt(6);
								String cover=(String) view.getText();
								
								String caption = "I like " + name + " released in" + year;
								
								
								b.putString("name", name);
								b.putString("link", details);
								b.putString("caption", caption);
								b.putString("description",artist +" & " + genre);
								b.putString("properties", "{\"Look at the Reviews\":{ \"text\": \"here\", \"href\":\""+details + "\"}}");
								if(!cover.equalsIgnoreCase("n/a"))
									b.putString("picture", cover);
								
								new FBDialog(b, RecordActivity.this).show();
							}
						});
						ImageView img = new ImageView(this);
						TextView imglink = new TextView(this);
						imglink.setWidth(0);
						imglink.setHeight(0);
						if(!jtemp.getString("cover").equals("N/A")){
							img.setImageBitmap(getImageBitmap(jtemp.getString("cover"),""+width/5));
							imglink.setText(jtemp.getString("cover"));
						}
						else{
							img.setImageResource(R.drawable.noalbum);
							imglink.setText("n/a");
						}
						TextView name = new TextView(this);
						name.setText("Title: " + jtemp.getString("name"));
						name.setWidth(width/5);
						TextView artist = new TextView(this);
						artist.setText("Artist: "+jtemp.getString("artist"));
						artist.setWidth(width/5);
						TextView genre = new TextView(this);
						genre.setText("Genre: "+jtemp.getString("genre"));
						genre.setWidth(width/5);
						TextView year = new TextView(this);
						year.setText("Year: "+jtemp.getString("year"));
						year.setWidth(width/5);
						TextView details = new TextView(this);
						details.setText(jtemp.getString("details"));
						details.setWidth(0);
						details.setHeight(0);
						
						tableRow.addView(img);
						tableRow.addView(name);
						tableRow.addView(artist);
						tableRow.addView(genre);
						tableRow.addView(year);
						tableRow.addView(details);
						tableRow.addView(imglink);
						tableLayout.addView(tableRow);
					}
				}else{
					JSONObject jtemp = json.getJSONObject("results").getJSONObject("result");
					final TableRow tableRow = new TableRow(this);
					tableRow.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							LOGGER.info("table row clicked");
							Bundle b = new Bundle();
							TextView view = (TextView) tableRow.getChildAt(1);
							String name = (String)view.getText();
							view = (TextView)tableRow.getChildAt(2);
							String artist = (String)view.getText();
							view = (TextView)tableRow.getChildAt(3);
							String genre = (String)view.getText();
							view = (TextView)tableRow.getChildAt(4);
							String year = (String)view.getText();
							view = (TextView)tableRow.getChildAt(5);
							String details = (String)view.getText();
							view = (TextView)tableRow.getChildAt(6);
							String cover=(String) view.getText();
							
							String caption = "I like " + name + " released in" + year;
							
							
							b.putString("name", name);
							b.putString("link", details);
							b.putString("caption", caption);
							b.putString("description",artist +" & " + genre);
							b.putString("properties", "{\"Look at the Reviews\":{ \"text\": \"here\", \"href\":\""+details + "\"}}");
							if(!cover.equalsIgnoreCase("n/a"))
								b.putString("picture", cover);
							
							new FBDialog(b, RecordActivity.this).show();
						}
					});
					ImageView img = new ImageView(this);
					TextView imglink = new TextView(this);
					imglink.setWidth(0);
					imglink.setHeight(0);
					if(!jtemp.getString("cover").equals("N/A")){
						img.setImageBitmap(getImageBitmap(jtemp.getString("cover"),""+width/5));
						imglink.setText(jtemp.getString("cover"));
					}
					else{
						img.setImageResource(R.drawable.noalbum);
						imglink.setText("n/a");
					}
					TextView name = new TextView(this);
					name.setText("Title: "+jtemp.getString("name"));
					name.setWidth(width/5);
					TextView artist = new TextView(this);
					artist.setText("Artist: " +jtemp.getString("artist"));
					artist.setWidth(width/5);
					TextView genre = new TextView(this);
					genre.setText("Genre: " +jtemp.getString("genre"));
					genre.setWidth(width/5);
					TextView year = new TextView(this);
					year.setText("Year: " +jtemp.getString("year"));
					year.setWidth(width/5);
					TextView details = new TextView(this);
					details.setText(jtemp.getString("details"));
					details.setWidth(0);
					details.setHeight(0);
					
					tableRow.addView(img);
					tableRow.addView(name);
					tableRow.addView(artist);
					tableRow.addView(genre);
					tableRow.addView(year);
					tableRow.addView(details);
					tableRow.addView(imglink);
					tableLayout.addView(tableRow);
				}

			}else{ 
				
				/**
				 * THE DATA BELOW IS OF TYPE SONG
				 */
				
				
				if(json.getJSONObject("results").get("result") instanceof JSONArray ){
					JSONArray jarray = (JSONArray) json.getJSONObject("results").get("result");
					for(int i=0;i<jarray.length();i++){
						final TableRow tableRow = new TableRow(this);
						tableRow.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								LOGGER.info("table row clicked");
								Bundle b = new Bundle();
								TextView view = (TextView) tableRow.getChildAt(1);
								String name = (String)view.getText();
								view = (TextView)tableRow.getChildAt(2);
								String performer = (String)view.getText();
								view = (TextView)tableRow.getChildAt(3);
								String composer = (String)view.getText();
								view = (TextView)tableRow.getChildAt(4);
								String sample = (String)view.getText();
								view = (TextView)tableRow.getChildAt(5);
								String details = (String)view.getText();
								
								String caption = "I like " + name + " composed by " + composer;
								
								
								b.putString("name", name);
								b.putString("link", details);
								b.putString("caption", caption);
								b.putString("description", performer);
								b.putString("properties", "{\"Look at the Reviews\":{ \"text\": \"here\", \"href\":\""+details + "\"}}");
								if(!sample.equalsIgnoreCase("n/a"))
									b.putString("sample", sample);
								
								new FBDialog(b, RecordActivity.this).show();
							}
						});
						JSONObject jtemp = jarray.getJSONObject(i);
						ImageView img = new ImageView(this);
						img.setImageResource(R.drawable.sample);
						TextView name = new TextView(this);
						name.setText("Title: " +jtemp.getString("name"));
						name.setWidth(width/4);
						TextView performer = new TextView(this);
						performer.setText("Performer: " +jtemp.getString("performer"));
						performer.setWidth(width/4);
						TextView composers = new TextView(this);
						composers.setText("Composer: " +jtemp.getString("composers"));
						composers.setWidth(width/4);
						TextView sample = new TextView(this);
						sample.setText(jtemp.getString("cover"));
						sample.setWidth(0);
						sample.setHeight(0);
						TextView details = new TextView(this);
						details.setText(jtemp.getString("details"));
						details.setWidth(0);
						details.setHeight(0);
						
						tableRow.addView(img);
						tableRow.addView(name);
						tableRow.addView(performer);
						tableRow.addView(composers);
						tableRow.addView(sample);
						tableRow.addView(details);
						tableLayout.addView(tableRow);
					}
				}else{
					final TableRow tableRow = new TableRow(this);
					tableRow.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							LOGGER.info("table row clicked");
							Bundle b = new Bundle();
							TextView view = (TextView) tableRow.getChildAt(1);
							String name = (String)view.getText();
							view = (TextView)tableRow.getChildAt(2);
							String performer = (String)view.getText();
							view = (TextView)tableRow.getChildAt(3);
							String composer = (String)view.getText();
							view = (TextView)tableRow.getChildAt(4);
							String sample = (String)view.getText();
							view = (TextView)tableRow.getChildAt(5);
							String details = (String)view.getText();
							
							String caption = "I like " + name + " composed by " + composer;
							
							
							b.putString("name", name);
							b.putString("link", details);
							b.putString("caption", caption);
							b.putString("description", performer);
							b.putString("properties", "{\"Look at the Reviews\":{ \"text\": \"here\", \"href\":\""+details + "\"}}");
							if(!sample.equalsIgnoreCase("n/a"))
								b.putString("sample", sample);
							new FBDialog(b, RecordActivity.this).show();
						}
					});
					JSONObject jtemp = json.getJSONObject("results").getJSONObject("result");
					ImageView img = new ImageView(this);
					img.setImageResource(R.drawable.sample);
					TextView name = new TextView(this);
					name.setText("Title:" + jtemp.getString("name"));
					name.setWidth(width/4);
					TextView performer = new TextView(this);
					performer.setText("Performer: "+jtemp.getString("performer"));
					performer.setWidth(width/4);
					TextView composers = new TextView(this);
					composers.setText("Composer: "+jtemp.getString("composers"));
					composers.setWidth(width/4);
					TextView sample = new TextView(this);
					sample.setText(jtemp.getString("cover"));
					sample.setWidth(0);
					sample.setHeight(0);
					TextView details = new TextView(this);
					details.setText(jtemp.getString("details"));
					details.setWidth(0);
					details.setHeight(0);
					
					tableRow.addView(img);
					tableRow.addView(name);
					tableRow.addView(performer);
					tableRow.addView(composers);
					tableRow.addView(sample);
					tableRow.addView(details);
					tableLayout.addView(tableRow);
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Bitmap getImageBitmap(String url, String imagewidth) {
		Bitmap bm = null;
		try {
			bm = new RetrieveBitmap().execute(url,imagewidth).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return bm;
    } 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record, menu);
		return true;
	}
	
	class FBDialog extends Dialog implements OnClickListener{

		Bundle bundle;
		public FBDialog(Context context) {
			super(context);
		}
		
		public FBDialog(Bundle bundle, Context context){
				super(context);
				this.bundle = bundle;
		}
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.posttofacebook);
			setTitle("Facebook");
			Button publish = (Button) findViewById(R.id.publishButton);
			publish.setOnClickListener(this);
			Button play = (Button)findViewById(R.id.playSampleButton);
			if(bundle.containsKey("sample")){
				play.setVisibility(View.VISIBLE);
				play.setOnClickListener(this);
			}
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.publishButton:
				new WebDialog.Builder(RecordActivity.this, app_id, FEED, bundle).build().show();

				break;
			case R.id.playSampleButton:
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri
				                .parse(bundle.getString("sample")),
				                "audio/mpeg3");
				startActivity(intent);
				break;
			}
			hide();
		}
		
	}
	
}

class RetrieveTask extends AsyncTask<String, Void, JSONObject>{

	@Override
	protected JSONObject doInBackground(String... params) {

		java.net.URL url;
		
		try {
			String urlStr = params[0]+"?title="+params[1]+"&type="+params[2];
			url = new java.net.URL(urlStr.replace(' ', '+'));
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder builder = new StringBuilder();
			String temp = "";
			while((temp=br.readLine())!=null)
				builder.append(temp);
			
			return new JSONObject(builder.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

class RetrieveBitmap extends AsyncTask<String,Void,Bitmap>{
Logger LOGGER = Logger.getLogger(RetrieveBitmap.class.getName());
	@Override
	protected Bitmap doInBackground(String... params) {
		  
		Bitmap bm = null;
        try {
        	LOGGER.info("image url " + params[0]);
            java.net.URL aURL = new java.net.URL(params[0]);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            LOGGER.info("available image bytes " + bis.available());
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
       } catch (IOException e) {
    	   e.printStackTrace();
       }
        bm = Bitmap.createScaledBitmap(bm, Integer.parseInt(params[1]), 120, false);
		return bm;
	}
}

