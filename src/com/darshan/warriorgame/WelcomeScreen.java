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

import android.util.Log;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeScreen extends Activity implements OnClickListener{
	//SharedPreferences someData;
	TextView tvWel;
	Button bInv,bStore,bBS,bQuit,bStatus,bCheat,bSave,bChat, bloby;
	SharingAtts sa;
	Intent i;
	Hashtable<String,String[]> allItms,allSkills;
	SaveGame sg;
	ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL = "http://warriorsonandroid.com/warrior_online/savegame.php";

	
	String filename;
	int id;
	//---------------ids
	
	final int bInv2= R.id.bInv;
	int bBS2= R.id.bBS;
	int bStore2 = R.id.bStore;
	int bQuit2 = R.id.bQuit;
	int bStatus2 = R.id.bStatus;
	//Intent intent;
	ItemTest it;
	DBManager itms;
	CheatCompiler cc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		cc = new CheatCompiler(this);
		sg = new SaveGame(this);
		
		tvWel = (TextView)findViewById(R.id.tvWelcom);
		bChat = (Button)findViewById(R.id.bChat);
		bInv = (Button)findViewById(R.id.bInv);
		bBS = (Button)findViewById(R.id.bBS);
		bStore = (Button)findViewById(R.id.bStore);
		bQuit = (Button)findViewById(R.id.bQuit);
		bStatus = (Button)findViewById(R.id.bStatus);
		bCheat = (Button)findViewById(R.id.bCheat2);
		bSave = (Button)findViewById(R.id.bSave1);
		bloby = (Button)findViewById(R.id.bBattleLobby);
		
		bInv.setOnClickListener(this);
		bStore.setOnClickListener(this);
		bBS.setOnClickListener(this);
		bQuit.setOnClickListener(this);
		bStatus.setOnClickListener(this);
		bCheat.setOnClickListener(this);
		bChat.setOnClickListener(this);
		bSave.setOnClickListener(this);
		bloby.setOnClickListener(this);
		allItms= new Hashtable<String,String[]>();
		allSkills= new Hashtable<String,String[]>();
		sa=((SharingAtts)getApplication());
		
		id =sa.getId();
		it= new ItemTest();
		
		try{
			sa.updateStat();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Toast.makeText(getApplicationContext(), String.valueOf(sa.getAllItms("101")), Toast.LENGTH_LONG).show();
	}


	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
			if(bInv2==arg0.getId()){
			i = new Intent("com.darshan.warriorgame.inventory");
			startActivity(i);
			}else if(bBS2==arg0.getId()){
				@SuppressWarnings("rawtypes")
				Class ourClass;
				try {
					ourClass = Class.forName("com.darshan.warriorgame.BattleStadium");
				i =new Intent(WelcomeScreen.this,ourClass);
				startActivity(i);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(bStore2==arg0.getId()){
				Intent inn = new Intent("com.darshan.warriorgame.store");
				startActivity(inn);
				
			}else if(bStatus2==arg0.getId()){
				i = new Intent("com.darshan.warriorgame.status");
				startActivity(i);
			}else if(arg0.getId()==R.id.bCheat2){
				cc.show();
			}else if(arg0.getId()==R.id.bSave1){
				new UpdateTables().execute();
			}else if(arg0.getId()==R.id.bChat){
				
				Intent intent = new Intent(this, ChatRoom.class);
				intent.putExtra("isWithout", true);
				intent.putExtra("topic","sports");
				intent.putExtra("uname", sa.name);
				startActivity(intent);
			
			}else if(arg0.getId()==R.id.bBattleLobby){
				Intent i = new Intent(WelcomeScreen.this,BattleLobby.class);
				startActivity(i);
			}
		
		}
		
	class UpdateTables extends AsyncTask<String,String,String>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 pDialog = new ProgressDialog(WelcomeScreen.this);
	           pDialog.setMessage("Saving Data...");
	           pDialog.setIndeterminate(false);
	           pDialog.setCancelable(true);
	         if(WelcomeScreen.this.isFinishing())
	        	 pDialog.show();
			
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			//String playerInfo = "";
			it = new ItemTest();
			String[] playerCol = it.printData("players")[1].split(" "); 
			
			for(int i=0; i<sa.inv.length; i++){
				params.add(new BasicNameValuePair("inv_"+String.valueOf(i+1),String.valueOf(sa.inv[i])));
				Log.d("Inv", "inv_"+String.valueOf(i+1)+","+String.valueOf(sa.inv[i]));
			}
			
			for(int i=0; i < sa.eqInv.length;i++){
				params.add(new BasicNameValuePair("eq_inv_"+String.valueOf(i+1),String.valueOf(sa.eqInv[i])));
				Log.d("eq_inv","eq_inv_"+String.valueOf(i+1)+","+String.valueOf(sa.eqInv[i]));
			}
			
			for(int i=0; i < sa.poInv.length;i++){
				params.add(new BasicNameValuePair("po_inv_"+String.valueOf(i+1),String.valueOf(sa.poInv[i])));
				Log.d("po_inv","po_inv_"+String.valueOf(i+1)+","+String.valueOf(sa.poInv[i]));
			}
			//params.add(new BasicNameValuePair("player_inv", playerInfo));
			params.add(new BasicNameValuePair("inv_tag", "player_inv"));
			
			//playerInfo = "";
			for(int i=0; i<sa.skills.length;i++){
				params.add(new BasicNameValuePair(String.valueOf(sa.skills[i]), String.valueOf(sa.skilllvl[i]) ));
				Log.wtf("skills", String.valueOf(sa.skills[i])+","+ String.valueOf(sa.skilllvl[i]));
			}
			params.add(new BasicNameValuePair("att_tag", "players_att"));
			for(int i=5; i<playerCol.length;i++){
				params.add(new BasicNameValuePair(playerCol[i],String.valueOf(sa.getMajatt()[i-5])));
				Log.d("player_atts", playerCol[i]+","+String.valueOf(sa.getMajatt()[i-5]));
			}
			params.add(new BasicNameValuePair("level",String.valueOf(sa.lvl)));
			Log.d("player_atts", "level"+","+String.valueOf(sa.lvl));
			params.add(new BasicNameValuePair("player_id",String.valueOf(sa.id)));
			Log.d("player_atts", "player_id"+","+String.valueOf(sa.id));
			
			Log.d("params_length", String.valueOf(params.size()));
			try {	
				JSONObject json = jsonParser.makeHttpRequest(
                    LOGIN_URL, "POST", params);
		
				String success1 = json.getString("suceess1");
				String success2 = json.getString("suceess2");
				String success3 = json.getString("suceess3");
				if(success1.equals("0")){
					Log.e("success1", success1);
					Log.e("Exception1", json.getString("Exception1"));
					Log.e("message1",json.getString("message1"));
				}else{
					Log.d("sucess1", success1);
					Log.d("message1", json.getString("message1"));
				}
				if(success2.equals("0")){
					Log.e("success2", success2);
					Log.e("Exception2", json.getString("Exception2"));
					Log.e("message2",json.getString("message2"));
				}else{
					Log.d("sucess2", success2);
					Log.d("message2", json.getString("message2"));
				}
				if(success3.equals("0")){
					Log.e("success3", success3);
					Log.e("Exception3", json.getString("Exception3"));
					Log.e("message3",json.getString("message3"));
				}else{
					Log.d("sucess3", success3);
					Log.d("message3", json.getString("message3"));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		
	}
	
	
	}


	
	//}

