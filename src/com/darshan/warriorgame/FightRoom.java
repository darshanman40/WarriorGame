package com.darshan.warriorgame;

import java.util.HashMap;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.TurnBasedRoomListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FightRoom extends Activity implements OnClickListener, ConnectionRequestListener,
TurnBasedRoomListener, NotifyListener,ZoneRequestListener{

	TextView fDetail;
	Button send;
	EditText attack;

	WarpClient myGame;
	SharingAtts sa;
	String opp;
	final String TAG = "Attack"; 
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	
		myGame.stopGame();
		removeListener();
		myGame.disconnect();
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fight_room);
		
		send = (Button)findViewById(R.id.bSend6);
		fDetail = (TextView)findViewById(R.id.tvFDetail); 
		attack = (EditText)findViewById(R.id.etAttack);
		
		opp = getIntent().getExtras().getString("opp");
		
		send.setOnClickListener(this);
		//TAG = send.getText().toString();
		sa = ((SharingAtts)getApplication());
		
		WarpClient.initialize(Constants.apiKey, Constants.secretKey);
		
		try {
			
			myGame = WarpClient.getInstance();
			
			myGame.addConnectionRequestListener(this);
			myGame.addTurnBasedRoomListener(this);
			myGame.addZoneRequestListener(this);
			myGame.addNotificationListener(this);
			if(getIntent().getBooleanExtra("challenged", false)){
				
			}else{
				myGame.createTurnRoom("Battle"+sa.name, sa.name, 2, null, 10);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.bSend6){
			myGame.sendMove(attack.getText().toString());
		}
	}
	
	
	public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            send.setText(TAG);
            send.setEnabled(true);
        }

        @Override
        public void onFinish() {
            send.setText("Hold "+TAG);
            send.setEnabled(false);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            send.setText( TAG +" "+(millisUntilFinished / 1000) );
        }
    }

	//------------------------------
	//	ConnectionRequestListener.
	//-------------------------------
	
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
	
	//------------------------------------
	//	TurnBasedRoomListener
	//------------------------------------

	@Override
	public void onGetMoveHistoryDone(byte arg0, MoveEvent[] arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSendMoveDone(byte arg0) {
		// TODO Auto-generated method stub
		FightRoom.this.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MyCount counter = new MyCount(0,0);
				counter.onFinish();
			}
			
		});
	}

	@Override
	public void onStartGameDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopGameDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}
	//------------------------------------
	//	NotifyListener
	//------------------------------------

	@Override
	public void onChatReceived(ChatEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameStarted(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		FightRoom.this.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				 MyCount counter = new MyCount(10000, 1000);
			     counter.start();
			}
		});
		
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
	public void onPrivateChatReceived(String sender, String message) {
		// TODO Auto-generated method stub
		if(message.startsWith("Battle")){
			myGame.joinRoom(message);
			myGame.startGame();
		}else{
			
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
	//---------------------------------
	//	ZoneRequestListener
	//---------------------------------

	@Override
	public void onCreateRoomDone(RoomEvent event) {
		// TODO Auto-generated method stub
		if(event.getResult()==WarpResponseResultCode.SUCCESS){
			myGame.joinRoom("Battle"+sa.name);
			myGame.sendPrivateChat(opp, "Battle"+sa.name);
			
		}else{
			Log.e("onCreateRoomDone", event.getResult()+"");
			
		}
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
	public void onGetOnlineUsersDone(AllUsersEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSetCustomUserDataDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	void removeListener(){
		myGame.removeConnectionRequestListener(this);
		myGame.removeTurnBasedRoomListener(this);
		myGame.removeZoneRequestListener(this);
		myGame.removeNotificationListener(this);
	}
	
}
