package com.darshan.warriorgame;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import com.darshan.warriorgame.Warrior;

public class Battle {
	int p1ACo = 2;
	int p2ACo = 2;
	int Plvl,Clvl;
	String CClass, PClass;
	Integer[] pla1att;
	Integer[] pla2att;
	Warrior player, computer ;
	Player Com,Osairas;
	
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
	
 }

