package com.darshan.warriorgame;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.darshan.warriorgame.Player;

public class NewGame extends Activity implements OnClickListener{

	SharingAtts sa;
	DBManager db,itms;
	ItemTest it;
	Player pl;
	String[] row,row2,row3, s1,s2,s3;
	RadioGroup rgClass;
	Button bName, bClear;
	EditText etName,etConPass,etPass;
	int success;
	
	 private ProgressDialog pDialog;

	    JSONParser jsonParser = new JSONParser();

	    private static final String LOGIN_URL = "http://warriorsonandroid.com/warrior_online/register.php";

	    private static final String TAG_SUCCESS = "success";
	    private static final String TAG_MESSAGE = "message";
	
	
	int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgame);
		
		id=1;
		
		sa=((SharingAtts)getApplication());
		it = new ItemTest();
		try{
			bName = (Button)findViewById(R.id.bAccept);
			bClear = (Button)findViewById(R.id.bClear);
			rgClass= (RadioGroup)findViewById(R.id.rgClass2);
			etName = (EditText)findViewById(R.id.etName);
			etPass = (EditText)findViewById(R.id.etPass1);
			etConPass = (EditText)findViewById(R.id.etConPass1);
		}catch(Exception e){
			Toast t = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
			t.show();
		}
		bName.setOnClickListener(this);
		bClear.setOnClickListener(this);
		
		
		
	//-----------------------------------initialization-----------------------------	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.bAccept){	
			  int cheked = rgClass.getCheckedRadioButtonId();
			 
			RadioButton rb =(RadioButton)findViewById(cheked);
			String pClass = rb.getText().toString();
			success=2;
		//try{
			if(chkPass(etPass.getText().toString(),(etConPass.getText().toString()))){
				pl = new Player(this,etName.getText().toString(),pClass,etPass.getText().toString());
				pl.newPlayer(etName.getText().toString(),pClass);
				sa.setPlaAtts(pl.getNPlayerAtts());
				sa.setAllInv(pl.getNPlayerInv());
				sa.setSkills(pl.getNPlayerSkilvls());
		
				
				new ChkUserName().execute();
				//		new CreateUser().execute();
				
				
				
			}else{
				Toast t = Toast.makeText(getApplicationContext(), "err", Toast.LENGTH_LONG);
				t.show();
			}
			
		//}catch(Exception e){
		//	Toast t2 = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
		//	t2.show();
		//}
		}else if(R.id.bClear==v.getId()){
			etName.setText("");
			etPass.setText("");
			etConPass.setText("");
			
		}
	}
	
	public boolean chkPass(String pass, String anPas){
		if(!pass.equals(anPas)){
			return false;
		}else{
			return true;
		}
		
	}
	
	class CreateUser extends AsyncTask<String, String, String> {

		 /**
        * Before starting background thread Show Progress Dialog
        * */
		boolean failure = false;

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           pDialog = new ProgressDialog(NewGame.this);
           pDialog.setMessage("Creating User...");
           pDialog.setIndeterminate(false);
           pDialog.setCancelable(true);
           if( NewGame.this.isFinishing())
        	  	pDialog.show();
       	}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag

           try {
               // Building Parameters
               String[] colNames = s1[1].split(" ");
        	   String[] colNames2 = s2[1].split(" ");
        	   String[] colNames3 = s3[1].split(" ");
        	   List<NameValuePair> params = new ArrayList<NameValuePair>();
        	   Log.d("Chk register info","down");
               for(int i=0 ; i<colNames.length;i++){
            	   Log.d(colNames[i], row[i]);
            	   params.add(new BasicNameValuePair(colNames[i],row[i]));
               }
               for(int i=1; i<colNames2.length ; i++)
            	   params.add(new BasicNameValuePair(colNames2[i],row2[i]));
               for(int i=1; i<colNames3.length ; i++)
            	   params.add(new BasicNameValuePair(colNames3[i],row3[i]));
               
               
               Log.d("request!", "starting");

               //Posting user data to script
               JSONObject json = jsonParser.makeHttpRequest(
                      LOGIN_URL, "POST", params);

               // full json response
               if(json.toString()!=null)
            	   Log.d("Login attempt", json.toString());

               // json success element
               success = json.getInt(TAG_SUCCESS);
               if (success == 1) {
            	
            	   Hashtable<String,String[]> allSkills = new Hashtable<String,String[]>();
            	   Hashtable<String,String[]> allItems = new Hashtable<String,String[]>();
            	   
            	   ItemTest it = new ItemTest();
       			String[] s4=it.printData("skills");
       			
       			
       			for(int i=2;i<s4.length;i++){
       				String[] sr4 = s4[i].split(" ");	
       				allSkills.put(sr4[0], sr4);
       			}
       			
       			String[] s5=it.printData("allItems");
       			
       			for(int i=2;i<s5.length;i++){
       				String[] sr5 = s5[i].split(" ");
       				allItems.put(sr5[0], sr5);
       			}
       			
       			sa.setAllItms(allItems);
       			sa.setAllSkills(allSkills);
            	  /*
            	  db = new DBManager(NewGame.this,s1[1],"allplayer",s1[0]);
            	  db.openToWrite();
            	  sa.id=json.getInt("player_id");
            	  row[0] = String.valueOf(json.getString("player_id"));
            	  String pId = String.valueOf(json.getString("player_id"))+" ";
            	  db.updateTable(pId,new String[]{"player_id"}, "player_name = '"+row[1]+"'");
            	  db.close();
            	  */
            	   
            	  Log.d("player_id",json.getString("player_id"));
            	  
               	  Log.d("User Created!", json.toString());
               	  return json.getString(TAG_MESSAGE);
               
               }else{
               
            	   Log.d("Login Failure!", json.getString(TAG_MESSAGE));
               	return json.getString(TAG_MESSAGE);

               }
           } catch (JSONException e) {
               e.printStackTrace();
           }
           return null;

		}
		/**
        * After completing background task Dismiss the progress dialog
        * **/
       protected void onPostExecute(String file_url) {
           // dismiss the dialog once product deleted
    	   pDialog.dismiss();
           if (file_url != null){
           	Toast.makeText(NewGame.this, file_url, Toast.LENGTH_LONG).show();
           }
           /*
           if(success ==0){
        	   Intent in = new Intent("com.darshan.warriorgame.welcome");
        	   startActivity(in);
        	   NewGame.this.finish();
           }
           */
       }
    }
	
	public class ChkUserName extends AsyncTask<String, String, String>{

		@Override
	    protected void onPreExecute() {
	           super.onPreExecute();
	           pDialog = new ProgressDialog(NewGame.this);
	           pDialog.setMessage("Creating User...");
	           pDialog.setIndeterminate(false);
	           pDialog.setCancelable(true);
	           if( NewGame.this.isFinishing())
	        	  	pDialog.show();
	    }
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			try {
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				params.add(new BasicNameValuePair("player_name",etName.getText().toString()));
				params.add(new BasicNameValuePair("password",etPass.getText().toString()));
				params.add(new BasicNameValuePair("check","check it"));
				JSONObject json = jsonParser.makeHttpRequest(
                    LOGIN_URL, "POST", params);
				if(json.toString()!=null)
					Log.d("UserName Checking", json.toString());
					
					// json success element
            		success = json.getInt(TAG_SUCCESS);
			
            	} catch (JSONException e) {
				// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
			return null;
		}
		
		protected void onPostExecute(String file_url) {
	           // dismiss the dialog once product deleted
	    	   pDialog.dismiss();
	           if (file_url != null){
	           		Toast.makeText(NewGame.this, file_url, Toast.LENGTH_LONG).show();
	           }
	           finalDest();
	      }
		
	}
	
	String[] createDatabase(Context c){
		String[] playerDet=new String[3];
		s1=it.printData("players");
		db = new DBManager(this,s1[1],"allplayer",s1[0]);
		db.openToWrite();
		db.dropTable();
		db.cretTable();
		for(int i=2;i<s1.length;i++)
			db.insertQuery(s1[i]);
		//String[] plats = pl.getNPlayerAtts();
		String pts="";
		for(int i=0;i<pl.getNPlayerAtts().length;i++)
			pts=pts+pl.getNPlayerAtts()[i]+" ";
		playerDet[0] =pts; 
		db.updateTable(pts, "player_id = "+String.valueOf(pl.id));
		db.close();
		
		//----------------------------------------------------------------
		s2=it.printData("player_inv");
		db = new DBManager(this,s2[1],"playersinv",s2[0]);
		db.openToWrite();
		db.dropTable();
		db.cretTable();
		for(int i=2;i<s2.length;i++)
			db.insertQuery(s2[i]);
		//String[] plats = pl.getNPlayerAtts();
		pts=String.valueOf(pl.id)+" ";
		for(int i=0;i<pl.getNPlayerInv().length;i++)
			pts=pts+pl.getNPlayerInv()[i]+" ";
		playerDet[1] = pts;
		db.updateTable(pts, "player_id = "+String.valueOf(pl.id));
		db.close();
		
		//----------------------------------------------
		s3=it.printData("player_skills");
		db = new DBManager(this,s3[1],"playerskill",s3[0]);
		db.openToWrite();
		db.dropTable();
		db.cretTable();
		for(int i=2;i<s3.length;i++)
			db.insertQuery(s3[i]);
		//String[] plats = pl.getNPlayerAtts();
		pts=String.valueOf(pl.id)+" ";
		for(int i=0;i<pl.getNPlayerSkilvls().length;i++)
			pts=pts+pl.getNPlayerSkilvls()[i]+" ";
		playerDet[2] = pts;
		db.updateTable(pts, "player_id = "+String.valueOf(pl.id));
		db.close();
		
		//-----------------------------------------------------
		String[] s4=it.printData("skills");
		db = new DBManager(this,s4[1],"allskills",s4[0]);
		db.openToWrite();
		db.dropTable();
		db.cretTable();
		for(int i=2;i<s4.length;i++)
			db.insertQuery(s4[i]);
		db.close();
		
		//------------------------------------------------------------
		String[] s5=it.printData("allItems");
		db = new DBManager(this,s5[1],"allitems",s5[0]);
		db.openToWrite();
		db.dropTable();
		db.cretTable();
		for(int i=2;i<s5.length;i++)
			db.insertQuery(s5[i]);
		db.close();
		
		db = new DBManager(this,s1[1],"allplayer",s1[0]);
		db.openToRead();
		row =db.queueAll(String.valueOf(pl.id)).split(" "); 
		db.close();
		
		db = new DBManager(this,s2[1],"playersinv",s2[0]);
		db.openToRead();
		row2 =db.queueAll(String.valueOf(pl.id)).split(" "); 
		db.close();
		
		db = new DBManager(this,s3[1],"playerskill",s3[0]);
		db.openToRead();
		row3 =db.queueAll(String.valueOf(pl.id)).split(" "); 
		db.close();
		
		
		sa.setId(pl.id);
		//Toast.makeText(getApplicationContext(), row[1]+" "+row[2], Toast.LENGTH_SHORT).show();
		return playerDet;
    }
	
	/*
	public void newPlayer(){
		it= new ItemTest();
		//String[] st = it.printData("players");
		//itms = new DBManager(this,st[1],"allplayer",st[0]);
		//itms.recover(st);
		String s,s2,s3,s4,s5,s6;
		s="asshole";
		s2="inv";
		s3="pskils";
		s4="skilid";
		String[] st1 = it.printData("players");
		itms = new DBManager(this,st1[1],"allplayer",st1[0]);
		//itms.recover(st1);
		itms.openToWrite();
		
		//for(int i=2;i<st1.length;i++)
		//	itms.insertQuery(st1[i]);
		 s = itms.queueAll(String.valueOf(id));
		itms.close();	
		String[] st2 = s.split(" ");
		sa.setPlaAtts(st2);
		try{
		String[] st3 = it.printData("player_inv");
		itms = new DBManager(this,st3[1],"playersinv",st3[0]);
		//itms.recover(st3);
		itms.openToWrite();
		//itms.cretTable();
		//for(int i=2;i<st3.length;i++)
		//	itms.insertQuery(st3[i]);
		s2 = itms.queueAll(String.valueOf(id));
		itms.close();	
		String[] st4 = s2.split(" ");
		sa.setAllInv(st4);
		
		String[] st5 = it.printData("player_skills");
		itms = new DBManager(this,st5[1],"playerskill",st5[0]);
		//itms.recover(st5);
		itms.openToWrite();
		//itms.cretTable();
		//for(int i=2;i<st5.length;i++)
		//	itms.insertQuery(st5[i]);
		s3 = itms.queueAll(String.valueOf(id));
		itms.close();	
		String[] st6 = s3.split(" ");
		//sa.setAllInv(st6);
		
		String[] st7 = it.printData("skills");
		itms = new DBManager(this,st7[1],"allskills",st7[0]);
		//itms.recover(st7);
		itms.openToWrite();
		//itms.cretTable();
		//for(int i=2;i<st7.length;i++)
		//	itms.insertQuery(st7[i]);
		s4 = itms.queueFew(new String[]{"skill_id"});
		itms.close();	
		s4 = s4.replace(" ", "-");
		String[] st8 = s4.split("-");
		//for(int i=1; i<st6.length;i++)
		//	st6[i-1]=st6[i].trim();
		//st6[st6.length-1]="";
			
		sa.setSkills(st8,st6);
		Toast t = Toast.makeText(getApplicationContext(), "Congratz", Toast.LENGTH_LONG);
		t.show();
		}catch(Exception e){
			System.err.print(e);
			Toast t = Toast.makeText(getApplicationContext(),s3+"   "+e.toString(), Toast.LENGTH_LONG);
			t.show();
		}
	}
	/*public boolean chkUserName(String name){
		
		//it = new ItemTest();
		String[] s = it.printData("players");
		db = new DBManager(this,s[1],"allplayer",s[0]);
		db.openToRead();
		
		
		/*if(!pass.equals(anPas)){
			return false;
		}else{
			return true;
		}
		 
		 */
		//return true;
	//}

	void finalDest(){
		if(success == 1 ){
			Toast.makeText(getApplicationContext(), "Username is Available", Toast.LENGTH_SHORT).show();
			createDatabase(NewGame.this);
			new CreateUser().execute();
			Intent in = new Intent("com.darshan.warriorgame.welcome");
        	startActivity(in);
        	finish();
		}else if(success ==0){
			Toast.makeText(getApplicationContext(), "Username isn't Available", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(getApplicationContext(), "Success is null", Toast.LENGTH_SHORT).show();
		}
	}
	
}
