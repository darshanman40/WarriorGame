package com.darshan.warriorgame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BattleArena extends Activity implements OnClickListener{
	
	SharedPreferences someData;
	Editor editor;
	String filename;
	
	ProgressBar pbCHp,pbPHp;
	RadioGroup rgAttacks;
	TextView tvCpClass,tvCpLevel,tvPClass,tvPLevel, tvPName ;
	Button attack;
	
	int CpLevel, PLevel;
	int aCode;
	
	String CpClass,PClass,PName;
	
	Battle b;
	Integer[] hps, maxhps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battlearena);
		
		aCode=0;
		
		filename = getIntent().getStringExtra("filename");
		someData = getSharedPreferences(filename,0);
		editor = someData.edit();
		
		CpLevel = someData.getInt("Computer_Level", 0);
		PLevel = someData.getInt(filename+"_Level", 0); 
		CpClass = someData.getString("Computer_Class","");
		PClass = someData.getString(filename+"_Class", "");
		PName = someData.getString("playername", "");
		
		attack = (Button)findViewById(R.id.bAttack);
		rgAttacks = (RadioGroup) findViewById(R.id.rgattacks);
		tvCpClass = (TextView) findViewById(R.id.tvCpClass);
		tvCpLevel = (TextView) findViewById(R.id.tvCpLevel);
		tvPClass = (TextView) findViewById(R.id.tvPClass);
		tvPLevel = (TextView) findViewById(R.id.tvPLevel);
		tvPName = (TextView) findViewById(R.id.tvPName);
		pbCHp = (ProgressBar) findViewById(R.id.pbComHp);
		pbPHp = (ProgressBar) findViewById(R.id.pbPHp);
		
		tvCpClass.setText(someData.getString("Computer_Class", ""));
		tvCpLevel.setText(String.valueOf(CpLevel));
		tvPClass.setText(PClass);
		tvPLevel.setText(String.valueOf(someData.getInt(filename+"_Level", 0)));
		tvPName.setText(PName);
		
		attack.setOnClickListener(this);
		
		b = new Battle(PLevel,CpLevel,CpClass,PClass);
		try{
		maxhps = (Integer[])b.initialize();
		pbPHp.setProgress(100);
		pbCHp.setProgress(100);
		}catch(Exception e){
			System.err.print(e);
		}	
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bAttack:
			int rgACheckedId = rgAttacks.getCheckedRadioButtonId();
			//RadioButton rbtemp = (RadioButton)findViewById(rgACheckedId);
			switch(rgACheckedId){
			case R.id.rbattack1:
				aCode=0;
				break;
			case R.id.rbattack2:
				aCode=1;
				break;
			}
			
			hps = (Integer[])b.initialize(aCode);
			pbCHp.setProgress((int)((hps[1]*100)/maxhps[1]));
			pbPHp.setProgress((int)((hps[0]*100)/maxhps[0]));
			
			try{
			if(Integer.valueOf(hps[0])<0){
		  		  //System.out.println("player1 is fainted");
				Toast t = Toast.makeText(getApplicationContext(), PName+" fainted", Toast.LENGTH_LONG);
				t.show();
				finish();
				break;
		  	  }else if(Integer.valueOf(hps[1])<0){
		  		Toast t = Toast.makeText(getApplicationContext(), "Computer fainted", Toast.LENGTH_LONG);
				t.show();
				finish();
		  		break;
		  	  }else{
		  		Toast t = Toast.makeText(getApplicationContext(), PName+" hp= "+hps[0]+"\n Com hp= "+hps[1], Toast.LENGTH_SHORT);
		  		t.show();
		  		//t = Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT);
		  		//t.show();
		  		
		  	  }
			}catch(Exception e){
				System.err.println(e);
				Toast t = Toast.makeText(getApplicationContext(), "err", Toast.LENGTH_LONG);
				t.show();
			}
			break;
		}
	}
	
	

	
}
