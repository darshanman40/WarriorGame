package com.darshan.warriorgame;

import android.content.Context;

public class SaveGame {

	
	Context context;
	DBManager db,itms;
	ItemTest it;
	public SaveGame(Context context){
		this.context=context;
		it= new ItemTest();
	}
	
	public void saveInv(String content, String where){
		String[] s2=it.printData("player_inv");
		db = new DBManager(context,s2[1],"playersinv",s2[0]);
		db.openToWrite();
		db.updateTable(content, "player_id= "+where);
		db.close();
	}
	
	
	public void savePlayerSkills(String content, String where){
		String[] s2=it.printData("player_skills");
		db = new DBManager(context,s2[1],"playerskill",s2[0]);
		db.openToWrite();
		db.updateTable(content, "player_id= "+where);
		db.close();
		
	}
	
	public void savePlayerAtts(String content, String where){
		String[] s2=it.printData("players");
		db = new DBManager(context,s2[1],"allplayer",s2[0]);
		db.openToWrite();
		db.updateTable(content, new String[]{"level","str","speed","max_hp","max_mana","max_xp","hp","mana","xp","gold","remSkilPts"}, "player_id= "+where);
		db.close();
		
	}
}
