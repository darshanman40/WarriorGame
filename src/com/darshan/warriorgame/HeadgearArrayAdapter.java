package com.darshan.warriorgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HeadgearArrayAdapter extends ArrayAdapter<String>{

	Context context;
	String[] values;
	//Hashtable<String,String[]> allItems;
	public HeadgearArrayAdapter(Context headgearStore, String[] values) {
		super(headgearStore, R.layout.weapon_list, values);
		this.context = headgearStore;
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
 
		//int i=0;
		//for(String e:allItems.keySet()){
		//	String[] m = allItems.get(e);
		//	for(int j=0;j<m.length;j++){
		//		
		//	}
	//	}
		
		//
			tv.setText(s);
			if (s.equals("banadana")) {
				im.setImageResource(R.drawable.bandana);
				tv.setText("Bandana");
		} else if (s.equals("iOS")) {
			//im.setImageResource(R.drawable.ios_logo);
		} else if (s.equals("Blackberry")) {
		//	im.setImageResource(R.drawable.blackberry_logo);
		} else {
		//	im.setImageResource(R.drawable.android_logo);
		}
 
		return rowView;
		
		//return super.getView(position, convertView, parent);
	}
	
	
}