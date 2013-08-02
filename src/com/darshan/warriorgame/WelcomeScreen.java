package com.darshan.warriorgame;

import java.util.Hashtable;

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
	Hashtable<String,String[]> allItms,allSkills;
	
	String filename;
	int id;
	
	ItemTest it;
	DBManager itms;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		tvWel = (TextView)findViewById(R.id.tvWelcom);
		bInv = (Button)findViewById(R.id.bInv);
		bBS = (Button)findViewById(R.id.bBS);
		bStore = (Button)findViewById(R.id.bStore);
		bQuit = (Button)findViewById(R.id.bQuit);
		bStatus = (Button)findViewById(R.id.bStatus);
		
		bInv.setOnClickListener(this);
		bStore.setOnClickListener(this);
		bBS.setOnClickListener(this);
		bQuit.setOnClickListener(this);
		bStatus.setOnClickListener(this);
		
		allItms= new Hashtable<String,String[]>();
		allSkills= new Hashtable<String,String[]>();
		sa=((SharingAtts)getApplication());
		
		id =sa.getId();
		it= new ItemTest();
		
		String[] s1 = it.printData("players");
		itms = new DBManager(this,s1[1],"allplayer",s1[0]);
		itms.openToRead();
		String st1=itms.queueAll(String.valueOf(id));
		itms.close();
		sa.setPlaAtts(st1.split(" "));
		
		String[] s2= it.printData("player_inv");
		itms = new DBManager(this,s2[1],"playersinv",s2[0]);
		itms.openToRead();
		String st2 = itms.queueAll(String.valueOf(id));
		itms.close();
		sa.setAllInv(st2.split(" "));
		
		String[] s3= it.printData("player_skills");
		itms = new DBManager(this,s3[1],"playerskill",s3[0]);
		itms.openToRead();
		String st3 = itms.queueAll(String.valueOf(id));
		itms.close();
		//sa.setAllSkills(skill, st3.split(" "));
		
		String[] s4 = it.printData("skills");
		itms = new DBManager(this,s4[1],"allskills",s4[0]);
		itms.openToRead();
		String st4=itms.queueFew(new String[]{"skill_id"});
		itms.close();
		sa.setSkills(st3.split(" "));
		
		String[] s5 = it.printData("inventory");
		itms = new DBManager(this,s5[1],"allitems",s5[0]);
		itms.openToRead();
		String st5 =itms.queueAll();
		itms.close();
		
		
		String[] ht1 = st4.split("\n");
		String[] ht2 = st5.split("\n");
		
		for(int i=0;i<ht1.length;i++){
			String[] s = ht1[i].split(" ");
			allItms.put(s[0], s);
		}
		
		for(int i=0;i<ht2.length;i++){
			String[] s = ht2[i].split(" ");
			allSkills.put(s[0], s);
		}	
		sa.setAllItms(allItms);
		sa.setAllSkills(allSkills);
		
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

