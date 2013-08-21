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

	Button tf,sm,sr,ag,ba,wa,sh,mr,ro,sma,ma,ra,as,kn,wi,tr,an,gu,vm,gk;
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
		
		tf = (Button)findViewById(R.id.bThief1);
		sm = (Button)findViewById(R.id.bSwordsman);
		sr = (Button)findViewById(R.id.bSeer);
		ag = (Button)findViewById(R.id.bAgent);
		ba = (Button)findViewById(R.id.bBandit);
		wa = (Button)findViewById(R.id.bWarrior);
		sh = (Button)findViewById(R.id.bShaman);
		mr = (Button)findViewById(R.id.bMercenery);
		ro = (Button)findViewById(R.id.bRonin);
		sma = (Button)findViewById(R.id.bSamurai);
		ma = (Button)findViewById(R.id.bMage);
		ra = (Button)findViewById(R.id.bRaider);
		as = (Button)findViewById(R.id.bAssailant);
		kn = (Button)findViewById(R.id.bKnight);
		wi = (Button)findViewById(R.id.bWitch);
		tr = (Button)findViewById(R.id.bTrooper);
		an = (Button)findViewById(R.id.bAntiNinja);
		gu = (Button)findViewById(R.id.bGuardian);
		vm = (Button)findViewById(R.id.bVoodooMaster);
		gk = (Button)findViewById(R.id.bGateKeeper);
		
		tf.setOnClickListener(this);
		sm.setOnClickListener(this);
		sr.setOnClickListener(this);
		ag.setOnClickListener(this);
		ba.setOnClickListener(this);
		wa.setOnClickListener(this);
		sh.setOnClickListener(this);
		mr.setOnClickListener(this);
		ro.setOnClickListener(this);
		sma.setOnClickListener(this);
		ma.setOnClickListener(this);
		ra.setOnClickListener(this);
		as.setOnClickListener(this);
		kn.setOnClickListener(this);
		wi.setOnClickListener(this);
		tr.setOnClickListener(this);
		an.setOnClickListener(this);
		gu.setOnClickListener(this);
		vm.setOnClickListener(this);
		gk.setOnClickListener(this);
		
		sa = ((SharingAtts)getApplication());
		test = (TextView)findViewById(R.id.tvTest);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		i = new Intent("com.darshan.warriorgame.battlearena");
		switch(arg0.getId()){
		case R.id.bThief1:
			
			setOpp(this,"1");
			//i.putExtra("oppName", "Thief");
		//	i.putExtra("oppClass", "Ninja");
			//i.putExtra("oppLevel", "1");
			startActivity(i);
			break;
		case R.id.bSwordsman:
			setOpp(this,"2");
		//	//i.putExtra("oppName", "Swordsman");
		//	i.putExtra("oppClass", "Samurai");
		//	i.putExtra("oppLevel", "1");
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
		String ss1[] = s.split(" ");
		test.setText(s);
		oppAtts.put(ss1[0], ss1);
		
		i.putExtra("oppName",ss1[1]);
		i.putExtra("oppClass", ss1[3]);
		i.putExtra("oppLevel", ss1[4]);
		sa.setMajOppAtts(oppAtts);
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
	}

	
}
