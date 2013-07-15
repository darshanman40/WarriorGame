package com.darshan.warriorgame;

import java.lang.reflect.Method;
import java.util.Random;

public class Player {


	String playerClass;
	String name;
	int id;
	int lvl;
	Warrior war = new Warrior();
	
		//real Player
		public Player(String name,int id, int lvl, Warrior x,String pc){
			this.name = name;
			this.id = id;
			this.lvl = lvl;
			this.war = x;
			this.playerClass=pc;
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
			Object[] retval2 = new Object[8];
			Integer[] retobj= new Integer[10];
				try {
		           		Class cls = Class.forName("com.darshan.warriorgame.Warrior");
		           		Class argType[]=new Class[1];
		           		argType[0]=Integer.TYPE;
		           		Method meth = cls.getMethod(playerClass,argType);	
			            //retobj =new Integer[10]; 
			            retobj= (Integer[]) meth.invoke(war,lvl);
			          //  retval2 = retobj;
			           // System.out.println("Player class "+retval2[0]+"\n");
		         }
		         catch (Throwable e) {
		            System.err.println(e);
		         }
				 return (Integer[])retobj; 
			}
		
}
