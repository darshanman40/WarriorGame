package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CsvTest extends Activity{

	String s;
	String[] st;
	TextView tvCT;
	private SQLiteAdapter mySQLiteAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.csvtest);
		
		tvCT = (TextView)findViewById(R.id.tvCSVTEST);
		
        ItemTest it =new ItemTest();
        String[] att = it.printData("inventory");
       
        mySQLiteAdapter = new SQLiteAdapter(this,att[0]);
       
        mySQLiteAdapter.openToWrite();
        //mySQLiteAdapter.dumTable();
        mySQLiteAdapter.deleteAll();
        s="";
        //try{
         for(int i=1;i<att.length;i++){
        	// String[] rows = att[i].split(" ");
        	mySQLiteAdapter.insertQuery(att[i]);
        	 //s=s+att[i]+" ";
         }
         
         mySQLiteAdapter.close();
       // }catch(Exception e){
        //	System.err.print(e);
        //	tvCT.setText(e.toString());
        //}
        /*
         *  Open the same SQLite database
         *  and read all it's content.
         */
        
       String contentRead ="no value";
       try{
        mySQLiteAdapter = new SQLiteAdapter(this,att[0]);
        
        mySQLiteAdapter.openToRead();
        contentRead = mySQLiteAdapter.queueAll();
        //
       //String contentRead = mySQLiteAdapter.colNamesChk();
        //
        mySQLiteAdapter.close();
        }catch(Exception e){
        	tvCT.setText(e.toString());
        	System.err.print(e);
        }
       // */
        tvCT.setText(contentRead);
        
        
        
	}

	
	
}
