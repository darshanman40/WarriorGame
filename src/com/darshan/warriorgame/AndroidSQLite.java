package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidSQLite extends Activity {
	 
	 private SQLiteAdapter mySQLiteAdapter;
	 
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sqltest);
	        TextView listContent = (TextView)findViewById(R.id.tvSqltest);
	        
	        String test="";
	        ItemTest it =new ItemTest();
	        String[] att = new String[10];
	        // = it.printData("inventory");
	        
	        
	        mySQLiteAdapter = new SQLiteAdapter(this,att[0]);
	       // try{
	        mySQLiteAdapter.openToWrite();
	      //  mySQLiteAdapter.dropTable();
	        mySQLiteAdapter.deleteAll();
	       for(int i=1;i<53;i++)
	    	  // test = test +att[i];
	        	mySQLiteAdapter.insertQuery(att[i]);
	     //   }catch(Exception e){
	     //   	System.err.print(e);
	      //  }
	     
	        mySQLiteAdapter.close();

	        /*
	         *  Open the same SQLite database
	         *  and read all it's content.
	         */
	        mySQLiteAdapter = new SQLiteAdapter(this,att[0]);
	        mySQLiteAdapter.openToRead();
	        //String contentRead = mySQLiteAdapter.queueAll();
	        //*/
	        //String contentRead = mySQLiteAdapter.colNamesChk();
	       
	        Integer[] eqps = {101,201,301,401};
	        double[] data = mySQLiteAdapter.getItemAtts(eqps);
	        String contentRead="";
	        for(int i=0;i<data.length;i++)
	        	contentRead = contentRead + String.valueOf(data[i])+"\n";
	       // */	
	        
	        mySQLiteAdapter.close();
	        
	        listContent.setText(contentRead);
	        
	    }
	}