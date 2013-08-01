package com.darshan.warriorgame;

import android.app.Activity;

import android.view.View.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeScreen extends Activity implements OnClickListener{
	//SharedPreferences someData;
	TextView tvWel;
	Button bInv,bStore,bBS,bQuit,bStatus;
	SharingAtts sa;
	Intent i;
	
	String filename;
	int id;
	
	ItemTest it;
	DBManager itms;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		//filename=getIntent().getStringExtra("filename");
		tvWel = (TextView)findViewById(R.id.tvWelcom);
		bInv = (Button)findViewById(R.id.bInv);
		bBS = (Button)findViewById(R.id.bBS);
		bStore = (Button)findViewById(R.id.bStore);
		bQuit = (Button)findViewById(R.id.bQuit);
		bStatus = (Button)findViewById(R.id.bStatus);
		//someData=getSharedPreferences(filename,0);
		
		bInv.setOnClickListener(this);
		bStore.setOnClickListener(this);
		bBS.setOnClickListener(this);
		bQuit.setOnClickListener(this);
		bStatus.setOnClickListener(this);
		
		
		
		
		sa=((SharingAtts)getApplication());
		
		id =sa.getId();
		it= new ItemTest();
		//String[] st = it.printData("players");
		//itms = new DBManager(this,st[1],"allplayer",st[0]);
		//itms.recover(st);
		String s,s2,s3,s4,s5,s6;
		s="asshole";
		s2="inv";
		s3="pskils";
		s4="skilid";
		String[] st1 = it.printData("players");
		itms = new DBManager(this,st1[1],"allplayer",st1[0]);
		//itms.recover(st1);
		itms.openToWrite();
		
		//for(int i=2;i<st1.length;i++)
		//	itms.insertQuery(st1[i]);
		 s = itms.queueAll(String.valueOf(id));
		itms.close();	
		String[] st2 = s.split(" ");
		sa.setPlaAtts(st2);
		try{
		String[] st3 = it.printData("player_inv");
		itms = new DBManager(this,st3[1],"playersinv",st3[0]);
		//itms.recover(st3);
		itms.openToWrite();
		//itms.cretTable();
		//for(int i=2;i<st3.length;i++)
		//	itms.insertQuery(st3[i]);
		s2 = itms.queueAll(String.valueOf(id));
		itms.close();	
		String[] st4 = s2.split(" ");
		sa.setAllInv(st4);
		
		String[] st5 = it.printData("player_skills");
		itms = new DBManager(this,st5[1],"playerskill",st5[0]);
		//itms.recover(st5);
		itms.openToWrite();
		//itms.cretTable();
		//for(int i=2;i<st5.length;i++)
		//	itms.insertQuery(st5[i]);
		s3 = itms.queueAll(String.valueOf(id));
		itms.close();	
		String[] st6 = s3.split(" ");
		//sa.setAllInv(st6);
		
		String[] st7 = it.printData("skills");
		itms = new DBManager(this,st7[1],"allskills",st7[0]);
		//itms.recover(st7);
		itms.openToWrite();
		//itms.cretTable();
		//for(int i=2;i<st7.length;i++)
		//	itms.insertQuery(st7[i]);
		s4 = itms.queueFew(new String[]{"skill_id"});
		itms.close();	
		s4 = s4.replace(" ", "-");
		String[] st8 = s4.split("-");
		//for(int i=1; i<st6.length;i++)
		//	st6[i-1]=st6[i].trim();
		//st6[st6.length-1]="";
			
		sa.setSkills(st8,st6);
		Toast t = Toast.makeText(getApplicationContext(), "Congratz", Toast.LENGTH_LONG);
		t.show();
		}catch(Exception e){
			System.err.print(e);
			Toast t = Toast.makeText(getApplicationContext(),s3+"   "+e.toString(), Toast.LENGTH_LONG);
			t.show();
		}
	}


	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		case R.id.bInv:
			i = new Intent("com.darshan.warriorgame.invstat");
			
			startActivity(i);
			break;
		case R.id.bBS:
			Class ourClass;
			try {
				ourClass = Class.forName("com.darshan.warriorgame.BattleStadium");
			i =new Intent(WelcomeScreen.this,ourClass);
			startActivity(i);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case R.id.bStatus:
			Intent in = new Intent("com.darshan.warriorgame.status");
			in.putExtra("filename", filename);
			startActivity(in);
			break;
		}
		
	}


	
	}

