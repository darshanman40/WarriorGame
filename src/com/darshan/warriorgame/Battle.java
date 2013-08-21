package com.darshan.warriorgame;

//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Scanner;

//import android.content.Context;

import java.util.Hashtable;

//import com.darshan.warriorgame.Warrior;

public class Battle{
	String[] arrayNames = {"MinAtts","MajAtts","eqInv","Skills"};
	
	Integer[] p1MinAtts;
	Integer[] p1MajAtts;
	Integer[] eqInvP1;
	Integer[] p1Skills;

	Integer[] p2MinAtts;
	Integer[] p2MajAtts;
	Integer[] eqInvP2;
	Integer[] p2Skills;
	
	int p1hp,p2hp,p1mana,p2mana,maxp1hp,maxp2hp,maxp1mana,maxp2mana,p1damage,p2damage;
	
	private SkillEffects[] skillList= new SkillEffects[]{
			new SkillEffects(){public void damage(int n){ newHpP2(n);}},		//0
			new SkillEffects(){public void damage(int n){ stab(n);}},			//1
			new SkillEffects(){public void damage(int n){ doubleStrike(n);}},	//2
			new SkillEffects(){public void damage(int n){ speedStrike(n);}},	//3
			new SkillEffects(){public void damage(int n){ verticalStrike(n);}},	//4
			new SkillEffects(){public void damage(int n){ avenger(n);}},		//5
			new SkillEffects(){public void damage(int n){ split(n);}},			//6
			new SkillEffects(){public void damage(int n){ execution(n);}},		//7
			new SkillEffects(){public void damage(int n){ shurikens(n);}},		//8
			new SkillEffects(){public void damage(int n){ energyShot(n);}},		//9
			new SkillEffects(){public void damage(int n){ blastFire(n);}},		//10
			new SkillEffects(){public void damage(int n){ charge(n);}},			//11
			new SkillEffects(){public void damage(int n){ manaBomb(n);}},		//12
			new SkillEffects(){public void damage(int n){ heal(n);}},			//13
			new SkillEffects(){public void damage(int n){ shadowStrike(n);}},	//14
			new SkillEffects(){public void damage(int n){ annihilate(n);}}		//15
			
	};
	
	public void damage(int i, int n){
		skillList[i].damage(n);
	}
	
	
	
	public Battle(Hashtable<String,Integer[]> P1,Hashtable<String,Integer[]> P2){
		p1MinAtts = P1.get(arrayNames[0]);
		p1MajAtts = P1.get(arrayNames[1]);
		eqInvP1 = P1.get(arrayNames[2]);
		p1Skills = P1.get(arrayNames[3]);
		
		p2MinAtts = P2.get(arrayNames[0]);
		p2MajAtts = P2.get(arrayNames[1]);
		eqInvP2 = P2.get(arrayNames[2]);
		p2Skills = P2.get(arrayNames[3]);
	
		maxp1hp = p1MajAtts[2];
		maxp2hp = p2MajAtts[2];
		maxp1mana = p1MajAtts[3];
		maxp2mana = p2MajAtts[3];
		p1hp=p1MajAtts[5];
		p1mana = p1MajAtts[6];
		
		p2hp = maxp2hp;
		p2mana = maxp2mana;
	}
	
	public void newHpP2(int id){
		
		int damage;
		if(id==0){
			damage = p1MinAtts[0]-(p2MinAtts[2]/10);
		if(damage<=0)
			damage=1;
		p1damage = damage;
		p2hp=p2hp-damage;
		}else if(id==1){
			newHpP1();
		}
		
	}
	
	public void newHpP1(){
		int damage = p2MinAtts[0]-(p1MinAtts[2]/10);
		if(damage<=0)
			damage=1;
		p2damage = damage;
		p1hp=p1hp-damage;
	}
	
	public void stab(int id){
		int damage;
		if(id == 0){
			damage =  (p1MinAtts[0])-(p2MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p1damage = damage+(p1Skills[0]*4);
			p2hp = p2hp-damage;
			//p1mana = p1mana - 15;
		}else if(id == 1){
			damage =  (p2MinAtts[0])-(p1MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p2damage = damage+(p2Skills[0]*4);
			p1hp = p1hp-damage;
			//p2mana = p2mana - 15;
		}
	}
	
	public void doubleStrike(int id){
		int damage;
		int coeff=60;
		if(id == 0){
			coeff = coeff+((p1Skills[1]-1)*8);
			damage =  ((coeff*p1MinAtts[0])/100)-(p2MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p1damage = 2*damage;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 20;
		}else if(id == 1){
			coeff = coeff+((p2Skills[1]-1)*8);
			damage =  ((coeff*p2MinAtts[0])/100)-(p1MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p2damage = 2*damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 20;
		}
	}
	
	public void speedStrike(int id){
		int damage;
		if(id == 0){
			
			damage =  ((p1MajAtts[1]*p1Skills[2]*60)/100)-(p2MinAtts[2]/10);
			p1damage = damage;
			if(damage<=0)
				damage=1;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 20;
		}else if(id == 1){
			damage =  ((p2MajAtts[1]*p2Skills[2]*60)/100)-(p1MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p2damage = damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 20;
		
		}
	}
	
	public void verticalStrike(int id){
		int damage;
		if(id == 0){
			damage =  ((p1MajAtts[0]*p1Skills[3]*80)/100)-(p2MinAtts[2]/10);
			p1damage = damage;
			if(damage<=0)
				damage=1;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 20;
		}else if(id == 1){
			damage =  ((p2MajAtts[0]*p2Skills[3]*80)/100)-(p1MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p2damage = damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 20;
		
		}
	}
	
	public void avenger(int id){
		int damage;
		//((p1maxhp-p1hp)*((coeff+((p1Skills[6]-1)*100))))
		int coeff=200;
		if(id == 0){
			damage =  ((maxp1hp-p1hp)*((coeff+((p1Skills[6]-1)*100)))/100)-(p2MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p1damage = damage;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 25;
		}else if(id == 1){
			damage =  ((maxp2hp-p2hp)*((coeff+((p2Skills[6]-1)*100)))/100)-(p1MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p2damage = damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 25;
		}
	}

	public void split(int id){
		int damage;
		//((p1maxhp-p1hp)*((coeff+((p1Skills[6]-1)*100))))
		int coeff=16;
		if(id == 0){
			damage =  ((p2hp)*((coeff+((p1Skills[7]-1)*2)))/100)-(p2MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p1damage = damage;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 25;
		}else if(id == 1){
			damage =  ((p1hp)*((coeff+((p2Skills[7]-1)*2)))/100)-(p2MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p2damage = damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 25;
		}
	}
	
	public void execution(int id){
		int damage;
		int coeff=75;
		if(id == 0){
			coeff = coeff+((p1Skills[8]-1)*5);
			damage =  ((coeff*p1MinAtts[0])/100)-(p2MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p1damage = 4*damage;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 70;
		}else if(id == 1){
			coeff = coeff+((p2Skills[8]-1)*5);
			damage =  ((coeff*p2MinAtts[0])/100)-(p1MinAtts[2]/10);
			if(damage<=0)
				damage=1;
			p2damage = 4*damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 70;
		}
	}
	//------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------
	public void shurikens(int id){
		int damage;
		p1MinAtts[1]=p1MinAtts[1]+1;
		if(id == 0){
			damage =  ((p1MinAtts[1]+(22*p1Skills[9])))-(p2MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p1damage = damage;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 20;
		}else if(id == 1){
			damage =  ((p2MinAtts[1]+(22*p2Skills[9])))-(p1MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p2damage = damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 20;
		}
	}
	
	public void energyShot(int id){
		int damage;
		p1MinAtts[1]=p1MinAtts[1]+1;
		if(id == 0){
			damage =  ((p1MinAtts[1]+(49*p1Skills[10])))-(p2MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p1damage = damage;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 25;
		}else if(id == 1){
			damage =  ((p2MinAtts[1]+(49*p2Skills[10])))-(p1MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p2damage = damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 25;
		}
	}
	
	public void blastFire(int id){
		int damage;
		int coeff = 12;
		p1MinAtts[1]=p1MinAtts[1]+1;
		if(id == 0){
			damage =  ((coeff+p1MinAtts[1])+(6*(p1Skills[11]-1)))-(p2MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p1damage = 2*damage;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 25;
		}else if(id == 1){
			damage =  ((coeff+p2MinAtts[1])+(6*(p2Skills[11]-1)))-(p1MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p2damage = 2*damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 25;
		}
	}
	
	public void charge(int id){
		p1MinAtts[1]=p1MinAtts[1]+1;
		if(id == 0 ){ //&& p1mana!=0){
			p1mana = p1mana+((maxp1mana * (p1Skills[13]*25))/100);
		}else if(id == 1){
			//p2mana = p2mana+(maxp2mana * (p2Skills[13]*25))/100;
			blastFire(id);
		}
	}
	
	public void manaBomb(int id){
		int damage;
		if(id == 0){
			damage = (((p1mana-1)*150*p1Skills[14])/100) -(p2MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p1damage = damage;
			p2hp = p2hp-p1damage;
			p1mana=0;
		}else if(id == 1){
			blastFire(id);
			//damage = (((p2mana-1)*150*p2Skills[14])/100)-(p2MinAtts[3]/10);
			//p2damage = damage;
			//p1hp = p1hp-p2damage;
			//p2mana=0;
		}
	}
	
	public void heal(int id){
		int coeff =25;
		p1MinAtts[1]=p1MinAtts[1]+1;
		if(id == 0){
			p1hp = p1hp+((maxp1hp * (coeff+(p1Skills[15]*8)))/100);
			p1mana = p1mana - 15;
		}else if(id == 1){
			p2hp = p2hp+((maxp2hp * (coeff+(p2Skills[15]*8)))/100);
			//p2mana = p2mana - 15;
		}
	}
	
	public void shadowStrike(int id){
		int damage;
		p1MinAtts[1]=p1MinAtts[1]+1;
		if(id == 0){
			damage =  ((p1MinAtts[1]+(85*p1Skills[16])))-(p2MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p1damage = damage;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 40;
		}else if(id == 1){
			damage =  ((p2MinAtts[1]+(85*p2Skills[16])))-(p1MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p2damage = damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 40;
		}
	}
	
	public void annihilate(int id){
		int damage;
		int coeff = 22;
		p1MinAtts[1]=p1MinAtts[1]+1;
		if(id == 0){
			damage =  ((coeff+p1MinAtts[1])+(11*(p1Skills[17]-1)))-(p2MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p1damage = 3*damage;
			p2hp = p2hp-p1damage;
			p1mana = p1mana - 60;
		}else if(id == 1){
			damage =  ((coeff+p2MinAtts[1])+(11*(p2Skills[17]-1)))-(p1MinAtts[3]/10);
			if(damage<=0)
				damage=1;
			p2damage = 3*damage;
			p1hp = p1hp-p2damage;
			//p2mana = p2mana - 60;
		}
	}
	
	//--------------------------------------------------------
	//public void newHpP1(int damage){
		//p2damage = damage;
	//	p1hp=p1hp-damage;
	//}
	//--------------------------------------------------
	//public void newHpP2(int damage){
		//p2damage = damage;
	//	p2hp=p2hp-damage;
	//}
	/*
	public void damageSkill1Calc(int aCode){
		int damage;
		
		switch(aCode){
		case 1:
			
			break;
		
		}
	}

	//@Override
	
	
	//public Integer[] getP1MinAtts(){
	
	//}
	
	/*
	public Battle(Player p1, Player com){
		//this.sa=sa;
		//this.com = com;
		//this.c=c;
		this.pl =  p1;//new Player(c,sa.getMajatt(),sa.getAllInv(),sa.getAllSkillsLvl(),sa.name,sa.playerClass,sa.id);
		this.com = com;//new Player(c,com.id);
		//setAllAtts();
		
	}
	
	//---------------------------  set-type functions------------------------------------------------- 
	//str,ph_dam,mag_dam,ph_def,mag_def,e_s_dam,s_hp,b_mana,speed
	//size = 9   //  last index = 8
	
	public void setAllAtts(String[] p1Inv,String[] p2Inv){
		//String[] p1Inv;// = new String[pl.eqInv.length];
/*		//String[] p2Inv;// = new String[com.eqInv.length];
		
		//for(int i=0;i<pl.eqInv.length;i++){
			//if(!pl.eqInv[i].equals("0")){
				//p1Inv=sa.getAllItms(String.valueOf(pl.eqInv[i]));
	*/
	/*
				for(int j=1;j<pla1att.length;j++){
					if(!p1Inv[j].equalsIgnoreCase("0"))
					pla1att[j-1] = pla1att[j-1]+Integer.valueOf(p1Inv[j]);
					if(!p2Inv[j].equalsIgnoreCase("0"))
					pla2att[j-1] = pla2att[j-1]+Integer.valueOf(p2Inv[j]);
			}
			/*if(!com.eqInv[i].equals("0")){
				p2Inv=sa.getAllItms(String.valueOf(com.eqInv[i]));
				for(int j=0;j<pla2att.length;j++)
					pla2att[j] =pla2att[j] + com.calcAtts(p2Inv)[j];
			}*/
		//}
		
		
		
		//
	/*
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
	/*
	public String[] getp1Atts(){
		return pl.getMajAtts();
	}*/
	/*
	public double getp1hp(){
		return p1hp;
	}
	
	
	//-------------------------------  p2 get-type functions----------------------------------------
	
	public String getp2Name(){
		return com.name;
	}
	/*
	public String[] getp2Atts(){
		return com.getMajAtts();
	}
	*/
	/*
	public double getp2hp(){
		return p2hp;
	}
	
	public Integer[] getPla2atts(){
		return pla2att;
	}
	
	
	*/
	
	
	
	
	
	
	
	
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
		
		
		if(damage<=0)
			hp = hp-((damage*(-1)));
		else
			hp = hp-(damage);
		return hp;
		
	}
	*/
 }

