package com.darshan.warriorgame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;



public class Status extends Activity{

	TextView tvName,tvAttack,tvDef,tvHp,tvMana,tvDR,tvAcc;
	SharedPreferences someData;
	String filename;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		filename = getIntent().getStringExtra("filename");
		tvName = (TextView)findViewById(R.id.tvName);
		tvAttack = (TextView)findViewById(R.id.tvAttack);
		tvDef = (TextView)findViewById(R.id.tvDef);
		tvHp = (TextView)findViewById(R.id.tvHP);
		tvMana = (TextView)findViewById(R.id.tvMana);
		tvDR = (TextView)findViewById(R.id.tvDR);
		tvAcc = (TextView)findViewById(R.id.tvAcc);
		
		someData = getSharedPreferences(filename,0);
		Integer[] stat=new Integer[7];
		for(int i=0;i<7;i++)
			stat[i]=someData.getInt(filename+"_att_"+i, 9999);
		
		tvName.setText(filename);
		tvAttack.setText(tvAttack.getText()+stat[6].toString());
		tvDef.setText(tvDef.getText()+stat[3].toString());
		tvHp.setText(tvHp.getText()+stat[0].toString());
		tvMana.setText(tvMana.getText()+stat[1].toString());
		tvDR.setText(tvDR.getText()+stat[4].toString());
		tvAcc.setText(tvAcc.getText()+stat[2].toString());
		
			
		
		
		
	}

	
	
}
