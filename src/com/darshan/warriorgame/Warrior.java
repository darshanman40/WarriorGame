package com.darshan.warriorgame;

public class Warrior {
float lvl;
	
	public Integer[] Samurai(int i){
		lvl=(float)i;

		double hp = 100 * (lvl*0.75);
		double mana = 100 * (lvl*0.25);
		double attack = 10 * (lvl*0.5);
		double defense = 10 * (lvl*0.75);
		double dodgerate = 10 * (lvl*0.25);
		double accuracy = 10 * (lvl*0.5);
		double exp = 200*lvl; 
		Integer[] att = new Integer[11];
       
    	att[0]=(int)hp;
    	att[1]=(int)mana;
    	att[2]=(int)accuracy;
    	att[3]=(int)defense;
    	att[4]=(int)dodgerate;
    	att[5]=(int)exp;
    	att[6]=(int)attack;
    	
    	
    	if(lvl >= 15 && lvl < 20){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    		att[10]=(int)attack+40;
    	}else if(lvl>=10 && lvl<15){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    	}else if(lvl>=5&&lvl<10){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    	}else if(lvl<5){
    		att[7]=(int)attack+25;
    	}else{
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    		att[9]=(int)attack+40;
    	} 
    	
    	return att;
    
	}
	
	public Integer[] Ninja(int i){
		lvl=(float)i;
		double hp = 100 * (lvl*0.5);
		double mana = 100 * (lvl*0.75);
		double attack = 10 * (lvl*0.75);
		double defense = 10 * (lvl*0.5);
		double dodgerate = 10 * (lvl*0.75);
		double accuracy = 10 * (lvl*0.5);
		double exp = 200*lvl; 
		Integer[] att = new Integer[11];
       
		att[0]=(int)hp;
    	att[1]=(int)mana;
    	att[2]=(int)accuracy;
    	att[3]=(int)defense;
    	att[4]=(int)dodgerate;
    	att[5]=(int)exp;
    	att[6]=(int)attack;
    	
    	//higher level attacks
    	if(lvl>=15&&lvl<20){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    		att[10]=(int)attack+40;
    	}else if(lvl>=10 && lvl<15){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    	}else if(lvl>=5&&lvl<10){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    	}else if(lvl<5){
    		att[7]=(int)attack+25;
    	}else{
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    		att[9]=(int)attack+40;
    	} 
    	
    	return att;
    	
    }
	
	public Integer[] Spearman(int i){
		lvl=(float)i;
		double hp = 100 * (lvl*0.75);
		double mana = 100 * (lvl*0.5);
		double attack = 10 * (lvl*0.5);
		double defense = 10 * (lvl*0.25);
		double dodgerate = 10 * (lvl*0.75);
		double accuracy = 10 * (lvl*0.75);
		double exp = 200*lvl; 
		Integer[] att = new Integer[11];
       
		att[0]=(int)hp;
    	att[1]=(int)mana;
    	att[2]=(int)accuracy;
    	att[3]=(int)defense;
    	att[4]=(int)dodgerate;
    	att[5]=(int)exp;
    	att[6]=(int)attack;
    	
    	//higher level attacks
    	if(lvl>=15&&lvl<20){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    		att[10]=(int)attack+40;
    	}else if(lvl>=10 && lvl<15){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    	}else if(lvl>=5&&lvl<10){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    	}else if(lvl<5){
    		att[7]=(int)attack+25;
    	}else{
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    		att[9]=(int)attack+40;
    	} 
    	
    	return att;
    }
	
	public Integer[] Archer(int i){
		lvl=(float)i;
		double hp = 100 * (lvl*0.25);
		double mana = 100 * (lvl*0.75);
		double attack = 10 * (lvl*0.5);
		double defense = 10 * (lvl*0.25);
		double dodgerate = 10 * (lvl*0.75);
		double accuracy = 10 * (lvl*0.75);
		double exp = 200*lvl; 
		Integer[] att = new Integer[11];
       
		att[0]=(int)hp;
    	att[1]=(int)mana;
    	att[2]=(int)accuracy;
    	att[3]=(int)defense;
    	att[4]=(int)dodgerate;
    	att[5]=(int)exp;
    	att[6]=(int)attack;
    	
    	//higher level attacks
    	if(lvl>=15&&lvl<20){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    		att[10]=(int)attack+40;
    	}else if(lvl>=10 && lvl<15){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    	}else if(lvl>=5&&lvl<10){
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    	}else if(lvl<5){
    		att[7]=(int)attack+25;
    	}else{
    		att[7]=(int)attack+25;
    		att[8]=(int)attack+30;
    		att[9]=(int)attack+35;
    		att[9]=(int)attack+40;
    	} 
    	
    	return att;
    }
}
