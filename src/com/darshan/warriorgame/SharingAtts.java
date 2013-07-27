package com.darshan.warriorgame;

import android.app.Application;

public class SharingAtts extends Application {

	String playerClass;
	String name;
	int id,lvl;
	double str,speed,maxHp,maxMana,maxXp,hp,mana,xp,gold; 
	Integer[] inv = new Integer[8];
	Integer[] eqInv = new Integer[4];
	Integer[] poInv = new Integer[2];
	Integer[] skills;
	Integer[] skilllvl;
	
	public void setSkills(String[] skil, String[] sLvl){
		skills = new Integer[skil.length];
		skilllvl = new Integer[skil.length];
		for(int i=0;i<skil.length;i++){
			skills[i]=Integer.valueOf(skil[i]);
		//}
		//for(int i=0;i<sLvl.length;i++){
			skilllvl[i]=Integer.valueOf(sLvl[i]);
		}
	}
	public void setName(String n){
		name=n;
	}
	public void setPlaAtts(String[] att){
		id=Integer.valueOf(att[0]);
		name=att[1];
		playerClass=att[2];
		lvl=Integer.valueOf(att[3]);
		str=Double.valueOf(att[4]);
		speed=Double.valueOf(att[5]);
		maxHp=Double.valueOf(att[6]);
		maxMana=Double.valueOf(att[7]);
		maxXp=Double.valueOf(att[8]);
		hp=Double.valueOf(att[9]);
		mana=Double.valueOf(att[10]);
		xp=Double.valueOf(att[11]);
		gold=Double.valueOf(att[12]);
	}
	
	public void setAllInv(String[] allInv){
		for(int i=0;i<inv.length;i++)
			inv[i]=Integer.valueOf(allInv[i]);
		for(int i=0;i<eqInv.length;i++)
			eqInv[i]=Integer.valueOf(allInv[i+8]);
		for(int i=0;i<poInv.length;i++)
			poInv[i]=Integer.valueOf(allInv[i+4+8]);		
	}
	//--------------------------------------------------------------------------
	
	
	
	
	public void setAllSkills(Integer[] skill, Integer[] skilllvl){
		for(int i=0; i<skill.length;i=i+10){
			skills[i]=skill[i];
		}
		for(int i=0;i<skilllvl.length;i++)
			this.skilllvl[i]=skilllvl[i];
		//}
			
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public String getNameOnly(){
		return name;
	}
	public double[] getMajatt(){
		double[] majAtts = new double[9];
		majAtts[0]=str;
		majAtts[1]=speed;
		majAtts[2]=maxHp;
		majAtts[3]=maxMana;
		majAtts[4]=maxXp;
		majAtts[5]=hp;
		majAtts[6]=mana;
		majAtts[7]=xp;
		majAtts[8]=gold;
		
		return majAtts;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPClass(){
		return playerClass;
	}
	
	public Integer getSkillLevel(Integer skillId){
		int i=0;
		while(skills[i]!=skillId)
			i++;
		return skilllvl[i];
	}
	
	public Integer[] getAllSkills(){
		return skills;
	}
	
	public Integer[] getAllSkillsLvl(){
		return skilllvl;
	}
	
	public Integer[] getAllInv(){
		Integer[] allInv = new Integer[14];
		for(int i=0;i<inv.length;i++)
			allInv[i]=inv[i];
		for(int i=0;i<eqInv.length;i++)
			allInv[i+inv.length]=eqInv[i];
		for(int i=0;i<poInv.length;i++)
			allInv[i+inv.length+eqInv.length]=poInv[i];
		return allInv;
	}
	
	/*
	SharingAtts appState = ((SharingAtts)getApplicationContext());
	String x = appState.getState();
	appState.setState(x);
	*/
	
	/*
	public String string1;
	public String getState(){
		return string1;
	}
	public void setState(String input){
		this.string1=input;
	}
	*/
}
