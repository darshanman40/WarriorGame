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
	//private SQLiteAdapter mySQLiteAdapter;
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
		Player pl = new Player(this,1);
		sa=((SharingAtts)getApplication());
		sa.setName("Dragon");
		//s="";
		//String gg=pl.loadPlayerMaj(this,1);
		//try{
		//String[] ggg=pl.allSkills();
		
//		for(int i=0;i<gg.length;i++)
	//		s=s+gg[i];
			
		sa.setPlaAtts(pl.loadPlayerMaj(this, 1));
		
		sa.setAllInv(pl.loadPlayerInv());
		//Integer[] skl = new Integer[(int)((pl.allSkills().length)/10)];
		//Integer[] sklvl = new Integer[(int)((pl.allSkills().length)/10)];
		String[] sas = pl.allSkills();
		String[] sb = pl.loadPlayerSkills();
		String[] sasa = new String[(int)(sas.length/10)];
		s="";
		for(int i=0; i< sas.length/10;i++){
			sasa[i]=sas[i*10].replace("\n", "");
			s=s+sasa[i]+".";
		}
		//String[] sasa=s.split(".");
		sa.setSkills(sasa,sb);
		
		//*/
		//}catch(Exception e){
		//	tvCT.setText(e.toString());//+gg[0]+" "+gg[1]);
		//	
		//}
		tvCT.setText(String.valueOf(s));
    }
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		sa=((SharingAtts)getApplication());
		it = new ItemTest();
		long affec=0;
		switch(arg0.getId()){
		case R.id.bPInv:
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
			
			break;
			
		case R.id.bPlayers:
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
			/*
			st = it.printData("players");
			itms = new DBManager(this,st[1],"allplayer",st[0]);
			String rows="";
			try{
			Player pl =new Player(this,1);
			
			//rows=pl.savePlayer(this, new String[]{"1","Osairas","Samurai","1","17","15","80","70","150","0","250"}, null, null);
			itms.openToRead();
			s = itms.queueAll();
			itms.close();
			}catch(Exception e){
				System.err.print(e);
				tvCT.setText(e.toString());
			}
			
			tvCT.setText(sa.getNameOnly());
			*/
			//*/W
			//tvCT.setText(rows);//+" rows are affected");
			//*/
			/*/
			 * 
			 */
			String[] pla = new String[]{"1","Osairas","Samurai","2","17","15","80","70","150","80","70","0","1000"};
			String[] inv = new String[]{"1","0","0","0","0","0","0","0","0","101","201","301","401","10","10"};
			String[] skills = new String[]{"1","1","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
			Player p =new Player(this,1);
			s=p.savePlayer(this, pla, inv, skills);
			if(s.compareTo("")!=0)
				//tvCT.setText(affec+" rows arWe affected");
				tvCT.setText(s);
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
			break;
		case R.id.bItems:
			/*
			st = it.printData("inventory");
			itms = new DBManager(this,st[1],"allitems",st[0]);
			itms.openToWrite();
			itms.cretTable();
			for(int i=2; i< st.length;i++)
				affec=itms.insertQuery(st[i]);
			itms.close();
			tvCT.setText(affec+" rows effected");
			*/
			/*
			st = it.printData("inventory");
			itms = new DBManager(this,st[1],"allitems",st[0]);
			itms.openToRead();
			s = itms.queueAll();
			itms.close();
			tvCT.setText(s);
			//*/
			/*
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
			*/
			String q="";
			//Integer[] d=sa.getAllInv();
			//Integer[] d=sa.getAllSkills();
			Integer[] d=sa.getAllSkillsLvl();
			for(int i=0; i<d.length;i++)
				q=q+String.valueOf(d[i])+" ";
			tvCT.setText(q);
			break;
		case R.id.bSkills:
			
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
			tvCT.setText(String.valueOf(pl.id));
			
			break;
		}
	}

	
	
}
