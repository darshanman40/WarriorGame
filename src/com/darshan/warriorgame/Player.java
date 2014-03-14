package com.darshan.warriorgame;





import java.lang.reflect.Method;

import android.content.Context;

public class Player {
	String playerClass;
	String name;
	String password;
	int id,lvl;
	double str,speed,maxHp,maxMana,skill,maxXp,hp,mana,xp,gold,remSkilPts,remAttPts; 
	Integer[] inv = new Integer[8];
	Integer[] eqInv = new Integer[4];
	Integer[] poInv = new Integer[2];
	Integer[] skills = new Integer[]{1001,1011,1012,1021,1041,1042,1051,2001,2011,2012,2022,2031,2032,2041,2042};
	Integer[] skilllvl= new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	//SharingAtts sa;
	Integer[] minorAtts;
	Context c;
	ItemTest it;
	//DBManager exisPlayerAtt,exisPlayerInv,exisPlayerSkill;
	Warrior war;// = new Warrior();
	
	// for exis player
	public Player(Context c,int id){
		this.c=c;
		this.id=id;
		it = new ItemTest(); 
		//String[] majAtts =loadPlayerMaj(c,id);
		//setMajAtts(majAtts);
		//String[] inv = loadPlayerInv();
		//setAllInv(inv);
		//String[] sklvl = loadPlayerSkills();
		//setSkilLvl(sklvl);
	}
	
	
	// for new player
	public Player(Context c,String name, String pClass, String pass) {
		// TODO Auto-generated constructor stub
		it = new ItemTest();
		this.name=name;
		this.playerClass = pClass;
		this.c= c;
		password = pass;
		newPlayer(name, pClass);
		//skills = new Integer[19];
		//skilllvl = new Integer[19];
		
	}
	
	public Player(String pClass, int lvl){
		playerClass = pClass;
		this.lvl=lvl;
	}
	public void newPlayer(String name, String pClass){
		lvl=1;
		id=21;
		this.name=name;
		playerClass=pClass;
		maxXp=150;
		xp=0;
		gold=250;
		remSkilPts=0;
		remAttPts=0;
		//
		for(int i=0;i<eqInv.length;i++){
			eqInv[i]=0;
			inv[i]=0;
		}
		for(int i=4;i<inv.length;i++){
			inv[i]=0;
		}
		eqInv[0]=101;
		poInv[0]=5;
		poInv[1]=5;
		
		//return pClass;
		
		if(playerClass.contentEquals("Samurai")){
			str=17;
			speed=15;
			maxHp=85;
			maxMana=75;
			skilllvl[0]=1;
			skilllvl[9]=0;
		}
		else if(playerClass.compareToIgnoreCase("Ninja")==0){
			str=15;
			speed=17;
			maxHp=80;
			maxMana=85;
			skilllvl[9]=1;
			skilllvl[0]=0;
		}else if(playerClass.compareToIgnoreCase("Wizard")==0){
			str=15;
			speed=16;
			maxHp=80;
			maxMana=85;
			skilllvl[9]=1;
			skilllvl[0]=0;
		}else if(playerClass.compareToIgnoreCase("Balanced")==0){
			str=16;
			speed=16;
			maxHp=80;
			maxMana=80;
			skilllvl[0]=1;
		}
		
		//for(int i=1;i<9;i++)
			
		
		hp=maxHp;
		mana=maxMana;
		
	}
	
	public String[] getNPlayerAtts(){
		String[] atts= new String[16];
		atts[0]= String.valueOf(id);
		atts[1]= name;
		atts[2]= password;
		atts[3]= playerClass;
		atts[4]= String.valueOf(lvl);
		atts[5]= String.valueOf(str);
		atts[6]= String.valueOf(speed);
		atts[7]= String.valueOf(maxHp);
		atts[8]= String.valueOf(maxMana);
		atts[9]= String.valueOf(maxXp);
		atts[10]= String.valueOf(hp);
		atts[11]= String.valueOf(mana);
		atts[12]= String.valueOf(xp);
		atts[13]= String.valueOf(gold);
		atts[14]= String.valueOf(remSkilPts);
		atts[15]= String.valueOf(remAttPts);
				
				return atts;
	}
	
	public String[] getNPlayerSkilvls(){
		String[] atts= new String[skilllvl.length];
		for(int i=0;i<skilllvl.length;i++){
			atts[i]=String.valueOf(skilllvl[i]);
		}
		return atts;
	}
	
	public String[] getNPlayerInv(){
		String[] atts= new String[inv.length+eqInv.length+poInv.length];
		//atts[0]="1";
		for(int i=0;i<inv.length;i++){
			atts[i]=String.valueOf(inv[i]);			
		}
		for(int i=0;i<eqInv.length;i++)
			atts[inv.length+i]=String.valueOf(eqInv[i]);
		for(int i=0;i<poInv.length;i++)
			atts[i+inv.length+eqInv.length] = String.valueOf(poInv[i]);
		
		return atts;
	}
	
	public Integer[] newLevel(Warrior war, int attCode, Integer[] atts){
		
		Integer[] retobj= new Integer[10];
			try {
	           		@SuppressWarnings("rawtypes")
					Class cls = Class.forName("com.darshan.warriorgame.Warrior");
	           		@SuppressWarnings("rawtypes")
					Class argType[]=new Class[3];
	           		
	           		argType[0]=Integer.TYPE;
	           		argType[1]=Integer.TYPE;
	           		argType[2]=Integer[].class;
	           		Method meth = cls.getMethod(playerClass,argType);	
		           
		            retobj= (Integer[]) meth.invoke(war,lvl,attCode, atts);
		          
	         }
	         //catch (Throwable e) {
	        //    System.err.println(e);
	         catch(Exception e){
	        	 System.err.println(e);
	         }
			 return (Integer[])retobj; 
		}
	
	
/*
	//for SA players
	public Player(Context c,double[] atts, Integer[] allInv, Integer[] skilvl, String name, String pClass,int id){
		it = new ItemTest();
		
		this.id=id;
		this.name = name;
		this.playerClass = pClass;
		
		//atts
		str=atts[0];
		speed=atts[1];
		maxHp=atts[2];
		maxMana=atts[3];
		maxXp=atts[4];
		hp=atts[5];
		mana=atts[6];
		xp=atts[7];
		gold=atts[8];
		
		//inv
		for(int i=0;i<inv.length;i++)
			inv[i]=Integer.valueOf(allInv[i]);
		for(int i=0;i<eqInv.length;i++)
			eqInv[i]=Integer.valueOf(allInv[i+8]);
		for(int i=0;i<poInv.length;i++)
			poInv[i]=Integer.valueOf(allInv[i+4+8]);
		
		//skilvl
		//for(int i=0; i<skill.length;i=i+10){
			//skills[i]=skill[i];
		//}
		for(int i=0;i<skilvl.length;i++)
			this.skilllvl[i]=skilvl[i];
		
		
	}
	


	
	public Integer getEffect(Integer skillid){
		String[] s = it.printData("skills");
		exisPlayerAtt = new DBManager(c,s[1],"allskills",s[0]);
		exisPlayerAtt.openToRead();
		String st=exisPlayerAtt.queueAll(String.valueOf(skillid));
		exisPlayerAtt.close();
		String[] atts = st.split(" ");
		
		return Integer.valueOf(atts[6]);
		
	}
	//----------------------Load all skills-------------------------
	public String[] allSkills(){
		//it = new ItemTest();
		String[] s = it.printData("skills");
		exisPlayerAtt = new DBManager(c,s[1],"allskills",s[0]);
		
		exisPlayerAtt.openToWrite();
		exisPlayerAtt.cretTable();
		for(int i=2; i<s.length;i++)
			exisPlayerAtt.insertQuery(s[i]);
		exisPlayerAtt.close();
		
		exisPlayerAtt.openToRead();
		String st=exisPlayerAtt.queueAll();
		exisPlayerAtt.close();
		String[] atts = st.split(" ");
		return atts;
	}
	
	//-------------------------NEW PLAYER-------------------------------------
	
	

	//-----------------------save the player record in db---------------------------------------------------------------------------------
	public String  savePlayer(Context c, String[] majatts, String[] inv,String[] plaSkills){
		it = new ItemTest();
		String[] s = it.printData("players");
		String[] inve = it.printData("player_inv");
		String[] pSkill = it.printData("player_skills");
		String[] allSkill = it.printData("skills");
		String majValues="";
		try{
		for(int i=0;i<majatts.length;i++)
			majValues=majValues+majatts[i]+" ";
		}catch(Exception e){
		System.err.print(e);	
		}
		
		String invValues=String.valueOf(id)+" ";
		for(int i=0;i<inv.length;i++)
			invValues=invValues+String.valueOf(inv[i])+" ";
		
		String pSkills=String.valueOf(id)+" ";
		for(int i=0;i<plaSkills.length;i++)
			pSkills=pSkills+String.valueOf(plaSkills[i])+" ";
		//
		
		//Storing maj Attributes
	exisPlayerAtt = new DBManager(c,s[1],"allplayer",s[0]);
		exisPlayerAtt.openToWrite();
		exisPlayerAtt.dropTable();
		exisPlayerAtt.cretTable();
		for(int i=2;i<s.length;i++)
			exisPlayerAtt.insertQuery(s[i]);
		
		long rows = exisPlayerAtt.updateTable(majValues, "player_id = "+id);//+majatts[0]);
		
		String st=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
	//	
		
		exisPlayerAtt = new DBManager(c,inve[1],"playersinv",inve[0]);
		exisPlayerAtt.openToWrite();
		exisPlayerAtt.dropTable();
		exisPlayerAtt.cretTable();
		for(int i=2;i<inve.length;i++)
		exisPlayerAtt.insertQuery(inve[i]);
		long row2 = exisPlayerAtt.updateTable(invValues, "player_id = "+id);//+majatts[0]);
		
		String st2=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
		

		exisPlayerAtt = new DBManager(c,pSkill[1],"playerskill",pSkill[0]);
		exisPlayerAtt.openToWrite();
		exisPlayerAtt.dropTable();
		exisPlayerAtt.cretTable();
		//try{
		exisPlayerAtt.deleteAll();
		for(int i = 2 ; i<pSkill.length;i++)
		exisPlayerAtt.insertQuery(pSkill[i]);
		long row3 = exisPlayerAtt.updateTable(pSkills, "player_id = "+id);//+majatts[0]);
		String st3=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
		
		
		exisPlayerAtt = new DBManager(c,allSkill[1],"allskills",allSkill[0]);
		exisPlayerAtt.openToWrite();
		exisPlayerAtt.dropTable();
		exisPlayerAtt.cretTable();
		//try{
		exisPlayerAtt.deleteAll();
		for(int i = 2 ; i<allSkill.length;i++)
		exisPlayerAtt.insertQuery(allSkill[i]);
		//long row3 = exisPlayerAtt.updateTable(pSkills, "player_id = "+id);//+majatts[0]);
		String st4=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
		
		
//}catch(Exception e){
//	return e+" return error found";
//}	
		return rows+" are affected\n"+st+"\n"+row2+" are affected\n"+st2+"\n"+row3+" are affected\n"+st3+"\n"+st4;
	}
	//-----------------------------------------------------Load from DB----------------------------------------------------------------
	public String[] loadPlayerMaj(Context c, int id){
		it = new ItemTest();
		String[] s = it.printData("players");
		//String[] inve = it.printData("player_inv");
		//String[] pSkill = it.printData("player_skills");
		//double[] majAtts;
		
		exisPlayerAtt= new DBManager(c,s[1],"allplayer",s[0]);

		exisPlayerAtt.openToRead();
		String mA="";
		mA=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
		String[] majA=mA.split(" ");
		//double[] pass=new double[majA.length];
		//for(int i=0;i<majA.length;i++)
			//pass[i]=Double.parseDouble(majA[i]);
		/*appState= ((SharingAtts)getApplication());
		String x = appState.getState();
		appState.setState(x);
		
		return majA;
	}
	//--------------------------------------------------------Load Inventory DB-----------------------------------------------------------------------
	public String[] loadPlayerInv(){
		it = new ItemTest();
		//String[] s = it.printData("players");
		String[] inve = it.printData("player_inv");
		//String[] pSkill = it.printData("player_skills");
		//double[] majAtts;
		exisPlayerAtt= new DBManager(c,inve[1],"playersinv",inve[0]);
		exisPlayerAtt.openToRead();
		String mA="";
		mA=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
		String[] majA=mA.split(" ");
		/*double[] pass=new double[majA.length];
		/*for(int i=0;i<majA.length;i++)
			pass[i]=Double.parseDouble(majA[i]);
		/*appState= ((SharingAtts)getApplication());
		String x = appState.getState();
		appState.setState(x);
		
		return majA;
	}
	//----------------------------------------------------------------Load Skills DB----------------------------------------------------------------
	public String[] loadPlayerSkills(){
		it = new ItemTest();
		//String[] s = it.printData("players");
		//String[] inve = it.printData("player_inv");
		String[] pSkill = it.printData("player_skills");
		//double[] majAtts;
		exisPlayerAtt= new DBManager(c,pSkill[1],"playerskill",pSkill[0]);
		exisPlayerAtt.openToRead();
		String mA="";
		mA=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
		String[] majA=mA.split(" ");
		/*double[] pass=new double[majA.length];
		for(int i=0;i<majA.length;i++)
			pass[i]=Double.parseDouble(majA[i]);
		
		return majA;
	}
	//--------------------------------------------------------------------------
	//
	//
	//
	public Integer[] calcAtts(String[] itmsAtts){
		//str,ph_dam,mag_dam,ph_def,mag_def,e_s_dam,s_hp,b_mana,speed
		//8
		//eqInv = new Integer[]{101,201,301,401};
		//minorAtts[0]=(int) str;
		minorAtts = new Integer[]{0,0,0,0,0,0,0,0,0};
		//for(int i=0;i<this.eqInv.length;i++){
			//if(this.eqInv[i]!=0){
				//String[] eqSel=itmsAtts;
				for(int j=0;j<minorAtts.length;j++){
					minorAtts[j]=minorAtts[j]+Integer.valueOf(itmsAtts[j+3]);
				}
			//}
		//}
		//skills=new Integer[]{1022,1031,2021};
		//skilllvl=new Integer[]{1,1,2};
				/*
		for(int i=0; i<skills.length;i++){
			if(skills[i]==1022 )
				minorAtts[8]=minorAtts[8]+(getEffect(skills[i])*skilllvl[i]);
			else if(skills[i]==1031)
				minorAtts[1]=minorAtts[1]+(getEffect(skills[i])*skilllvl[i]);
			else if(skills[i]==2021)
				minorAtts[2]=minorAtts[2]+(getEffect(skills[i])*skilllvl[i]);
		}
		
		return minorAtts;
	} 
	
	public String[] itmAtts(int id){
		
		it = new ItemTest();
		String[] inve = it.printData("inventory");
		exisPlayerAtt= new DBManager(c,inve[1],"allitems",inve[0]);
		//exisPlayerAtt.openToWrite();
		//exisPlayerAtt.cretTable();
		//exisPlayerAtt.deleteAll();
		//for(int i=2;i<inve.length;i++)
		//	exisPlayerAtt.insertQuery(inve[i]);
		//exisPlayerAtt.close();
		exisPlayerAtt.openToRead();
		String mA="";
		mA=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
		String[] majA=mA.split(" ");
		return majA;
		
	}
	
	//-------------------------------------------------------NEW LEVEL---------------------------------------------------------------------------
	public Integer[] newLevel(int lv,int attCode, Integer[] att, String pClass){
		lvl = lv;
		if(pClass.compareTo("Samurai")==0){
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
	
		
		}else if(pClass.compareTo("Ninja")==0){
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
		}else if(pClass.compareTo("Wizard")==0){
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
		}else if(pClass.compareTo("Balanced")==0){
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
		return new Integer[]{0};
	}
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	
	public void setMajAtts(String[] majAtts){
		id = Integer.valueOf(majAtts[0]);
		name = majAtts[1];
		password = majAtts[2];
		playerClass = majAtts[3];
		lvl = Integer.valueOf(majAtts[4]);
		str = Integer.valueOf(majAtts[5]);
		speed = Integer.valueOf(majAtts[6]);
		maxHp = Integer.valueOf(majAtts[7]);
		maxMana = Integer.valueOf(majAtts[8]);
		maxXp = Integer.valueOf(majAtts[9]);
		hp = Integer.valueOf(majAtts[10]);
		mana = Integer.valueOf(majAtts[11]);
		xp = Integer.valueOf(majAtts[12]);
		gold = Integer.valueOf(majAtts[13]);
		
	}
	
	public void setAllInv(String[] allInv){
		//String[] allInv = new String[inv.length+eqInv.length+poInv.length];
		for(int i=0;i<inv.length;i++)
			inv[i]=Integer.valueOf(allInv[i]);
		for(int i=0;i<eqInv.length;i++)
			eqInv[i]=Integer.valueOf(allInv[i+inv.length]);
		for(int i=0;i<poInv.length;i++)
			poInv[i] = Integer.valueOf(allInv[i+inv.length+eqInv.length]);	
	}
	
	public void setSkilLvl(String[] sklvl){
		for(int i=0; i<skilllvl.length;i++)
			skilllvl[i]= Integer.valueOf(sklvl[i]);   
	}
	
	
	
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	
	public String[] getMajAtts(){
		String[] majAtts = new String[14];
		majAtts[0] = String.valueOf(id);
		majAtts[1] = name;
		majAtts[2] = password;
		majAtts[3] = playerClass;
		majAtts[4] = String.valueOf(lvl);
		majAtts[5] = String.valueOf(str);
		majAtts[6] = String.valueOf(speed);
		majAtts[7] = String.valueOf(maxHp);
		majAtts[8] = String.valueOf(maxMana);
		majAtts[9] = String.valueOf(maxXp);
		majAtts[10] = String.valueOf(hp);
		majAtts[11] = String.valueOf(mana);
		majAtts[12] = String.valueOf(xp);
		majAtts[13] = String.valueOf(gold);
		
		return majAtts;
		
	} 
	
	public String[] getAllInv(){
		String[] allInv = new String[inv.length+eqInv.length+poInv.length];
		for(int i=0;i<inv.length;i++)
			allInv[i]=String.valueOf(inv[i]);
		for(int i=0;i<eqInv.length;i++)
			allInv[i+inv.length]=String.valueOf(eqInv[i]);
		for(int i=0;i<poInv.length;i++)
			allInv[i+inv.length+eqInv.length]=String.valueOf(poInv[i]);
		return allInv;
	}
	
	public String[] getSkilLvl(){
		String[] sklvl = new String[skilllvl.length];
		for(int i=0; i<skilllvl.length;i++)
			sklvl[i] = String.valueOf(skilllvl[i]); 
		
		return sklvl;
	}
	
	
	
	
	/*
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
		*/
	
		
		/*
		public Integer[] pInv(){
			Integer[] inve = new Integer[8];
			
			
			return inve;
		}
		
		public Integer[] eInv(){
			Integer[] inve = new Integer[4];
			
			
			return inve;
		}
	//*/	
}
