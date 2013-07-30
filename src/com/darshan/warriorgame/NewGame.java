package com.darshan.warriorgame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.darshan.warriorgame.Warrior;
import com.darshan.warriorgame.Player;

public class NewGame extends Activity implements OnClickListener{

	//SharedPreferences someData,filenames;
	SharingAtts sa;
	DBManager db,itms;
	ItemTest it;
	Player pl;
	//Editor editor,fname;
	//String filename;
	
	RadioGroup rgClass;
	Button bName, bClear;
	EditText etName,etConPass,etPass;
	
	int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgame);
		
		id=1;
		
		sa=((SharingAtts)getApplication());
		it = new ItemTest();
		try{
		bName = (Button)findViewById(R.id.bAccept);
		bClear = (Button)findViewById(R.id.bClear);
		rgClass= (RadioGroup)findViewById(R.id.rgClass2);
		etName = (EditText)findViewById(R.id.etName);
		etPass = (EditText)findViewById(R.id.etPass1);
		etConPass = (EditText)findViewById(R.id.etConPass1);
		}catch(Exception e){
			Toast t = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
			t.show();
			
		}
		bName.setOnClickListener(this);
		bClear.setOnClickListener(this);
		
		
		
	//-----------------------------------initialization-----------------------------	
		
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bAccept:
			
			  int cheked = rgClass.getCheckedRadioButtonId();
			 
			RadioButton rb =(RadioButton)findViewById(cheked);
			String pClass = rb.getText().toString();
			
			
			//pl = new Player(this, "Osairas","Samurai","zan");
			
			//Toast t = Toast.makeText(getApplicationContext(), pl.playerClass, Toast.LENGTH_LONG);
			//t.show();
			//*/
			
			try{
			
			 if(chkPass(etPass.getText().toString(),(etConPass.getText().toString()))){
				pl = new Player(this,etName.getText().toString(),pClass,etPass.getText().toString());
			//pl.newPlayer()
				
			//pl.newPlayer("Osairas", "Samurai");
			pl.newPlayer(etName.getText().toString(),pClass);
			
			String stat =pl.savePlayer(this, pl.getMajAtts(), pl.getAllInv(), pl.getSkilLvl());
			Toast t1 = Toast.makeText(getApplicationContext(), stat, Toast.LENGTH_LONG);
			t1.show();
			Intent in = new Intent("com.darshan.warriorgame.welcome");
			startActivity(in);
			// * 
			 //*/
			sa.setId(pl.id);
			//}catch(Exception e){
			//	Toast t2 = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
			//	t2.show();
			//}
			
				//
			}else{
				Toast t = Toast.makeText(getApplicationContext(), "err", Toast.LENGTH_LONG);
				t.show();
			}}catch(Exception e){
			//		Toast t2 = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
				//	t2.show();
				}
			
				
			//}
			
			/*
			filename=etName.getText().toString();
			someData=getSharedPreferences(filename,0);
			filenames=getSharedPreferences("players",0);
			editor = someData.edit();
			fname = filenames.edit();
			editor.putString("playername",filename);
			fname.putString("player_1", filename);
			
			editor.putInt(filename+"_Level", 1);
			editor.putString(filename+"_Class", pClass);
			editor.putInt(filename+"_exp", 0);
			editor.putInt(filename+"_maxExp", 200);
			try{
			Battle b = new Battle(1,1,"Samurai",pClass);
			Integer[] p1Att =(Integer[]) b.playerData();
			
			for(int i=0;i<8;i++)
				editor.putInt(filename+"_att_"+i,p1Att[i]);
			}catch(Exception e){
				Toast t =Toast.makeText(getApplicationContext(), "err "+e, Toast.LENGTH_LONG);
				t.show();
			}
			editor.commit();
			fname.commit();
			try{
			Intent i = new Intent("com.darshan.warriorgame.welcome");
			i.putExtra("filename", filename);
			startActivity(i);
			}catch(Exception e){
				Toast t2 = Toast.makeText(getApplicationContext(), "err2", Toast.LENGTH_SHORT);
				t2.show();
			}
			finish();
			
			*/
			
			break;
		case R.id.bClear:
			etName.setText("");
			break;
		}
	}
	
	public boolean chkPass(String pass, String anPas){
		if(!pass.equals(anPas)){
			return false;
		}else{
			return true;
		}
		
	}
	
	
	
	
	/*
	public void newPlayer(){
		it= new ItemTest();
		//String[] st = it.printData("players");
		//itms = new DBManager(this,st[1],"allplayer",st[0]);
		//itms.recover(st);
		String s,s2,s3,s4,s5,s6;
		s="asshole";
		s2="inv";
		s3="pskils";
		s4="skilid";
		String[] st1 = it.printData("players");
		itms = new DBManager(this,st1[1],"allplayer",st1[0]);
		//itms.recover(st1);
		itms.openToWrite();
		
		//for(int i=2;i<st1.length;i++)
		//	itms.insertQuery(st1[i]);
		 s = itms.queueAll(String.valueOf(id));
		itms.close();	
		String[] st2 = s.split(" ");
		sa.setPlaAtts(st2);
		try{
		String[] st3 = it.printData("player_inv");
		itms = new DBManager(this,st3[1],"playersinv",st3[0]);
		//itms.recover(st3);
		itms.openToWrite();
		//itms.cretTable();
		//for(int i=2;i<st3.length;i++)
		//	itms.insertQuery(st3[i]);
		s2 = itms.queueAll(String.valueOf(id));
		itms.close();	
		String[] st4 = s2.split(" ");
		sa.setAllInv(st4);
		
		String[] st5 = it.printData("player_skills");
		itms = new DBManager(this,st5[1],"playerskill",st5[0]);
		//itms.recover(st5);
		itms.openToWrite();
		//itms.cretTable();
		//for(int i=2;i<st5.length;i++)
		//	itms.insertQuery(st5[i]);
		s3 = itms.queueAll(String.valueOf(id));
		itms.close();	
		String[] st6 = s3.split(" ");
		//sa.setAllInv(st6);
		
		String[] st7 = it.printData("skills");
		itms = new DBManager(this,st7[1],"allskills",st7[0]);
		//itms.recover(st7);
		itms.openToWrite();
		//itms.cretTable();
		//for(int i=2;i<st7.length;i++)
		//	itms.insertQuery(st7[i]);
		s4 = itms.queueFew(new String[]{"skill_id"});
		itms.close();	
		s4 = s4.replace(" ", "-");
		String[] st8 = s4.split("-");
		//for(int i=1; i<st6.length;i++)
		//	st6[i-1]=st6[i].trim();
		//st6[st6.length-1]="";
			
		sa.setSkills(st8,st6);
		Toast t = Toast.makeText(getApplicationContext(), "Congratz", Toast.LENGTH_LONG);
		t.show();
		}catch(Exception e){
			System.err.print(e);
			Toast t = Toast.makeText(getApplicationContext(),s3+"   "+e.toString(), Toast.LENGTH_LONG);
			t.show();
		}
	}
	/*public boolean chkUserName(String name){
		
		//it = new ItemTest();
		String[] s = it.printData("players");
		db = new DBManager(this,s[1],"allplayer",s[0]);
		db.openToRead();
		
		
		/*if(!pass.equals(anPas)){
			return false;
		}else{
			return true;
		}
		 
		 */
		//return true;
	//}
	
}
