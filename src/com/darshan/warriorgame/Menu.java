package com.darshan.warriorgame;

import java.util.ArrayList;
import java.util.Collection;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Menu extends ListActivity{

	SharedPreferences filenames;
	
	
	String[] classes={"NGame","New Game","Load Game","Class N Opp","BattleArena","CsvTest"};
	//classes[4];
	
	
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		//return super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		//blowUp.inflate(R.menu.cool_menu, menu);
	
		return true;
		
	}

	
/*	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case R.id.aboutUs:
			Intent i=new Intent("com.manchekar.darshan.total.ABOUT");
			startActivity(i);
			break;
		case R.id.preferences:
			Intent p= new Intent("com.manchekar.darshan.total.PREFS");
			startActivity(p);
			break;
		case R.id.exit:
			finish();
			break;
		
		}
		return false;
		
		//return super.onOptionsItemSelected(item);
	}

*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		filenames = getSharedPreferences("players",0);
		//String p1 = filenames.getString("player_1", null);
		//ArrayList<String> menu = new ArrayList();
		
		//if(p1!=null){
		//	menu.add(p1);
		//	for(int i=0;i<classes.length;i++)
		//		menu.add(classes[i]);
		//setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,menu));
		//}else{
			setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,classes));
		//}
		
		
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		String cheese=classes[position];
		super.onListItemClick(l, v, position, id);
		try{
			Class ourClass=Class.forName("com.darshan.warriorgame."+cheese.replaceAll("\\s",""));
			Intent ourIntent=new Intent(Menu.this,ourClass);
			startActivity(ourIntent);
			finish();
		}catch(ClassNotFoundException e){
			//Class ourClass=Class.forName("com.darshan.warriorgame.PlayGame");
			Intent ourIntent=new Intent("com.darshan.warriorgame.welcome");
			startActivity(ourIntent);
			finish();
			e.printStackTrace();}
	
	}

	
	
}
