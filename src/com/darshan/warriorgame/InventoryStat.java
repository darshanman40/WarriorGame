package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class InventoryStat extends Activity{

	TextView tvInvStat;
	SharingAtts sa;
	String s;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invstat);
		
		sa=((SharingAtts)getApplication());
		tvInvStat = (TextView)findViewById(R.id.tvInStat);
		s="";
		
		try{
		Integer[] d = sa.getAllInv();
		for(int i=0; i<d.length;i++)
			s=s+d[i]+"\n";
		
		//DBManager itms;
		//ItemTest it = new ItemTest();
		//String[] st3 = it.printData("player_inv");
		//itms = new DBManager(this,st3[1],"playersinv",st3[0]);
		//itms.recover(st3);
		//itms.openToWrite();
		//itms.cretTable();
		//for(int i=2;i<st3.length;i++)
		//	itms.insertQuery(st3[i]);
		//String s2 = itms.queueAll(String.valueOf(sa.getId()));
		//itms.close();	
		//String[] st4 = s2.split(" ");
		}catch(Exception e){
			Toast t = Toast.makeText(getApplicationContext(), "nul data", Toast.LENGTH_LONG);
			t.show();
		}
		tvInvStat.setText(s+"\n"+sa.getId());
	}

	
}
