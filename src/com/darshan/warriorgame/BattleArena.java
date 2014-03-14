package com.darshan.warriorgame;


//import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
import android.os.Bundle;
//import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
//import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.Toast;
//import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class BattleArena extends Activity implements OnClickListener{
	
	String t;
	String TAG_LINE ="Line #";
	String[] skilName = new String[]{"stab","double_strike","speed_strike","speed_strike","vertical_strike","shadow_blend","inner_strength","avenger","split","execution","shurikens","energy_shot","blast_fire","energy_field","charge","mana_bomb","heal","shadow_strike","annihilate","shadow_replicate"};
	String[] skilId = new String[]{"1001","1011","1012","1021","1022","1031","1041","1042","1051","2001","2011","2012","2021","2022","2031","2032","2041","2042","2051"};
	ProgressBar pbCHp,pbPHp;
	RadioGroup rgAttacks;
	TextView tvCpClass,tvCpLevel,tvPClass,tvPLevel, tvPName, tvFStat, tvCpName, tvLPQty, tvMPQty ;
	ImageView bSkill2,bSkill1,bSkill3,bSkill4,bSkill5,bSkill6,bSkill7
	,bSkill8,bSkill9,bSkill10,bSkill11,bSkill12,bSkill13,
	bSkill14,bSkill15,bSkill16;
	ImageView ivMPot,ivLPot;
	Button attack;
	ScrollView svFStat;
	
	RelativeLayout rl;
	LayoutParams lp;
	
	List<Integer> lAttackCodes,p1SuccStrike,p2SuccStrike;//Samurai,lSamuraiDan,lNinja,lWizard,lBal,lBalDan;
	List<Integer> skillIndex;
	
	Integer[] p1MajAtts,p2MajAtts,p1MinAtts,p2MinAtts, p1Inv,p2Inv,p1Skill,p2Skill;
	int comId;
	String p1name,p2name;
	
	
	Player pl,comPl;
	SharingAtts sa;
	Battle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battlearena);
		sa = ((SharingAtts)getApplication());
		lAttackCodes = new LinkedList<Integer>();
		
		p1name = sa.name;
		p2name = getIntent().getStringExtra("oppName");
		pbPHp = (ProgressBar)findViewById(R.id.pbPHp);
		pbCHp = (ProgressBar)findViewById(R.id.pbComHp);
		attack = (Button)findViewById(R.id.bAttack);
		//bSkill1 = (Button)findViewById(R.id.bSkill1);
		
		ivLPot = (ImageView)findViewById(R.id.ivLPotion);
		ivMPot = (ImageView)findViewById(R.id.ivMPotion);
		
		bSkill2 = (ImageView)findViewById(R.id.ivStab);
		bSkill3 = (ImageView)findViewById(R.id.ivDouble_strike);
		bSkill4 = (ImageView)findViewById(R.id.ivSpeed_strike);
		bSkill5 = (ImageView)findViewById(R.id.ivShuriken);
		bSkill6 = (ImageView)findViewById(R.id.ivBlast_fire);
		bSkill7 = (ImageView)findViewById(R.id.ivVertical_strike);
		bSkill8 = (ImageView)findViewById(R.id.ivCharge);
		bSkill9 = (ImageView)findViewById(R.id.ivHeal);
		bSkill10 = (ImageView)findViewById(R.id.ivEnergy_shot);
		bSkill11 = (ImageView)findViewById(R.id.ivAnnihilate);
		bSkill12 = (ImageView)findViewById(R.id.ivShadow_strike);
		bSkill13 = (ImageView)findViewById(R.id.ivSplit);
		bSkill14 = (ImageView)findViewById(R.id.ivAvenger);
		bSkill15 = (ImageView)findViewById(R.id.ivMana_bomb);
		bSkill16 = (ImageView)findViewById(R.id.ivExecution);
		
		tvLPQty = (TextView) findViewById(R.id.tvLPQty);
		tvMPQty = (TextView) findViewById(R.id.tvMPQty);
		tvCpClass = (TextView) findViewById(R.id.tvCpClass);
		tvCpLevel = (TextView) findViewById(R.id.tvCpLevel);
		tvPClass = (TextView) findViewById(R.id.tvPClass);
		tvPLevel = (TextView) findViewById(R.id.tvPLevel);
		tvPName = (TextView) findViewById(R.id.tvPName);
		tvFStat = (TextView) findViewById(R.id.tvFStat);
		tvCpName = (TextView) findViewById(R.id.tvComp);

		svFStat = (ScrollView)findViewById(R.id.svFStat1);
		
		tvFStat.setMovementMethod(new ScrollingMovementMethod());
		tvFStat.setGravity(android.R.attr.bottom);
		
		tvCpClass.setText(getIntent().getStringExtra("oppClass"));
		
		tvPClass.setText(sa.playerClass);//sa.playerClass);
		tvPName.setText(p1name);//sa.name);
		
		tvLPQty.setText("x"+sa.poInv[0]);
		tvMPQty.setText("x"+sa.poInv[1]);
		tvCpName.setText(p2name);
		//*/
		ivLPot.setOnClickListener(this);
		ivMPot.setOnClickListener(this);
		attack.setOnClickListener(this);
		//bSkill1.setOnClickListener(this);
		if(sa.skilllvl[0]!=0){
			bSkill2.setImageResource(com.darshan.warriorgame.R.drawable.stab_act);
			bSkill2.setOnClickListener(this);
		}
		
		if(sa.skilllvl[1]!=0){
			bSkill3.setImageResource(com.darshan.warriorgame.R.drawable.double_strike_act);
			bSkill3.setOnClickListener(this);
		}
		
		if(sa.skilllvl[2]!=0){
			bSkill4.setImageResource(com.darshan.warriorgame.R.drawable.speed_strike_act);
			bSkill4.setOnClickListener(this);
		}
		if(sa.skilllvl[7]!=0){
			bSkill5.setImageResource(com.darshan.warriorgame.R.drawable.shuriken_act);
			bSkill5.setOnClickListener(this);
		}
		if(sa.skilllvl[9]!=0){
			bSkill6.setImageResource(com.darshan.warriorgame.R.drawable.blast_fire_act);
			bSkill6.setOnClickListener(this);
		}
		if(sa.skilllvl[3]!=0){
			bSkill7.setImageResource(com.darshan.warriorgame.R.drawable.vertical_strike_act);
			bSkill7.setOnClickListener(this);
		}
		if(sa.skilllvl[10]!=0){
			bSkill8.setImageResource(com.darshan.warriorgame.R.drawable.charge_act);
			bSkill8.setOnClickListener(this);
		}
		if(sa.skilllvl[12]!=0){
			bSkill9.setImageResource(com.darshan.warriorgame.R.drawable.heal_act);
			bSkill9.setOnClickListener(this);
		}
		if(sa.skilllvl[8]!=0){
			bSkill10.setImageResource(com.darshan.warriorgame.R.drawable.energy_shot_act);
			bSkill10.setOnClickListener(this);
		}
		if(sa.skilllvl[14]!=0){
			bSkill11.setImageResource(com.darshan.warriorgame.R.drawable.annihilate_act);
			bSkill11.setOnClickListener(this);
		}if(sa.skilllvl[13]!=0){
			bSkill12.setImageResource(com.darshan.warriorgame.R.drawable.shadow_strike_act);
			bSkill12.setOnClickListener(this);
		}if(sa.skilllvl[5]!=0){
			bSkill13.setImageResource(com.darshan.warriorgame.R.drawable.split_act);
			bSkill13.setOnClickListener(this);
		}if(sa.skilllvl[4]!=0){
			bSkill14.setImageResource(com.darshan.warriorgame.R.drawable.avenger_act);
			bSkill14.setOnClickListener(this);
		}if(sa.skilllvl[11]!=0){
			bSkill15.setImageResource(com.darshan.warriorgame.R.drawable.mana_bomb_act);
			bSkill15.setOnClickListener(this);
		}
		if(sa.skilllvl[6]!=0){
			bSkill16.setImageResource(com.darshan.warriorgame.R.drawable.execution_act);
			bSkill16.setOnClickListener(this);
		}
		hastablesFor();
		sa.updateStat();
		
		tvPLevel.setText(String.valueOf(sa.lvl)+ "\t" + b.p1hp+"/"+b.maxp1hp+"\t"+ b.p1mana+"/"+b.maxp1mana);
		tvCpLevel.setText(getIntent().getStringExtra("oppLevel")+"\t"+b.p2hp+"/"+b.maxp2hp+"\t"+b.p2mana+"/"+b.maxp2mana);//comPl.lvl));
		
		//createButtons();
		
	}

	public int dpToPx(int dp) {
	    DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
	    int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));       
	    return px;
	}
	
	public void hastablesFor(){
		
		sa = ((SharingAtts)getApplication());
		Integer[] eqInvPl = sa.getBattleInv(); 
		
		Hashtable<String,Integer[]> Opp = new Hashtable<String,Integer[]>();
		Hashtable<String,Integer[]> Pla = new Hashtable<String,Integer[]>();
		
		Opp.put("MinAtts", setOppMinAtts());
		Opp.put("MajAtts", sa.oppMajAtts);
		Opp.put("eqInv", sa.oppInv);
		Opp.put("Skills", sa.oppSkilllvl);
		sa.updateStat();
		Pla.put("MinAtts",setPlaMinAtts());
		Pla.put("MajAtts", sa.getMajatt());
		Pla.put("eqInv", eqInvPl);
		Pla.put("Skills", sa.skilllvl);

		b = new Battle(Pla,Opp);
		
		pbPHp.setProgress((int)((sa.hp*100)/sa.maxHp));
		pbCHp.setProgress((int)(b.maxp2hp*100/b.maxp2hp));
		
		initSuccStrike();
		initForOpp();
	}
	
	
	public void AI2(){
		try{
		@SuppressWarnings("unused")
		String battleDetails="";
		if(skillIndex==null || skillIndex.isEmpty()){
			skillIndex = new LinkedList<Integer>(); Log.d(TAG_LINE, "236");
			for(int i=0;i<b.p2Skills.length;i++){
				if(b.p2Skills[i]>0)
					skillIndex.add(b.p2Skills[i]);
			}
		}
		int multiplier = skillIndex.size()/3;
		//List<Integer> lowAttackCodes = new ArrayList<Integer>();
		/*
		List<Integer> midAttackCodes = new ArrayList<Integer>();
		List<Integer> hiAttackCodes = new ArrayList<Integer>();
		*/
		//lowAttackCodes.add(0);
		/*
		for(int i=0; i<skillIndex.size()/2;i++){
			midAttackCodes.add(skillIndex.get(i));
		}
		for(int i=skillIndex.size()/2;i<skillIndex.size();i++){
			hiAttackCodes.add(skillIndex.get(i));
		}
		 */
		if(lAttackCodes.isEmpty() || lAttackCodes==null){	
			initForOpp(); Log.d(TAG_LINE, 257+"");
			Collections.shuffle(lAttackCodes);
		}
		b.damage(lAttackCodes.get(0)*multiplier, 1);
		/*
		
		//	for(int i=0; i<skillIndex.size();i++){
				Log.d("AttackCodes", lAttackCodes.get(0)+"");
				if(lAttackCodes.get(0)==0){  
					b.damage(0,1); Log.d(TAG_LINE, "262");
					battleDetails="P2 used normal Attack\n";
				}else if(lAttackCodes.get(0)==1){ 
					b.damage(1, 1); Log.d(TAG_LINE, "265");
					battleDetails="P2 used "+skilName[0]+" Attack\n";
				}else if(lAttackCodes.get(0)==2){ 
					b.damage(2, 1); Log.d(TAG_LINE, "267");
					battleDetails="P2 used "+skilName[1]+" Attack\n";
				}else{
					b.damage(0,1); Log.d(TAG_LINE, "272");
					battleDetails="P2 used normal Attack\n";
				}
			*/
				lAttackCodes.remove(0);
		//	}
		
		}catch(Exception e){
			Toast.makeText(this, "AttackCodes size= "+String.valueOf(lAttackCodes.size()), Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public void AI(View arg0){
		skillIndex = new LinkedList<Integer>();
		skillIndex.add(0);
		for(int i=0;i<b.p2Skills.length;i++){
			if(b.p2Skills[i]>0){
				skillIndex.add(i);
			}
		}
		int group;
		if(getIntent().getStringExtra("oppClass").equalsIgnoreCase("Samurai")||getIntent().getStringExtra("oppClass").equalsIgnoreCase("Ninja"))
			group = skillIndex.size()/3;
		else
			group = skillIndex.size()/4;
		int min,max;
		if(lAttackCodes.isEmpty()){
			initForOpp();
			Collections.shuffle(lAttackCodes);
			AI(arg0);
		}else{
			int n = Integer.valueOf(lAttackCodes.get(0).toString());
			lAttackCodes.remove(0);
			if(n!=4){
			min = (n*group)-group;
			max = min + group-1;
			int ran =min+(int)(Math.random()*(max-min));
			t = String.valueOf(n)+
					"\nSkillIndexSize= "+skillIndex.size()+
					"\nMin= "+min+"\nMax"+max+"\n real t value= "
			+String.valueOf(ran) ;
			try{
			b.damage(ran, 1);
			}catch(Exception e){
				AI(arg0);
			}
			}else if(n==4){
				b.damage(13, 1);
			}
		}
		}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		//Will Player able to attack or not
		
		if(Integer.valueOf(p1SuccStrike.get(0).toString())==0){
			appendTextAndScroll("P1 Attack Missed");
	//	}else if(arg0.getId()==bSkill3.getId() ){
		//	b.damage(3, 0);
			//refreshScreen();
	//	}else if( arg0.getId()==bSkill15.getId()){
		//	b.damage(15, 0);
		//	refreshScreen();
		//}else if( arg0.getId()==0){
		//	b.damage(1,0);
	//	}else if( arg0.getId()==1){
	//		b.damage(2,0);
		}else if( arg0.getId()==R.id.bAttack){
			b.damage(0,0);
		}else if( arg0.getId()==bSkill2.getId()){
			b.damage(1,0);
		}else if( arg0.getId()==bSkill3.getId()){
			b.damage(2,0);
		}else if( arg0.getId()==bSkill4.getId()){
			b.damage(3,0);
		}else if( arg0.getId()==bSkill5.getId()){
			b.damage(4,0);
		}else if( arg0.getId()==bSkill6.getId()){
			b.damage(5,0);
		}else if( arg0.getId()==bSkill7.getId()){
			b.damage(6,0);
		}else if( arg0.getId()== bSkill8.getId()){
			b.damage(7,0);
		}else if( arg0.getId()==bSkill9.getId()){
			b.damage(8,0);
		}else if( arg0.getId()==bSkill10.getId()){
			b.damage(9,0);
		}else if( arg0.getId()==bSkill11.getId()){
			b.damage(10,0);
		}else if( arg0.getId()==bSkill12.getId()){
			b.damage(11,0);
		}else if( arg0.getId()==bSkill13.getId()){
			b.damage(12,0);
		}else if( arg0.getId()==bSkill14.getId()){
			b.damage(13,0);
		}else if( arg0.getId()==bSkill15.getId()){
			b.damage(14,0);
		}else if(arg0.getId()==ivLPot.getId()){
			if(sa.poInv[0]>0){
			b.p1hp += 80;
			if(b.p1hp>sa.maxHp)
				b.p1hp=(int) sa.maxHp;
			sa.poInv[0]-=1;
			tvLPQty.setText("x"+sa.poInv[0]);
			}else{
				Toast.makeText(getApplicationContext(), "Out of Mana Potion", Toast.LENGTH_SHORT).show();
			}
		}else if(arg0.getId()==ivMPot.getId()){	
			if(sa.poInv[1]>0){
				b.p1mana += 80;
				if(b.p1mana>sa.maxMana)
					b.p1mana=(int)sa.maxMana;
				sa.poInv[1]-=1;
				tvMPQty.setText("x"+sa.poInv[1]);
			}else{
				Toast.makeText(getApplicationContext(), "Out of Mana Potion", Toast.LENGTH_SHORT).show();
			}
		}
	//	refreshScreen();
		
		if(b.p2hp<0){
			b.p2hp=0;
		}
		if(hpCheck(b.p2hp)){
			appendTextAndScroll(p2name+" has been defeted");
			
			if(sa.lvl==Integer.valueOf(getIntent().getStringExtra("oppLevel"))){
				//sa.remSkilPts+=1;
				@SuppressWarnings("rawtypes")
				Class ourClass;
				try {
					 ourClass = Class.forName("com.darshan.warriorgame.AttsUp");
					 sa.lvl+=1;
					 sa.remSkilPts+=1;
					 sa.remAttPts+=1;
					 int gold = getIntent().getIntExtra("oppGold", 0); 
					 if(gold==0)
						 gold = Integer.valueOf(getIntent().getStringExtra("oppGold"));
					 sa.gold+= gold;
					 Intent in = new Intent(BattleArena.this,ourClass);
					 startActivity(in);
					 finish();
					 
					 
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				 int gold = getIntent().getIntExtra("oppGold", 0); 
				 if(gold==0)
					 gold = Integer.valueOf(getIntent().getStringExtra("oppGold"));
				 sa.gold+= (gold/2);
				finish();
			}
			attack.setClickable(false);
		//}
		}else{
		//machinePlayer Attack
			if(Integer.valueOf(p2SuccStrike.get(0).toString())==0){
				appendTextAndScroll("P2 Attack Missed"); Log.d(TAG_LINE, "424");
			}else{
		//AI(arg0);
				AI2(); Log.d(TAG_LINE, "427");
			}
		}
		p1SuccStrike.remove(0);
		p2SuccStrike.remove(0);
		
		refreshScreen();
	}
	
	public void refreshScreen(){
		pbPHp.setProgress((int)((b.p1hp*100)/b.maxp1hp));
		pbCHp.setProgress(((b.p2hp*100/b.maxp2hp)));
		
		tvPLevel.setText(String.valueOf(sa.lvl)+ "\t" + b.p1hp+"/"+b.maxp1hp+"\t   "+ b.p1mana+"/"+b.maxp1mana);
		tvCpLevel.setText(getIntent().getStringExtra("oppLevel")+"\t"+b.p2hp+"/"+b.maxp2hp+"\t   "+b.p2mana+"/"+b.maxp2mana);//comPl.lvl));
		
		String s = ("\nPlayer 1 damaged Player 2 by "+String.valueOf(b.p1damage)
				+"\nP2 new HP = "+b.p2hp+"\ndamage = "+b.p1damage+"\nAttack name = "+skilName[Integer.valueOf(0)]);
		appendTextAndScroll(s);
	}
	
	//ph_dam,mag_dam,ph_def,mag_def,e_s_dam,s_hp,b_mana,speed
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
	
	public Integer[] setOppMinAtts(){
		Integer[] ItemAtts = new Integer[]{0,0,0,0,0,0,0,0};
		for(int i=0;i<4;i++){
			String[] itmAtts=sa.getAllItms(String.valueOf(sa.oppInv[i]));
			if(itmAtts!=null){
			for(int j=3;j<itmAtts.length-4;j++)
				ItemAtts[j-3]=ItemAtts[j-3]+Integer.valueOf(itmAtts[j]);
			}
		}
		return ItemAtts;
	}
	
	public void initForOpp(){
		lAttackCodes = new LinkedList<Integer>();
		String CpClass =getIntent().getStringExtra("oppClass");
		
		if(CpClass.equalsIgnoreCase("Samurai")){
			for(int i=0;i<7;i++)
				lAttackCodes.add(1);
			for(int i=0;i<2;i++)
				lAttackCodes.add(2);
			lAttackCodes.add(3);
		}else if(CpClass.equalsIgnoreCase("Ninja")){
			for(int i=0;i<7;i++)
				lAttackCodes.add(3);
			for(int i=0;i<2;i++)
				lAttackCodes.add(1);
			lAttackCodes.add(2);
		}else if(CpClass.equalsIgnoreCase("Wizard")){
			for(int i=0;i<5;i++)
				lAttackCodes.add(1);
			for(int i=0;i<2;i++){
				lAttackCodes.add(4);
				lAttackCodes.add(2);
			}
			lAttackCodes.add(3);
		}else if(CpClass.equalsIgnoreCase("Balanced")){
			for(int i=0;i<5;i++)
				lAttackCodes.add(3);
			for(int i=0;i<2;i++){
				lAttackCodes.add(4);
				lAttackCodes.add(1);
			}
			lAttackCodes.add(2);
		}
		
		
	}
	
	
	public void initSuccStrike(){
		p1SuccStrike = new LinkedList<Integer>();
		p2SuccStrike = new LinkedList<Integer>();
		double p1List=(b.p1MajAtts[1]*100)/b.p2MajAtts[1];
		double p2List=(b.p2MajAtts[1]*100)/b.p1MajAtts[1];
		for(int i=0;i<p1List;i++)
			p1SuccStrike.add(1);
		if(p1List<100){
			for(int i=0;i<(100-p1List);i++)
				p1SuccStrike.add(0);
		}
		Collections.shuffle(p1SuccStrike);
			
		
		for(int i=0;i<p2List;i++)
			p2SuccStrike.add(1);
		if(p2List<100){
			for(int i=0;i<(100-p2List);i++)
				p2SuccStrike.add(0);
		}
		Collections.shuffle(p2SuccStrike);
		
		
	}
	
	public boolean hpCheck(int hp1){
		if(hp1<=0)
			return true;
		else
			return false;
	}
	
	//-----------------------------------------------
	private void appendTextAndScroll(String text){
		tvFStat.append(text + "\n");
		svFStat.post(new Runnable(){
            public void run(){
            	svFStat.fullScroll(View.FOCUS_DOWN);
            }
        });
	}
	
	@Override
	public void onBackPressed() {
	    sa.hp=(double)b.p1hp;
		sa.mana = b.p1mana;
		finish();
		
	}
	
	
	
	
	
	
	
	//public void calcAtts(Player p){
		//for(int i=0;i<p.eqInv.length;i++){
			
		//}	
	//}
//	
	/*
	private void createButtons() {
	//	rl = (RelativeLayout)findViewById(R.id.rlSkills1);
		int lMargin=dpToPx(10);
		int tMargin=dpToPx(0);
		
		
		for(int j=0,i=0;i<7;i++){
			if(sa.getSkilAcc(skilId[i]).contentEquals("0")){
				continue;	
			}else{
			
				if(i%3==0 && i>0){
					lMargin = 0;
					tMargin = tMargin + dpToPx(50);
				}
			
			//else if(i/3==2)
				//tMargin = dpToPx(40);
			
				Button myButton = new Button(this);
				lp = new LayoutParams(dpToPx(100), LayoutParams.WRAP_CONTENT);
			
			//myButton.setText("Push Me "+i);
				myButton.setText(sa.getSkillName(skilId[i]));
				myButton.setId(j+100);
			
				if(i!=0){
					lp.setMargins(lMargin, tMargin, 0, 0);
				}else{
					lp.setMargins(dpToPx(10), 0, 0, 0);
				}
			rl.addView(myButton, lp);
			myButton.setOnClickListener(this);
			
			lMargin = lMargin+dpToPx(110);
			j++;
			}
		}
	}
*/
	
	
	
	public void oldFxn(){
		/*
		aCode=0;
		
		filename = getIntent().getStringExtra("filename");
		someData = getSharedPreferences(filename,0);
		editor = someData.edit();
		
		CpLevel = someData.getInt("Computer_Level", 0);
		PLevel = someData.getInt(filename+"_Level", 0); 
		CpClass = someData.getString("Computer_Class","");
		PClass = someData.getString(filename+"_Class", "");
		PName = someData.getString("playername", "");
		
		attack = (Button)findViewById(R.id.bAttack);
		rgAttacks = (RadioGroup) findViewById(R.id.rgattacks);
		tvCpClass = (TextView) findViewById(R.id.tvCpClass);
		tvCpLevel = (TextView) findViewById(R.id.tvCpLevel);
		tvPClass = (TextView) findViewById(R.id.tvPClass);
		tvPLevel = (TextView) findViewById(R.id.tvPLevel);
		tvPName = (TextView) findViewById(R.id.tvPName);
		pbCHp = (ProgressBar) findViewById(R.id.pbComHp);
		pbPHp = (ProgressBar) findViewById(R.id.pbPHp);
		
		tvCpClass.setText(someData.getString("Computer_Class", ""));
		tvCpLevel.setText(String.valueOf(CpLevel));
		tvPClass.setText(PClass);
		tvPLevel.setText(String.valueOf(someData.getInt(filename+"_Level", 0)));
		tvPName.setText(PName);
		
		attack.setOnClickListener(this);
		
		b = new Battle(PLevel,CpLevel,CpClass,PClass);
		try{
		maxhps = (Integer[])b.initialize();
		pbPHp.setProgress(100);
		pbCHp.setProgress(100);
		}catch(Exception e){
			System.err.print(e);
		}
		*/
	}
	
	
	
	
}
