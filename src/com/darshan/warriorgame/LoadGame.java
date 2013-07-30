package com.darshan.warriorgame;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoadGame extends Activity implements OnClickListener {

	EditText lgId,pass;
	Button bcon,bcan;
	
	String all;
	
	SharingAtts sa;
	DBManager lin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadgame);
		
		lgId = (EditText)findViewById(R.id.etLogin1);
		pass = (EditText)findViewById(R.id.etPassword);
		bcon = (Button)findViewById(R.id.bCon1);
		bcan = (Button)findViewById(R.id.bCan1);
		
		bcon.setOnClickListener(this);
		bcan.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bCon1:
			String login = lgId.getText().toString();
			String pas = pass.getText().toString();
			ItemTest it = new ItemTest();
			String[] ss = it.printData("players");
			lin = new DBManager(this, ss[1], "allplayer", ss[0]);
			lin.openToWrite();
			//lin.cretTable();
			//lin.insertQuery(ss[2]);
			int n =lin.queueAll(login,pas);
			lin.close();
			sa=((SharingAtts)getApplication());
			
			if(n!=0){
				Intent in = new Intent("com.darshan.warriorgame.welcome");
				startActivity(in);
				sa.setId(n);
			}else{
				Toast t;
				t = Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG);
				t.show();
			}
				
				
			break;
			
		case R.id.bCan1:
			
			finish();
		}
		
	}
	

	
}
