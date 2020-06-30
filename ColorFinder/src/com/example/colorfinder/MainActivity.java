package com.example.colorfinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.R.layout;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colorfinder.R;
import com.example.colorfinder.MainActivity;
//import com.google.android.maps.*;


public class MainActivity extends Activity implements View.OnClickListener{

	//Thread th;  

	private ImageView im = null;
	private int op,pbar=0; 
	private boolean outact=false;
	Handler hdlr = new Handler(),phdlr = new Handler();
	FileInputStream in = null;
	FileOutputStream ou = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.activity_main);

    
    	im =(ImageView)findViewById(R.id.img);
   
    	im.setOnClickListener(this);
    
    	im.setVisibility(View.GONE);
    	
    	restoreopening();  loading(); 
    	  
	}
 
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.img)
		{
			saveopening();
			Intent i = new Intent(MainActivity.this,GameActivity.class);
			startActivity(i);
	
		}
			
	}
	@Override
	public void onBackPressed() 
	{
//	   this.finish();
	   if(outact)
	      {
		      this.finish();
	      }
		if(outact==false) Toast.makeText(MainActivity.this, "Appuyer de nouveau pour quitter ColorFinder ", Toast.LENGTH_LONG).show();	
	      outact=true;
	      new Handler().postDelayed(new Runnable(){ 
			@Override
			public void run() {outact = false;}	  
	      }, 2000);
	}
	@Override
	public void onDestroy() {
	    super.onDestroy();
	}
	public void check()
	{
		 
		if(op==800)
    	{
    	 	
    		Intent i = new Intent(MainActivity.this,GameActivity.class);
			startActivity(i);
    	}
		//else     	im.setVisibility(View.VISIBLE);
		
	}
	       
	  public void loading()
	  {
			final ProgressDialog bar = ProgressDialog.show(MainActivity.this, "", "Loading...", true);
			new Thread(new Runnable() {
			    public void run() {
			    	while(pbar<100){
	        try{ 
			    Thread.sleep(200);
				pbar++;
			    }catch(InterruptedException e)
			    {
	               e.printStackTrace();
			    } 
			    phdlr.post(new Runnable() {	     	
			     public void run() {
			    	bar.setMessage("loading data & color's "+pbar+"%");
			    	if(op != 800 && pbar >= 80)  im.setVisibility(View.VISIBLE);
			    }
			    });
			   }
			   	check();   bar.dismiss();  }
			  }).start(); 
		 
	  }
	            
	  public void saveopening()
	  {
		  SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", 0);
		  SharedPreferences.Editor editor = settings.edit();
		  editor.putInt("openingGame", 800);
		  editor.apply();
	  }
	  public void restoreopening()
	  {
		  SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", 0);
		  op = settings.getInt("openingGame", 0);
	  }
 
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


