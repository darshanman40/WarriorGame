package com.darshan.warriorgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

//import com.darshan.warriorgame.SkillList.SQLiteHelper;

public class PlayerTables {
	
		 public static final String MYDATABASE_NAME = "Inventory";
		 public static final String MYDATABASE_TABLE = "allplayer";
		 public static final int MYDATABASE_VERSION = 1;
		 public static final String KEY_CONTENT = "Content";

		 public String[] colNames; 
		 public String cols;
		 //create table MY_DATABASE (ID integer primary key, Content text not null);
		 private static final String SCRIPT_CREATE_DATABASE =
				 "CREATE TABLE 'allplayer' ('player_id' INTEGER PRIMARY KEY  NOT NULL  UNIQUE , 'player_name' VARCHAR, 'player_class' VARCHAR, 'level' DOUBLE,'str' DOUBLE, 'speed' DOUBLE, 'max_hp' DOUBLE, 'max_mana' DOUBLE, 'max_xp' DOUBLE, 'xp' DOUBLE);";
		 
		 private SQLiteHelper sqLiteHelper;
		 private SQLiteDatabase sqLiteDatabase;

		 private Context context;
		 
		 public PlayerTables(Context c, String colNames){
		  context = c;
		  cols=colNames;
		  this.colNames=colNames.split(" ");
		 }
		
		 public PlayerTables openToRead() throws android.database.SQLException {
		  sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
		  sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		  return this; 
		 }
		 
		 public PlayerTables openToWrite() throws android.database.SQLException {
		  sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
		  sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		  return this; 
		 }
		 
		 public void close(){
		  sqLiteHelper.close();
		 }
		 
		 public long insertQuery(String content){
			 //try{
			 String atts[] = content.split(" "); 
			  ContentValues contentValues = new ContentValues();
			  try{
			  for(int i=0; i<10;i++)
				  contentValues.put(colNames[i], atts[i]);
			  return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
			 }catch(Exception e){
				 System.err.print(e+" colName6= "+colNames[6]);
			 }
			  return 0;
		 }
			 
		 //}
		 
		 public int deleteAll(){
		  return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
		 }
		 public void dropTable(){
			  sqLiteDatabase.execSQL("Drop Table If Exists 'allplayer'");
			  sqLiteDatabase.execSQL(SCRIPT_CREATE_DATABASE);
			 }
		 //-------------------------------------------------------------
		 public String queueAll(){
		 // String[] columns=colNames;
			 String result = "";
			 
			 try{
		  Cursor cursor = sqLiteDatabase.query("allplayer", new String[]{"player_id","player_name","player_class","level","str","speed","max_hp","max_mana","max_xp","xp"},null, null, null, null, null);
		  
		  int index[] = new int[10];
		  
		  for(int i=0;i<10;i++)
			  index[i]= cursor.getColumnIndex(colNames[i]);
		  
		  
		  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
			  for(int i=0;i<10;i++)
				  result = result + cursor.getString(index[i])+" ";// + " "+cursor.getString(index[1]) + "\n";
			  result=result+"\n";
		  }
			 }catch(Exception e){
				 System.err.print(e+" colNames6= "+colNames[6]);
			 }
		 
		  return result;
		 }
		 //---------------------------------------------------------------------------
		 public String colNamesChk(){
			 
			 
			 String res="";
			 Cursor cursor = sqLiteDatabase.rawQuery("SELECT sql FROM sqlite_master WHERE tbl_name = 'allplayer' AND type = 'table'", null);
			  int ind = cursor.getColumnIndex("sql");
			  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
				  res = res+cursor.getString(ind)+"\n";
			  }
			  
			 return res;
			 
		 }
		 
		 public void dropQueries(){
			sqLiteDatabase.execSQL("Drop Table If Exists 'allplayer'");
			
			 
		 }
		 public void cretQueries(){
			 sqLiteDatabase.execSQL(SCRIPT_CREATE_DATABASE);	 
		}
		 
		 public class SQLiteHelper extends SQLiteOpenHelper {

		  public SQLiteHelper(Context context, String name,CursorFactory factory, int version) {
		   super(context, name, factory, version);
		  }

		  @Override
		  public void onCreate(SQLiteDatabase db) {
		   // TODO Auto-generated method stub
			 // db.rawQuery("Drop Table If Exists 'skills'", null);
		   db.execSQL(SCRIPT_CREATE_DATABASE);
		  }

		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		   // TODO Auto-generated method stub
			  db.rawQuery("Drop Table If Exists 'allplayer'", null);
			  onCreate(db);
		  }

		 }
		 
}

