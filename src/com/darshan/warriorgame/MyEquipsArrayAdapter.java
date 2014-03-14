package com.darshan.warriorgame;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyEquipsArrayAdapter extends ArrayAdapter<String>{

	Context context;
	String[] values;
	ItemTest it;
	int[] code;
	//Hashtable<String,String[]> allItems;
	String[] weaponNames ={"iron knife","energy knife","silver knife", 
			"raider sword","long sword","spiked axe","heavy blade",
			"rust blade","double razor","katana",
			"blaze edge","wooden rod","shield breaker","spiked sword",
			"demon sword","fushion edge","guard blade","golden blade",
			"shadow katana","fallen blade","golden rod","wooden staff","shadow spirit"}; 
	
	String[] weaDisplayNames ={"Iron Knife","Energy Knife","Silver Knife", 
			"Raider Sword","Long Sword","Spiked Axe","Heavy Blade",
			"Rust Blade","Double Razor","Katana",
			"Blaze Edge","Wooden Rod","Shield Breaker","Spiked Sword",
			"Demon Sword","Fushion Edge","Guard Blade","Golden Blade",
			"Shadow Katana","Fallen Blade","Golden Rod","Wooden Staff","Shadow Spirit"};
	
	int[] weaponID = {
			R.drawable.iron_knife,R.drawable.energy_knife, R.drawable.silver_knife,
			R.drawable.raider_sword, R.drawable.long_sword, R.drawable.spiked_axe,
			R.drawable.heavy_blade, R.drawable.rust_blade,R.drawable.double_razor,
			R.drawable.katana, R.drawable.blaze_edge,
			R.drawable.wooden_rod, R.drawable.shield_breaker, R.drawable.spike_sword,
			R.drawable.demon_sword, R.drawable.fusion_edge,R.drawable.guard_blade,
			R.drawable.golden_blade, R.drawable.shadow_katana, R.drawable.fallen_blade,
			R.drawable.golden_rod, R.drawable.wooden_staff,
			R.drawable.shadow_spirit
			};
	
	String[] armorNames ={"leather armour","snake skin","metal plates", "metal armour","demon rags","focus armour","golden armour","shadow armour"}; 
	String[] armorDisplayNames ={"Leather Armour","Snake Skin","Metal Plates", "Metal Armour","Demon Rags","Focus Armour","Golden Armour","Shadow Armour"};
	int[] armorsID = {
			R.drawable.leather_armour,R.drawable.snake_skin, R.drawable.metal_plates,
			R.drawable.metal_armour, R.drawable.demon_rags, R.drawable.focus_armour,
			R.drawable.golden_armour, R.drawable.shadow_armour
	};
	
	String[] shieldNames ={"leather wrist","metal wrist","wooden guard","hand blade","metal guard","wooden shield","double blade","claw blade","shadow wrist","metal shield","energy wrist","voodoo shield","blade shield","spike shield","demon shield","fallen shield","the guardian" }; 
	String[] shieldDisplayNames ={"Leather Wrist","Metal Wrist","Wooden Guard","Hand Blade","Metal Guard","Wooden Shield","Double Blade","Claw Blade","Shadow Wrist","Metal Shield","Energy Wrist","Voodoo Shield","Blade Shield","Spike Shield","Demon Shield","Fallen Shield","The Guardian" };
	
	int[] shieldsID = {
			R.drawable.leather_wrist, R.drawable.metal_wrist, R.drawable.wooden_guard,
			R.drawable.hand_blade, R.drawable.metal_guard, R.drawable.wooden_shield,
			R.drawable.double_blade, R.drawable.claw_blade,R.drawable.shadow_wrist,
			R.drawable.metal_shield, R.drawable.energy_wrist, R.drawable.voodoo_shield,
			R.drawable.blade_shield, R.drawable.spike_shield, R.drawable.demon_shield,
			R.drawable.fallen_shield, R.drawable.the_guardian
		};
	
	public MyEquipsArrayAdapter(Context armourStore, String[] values, int[] code) {
		super(armourStore, R.layout.weapon_list, values);
		this.context = armourStore;
		this.values = values;
		this.code = code;
		it = new ItemTest();
		//this.allItems = allItems;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.weapon_list, parent,false);
		TextView tv = (TextView)rowView.findViewById(R.id.tvWeaName);
		ImageView im = (ImageView)rowView.findViewById(R.id.weaLogo);
		
		
		//String s = values[position];
		String s = firstUp(values[position]);
		tv.setText(s);
		Log.d("In MyEquipsArrayAdapter", s);
		//im.setImageResource(shieldsID[position]);
		
		Log.d("position", code[position]+"");
		switch(code[position]){
		case 1:
		//System.out.println(s);
		for(int i=0; i<weaDisplayNames.length;i++){
			Log.d("check ", weaDisplayNames[i]+","+s);
			if(s.contains(weaDisplayNames[i])){
				im.setImageResource(weaponID[i]);
				Log.d("Return time weapon", weaDisplayNames[i]);
				return rowView;
			}
		}
		
		case 2:
		for(int i=0; i<shieldDisplayNames.length;i++){
			if(s.contains(shieldDisplayNames[i])){
				im.setImageResource(shieldsID[i]);
				Log.d("Return time shield", shieldDisplayNames[i]);
				return rowView;
			}
		}
		case 4:
		for(int i=0; i<armorDisplayNames.length;i++){
			if(s.contains(armorDisplayNames[i])){
				im.setImageResource(armorsID[i]);
				Log.d("Return time armor", armorDisplayNames[i]);
				return rowView;
			}
		}
		
		case 3:
			tv.setText(s);
			if (s.contains("Banadana")) {
				im.setImageResource(R.drawable.bandana);
				tv.setText("Bandana");
			} else if (s.contains("Focus Band")) {
				im.setImageResource(R.drawable.focus_band);
				tv.setText("Focus Band");
			} else if (s.contains("Fire Band")) {
				im.setImageResource(R.drawable.fire_band);
				tv.setText("Fire Band");
			} else if (s.contains("Metal Band")) {
				im.setImageResource(R.drawable.metal_band);
				tv.setText("Metal Band");
			} else if (s.contains("Power Band")) {
				im.setImageResource(R.drawable.power_band);
				tv.setText("Power Band");
			} else if (s.contains("Snake Band")) {
				im.setImageResource(R.drawable.snake_band);
				tv.setText("Snake Band");
			} else if (s.contains("Demon Skin")) {
				im.setImageResource(R.drawable.demon_skin);
				tv.setText("Demon Skin");
			} else if (s.contains("Ki Band")) {
				im.setImageResource(R.drawable.ki_band);
				tv.setText("Ki Band");
			} else if (s.contains("Golden Band")) {
				im.setImageResource(R.drawable.golden_band);
				tv.setText("Golden Band");
			}else if(s.contains("Empty")){
				tv.setText("Empty");
			}
		return rowView;
		}	
		return rowView;	
	}
	
	String firstUp(String source){
		String[] words = source.split(" ");
		source ="";
		for(int i=0; i<words.length;i++)
			source = source + words[i].replaceFirst(String.valueOf(words[i].charAt(0)), String.valueOf(words[i].charAt(0)).toUpperCase())+" ";
		
		return source;
	}
	
}