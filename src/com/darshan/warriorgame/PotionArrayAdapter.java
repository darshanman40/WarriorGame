package com.darshan.warriorgame;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PotionArrayAdapter extends ArrayAdapter<String>{

	Context context;
	String[] values;
	//Hashtable<String,String[]> allItems;
	
	
	
	public PotionArrayAdapter(Context pStore, String[] values) {
		super(pStore, R.layout.weapon_list, values);
		this.context = pStore;
		this.values = values;
		//this.allItems = allItems;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.potion_list, parent,false);
		TextView tv = (TextView)rowView.findViewById(R.id.tvPotName);
		ImageView im = (ImageView)rowView.findViewById(R.id.potLogo);
		
		String s = values[position];
		 
		System.out.println(s);
 
	
			tv.setText(s);
			if (s.equals("life potion")) {
				im.setImageResource(R.drawable.life_potion);
				tv.setText("Life Potion");
		} else if (s.equals("mana potion")) {
			im.setImageResource(R.drawable.mana_potion);
			tv.setText("Mana Potion");
		} else if (s.equals("Blackberry")) {
		//	im.setImageResource(R.drawable.blackberry_logo);
		} else {
		//	im.setImageResource(R.drawable.android_logo);
		}
 
		return rowView;
		
		//return super.getView(position, convertView, parent);
	}
}
