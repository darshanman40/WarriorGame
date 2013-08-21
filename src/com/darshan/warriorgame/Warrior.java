package com.darshan.warriorgame;

public class Warrior {
//double lvl;
	
public Integer[] Samurai(int lv,int attCode, Integer[] att){
	if(lv%5==0){
		att[0]=att[0]+3;
		att[1]=att[1]+1;
		att[2]=att[2]+15;
		att[3]=att[3]+5;
	}
	
	switch(attCode){
	case 0:
		att[0]=att[0]+3;
		break;
	case 1:
		att[1]=att[1]+1;
		break;
	case 2:
		att[2]= att[2]+15;
		break;
	case 3:
		att[3]=att[3]+5;
		break;
	}
	return att;
}
	
	public Integer[] Ninja(int lv,int attCode, Integer[] att){
		if(lv%5==0){
			att[0]=att[0]+1;
			att[1]=att[1]+3;
			att[2]=att[2]+5;
			att[3]=att[3]+10;
		}
		
		switch(attCode){
		case 0:
			att[0]=att[0]+1;
			break;
		case 1:
			att[1]=att[1]+3;
			break;
		case 2:
			att[2]= att[2]+5;
			break;
		case 3:
			att[3]=att[3]+10;
			break;
		}
		return att;
	}
	
	public double[] Wizard(int lv,int attCode, double[] att){
		if(lv%5==0){
			att[0]=att[0]+1;
			att[1]=att[1]+2;
			att[2]=att[2]+10;
			att[3]=att[3]+15;
		}
		
		switch(attCode){
		case 0:
			att[0]=att[0]+1;
			break;
		case 1:
			att[1]=att[1]+2;
			break;
		case 2:
			att[2]= att[2]+10;
			break;
		case 3:
			att[3]=att[3]+15;
			break;
		}
		return att;
	}
	
	public double[] Balanced(int lv,int attCode, double[] att){
		if(lv%5==0){
			att[0]=att[0]+2;
			att[1]=att[1]+2;
			att[2]=att[2]+10;
			att[3]=att[3]+10;
		}
		
		switch(attCode){
		case 0:
			att[0]=att[0]+2;
			break;
		case 1:
			att[1]=att[1]+2;
			break;
		case 2:
			att[2]= att[2]+10;
			break;
		case 3:
			att[3]=att[3]+10;
			break;
		}
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
