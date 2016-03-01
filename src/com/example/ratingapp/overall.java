package com.example.ratingapp;

import java.util.ArrayList;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class overall extends Activity implements OnClickListener {

	RatingBar ratingbar;
	Button submit;
	TextView tvname, tvrating ,lview ,main;
	EditText etname;
	float value, voverall;
    SQLiteDatabase sql;
	TextView lv;
	ArrayList<String> al=new ArrayList<String>();
	

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.overall);

		ratingbar = (RatingBar) findViewById(R.id.ratingBar1);
		submit = (Button) findViewById(R.id.bsubmit);
		tvname = (TextView) findViewById(R.id.tvname);
		etname = (EditText) findViewById(R.id.etname);
		lv=(TextView) findViewById(R.id.tvlist);
		lview = (TextView) findViewById(R.id.tvmain);
		main = (TextView) findViewById(R.id.tvratedlist);
		

		Intent i = getIntent();
		if (null != i) {
			value = i.getFloatExtra("av", voverall);

		}

		ratingbar.setRating(value / 2);

		submit.setOnClickListener(this);
        lview.setOnClickListener(this);
        main.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		
		case R.id.bsubmit:
		
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Average value:" + value,
					Toast.LENGTH_SHORT).show();
			
			sql=openOrCreateDatabase("Ratedb", MODE_PRIVATE, null);
			sql.execSQL("create table if not exists ratetable(name varchar, val float)");
	       
			if(etname.length()==0){
				Toast.makeText(getApplicationContext(), "Enter a name",
						Toast.LENGTH_SHORT).show();
			}
			else{
				String name= etname.getText().toString();
				float val= value;
				etname.setText("");
				
				sql.execSQL("insert into ratetable values('"+name+"','"+val+"')");
				
				 Toast.makeText(this, "values inserted successfully.", Toast.LENGTH_LONG).show();
			}
			
			
		   break;
		
		case R.id.tvmain:
		{
			
			Intent i = new Intent(overall.this ,MainActivity.class);
			startActivity(i);
		} break;
			
		case R.id.tvratedlist:
		{   
			
			SQLiteDatabase sq=openOrCreateDatabase("Ratedb",2,null);
			Cursor cs=sq.rawQuery("select * from ratetable",null);
			
			cs.moveToFirst();
			while(cs.moveToNext())
			{
				/*String ss=cs.getString(1);
				int sss=(int)cs.getFloat(2);
				String as=Integer.toString(sss);
				ss=ss+"   "+as;
				al.add(ss);*/
				lview.setVisibility(View.INVISIBLE);
				 String name=cs.getString(cs.getColumnIndex("name"));
				    String surname=cs.getString(1);
				    //display on text view
				    lv.append("Name:"+name+" and SurName:"+surname+"\n");
				    //move next position until end of the data
			}
			
			
			//ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al);
			//lv.setAdapter(ad);
		} break;
		    
		
		}
		
	}
}