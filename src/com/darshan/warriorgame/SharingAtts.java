package com.darshan.warriorgame;

import java.util.Hashtable;

import android.app.Application;
import android.content.Context;

public class SharingAtts extends Application {

	Context c;
	String playerClass;
	String name;
	int id,lvl;
	double str,speed,maxHp,maxMana,maxXp,hp,mana,xp,gold; 
	Integer[] inv;// = new Integer[8];
	Integer[] eqInv;// = new Integer[4];
	Integer[] poInv;// = new Integer[2];
	Integer[] skills = new Integer[]{1001,1011,1012,1021,1022,1031,1041,1042,1051,2001,2011,2012,2021,2022,2031,2032,2041,2042,2051};
	Integer[] skilllvl;
	Integer[] oppInv;// = new Integer[6];
	Integer[] oppSkilllvl;// = new Integer[19];
	Integer[] oppAtts;
	
	Hashtable<String,String[]> allSkills;
	Hashtable<String,String[]> allItms;
	//Hashtable<String,String[]> oppAtts;
	//Hashtable<String,String[]> oppSkills;
	//Hashtable<String,String[]> oppInv;
	
	
	public void setId(int id){
		this.id=id;
	}

	
	public void setSkills(String[] sLvl){
		skilllvl = new Integer[sLvl.length-1];
		for(int i=1;i<sLvl.length;i++){
			try{
				skilllvl[i-1]=Integer.valueOf(sLvl[i]);
				}catch(Exception e){}
		}
	}
	
	public void setName(String n){
		name=n;
	}
	
	public void setPlaAtts(String[] att){
		id=Integer.valueOf(att[0]);
		name=att[1];
		playerClass=att[3];
		lvl=Integer.valueOf(att[4]);
		str=Double.valueOf(att[5]);
		speed=Double.valueOf(att[6]);
		maxHp=Double.valueOf(att[7]);
		maxMana=Double.valueOf(att[8]);
		maxXp=Double.valueOf(att[9]);
		hp=Double.valueOf(att[10]);
		mana=Double.valueOf(att[11]);
		xp=Double.valueOf(att[12]);
		gold=Double.valueOf(att[13]);
	}
	
	public void setAllInv(String[] allInv){
		inv = new Integer[8];
		eqInv = new Integer[4];
		poInv = new Integer[2];
		
		
		for(int i=0;i<8;i++)
			inv[i]=Integer.valueOf(allInv[i]);
		for(int i=0;i<4;i++)
			eqInv[i]=Integer.valueOf(allInv[i+8]);
		for(int i=0;i<2;i++)
			poInv[i]=Integer.valueOf(allInv[i+4+8]);		
	}
	//--------------------------------------------------------------------------
	
	
	
	
	public void setAllSkills(Integer[] skilllvl){
		
		for(int i=0;i<skilllvl.length;i++)
			this.skilllvl[i]=skilllvl[i];		
	}
	
	public void setAllItms(Hashtable<String,String[]> allItms){
		this.allItms = allItms;
	}
	
	public void setAllSkills(Hashtable<String,String[]> allSkills){
		this.allSkills = allSkills;
	}
	
	//--------------Set Opp Data
	public void setOppAtts(Hashtable<String,String[]> oppAtts){
		this.oppAtts = new Integer[5];
		String[] sm;
		for(String s: oppAtts.keySet()){
			sm = oppAtts.get(s);
			this.oppAtts[0] = Integer.valueOf(sm[5]);
			this.oppAtts[1] = Integer.valueOf(sm[6]);
			this.oppAtts[2] = Integer.valueOf(sm[7]);
			this.oppAtts[3] = Integer.valueOf(sm[8]);
			this.oppAtts[4] = Integer.valueOf(sm[9]);
		}
	}
	
	public void setOppInv(Hashtable<String,String[]> oppInv){
		String[] sm;
		this.oppInv = new Integer[6];
		//this.oppInv = new Integer[19];
		for(String s: oppInv.keySet()){
			sm = oppInv.get(s);
			for(int i=0; i<this.oppInv.length;i++){
				this.oppInv[i] = Integer.valueOf(sm[i+9]);
			}
		}
	}
	
	public void setOppSkills(Hashtable<String,String[]> oppSkills){
		String[] sm;
		this.oppSkilllvl = new Integer[19];
		for(String s: oppSkills.keySet()){
			sm = oppSkills.get(s);
			for(int i=0; i<this.oppSkilllvl.length;i++){
				this.oppSkilllvl[i] = Integer.valueOf(sm[i+1]);
			}
		}
		
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
	public int getId(){
		return id;
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
	//--------------------------------------------------------------------------
	
	public void getPlaAtts(){
		
	}
	
	public String[] getAllItms(String sid){
		//allSkills.
		if(!sid.equals("0"))
			return allItms.get(sid);
		else
			return new String[]{"0","0","0","0","0","0","0","0","0"};
	}
	
	public String[] getAllSkills(String sid){
		//allSkills.
		return allSkills.get(sid);
	}
	
	public void getPlaInv(){
		
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
