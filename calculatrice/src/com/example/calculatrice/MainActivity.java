package com.example.calculatrice;

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

import com.example.calculatrice.R;
import com.example.calculatrice.MainActivity;



public class MainActivity extends Activity implements View.OnClickListener{
	
	private TextView rs = null;
	private EditText num=null, numm=null;
	private Button plus=null,moin=null,dv=null,foi=null;
	int r,rr,res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plus=(Button)findViewById(R.id.plus);
        moin=(Button)findViewById(R.id.moin);
        dv=(Button)findViewById(R.id.div);
        foi=(Button)findViewById(R.id.mult);
        plus.setOnClickListener(this);
        moin.setOnClickListener(this);
        dv.setOnClickListener(this);
        
        num=(EditText)findViewById(R.id.ed1);
        numm=(EditText)findViewById(R.id.ed2);
        rs=(TextView)findViewById(R.id.res);
        
        foi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try
		    	{
		    	String l = num.getText().toString();
		    	String ll = numm.getText().toString();
		    	r = Integer.parseInt(l);
		    	rr = Integer.parseInt(ll);
		    		 rs.setText("resultat de multiplication :"+(r*rr));
		    	}
		    	catch(Exception e){    		 rs.setText("erreur de donnees --!!");    }
				
			}
		});
        
        

    }
    
    @Override
	public void onClick(View v) 
    {
    	try
    	{
    	String l = num.getText().toString();
    	String ll = numm.getText().toString();
    	r = Integer.parseInt(l);
    	rr = Integer.parseInt(ll);
    	
    	 if(v.getId()==R.id.plus)
    	 {
    		 rs.setText("resultat d'addition :"+(r+rr));
    	 }
    	 if(v.getId()==R.id.moin)
    	 {
    		 rs.setText("resultat de soustraction :"+(r-rr));
    	 }
    	 if(v.getId()==R.id.div)
    	 {
    		 rs.setText("resultat de division :"+(r/rr));
    	 }
    	/* if(v.getId()==R.id.mult)
    	 {
    		 rs.setText("resultat de multiplication :"+(r*rr));
    	 }*/
    	}
    	catch(Exception e){    		 rs.setText("erreur de donnees !!");    }
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
