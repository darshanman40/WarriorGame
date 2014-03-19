package com.darshan.warriorgame;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;



public class DBManager {
	public String MYDATABASE_NAME = "Inventory";
	 public String MYDATABASE_TABLE;
	 public int MYDATABASE_VERSION = 1;
	 

	 public String[] colNames; 
	 public String cols;
	 //create table MY_DATABASE (ID integer primary key, Content text not null);
	 private String SCRIPT_CREATE_DATABASE;
			 //"CREATE TABLE 'allitems' ('item_id' INTEGER PRIMARY KEY  NOT NULL  UNIQUE , 'item' VARCHAR, 'str' DOUBLE, 'ph_dam' DOUBLE, 'mag_dam' DOUBLE, 'ph_def' DOUBLE, 'mag_def' DOUBLE, 'e_s_dam' DOUBLE, 's_hp' DOUBLE, 'b_mana' DOUBLE, 'speed' DOUBLE, 'cost' DOUBLE, 'selling_price' DOUBLE, 'hp_plus' DOUBLE, 'mana_plus' DOUBLE);";
	 
	 private SQLiteHelper sqLiteHelper;
	 private SQLiteDatabase sqLiteDatabase;

	 private Context context;
	 
	 public DBManager(Context c, String colNames,String tblName, String script){
	  context = c;
	  cols=colNames;
	  if(colNames!=null)
		  this.colNames=colNames.split(" ");
	  MYDATABASE_TABLE=tblName;
	  SCRIPT_CREATE_DATABASE = script;
	 }
	
	 public DBManager openToRead() throws android.database.SQLException {
	  sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
	  sqLiteDatabase = sqLiteHelper.getReadableDatabase();
	  return this; 
	 }
	 
	 public DBManager openToWrite() throws android.database.SQLException {
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
	 
	 public void autoRecover(String[] s2){
		 openToWrite();
		 dropTable();
		 cretTable();
		 for(int i=2;i<s2.length;i++)
			 insertQuery(s2[i]);
		 close();		 
	 }
	 public void recover(String[] s2){
		 openToWrite();
		 //dropTable();
		 cretTable();
		 for(int i=2; i<s2.length;i++)
			 insertQuery(s2[i]);
		 close();		 
	 }
	 public long insertQuery(String content){
		  String atts[] = content.split(" "); 
		  ContentValues contentValues = new ContentValues();
		  try{
		  for(int i=0; i<colNames.length;i++)
			  contentValues.put(colNames[i], atts[i]);
		  return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
		 }catch(Exception e){
			 System.err.print(e+" colName6= "+colNames[6]);
		 }
		  return 0;
	 }
	 
	 public long insertQuery(String content, String colNames2){
		  String atts[] = content.split(" "); 
		  String[] colNames = colNames2.split(" ");
		  ContentValues contentValues = new ContentValues();
		  try{
		  for(int i=0; i<colNames.length;i++){
			  atts[i] = atts[i].replace("?*", " ");
			  contentValues.put(colNames[i], atts[i]);
		  }
		  return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
		 }catch(Exception e){
			 System.err.print(e+" colName6= "+colNames[6]);
		 }
		  return 0;
	 }
	 
	 public long updateTable(String content, String where){
		 String[] cols = content.split(" ");
		 ContentValues cvalues = new ContentValues();
		 
		 for(int i=1; i<colNames.length;i++){
			 
			 cvalues.put(colNames[i], cols[i]);
		 }
		 //sqLiteDatabase.up
		 return sqLiteDatabase.update(MYDATABASE_TABLE, cvalues, where, null);
	 }
	 public long updateTable(String content, String where,String[] args){
		 String[] cols = content.split(" ");
		 ContentValues cvalues = new ContentValues();
		 
		 for(int i=1; i<colNames.length;i++){
		
			 cvalues.put(colNames[i], cols[i]);
		 }
			 //sqLiteDatabase.up
		 return sqLiteDatabase.update(MYDATABASE_TABLE, cvalues, where, args);
	 }
		
	 public long updateTable(String content,String[] colNames, String where){
		 String[] cols = content.split(" ");
		 ContentValues cvalues = new ContentValues();
		 
		 for(int i=0; i<colNames.length;i++){
			 Log.d("DBM_Val", cols[i]);
			 Log.d("DBM_ColNames", colNames[i]);
			 cvalues.put(colNames[i], cols[i]);
		 }
		 Log.d("DBM_WHERE",where);
			 //sqLiteDatabase.up
		 return sqLiteDatabase.update(MYDATABASE_TABLE, cvalues, where, null);
	 }
	 //}
	 
	 public int deleteAll(){
	  return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
	 }
	 public void dropTable(){
		  sqLiteDatabase.execSQL("Drop Table If Exists "+MYDATABASE_TABLE);
		  //sqLiteDatabase.execSQL(SCRIPT_CREATE_DATABASE);
		 }
	 public void cretTable(){
		  sqLiteDatabase.execSQL(SCRIPT_CREATE_DATABASE);
		 }
	 
	 public String queueFew(String[] custCNames){
		 //String[] columns=colNames;
			 String result = "";
			 
			 try{
		  Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE,custCNames,null,null,null,null,null);
		  int index[] = new int[custCNames.length];
		  for(int i=0;i<custCNames.length;i++)
			  index[i]= cursor.getColumnIndex(custCNames[i]);
		  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
			  for(int i=0;i<custCNames.length;i++)
				  result = result + (cursor.getString(index[i]))+" ";// + " "+cursor.getString(index[1]) + "\n";
			  result=result+"\n";
		  }
		  return result;
			 }catch(Exception e){
			 System.err.print(e+" colNames6= "+colNames[6]);
			 }
			 return null;
	 }
	 public String queueAll(){
	 //String[] columns=colNames;
		 String result = "";
		 
		 try{
	  Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE,colNames,null,null,null,null,null);
	  int index[] = new int[colNames.length];
	  for(int i=0;i<colNames.length;i++)
		  index[i]= cursor.getColumnIndex(colNames[i]);
	  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
		  for(int i=0;i<colNames.length;i++)
			  result = result + cursor.getString(index[i])+" ";// + " "+cursor.getString(index[1]) + "\n";
		  result=result+"\n";
	  }
	  return result;
		 }catch(Exception e){
		 System.err.print(e+" colNames6= "+colNames[6]);
		 }
		 return null;
	 }
	 public String queueAll(String row){
		 //String[] columns=colNames;
			 String result = "";
			 
			 try{
		  Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE,colNames,null,null,null,null,null);
		  int index[] = new int[colNames.length];
		  for(int i=0;i<colNames.length;i++)
			  index[i]= cursor.getColumnIndex(colNames[i]);
		  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
			  if(row.compareTo(String.valueOf(cursor.getInt(index[0])))==0){
			  for(int i=0;i<colNames.length;i++)
				  result = result + cursor.getString(index[i])+" ";// + " "+cursor.getString(index[1]) + "\n";
			 // result=result+"\n";
			  }
		  }
		  return result;
			 }catch(Exception e){
			 System.err.print(e+" colNames6= "+colNames[6]);
			 return e+"ur dumbass";
			 }
			// return "ur dumbass";
		 }
	 public int queueAll(String row1, String row2){
		 //String[] columns=colNames;
			 //String result = "";
		try{
		  Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE,colNames,null,null,null,null,null);
		  int index[] = new int[colNames.length];
		  //long count=0;
		  for(int i=0;i<colNames.length;i++)
			  index[i]= cursor.getColumnIndex(colNames[i]);
		  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
			  if(row1.compareTo(String.valueOf(cursor.getString(index[1])))==0 && row2.compareTo(String.valueOf(cursor.getString(index[2])))==0){
				  return cursor.getInt(index[0]);
				  //for(int i=0;i<colNames.length;i++)
				  //result = result + cursor.getString(index[i])+" ";// + " "+cursor.getString(index[1]) + "\n";
				  
			  //result=result+"\n";
			  }
		  }
		  
			 }catch(Exception e){
			 System.err.print(e+" colNames6= "+colNames[6]);
			 }
			 return 0;
		 }
	 
	 public String directQuery(String query, String[] colNames){
		 
		 String result = "";
		 List<String> rows = new ArrayList<String>();
		 try{
		 Cursor cursor = sqLiteDatabase.rawQuery(query, null);
		 //int index[] = new int[colNames.length];
		 /*
		 for(int i=0; i<colNames.length;i++){
			 index[i] = cursor.getColumnIndex(colNames[i]);
		 }
		 */
		 
		 for(cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()){
			 for(int i=0;i<colNames.length;i++)
				 result = result + "," + cursor.getString(cursor.getColumnIndex(colNames[i]));
			 rows.add(result.replaceFirst(",", ""));
			 result = "";//result.replaceFirst(",", "");
			 //result+="\n";
		 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 for(int i=0;i<rows.size();i++)
			 result = result + rows.get(i)+"\n";
		 //result = rows.toArray().toString();
		 return result;
	 }
	 
	 
	 public String colNamesChk(){
		 
		 
		 String res="";
		 Cursor cursor = sqLiteDatabase.rawQuery("SELECT sql FROM sqlite_master WHERE tbl_name = "+MYDATABASE_TABLE+" AND type = 'table'", null);
		  int ind = cursor.getColumnIndex("sql");
		  for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
			  res = res+cursor.getString(ind)+"\n";
		  }
		  
		 return res;
		 
	 }
 public String tblNamesChk(){
		 
		 String res="";
		 Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM sqlite_master WHERE type = 'table'", null);
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
		  db.rawQuery("Drop Table If Exists "+MYDATABASE_TABLE, null);
		  onCreate(db);
	  }

	 }
}
