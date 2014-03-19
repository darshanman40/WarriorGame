package com.darshan.warriorgame;

//package com.darshan.appwarptest;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Hashtable;
import java.util.List;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ChatRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.RoomRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.Spinner;


public class ChatRoom extends Activity implements ConnectionRequestListener,
 ZoneRequestListener, RoomRequestListener, OnClickListener, OnItemSelectedListener,
 ChatRequestListener, NotifyListener{

	WarpClient myGame;
	DBManager dbm;
	
	Spinner srIDs;
	Button ref,chat,jLobby;
	
	ItemTest it;
	String user,colNames;
	String[] playerIds;
	String tblName = "chat";
	SharingAtts sa;
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(myGame!=null){
			myGame.disconnect();
		}
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_room);
		
		user = getIntent().getExtras().getString("uname");
		//chal = (Button)findViewById(R.id.bChal);
		chat = (Button)findViewById(R.id.bChat);
		ref = (Button)findViewById(R.id.bRefresh);
		jLobby = (Button)findViewById(R.id.bJLobby);
		srIDs = (Spinner)findViewById(R.id.srPlayers);
		
		sa = ((SharingAtts)getApplication());
		
		jLobby.setOnClickListener(this);
		chat.setOnClickListener(this);
		//chal.setOnClickListener(this);
		ref.setOnClickListener(this);
		
		it = new ItemTest();
		colNames = it.printData("chat")[1];
		WarpClient.initialize(Constants.apiKey, Constants.secretKey);
		try {
			myGame = WarpClient.getInstance();
			myGame.addConnectionRequestListener(this);  
			myGame.connectWithUserName(user);
			myGame.addZoneRequestListener(this);
			myGame.addRoomRequestListener(this);
			myGame.addChatRequestListener(this);
			myGame.addNotificationListener(this);
			myGame.getOnlineUsers();
			getPlayers();
			
			dbm = new DBManager(ChatRoom.this, colNames, tblName,it.printData("chat")[0]);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getPlayers(){
		final Handler handler=new Handler();
		final Runnable r = new Runnable(){
		    public void run(){
		        handler.postDelayed(this, 3000);
		    	if(playerIds!=null){
		    		List<String> list =null;
					list = new ArrayList<String>();
					for(int i=0;i<playerIds.length;i++){
						list.add(playerIds[i]);
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChatRoom.this,android.R.layout.simple_spinner_item, list);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					srIDs.setAdapter(adapter);
					srIDs.setOnItemSelectedListener(ChatRoom.this);
					playerIds=null;
				}
		    	
		    }
		};

		handler.postDelayed(r, 3000);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.bRefresh){
			myGame.getOnlineUsers();
			getPlayers();
		/*/
		}else if(v.getId()==chal.getId()){
			if(srIDs.getSelectedItem().toString()!=null)
				myGame.sendPrivateChat(srIDs.getSelectedItem().toString(), "Fight with me");
		*/
		}else if(v.getId()==chat.getId()){
			if(srIDs.getSelectedItem().toString()!=null){
				String sender = srIDs.getSelectedItem().toString();
				Intent i = new Intent(ChatRoom.this,ChatActivity.class);
				i.putExtra("challenged", sender);
				i.putExtra("user", user);
				startActivity(i);
				removeListeners();
				ChatRoom.this.finish();
			}
			
		}else if(v.getId()==R.id.bJLobby){
			Intent i = new Intent(ChatRoom.this,ChatHistory.class);
			//i.putExtra("challenged", sender);
			//i.putExtra("user", user);
			startActivity(i);
			removeListeners();
			ChatRoom.this.finish();
		}
	}
	
	@Override
	public void onGetLiveRoomInfoDone(LiveRoomInfoEvent event) {
		// TODO Auto-generated method stub
		if(event.getResult() == WarpResponseResultCode.SUCCESS){            
			Log.d("Success","yipee I have connected");
			myGame.getAllRooms();
        }else{
        	Log.e("ERROR","I haven't connected");
        }
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override	
	public void onJoinRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLeaveRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSetCustomRoomDataDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnlockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdatePropertyDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreateRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeleteRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetAllRoomsDone(AllRoomsEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetLiveUserInfoDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetMatchedRoomsDone(MatchedRoomsEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetOnlineUsersDone(AllUsersEvent event) {
		// TODO Auto-generated method stub
		if(event.getResult()== WarpResponseResultCode.SUCCESS){
			Log.d("getOnlineUsers", "Success");
			playerIds = event.getUserNames();
			
		}else{
			Log.e("getOnlineUsers", "Unsuccessful");
		}
	}

	@Override
	public void onSetCustomUserDataDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectDone(ConnectEvent event) {
		// TODO Auto-generated method stub
		if(event.getResult()==WarpResponseResultCode.SUCCESS){
			Log.d("onConnect", "Success");
		}else{
			Log.e("onConnect", "err");
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

	@Override
	public void onSendChatDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSendPrivateChatDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

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
		//Toast.makeText(getApplicationContext(), sender+" : "+message, Toast.LENGTH_LONG).show();

		Log.d("PrivateChat: "+sender, message);
		if(message.contains("Fight with me")){
		ChatRoom.this.runOnUiThread(new Runnable() {
			  public void run() {
				  sa = ((SharingAtts)getApplication());
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				ChatRoom.this);
 
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
						Intent i = new Intent(ChatRoom.this,ChatActivity.class);
						i.putExtra("challenged", sender);
						i.putExtra("user", user);
						startActivity(i);
						removeListeners();
						myGame.sendPrivateChat(sender, "LetsFight!");
						ChatRoom.this.finish();
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
			alertDialogBuilder.create();
			alertDialogBuilder.show();
			  }
			});
		}else if(message.contains("LetsFight!")){
			Log.d("Fight", "Challenge Accepted");
			ChatRoom.this.runOnUiThread(new Runnable() {
				  public void run() {
					  	Intent i = new Intent(ChatRoom.this,ChatActivity.class);
						i.putExtra("challenged", sender);
						i.putExtra("user", user);
						startActivity(i);
						removeListeners();
						ChatRoom.this.finish();
				  }
			});
		}else{
			final Activity c;
			if(getParent()==null){
				c= ChatRoom.this;
				Log.d("Activity","BattleTime");
				
				//Log.d("Activity", c.getTitle().toString()+" getParent");
				
			}else{
				c = getParent();
				Log.d("Activity", c.getTitle().toString()+" getParent");
			}	
			
			c.runOnUiThread(new Runnable(){
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					sa = ((SharingAtts)getApplication());
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c); 
					final EditText qChat = new EditText(c);
					qChat.setId(123);
					
					// set title
					alertDialogBuilder.setTitle(sender+" says:");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage(message)
						.setCancelable(false)
						.setPositiveButton("Chat",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								Intent i = new Intent(c,ChatActivity.class);
								i.putExtra("challenged", sender);
								i.putExtra("user", user);
								startActivity(i);
								removeListeners();
								dialog.cancel();
								ChatRoom.this.finish();
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						})
						.setNeutralButton("Quick Chat",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
									if(qChat.getText().toString()!=null){
										String msg = qChat.getText().toString();
										myGame.sendPrivateChat(sender, msg);
										ItemTest it = new ItemTest();
										colNames = it.printData("chat")[1];
										dbm = new DBManager(c, colNames,"chat",it.printData("chat")[0]);
										dbm.openToWrite();
										dbm.cretTable();
										
										Log.d("content", sa.name+" "+qChat.getText().toString()+" "+sender);
										msg = msg.replace(" ", "?*");
										dbm.insertQuery(sa.name+" "+msg+" "+sender, colNames.split(" ")[1]+" "+colNames.split(" ")[2]+" "+colNames.split(" ")[3]);
										dbm.close();
										
										
										dialog.cancel();
									}else{
										Toast.makeText(c, "Pls enter text", Toast.LENGTH_SHORT).show();
									}
								
								}
							})
						.setView(qChat);
				
						
					alertDialogBuilder.create();
					alertDialogBuilder.show();
					ItemTest it = new ItemTest();
					colNames = it.printData("chat")[1];
					dbm = new DBManager(c, colNames,"chat",it.printData("chat")[0]);
					dbm.openToWrite();
					dbm.cretTable();
					String message2 = message.replace(" ", "?*");
					Log.d("insertQuery 492", sender+" "+message2+" "+sa.name);
					dbm.insertQuery(sender+" "+message2+" "+sa.name, colNames.split(" ")[1]+" "+colNames.split(" ")[2]+" "+colNames.split(" ")[3]);
					dbm.close();
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

	public void removeListeners(){
		myGame.removeConnectionRequestListener(this);  
		myGame.removeZoneRequestListener(this);
		myGame.removeRoomRequestListener(this);
		myGame.removeChatRequestListener(this);
		myGame.removeNotificationListener(this);
	}	
	
	public void onDestroy(){
		super.onDestroy();
		removeListeners();
		
	}

	

}

