package com.darshan.warriorgame;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.darshan.warriorgame.Constants;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.user.User;
import com.shephertz.app42.paas.sdk.android.user.UserService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.AsyncTask;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoadGame extends Activity implements OnClickListener {

	EditText lgId,pass;
	Button bcon,bcan;
	ProgressDialog pDialog;
	String all;
	TextView tvTest;
	int success;
	SharingAtts sa;
	DBManager lin;
	Hashtable<String,String[]> allSkills, allItems;
	String plaAtts,skills,inv;
	JSONArray allskills,allitms;
	
	UserService userService;
	
    JSONParser jsonParser = new JSONParser();

    private static final String LOGIN_URL = "http://warriorsonandroid.com/warrior_online/loadgame.php";
    //private static final String ALL_DATA = "http://warriorsonandroid.com/warrior_online/skills.php";
    //private static final String TAG_SKILLS = "skills";
    //private static final String TAG_ITEMS = "items";
    
      private static final String TAG_SUCCESS = "success";
      private static final String TAG_MESSAGE = "message";

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadgame);
		System.out.print("Enter username and password");
		allSkills = new Hashtable<String,String[]>();
		allItems = new Hashtable<String,String[]>();
		
		App42API.initialize(this,Constants.apiKey,Constants.secretKey);  
		userService = App42API.buildUserService();
		
		lgId = (EditText)findViewById(R.id.etLogin1);
		pass = (EditText)findViewById(R.id.etPassword);
		bcon = (Button)findViewById(R.id.bCon1);
		bcan = (Button)findViewById(R.id.bCan1);
		tvTest = (TextView)findViewById(R.id.tvTest5);
		sa = ((SharingAtts)getApplication());
		bcon.setOnClickListener(this);
		bcan.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.bCon1){
			
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
			
			//new LoadData().execute();
			new LoadUser().execute();
			
			
			
		}else if(v.getId()==R.id.bCan1){
			
			finish();
		}
		
	}
	
	
	class LoadUser extends AsyncTask<String, String, String> {

		 /**
        * Before starting background thread Show Progress Dialog
        * */
		boolean failure = false;

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           pDialog = new ProgressDialog(LoadGame.this);
           pDialog.setMessage("Load User...");
           pDialog.setIndeterminate(false);
           pDialog.setCancelable(true);
         if(LoadGame.this.isFinishing())
        	 pDialog.show();
       }

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
           
           ItemTest it = new ItemTest();
           String[] s1 = it.printData("players");
           String[] plAttsNam = s1[1].split(" ");
           String[] s2 = it.printData("player_skills");
           String[] plAttsNam2 = s2[1].split(" ");
           String[] s3 = it.printData("player_inv");
           String[] plAttsNam3 = s3[1].split(" ");
           
           String login = lgId.getText().toString();
		   String pas = pass.getText().toString();
           try{
               // Building Parameters
               List<NameValuePair> params = new ArrayList<NameValuePair>();
               params.add(new BasicNameValuePair("player_name", login));
               params.add(new BasicNameValuePair("password", pas));

               Log.d("request!", "starting");

               //Posting user data to script
               JSONObject json = jsonParser.makeHttpRequest(
                      LOGIN_URL, "POST", params);
               success = json.getInt(TAG_SUCCESS);
               if(success==1) {
               plaAtts = json.getString("player_id")+" ";
               plaAtts = plaAtts + json.getString("player_name")+" ";
               plaAtts = plaAtts + null+" ";
               for(int i=3; i<plAttsNam.length;i++){
            	   if(json.get(plAttsNam[i])!=null)
            		   plaAtts = plaAtts + String.valueOf(json.get(plAttsNam[i]))+" ";
               }
               skills =null+" ";
               for(int i=1; i<plAttsNam2.length;i++){
            	   if(json.get(plAttsNam2[i])!=null)
            		   skills = skills + String.valueOf(json.get(plAttsNam2[i]))+" ";
               }
               
               inv = "";
               for(int i=1; i<plAttsNam3.length;i++){
            	   if(json.get(plAttsNam3[i])!=null)
            		   inv = inv + String.valueOf(json.get(plAttsNam3[i]))+" ";
            	   Log.d("inv_"+(i-1), plAttsNam3[i]+","+String.valueOf(json.get(plAttsNam3[i])));
               }
               if(json.toString()!=null)
            	   Log.d("JSON", json.getString("player_class"));

               try{
            	   sa.setPlaAtts(plaAtts.split(" "));
                   sa.setAllInv(inv.split(" "));
                   sa.setSkills(skills.split(" "));   
               }catch(Exception e){
            	   System.err.print(e.toString());
               }
            	   if(json.getString("player_class")!= null){

               }else{
            	   Toast.makeText(getApplicationContext(), "NOPE", Toast.LENGTH_SHORT).show();
            	   
               }
               }else{
            	   Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                  	//Log.d("Exception", json.getString("Exception"));
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
           final String uname;
		final String pas;
           if(success==1){
        	   	uname = lgId.getText().toString();
   				pas = pass.getText().toString(); 
   			
   				final ProgressDialog progressDialog = ProgressDialog.show(LoadGame.this, "", "signing in..");
   				progressDialog.setCancelable(true);
   				userService.authenticate(uname, pas,  new App42CallBack() {
   				public void onSuccess(Object response){
   					User user = (User)response;
   					System.out.println("userName is " + user.getUserName());  
   					System.out.println("sessionId is " + user.getSessionId());
   					progressDialog.dismiss();
   					
   					Intent i = new Intent("com.darshan.warriorgame.welcome");
   					i.putExtra("uname", uname);
					i.putExtra("pass", pas);
   					startActivity(i);
   					finish();
   				}
   				public void onException(Exception ex){
   					System.out.println("Exception Message : "+ex.getMessage());
   					progressDialog.dismiss();
   				}
   				});
           }
       }
	}
	
	/*
	class LoadData extends AsyncTask<String, String, String> {

		 /**
       * Before starting background thread Show Progress Dialog
       * */
		boolean failure = false;
/*
      @Override
      protected void onPreExecute() {
          super.onPreExecute();
          pDialog = new ProgressDialog(LoadGame.this);
          pDialog.setMessage("Load User...");
          pDialog.setIndeterminate(false);
          pDialog.setCancelable(true);
        if(LoadGame.this.isFinishing())
       	 pDialog.show();
      }

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		ItemTest it = new ItemTest();
		String[] wholeFile =it.printData("skills");
		String[] colNames = wholeFile[1].split(" ");
		String[] wholeFile2 =it.printData("allItems");
		String[] colNames2 = wholeFile2[1].split(" ");
		
        //String username = user.getText().toString();
        //String password = pass.getText().toString();
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("table", ""));
            //params.add(new BasicNameValuePair("password", password));

            Log.d("request!", "starting");
            // getting product details by making HTTP request
            
            
            JSONObject json = jsonParser.makeHttpRequest(
            		ALL_DATA, "POST", params);
            
            //skills = json.getJSONArray("tablearray");
            allskills = json.getJSONArray(TAG_SKILLS);
            allitms = json.getJSONArray(TAG_ITEMS);
            String st="";
            for(int i=0;i<skills.length();i++){
            	for(int j = 0 ;j< allskills.getJSONObject(i).length(); j++)
            		st = st + allskills.getJSONObject(i).getString(colNames[j])+" ";
            	st = st + "\n";
            }
            
            Hashtable<String,String[]> allSkills = new Hashtable<String,String[]>(); 
            String[] skilRows = st.split("\n");
            for(int i=0; i< skilRows.length;i++){
            	allSkills.put(skilRows[i].split(" ")[0], skilRows[i].split(" "));
            }
            
            Hashtable<String,String[]> allItems = new Hashtable<String,String[]>();
            String[] itmRows = st.split("\n");
            for(int i=0; i< itmRows.length;i++){
            	allItems.put(itmRows[i].split(" ")[0], itmRows[i].split(" "));
            }
            
            sa.setAllSkills(allSkills);
            sa.setAllItms(allItems);
            st = st + "\n";
            
            for(int i=0;i<allitms.length();i++){
            	for(int j = 0 ;j< allitms.getJSONObject(i).length(); j++)
            		st = st + allitms.getJSONObject(i).getString(colNames2[j])+" ";
            	st = st + "\n";
            }
            
} catch (Exception e) {
            e.printStackTrace();
        }
		//return st;
		
		return null;
		}
	
	protected void onPostExecute(String file_url) {
        // dismiss the dialog once product deleted
        pDialog.dismiss();
        sa.updateStat();
       // tvTest.setText(plaAtts+"\n"+skills+"\n"+inv);
        //Intent i = new Intent("com.darshan.warriorgame.welcome");
        //startActivity(i);
        //finish();
        //if (file_url != null){
        	//Toast.makeText(LoadGame.this, file_url, Toast.LENGTH_LONG).show();
        //}

    }
	
	}
	*/
}
	

