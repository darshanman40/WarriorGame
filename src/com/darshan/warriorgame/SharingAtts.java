package com.darshan.warriorgame;

import java.util.Hashtable;

import android.app.Application;
import android.content.Context;

public class SharingAtts extends Application {

	Context c;
	String playerClass;
	String name;
	int id,lvl;
	double str,speed,maxHp,maxMana,maxXp,initMana,initSpeed,hp,mana,xp,gold,remSkilPts; 
	Integer[] inv;
	Integer[] eqInv;
	Integer[] poInv;
	Integer[] skills = new Integer[]{1001,1011,1012,1021,1022,1031,1041,1042,1051,2001,2011,2012,2021,2022,2031,2032,2041,2042,2051};
	Integer[] goldToWin = new Integer[]{};
	Integer[] skilllvl;
	Integer[] oppInv;
	Integer[] oppSkilllvl;
	Integer[] oppMajAtts;
	Integer[] oppMinAtts;
	
	Hashtable<String,String[]> allSkills;
	Hashtable<String,String[]> allItms;
	
	public void setPlaInv(Integer[] inv){
		this.inv=inv;
	}
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
		initSpeed=Double.valueOf(att[6]);
		maxHp=Double.valueOf(att[7]);
		initMana=Double.valueOf(att[8]);
		maxXp=Double.valueOf(att[9]);
		hp=Double.valueOf(att[10]);
		mana=Double.valueOf(att[11]);
		xp=Double.valueOf(att[12]);
		gold=Double.valueOf(att[13]);
		remSkilPts = Double.valueOf(att[14]);
	}
	
	public void setPlaMajAtts(Integer[] att){
		
		str=Double.valueOf(att[0]);
		initSpeed=Double.valueOf(att[1]);
		maxHp=Double.valueOf(att[2]);
		initMana=Double.valueOf(att[3]);
	}
	
	public void setAllInv(String[] allInv){
		inv = new Integer[8];
		eqInv = new Integer[4];
		poInv = new Integer[2];
		
		if(allInv.length==14){
		for(int i=0;i<8;i++)
			inv[i]=Integer.valueOf(allInv[i]);
		for(int i=0;i<4;i++)
			eqInv[i]=Integer.valueOf(allInv[i+8]);
		for(int i=0;i<2;i++)
			poInv[i]=Integer.valueOf(allInv[i+4+8]);		
	}else{
		for(int i=1;i<9;i++)
			inv[i-1]=Integer.valueOf(allInv[i]);
		for(int i=1;i<5;i++)
			eqInv[i-1]=Integer.valueOf(allInv[i+8]);
		for(int i=1;i<3;i++)
			poInv[i-1]=Integer.valueOf(allInv[i+4+8]);
	
		}
	}
	//--------------------------------------------------------------------------
	
	
	
	
	public void setAllSkills(Integer[] skilllvl){
		
		for(int i=0;i<skilllvl.length;i++)
			this.skilllvl[i]=skilllvl[i];		
	}
	
	public void setAllItms(Hashtable<String,String[]> allItms){
		this.allItms= new Hashtable<String,String[]>();
		this.allItms = allItms;
	}
	
	public void setAllSkills(Hashtable<String,String[]> allSkills){
		this.allSkills= new Hashtable<String,String[]>();
		this.allSkills = allSkills;
	}
	
	//--------------Set Opp Data
	public void setMajOppAtts(Hashtable<String,String[]> oppAtts){
		this.oppMajAtts = new Integer[6];
		String[] sm;
		for(String s: oppAtts.keySet()){
			sm = oppAtts.get(s);
			this.oppMajAtts[0] = Integer.valueOf(sm[5]);
			this.oppMajAtts[1] = Integer.valueOf(sm[6]);
			this.oppMajAtts[2] = Integer.valueOf(sm[7]);
			this.oppMajAtts[3] = Integer.valueOf(sm[8]);
			this.oppMajAtts[4] = Integer.valueOf(sm[9]);
			this.oppMajAtts[5] = Integer.valueOf(sm[13]);
		}
	}
	
	public void setMinOppAtts(Hashtable<String,String[]> oppAtts){
		this.oppMinAtts = new Integer[6];
		String[] sm;
		for(String s: oppAtts.keySet()){
			sm = oppAtts.get(s);
			this.oppMinAtts[0] = Integer.valueOf(sm[5]);
			this.oppMinAtts[1] = Integer.valueOf(sm[6]);
			this.oppMinAtts[2] = Integer.valueOf(sm[7]);
			this.oppMinAtts[3] = Integer.valueOf(sm[8]);
			this.oppMinAtts[4] = Integer.valueOf(sm[9]);
			this.oppMinAtts[5] = Integer.valueOf(sm[13]);
		}
	}
	
	public void setOppInv(Hashtable<String,String[]> oppInv){
		String[] sm;
		this.oppInv = new Integer[6];
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
	
	public Integer[] getMajatt(){
		updateStat();
		Integer[] majAtts = new Integer[10];
		majAtts[0]=(int)str;
		majAtts[1]=(int)speed;
		majAtts[2]=(int)maxHp;
		majAtts[3]=(int)maxMana;
		majAtts[4]=(int)maxXp;
		majAtts[5]=(int)hp;
		majAtts[6]=(int)mana;
		majAtts[7]=(int)xp;
		majAtts[8]=(int)gold;
		majAtts[9]=(int)remSkilPts;
		return majAtts;
	}
	
	public Integer[] getPlaUpatt(){
		updateStat();
		Integer[] majAtts = new Integer[9];
		majAtts[0]=(int)str;
		majAtts[1]=(int)initSpeed;
		majAtts[2]=(int)maxHp;
		majAtts[3]=(int)initMana;
		//majAtts[4]=(int)maxXp;
		
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
	public Integer[] getBattleInv(){
		Integer[] Inv = new Integer[6];
		for(int i=0;i<eqInv.length;i++)
			Inv[i]=eqInv[i];
		for(int i=0;i<poInv.length;i++)
			Inv[i+eqInv.length]=poInv[i];
		return Inv;
	}
	//--------------------------------------------------------------------------
	
	//public void getPlaAtts(){
		
	//}
	
	public String[] getAllItms(String sid){
		//allSkills.
		if(!sid.equals("0")||!sid.equals("0 ")||!sid.equals(" 0")||!sid.equals(" 0 "))
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
	
	public String getSkillName(String id){
		String[] detail = allSkills.get(id);
		
		return detail[1];
	}
	public String getSkilAcc(String id){
		String[] detail = allSkills.get(id);
		
		return detail[3];
		
	}
	
	
	public void updateStat(){
		//BattleArena ba = new BattleArena();
		//ba.dumbass();
		//double oldMana = maxMana;
		Integer[] atts= setPlaMinAtts();
		maxMana = initMana+atts[6];
		speed = initSpeed + atts[7];
		if(mana>maxMana){
			mana=maxMana;
		}
	}
	
	public Integer[] setPlaMinAtts(){
		Integer[] ItemAtts = new Integer[]{0,0,0,0,0,0,0,0};
		for(int i=0;i<4;i++){
			String[] itmAtts=getAllItms(String.valueOf(eqInv[i]));
			if(itmAtts!=null){
				for(int j=3;j<itmAtts.length-4;j++)
					ItemAtts[j-3]=ItemAtts[j-3]+Integer.valueOf(itmAtts[j]);
			}
		}
		maxMana = initMana+ItemAtts[6];
		speed = initSpeed + ItemAtts[7];
		
		return ItemAtts;
	}
	
	public Integer[] setOppMinAtts(){
		Integer[] ItemAtts = new Integer[]{0,0,0,0,0,0,0,0};
		for(int i=0;i<4;i++){
			String[] itmAtts=getAllItms(String.valueOf(oppInv[i]));
			if(itmAtts[i]!=null){
			for(int j=3;j<itmAtts.length-4;j++)
				ItemAtts[j-3]=ItemAtts[j-3]+Integer.valueOf(itmAtts[j]);
			}
		}
		return ItemAtts;
	}
	public String processCheat(String cCode) {
		// TODO Auto-generated method stub
		if(cCode.equals("FeedMe")) {
			hp=maxHp;
			mana=maxMana;
			return ("hp= "+hp+" mana= "+mana);
		}
		
		return "Wrong Cheat Code";
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
