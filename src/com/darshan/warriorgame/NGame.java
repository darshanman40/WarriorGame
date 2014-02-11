package com.darshan.warriorgame;

import java.util.Hashtable;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class NGame extends Activity implements OnClickListener{

	Hashtable<String,String[]> allSkills,allItms;
	ItemTest it;
	DBManager itms;
	SharingAtts sa;
	
	String filename;
	String[] st2;
	//SharedPreferences someData,filenames;
	Editor editor,fname;
	RadioGroup rgClass;
	Button bName, bClear;
	EditText etName;
	TextView t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ngame);
		
		
		sa = ((SharingAtts)getApplication());
		allSkills = new Hashtable<String,String[]>();
		allItms = new Hashtable<String,String[]>();
		bName = (Button)findViewById(R.id.bEnter);
		//bClear = (Button)findViewById(R.id.bClear);
		//rgClass= (RadioGroup)findViewById(R.id.rgClass2);
		etName = (EditText)findViewById(R.id.etWName);
		t = (TextView) findViewById(R.id.tvHTTest);
		
		bName.setOnClickListener(this);
		//bClear.setOnClickListener(this);
		
		it = new ItemTest();
		String[] st7 = it.printData("skills");
		itms = new DBManager(this,st7[1],"allskills",st7[0]);
		itms.openToWrite();
		String ss1 = itms.queueAll();
		itms.close();
		String[] stt = ss1.split("\n");
		for(int i=0;i<stt.length;i++){
			String[] sss1 = stt[i].split(" ");
			allSkills.put(sss1[0], sss1);
		}
		
		sa.setAllSkills(allSkills);
		
		String[] st9 = it.printData("inventory");
		
		itms = new DBManager(this,st9[1],"allitems",st9[0]);
		itms.openToWrite();
		itms.dropTable();
		itms.cretTable();
		for(int i=2;i<st9.length;i++)
			itms.insertQuery(st9[i]);
		String ss2 = itms.queueAll();
		itms.close();	
		
		String[] st10 = ss2.split("\n");
		for(int i=0;i<st10.length;i++){
			String[] sss1 = st10[i].split(" ");
			allItms.put(sss1[0], sss1);
		}
		sa.setAllItms(allItms);		
		
		t.setText(st9[0]+"  "+st9[1]);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	if(v.getId()==R.id.bEnter){
			String[] m=sa.getAllItms("101");
			String r="";
			for(String h:m)
				r=r+h+" ";
			t.setText(r);
		/*	String s="";
			for(String t:test.keySet()){
				s=s+t+"-";
				for(int i=1; i<test.get(t).length;i++)
					s=s+test.get(t)[i]+" ";
				s=s+"\n";
				
			}
			t.setText(s);
			*/
			//break;
			
		//	int cheked = rgClass.getCheckedRadioButtonId();
		//	RadioButton rb =(RadioButton)findViewById(cheked);
			
		//	String pClass = rb.getText().toString();
			/*
			filename=etName.getText().toString();
			someData=getSharedPreferences(filename,0);
			filenames=getSharedPreferences("players",0);
			editor = someData.edit();
			fname = filenames.edit();
			editor.putString("playername",filename);
			fname.putString("player_1", filename);
			/*
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
			}*/
			/*editor.commit();
			fname.commit();
			try{
			Intent i = new Intent("com.darshan.warriorgame.classnopp");
			i.putExtra("filename", filename);
			startActivity(i);
			}catch(Exception e){
				Toast t2 = Toast.makeText(getApplicationContext(), "err2", Toast.LENGTH_SHORT);
				t2.show();
			}
			finish();
			*/
			
		//case R.id.bClear:
		//	etName.setText("");
		//	break;
		}
	}

	
}
