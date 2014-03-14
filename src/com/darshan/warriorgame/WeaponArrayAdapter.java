package com.darshan.warriorgame;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeaponArrayAdapter extends ArrayAdapter<String>{

	Context context;
	String[] values;
	//Hashtable<String,String[]> allItems;
	String[] weaponNames ={"iron knife","energy knife","silver knife", 
			"raider sword","long sword","spiked axe","heavy blade",
			"rust blade","double razor","katana",
			"blaze edge","wooden rod","shield breaker","spiked sword",
			"demon sword","fushion edge","guard blade","golden blade",
			"shadow katana","fallen blade","golden rod","wooden staff","shadow spirit"}; 
	
	String[] displayNames ={"Iron Knife","Energy Knife","Silver Knife", 
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
	
	public WeaponArrayAdapter(Context weaponsStore, String[] values) {
		super(weaponsStore, R.layout.weapon_list, values);
		this.context = weaponsStore;
		this.values = values;
		//this.allItems = allItems;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.weapon_list, parent,false);
		TextView tv = (TextView)rowView.findViewById(R.id.tvWeaName);
		ImageView im = (ImageView)rowView.findViewById(R.id.weaLogo);
		
		String s = values[position];
		 
		System.out.println(s);
 
		
		tv.setText(displayNames[position]);
		im.setImageResource(weaponID[position]);
		/*
	
			tv.setText(s);
			if (s.equals("iron knife")) {
				im.setImageResource(R.drawable.iron_knife);
				tv.setText("Iron Knife");
		} else if (s.equals("iOS")) {
			//im.setImageResource(R.drawable.ios_logo);
		} else if (s.equals("Blackberry")) {
		//	im.setImageResource(R.drawable.blackberry_logo);
		} else {
		//	im.setImageResource(R.drawable.android_logo);
		}
 */
		return rowView;
		
		//return super.getView(position, convertView, parent);
	}
	
	
}
