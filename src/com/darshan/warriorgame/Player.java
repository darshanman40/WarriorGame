package com.darshan.warriorgame;

import java.lang.reflect.Method;
import java.util.Random;

public class Player {
	String playerClass;
	String name;
	int id,lvl;
	double str,speed,maxHp,maxMana,skill; 
	Integer[] inv = new Integer[8];
	Integer[] eqInv = new Integer[4];
	Integer[] poInv = new Integer[2];
	
	Warrior war = new Warrior();
	
	public void newPlayer(String name, String pClass){
		
	}

	
	
	/*
		//real Player
		public Player(String name,int id, int lvl, Warrior x,String pc, Integer[] majAtt){
			this.name = name;
			this.id = id;
			this.lvl = lvl;
			this.war = x;
			this.playerClass=pc;
			
			str=majAtt[0];
			speed = majAtt[1];
			maxHp = majAtt[2];
			maxMana = majAtt[3];
			skill = majAtt[4];
		}
		
		public Player(String name,int id, int lvl, Warrior x,String pc, Integer[] majAtt, Integer[] inv, Integer[] eqInv, Integer[] poInv){
			this.name = name;
			this.id = id;
			this.lvl = lvl;
			this.war = x;
			this.playerClass=pc;
		
			str=majAtt[0];
			speed = majAtt[1];
			maxHp = majAtt[2];
			maxMana = majAtt[3];
			skill = majAtt[4];
			for(int i=0;i<2;i++){
				this.inv[i]=inv[i];
				this.eqInv[i]=eqInv[i];
				this.poInv[i]=poInv[i];
			}
			for(int i=2;i<4;i++){
				this.inv[i]=inv[i];
				this.eqInv[i]=eqInv[i];
			}
			for(int i=4;i<8;i++){
				this.inv[i]=inv[i];
			}
				
			
		}
		//Computer Player
		public Player(int lvl, Warrior x,String pc){
			this.name="Computer";
			this.id = 0;
			this.lvl = lvl;
			this.war = x;
			this.playerClass=pc;
		}
		public int ranPick(){
			Random ran;

			if(lvl>=15&&lvl<20){
				ran = new Random();
				ran.nextInt(3);
	    	}else if(lvl>=10 && lvl<15){
	    		ran = new Random();
	    		return ran.nextInt(2);
	    	}else if(lvl>=5&&lvl<10){
	    		ran = new Random();
	    		return ran.nextInt(1);
	    	}
			return 1;
		}
		
		public Integer[] hellyeah(Warrior war){
			
			Integer[] retobj= new Integer[10];
				try {
		           		Class cls = Class.forName("com.darshan.warriorgame.Warrior");
		           		Class argType[]=new Class[1];
		           		argType[0]=Integer.TYPE;
		           		Method meth = cls.getMethod(playerClass,argType);	
			           
			            retobj= (Integer[]) meth.invoke(war,lvl);
			          
		         }
		         catch (Throwable e) {
		            System.err.println(e);
		         }
				 return (Integer[])retobj; 
			}
		
		public Integer[] pInv(){
			Integer[] inve = new Integer[8];
			
			
			return inve;
		}
		
		public Integer[] eInv(){
			Integer[] inve = new Integer[4];
			
			
			return inve;
		}
	*/	
}
