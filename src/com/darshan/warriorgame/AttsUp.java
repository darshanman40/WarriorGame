package com.darshan.warriorgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AttsUp extends Activity implements OnClickListener{

	ImageView ivStr,ivSpd,ivMHp,ivMMana;
	TextView tvStr,tvSpd,tvMHp,tvMMana,tvTitle;
	SharingAtts sa;
	Player pl;
	Warrior war;
	private Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attsup);
		
		mHandler = new Handler();
		sa = ((SharingAtts)getApplication());
		
		ivStr = (ImageView)findViewById(R.id.ivStr4);
		ivSpd = (ImageView)findViewById(R.id.ivSpd4);
		ivMHp = (ImageView)findViewById(R.id.ivMHp4);
		ivMMana = (ImageView)findViewById(R.id.ivMMana4);
		
		tvStr = (TextView)findViewById(R.id.tvStr4);
		tvSpd = (TextView)findViewById(R.id.tvSpd4);
		tvMHp = (TextView) findViewById(R.id.tvMaxHp4);
		tvMMana = (TextView) findViewById(R.id.tvMaxMana4);
		tvTitle= (TextView) findViewById(R.id.tvTitle4);
		
		ivStr.setOnClickListener(this);
		ivSpd.setOnClickListener(this);
		ivMHp.setOnClickListener(this);
		ivMMana.setOnClickListener(this);
		String temp = tvStr.getText().toString();
		tvStr.setText("Str "+sa.str);
		//temp = tvSpd.getText().toString();
		tvSpd.append(" "+sa.speed);
		temp = tvMHp.getText().toString();
		tvMHp.setText(temp+" "+sa.maxHp);
		temp = tvMMana.getText().toString();
		tvMMana.setText(temp+" "+sa.maxMana);
		temp = tvTitle.getText().toString();
		tvTitle.setText(temp+"\nLevel = "+sa.lvl);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		sa.updateStat();
		switch(arg0.getId()){
		case R.id.ivStr4:
			sa.lvl++;
			pl= new Player(sa.playerClass, sa.lvl);
			war = new Warrior();
			sa.setPlaMajAtts(pl.newLevel(war, 0, sa.getPlaUpatt()));
			
			//String temp =tvStr.getText().toString();
			
			//finish();
			break;
			
		case R.id.ivSpd4:
			sa.lvl++;
			pl= new Player(sa.playerClass, sa.lvl);
			war = new Warrior();
			sa.setPlaMajAtts(pl.newLevel(war, 1, sa.getMajatt()));
			
			//String temp =tvStr.getText().toString();
			//tvSpd.setText("Speed "+sa.speed);
			//finish();
			break;
			
		case R.id.ivMHp4:
			sa.lvl++;
			pl= new Player(sa.playerClass, sa.lvl);
			war = new Warrior();
			sa.setPlaMajAtts(pl.newLevel(war, 2, sa.getMajatt()));
			
			//String temp =tvStr.getText().toString();
			//tvMHp.setText("Max Hp "+sa.maxHp);
			//finish();
			break;
			
		case R.id.ivMMana4:
			sa.lvl++;
			pl= new Player(sa.playerClass, sa.lvl);
			war = new Warrior();
			sa.setPlaMajAtts(pl.newLevel(war, 3, sa.getMajatt()));
			
			//String temp =tvStr.getText().toString();
			//tvMMana.setText("Max Mana "+sa.maxMana);
			//finish();
			break;
		}
		
		sa.updateStat();
		tvStr.setText("Str "+sa.str);
		tvSpd.setText("Speed "+sa.speed);
		tvMHp.setText("Max Hp "+sa.maxHp);
		tvMMana.setText("Max Mana "+sa.maxMana);
		//String temp = tvTitle.getText().toString();
		tvTitle.append("\nLevel = "+sa.lvl);
		mHandler.postDelayed(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent("com.darshan.warriorgame.skillup");
				startActivity(i);
				finish();
			}
			
		},5000);
		
		
	}

}
