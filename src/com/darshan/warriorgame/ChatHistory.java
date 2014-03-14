package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ChatHistory extends Activity{

	
	TextView hist;
	ItemTest it;
	DBManager dbm;
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_history);
		
		it = new ItemTest();
		
		hist = (TextView)findViewById(R.id.tvHistory);
		dbm = new DBManager(ChatHistory.this,it.printData("chat")[1],"chat",it.printData("chat")[0]);
		
		dbm.openToRead();
		hist.setText("");
		String[] rows = dbm.queueAll().split("\n");
		for(int i=0; i< rows.length;i++){
			 hist.append(rows[i].split(" ")[1]+" says, "+rows[i].split(" ")[2]+
					 " to "+rows[i].split(" ")[3]+"\n \t at time: "+rows[i].split(" ")[4]+"\n");
		}
		dbm.close();
	}

	
	
}
