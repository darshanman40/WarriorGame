package com.darshan.warriorgame;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SkillUp extends Activity implements OnClickListener{

	ImageView PS1,PS2,PS3,PS4,PS5,PS6,PS7,MS1,MS2,MS3,MS4,MS5,MS6,MS7,MS8;
	TextView Stats, Detail,P1,P2,P3,P4,P5,P6,P7,P8,P9,M1,M2,M3,M4,M5,M6,M7,M8,M9,M10;
	TextView[] skilLvl;// = {P1,P2,P3,P4,P5,P6,P7,P8,P9,M1,M2,M3,M4,M5,M6,M7,M8,M9,M10};
	int[] onSkills = {R.drawable.stab_act,R.drawable.double_strike_act, R.drawable.speed_strike_act,
				R.drawable.vertical_strike_act, R.drawable.avenger_act,R.drawable.split_act,
				R.drawable.execution_act, R.drawable.shuriken_act, R.drawable.energy_shot_act,
				R.drawable.blast_fire_act, R.drawable.charge_act, R.drawable.heal_act,
				R.drawable.mana_bomb_act, R.drawable.shadow_strike_act, R.drawable.annihilate_act
	};
	int[] prereqSkills = {0,1001,1001,1011,1021,1021,1041,0,2001,2001,2012,2022,2011,2032,2031};
	Button bSkillUp,bCont;
	SharingAtts sa;
	int selectedSId;
	ItemTest it;
	ImageView[] lskills;
	String[] skillsDetails;
	final String REMSPTS = "Remaining Skill Points";
	final String CLVL = "Current Level";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skillsup);
		selectedSId =-1;
		sa = ((SharingAtts)getApplication());
		PS1 = (ImageView)findViewById(R.id.ivStab2);
		PS2 = (ImageView)findViewById(R.id.ivDStrike2);
		PS3 = (ImageView)findViewById(R.id.ivSStrike2);
		PS4 = (ImageView)findViewById(R.id.ivVStrike2);
		PS5 = (ImageView)findViewById(R.id.ivAvenger2);
		PS6 = (ImageView)findViewById(R.id.ivSplit2);
		PS7 = (ImageView)findViewById(R.id.ivExecution2);
		
		MS1 = (ImageView)findViewById(R.id.ivShuriken2);
		MS2 = (ImageView)findViewById(R.id.ivEShot2);
		MS3 = (ImageView)findViewById(R.id.ivBFire2);
		MS4 = (ImageView)findViewById(R.id.ivCharge2);
		MS5 = (ImageView)findViewById(R.id.ivHeal2);
		MS6 = (ImageView)findViewById(R.id.ivMBomb2);
		MS7 = (ImageView)findViewById(R.id.ivShStrike2);
		MS8 = (ImageView)findViewById(R.id.ivAnhi2);
		
		lskills = new ImageView[]{PS1,PS2,PS3,PS4,PS5,PS6,PS7,MS1,MS2,MS3,MS4,MS5,MS6,MS7,MS8}; 
		for(int i=0; i<lskills.length;i++){
			lskills[i].setOnClickListener(this);
		}
		
		refreshScreen();
		/*
		P2 = (TextView)findViewById(R.id.tvPS2);
		P3 = (TextView)findViewById(R.id.tvPS3);
		P4 = (TextView)findViewById(R.id.tvPS4);
		P5 = (TextView)findViewById(R.id.tvPS5);
		P6 = (TextView)findViewById(R.id.tvPS6);
		P7 = (TextView)findViewById(R.id.tvPS7);
		P8 = (TextView)findViewById(R.id.tvPS8);
		P9 = (TextView)findViewById(R.id.tvPS9);
		M2 = (TextView)findViewById(R.id.tvMS2);
		M3 = (TextView)findViewById(R.id.tvMS3);
		M4 = (TextView)findViewById(R.id.tvMS4);
		M5 = (TextView)findViewById(R.id.tvMS5);
		M6 = (TextView)findViewById(R.id.tvMS6);
		M7 = (TextView)findViewById(R.id.tvMS7);
		M8 = (TextView)findViewById(R.id.tvMS8);
		M9 = (TextView)findViewById(R.id.tvMS9);
		M10 = (TextView)findViewById(R.id.tvMS10);
		*/
		Detail = (TextView)findViewById(R.id.tvDet5);
		Stats = (TextView)findViewById(R.id.tvStat5);
		bSkillUp = (Button)findViewById(R.id.bBoostSkill2);
		bCont = (Button)findViewById(R.id.bContinue2);
		
		bCont.setOnClickListener(this);
		PS2.setOnClickListener(this);
		MS2.setOnClickListener(this);
		if(sa.remSkilPts>0){
			bSkillUp.setOnClickListener(this);
		}
		//lskills = new ImageView[]{PS1,PS2,PS3,PS4,PS5,PS6,PS7,MS1,MS2,MS3,MS4,MS5,MS6,MS7,MS8};
		//selectedSId=0;
		//Detail.setText(String.valueOf(sa.remSkilPts));
		Detail.setText("");
		Stats.setText(REMSPTS+" = "+sa.remSkilPts+"\n");
		
		it = new ItemTest();
		
		
		skillsDetails = it.printData("skilldet");
		
	}
	void refreshScreen(){
		for(int i=0; i<lskills.length;i++){
			if(sa.skilllvl[i]>0)
				lskills[i].setImageResource(onSkills[i]);
		}
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	
		Stats.setText(REMSPTS+" = "+sa.remSkilPts+"\n");
		int skillIndex = 0;
		if(arg0.getId()==R.id.bContinue2){
			finish();
		}else if(arg0.getId()==R.id.bBoostSkill2){
			int skill = prereqSkills[selectedSId];
			for(int i=0;i <sa.skills.length;i++){
				if(skill==sa.skills[i]){
					skillIndex = i;
					break;
				}
			}
			
			if(selectedSId!=-1 && sa.remSkilPts>0 && sa.skilllvl[skillIndex]>0){
				sa.skilllvl[selectedSId]+=1;
				sa.remSkilPts-=1;
				if(sa.remSkilPts<1)
					bSkillUp.setClickable(false);
				if(selectedSId > -1){
					Stats.setText(REMSPTS+" = "+sa.remSkilPts+"\n");
					Stats.append(CLVL+" = "+sa.skilllvl[selectedSId]);
				}
			}
			Log.d("skillIndex",skillIndex+"");
			
		}else{
			for(int i=0; i<lskills.length;i++){
				if(lskills[i].getId()==arg0.getId()){
					selectedSId = i;
					break;
				}
				
			}
			
			if(selectedSId!=-1){
				Stats.append(CLVL+" = "+sa.skilllvl[selectedSId]);
				Detail.setText(skillsDetails[selectedSId].replace(".", "\n"));
			}
		}
		refreshScreen();
		
		/*
		switch(arg0.getId()){
		case R.id.bSkillUp:
			//if((selectedSId-1)>=0)
			sa.skilllvl[selectedSId]++;
			break;
			
		case R.id.ivPhS2:
			selectedSId = 1;
			Detail.setText("Double Strike\nRequired Mana 20\n Next lvl 8% extra physical damage");
			break;
		case R.id.ivMgS2:
			selectedSId = 10;
			Detail.setText("Energy Shot\nRequired Mana 25\n Next lvl 49 extra magical damage");
			break;
		}
		*/
		/*
		if(arg0.getId()==R.id.bBoostSkill){
			if(selectedSId!=-1){
				sa.skilllvl[selectedSId]+=1;
				sa.remSkilPts-=1;
				if(sa.remSkilPts<1)
					bSkillUp.setClickable(false);
			}
			else
				Toast.makeText(getApplicationContext(), "Please select Skill", Toast.LENGTH_SHORT).show();
		}else if(arg0.getId()==R.id.ivPhS2){
			selectedSId = 1;
			Detail.setText("Double Strike\nRequired Mana 20\n Next lvl 8% extra physical damage");
		}else if(arg0.getId()==R.id.ivMgS2){
			selectedSId = 10;
			Detail.setText("Energy Shot\nRequired Mana 25\n Next lvl 49 extra magical damage");
		}else if(arg0.getId()==R.id.bContinue){
			finish();
		}
		if(selectedSId!=-1)
			skilLvl[selectedSId].setText(sa.skilllvl[selectedSId].toString());
		if(sa.remSkilPts<1){
			bSkillUp.setClickable(false);
		}
		
		Toast.makeText(getApplicationContext(),"rem Skill points= "+sa.remSkilPts, Toast.LENGTH_SHORT).show();
		//finish();
	*/
	}

	

}
