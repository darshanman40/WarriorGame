package com.darshan.warriorgame;

import android.app.Activity;

import android.view.View.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreen extends Activity implements OnClickListener{
	SharedPreferences someData;
	TextView tvWel;
	Button bInv,bStore,bBA,bQuit,bStatus;
	
	String filename;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		filename=getIntent().getStringExtra("filename");
		tvWel = (TextView)findViewById(R.id.tvWelcom);
		bInv = (Button)findViewById(R.id.bInv);
		bBA = (Button)findViewById(R.id.bBA);
		bStore = (Button)findViewById(R.id.bStore);
		bQuit = (Button)findViewById(R.id.bQuit);
		bStatus = (Button)findViewById(R.id.bStatus);
		someData=getSharedPreferences(filename,0);
		
		
		
		bInv.setOnClickListener(this);
		bStore.setOnClickListener(this);
		bBA.setOnClickListener(this);
		bQuit.setOnClickListener(this);
		bStatus.setOnClickListener(this);
	}


	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		case R.id.bInv:
		
			break;
		case R.id.bBA:
			Intent i = new Intent("com.darshan.warriorgame.battlearena");
			
			startActivity(i);
			break;
			
		case R.id.bStatus:
			Intent in = new Intent("com.darshan.warriorgame.status");
			in.putExtra("filename", filename);
			startActivity(in);
			break;
		}
		
	}


	
	}

