package com.darshan.warriorgame;



//import java.lang.reflect.Method;

import android.content.Context;

public class Player {
	String playerClass;
	String name;
	int id,lvl;
	double str,speed,maxHp,maxMana,skill,maxXp,xp,gold; 
	Integer[] inv = new Integer[8];
	Integer[] eqInv = new Integer[4];
	Integer[] poInv = new Integer[2];
	Integer[] skills;
	Integer[] skilllvl;
	SharingAtts appState;
	
	Context c;
	ItemTest it;
	DBManager exisPlayerAtt,exisPlayerInv,exisPlayerSkill;
	Warrior war;// = new Warrior();
	
	public Player(Context c,int id){
		this.c=c;
		this.id=id;
		it = new ItemTest(); 
	}
	
	//----------------------Load all skills-------------------------
	public String[] allSkills(){
		it = new ItemTest();
		String[] s = it.printData("skills");
		exisPlayerAtt = new DBManager(c,s[1],"allskills",s[0]);
		exisPlayerAtt.openToRead();
		String st=exisPlayerAtt.queueAll();
		exisPlayerAtt.close();
		String[] atts = st.split(" ");
		return atts;
	}
	
	//-------------------------NEW PLAYER-------------------------------------
	
	public void newPlayer(String name, String pClass){
		lvl=1;
		id=1;
		this.name=name;
		playerClass=pClass;
		maxXp=150;
		xp=0;
		gold=250;
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
		if(pClass.compareToIgnoreCase("Samurai")==0){
			str=17;
			speed=15;
			maxHp=85;
			maxMana=75;
			skills[0]=1001;
		}else if(pClass.compareToIgnoreCase("Ninja")==0){
			str=15;
			speed=17;
			maxHp=80;
			maxMana=85;
			skills[0]=2001;
		}else if(pClass.compareToIgnoreCase("Wizard")==0){
			str=15;
			speed=16;
			maxHp=80;
			maxMana=85;
			skills[0]=2001;
		}else if(pClass.compareToIgnoreCase("Balanced")==0){
			str=16;
			speed=16;
			maxHp=80;
			maxMana=80;
			skills[0]=1001;
		}
	}

	//Existing Player
	
		
		/*
		//player major atts read
		it = new ItemTest();
		String[] s = it.printData("players");
		exisPlayerAtt = new DBManager(c,s[1],"allplayer",s[0]);
		exisPlayerAtt.openToRead();
		String st=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
		String[] atts = st.split(" ");
		
		name = atts[1];
		playerClass=atts[2];
		lvl = Integer.parseInt(atts[3]);
		str = Double.parseDouble(atts[4]);
		speed = Double.parseDouble(atts[5]);
		maxHp = Double.parseDouble(atts[6]);
		maxMana = Double.parseDouble(atts[7]);
		maxXp = Double.parseDouble(atts[8]);
		xp = Double.parseDouble(atts[9]);
		gold = Double.parseDouble(atts[10]);
		
		//player inventory read
		String[] s1 = it.printData("player_inv");
		exisPlayerInv = new DBManager(c,s1[1],"playersinv",s1[0]);
		exisPlayerInv.openToRead();
		st=exisPlayerInv.queueAll(String.valueOf(id));
		String[] inv=st.split(" ");
		exisPlayerInv.close();
		for(int i=0;i<this.inv.length;i++){
			this.inv[i]=Integer.valueOf(inv[i]);
		}
		for(int i=0;i<this.eqInv.length;i++){
			this.eqInv[i]=Integer.valueOf(inv[this.inv.length+i]);
		}
		for(int i=0;i<this.poInv.length;i++){
			this.poInv[i]=Integer.valueOf(inv[this.inv.length+eqInv.length+i]);
		}
	
		//Skills Read
		String[] s2 = it.printData("player_skills");
		exisPlayerSkill = new DBManager(c,s2[1],"playerskill",s2[0]);
		exisPlayerSkill.openToRead();
		st=exisPlayerInv.queueAll(String.valueOf(id));
		String[] skills=st.split(" ");
		exisPlayerSkill.close();
		this.skills= new Integer[skills.length];
		
		for(int i=2;i<skills.length;i=i+3){
			this.skills[i]=Integer.valueOf(skills[i]);
			skilllvl[i]=Integer.valueOf(skills[i+1]);
		}
		
		
		//*/
		//return st;
	//}
	//------------------------------------------------------save the player record in db--------------------------------------------------------
	public String  savePlayer(Context c, String[] majatts, String[] inv,String[] plaSkills){
		it = new ItemTest();
		String[] s = it.printData("players");
		String[] inve = it.printData("player_inv");
		String[] pSkill = it.printData("player_skills");
		String majValues="";
		try{
		for(int i=0;i<majatts.length;i++)
			majValues=majValues+majatts[i]+" ";
		}catch(Exception e){
		System.err.print(e);	
		}
		
		String invValues="";
		for(int i=0;i<inv.length;i++)
			invValues=invValues+String.valueOf(inv[i])+" ";
		
		String pSkills="";
		for(int i=0;i<plaSkills.length;i++)
			pSkills=pSkills+String.valueOf(plaSkills[i])+" ";
		//*/
		
		//Storing maj Attributes
	exisPlayerAtt = new DBManager(c,s[1],"allplayer",s[0]);
		exisPlayerAtt.openToWrite();
		long rows = exisPlayerAtt.updateTable(majValues, "player_id = "+id);//+majatts[0]);
		
		String st=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
	//	*/
		
		exisPlayerAtt = new DBManager(c,inve[1],"playersinv",inve[0]);
		exisPlayerAtt.openToWrite();
		long row2 = exisPlayerAtt.updateTable(invValues, "player_id = "+id);//+majatts[0]);
		
		String st2=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
		

		exisPlayerAtt = new DBManager(c,pSkill[1],"playerskill",pSkill[0]);
		exisPlayerAtt.openToWrite();
		//exisPlayerAtt.dropTable();
		//exisPlayerAtt.cretTable();
		//try{
		//exisPlayerAtt.insertQuery(pSkill[2]);
		long row3 = exisPlayerAtt.updateTable(pSkills, "player_id = "+id);//+majatts[0]);
		String st3=exisPlayerAtt.queueAll(String.valueOf(id));
		exisPlayerAtt.close();
//}catch(Exception e){
//	return e+" return error found";
//}	
		return rows+" are affected\n"+st+"\n"+row2+" are affected\n"+st2+"\n"+row3+" are affected\n"+st3;
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
		*/
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
		*/
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
		*/
		return majA;
	}
	
	//-------------------------------------------------------NEW LEVEL---------------------------------------------------------------------------
	public Integer[] newLevel(int lv,int attCode, Integer[] att, String pClass){
		
		if(pClass.compareTo("Mighty")==0){
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
		public Integer[] newLevel(Warrior war, int attCode, Integer[] atts, String playerClass){
			
			Integer[] retobj= new Integer[10];
				try {
		           		Class cls = Class.forName("com.darshan.warriorgame.Warrior");
		           		Class argType[]=new Class[3];
		           		argType[0]=Integer.TYPE;
		           		Method meth = cls.getMethod(playerClass,argType);	
			           
			            retobj= (Integer[]) meth.invoke(war,1,attCode, atts);
			          
		         }
		         catch (Throwable e) {
		            System.err.println(e);
		         }
				 return (Integer[])retobj; 
			}
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
