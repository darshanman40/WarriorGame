package com.darshan.warriorgame;

import java.util.Hashtable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BattleStadium extends Activity implements OnClickListener{

	Button bTheif;
	Player pl,com;
	Intent i;
	DBManager db;
	ItemTest it;
	SharingAtts sa;
	TextView test;
	Hashtable<String,String[]> oppAtts,oppSkills,oppInv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battlestadium);
		
		bTheif = (Button)findViewById(R.id.bThief1);
		
		bTheif.setOnClickListener(this);
		
		sa = ((SharingAtts)getApplication());
		
		test = (TextView)findViewById(R.id.tvTest);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bThief1:
			//com = new Player(this, 2);
			/*
			it = new ItemTest();
			String[] s1=it.printData("players");
			db = new DBManager(this,s1[1],"allplayer",s1[0]);
			db.openToRead();
			String s=db.queueAll("2");
			db.close();
			String ss1[] = s.split(" ");
			//for(int i=0;i<ss1.length;i++)
			oppAtts.put(ss1[0], ss1);
			sa.setOppAtts(oppAtts);
			
			//----------------------------------------------------------------
			String[] s2=it.printData("player_inv");
			db = new DBManager(this,s2[1],"playersinv",s2[0]);
			db.openToRead();
			s=db.queueAll("2");
			db.close();
			String ss2[] = s.split(" ");
			oppInv.put(ss2[0], ss2);
			sa.setOppAtts(oppInv);
			//----------------------------------------------
			String[] s3=it.printData("player_skills");
			db = new DBManager(this,s3[1],"playerskill",s3[0]);
			db.openToRead();
			s=db.queueAll("2");
			db.close();
			String ss3[] = s.split(" ");
			oppSkills.put(ss3[0], ss3);
			*/
			setOpp(this,"2");
			//test.setText(String.valueOf(sa.getAllSkills("1001")));
			String s="101";
			//for(String saaa:sa.allItms.keySet()){
				String[] sn = sa.allItms.get(s);
				s=s+"\n";
				for(int i =0;i<sn.length;i++){
					s= s+ sn[i]+" ";
				}
		//		s=s+"\n";
				//for(int i =0;i<sa.allItms.get(saaa).length;i++){
				//	s=s+sa.allItms.get(saaa)[i]+"  ";
			//	}
			//}
			test.setText(s);
			i = new Intent("com.darshan.warriorgame.battlearena");
			i.putExtra("oppName", "Thief");
			i.putExtra("oppClass", "Ninja");
			i.putExtra("oppLevel", "1");
			startActivity(i);
			
			break;
		
		}
	}
	public void setOpp(Context c,String id){
		it = new ItemTest();
		oppAtts = new Hashtable<String,String[]>();
		oppSkills = new Hashtable<String,String[]>();
		oppInv = new Hashtable<String,String[]>();
		
		String[] s1=it.printData("players");
		db = new DBManager(c,s1[1],"allplayer",s1[0]);
		db.openToRead();
		String s=db.queueAll(id);
		db.close();
		//try{
		String ss1[] = s.split(" ");
		test.setText(s);
		//for(int i=0;i<ss1.length;i++)
		
		oppAtts.put(ss1[0], ss1);
		sa.setMajOppAtts(oppAtts);
		
	//}catch(Exception e){
	//		Toast t = Toast.makeText(getApplicationContext(), "players table only", Toast.LENGTH_LONG);
	//		t.show();
	//	}
		//----------------------------------------------------------------
		String[] s2=it.printData("player_inv");
		db = new DBManager(c,s2[1],"playersinv",s2[0]);
		db.openToRead();
		s=db.queueAll(id);
		db.close();
		String ss2[] = s.split(" ");
		oppInv.put(ss2[0], ss2);
		sa.setOppInv(oppInv);
		//----------------------------------------------
		String[] s3=it.printData("player_skills");
		db = new DBManager(c,s3[1],"playerskill",s3[0]);
		db.openToRead();
		s=db.queueAll(id);
		db.close();
		String ss3[] = s.split(" ");
		oppSkills.put(ss3[0], ss3);
		sa.setOppSkills(oppSkills);
		//*/
	}

	
}
