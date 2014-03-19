package com.darshan.warriorgame;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChatHistory extends Activity{

	
	TextView hist;
	ListView plist;
	
	ItemTest it;
	DBManager dbm;
	SharingAtts sa;
	
	List<String> players; 
	
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
		sa = ((SharingAtts)getApplication());
		
		hist = (TextView)findViewById(R.id.tvHistory);
		plist = (ListView)findViewById(R.id.lvPlayers3);
		dbm = new DBManager(ChatHistory.this,it.printData("chat")[1],"chat",it.printData("chat")[0]);
		
		dbm.openToRead();
		hist.setText("");
		String[] rows = dbm.directQuery("SELECT DISTINCT sender FROM chat where receiver LIKE '"+sa.name+"'", new String[]{"sender"}).replace(","," ").split("\n");
		String[] rows2= dbm.directQuery("SELECT DISTINCT receiver FROM chat where sender LIKE '"+sa.name+"'", new String[]{"receiver"}).replace(","," ").split("\n");
		dbm.close();
		
		players = new ArrayList<String>();
		for(int i =0;i< rows.length;i++){
			Log.d("rows_"+i, rows[i]);
			players.add(rows[i].replace(" ", ""));
		}
		
		for(int i=0;i<rows2.length;i++){
			Log.d("rows2_"+i, rows2[i]);
			rows2[i] = rows2[i].replace(" ", "");
			if(!players.contains(rows2[i])){
				players.add(rows2[i]);
			}
		}
		
		   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		              android.R.layout.simple_list_item_1, android.R.id.text1, players);
		   plist.setAdapter(adapter);
		   plist.setOnItemClickListener(new OnItemClickListener(){
			   //String selectedPlayer;
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				//int itemPosition = position;
                
                // ListView Clicked item value
				String selectedPlayer = players.get(position);
				Intent i = new Intent(ChatHistory.this,ChatActivity.class);
				Log.d("selecedPlayer",selectedPlayer);
				i.putExtra("challenged", selectedPlayer);
				i.putExtra("user", sa.name);
				startActivity(i);
				finish();
			}
			   
		   });
		   
		for(int i=0; i< players.size();i++){
			//if(!rows[i].contains(sa.name)) 
				hist.append(players.get(i)+"\n");
		}
		/*
		for(int i=0; i< rows.length;i++){
			 hist.append(rows[i].split(" ")[1]+" says, "+rows[i].split(" ")[2]+
					 " to "+rows[i].split(" ")[3]+"\n \t at time: "+rows[i].split(" ")[4]+"\n");
		}
		*/

	}

	
	
}
