package com.darshan.warriorgame;

import java.util.Hashtable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
	String[] minAtts = {"Physical damage","Magical damage","Physical defense","Magical defense","Extra shield damage","Shield hp","Bonus mana","Speed"};
	
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
			tvPlStr = (TextView)findViewById(R.id.tvStr5);
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
					int itmStr = Integer.valueOf(sa.getAllItms(String.valueOf(selectedItm))[2]);
					if(weapon == 0){
						if(itmStr <= sa.str){
							weapon = selectedItm;
							sa.inv[listId] = 0;
						}else{
							Toast.makeText(getApplicationContext(), "Can't equip", Toast.LENGTH_SHORT).show();
						}
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
						if(itmStr <= sa.str){
							int temp = weapon;
							weapon = selectedItm;
							sa.inv[listId] = temp;
						}else{
							Toast.makeText(getApplicationContext(), "Can't equip", Toast.LENGTH_SHORT).show();
						}
					}
					ivWea.setImageResource(retImagId(weapon));
					ivWea.setMaxHeight(26);
					ivWea.setMaxWidth(26);
					break;
				case 2:
					itmStr = Integer.valueOf(sa.getAllItms(String.valueOf(selectedItm))[2]);
					if(shield == 0){
						if(itmStr <= sa.str){
							shield = selectedItm;
							sa.inv[listId] = 0;
						}else{
							Toast.makeText(getApplicationContext(), "Can't equip", Toast.LENGTH_SHORT).show();
						}
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
						if(itmStr <= sa.str){
							int temp = shield;
							shield = selectedItm;
							sa.inv[listId] = temp;
						}else{
							Toast.makeText(getApplicationContext(), "Can't equip", Toast.LENGTH_SHORT).show();
						}
					}
					ivShield.setImageResource(retImagId(shield));
					break;
				
				case 3:
					itmStr = Integer.valueOf(sa.getAllItms(String.valueOf(selectedItm))[2]);
					if(headgear == 0){
						if(itmStr <= sa.str){
							headgear = selectedItm;
							sa.inv[listId] = 0;
						}else{
							Toast.makeText(getApplicationContext(), "Can't equip", Toast.LENGTH_SHORT).show();
						}
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
						if(itmStr <= sa.str){
							int temp = headgear;
							headgear = selectedItm;
							sa.inv[listId] = temp;
						}else{
							Toast.makeText(getApplicationContext(), "Can't equip", Toast.LENGTH_SHORT).show();
						}
					}
					ivHg.setImageResource(retImagId(headgear));
					break;
				case 4:
					itmStr = Integer.valueOf(sa.getAllItms(String.valueOf(selectedItm))[2]);
					if(armour == 0){
						if(itmStr <= sa.str){
							armour = selectedItm;
							sa.inv[listId] = 0;
						}else{
							Toast.makeText(getApplicationContext(), "Can't equip", Toast.LENGTH_SHORT).show();
						}
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
						if(itmStr <= sa.str){
							int temp = armour;
							armour = selectedItm;
							sa.inv[listId] = temp;
						}else{
							Toast.makeText(getApplicationContext(), "Can't equip", Toast.LENGTH_SHORT).show();
						}
					}
					ivAr.setImageResource(retImagId(armour));
					break;
				}
				
			
			sa.eqInv[0] = weapon;
			sa.eqInv[1] = headgear ;
			sa.eqInv[2] = armour;
			sa.eqInv[3] = shield;
			
			
			setArrayAdapter();
			
			sa.updateStat();
			Integer[] eqInvPl = sa.getBattleInv(); 
			
			//Hashtable<String,Integer[]> Opp = new Hashtable<String,Integer[]>();
			Hashtable<String,Integer[]> Pla = new Hashtable<String,Integer[]>();
			/*
			Opp.put("MinAtts", setOppMinAtts());
			Opp.put("MajAtts", sa.oppMajAtts);
			Opp.put("eqInv", sa.oppInv);
			Opp.put("Skills", sa.oppSkilllvl);
			sa.updateStat();
			*/
			
			Pla.put("MinAtts",setPlaMinAtts());
			Pla.put("MajAtts", sa.getMajatt());
			Pla.put("eqInv", eqInvPl);
			Pla.put("Skills", sa.skilllvl);

			Battle b = new Battle(Pla);
			
			
			String detail = "";
			for(int i=0; i<b.p1MinAtts.length;i++)
				detail = detail + minAtts[i]+"= "+String.valueOf(b.p1MinAtts[i])+"\n";
			//detail = "Physical Damage "+ b.p1damage; //+sa.
			tvStat.setText(detail);
			
			}else if(arg0.getId()==R.id.bSell3){
				
				if(selectedItm == armour){
					sellinPrice = Integer.valueOf(sa.getAllItms(String.valueOf(armour))[12]);
					armour=0;
				}else if(selectedItm == shield){
					sellinPrice = Integer.valueOf(sa.getAllItms(String.valueOf(shield ))[12]);
					shield = 0;
				}else if(selectedItm == headgear){
					sellinPrice = Integer.valueOf(sa.getAllItms(String.valueOf(headgear))[12]);
					headgear=0;
				}else if(selectedItm == weapon){
					sellinPrice = Integer.valueOf(sa.getAllItms(String.valueOf(weapon))[12]);
					weapon=0;
				}else{
					sellinPrice = Integer.valueOf(sa.getAllItms(String.valueOf(sa.inv[listId]))[12]);
					sa.inv[listId]=0;
				}
				sa.gold += sellinPrice;
				Toast.makeText(getApplicationContext(), sellinPrice+"", Toast.LENGTH_SHORT).show();
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
			int[] codes = new int[8];
			for(int i=0;i<sa.inv.length;i++){
				if(sa.inv[i]!=0){
					String[] m =sa.allItms.get(String.valueOf(sa.inv[i]));
					g=g+m[1]+",";
					codes[i] = sa.inv[i]/100;
				}else{
					g=g+"Empty,";
					codes[i]=0;
				}
			}
			String[] Weapons = g.split(",");
			for(int i=0;i<Weapons.length;i++){
				Weapons[i] = Weapons[i].replace("_", " ");
				Log.d("In MyEquips", Weapons[i]);
			}
			lvWeapon1.setAdapter(new MyEquipsArrayAdapter(this, Weapons,codes));
		}
	
	public int retImagId(int equip){
		switch(equip){
		case 0:
			return R.drawable.empty_inv;
		case 101:
			return R.drawable.iron_knife;
		case 102:
			return R.drawable.energy_knife;
		case 103:
			return R.drawable.silver_knife;
		case 104:
			return R.drawable.raider_sword;
		case 105:
			return R.drawable.long_sword;
		case 106:
			return R.drawable.spiked_axe;
		case 107:
			return R.drawable.heavy_blade;
		case 108:
			return R.drawable.rust_blade;
		case 109:
			return R.drawable.double_razor;
		case 110:
			return R.drawable.katana;
		case 111:
			return R.drawable.blaze_edge;
		case 112:
			return R.drawable.wooden_rod;
		case 113:
			return R.drawable.shield_breaker;
		case 114:
			return R.drawable.spike_sword;
		case 115:
			return R.drawable.demon_sword;
		case 116:
			return R.drawable.fusion_edge;
		case 117:
			return R.drawable.guard_blade;
		case 118:
			return R.drawable.golden_blade;
		case 119:
			return R.drawable.shadow_katana;
		case 120:
			return R.drawable.fallen_blade;
		case 121:
			return R.drawable.golden_rod;
		case 122:
			return R.drawable.wooden_staff;
		case 123:
			return R.drawable.shadow_spirit;
		case 124:
			return R.drawable.ion_bo;
		case 201:
			return R.drawable.leather_wrist;
		case 202:
			return R.drawable.metal_wrist;
		case 203:
			return R.drawable.wooden_guard;
		case 204:
			return R.drawable.hand_blade;
		case 205:
			return R.drawable.metal_guard;
		case 206:
			return R.drawable.wooden_shield;
		case 207:
			return R.drawable.double_blade;
		case 208:
			return R.drawable.claw_blade;
		case 209:
			return R.drawable.shadow_wrist;
		case 210:
			return R.drawable.metal_shield;
		case 211:
			return R.drawable.energy_wrist;
		case 212:
			return R.drawable.voodoo_shield;
		case 213:
			return R.drawable.blade_shield;
		case 214:
			return R.drawable.spike_shield;
		case 215:
			return R.drawable.demon_shield;
		case 216:
			return R.drawable.fallen_shield;
		case 217:
			return R.drawable.the_guardian;
		case 301:
			return R.drawable.bandana;
		case 302:
			return R.drawable.metal_band;
		case 303:
			return R.drawable.focus_band;
		case 304:
			return R.drawable.fire_band;
		case 305:
			return R.drawable.power_band;
		case 306:
			return R.drawable.snake_band;
		case 307:
			return R.drawable.demon_skin;
		case 308:
			return R.drawable.ki_band;
		case 309:
			return R.drawable.golden_band;
		case 310:
			return R.drawable.shadow_band;
		case 401:
			return R.drawable.leather_armour;
		case 402:
			return R.drawable.snake_skin;
		case 403:
			return R.drawable.metal_plates;
		case 404:
			return R.drawable.metal_armour;
		case 405:
			return R.drawable.demon_rags;
		case 406:
			return R.drawable.focus_armour;
		case 407:
			return R.drawable.golden_armour;
		case 408:
			return R.drawable.shadow_armour;
		}
		return 0;
	}
	
	public Integer[] setPlaMinAtts(){
		Integer[] ItemAtts = new Integer[]{1,1,10,10,0,0,0,0};
		for(int i=0;i<4;i++){
			String[] itmAtts=sa.getAllItms(String.valueOf(sa.eqInv[i]));
			if(itmAtts!=null){
			for(int j=3;j<itmAtts.length-4;j++)
				ItemAtts[j-3]=ItemAtts[j-3]+Integer.valueOf(itmAtts[j]);
			}
		}
		return ItemAtts;
	}
		
	}
