package com.darshan.warriorgame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;



public class Status extends Activity{

	TextView tvName,tvStr,tvSpeed,tvMaxHp,tvMaxMana,tvMaxXp,tvGold;
	//SharedPreferences someData;
	String filename;
	
	SharingAtts sa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		//filename = getIntent().getStringExtra("filename");
		
		tvName = (TextView)findViewById(R.id.tvName);
		tvStr = (TextView)findViewById(R.id.tvStr);
		tvSpeed = (TextView)findViewById(R.id.tvSpeed);
		tvMaxHp = (TextView)findViewById(R.id.tvMaxHp);
		tvMaxMana = (TextView)findViewById(R.id.tvMaxMana);
		tvMaxXp = (TextView)findViewById(R.id.tvMaxExp);
		tvGold = (TextView)findViewById(R.id.tvGold);
		
		sa=((SharingAtts)getApplication());
		
		String s="";
		double[] atts = sa.getMajatt();
		s=tvName.getText().toString();
		tvName.setText(s+" "+sa.getName());
		
		s=tvStr.getText().toString();
		tvStr.setText(s+String.valueOf(atts[0]));
		
		s=tvSpeed.getText().toString();
		tvSpeed.setText(s+String.valueOf(atts[1]));
		
		s=tvMaxHp.getText().toString();
		tvMaxHp.setText(s+String.valueOf(atts[2]));
		
		s=tvMaxMana.getText().toString();
		tvMaxMana.setText(s+String.valueOf(atts[3]));
		
		s=tvMaxXp.getText().toString();
		tvMaxXp.setText(s+String.valueOf(atts[4]));
		
		s=tvGold.getText().toString();
		tvGold.setText(s+String.valueOf(atts[8]));
		
		Integer[] tt =sa.getAllSkills();
		Integer[] t1 = sa.getAllSkillsLvl();
		s="";
		for(int i=0; i<t1.length;i++)
			s=s+String.valueOf(tt[i])+"-"+t1[i]+"\n";
		
		ItemTest it = new ItemTest();
		String[] sss = it.printData("player_skills");
		DBManager db = new DBManager(this,sss[1],"playerskill",sss[0]);
		db.openToWrite();
		db.deleteAll();
		db.insertQuery("1 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20");
	
		String s1 =db.queueAll(String.valueOf(sa.getId()));
		String s3 = db.tblNamesChk();
		db.close();
		
		s1=s1.replace(" ", "-");
		sss[1]=sss[1].replace(" ", "-");
		String[] y1 = s1.split("-");
		String[] y2 = sss[1].split("-");
		for(int i=0; i<y2.length;i++)
			s=s+(y1[i])+" - "+y2[i]+"\n";
		
		int i=0;
		Integer[] a= new Integer[y1.length];
		try{
		for(i=0; i<y1.length;i++){
			a[i]=Integer.valueOf(y1[i]);
		}
		}catch(Exception e){
			Toast t =Toast.makeText(getApplicationContext(), e.toString()+i, Toast.LENGTH_LONG);
			t.show();
		}
		//*/
		//tvGold.setText(s+"\n"+s1+"\n"+sss[2].replace(" ", "-")+"\n"+s3);
		tvGold.setText(s+i);
		//*/
		
		
	}

	
	
}
