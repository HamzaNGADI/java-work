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
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
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
import com.example.colorfinder.GameActivity;
//import com.google.android.maps.*;


public class GameActivity extends Activity implements View.OnClickListener{

	//Thread th;  
	private TextView t = null, tscrs = null;
	private Button b = null; 
	ImageView im = null;
	private LinearLayout l = null, ll=null; 
	private int ii=0,iii=0,scr=0,bestscr=0,thtime=1900,pbar=0; 
	private Random rand = new Random();
	private boolean bl=true,bb=false,whilebo=true,tt=false,go=false,rest=true;
	Handler hdlr = new Handler(), gmhdlr = new Handler();
	FileInputStream in = null;
	FileOutputStream ou = null;
	dbscr dscr;
	private boolean outact = false;
	private int ilu = 0;
	private boolean pause = false, gmrn=false;
     int ik=0,kh=0,lock=0;
	private int tabcol[] ={Color.WHITE,Color.rgb(222, 56, 112),Color.GRAY,Color.rgb(100,10,100),Color.YELLOW,Color.rgb(12,55,160),Color.MAGENTA,Color.rgb(7,98,114),Color.BLUE,Color.rgb(50,79,43),Color.RED,Color.rgb(61,0,121),Color.GREEN,Color.rgb(170,174,94),Color.rgb(101,27,41),Color.rgb(79,112,16),Color.CYAN};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
 
    	setContentView(R.layout.activity_game);
    	t = (TextView)findViewById(R.id.txt); 
    	tscrs = (TextView)findViewById(R.id.txtscrs); 
    	b = (Button)findViewById(R.id.butt); 
    	b.setOnClickListener(this); 
    	l = (LinearLayout)findViewById(R.id.lhaut);
    	ll = (LinearLayout)findViewById(R.id.lbas);
  
    	l.setOnClickListener(this);
    	ll.setOnClickListener(this);
  
      //th = new Thread(this);
      //th.start(); 
    	//b.setVisibility(View.VISIBLE);
    	t.setVisibility(View.GONE);
    	tscrs.setVisibility(View.GONE);
    	
    	b.setCompoundDrawablePadding(10);
    	b.setBackgroundColor(Color.TRANSPARENT);
    	b.setText("PLAY");
    	b.setCompoundDrawablesWithIntrinsicBounds(R.drawable.playit, 0, 0, 0);
    
		l.setVisibility(View.VISIBLE);
    	ll.setVisibility(View.VISIBLE);
    	b.setVisibility(View.VISIBLE);	 
    	
    	dscr = new dbscr(this);
    	 
    	 restore(); restorestate();          //     iii=(tabcol.length)-1; 
    	 l.setBackgroundColor(tabcol[iii]);
     	ll.setBackgroundColor(tabcol[rand.nextInt(tabcol.length)]);

	}
  
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//int icol = rand.nextInt(5)+1;
		if(v.getId() == R.id.lhaut)
		{
		 if(whilebo==true && go==true && gmrn==false){
			if(ilu == 0)
			{
				t.setText("");
				String stp = "Pause"; 
				SpannableString st = new SpannableString(stp);
				st.setSpan(new RelativeSizeSpan(2.5f), 0, stp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				st.setSpan(new StyleSpan(Typeface.BOLD), 0, stp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				t.append(st);
			     
			    pause=true;
		    	
			}
			ilu++;
			if(ilu == 2)
			{
				pause=false; 
				t.setText(/*"thrd : "+thtime+*/" score : "+scr);
				ilu = 0;
			}
		 }
		}
 
		if(v.getId() == R.id.lbas)
		{
			//l.setBackgroundColor(tabcol[ii]); 
			//ii++;
			  if(pause == false)
			  {
			if(go==true)  {
			if(ii==iii)
			{ 
				rungm();
			}
			else 
			{ 
				tt=true;
				 whilebo=false;
				b.setVisibility(View.VISIBLE);
				if(bestscr<scr) {bestscr = scr;save();   dscr.insertscr(bestscr);   dscr.verifyAll();}
				
				t.setText("");
				String stscr = "Score : "+scr; 
				String topscr = "Top Scors";
				SpannableString st = new SpannableString(stscr);
				SpannableString tst = new SpannableString(topscr);		
				st.setSpan(new RelativeSizeSpan(2f), 0, stscr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				st.setSpan(new StyleSpan(Typeface.BOLD), 0, stscr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				tst.setSpan(new RelativeSizeSpan(1.5f), 0, topscr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				t.append(st);	 t.append("\n"); t.append("Best score : "+bestscr);
				//t.setText(/*"thrd : "+thtime+*/st+"\nbest score : "+bestscr);
				//t.setText(/*"thrd : "+thtime+*/" score : "+scr+" | best score : "+bestscr);
				if(!(dscr.getscors().trim()).equals(""))
				{ 
				tscrs.setVisibility(View.VISIBLE);
				tscrs.setText("");
				tscrs.append(tst);	 tscrs.append("\n"); tscrs.append(dscr.getscors());
				}
               go=false;      	
               
               b.setCompoundDrawablePadding(10);
            	b.setCompoundDrawablesWithIntrinsicBounds(R.drawable.replayit, 0, 0, 0);
               b.setText("PLAY AGAIN");
			}
			   }
			         }   
		}
		if(v.getId()==R.id.butt)
		{
			go = true;
			tt=false;
			scr=0;
			t.setVisibility(View.VISIBLE);
			whilebo=true;
			runth();
			b.setVisibility(View.GONE);
			tscrs.setVisibility(View.GONE);
		}
		 //if(bb==false){ bb=true;runth();}
		 
	}
	     public void rungm()
	    {
	    	  ik=0; gmrn=true; kh = scr+10; lock=1;
				new Thread(new Runnable() {
				    public void run() {
				    	while(ik<10){
		        try{ 
		        	scr++;
				    Thread.sleep(80);
					ik++;
				    }catch(InterruptedException e)
				    {
		               e.printStackTrace();
				    } 
				    gmhdlr.post(new Runnable() {	     	
				     public void run() {
							if(kh==scr && lock==1)
							{
						    	iii++; if(iii>(tabcol.length-1)) iii=0;
								l.setBackgroundColor(tabcol[iii]); 
								savestate(); 				   	
						   	gmrn=false; lock=0; 
							}
							t.setText(/*"thrd : "+thtime+*/" score : "+scr);

				    }
				    });
				   }
				   	
				    }
				  }).start(); 
			    
	    } 
	  public void runth()
	  { 
			new Thread(new Runnable() {
			    public void run() {
			    	while(whilebo){
	
	        try{  
			    Thread.sleep(thtime);
				//ii++; 
				//if(ii>(tabcol.length-1)) ii=0;
				ii=rand.nextInt(tabcol.length);
				if(bl==true)thtime-=100;
				if(bl==false)thtime+=100;
				if(thtime>2000) bl=true;
				if(thtime<50) bl=false;
			    }catch(InterruptedException e)
			    {
	               e.printStackTrace();
			    }
			    hdlr.post(new Runnable() {
			    
			    @Override
			     	
			     public void run() {
			    if(tt==false)
			    {
			    	if(pause==false){ t.setText(/*"thrd : "+thtime+*/" score : "+scr);ll.setBackgroundColor(tabcol[ii]);  }
			    }
			    if(tt==true)
			    {
			    	//t.setText(/*"thrd : "+thtime+*/" score : "+scr+" | best score : "+bestscr);
			    	t.setText("");
					String stscr = "Score : "+scr; 
					SpannableString st = new SpannableString(stscr);
					st.setSpan(new RelativeSizeSpan(2f), 0, stscr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					st.setSpan(new StyleSpan(Typeface.BOLD), 0, stscr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					t.append(st);	 t.append("\n"); t.append("Best score : "+bestscr);
  			    
			    }
 
			    }
			    });
			    }
			  }    
			  }).start(); 
		
	  }
	  
	    
  
	  public void save()
	  {
		  SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", 0);
		  SharedPreferences.Editor editor = settings.edit();
		  editor.putInt("score", scr);
		  editor.apply();
	  }
	  public void restore()
	  {
		  SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", 0);
		  bestscr = settings.getInt("score", 0);

	  }
	  public void savestate()
	  {
		  SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", 0);
		  SharedPreferences.Editor editor = settings.edit();
		  editor.putInt("rnd", iii);
		  editor.apply();
	  }
	  public void restorestate()
	  {
		  SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", 0);
		  iii = settings.getInt("rnd", 0);

	  }
 
   
	  @Override
	  public void onBackPressed() 
	   {
		//	Toast.makeText(GameActivity.this, "back it !! ", Toast.LENGTH_LONG).show();	
		      if(outact)
		      {  
			      this.finishAffinity();
		      }
			if(outact==false) Toast.makeText(GameActivity.this, "Appuyer de nouveau pour quitter ColorFinder ", Toast.LENGTH_LONG).show();	
		      outact=true;
		      new Handler().postDelayed(new Runnable(){ 
				@Override
				public void run() {outact = false;}	  
		      }, 2000);
		}
	  @Override
		public void onDestroy() 
	    {
		    super.onDestroy();
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


 


class dbscr extends SQLiteOpenHelper
{
	private String reqtable = "create table score(" +
			"id integer  primary key autoincrement," +
			"scoreValue integer not null);";
			
	private static String BASENAME = "dbase.db";
	public dbscr(Context context)
	{
		super(context,BASENAME,null,1);
	
	}
	
	public void insertscr(int scr)
	{
		SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentval = new ContentValues();
	      contentval.put("scoreValue", scr);
	      db.insert("score", null, contentval);

	}
	public String getscors()
	{
		SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from score ORDER BY scoreValue desc", null );
	      String scrlignes="";
	      int i=1;
	      if(res.moveToFirst()){
	            do{
	               int uscr = res.getInt(1);
	            scrlignes+=i+". you : "+uscr+"\n";
	            i++;
	            }while(res.moveToNext());
	        }
	      return scrlignes;
	}
	public void verifyAll()
	{
		SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from score", null );
	      int r = res.getCount();
	      if(r>3)
	      {
	    	  int ro = r-3;
	      if(res.moveToFirst()){
	            do{
	            	db.execSQL("delete from score where id = "+Integer.parseInt(res.getString(0)));
	            	ro--;
	            }while(res.moveToNext() && ro !=0);
	        }
	      }
   
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(reqtable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS score");
	      onCreate(db);
	}
}