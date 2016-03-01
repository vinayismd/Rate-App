package com.example.ratingapp;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnSeekBarChangeListener, OnClickListener {

	SeekBar sbstage, sbenergy, sbperformance, sbaudience;
	TextView tvstagepresence, tvonstage, tvperformance, tvaudience;
	Button boverall;
	float vstage, venergy, vperformance, vaudience,voverall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sbstage = (SeekBar) findViewById(R.id.sbstage);
		sbenergy = (SeekBar) findViewById(R.id.sbenergy);
		sbperformance = (SeekBar) findViewById(R.id.sbperformance);
		sbaudience = (SeekBar) findViewById(R.id.sbaudience);
		tvstagepresence = (TextView) findViewById(R.id.tvstagepresence);
		tvonstage = (TextView) findViewById(R.id.tvonstage);
		tvperformance = (TextView) findViewById(R.id.tvperformance);
		tvaudience = (TextView) findViewById(R.id.tvaudience);
		boverall = (Button) findViewById(R.id.boverall);

		sbstage.setOnSeekBarChangeListener(this);
		sbenergy.setOnSeekBarChangeListener(this);
		sbperformance.setOnSeekBarChangeListener(this);
		sbaudience.setOnSeekBarChangeListener(this);
		boverall.setOnClickListener(this);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		switch (seekBar.getId()) {
		case R.id.sbstage: {

			Toast.makeText(getApplicationContext(),
					"seek: " + progress, Toast.LENGTH_SHORT).show();
			
			 vstage = progress;

		}  break;
		case R.id.sbenergy: {
			Toast.makeText(getApplicationContext(),
					"seekbar: " + progress, Toast.LENGTH_SHORT).show();
			
			venergy = progress;

		} break;
		case R.id.sbperformance: {
			Toast.makeText(getApplicationContext(),
					"seekbar pro: " + progress, Toast.LENGTH_SHORT).show();
			vperformance = progress;
		}  break;
		case R.id.sbaudience: {
			Toast.makeText(getApplicationContext(),
					"seekbar progress: " + progress, Toast.LENGTH_SHORT).show();
			
			vaudience = progress;
		} break;
		}
		voverall = (vstage+vaudience+vperformance+venergy)/4;
		Toast.makeText(getApplicationContext(),
				"Average value:" +voverall, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	voverall = (vstage+vaudience+vperformance+venergy)/4;
		//Toast.makeText(getApplicationContext(),
			//	"Average value:" +voverall, Toast.LENGTH_SHORT).show();
		
		Intent i = new Intent(MainActivity.this , overall.class);
		i.putExtra("av", voverall);
		
		startActivity(i);
		
	}


}
