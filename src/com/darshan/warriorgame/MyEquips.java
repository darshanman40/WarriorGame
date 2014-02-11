package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyEquips extends Activity implements OnClickListener,OnItemClickListener{

	
	TextView tvStat,tvPlStr,tvPlGold,lifePot,manaPot; 
	ListView lvWeapon1;//,lvWeapon2;
	Button bEquip;
	ImageView ivHg,ivAr,ivWea,ivShield;
	
	SharingAtts sa;
	ItemTest it;
	DBManager db;
	
	Integer[] inv;
	String[] armourId = {"401","402","403","404","405","406","407","408"};
	String[] details ={"Item Id","Item","Str","Physical damage","Magical damage","Physical defense","Magical defense","Extra Shield Damage","Shield HP","Bonus Mana","Speed","Cost","Selling Price","HP Plus","Mana Plus"};
	int selectedItm=0;
	
	int costOfItm,listId,sellinPrice;
	Toast t;
	int shield,armour,weapon,headgear;
	 
	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.inventory);
			
			
			//allItems = new Hashtable<String,String[]>();
			it = new ItemTest();
			sa =((SharingAtts)getApplication());
			tvStat = (TextView)findViewById(R.id.tvDetail3);
			tvPlStr = (TextView)findViewById(R.id.tvStr3);
			tvPlGold = (TextView)findViewById(R.id.tvGold3);
			lifePot = (TextView)findViewById(R.id.tvLifePot1);
			manaPot = (TextView)findViewById(R.id.tvManaPot1);
			lvWeapon1 = (ListView)findViewById(R.id.lvMyEqps1);
			bEquip = (Button)findViewById(R.id.bEquip3);
			ivHg=(ImageView)findViewById(R.id.ivheadGear1);
			ivAr = (ImageView)findViewById(R.id.ivArmour1);
			ivWea = (ImageView)findViewById(R.id.ivWeapon1);
			ivShield = (ImageView)findViewById(R.id.ivShield1);
			
			
			
			tvPlStr.setText("Str "+String.valueOf(sa.str));
			tvPlGold.setText("Gold "+String.valueOf(sa.gold));
			setArrayAdapter();
			
			
			lvWeapon1.setOnItemClickListener(this);
			bEquip.setOnClickListener(this);
			ivHg.setOnClickListener(this);
			ivShield.setOnClickListener(this);
			ivWea.setOnClickListener(this);
			ivAr.setOnClickListener(this);
			
			weapon = sa.eqInv[0];
			headgear = sa.eqInv[1];
			armour = sa.eqInv[2];
			shield = sa.eqInv[3];
			
			
			String temp = lifePot.getText().toString();
			lifePot.setText(temp+" "+sa.poInv[0]);
			temp = manaPot.getText().toString();
			manaPot.setText(temp+" "+sa.poInv[1]);
			
			ivWea.setImageResource(retImagId(weapon));
			ivHg.setImageResource(retImagId(headgear));
			ivAr.setImageResource(retImagId(armour));
			ivShield.setImageResource(retImagId(shield));

		}
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//switch(arg0.getId()){
			if(arg0.getId()==R.id.ivArmour1){
				if(armour==0){
					tvStat.setText("Empty");
				}else{
					selectedItm = armour;
					String[] h=sa.getAllItms(String.valueOf(selectedItm));
					String detail="";
					for(int i=1;i<h.length;i++){
						if(!h[i].contentEquals("0"))
							detail = detail +details[i]+"   "+h[i]+"\n";
						if(details[i].contentEquals("Cost"))
							costOfItm = Integer.valueOf(h[i]);
					}
					tvStat.setText(detail);
				}
			
			//break;
			
			}else if(arg0.getId()==R.id.ivheadGear1){
				if(headgear==0){
					tvStat.setText("Empty");
				}else{
					selectedItm = headgear;
					String[] h=sa.getAllItms(String.valueOf(selectedItm));
					String detail="";
					for(int i=1;i<h.length;i++){
						if(!h[i].contentEquals("0"))
							detail = detail +details[i]+"   "+h[i]+"\n";
						if(details[i].contentEquals("Cost"))
							costOfItm = Integer.valueOf(h[i]);
					}
					tvStat.setText(detail);
				}
			
			}else if(arg0.getId()==R.id.ivWeapon1){
				if(weapon==0){
					tvStat.setText("Empty");
				}else{
					selectedItm = weapon;
					String[] h=sa.getAllItms(String.valueOf(selectedItm));
					String detail="";
					for(int i=1;i<h.length;i++){
						if(!h[i].contentEquals("0"))
							detail = detail +details[i]+"   "+h[i]+"\n";
						if(details[i].contentEquals("Cost"))
							costOfItm = Integer.valueOf(h[i]);
					}
					tvStat.setText(detail);
				}
			}else if(arg0.getId()==R.id.ivShield1){
				if(shield==0){
					tvStat.setText("Empty");
				}else{
					selectedItm = shield;
					String[] h=sa.getAllItms(String.valueOf(selectedItm));
					String detail="";
					for(int i=1;i<h.length;i++){
						if(!h[i].contentEquals("0"))
							detail = detail +details[i]+"   "+h[i]+"\n";
						if(details[i].contentEquals("Cost"))
							costOfItm = Integer.valueOf(h[i]);
					}
					tvStat.setText(detail);
				}
			
			}else if(arg0.getId()==R.id.bEquip3){
				int typeEquip = selectedItm/100;
				switch(typeEquip){
				case 1:
					if(weapon == 0){
						weapon = selectedItm;
						sa.inv[listId] = 0;
						//if(retImagId(weapon)!=0)
							
					}else if(weapon==selectedItm){
						for(int i=0; i<sa.inv.length;i++){
							if(sa.inv[i]==0){
								sa.inv[i]=selectedItm;
								selectedItm=weapon=0;
							}
						}
						if(selectedItm!=0){
							t = Toast.makeText(getApplicationContext(), "Inventory full", Toast.LENGTH_LONG);
							t.show();
						}else{
							t = Toast.makeText(getApplicationContext(), "Item unequiped", Toast.LENGTH_LONG);
							t.show();
						}
					}else{
						int temp = weapon;
						weapon = selectedItm;
						sa.inv[listId] = temp;
					}
					ivWea.setImageResource(retImagId(weapon));
					break;
				case 2:
					if(shield == 0){
						shield = selectedItm;
						sa.inv[listId] = 0;
					}else if(shield==selectedItm){
						for(int i=0; i<sa.inv.length;i++){
							if(sa.inv[i]==0){
								sa.inv[i]=selectedItm;
								selectedItm=shield=0;
							}
						}
						if(selectedItm!=0){
							t = Toast.makeText(getApplicationContext(), "Inventory full", Toast.LENGTH_LONG);
							t.show();
						}else{
							t = Toast.makeText(getApplicationContext(), "Item unequiped", Toast.LENGTH_LONG);
							t.show();
						}
					}else{
						int temp = shield;
						shield = selectedItm;
						sa.inv[listId] = temp;
					}
					ivShield.setImageResource(retImagId(shield));
					break;
				
				case 3:
					if(headgear == 0){
						headgear = selectedItm;
						sa.inv[listId] = 0;
					}else if(headgear==selectedItm){
						for(int i=0; i<sa.inv.length;i++){
							if(sa.inv[i]==0){
								sa.inv[i]=selectedItm;
								selectedItm=headgear=0;
							}
						}
						if(selectedItm!=0){
							t = Toast.makeText(getApplicationContext(), "Inventory full", Toast.LENGTH_LONG);
							t.show();
						}else{
							t = Toast.makeText(getApplicationContext(), "Item unequiped", Toast.LENGTH_LONG);
							t.show();
						}
					}else{
						int temp = headgear;
						headgear = selectedItm;
						sa.inv[listId] = temp;
					}
					ivHg.setImageResource(retImagId(headgear));
					break;
				case 4:
					if(armour == 0){
						armour = selectedItm;
						sa.inv[listId] = 0;
					}else if(armour==selectedItm){
						for(int i=0; i<sa.inv.length;i++){
							if(sa.inv[i]==0){
								sa.inv[i]=selectedItm;
								selectedItm=armour=0;
							}
						}
						if(selectedItm!=0){
							t = Toast.makeText(getApplicationContext(), "Inventory full", Toast.LENGTH_LONG);
							t.show();
						}else{
							t = Toast.makeText(getApplicationContext(), "Item unequiped", Toast.LENGTH_LONG);
							t.show();
						}
					}else{
						int temp = armour;
						armour = selectedItm;
						sa.inv[listId] = temp;
					}
					ivAr.setImageResource(retImagId(armour));
					break;
				}
				
			
			sa.eqInv[0] = weapon;
			sa.eqInv[1] = headgear ;
			sa.eqInv[2] = armour;
			sa.eqInv[3] = shield;
			
			
			setArrayAdapter();
			}else if(arg0.getId()==R.id.bSell3){
				sa.gold = sa.gold+sellinPrice;
				if(selectedItm == armour)
					armour=0;
				else if(selectedItm == shield)
					shield = 0;
				else if(selectedItm == headgear)
					headgear=0;
				else if(selectedItm == weapon)
					weapon=0;
				else
					sa.inv[listId]=0;
				//break;
			}

		}
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			selectedItm = sa.inv[arg2];
			String[] h=sa.getAllItms(String.valueOf(selectedItm));
			String detail="";
			for(int i=1;i<h.length;i++){
				if(!h[i].contentEquals("0"))
					detail = detail +details[i]+"   "+h[i]+"\n";
				if(details[i].contentEquals("Cost"))
					costOfItm = Integer.valueOf(h[i]);
				if(details[i].contentEquals("Selling Price"))
					sellinPrice = Integer.valueOf(h[i]);
				
			}
			//tvStat.setText(String.valueOf(arg2));
			tvStat.setText(detail);
			listId=arg2;
		}

		
	public void setArrayAdapter(){
			String g="";
			for(int i=0;i<sa.inv.length;i++){
				if(sa.inv[i]!=0){
					String[] m =sa.allItms.get(String.valueOf(sa.inv[i]));
					g=g+m[1]+" ";
				}else{
					g=g+"Empty ";
				}
			}
			String[] Weapons = g.split(" ");
			for(int i=0;i<Weapons.length;i++){
				Weapons[i] = Weapons[i].replace("_", " ");
			}
			lvWeapon1.setAdapter(new MyEquipsArrayAdapter(this, Weapons));
		}
	
	public int retImagId(int equip){
		switch(equip){
		case 0:
			return R.drawable.empty_inv;
		case 101:
			return R.drawable.iron_knife;
		case 201:
			return R.drawable.leather_wrist;
		case 301:
			return R.drawable.bandana;
		case 401:
			return R.drawable.leather_armour;
		}
		return 0;
	}
		
	}
