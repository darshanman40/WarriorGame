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

public class NGame extends Activity implements OnClickListener{

	SharedPreferences someData,filenames;
	Editor editor,fname;
	String filename;
	
	RadioGroup rgClass;
	Button bName, bClear;
	EditText etName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ngame);
		
		bName = (Button)findViewById(R.id.bEnter);
		//bClear = (Button)findViewById(R.id.bClear);
		//rgClass= (RadioGroup)findViewById(R.id.rgClass2);
		etName = (EditText)findViewById(R.id.etWName);
		
		bName.setOnClickListener(this);
		//bClear.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bEnter:
		//	int cheked = rgClass.getCheckedRadioButtonId();
		//	RadioButton rb =(RadioButton)findViewById(cheked);
			
		//	String pClass = rb.getText().toString();
			
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
			editor.commit();
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
			break;
		//case R.id.bClear:
		//	etName.setText("");
		//	break;
		}
	}

	
}
