package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CsvTest extends Activity implements OnClickListener{

	String s;
	String[] st;
	
	TextView tvCT;
	Button bskill,bplayer,bpinv,bitms;
	
	ItemTest it;
	DBManager itms;//,attacks;
	SharingAtts sa;
	Player pl;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.csvtest);
		
		tvCT = (TextView)findViewById(R.id.tvTbl);
		bskill = (Button)findViewById(R.id.bSkills);
		bplayer = (Button)findViewById(R.id.bPlayers);
		bpinv = (Button)findViewById(R.id.bPInv);
		bitms = (Button)findViewById(R.id.bItems);
		
		bskill.setOnClickListener(this);
		bplayer.setOnClickListener(this);
		bpinv.setOnClickListener(this);
		bitms.setOnClickListener(this);
	/*
		SharingAtts sa = ((SharingAtts)getApplication());
		sa.setState("Sharing atts");
		
		*/
		/*
		Player pl = new Player(this,1);
		sa=((SharingAtts)getApplication());
		sa.setName("Dragon");
		s="Idiot";
		//String[] gg=pl.loadPlayerMaj(this,1);
		//try{
		//String[] ggg=pl.allSkills();
		
		//for(int i=0;i<gg.length;i++)
		//	s=s+gg[i]+".";
			
		sa.setPlaAtts(pl.loadPlayerMaj(this, 1));
		
		sa.setAllInv(pl.loadPlayerInv());
		//Integer[] skl = new Integer[(int)((pl.allSkills().length)/10)];
		//Integer[] sklvl = new Integer[(int)((pl.allSkills().length)/10)];
		String[] sas = pl.allSkills();
		String[] sb = pl.loadPlayerSkills();
		String[] sasa = new String[(int)(sas.length/10)];
		s="";
		/*
		for(int i=0; i< sas.length/10;i++){
			sasa[i]=sas[i*10].replace("\n", "");
			s=s+sasa[i]+".";
		}
		for(int i=0; i< sas.length/10;i++){
			sasa[i]=sas[i*10].replace("\n", "");
			s=s+sasa[i]+".";
		}
		//
		//String[] ss=s.split(".");
		sa.setSkills(sasa,sb);
		//
		//
		//}catch(Exception e){
		//	tvCT.setText(e.toString());//+gg[0]+" "+gg[1]);
		//	
		//}
		tvCT.setText(s);
		*/
    }
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		sa=((SharingAtts)getApplication());
		it = new ItemTest();
		//long affec=0;
		//switch(arg0.getId()){
		if(arg0.getId()==R.id.bPInv){
			//long affec=0;
			st = it.printData("player_inv");
			itms = new DBManager(this,st[1],"playersinv",st[0]);
			itms.openToRead();
			s = itms.queueAll();
			itms.close();
			tvCT.setText(s);
			
			/*
			st = it.printData("player_inv");
			itms = new DBManager(this,st[1],"playersinv",st[0]);
			itms.openToWrite();
			
			//itms.cretTable();
			
			for(int i=2; i<st.length;i++)
				affec=itms.insertQuery(st[i]);
			
			itms.close();
			*/
			//tvCT.setText(affec+" rows are affected");
			
			//break;
			
		}else if(arg0.getId()== R.id.bPlayers){
			/*
			st = it.printData("player_skills");
			itms = new DBManager(this,st[1],"playerskill",st[0]);
			itms.openToWrite();
			itms.dropTable();
			itms.cretTable();
			for(int i = 2; i<st.length; i++)
				affec=itms.insertQuery(st[i]);
			//
			itms.close();
			tvCT.setText(affec+" rows are affected");
			//*/
			
			st = it.printData("players");
			itms = new DBManager(this,st[1],"allplayer",st[0]);
			//String rows="";
			try{
			//Player pl =new Player(this,1);
			
			//rows=pl.savePlayer(this, new String[]{"1","Osairas","Samurai","1","17","15","80","70","150","0","250"}, null, null);
			itms.openToWrite();
			itms.dropTable();
			itms.cretTable();
			for(int i=2;i<st.length;i++)
				itms.insertQuery(st[i]);
			s = itms.queueAll();
			itms.close();
			}catch(Exception e){
				System.err.print(e);
				tvCT.setText(e.toString());
			}
			
			tvCT.setText(sa.getNameOnly());
			//*/
			//*/W
			//tvCT.setText(rows);//+" rows are affected");
			//*/
			/*/
			 * 
			 */
			//String[] pla = new String[]{"1","Osairas","Samurai","2","17","15","80","70","150","80","70","0","1000"};
			//String[] inv = new String[]{"1","0","0","0","0","0","0","0","0","101","201","301","401","10","10"};
			//String[] skills = new String[]{"1","1","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
			//Player p =new Player(this,1);
			//s=p.savePlayer(this, pla, inv, skills);
			//if(s.compareTo("")!=0)
				//tvCT.setText(affec+" rows arWe affected");
				//tvCT.setText(s);
				//*/
			//------------------------
			/*
			Player pl = new Player(this,1);
			Integer[] newValue=new Integer[]{17,15,85,80};
			
			newValue=pl.newLevel(2, 0,newValue,"Mighty");
			String s="";
			for(int i=0;i<newValue.length;i++)
				s=s+newValue[i]+"\t";
			tvCT.setText(sa.getName());
			*/
			//break;
		}else if(arg0.getId()==  R.id.bItems){
			/*
			st = it.printData("inventory");
			itms = new DBManager(this,st[1],"allitems",st[0]);
			itms.openToWrite();
			itms.cretTable();
			for(int i=2; i< st.length;i++)
				affec=itms.insertQuery(st[i]);
			itms.close();
			tvCT.setText(affec+" rows effected");
			
			/*
			st = it.printData("inventory");
			itms = new DBManager(this,st[1],"allitems",st[0]);
			itms.openToRead();
			s = itms.queueAll();
			itms.close();
			tvCT.setText(s);
			//*/
			
			String[] s = it.printData("players");
			//String[] inve = it.printData("player_inv");
			//String[] pSkill = it.printData("player_skills");
			//double[] majAtts;
			itms= new DBManager(this,s[1],"allplayer",s[0]);
			itms.openToRead();
			String mA="";
			mA=itms.queueAll(String.valueOf(1));
			itms.close();
			tvCT.setText(mA);
			
			/*
			String q="";
			//Integer[] d=sa.getAllInv();
			double[] d=sa.getMajatt();
			//Integer[] d=sa.getAllSkillsLvl();
			for(int i=0; i<d.length;i++)
				q=q+String.valueOf(d[i])+" ";
			tvCT.setText(q);
			*/
			/*
			String[] st1 = it.printData("players");
			itms = new DBManager(this,st1[1],"allplayer",st1[0]);
			//itms.recover(st1);
			
			itms.openToWrite();
			itms.dropTable();
			itms.cretTable();
			for(int i=2;i<st1.length;i++)
				itms.insertQuery(st1[i]);
			
			String s1 = itms.queueAll();
			//s1 =itms.colNamesChk();
			itms.close();	
			String[] st2 = s1.split(" ");
			//sa.setPlaAtts(st2);
			
			String[] st3 = it.printData("player_inv");
			itms = new DBManager(this,st3[1],"playersinv",st3[0]);
			//itms.recover(st3);
			
			itms.openToWrite();
			itms.dropTable();
			itms.cretTable();
			for(int i=2;i<st3.length;i++)
				itms.insertQuery(st3[i]);
			String s2 = itms.queueAll();
			//s2 = itms.colNamesChk();
			itms.close();	
			String[] st4 = s2.split(" ");
			//sa.setAllInv(st4);
			
			String[] st5 = it.printData("player_skills");
			itms = new DBManager(this,st5[1],"playerskill",st5[0]);
			//itms.recover(st5);
			itms.openToWrite();
			itms.dropTable();
			itms.cretTable();
			for(int i=2;i<st5.length;i++)
				itms.insertQuery(st5[i]);
			String s3 = itms.queueAll();
			//s3 = itms.colNamesChk();
			itms.close();	
			String[] st6 = s3.split(" ");
			//sa.setAllInv(st6);
			
			String[] st7 = it.printData("skills");
			itms = new DBManager(this,st7[1],"allskills",st7[0]);
			//itms.recover(st7);
			//itms.dropTable();
			itms.openToWrite();
			itms.dropTable();
			itms.cretTable();
			for(int i=2;i<st7.length;i++)
				itms.insertQuery(st7[i]);
			String s4 = itms.queueAll();
			//s4=itms.colNamesChk();
			itms.close();	
			String[] st8 = s4.split(" ");
			//sa.setSkills(st8,st6);
			tvCT.setText(s1+"\n"+s2+"\n"+s3+"\n"+s4);
			*/
			
			//break;
		}else if(arg0.getId()== R.id.bSkills){
			
			/*
			 *
			st = it.printData("player_skills");
			 
			itms = new DBManager(this,st[1],"playerskill",st[0]);
			itms.openToRead();
			s=itms.queueAll("1");
			itms.close();
			tvCT.setText(s);
			//*/
			/*
			st = it.printData("player_skills");
			itms = new DBManager(this,st[1],"playerskill",st[0]);
			itms.openToWrite();
			itms.cretTable();
			for(int i=2;i<st.length;i++)
				affec=itms.insertQuery(st[i]);
			itms.close();
			tvCT.setText(affec+" rows affected");
			//*/
			//String ss=pl.loadPlayerMaj(this, 1);
			pl = new Player(this,1);
			//tvCT.setText(String.valueOf(pl.id));
			/*
			 * Integer[] z=pl.calcAtts(new Integer[]{101,201,301,401});
			 
			String s1="";
			for(int i=0;i<z.length;i++)
				s1=s1+String.valueOf(z[i])+"\n";
			tvCT.setText(s1);
			*/
			//break;
		}
	}

	
	
}
