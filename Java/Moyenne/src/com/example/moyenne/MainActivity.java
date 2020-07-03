package com.example.moyenne;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import android.R.layout;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moyenne.R;
import com.example.moyenne.MainActivity;



public class MainActivity extends Activity implements View.OnClickListener {

	private EditText e1=null, e2=null,e3 = null;
	private Button b=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.ch1);
        e2=(EditText)findViewById(R.id.ch2);
        e3=(EditText)findViewById(R.id.ch3);
        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(this);
 
        
    }
    @Override
	public void onClick(View v) {
		if(v.getId()==R.id.button)
		{
			try
			{ 
				String e=e1.getText().toString();
				String et=e2.getText().toString();
				String etr=e3.getText().toString();
				float eo=Float.valueOf(e); float eoo=Float.valueOf(et); float eot=Float.valueOf(etr);
				float s = (eo+eoo+eot)/3;
				if(s>=10)
				{
			        setContentView(R.layout.reussite);
			        TextView es = (TextView)findViewById(R.id.reuss);
			        es.setText("moyenne : "+String.format("%.2f", s));
				}
				else if(s<10 && s>=0)
				{
					setContentView(R.layout.recale);
			        TextView es = (TextView)findViewById(R.id.recal);
			        es.setText("moyenne : "+String.format("%.2f", s));
				}
				else
					Toast.makeText(MainActivity.this, "données negatifs", Toast.LENGTH_SHORT).show();
					
					
			}
			catch(Exception e) {Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show(); }
		}
		
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
