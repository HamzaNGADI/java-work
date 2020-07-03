package example.com.mydroid;

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
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import example.com.mydroid.R;
import example.com.mydroid.MainActivity;

public class MainActivity extends Activity implements View.OnClickListener{
  
	private int x=0,xi=0;
    private Button b = null;
    private TextView t = null;
    private CheckBox chk = null;
    private RadioGroup rdg= null;
    private RatingBar ratin = null;
    private TabHost mytab = null;
    private Handler hd = new Handler();
    private boolean boo;
    Thread tho = new Thread();
    private String macle="macle", val="";
          
               
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mytab = (TabHost)findViewById(R.id.tabhost);
        mytab.setup();
        
        TabSpec spec = mytab.newTabSpec("onglet 1");
        spec.setIndicator("onglet1",getResources().getDrawable(R.drawable.ic_launcher));
        spec.setContent(R.id.onglet1);
        mytab.addTab(spec);
        
        mytab.addTab(mytab.newTabSpec("onglet 2").setIndicator("onglet2").setContent(R.id.onglet2));
        mytab.addTab(mytab.newTabSpec("onglet 3").setIndicator("onglet3").setContent(R.id.onglet3));
        
        mytab.setOnTabChangedListener(new OnTabChangeListener()
        {
			@Override
			public void onTabChanged(String tabId) {
				Toast.makeText(MainActivity.this, "tab changed ", Toast.LENGTH_LONG).show();	
			}	
        });
        
       ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try
		    	{
		          //((Button)findViewById(R.id.button)).setText(val);
					Intent i = new Intent(MainActivity.this,ActActivity.class);
					startActivity(i);
		    	}
		    	catch(Exception e){     }
				
			}
		});
        
        
        
        rth();
        boo = false;
        if(savedInstanceState != null && savedInstanceState.containsKey("cl"))
        {
        	String vl = savedInstanceState.getString("cl");
    		Toast.makeText(MainActivity.this, "restored is :  "+vl, Toast.LENGTH_LONG).show();	
        }
        //else Toast.makeText(MainActivity.this, "noop", Toast.LENGTH_LONG).show();
    }
       
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
    	savedInstanceState.putString("cl", "imcleit");
    	val = savedInstanceState.getString("cl");
		Toast.makeText(MainActivity.this, "saved !! ", Toast.LENGTH_LONG).show();	
    	super.onSaveInstanceState(savedInstanceState);
    }
     
    @Override
    public void onDestroy()
    {
    	super.onDestroy();
    }
    
    public void onClick(View v)
    {
       /* if(v.getId()==R.id.button)
        {
            //x++; 
           // b.setText("button is : "+x); 
            if(xi==0){boo=true; }
            xi++;
            if(xi==2){boo=false; x=0; }
           Thread tho= new Thread(new Runnable() {
                @Override
                public void run() {
                    while(boo)
                    {
                        try {
                              // t.setText("b " + x);
                            Thread.sleep(2000); x++;
                        }
                        catch (Exception e){}
                        hd.post(new Runnable() {
                            @Override
                            public void run() {
                                if(xi != 2)b.setText("var is : "+x);
                                else{ b.setText("--STOP--"); xi=0;x=0;}
                            }
                        });
                    }
                }
            }
            );
            tho.start();

        }
*/
    }
    public void rth()
    {

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
    public boolean onTouch(View v, MotionEvent ev)
    {
        return true;
    }
}
