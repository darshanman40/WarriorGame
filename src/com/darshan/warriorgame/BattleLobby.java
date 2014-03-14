package com.darshan.warriorgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ChatRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.LobbyRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import android.widget.ListView;

import android.widget.Toast;

public class BattleLobby extends Activity implements OnClickListener, LobbyRequestListener,
ConnectionRequestListener, NotifyListener,ChatRequestListener{

	Button ref,leave;
	WarpClient myGame;
	SharingAtts sa;
	ListView lv;
	TextView noPla;
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		myGame.disconnect();
		
		removeListeners();
		
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle_lobby);
		ref = (Button)findViewById(R.id.bRef5);
		leave = (Button)findViewById(R.id.bLeave);
		lv = (ListView)findViewById(R.id.lvOnlinePlayers);
		sa = ((SharingAtts)getApplication());
		noPla = (TextView)findViewById(R.id.tvEmpty);
		
		
		
		lv.setVisibility(View.GONE);
		WarpClient.initialize(Constants.apiKey, Constants.secretKey);
		try {
			myGame = WarpClient.getInstance();
			myGame.addConnectionRequestListener(this);  
			myGame.connectWithUserName(sa.name);
			myGame.addLobbyRequestListener(this);
			myGame.addNotificationListener(this);
			myGame.addChatRequestListener(this);
		
			//myGame.joinLobby();
			
			/*
			myGame.addZoneRequestListener(this);
			myGame.addRoomRequestListener(this);
			myGame.addChatRequestListener(this);
			
			*/
			//myGame.getOnlineUsers();
			
			//myGame.addTurnBasedRoomListener(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ref.setOnClickListener(this);
		leave.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.bRef5){
			myGame.getLiveLobbyInfo();
			
		}else if(v.getId()==R.id.bLeave){
			myGame.leaveLobby();
			
		}
	}

	//----------------------------------------------
	// LobbyListener
	//-----------------------------------------------
	
	@Override
	public void onGetLiveLobbyInfoDone(LiveRoomInfoEvent event) {
		// TODO Auto-generated method stub
		if(event.getResult()==WarpResponseResultCode.SUCCESS){
			final String[] players = event.getJoinedUsers(); 
			BattleLobby.this.runOnUiThread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					final ListView lv = (ListView) findViewById(R.id.lvOnlinePlayers);
					lv.setVisibility(View.VISIBLE);
					noPla.setVisibility(View.GONE);
					final ArrayList<String> list = new ArrayList<String>();
					for (int i = 0; i < players.length; i++) {
					      list.add(players[i]);
					    }
					
					final StableArrayAdapter adapter = new StableArrayAdapter(BattleLobby.this,
					        android.R.layout.simple_list_item_1, list);
					    lv.setAdapter(adapter);
					
					
				    
					lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							Toast.makeText(BattleLobby.this, players[arg2], Toast.LENGTH_SHORT).show();
							myGame.sendPrivateChat(players[arg2], "FistFight");
						}
					});
					
				}
			});
		}
	}

	@Override
	public void onJoinLobbyDone(LobbyEvent event) {
		// TODO Auto-generated method stub
		if(event.getResult()==WarpResponseResultCode.SUCCESS){
			Log.d("onJoinLobby", "Done");
			BattleLobby.this.runOnUiThread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					myGame.getLiveLobbyInfo();
				}
			});
			
		}else{
			Log.e("onJoinLobbyDone", event.getResult()+"");
		}
	}

	@Override
	public void onLeaveLobbyDone(LobbyEvent event) {
		// TODO Auto-generated method stub
		if(event.getResult()==WarpResponseResultCode.SUCCESS){
			Log.d("LeaveLobby", "Left Lobby");
		}else{
			Log.e("LeaveLobby", "Cant Leave Lobby");
		}
	}

	@Override
	public void onSubscribeLobbyDone(LobbyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnSubscribeLobbyDone(LobbyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//----------------------------------------------
	// ConnectionRequestListener
	//-----------------------------------------------

	@Override
	public void onConnectDone(ConnectEvent event) {
		// TODO Auto-generated method stub
		if(event.getResult()==WarpResponseResultCode.SUCCESS){
			Log.d("onConnect", "Success");
			myGame.joinLobby();
		}else{
			Log.e("onConnectDone", "Failed "+WarpResponseResultCode.CONNECTION_ERROR);
		}
	}

	@Override
	public void onDisconnectDone(ConnectEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitUDPDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	//----------------------------------------------
	// NotifyListener
	//-----------------------------------------------
	
	@Override
	public void onChatReceived(ChatEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameStarted(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameStopped(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveCompleted(MoveEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPrivateChatReceived(final String sender, final String message) {
		// TODO Auto-generated method stub
		if(message.contains("FistFight")){
		BattleLobby.this.runOnUiThread(new Runnable(){
	
			@Override
			public void run() {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						BattleLobby.this);
		 
					// set title
					alertDialogBuilder.setTitle("Challenge");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage(sender+" challenged you for fight!")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								/*
								Intent i = new Intent(BattleLobby.this,ChatActivity.class);
								i.putExtra("challenged", sender);
								i.putExtra("user", sa.name);
								startActivity(i);
								removeListeners();
								myGame.sendPrivateChat(sender, "LetsFight!");
								BattleLobby.this.finish();
								*/
								
								//myGame.createTurnRoom("Battle"+sa.name, sa.name, 2, null, 10);
								//myGame.joinRoom("Battle"+sa.name);
								myGame.sendPrivateChat(sender, "chalAcp");
								dialog.dismiss();
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								myGame.sendPrivateChat(sender, "chalDec");
								dialog.cancel();
							}
						});
					alertDialogBuilder.create();
					alertDialogBuilder.show();
				}
		});
		}else if(message.startsWith("Battle")){
			myGame.joinRoom(message);
		}else if(message.contains("chalAcp")){
			BattleLobby.this.runOnUiThread(new Runnable(){
				
				@Override
				public void run() {
					Toast.makeText(BattleLobby.this, "Challenge Accepted by " + sender, Toast.LENGTH_SHORT).show();
					Log.d(message, "Challenge Accepted by " + sender);
				}
			});
		}else if(message.contains("chalDec")){
			BattleLobby.this.runOnUiThread(new Runnable(){
				
				@Override
				public void run() {
					Toast.makeText(BattleLobby.this, "Challenge Declined by " + sender, Toast.LENGTH_SHORT).show();
					Log.d(message, "Challenge Declined by " + sender);
				}
			});
		}
	}

	@Override
	public void onRoomCreated(RoomData arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRoomDestroyed(RoomData arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdatePeersReceived(UpdateEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserChangeRoomProperty(RoomData arg0, String arg1,
			HashMap<String, Object> arg2, HashMap<String, String> arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserJoinedLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserJoinedRoom(RoomData arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserLeftLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserLeftRoom(RoomData arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserPaused(String arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserResumed(String arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}
		
	//--------------------------------
	// ChatRequestListener
	//--------------------------------
	
	@Override
	public void onSendChatDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSendPrivateChatDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	//--------------------------------
	// Custom functions
	//--------------------------------
	
	void removeListeners(){
		BattleLobby.this.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				myGame.removeConnectionRequestListener(BattleLobby.this);  
				myGame.removeChatRequestListener(BattleLobby.this);
				myGame.removeNotificationListener(BattleLobby.this);
				myGame.removeLobbyRequestListener(BattleLobby.this);
			}
		});
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId,
	        List<String> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        mIdMap.put(objects.get(i), i);
	      }
	    }

	    @Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }

	  }

}
