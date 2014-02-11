package com.darshan.warriorgame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class Status extends Activity implements OnClickListener{

	TextView tvName,tvStr,tvSpeed,tvMaxHp,tvMaxMana,tvMaxXp,tvGold;
	//SharedPreferences someData;
	String filename;
	Button bSkilUp;
	
	SharingAtts sa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		//filename = getIntent().getStringExtra("filename");
		
		tvName = (TextView)findViewById(R.id.tvName);
		tvStr = (TextView)findViewById(R.id.tvStr);
		tvSpeed = (TextView)findViewById(R.id.tvSpeed);
		tvMaxHp = (TextView)findViewById(R.id.tvMaxHp);
		tvMaxMana = (TextView)findViewById(R.id.tvMaxMana);
		tvMaxXp = (TextView)findViewById(R.id.tvMaxExp);
		tvGold = (TextView)findViewById(R.id.tvGold);
		bSkilUp = (Button)findViewById(R.id.bSkillUp);
		sa=((SharingAtts)getApplication());
		sa.updateStat();
		
		
		String s="";
		Integer[] atts = sa.getMajatt();
		s=tvName.getText().toString();
		tvName.setText(s+" "+sa.getName());
		
		s=tvStr.getText().toString();
		tvStr.setText(s+String.valueOf(atts[0]));
		
		s=tvSpeed.getText().toString();
		tvSpeed.setText("Speed "+String.valueOf(atts[1]));
		
		s=tvMaxHp.getText().toString();
		tvMaxHp.setText(s+String.valueOf(atts[2]));
		
		s=tvMaxMana.getText().toString();
		tvMaxMana.setText(s+String.valueOf(atts[3]));
		
		s=tvMaxXp.getText().toString();
		tvMaxXp.setText(s+String.valueOf(atts[4]));
		
		s=tvGold.getText().toString();
		tvGold.setText(s+String.valueOf(atts[8]));
		
		Integer[] tt =sa.getAllSkills();
		Integer[] t1 = sa.getAllSkillsLvl();
		s="";
		
		for(int i=0; i<t1.length;i++)
			s=s+String.valueOf(t1[i])+" "+String.valueOf(t1[i])+"\n";
	
		tvGold.setText(s);
		bSkilUp.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.bSkillUp){
			Intent i =new Intent("com.darshan.warriorgame.skillup");
			startActivity(i);
			finish();
		}
	}
	
	
	
}
