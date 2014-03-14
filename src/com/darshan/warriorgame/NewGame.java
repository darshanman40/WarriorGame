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

import com.darshan.warriorgame.Constants;
import com.darshan.warriorgame.Player;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.user.User;
import com.shephertz.app42.paas.sdk.android.user.UserService;

public class NewGame extends Activity implements OnClickListener{

	SharingAtts sa;
	DBManager db,itms;
	ItemTest it;
	Player pl;
	String[] row,row2,row3, s1,s2,s3;
	RadioGroup rgClass;
	Button bName, bClear;
	EditText etName,etConPass,etPass, email;
	int success;
	
	UserService userService;
	
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
		s1=it.printData("players");
		s2=it.printData("player_inv");
		s3=it.printData("player_skills");
		try{
			bName = (Button)findViewById(R.id.bAccept);
			bClear = (Button)findViewById(R.id.bClear);
			rgClass= (RadioGroup)findViewById(R.id.rgClass2);
			etName = (EditText)findViewById(R.id.etName);
			etPass = (EditText)findViewById(R.id.etPass1);
			etConPass = (EditText)findViewById(R.id.etConPass1);
			email = (EditText)findViewById(R.id.etEmail);
			
			App42API.initialize(this,Constants.apiKey,Constants.secretKey);  
			userService = App42API.buildUserService();
			
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
	/*
	class CreateUser extends AsyncTask<String, String, String> {

		 /**
        * Before starting background thread Show Progress Dialog
        * */
/*
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
        	   Log.d("ChkPt", "PostExec Create User");
        	   String[] colNames = s1[1].split(" ");
        	   String[] colNames2 = s2[1].split(" ");
        	   String[] colNames3 = s3[1].split(" ");
        	   List<NameValuePair> params = new ArrayList<NameValuePair>();
        	   Log.d("Chk register info","down");
        	   params.add(new BasicNameValuePair(colNames[1],etName.getText().toString()));
        	   params.add(new BasicNameValuePair(colNames[2],etPass.getText().toString()));
        	   
        	   for(int i=1;i<colNames.length;i++)
        		   params.add(new BasicNameValuePair(colNames[i],pl.getNPlayerAtts()[i]));
        	   for(int i=1;i<colNames2.length;i++)
        		   params.add(new BasicNameValuePair(colNames2[i],pl.getNPlayerInv()[i-1]));
        	   for(int i=1;i<colNames3.length;i++)
        		   params.add(new BasicNameValuePair(colNames2[i],pl.getNPlayerSkilvls()[i-1]));
       			
        	   
        	   /*
        	   for(int i=0 ; i<colNames.length;i++){
            	   Log.d(colNames[i], row[i]);
            	   params.add(new BasicNameValuePair(colNames[i],row[i]));
               }
               for(int i=1; i<colNames2.length ; i++)
            	   params.add(new BasicNameValuePair(colNames2[i],row2[i]));
               for(int i=1; i<colNames3.length ; i++)
            	   params.add(new BasicNameValuePair(colNames3[i],row3[i]));
               */
  /*             
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
    /*        	   
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
    /* 
	protected void onPostExecute(String file_url) {
           // dismiss the dialog once product deleted
    	   pDialog.dismiss();
           if (file_url != null){
           	Toast.makeText(NewGame.this, file_url, Toast.LENGTH_LONG).show();
           }
           

			if(success==1){
				Log.d("ChkUsername", "done");
				finalDest();
				//new CreateUser().execute();
			}else{
				Log.e("Error ","Chk user name");
			}
			
           /*
           if(success ==0){
        	   Intent in = new Intent("com.darshan.warriorgame.welcome");
        	   startActivity(in);
        	   NewGame.this.finish();
           }
           */
      // }
    //}
	
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
			
            	if(success==1){
            		String[] colNames = s1[1].split(" ");
             	   	String[] colNames2 = s2[1].split(" ");
             	   	String[] colNames3 = s3[1].split(" ");
             	   	params = new ArrayList<NameValuePair>();
             	   	Log.d("Chk register info","down");
             	   	params.add(new BasicNameValuePair(colNames[1],etName.getText().toString()));
             	   	params.add(new BasicNameValuePair(colNames[2],etPass.getText().toString()));
             	   
             	   	for(int i=1;i<colNames.length;i++)
             	   		params.add(new BasicNameValuePair(colNames[i],pl.getNPlayerAtts()[i]));
             	   	for(int i=1;i<colNames2.length;i++)
             	   		params.add(new BasicNameValuePair(colNames2[i],pl.getNPlayerInv()[i-1]));
             	   	for(int i=1;i<colNames3.length;i++)
             	   		params.add(new BasicNameValuePair(colNames3[i],pl.getNPlayerSkilvls()[i-1]));
                    json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);
             	   	
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
            			
            		
                    }else{
                    	Log.e("message",json.getString(TAG_MESSAGE));
                    }
                  }
                    
            	} catch (JSONException e) {
				// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
			return null;
		}
		
		protected void onPostExecute(String file_url) {
	           // dismiss the dialog once product deleted
	    	   pDialog.dismiss();
	    	   //sa.setPlaAtts(att)
	    	   
	    	   String uname = etName.getText().toString();
	    	   String pas = etPass.getText().toString();
	    	   String emailId = "";
	    	   if(email.getText().toString()!=null){
					emailId = email.getText().toString();
				}
				final ProgressDialog progressDialog = ProgressDialog.show(NewGame.this, "", "registering..");
				progressDialog.setCancelable(true);
				userService.createUser(uname, pas,emailId, new App42CallBack() {
					public void onSuccess(Object response) 
					{
						User user = (User)response;
						System.out.println("userName is " + user.getUserName());
						System.out.println("emailId is " + user.getEmail());
						progressDialog.dismiss();
					}
					public void onException(Exception ex) 
					{
						System.out.println("Exception Message"+ex.getMessage());
						progressDialog.dismiss();
					}
					});
	    	   
				Intent in = new Intent("com.darshan.warriorgame.loadgame");
	        	startActivity(in);
	        	finish();
	        	
	           if (file_url != null){
	           		Toast.makeText(NewGame.this, file_url, Toast.LENGTH_LONG).show();
	           }
	           //finalDest();
	      }
		
		
	}
/*
	void finalDest(){
		if(success == 1 ){
			Toast.makeText(getApplicationContext(), "Username is Available", Toast.LENGTH_SHORT).show();
//			createDatabase(NewGame.this);
			Log.d("ChkPt", "Entering Create User");
			//new CreateUser().execute();
			Intent in = new Intent("com.darshan.warriorgame.welcome");
        	startActivity(in);
        	finish();
		}else if(success ==0){
			Toast.makeText(getApplicationContext(), "Username isn't Available", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(getApplicationContext(), "Success is null", Toast.LENGTH_SHORT).show();
		}
	}
	*/
}
