package com.darshan.warriorgame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
//import android.widget.Toast;

public class ClassNOpp extends Activity implements OnClickListener{

	SharedPreferences someData;
	Editor editor;
	String filename;
	
	RadioGroup rgComp,rgPlayer;
	RadioButton rbPlayer,rbComp;
	
	Button bFight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classnopp);
		
		filename = getIntent().getStringExtra("filename");
		someData = getSharedPreferences(filename,0);
		//editor = someData.edit();
		
		//rbnin1 = (RadioButton)findViewById(R.id.rbNinja1);
		rgPlayer = (RadioGroup)findViewById(R.id.rg1);
		rgComp =   (RadioGroup)findViewById(R.id.rg2);
		bFight = (Button)findViewById(R.id.bFight);
		
		bFight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//switch(v.getId()){
		if(v.getId()==R.id.bFight){
			
			int playerId = rgPlayer.getCheckedRadioButtonId();
			int compId = rgComp.getCheckedRadioButtonId();
			
			rbPlayer = (RadioButton) findViewById(playerId);
			rbComp = (RadioButton) findViewById(compId);
			
			editor.putString(filename+"_Class",rbPlayer.getText().toString());
			editor.putString("Computer_Class",rbComp.getText().toString());
			editor.putInt(filename+"_Level", 5);
			editor.putInt("Computer_Level", 5);
			editor.commit();
			/*
			
			Toast t = Toast.makeText(getApplicationContext(), someData.getString(filename+"_Class", ""), Toast.LENGTH_SHORT);
			Toast t2 = Toast.makeText(getApplicationContext(), someData.getString("Computer_Class", ""), Toast.LENGTH_SHORT);
			t.show();
			t2.show();
			*/
			Intent i = new Intent("com.darshan.warriorgame.battlearena");
			i.putExtra("filename", filename);
			startActivity(i);
			
			//break;
		}
	}

	
	
}
