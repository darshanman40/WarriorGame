package com.darshan.warriorgame;

import android.app.Application;
import android.content.Context;

public class SharingAtts extends Application {

	Context c;
	String playerClass;
	String name;
	int id,lvl;
	double str,speed,maxHp,maxMana,maxXp,hp,mana,xp,gold; 
	Integer[] inv = new Integer[8];
	Integer[] eqInv = new Integer[4];
	Integer[] poInv = new Integer[2];
	Integer[] skills;
	Integer[] skilllvl;
	
	public void setId(int id){
		this.id=id;
	}
	
	public void setSkills(String[] skil, String[] sLvl){
		skills = new Integer[skil.length];
		skilllvl = new Integer[sLvl.length-1];
		for(int i=0;i<skil.length;i++){
			//String[] b = skil[i].split("");
			//if(!(skil[i].trim()))
			try{
				skills[i]=Integer.valueOf(skil[i].trim());
			}catch(Exception e){
				//i++;
			}
		}
		for(int i=1;i<sLvl.length;i++){
			//String[] a=sLvl[i].split("");
			//if(!(sLvl[i]).contentEquals(""))
			try{
				skilllvl[i-1]=Integer.valueOf(sLvl[i]);
			}catch(Exception e){
				//i++;
			}
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
		for(int i=0;i<inv.length;i++)
			inv[i]=Integer.valueOf(allInv[i+1]);
		for(int i=0;i<eqInv.length;i++)
			eqInv[i]=Integer.valueOf(allInv[i+9]);
		for(int i=0;i<poInv.length;i++)
			poInv[i]=Integer.valueOf(allInv[i+4+9]);		
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
	
	public void getPlaSkills(){
		
		
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
