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
        
        
       // mySQLiteAdapter = new SQLiteAdapter(this,att[0]);
       
       // mySQLiteAdapter.openToWrite();
       // mySQLiteAdapter.dumTable();
      //  mySQLiteAdapter.deleteAll();
       for(int i=0;i<att.length;i++){
        //	mySQLiteAdapter.insertQuery(att[i]);
    	   s=s+att[i]+" ";
       }
    //   mySQLiteAdapter.close();

        /*
         *  Open the same SQLite database
         *  and read all it's content.
         
        mySQLiteAdapter = new SQLiteAdapter(this,att[0],"allskills");
        mySQLiteAdapter.openToRead();
        String contentRead = mySQLiteAdapter.queueAll();
        //
       //String contentRead = mySQLiteAdapter.colNamesChk();
        */
        tvCT.setText(s);
        
       // mySQLiteAdapter.close();
        
	}

	
	
}
