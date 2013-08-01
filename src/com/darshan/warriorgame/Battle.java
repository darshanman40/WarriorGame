package com.darshan.warriorgame;

//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Scanner;

import android.content.Context;

import com.darshan.warriorgame.Warrior;

public class Battle {
	int p1ACo = 2;
	int p2ACo = 2;
	int Plvl,Clvl;
	String CClass, PClass;
	Integer[] pla1att;
	Integer[] pla2att;
	double p1hp,p2hp,p1maxHp,p2maxHp;
	Warrior player;//, computer ;
	SharingAtts sa;
	Player pl,com;
	Context c;
	
	public Battle(Context c,SharingAtts sa, Player com){
		this.sa=sa;
		this.com = com;
		this.c=c;
		this.pl = new Player(c,sa.getMajatt(),sa.getAllInv(),sa.getAllSkillsLvl(),sa.name,sa.playerClass,sa.id);
		this.com = new Player(c,com.id);
		//setAllAtts();
		
	}
	
	//---------------------------  set-type functions------------------------------------------------- 
	//str,ph_dam,mag_dam,ph_def,mag_def,e_s_dam,s_hp,b_mana,speed
	//size = 9   //  last index = 8
	
	public void setAllAtts(){
		pla1att = pl.calcAtts();
		pla2att = com.calcAtts();
		//
		p1maxHp=Double.valueOf(pl.maxHp);
		p2maxHp=Double.valueOf(this.com.maxHp);
		p1hp=p1maxHp;
		p2hp=p2maxHp;
	}
	
	//---------------------------- p1 set-type functions--------------------------------------------------
	public void setp1hp(){
		double damage=Double.valueOf(pla1att[1]-(pla2att[3]/10));
		p1hp=p1hp-damage;
		
	}
	
	//---------------------------- p2 set-type functions--------------------------------------------------
	public void setp2hp(){
		double damage=Double.valueOf(pla2att[1]-(pla1att[3]/10));
		p2hp=p2hp-damage;
		
	}
	
	//----------------------------  p1 get-type functions--------------------------------------------------
	
	public Integer[] getPla1atts(){
		return pla1att;
	}
	
	public String getp1Name(){
		return pl.name;
	}
	
	public String[] getp1Atts(){
		return pl.getMajAtts();
	}
	
	public double getp1hp(){
		return p1hp;
	}
	
	
	//-------------------------------  p2 get-type functions----------------------------------------
	
	public String getp2Name(){
		return com.name;
	}
	
	public String[] getp2Atts(){
		return com.getMajAtts();
	}
	
	public double getp2hp(){
		return p2hp;
	}
	
	public Integer[] getPla2atts(){
		return pla2att;
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	public Battle(int Plvl, int Clvl, String CClass, String PClass){
		this.Plvl = Plvl;
		this.Clvl = Clvl;
		this.CClass = CClass;
		this.PClass = PClass;
		pla1att = new Integer[7];
		pla2att = new Integer[7];
	  	  player = new Warrior();
	  	  computer= new Warrior();
	  	//  Com = new Player(Plvl,computer,CClass);
	  	 // Osairas = new Player("Osairas",1,Plvl,player,PClass);
	  	 
	  	//  pla1att = Osairas.hellyeah(player);
	  	 // pla2att = Com.hellyeah(computer);
	    	  
	}
	
	Integer[] playerData(){
		
		
		return pla1att;
	}
	
	Integer[] initialize(){
	Integer[] hps = new Integer[2];
		hps[0] = pla1att[0];
		hps[1] = pla2att[0];
		return hps;
	}
	Integer[] initialize(int aCode){
  	 
  	 
  	  	  p1ACo = aCode;
  		
  		  pla2att[0] = newHp(pla1att,pla2att,p1ACo);
  		 
  		  Integer[] hps = new Integer[2];
  		 
  		  hps[1] = pla2att[0];
  		
  	  return hps;
    	}
	Integer[] initialize(String aCode){
	  	 
	  	 
	  	  
		 /// p2ACo = Com.ranPick();
		  pla1att[0] = newHp(pla2att,pla1att,p2ACo);
		  Integer[] hps = new Integer[2];
		  hps[0] = pla1att[0];
		  
	  return hps;
  	}
	
	public int newHp(Integer[] attacker, Integer[] defender, int playerAttack){
		
			
		int hp =Integer.valueOf(defender[0]);
		int damage = Integer.valueOf(defender[2]) - (Integer.valueOf(attacker[playerAttack+2])*3);
		
		
		if(damage<0)
			hp = hp-((damage*(-1)));
		else
			hp = hp-(damage);
		return hp;
		
	}
	*/
 }

