package com.darshan.warriorgame;

import java.util.HashMap;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ChatRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;

import android.app.Activity;
//
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends Activity implements OnClickListener,
ConnectionRequestListener, NotifyListener, ChatRequestListener{

	WarpClient myGame;
	EditText text;
	Button send;
	TextView chat;
	String user,challenged;
	SharingAtts sa;
	String msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_activity);
		
		text = (EditText)findViewById(R.id.etSendMsg);
		chat = (TextView)findViewById(R.id.tvChat);
		send = (Button)findViewById(R.id.bSend);
		
		send.setOnClickListener(this);
		user = getIntent().getStringExtra("user");
		challenged = getIntent().getStringExtra("challenged");
		sa = ((SharingAtts)getApplication());
		WarpClient.initialize(Constants.apiKey, Constants.secretKey);
		try {
			myGame = WarpClient.getInstance();
			myGame.addConnectionRequestListener(this);  
			myGame.connectWithUserName(user);
			myGame.addNotificationListener(this);
			myGame.addChatRequestListener(this);
			chat.setText("");
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
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.bSend){
			if(text.getText().toString()!=null){
				msg = text.getText().toString();
				chat.append(user+": "+msg+"\n");
				myGame.sendPrivateChat(challenged, msg);
				text.setText("");
			}else{
				Toast.makeText(ChatActivity.this, "Pls Enter Text", Toast.LENGTH_SHORT).show();
			}
	//	}else if(v.getId()==R.id){
			
		}
	}

	@Override
	public void onConnectDone(ConnectEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void onPrivateChatReceived(final String sender, final String msg) {
		// TODO Auto-generated method stub
		ChatActivity.this.runOnUiThread(new Runnable() {
			  public void run() {
				  sa = ((SharingAtts)getApplication());
				  chat.append(sender+": "+msg+"\n");	
				  ItemTest it = new ItemTest();
				  String colNames = it.printData("chat")[1];
				  DBManager dbm = new DBManager(ChatActivity.this, colNames,"chat",it.printData("chat")[0]);
				  dbm.openToWrite();
				  dbm.cretTable();
				  dbm.insertQuery(sender+" "+msg+" "+sa.name, colNames.split(" ")[1]+" "+colNames.split(" ")[2]+" "+colNames.split(" ")[3]);
				  dbm.close();
			  }
		});
		
		
		
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

	@Override
	public void onSendChatDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSendPrivateChatDone(byte result) {
		// TODO Auto-generated method stub
		if(WarpResponseResultCode.SUCCESS==result){
			Log.d("Receiver", "He is Online");
			ChatActivity.this.runOnUiThread(new Runnable() {
				  public void run() {
					  sa = ((SharingAtts)getApplication());
					  ItemTest it = new ItemTest();
					  String colNames = it.printData("chat")[1];
					  DBManager dbm = new DBManager(ChatActivity.this, colNames,"chat",it.printData("chat")[0]);
					  dbm.openToWrite();
					  dbm.cretTable();
					  dbm.insertQuery(sa.name+" "+msg+" "+challenged, colNames.split(" ")[1]+" "+colNames.split(" ")[2]+" "+colNames.split(" ")[3]);
					  dbm.close();
		
				  }
			});
			
			
		}else{
			ChatActivity.this.runOnUiThread(new Runnable() {
				  public void run() {
					  Toast.makeText(ChatActivity.this, "Receiver is offline. Come back later", Toast.LENGTH_SHORT).show();	
					  
				  }
			});
		}
	}

	
	
}
