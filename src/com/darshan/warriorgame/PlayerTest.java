package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PlayerTest extends Activity{

	TextView ptest;
	PlayerInv pt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ptest);
		
		ptest = (TextView)findViewById(R.id.tvPTest);
		
		try{
			
			 ItemTest it =new ItemTest();
		        String[] att= new String[10];
		       // = it.printData("player_inv");
			
		        pt = new PlayerInv(this,att[0]);
		               
		        pt.openToWrite();
		        //pt.dropQueries();
		        //pt.cretQueries();
		        pt.deleteAll();
			   //    for(int i=0;i<19;i++)
		       for(int i=1;i<2;i++)
		        	pt.insertQuery(att[i]);
		    	   //test=test+att[i]+" ";
		     
		        pt.close();

		        /*
		         *  Open the same SQLite database
		         *  and read all it's content.
		         */
		        pt = new PlayerInv(this,att[0]);
		        pt.openToRead();
		       String contentRead = pt.queueAll();
		        //*/
		       // String contentRead = pt.colNamesChk();
		        pt.close();
		        ptest.setText(contentRead);
			}catch(Exception e){
				ptest.setText(e.toString());
			}
		
	}
	
	

}
