package com.darshan.warriorgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLiteAdapter {

	 public static final String MYDATABASE_NAME = "Inventory";
	 public static final String MYDATABASE_TABLE = "allitems";
	 public static final int MYDATABASE_VERSION = 1;
	 public static final String KEY_CONTENT = "Content";

	 public String[] colNames; 
	 public String cols;
	 //create table MY_DATABASE (ID integer primary key, Content text not null);
	 private static final String SCRIPT_CREATE_DATABASE =
			 "CREATE TABLE 'allitems' ('item_id' INTEGER PRIMARY KEY  NOT NULL  UNIQUE , 'item' VARCHAR, 'str' DOUBLE, 'ph_dam' DOUBLE, 'mag_dam' DOUBLE, 'ph_def' DOUBLE, 'mag_def' DOUBLE, 'e_s_dam' DOUBLE, 's_hp' DOUBLE, 'b_mana' DOUBLE, 'speed' DOUBLE, 'cost' DOUBLE, 'selling_price' DOUBLE, 'hp_plus' DOUBLE, 'mana_plus' DOUBLE);";
	 
	 private SQLiteHelper sqLiteHelper;
	 private SQLiteDatabase sqLiteDatabase;

	 private Context context;
	 
	 public SQLiteAdapter(Context c, String colNames){
	  context = c;
	  cols=colNames;
	  this.colNames=colNames.split(" ");
	 }
	/* public SQLiteAdapter(Context c){
		  context = c;
		 }
	 */
	 public SQLiteAdapter openToRead() throws android.database.SQLException {
	  sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
	  sqLiteDatabase = sqLiteHelper.getReadableDatabase();
	  return this; 
	 }
	 
	 public SQLiteAdapter openToWrite() throws android.database.SQLException {
	  sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
	  sqLiteDatabase = sqLiteHelper.getWritableDatabase();
	  return this; 
	 }
	 
	 public void close(){
	  sqLiteHelper.close();
	 }
	 
/*	 public long insert(String content){
	  
	  ContentValues contentValues = new ContentValues();
	  contentValues.put(KEY_CONTENT, content);
	  return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
	 }
	*/ 
	 public long insertQuery(String content){
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
		  sqLiteDatabase.execSQL("Drop Table If Exists 'allitems'");
		  sqLiteDatabase.execSQL(SCRIPT_CREATE_DATABASE);
		 }
	 
	 public String queueAll(){
	 String[] columns=colNames;
		 String result = "";
		 
		 try{
	  Cursor cursor = sqLiteDatabase.query("allitems",columns,null,null,null,null,null);
			 // new String[]{"item_id","item","str","ph_dam","mag_dam","ph_def","mag_def","e_s_dam","s_hp","b_mana","speed","cost","selling_price","hp_plus","mana_plus"},null, null, null, null, null);
			// Cursor cursor = sqLiteDatabase.rawQuery("select 'item' from 'allitems'",null);
	  int index[] = new int[colNames.length];
	  
	  for(int i=0;i<colNames.length;i++)
		  index[i]= cursor.getColumnIndex(colNames[i]);
	  
	  //int index_1 = cursor.getColumnIndex(colNames[0]);
	  //int index_2 = cursor.getColumnIndex(colNames[1]);
	  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
		  for(int i=0;i<15;i++)
			  result = result + cursor.getString(index[i])+" ";// + " "+cursor.getString(index[1]) + "\n";
		  result=result+"\n";
	  }
	 
	  
	 // for(int i=0; i< colNames.length;i++)
		//  result= result + colNames[i]+" ";
	  
	  return result;
		 }catch(Exception e){
		 System.err.print(e+" colNames6= "+colNames[6]);
		 }
		 return null;
	 }
	 
	 public String colNamesChk(){
		 
		 
		 String res="";
		 Cursor cursor = sqLiteDatabase.rawQuery("SELECT sql FROM sqlite_master WHERE tbl_name = 'allitems' AND type = 'table'", null);
		  int ind = cursor.getColumnIndex("sql");
		  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
			  res = res+cursor.getString(ind)+"\n";
		  }
		  
		 return res;
		 
	 }
	 public double[] getItemAtts(Integer[] eqps){
		 double[] atts = {0,0,0,0,0,0,0,0,0,0,0,0,0};
		 
		 String[] colNames={"item_id","str","ph_dam","mag_dam","ph_def","mag_def","e_s_dam","s_hp","b_mana","speed"};
		 try{
			 Cursor c = sqLiteDatabase.query("allitems", new String[]{"item_id","str","ph_dam","mag_dam","ph_def","mag_def","e_s_dam","s_hp","b_mana","speed"}, null, null,null, null, null);
		 
			 
			 int[] index = new int[colNames.length];
		 
			 for(int j=0;j<colNames.length;j++)
				  index[j]= c.getColumnIndex(colNames[j]);
			 
			 for(c.moveToFirst(); !(c.isAfterLast()); c.moveToNext()){
				 
				  if(c.getInt(index[0]) == eqps[0] || c.getInt(index[0]) == eqps[1]|| c.getInt(index[0]) == eqps[2] || c.getInt(index[0]) == eqps[3]){
					 
				 for(int j=1;j<colNames.length;j++)
					 atts[j-1] = atts[j-1]+Double.parseDouble(c.getString(index[j]));
				
			}
		 }
		 }catch(Exception e){
			 System.err.print(e);
		 }
		 return atts;
	 }
	 
	 
	 public class SQLiteHelper extends SQLiteOpenHelper {

	  public SQLiteHelper(Context context, String name,
	    CursorFactory factory, int version) {
	   super(context, name, factory, version);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase db) {
	   // TODO Auto-generated method stub
		 // db.rawQuery("Drop Table If Exists 'items'", null);
	   db.execSQL(SCRIPT_CREATE_DATABASE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	   // TODO Auto-generated method stub
		  db.rawQuery("Drop Table If Exists 'allitems'", null);
		  onCreate(db);
	  }

	 }
	 
	}
