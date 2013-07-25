package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SkillTest extends Activity {

	String test;
	TextView tvST;
	SkillList skl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skilltest);
		test="";
		try{
		tvST = (TextView)findViewById(R.id.tvSkillTest);
		
		 ItemTest it =new ItemTest();
	        String[] att = new String[10];
	        //= it.printData("skills");
		
	        
	        skl = new SkillList(this,att[0]);
	        
	        skl.openToWrite();
	        //skl.dropQueries();
	        //skl.cretQueries();
	        skl.deleteAll();
		    //   for(int i=0;i<19;i++)
	        for(int i=1;i<19;i++)
	        	skl.insertQuery(att[i]);
	    	   //test=test+att[i]+" ";
	     
	        skl.close();

	        /*
	         *  Open the same SQLite database
	         *  and read all it's content.
	         */
	        skl = new SkillList(this,att[0]);
	        skl.openToRead();
	        String contentRead = skl.queueAll();
	        //*/
	        //String contentRead = skl.colNamesChk();
	        skl.close();
	        tvST.setText(contentRead);
		}catch(Exception e){
			System.err.print(e);
		}
	}
	

	
}
