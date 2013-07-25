package com.darshan.warriorgame;

public class Warrior {
double lvl;
	
	public double strMighty(double lvl, double att ){
		//double iniAtt[] = new double[4];
		att=att+3;
		if((int)(lvl%5)==0)
			att=att+3;
		return att;
	}
	public double spdMighty(double lvl, double att ){
		att=att+1;
		if((int)(lvl%5)==0)
			att=att+1;
		return att;
	}
	public double hpMighty(double lvl, double att ){
		//double iniAtt[] = new double[4];
		att=att+15;
		if((int)(lvl%5)==0)
			att=att+15;
		return att;
	}
	public double manaMighty(double lvl, double att ){
		att=att+5;
		if((int)(lvl%5)==0)
			att=att+5;
		return att;
	}
	public double strBalanced(double lvl, double att ){
		//double iniAtt[] = new double[4];
		att=att+2;
		if((int)(lvl%5)==0)
			att=att+2;
		return att;
	}
	public double spdBalanced(double lvl, double att ){
		att=att+2;
		if((int)(lvl%5)==0)
			att=att+2;
		return att;
	}
	public double hpBalanced(double lvl, double att ){
		//double iniAtt[] = new double[4];
		att=att+10;
		if((int)(lvl%5)==0)
			att=att+10;
		return att;
	}
	public double manaBalanced(double lvl, double att ){
		att=att+10;
		if((int)(lvl%5)==0)
			att=att+10;
		return att;
	}
	public double strNinja(double lvl, double att ){
		//double iniAtt[] = new double[4];
		att=att+1;
		if((int)(lvl%5)==0)
			att=att+1;
		return att;
	}
	public double spdNinja(double lvl, double att ){
		att=att+3;
		if((int)(lvl%5)==0)
			att=att+3;
		return att;
	}
	public double hpNinja(double lvl, double att ){
		//double iniAtt[] = new double[4];
		att=att+5;
		if((int)(lvl%5)==0)
			att=att+5;
		return att;
	}
	public double manaNinja(double lvl, double att ){
		att=att+10;
		if((int)(lvl%5)==0)
			att=att+10;
		return att;
	}
	public double strWizard(double lvl, double att ){
		//double iniAtt[] = new double[4];
		att=att+1;
		if((int)(lvl%5)==0)
			att=att+1;
		return att;
	}
	public double spdWizard(double lvl, double att ){
		att=att+1;
		if((int)(lvl%5)==0)
			att=att+1;
		return att;
	}
	public double hpWizard(double lvl, double att ){
		//double iniAtt[] = new double[4];
		att=att+5;
		if((int)(lvl%5)==0)
			att=att+5;
		return att;
	}
	public double manaWizard(double lvl, double att ){
		att=att+15;
		if((int)(lvl%5)==0)
			att=att+15;
		return att;
	}
	


/*
	att[1]=att[1]+1;
	att[2]=att[2]+15;
	att[3]=att[3]+5;


/*
	public Integer[] Balanced(int i){
		lvl=(float)i;

		double hp = 100 * (lvl*0.75);
		double mana = 100 * (lvl*0.25);
		double attack = 10 * (lvl*0.5);
		double mgAttack = 10 * (lvl*0.5);
		double mgDefense = 10 * (lvl*0.75);
		double phDefense = 10 * (lvl*0.75);
		double dodgerate = 10 * (lvl*0.25);
		double accuracy = 10 * (lvl*0.5);
		double exp = 200*lvl; 
		Integer[] att = new Integer[11];
       
    	att[0]=(int)hp;
    	att[1]=(int)mana;
    	att[2]=(int)accuracy;
    	att[3]=(int)phDefense;
    	att[4]=(int)mgDefense;
    	att[5]=(int)dodgerate;
    	att[6]=(int)exp;
    	att[7]=(int)attack;
    	
    	
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
	
	public Integer[] Magic(int i){
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
	
	public Integer[] Juggernaut(int i){
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
    }*/
}
