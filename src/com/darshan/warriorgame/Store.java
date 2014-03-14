package com.darshan.warriorgame;


import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Store extends TabActivity{

	
	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store);
		tabHost = getTabHost();
		
		//Tab for Weapons
		TabSpec weapons = tabHost.newTabSpec("Weapons");
        // setting Title and Icon for the Tab
		weapons.setIndicator("Weapons", getResources().getDrawable(R.drawable.weapon));
        Intent weaIntent = new Intent(this, WeaponsStore.class);
        weapons.setContent(weaIntent);
        
        //Tab for armour
        TabSpec armour = tabHost.newTabSpec("Armours");
        // setting Title and Icon for the Tab
		armour.setIndicator("Armours", getResources().getDrawable(R.drawable.leather_armour));
        Intent armIntent = new Intent(this, ArmourStore.class);
        armour.setContent(armIntent);
        
        //Tab for Shields
        TabSpec shield = tabHost.newTabSpec("Shields");
        // setting Title and Icon for the Tab
        shield.setIndicator("Shields", getResources().getDrawable(R.drawable.shield));
        Intent shieldIntent = new Intent(this, ShieldsStore.class);
        shield.setContent(shieldIntent);
        
      //Tab for Headgears
        TabSpec Headgears = tabHost.newTabSpec("Headgear");
        // setting Title and Icon for the Tab
        Headgears.setIndicator("Headgear", getResources().getDrawable(R.drawable.headgear));
        Intent HeadgearsIntent = new Intent(this, HeadgearStore.class);
        Headgears.setContent(HeadgearsIntent);
        
        //Tab for Potions
        TabSpec Potions = tabHost.newTabSpec("Potions");
        // setting Title and Icon for the Tab
        Potions.setIndicator("Potions", getResources().getDrawable(R.drawable.life_potion));
        Intent PotionsIntent = new Intent(this, PotionStore.class);
        Potions.setContent(PotionsIntent);
        
        tabHost.addTab(weapons);
        tabHost.addTab(armour);
        tabHost.addTab(shield);
        tabHost.addTab(Headgears);
        tabHost.addTab(Potions);
		
	}
	

	
	
}
