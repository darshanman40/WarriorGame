package com.darshan.warriorgame;

import java.util.Hashtable;

import android.app.Activity;
//import android.app.AlertDialog;
import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
//import android.widget.EditText;
import android.widget.TextView;
//import android.widget.Toast;

public class BattleStadium extends Activity implements OnClickListener{

	Button tf,sm,sr,ag,ba,sh,mr,ro,sma,ma,ra,as,kn,wi,tr,an,gu,vm,gk;
	Button cheat;
	Player pl,com;
	Intent i;
	DBManager db;
	ItemTest it;
	SharingAtts sa;
	TextView test;
	String cCode;
	CheatCompiler cc;
	
	Hashtable<String,String[]> oppAtts,oppSkills,oppInv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battlestadium);
		sa = ((SharingAtts)getApplication());
		cc = new CheatCompiler(this);
		cCode="";
		tf = (Button)findViewById(R.id.bThief1);
		sm = (Button)findViewById(R.id.bSwordsman);
		sr = (Button)findViewById(R.id.bSeer);
		ag = (Button)findViewById(R.id.bAgent);
		ba = (Button)findViewById(R.id.bBandit);
		
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
		gk = (Button)findViewById(R.id.bGateKeeper);
		cheat = (Button)findViewById(R.id.bcheat1);
		test = (TextView)findViewById(R.id.tvTest);
		cheat.setOnClickListener(this);
		tf.setOnClickListener(this);
		
		if(sa.lvl>1)
			sm.setOnClickListener(this);
		if(sa.lvl>2)
			sr.setOnClickListener(this);
		if(sa.lvl>3)
			ag.setOnClickListener(this);
		if(sa.lvl>4)
			ba.setOnClickListener(this);
		if(sa.lvl>5)
			sh.setOnClickListener(this);
		if(sa.lvl>6)
			mr.setOnClickListener(this);
		if(sa.lvl>7)
			ro.setOnClickListener(this);
		if(sa.lvl>8)
			sma.setOnClickListener(this);
		if(sa.lvl>9)
			ma.setOnClickListener(this);
		if(sa.lvl>10)
		ra.setOnClickListener(this);
		if(sa.lvl>11)
		as.setOnClickListener(this);
		if(sa.lvl>12)
		kn.setOnClickListener(this);
		if(sa.lvl>13)
		wi.setOnClickListener(this);
		if(sa.lvl>14)
		tr.setOnClickListener(this);
		if(sa.lvl>15)
		an.setOnClickListener(this);
		if(sa.lvl>16)
		gu.setOnClickListener(this);
		if(sa.lvl>17)
		vm.setOnClickListener(this);
		if(sa.lvl>18)
			gk.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		i = new Intent("com.darshan.warriorgame.battlearena");
		//switch(arg0.getId()){
		if(arg0.getId()==R.id.bThief1){
			setOpp(this,"1");
			startActivity(i);
		}else if(arg0.getId()==R.id.bSwordsman){
			setOpp(this,"2");
			startActivity(i);
		}else if(arg0.getId()==R.id.bSeer){
			setOpp(this,"3");
			startActivity(i);
		}else if(arg0.getId()==R.id.bAgent){
			setOpp(this,"4");
			startActivity(i);
		}else if(arg0.getId()==R.id.bBandit){
			setOpp(this,"5");
			startActivity(i);
		}else if(arg0.getId()==R.id.bShaman){
			setOpp(this,"6");
			startActivity(i);
		}else if(arg0.getId()==R.id.bMercenery){
			setOpp(this,"7");
			startActivity(i);
		}else if(arg0.getId()==R.id.bRonin){
			setOpp(this,"8");
			startActivity(i);
		}else if(arg0.getId()==R.id.bSamurai){
			setOpp(this,"9");
			startActivity(i);
		}else if(arg0.getId()==R.id.bMage){
			setOpp(this,"10");
			startActivity(i);
		}else if(arg0.getId()==R.id.bRaider){
			setOpp(this,"11");
			startActivity(i);
		}else if(arg0.getId()==R.id.bAssailant){
			setOpp(this,"12");
			startActivity(i);
		}else if(arg0.getId()==R.id.bKnight){
			setOpp(this,"13");
			startActivity(i);
		}else if(arg0.getId()==R.id.bWitch){
			setOpp(this,"14");
			startActivity(i);
		}else if(arg0.getId()==R.id.bTrooper){
			setOpp(this,"15");
			startActivity(i);
		}else if(arg0.getId()==R.id.bAntiNinja){
			setOpp(this,"16");
			startActivity(i);
		}else if(arg0.getId()==R.id.bGuardian){
			setOpp(this,"17");
			startActivity(i);
		}else if(arg0.getId()==R.id.bVoodooMaster){
			setOpp(this,"18");
			startActivity(i);
		}else if(arg0.getId()==R.id.bGateKeeper){
			setOpp(this,"19");
			startActivity(i);
		}else if(arg0.getId()==R.id.bcheat1){
			sa.setContext(this);
			cc.show();
		}
		
		if(arg0.getId()!=R.id.bcheat1){
			finish();
		}
	}
	public void setOpp(Context c,String id){
		it = new ItemTest();
		oppAtts = new Hashtable<String,String[]>();
		oppSkills = new Hashtable<String,String[]>();
		oppInv = new Hashtable<String,String[]>();
		
		String[] s1=it.printData("players");
		String[] rows = s1[Integer.valueOf(id)+1].split(" ");
		
		Toast.makeText(getApplicationContext(), String.valueOf(rows[0]), Toast.LENGTH_SHORT).show();
		
		i.putExtra("oppName",rows[1]);
		i.putExtra("oppClass", rows[3]);
		i.putExtra("oppLevel", rows[4]);
		i.putExtra("oppGold", rows[13]);
		oppAtts.put(rows[0], rows);
		sa.setMajOppAtts(oppAtts);
		
		String[] s2=it.printData("player_inv");
		rows = null;
		rows = s2[Integer.valueOf(id)+1].split(" ");
		
		oppInv.put(rows[0], rows);
		sa.setOppInv(oppInv);
		
		String[] s3=it.printData("player_skills");
		rows = null;
		rows = s3[Integer.valueOf(id)+1].split(" ");
		
		oppSkills.put(rows[0], rows);
		sa.setOppSkills(oppSkills);
		
		/*
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
		*/
		
	}

	
}
