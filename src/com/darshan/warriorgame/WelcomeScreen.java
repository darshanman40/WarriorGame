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
//import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

public class WelcomeScreen extends Activity implements OnClickListener{
	//SharedPreferences someData;
	TextView tvWel;
	Button bInv,bStore,bBS,bQuit,bStatus,bCheat,bSave;
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
		bInv = (Button)findViewById(R.id.bInv);
		bBS = (Button)findViewById(R.id.bBS);
		bStore = (Button)findViewById(R.id.bStore);
		bQuit = (Button)findViewById(R.id.bQuit);
		bStatus = (Button)findViewById(R.id.bStatus);
		bCheat = (Button)findViewById(R.id.bCheat2);
		bSave = (Button)findViewById(R.id.bSave1);
		
		bInv.setOnClickListener(this);
		bStore.setOnClickListener(this);
		bBS.setOnClickListener(this);
		bQuit.setOnClickListener(this);
		bStatus.setOnClickListener(this);
		bCheat.setOnClickListener(this);
		bSave.setOnClickListener(this);
		//bCheat.setVisibility(bCheat.VISIBLE);
		allItms= new Hashtable<String,String[]>();
		allSkills= new Hashtable<String,String[]>();
		sa=((SharingAtts)getApplication());
		
		id =sa.getId();
		it= new ItemTest();
		/*
		String[] s1 = it.printData("players");
		itms = new DBManager(this,s1[1],"allplayer",s1[0]);
		itms.openToRead();
		String st1=itms.queueAll(String.valueOf(id));
		itms.close();
		sa.setPlaAtts(st1.split(" "));
		
		String[] s2= it.printData("player_inv");
		itms = new DBManager(this,s2[1],"playersinv",s2[0]);
		itms.openToRead();
		String st2 = itms.queueAll(String.valueOf(id));
		itms.close();
		sa.setAllInv(st2.split(" "));
	
		String[] s3= it.printData("player_skills");
		itms = new DBManager(this,s3[1],"playerskill",s3[0]);
		itms.openToRead();
		String st3 = itms.queueAll(String.valueOf(id));
		itms.close();
		*/
		/*
		String[] s4 = it.printData("skills");
		itms = new DBManager(this,s4[1],"allskills",s4[0]);
		itms.openToRead();
		//String st4=itms.queueFew(new String[]{"skill_id"});
		String st6 = itms.queueAll();
		itms.close();
		//sa.setSkills(st3.split(" "));
		
		String[] s5 = it.printData("inventory");
		itms = new DBManager(this,s5[1],"allitems",s5[0]);
		itms.openToRead();
		String st5 =itms.queueAll();
		itms.close();
		
		
		String[] ht1 = st5.split("\n");
		String[] ht2 = st6.split("\n");
		
		for(int i=0;i<ht1.length;i++){
			String[] s = ht1[i].split(" ");
			allItms.put(s[0], s);
		}
		
		for(int i=0;i<ht2.length;i++){
			String[] s = ht2[i].split(" ");
			allSkills.put(s[0], s);
		}	
		
		sa.setAllItms(allItms);
		sa.setAllSkills(allSkills);
		*/
		/*
		String[] s4=it.printData("skills");
		//db = new DBManager(this,s4[1],"allskills",s4[0]);
		
		for(int i=2;i<s4.length;i++){
			String[] s = s4[i].split(",");
			allSkills.put(s[0], s);
		}
		
		String[] s5=it.printData("allItems");
		//db = new DBManager(this,s4[1],"allskills",s4[0]);
		
		for(int i=2;i<s5.length;i++){
			String[] s = s5[i].split(",");
			allItms.put(s[0], s);
		}
		
		sa.setAllItms(allItms);
		sa.setAllSkills(allSkills);
		*/
		//------------------------------------------------------------
		/*String[] s5=it.printData("allItems");
		db = new DBManager(this,s5[1],"allitems",s5[0]);
		db.openToWrite();
		db.dropTable();
		db.cretTable();
		for(int i=2;i<s5.length;i++)
			db.insertQuery(s5[i]);
		db.close();
		*/
		sa.updateStat();
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
				//inn.putExtra("filename", filename);
				startActivity(inn);
				
			}else if(bStatus2==arg0.getId()){
				i = new Intent("com.darshan.warriorgame.status");
				//i.putExtra("filename", filename);
				startActivity(i);
			}else if(arg0.getId()==R.id.bCheat2){
				//Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
				cc.show();
			}else if(arg0.getId()==R.id.bSave1){
			/*
				String playerInfo="";//String.valueOf(sa.id);
				for(int i=0; i<sa.inv.length; i++)
					playerInfo = playerInfo +" "+ String.valueOf(sa.inv[i]);
				for(int i=0; i < sa.eqInv.length;i++)
					playerInfo = playerInfo +" "+ String.valueOf(sa.eqInv[i]);
				for(int i=0; i < sa.poInv.length;i++)
					playerInfo = playerInfo +" "+ String.valueOf(sa.poInv[i]);
				sg.saveInv(playerInfo, String.valueOf(sa.id));
				Toast.makeText(getApplicationContext(), "eq= "+playerInfo,Toast.LENGTH_SHORT).show();
				
				playerInfo="";//String.valueOf(sa.id);
				for(int i=0; i<sa.skilllvl.length;i++)
					playerInfo = playerInfo +" "+ String.valueOf(sa.skilllvl[i]);
				sg.savePlayerSkills(playerInfo, String.valueOf(sa.id));
				Toast.makeText(getApplicationContext(), "skills= "+playerInfo,Toast.LENGTH_SHORT).show();
				
				playerInfo=String.valueOf(sa.lvl);
				for(int i=0; i<sa.getMajatt().length;i++)
					playerInfo = playerInfo + " " + sa.getMajatt()[i];
				sg.savePlayerAtts(playerInfo, String.valueOf(sa.id));
				*/
				new UpdateTables().execute();
				//Toast.makeText(getApplicationContext(), "atts= "+playerInfo,Toast.LENGTH_SHORT).show();
				//}catch(Exception e){
				//	Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_SHORT).show();
				//}
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
			String playerInfo = "";
			for(int i=0; i<sa.inv.length; i++)
				params.add(new BasicNameValuePair("inv_"+String.valueOf(i+1),String.valueOf(sa.inv[i])));
			//playerInfo = playerInfo + String.valueOf(sa.inv[i])+" ";
			
			for(int i=0; i < sa.eqInv.length;i++)
				params.add(new BasicNameValuePair("eq_inv_"+String.valueOf(i+1),String.valueOf(sa.eqInv[i])));
				//playerInfo =  playerInfo + String.valueOf(sa.eqInv[i])+" ";
			
			for(int i=0; i < sa.poInv.length;i++)
				params.add(new BasicNameValuePair("po_inv_"+String.valueOf(i+1),String.valueOf(sa.poInv[i])));
				//playerInfo = playerInfo + String.valueOf(sa.poInv[i])+" ";
			
			//params.add(new BasicNameValuePair("player_inv", playerInfo));
			params.add(new BasicNameValuePair("inv_tag", "player_inv"));
			
			playerInfo = "";
			for(int i=0; i<sa.skilllvl.length;i++)
				playerInfo = playerInfo + String.valueOf(sa.skilllvl[i])+" ";
			params.add(new BasicNameValuePair("player_skills",playerInfo));
			params.add(new BasicNameValuePair("skills_tag","playerskill"));
			
			playerInfo="";
			for(int i=0; i<sa.getMajatt().length;i++)
				playerInfo = playerInfo + sa.getMajatt()[i] + " " ;
			params.add(new BasicNameValuePair("allplayer",playerInfo));
			params.add(new BasicNameValuePair("players_tag","allplayer"));
			params.add(new BasicNameValuePair("player_id",String.valueOf(sa.id)));
			try {	
				JSONObject json = jsonParser.makeHttpRequest(
                    LOGIN_URL, "POST", params);
		
				String  success =json.getString("suceess");
				Log.d("sucess", success);
				
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
	
	}


	
	//}

