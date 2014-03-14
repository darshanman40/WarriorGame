package com.darshan.warriorgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArmourArrayAdapter extends ArrayAdapter<String>{

	Context context;
	String[] values;
	//Hashtable<String,String[]> allItems;

	String[] armorNames ={"leather armour","snake skin","metal plates", "metal armour","demon rags","focus armour","golden armour","shadow armour"}; 
	String[] displayNames ={"Leather Armour","Snake Skin","Metal Plates", "Metal Armour","Demon Rags","Focus Armour","Golden Armour","Shadow Armour"};
	int[] armorsID = {
			R.drawable.leather_armour,R.drawable.snake_skin, R.drawable.metal_plates,
			R.drawable.metal_armour, R.drawable.demon_rags, R.drawable.focus_armour,
			R.drawable.golden_armour, R.drawable.shadow_armour
	};
	public ArmourArrayAdapter(Context armourStore, String[] values) {
		super(armourStore, R.layout.weapon_list, values);
		this.context = armourStore;
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
 
		for(int i=0; i<armorNames.length;i++){
			if(armorNames[i].equals(s)){
				im.setImageResource(armorsID[i]);
				tv.setText(displayNames[i]);
				break;
			}
		}
		
		//int i=0;
		//for(String e:allItems.keySet()){
		//	String[] m = allItems.get(e);
		//	for(int j=0;j<m.length;j++){
		//		
		//	}
	//	}
		
		//
		/*
			tv.setText(s);
			if (s.equals("leather armour")) {
				im.setImageResource(R.drawable.leather_armour);
				tv.setText("Leather Armour");
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