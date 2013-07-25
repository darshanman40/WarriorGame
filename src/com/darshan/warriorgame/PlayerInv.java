package com.darshan.warriorgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;



public class PlayerInv {


	 public static final String MYDATABASE_NAME = "Inventory";
	 public static final String MYDATABASE_TABLE = "playersinv";
	 public static final int MYDATABASE_VERSION = 1;
	 public static final String KEY_CONTENT = "Content";

	 public String[] colNames; 
	 public String cols;
	 //create table MY_DATABASE (ID integer primary key, Content text not null);
	 private static final String SCRIPT_CREATE_DATABASE =
			 "CREATE TABLE 'playersinv' ('player_id' INTEGER PRIMARY KEY  NOT NULL  UNIQUE , 'inv_1' INTEGER, 'inv_2' INTEGER, 'inv_3' INTEGER,'inv_4' INTEGER, 'inv_5' INTEGER, 'inv_6' INTEGER, 'inv_7' INTEGER, 'inv_8' INTEGER, 'eq_inv_1' INTEGER, 'eq_inv_2' INTEGER, 'eq_inv_3' INTEGER, 'eq_inv_4' INTEGER, 'po_inv_1' INTEGER, 'po_inv_2' INTEGER);";
	 
	 private SQLiteHelper sqLiteHelper;
	 private SQLiteDatabase sqLiteDatabase;

	 private Context context;
	 
	 public PlayerInv(Context c, String colNames){
	  context = c;
	  cols=colNames;
	  this.colNames=colNames.split(" ");
	 }
	
	 public PlayerInv openToRead() throws android.database.SQLException {
	  sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
	  sqLiteDatabase = sqLiteHelper.getReadableDatabase();
	  return this; 
	 }
	 
	 public PlayerInv openToWrite() throws android.database.SQLException {
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
		  for(int i=0; i<15;i++)
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
		  sqLiteDatabase.execSQL("Drop Table If Exists 'playersinv'");
		  sqLiteDatabase.execSQL(SCRIPT_CREATE_DATABASE);
		 }
	 //-------------------------------------------------------------
	 public String queueAll(){
	 // String[] columns=colNames;
		 String result = "";
		 
		 try{
	  Cursor cursor = sqLiteDatabase.query("playersinv", new String[]{"player_id","inv_1","inv_2","inv_3","inv_4","inv_5","inv_6","inv_7","inv_8","eq_inv_1","eq_inv_2","eq_inv_3","eq_inv_4","po_inv_1","po_inv_2"},null, null, null, null, null);
	  
	  int index[] = new int[colNames.length];
	  
	  for(int i=0;i<colNames.length;i++)
		  index[i]= cursor.getColumnIndex(colNames[i]);
	  
	  
	  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
		  for(int i=0;i<colNames.length;i++)
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
		 Cursor cursor = sqLiteDatabase.rawQuery("SELECT sql FROM sqlite_master WHERE tbl_name = 'playersinv' AND type = 'table'", null);
		  int ind = cursor.getColumnIndex("sql");
		  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
			  res = res+cursor.getString(ind)+"\n";
		  }
		  
		 return res;
		 
	 }
	 
	 public void dropQueries(){
		sqLiteDatabase.execSQL("Drop Table If Exists 'playersinv'");
		
		 
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
		  db.rawQuery("Drop Table If Exists 'playersinv'", null);
		  onCreate(db);
	  }

	 }
	
}
