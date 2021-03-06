package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShieldsStore extends Activity implements OnClickListener,OnItemClickListener{

	
	TextView tvStat,tvPlStr,tvPlGold; 
	ListView lvWeapon1;//,lvWeapon2;
	Button bBuy;
	
	
	SharingAtts sa;
	ItemTest it;
	DBManager db;
	
	Integer[] inv;
	String[] armourId = {"201","202","203","204","205","206","207","208","209","210","211","212","213","214","215","216","217"};
	//Hashtable<String,String[]> allItems;
	String[] details ={"Item Id","Item","Str","Physical damage","Magical damage","Physical defense","Magical defense","Extra Shield Damage","Shield HP","Bonus Mana","Speed","Cost","Selling Price","HP Plus","Mana Plus"};
	int selectedItm=0;
	int costOfItm;
	Toast t;
	//static final String[] Weapons = 
	               //new String[] { "iron_knife", "iOS", "WindowsMobile", "Blackberry"};
	 
	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.weaponsstore);
			
			
			//allItems = new Hashtable<String,String[]>();
			it = new ItemTest();
			sa =((SharingAtts)getApplication());
			tvStat = (TextView)findViewById(R.id.tvItmStatus1);
			tvPlStr = (TextView)findViewById(R.id.tvStr1);
			tvPlGold = (TextView)findViewById(R.id.tvGold1);
			lvWeapon1 = (ListView)findViewById(R.id.lvWeapon1);
			bBuy = (Button)findViewById(R.id.bBuy);
			
			String g="";
			for(int i=0;i<armourId.length;i++){
				String[] m =sa.allItms.get(armourId[i]);
				g=g+m[1]+" ";
			}
			String[] Shields = g.split(" ");
			for(int i=0;i<Shields.length;i++){
				Shields[i] = Shields[i].replace("_", " ");
			}
			
			tvPlStr.setText("Str "+String.valueOf(sa.str));
			tvPlGold.setText("Gold "+String.valueOf(sa.gold));
			
			lvWeapon1.setAdapter(new ShieldsArrayAdapter(this, Shields));
			lvWeapon1.setOnItemClickListener(this);
			bBuy.setOnClickListener(this);

		}
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//switch(arg0.getId()){
			//case R.id.bBuy:
				
				if(selectedItm!=0){
					boolean succ=false;
					
					 //String[] h=sa.getAllItms(String.valueOf(selectedItm));
					 //= Integer.valueOf(h[11]); 
					if(costOfItm>sa.gold){
						t= Toast.makeText(getApplicationContext(), "Insuffecient Gold    "+costOfItm, Toast.LENGTH_LONG);
						t.show();
						//break;
						//boolean exit;
						//goto exit;
					}
					
					for(int i=0;i<8;i++){
						if(sa.inv[i]==0&&succ==false){
							succ=true;
							sa.inv[i]=selectedItm;
							sa.gold = sa.gold-costOfItm;
							tvPlGold.setText("Gold "+String.valueOf(sa.gold));
							
						}
					}
				if(succ!=true){
					t= Toast.makeText(getApplicationContext(), "Inventory full", Toast.LENGTH_LONG);
					t.show();
				}else{
					//sa.setPlaInv(inv);
					t= Toast.makeText(getApplicationContext(), "Item purchased successfully", Toast.LENGTH_LONG);
					t.show();
				}
				
				}else{
				exit:	t= Toast.makeText(getApplicationContext(), "Item not selected", Toast.LENGTH_LONG);
					t.show();
				}
				//break;
				
			}
			
		//}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			//if(arg2<10)
			arg2=arg2+201;
			selectedItm=arg2;
			String[] h=sa.getAllItms(String.valueOf(arg2));
			String detail="";
			for(int i=2;i<h.length;i++){
				if(!h[i].contentEquals("0"))
					detail = detail +details[i]+"   "+h[i]+"\n";
				if(details[i].contentEquals("Cost"))
					costOfItm = Integer.valueOf(h[i]);
			}
			//tvStat.setText(String.valueOf(arg2)+" "+arg3);
			tvStat.setText(detail);
		}

	}