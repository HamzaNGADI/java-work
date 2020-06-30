package com.example.formulaire;

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
import android.content.ContentValues;
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

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.formulaire.R;
import com.example.formulaire.MainActivity;


public class MainActivity extends Activity implements View.OnClickListener{
	
    private RadioGroup rdg= null;
    private CheckBox chb=null,chbb=null,chbbi=null;
    private EditText us=null,pass=null,eml=null;
    private String sx = "masculin", degree1=null,degree2=null,degree3=null;
    private Button b =null;
    private TextView tx=null;
    Context cnx;
	String pw=null, emli=null, user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rdg = (RadioGroup)findViewById(R.id.rg);
        chb = (CheckBox)findViewById(R.id.chk1);
        chbb = (CheckBox)findViewById(R.id.chk2);
        chbbi = (CheckBox)findViewById(R.id.chk3);
        chb.setOnClickListener(this);        chbb.setOnClickListener(this);   chbbi.setOnClickListener(this);
       
        us = (EditText)findViewById(R.id.usr);
        pass = (EditText)findViewById(R.id.passwd);
        eml = (EditText)findViewById(R.id.em);
         
        b = (Button)findViewById(R.id.btn);
        b.setOnClickListener(this);

        
        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
			@Override
			public void onCheckedChanged(RadioGroup grp, int checkedid) 
			{
		    sx = (((RadioButton)findViewById(checkedid)).getText()).toString();
			}
        });
    }
    @Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn)
		{
		  try{
			 user = us.getText().toString();
			 pw = pass.getText().toString();
		     emli = eml.getText().toString();
			if(!user.trim().equals("") && !pw.trim().equals("") && !emli.trim().equals("")&& emli.indexOf("@", 2)!=-1)
			{	
			if(chb.isChecked()==true) degree1=" "+chb.getText().toString();
			else degree1="";
			if(chbb.isChecked()==true) degree2=" "+chbb.getText().toString();
			else degree2="";
			if(chbbi.isChecked()==true) degree3=" "+chbbi.getText().toString();
			else degree3="";
			//((TextView)findViewById(R.id.res)).setText("user : "+user+"\n password : "+pw+"\nemail : "+emli+"\nsexe : "+sx+"\ndiplomes : "+degree1+degree2+degree3);
		    if(degree1.equals("") && degree2.equals("") && degree3.equals("")) degree1=" aucun diplome";
    		
		    	
		    Toast.makeText(MainActivity.this, "Toast :\n"+"user : "+user+"\n password : "+pw+"\nemail : "+emli+"\nsexe : "+sx+"\ndiplomes : "+degree1+degree2+degree3, Toast.LENGTH_LONG).show();

			file("user : "+user+"\n password : "+pw+"\nemail : "+emli+"\nsexe : "+sx+"\ndiplomes : "+degree1+degree2+degree3);
			dbase();
			
			}
		  }catch(Exception e){ ((TextView)findViewById(R.id.res)).setText("erreur données ..."); }
		}
	}
    public void file(String str)
    {
    	try
    	{  
    		
    		FileOutputStream is = openFileOutput("myfile.dat",MODE_PRIVATE);
    		DataOutputStream data = new DataOutputStream(is);
    		data.writeChars(str);
    		
    		 FileInputStream ins = openFileInput("myfile.dat");
     		DataInputStream datain = new DataInputStream(ins);
     		String sl ="";
     		String su="";
     		while((sl = datain.readLine()) != null) {su+=sl+"\n"; }

    		 
    		//Toast.makeText(MainActivity.this, "Toast :\n"+su, Toast.LENGTH_LONG).show(); 
			((TextView)findViewById(R.id.res)).setText("file :\n"+su);

    		
    	}catch(Exception e){ ((TextView)findViewById(R.id.res)).setText(e.getMessage()); }
    }
    
    
    public void dbase()
    {
    	madb dbhelp = new madb(this);
    			SQLiteDatabase db = dbhelp.getWritableDatabase();
        dbhelp.insertpersonne(user, pw, emli, sx, degree1+degree2+degree3);		
        String cbd="";
        Cursor c = dbhelp.getpersonne();
        if(c.moveToFirst()){
            do{
               String us = c.getString(1);
               String pwd = c.getString(2);
               String emlin = c.getString(3);
               String sx = c.getString(4);
               String dp = c.getString(5);
            cbd+="user : "+us+"\npassword : "+pwd+"\nemail : "+emlin+"\nsexe : "+sx+"\ndiplomes : "+dp+"\n";

            }while(c.moveToNext());
        }
            
        
    	((TextView)findViewById(R.id.resdb)).setText("SQLite :\n "+cbd);
    	
    dbhelp.deleteAll();  	  
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	
}


class madb extends SQLiteOpenHelper
{
	private String reqtable = "create table personne(" +
			"id integer  primary key autoincrement," +
			"user text not null," +
			"passwd text not null," +
			"email text not null," +
			"sexe text not null,"+
			"diplome text not null);";
			
	private static String BASENAME = "mydbase.db";
	public madb(Context context)
	{
		super(context,BASENAME,null,1);
	
	}
	
	public void insertpersonne(String usr,String pass, String eml,String sxu, String dips)
	{
		SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentval = new ContentValues();
	      contentval.put("user", usr);
	      contentval.put("passwd", pass);
	      contentval.put("email", eml);	
	      contentval.put("sexe", sxu);
	      contentval.put("diplome", dips);
	      db.insert("personne", null, contentval);

	}
	public Cursor getpersonne()
	{
		SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from personne", null );
	      return res;
	}
	public void deleteAll()
	{
		SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from personne", null );
	      if(res.moveToFirst()){
	            do{
	            	db.execSQL("delete from personne where id = "+Integer.parseInt(res.getString(0)));
	            	
	            }while(res.moveToNext());
	        }

	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(reqtable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS personne");
	      onCreate(db);
	}
}