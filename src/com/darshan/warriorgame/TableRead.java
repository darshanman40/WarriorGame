package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TableRead extends Activity implements OnClickListener{

	
	EditText tbName;
	Button bTable;
	TextView tvDetail;
	DBManager db;
	ItemTest it;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tableread);
		
		
		tvDetail = (TextView)findViewById(R.id.tvTable1);
		bTable =(Button)findViewById(R.id.bShow1);
		tbName = (EditText)findViewById(R.id.etTableName1);
		it = new ItemTest();
		
		bTable.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId()==R.id.bShow1){
			if(tbName.getText().toString()==null){
				Toast.makeText(getApplicationContext(), "Pls Enter Table Name", Toast.LENGTH_SHORT).show();
			}else if(tbName.getText().toString().equals("allplayer")){
				try{
				String[] s1=it.printData("players");
				db = new DBManager(this,s1[1],"allplayer",s1[0]);	
				db.openToRead();
				String rows = db.queueAll();
				db.close();
				tvDetail.setText(rows);
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
				}
			}else if(tbName.getText().toString().equals("playersinv")){
				try{
					String[] s2=it.printData("player_inv");
					db = new DBManager(this,s2[1],"playersinv",s2[0]);
					db.openToRead();
					String rows = db.queueAll();
					db.close();
					tvDetail.setText(rows);
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
				}
			}else if(tbName.getText().toString().equals("playerskill")){
				try{
					String[] s3=it.printData("player_skills");
					db = new DBManager(this,s3[1],"playerskill",s3[0]);
					db.openToRead();
					String rows = db.queueAll();
					db.close();
					tvDetail.setText(rows);
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
				}
			}else if(tbName.getText().toString().equals("allskills")){
				try{
					String[] s4=it.printData("skills");
					db = new DBManager(this,s4[1],"allskills",s4[0]);
					db.openToRead();
					String rows = db.queueAll();
					db.close();
					tvDetail.setText(rows);
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
				}
			}else if(tbName.getText().toString().equals("allitems")){
				try{
					String[] s5=it.printData("allItems");
					db = new DBManager(this,s5[1],"allitems",s5[0]);
					db.openToRead();
					String rows = db.queueAll();
					db.close();
					tvDetail.setText(rows);
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	
	
}
