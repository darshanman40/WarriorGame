package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SkillUp extends Activity implements OnClickListener{

	ImageView PS1,PS2,PS3,PS4,PS5,PS6,PS7,PS8,PS9,MS1,MS2,MS3,MS4,MS5,MS6,MS7,MS8,MS9,MS10;
	TextView Detail,P1,P2,P3,P4,P5,P6,P7,P8,P9,M1,M2,M3,M4,M5,M6,M7,M8,M9,M10;
	TextView[] skilLvl;// = {P1,P2,P3,P4,P5,P6,P7,P8,P9,M1,M2,M3,M4,M5,M6,M7,M8,M9,M10};
	Button bSkillUp;
	SharingAtts sa;
	int selectedSId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skillup);
		
		sa = ((SharingAtts)getApplication());
		PS1 = (ImageView)findViewById(R.id.ivPhS1);
		PS2 = (ImageView)findViewById(R.id.ivPhS2);
		PS3 = (ImageView)findViewById(R.id.ivPhS3);
		PS4 = (ImageView)findViewById(R.id.ivPhS4);
		PS5 = (ImageView)findViewById(R.id.ivPhS5);
		PS6 = (ImageView)findViewById(R.id.ivPhS6);
		PS7 = (ImageView)findViewById(R.id.ivPhS7);
		PS8 = (ImageView)findViewById(R.id.ivPhS8);
		PS9 = (ImageView)findViewById(R.id.ivPhS9);
		MS1 = (ImageView)findViewById(R.id.ivMgS1);
		MS2 = (ImageView)findViewById(R.id.ivMgS2);
		MS3 = (ImageView)findViewById(R.id.ivMgS3);
		MS4 = (ImageView)findViewById(R.id.ivMgS4);
		MS5 = (ImageView)findViewById(R.id.ivMgS5);
		MS6 = (ImageView)findViewById(R.id.ivMgS6);
		MS7 = (ImageView)findViewById(R.id.ivMgS7);
		MS8 = (ImageView)findViewById(R.id.ivMgS8);
		MS9 = (ImageView)findViewById(R.id.ivMgS9);
		MS10 = (ImageView)findViewById(R.id.ivMgS10);
		P2 = (TextView)findViewById(R.id.tvPS2);
		P3 = (TextView)findViewById(R.id.tvPS3);
		P4 = (TextView)findViewById(R.id.tvPS4);
		P5 = (TextView)findViewById(R.id.tvPS5);
		P6 = (TextView)findViewById(R.id.tvPS6);
		P7 = (TextView)findViewById(R.id.tvPS7);
		P8 = (TextView)findViewById(R.id.tvPS8);
		P9 = (TextView)findViewById(R.id.tvPS9);
		M2 = (TextView)findViewById(R.id.tvMS2);
		M3 = (TextView)findViewById(R.id.tvMS3);
		M4 = (TextView)findViewById(R.id.tvMS4);
		M5 = (TextView)findViewById(R.id.tvMS5);
		M6 = (TextView)findViewById(R.id.tvMS6);
		M7 = (TextView)findViewById(R.id.tvMS7);
		M8 = (TextView)findViewById(R.id.tvMS8);
		M9 = (TextView)findViewById(R.id.tvMS9);
		M10 = (TextView)findViewById(R.id.tvMS10);
		Detail = (TextView)findViewById(R.id.tvSkillDetail);
		
		
		bSkillUp = (Button)findViewById(R.id.bSkillUp);
		
		
		PS2.setOnClickListener(this);
		MS2.setOnClickListener(this);
		bSkillUp.setOnClickListener(this);
		
		skilLvl = new TextView[]{P1,P2,P3,P4,P5,P6,P7,P8,P9,M1,M2,M3,M4,M5,M6,M7,M8,M9,M10};
		selectedSId=0;
	}
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bSkillUp:
			//if((selectedSId-1)>=0)
			sa.skilllvl[selectedSId]++;
			break;
			
		case R.id.ivPhS2:
			selectedSId = 1;
			Detail.setText("Double Strike\nRequired Mana 20\n Next lvl 8% extra physical damage");
			break;
		case R.id.ivMgS2:
			selectedSId = 10;
			Detail.setText("Energy Shot\nRequired Mana 25\n Next lvl 49 extra magical damage");
			break;
		}
		
		skilLvl[selectedSId].setText(sa.skilllvl[selectedSId].toString()); 
	}

	

}
