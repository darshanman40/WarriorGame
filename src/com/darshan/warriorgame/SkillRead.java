package com.darshan.warriorgame;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
//import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget.Toast;

public class SkillRead extends Activity implements OnClickListener{

	TextView skill;
	EditText tableName;
	Button send;
	String st="";
	private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    JSONArray skills,items;
    //php login script location:

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
   // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";

    //testing on Emulator:
    private static final String LOGIN_URL = "http://warriorsonandroid.com/warrior_online/skills.php";

  //testing from a real server:
    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/login.php";

    //JSON element ids from repsonse of php script:
    //private static final String TAG_SUCCESS = "success";
    //private static final String TAG_MESSAGE = "message";
    private static final String TAG_SKILLS = "skills";
    private static final String TAG_ITEMS = "items";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skilldisplay);
		
		skill = (TextView)findViewById(R.id.tvSkills);
		send = (Button)findViewById(R.id.bSend5);
		tableName = (EditText)findViewById(R.id.etTables5);
		send.setOnClickListener(this);
		
		
	}
	
	class SkillAbstract extends AsyncTask<String, String, String> {

		boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SkillRead.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			//int success;
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
                params.add(new BasicNameValuePair("table", tableName.getText().toString()));
                //params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                
                
                JSONObject json = jsonParser.makeHttpRequest(
                		LOGIN_URL, "POST", params);
                //skills = json.getJSONArray("tablearray");
                skills = json.getJSONArray(TAG_SKILLS);
                items = json.getJSONArray(TAG_ITEMS);
                st="";
                for(int i=0;i<skills.length();i++){
                	for(int j = 0 ;j< skills.getJSONObject(i).length(); j++)
                		st = st + skills.getJSONObject(i).getString(colNames[j])+" ";
                	st = st + "\n";
                }
                
                st = st + "\n";
                
                for(int i=0;i<items.length();i++){
                	for(int j = 0 ;j< items.getJSONObject(i).length(); j++)
                		st = st + items.getJSONObject(i).getString(colNames2[j])+" ";
                	st = st + "\n";
                }
                
                /*
               skills = json.getJSONArray(TAG_SKILLS);
               items = json.getJSONArray(TAG_ITEMS);
                // check your log for json response
               Log.d("Login attempt", json.toString());
                
                
                for(int i = 0; i < skills.length(); i++){
                   JSONObject s = skills.getJSONObject(i);
                   st=st+s.getS
                   tring("skill_id")+" "+s.getString("skill_name")+" "+s.getString("mana")+" "+s.getString("accuracy")+" "+s.getString("operation")+" "+s.getString("stat")+" "+s.getString("effect")+" "+s.getString("add_dam")+" "+s.getString("pre_req_skill_id")+" "+s.getString("pre_req_skill_id2")+" ";
                   st=st+"\n"; 
                }
                for(int i=0; i<items.length();i++){
                	JSONObject s = items.getJSONObject(i);
                    st=st+s.getString("item_id")+" "+s.getString("name")+" "+s.getString("str")+" "+s.getString("ph_dam")+" "+s.getString("mag_dam")+" "+s.getString("ph_def")+" "+s.getString("mag_def")+" "+s.getString("e_s_dam")+" "+s.getString("s_hp")+" "+s.getString("b_mana")+" "+s.getString("spd")+" "+s.getString("cost")+" "+s.getString("s_price")+" "+s.getString("hp_plus")+" "+s.getString("mana_plus")+" ";
                    st=st+"\n";
                }
                */
//-------------------------------------------------------------
             
                // json success tag
                //success = json.getInt(TAG_SUCCESS);
                /*
                if (success == 1) {
                	Log.d("Login Successful!", json.toString());
                	Intent i = new Intent(Login.this, ReadComments.class);
                	finish();
    				startActivity(i);
                	return json.getString(TAG_MESSAGE);
                }else{
                	Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                	return json.getString(TAG_MESSAGE);

                }
*/               
                //-------------------------------------------------------------
            } catch (Exception e) {
                e.printStackTrace();
            }
			return st;
		}
		
		 protected void onPostExecute(String file_url) {
	            // dismiss the dialog once product deleted
	            pDialog.dismiss();
	            if (file_url != null){
	            	//Toast.makeText(SkillRead.this, file_url, Toast.LENGTH_LONG).show();
	            	skill.setText(st.toString());
	            }
	            
	        }

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		new SkillAbstract().execute();
	}
}
