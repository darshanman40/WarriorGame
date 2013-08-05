package com.darshan.warriorgame;

import java.util.Hashtable;

import android.app.Activity;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
//import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
//import android.widget.Toast;

public class BattleArena extends Activity implements OnClickListener{
	
	//SharedPreferences someData;
	//Editor editor;
	String filename;
	
	ProgressBar pbCHp,pbPHp;
	RadioGroup rgAttacks;
	TextView tvCpClass,tvCpLevel,tvPClass,tvPLevel, tvPName, tvFStat, tvCpName ;
	Button attack,bSkill2,bSkill1,bSkill3,bSkill4,bSkill5,bSkill6,bSkill7
	,bSkill8,bSkill9,bSkill10,bSkill11,bSkill12,bSkill13,
	bSkill14,bSkill15,bSkill16;
	/*
	int CpLevel, PLevel;
	int aCode;
	String CpClass,PClass,PName;
	*/
	//Battle b;
	Integer[] p1MajAtts,p2MajAtts,p1MinAtts,p2MinAtts, p1Inv,p2Inv,p1Skill,p2Skill;
	//Integer[] hps, maxhps;
	int comId;
	String p1name,p2name;
	
	Player pl,comPl;
	SharingAtts sa;
	Battle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battlearena);
		sa = ((SharingAtts)getApplication());
		
		p1name = sa.name;
		p2name = getIntent().getStringExtra("oppName");
		//comId = getIntent().getIntExtra("comId", 0);
		//comPl = new Player(this,comId);
		//pl = new Player(this,sa.getMajatt(),sa.getAllInv(),sa.getAllSkillsLvl(),sa.name,sa.playerClass,sa.id);
		
		//p1MajAtts = new Integer[8];
		//p2MajAtts = new Integer[6];
		//p1MinAtts = new Integer[8];
		//p2MinAtts = new Integer[8];
		//p1Inv = new Integer[6];
		//p2Inv = new Integer[6];
		//p1Skill = new Integer[19];
		//p2Skill = new Integer[19];
		//
		pbPHp = (ProgressBar)findViewById(R.id.pbPHp);
		pbCHp = (ProgressBar)findViewById(R.id.pbComHp);
		attack = (Button)findViewById(R.id.bAttack);
		bSkill1 = (Button)findViewById(R.id.bSkill1);
		bSkill2 = (Button)findViewById(R.id.bSkill2);
		bSkill3 = (Button)findViewById(R.id.bSkill3);
		bSkill4 = (Button)findViewById(R.id.bSkill4);
		bSkill5 = (Button)findViewById(R.id.bSkill5);
		bSkill6 = (Button)findViewById(R.id.bSkill6);
		bSkill7 = (Button)findViewById(R.id.bSkill7);
		bSkill8 = (Button)findViewById(R.id.bSkill8);
		bSkill9 = (Button)findViewById(R.id.bSkill9);
		bSkill10 = (Button)findViewById(R.id.bSkill10);
		bSkill11 = (Button)findViewById(R.id.bSkill11);
		bSkill12 = (Button)findViewById(R.id.bSkill12);
		bSkill13 = (Button)findViewById(R.id.bSkill13);
		bSkill14 = (Button)findViewById(R.id.bSkill14);
		bSkill15 = (Button)findViewById(R.id.bSkill15);
		bSkill16 = (Button)findViewById(R.id.bSkill16);
		
		tvCpClass = (TextView) findViewById(R.id.tvCpClass);
		tvCpLevel = (TextView) findViewById(R.id.tvCpLevel);
		tvPClass = (TextView) findViewById(R.id.tvPClass);
		tvPLevel = (TextView) findViewById(R.id.tvPLevel);
		tvPName = (TextView) findViewById(R.id.tvPName);
		tvFStat = (TextView) findViewById(R.id.tvFStat);
		tvCpName = (TextView) findViewById(R.id.tvComp);

		
		tvCpClass.setText(getIntent().getStringExtra("oppClass"));
		
		tvPClass.setText(sa.playerClass);//sa.playerClass);
		tvPName.setText(p1name);//sa.name);
		
		
		tvCpName.setText(p2name);
		//*/
		attack.setOnClickListener(this);
		bSkill1.setOnClickListener(this);
		bSkill2.setOnClickListener(this);
		bSkill3.setOnClickListener(this);
		bSkill4.setOnClickListener(this);
		bSkill5.setOnClickListener(this);
		bSkill6.setOnClickListener(this);
		bSkill7.setOnClickListener(this);
		bSkill8.setOnClickListener(this);
		bSkill9.setOnClickListener(this);
		bSkill10.setOnClickListener(this);
		bSkill11.setOnClickListener(this);
		bSkill12.setOnClickListener(this);
		bSkill13.setOnClickListener(this);
		bSkill14.setOnClickListener(this);
		bSkill15.setOnClickListener(this);
		bSkill16.setOnClickListener(this);
		hastablesFor();
		//Integer[] plMinAtts = setPlaMinAtts();
		//Integer[] oppMinAtts = setOppMinAtts();
		//Integer[] plMajAtts = sa.getMajatt();
		//Integer[] oppMajAtts = sa.oppMajAtts;
		
		//sa.updateStat();
		
		tvPLevel.setText(String.valueOf(sa.lvl)+ "\t" + b.p1hp+"/"+b.maxp1hp+"\t"+ b.p1mana+"/"+b.maxp1mana);
		tvCpLevel.setText(getIntent().getStringExtra("oppLevel")+"\t"+b.p2hp+"/"+b.maxp2hp+"\t"+b.p2mana+"/"+b.maxp2mana);//comPl.lvl));
		
	}

	public void hastablesFor(){
		
		sa = ((SharingAtts)getApplication());
		Integer[] eqInvPl = sa.getBattleInv(); 
		
		//Integer[] poInvPl = sa.poInv;
		//Integer[] eqInvOpp = sa.oppInv;
		//Integer[] oppSkills = sa.oppSkilllvl;
		//Integer[] plSkills = sa.skilllvl;
		
		Hashtable<String,Integer[]> Opp = new Hashtable<String,Integer[]>();
		Hashtable<String,Integer[]> Pla = new Hashtable<String,Integer[]>();
		
		Opp.put("MinAtts", setOppMinAtts());
		Opp.put("MajAtts", sa.oppMajAtts);
		Opp.put("eqInv", sa.oppInv);
		Opp.put("Skills", sa.oppSkilllvl);
		sa.updateStat();
		Pla.put("MinAtts",setPlaMinAtts());
		Pla.put("MajAtts", sa.getMajatt());
		Pla.put("eqInv", eqInvPl);
		Pla.put("Skills", sa.skilllvl);

		b = new Battle(Pla,Opp);
		
		pbPHp.setProgress((int)(sa.hp/sa.maxHp)*100);
		pbCHp.setProgress((int)(b.maxp2hp/b.maxp2hp)*100);
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bAttack:
			b.damage(0,0);
			refreshScreen();
			break;
		
		case R.id.bSkill1:
			b.damage(1,0);
			refreshScreen();
			break;
		
		case R.id.bSkill2:
			b.damage(2,0);
			refreshScreen();
			break;
		
		case R.id.bSkill3:
			b.damage(3,0);
			refreshScreen();
			break;
		
		case R.id.bSkill4:
			b.damage(4,0);
			refreshScreen();
			break;
		
		case R.id.bSkill5:
			b.damage(5,0);
			refreshScreen();
			break;
		
		case R.id.bSkill6:
			b.damage(6,0);
			refreshScreen();
			break;
		
		case R.id.bSkill7:
			b.damage(7,0);
			refreshScreen();
			break;
		case R.id.bSkill8:
			b.damage(8,0);
			refreshScreen();
			break;
		
		case R.id.bSkill9:
			b.damage(9,0);
			refreshScreen();
			break;
		
		case R.id.bSkill10:
			b.damage(10,0);
			refreshScreen();
			break;
		
		case R.id.bSkill11:
			b.damage(11,0);
			refreshScreen();
			break;
		case R.id.bSkill12:
			b.damage(12,0);
			refreshScreen();
			break;
		
		case R.id.bSkill13:
			b.damage(13,0);
			refreshScreen();
			break;
		
		case R.id.bSkill14:
			b.damage(14,0);
			refreshScreen();
			break;
		
		case R.id.bSkill15:
			b.damage(15,0);
			refreshScreen();
			break;
			
		//*/
			//String s="";
			
			/*int rgACheckedId = rgAttacks.getCheckedRadioButtonId();
		
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
			
			if(Integer.valueOf(hps[1])<0){
		  		  
				Toast t = Toast.makeText(getApplicationContext(), "Computer fainted", Toast.LENGTH_LONG);
				t.show();
				finish();
				break;
			}
			
			hps = (Integer[])b.initialize("");
			pbPHp.setProgress((int)((hps[0]*100)/maxhps[0]));
			
		  	  if(Integer.valueOf(hps[0])<0){
		  		Toast t = Toast.makeText(getApplicationContext(), PName+" fainted", Toast.LENGTH_LONG);
				t.show();
				finish();
		  		break;
		  	 
		  	  }
	*/
			
			
			
			
			
		//*/	
		}
		
	}
	
	public void refreshScreen(){
		pbPHp.setProgress((int)((b.p1hp*100)/b.maxp1hp));
		pbCHp.setProgress(((b.p2hp*100/b.maxp2hp)));
		
		tvPLevel.setText(String.valueOf(sa.lvl)+ "\t" + b.p1hp+"/"+b.maxp1hp+"\t   "+ b.p1mana+"/"+b.maxp1mana);
		tvCpLevel.setText(getIntent().getStringExtra("oppLevel")+"\t"+b.p2hp+"/"+b.maxp2hp+"\t   "+b.p2mana+"/"+b.maxp2mana);//comPl.lvl));
		
		
		tvFStat.setText("Player 1 damaged Player 2 by "+String.valueOf(b.p1damage)
				+"\nP2 new HP = "+b.p2hp+"\ndamage = "+b.p1damage);
	}
	
	public Integer[] setPlaMinAtts(){
		Integer[] ItemAtts = new Integer[]{0,0,0,0,0,0,0,0};
		for(int i=0;i<4;i++){
			String[] itmAtts=sa.getAllItms(String.valueOf(sa.eqInv[i]));
			if(itmAtts[i]!=null){
			for(int j=3;j<itmAtts.length-4;j++)
				ItemAtts[j-3]=ItemAtts[j-3]+Integer.valueOf(itmAtts[j]);
			}
		}
		
		
		return ItemAtts;
	}
	
	public Integer[] setOppMinAtts(){
		Integer[] ItemAtts = new Integer[]{0,0,0,0,0,0,0,0};
		for(int i=0;i<4;i++){
			String[] itmAtts=sa.getAllItms(String.valueOf(sa.oppInv[i]));
			if(itmAtts[i]!=null){
			for(int j=3;j<itmAtts.length-4;j++)
				ItemAtts[j-3]=ItemAtts[j-3]+Integer.valueOf(itmAtts[j]);
			}
		}
		return ItemAtts;
	}
	
	public void calcAtts(Player p){
		for(int i=0;i<p.eqInv.length;i++){
			
		}	
	}
	
	public void oldFxn(){
		/*
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
		*/
	}
	
	
	
}
