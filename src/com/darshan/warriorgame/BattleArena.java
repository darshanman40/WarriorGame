package com.darshan.warriorgame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BattleArena extends Activity implements OnClickListener{
	
	//SharedPreferences someData;
	//Editor editor;
	String filename;
	
	ProgressBar pbCHp,pbPHp;
	RadioGroup rgAttacks;
	TextView tvCpClass,tvCpLevel,tvPClass,tvPLevel, tvPName, tvFStat ;
	Button attack;
	/*
	int CpLevel, PLevel;
	int aCode;
	String CpClass,PClass,PName;
	*/
	//Battle b;
	Integer[] hps, maxhps;
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
		//comId = getIntent().getIntExtra("comId", 0);
		//comPl = new Player(this,comId);
		//pl = new Player(this,sa.getMajatt(),sa.getAllInv(),sa.getAllSkillsLvl(),sa.name,sa.playerClass,sa.id);
		
		
		pbPHp = (ProgressBar)findViewById(R.id.pbPHp);
		pbCHp = (ProgressBar)findViewById(R.id.pbComHp);
		attack = (Button)findViewById(R.id.bAttack);
		//rgAttacks = (RadioGroup) findViewById(R.id.rgattacks);
		tvCpClass = (TextView) findViewById(R.id.tvCpClass);
		tvCpLevel = (TextView) findViewById(R.id.tvCpLevel);
		tvPClass = (TextView) findViewById(R.id.tvPClass);
		tvPLevel = (TextView) findViewById(R.id.tvPLevel);
		tvPName = (TextView) findViewById(R.id.tvPName);
		tvFStat = (TextView) findViewById(R.id.tvFStat);
		
		//b= new Battle(this,pl,comPl);
		/*
		for(int i = 0; i<sa.eqInv.length;i++)
			b.setAllAtts(sa.getAllItms(String.valueOf(pl.eqInv[i])), sa.getAllItms(String.valueOf(comPl.eqInv[i])));
		//*/
		//p1name = b.getp1Name();
		//p2name = b.getp2Name();
		/*
		pbPHp.setProgress((int)(sa.hp/sa.maxHp)*100);
		pbCHp.setProgress((int)(comPl.hp/comPl.maxHp)*100);
		tvCpClass.setText(comPl.playerClass);
		tvCpLevel.setText(String.valueOf(comPl.lvl));
		tvPClass.setText(sa.playerClass);
		tvPName.setText(sa.name);
		tvPLevel.setText(String.valueOf(sa.lvl));
		*/
		attack.setOnClickListener(this);
			
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bAttack:
			//tvFStat.setText(b.pla2att[0].toString());
			//tvFStat.setText(String.valueOf(comPl.eqInv[0]));
			
			///for(int i = 0; i<sa.eqInv.length;i++)
			/*
				String[] s =sa.getAllItms(String.valueOf(comPl.eqInv[1]));
			String m="";
			for(int i=0;i<s.length;i++)
				m=m+s[i]+"\n";
				*/
			String s="";
			for(int i=0;i<sa.oppAtts.length;i++)
				s=s+String.valueOf(sa.oppAtts[i])+"  ";
			s=s+"\n";
			for(int i=0; i<sa.oppInv.length;i++)
				s=s+String.valueOf(sa.oppInv[i])+"  ";
			s=s+"\n";
			for(int i=0;i<sa.oppSkilllvl.length;i++)
				s=s+String.valueOf(sa.oppSkilllvl[i])+"  ";
			
			tvFStat.setText(s);
			break;
		//*/
		
		
		/*
		case R.id.bAttack:
			int rgACheckedId = rgAttacks.getCheckedRadioButtonId();
		
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

			
			
			break;
			
			
		*/	
		}
		
		
		
		
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
