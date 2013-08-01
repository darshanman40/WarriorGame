package com.darshan.warriorgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BattleStadium extends Activity implements OnClickListener{

	Button bTheif;
	Player pl,com;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battlestadium);
		
		bTheif = (Button)findViewById(R.id.bThief1);
		
		bTheif.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bThief1:
			//com = new Player(this, 2);
			i = new Intent("com.darshan.warriorgame.battlearena");
			i.putExtra("comId", 2);
			startActivity(i);
			
			break;
		
		}
	}

	
}
