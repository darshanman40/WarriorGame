package com.darshan.warriorgame;


import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
import android.os.Bundle;
//import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
//import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
//import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class BattleArena extends Activity implements OnClickListener{
	
	String t;
	String[] skilName = new String[]{"stab","double_strike","speed_strike","speed_strike","vertical_strike","shadow_blend","inner_strength","avenger","split","execution","shurikens","energy_shot","blast_fire","energy_field","charge","mana_bomb","heal","shadow_strike","annihilate","shadow_replicate"};
	ProgressBar pbCHp,pbPHp;
	RadioGroup rgAttacks;
	TextView tvCpClass,tvCpLevel,tvPClass,tvPLevel, tvPName, tvFStat, tvCpName ;
	Button attack,bSkill2,bSkill1,bSkill3,bSkill4,bSkill5,bSkill6,bSkill7
	,bSkill8,bSkill9,bSkill10,bSkill11,bSkill12,bSkill13,
	bSkill14,bSkill15,bSkill16;
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
		bSkill1 = (Button)findViewById(R.id.bSkill1);
		bSkill2 = (Button)findViewById(R.id.bSkill2);
		bSkill3 = (Button)findViewById(R.id.bSkill3);
		bSkill4 = (Button)findViewById(R.id.bSkill4);
		bSkill5 = (Button)findViewById(R.id.bSkill5);
		bSkill6 = (Button)findViewById(R.id.bSkill6);
		bSkill7 = (Button)findViewById(R.id.bSkill7);
		bSkill8 = (Button)findViewById(R.id.bSkill8);
		bSkill9 = (Button)findViewById(R.id.bSkill9);
		bSkill10 = (Button)findViewById(R.id.bSkill10);
		bSkill11 = (Button)findViewById(R.id.bSkill11);
		bSkill12 = (Button)findViewById(R.id.bSkill12);
		bSkill13 = (Button)findViewById(R.id.bSkill13);
		bSkill14 = (Button)findViewById(R.id.bSkill14);
		bSkill15 = (Button)findViewById(R.id.bSkill15);
		bSkill16 = (Button)findViewById(R.id.bSkill16);
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
		
		
		tvCpName.setText(p2name);
		//*/
		attack.setOnClickListener(this);
		bSkill1.setOnClickListener(this);
		bSkill2.setOnClickListener(this);
		bSkill3.setOnClickListener(this);
		bSkill4.setOnClickListener(this);
		bSkill5.setOnClickListener(this);
		bSkill6.setOnClickListener(this);
		bSkill7.setOnClickListener(this);
		bSkill8.setOnClickListener(this);
		bSkill9.setOnClickListener(this);
		bSkill10.setOnClickListener(this);
		bSkill11.setOnClickListener(this);
		bSkill12.setOnClickListener(this);
		bSkill13.setOnClickListener(this);
		bSkill14.setOnClickListener(this);
		bSkill15.setOnClickListener(this);
		bSkill16.setOnClickListener(this);
		hastablesFor();
		sa.updateStat();
		
		tvPLevel.setText(String.valueOf(sa.lvl)+ "\t" + b.p1hp+"/"+b.maxp1hp+"\t"+ b.p1mana+"/"+b.maxp1mana);
		tvCpLevel.setText(getIntent().getStringExtra("oppLevel")+"\t"+b.p2hp+"/"+b.maxp2hp+"\t"+b.p2mana+"/"+b.maxp2mana);//comPl.lvl));
		
		createButtons();
		
		
		
		
		
	}

	public int dpToPx(int dp) {
	    DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
	    int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));       
	    return px;
	}
	
	private void createButtons() {
		rl = (RelativeLayout)findViewById(R.id.rlSkills1);
		for(int i=0;i<5;i++){
			
			Button myButton = new Button(this);
			lp = new LayoutParams(dpToPx(100), LayoutParams.WRAP_CONTENT);
			myButton.setText("Push Me "+i);
			myButton.setId(i);
			if(i!=0){
				Button leftButton = new Button(this);
				leftButton.setId(i-1);
				//myButton.setPadding(leftButton.getPaddingLeft()+dpToPx(10), 0, 0, 0);
			lp.setMargins(lp.leftMargin+leftButton.getWidth()+dpToPx(10), 0, 0, 0);
			}else{
				//myButton.setPadding(dpToPx(10), 0, 0, 0);
				//lp.setMargins(100*(i+1), 0, 0, 0);
			}
			rl.addView(myButton, lp);
			myButton.setOnClickListener(this);
		}
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
		}else if(arg0.getId()==R.id.bSkill3 ){
			b.damage(3, 0);
			refreshScreen();
		}else if( arg0.getId()==R.id.bSkill15){
			b.damage(15, 0);
			refreshScreen();
		}else{
		
		switch(arg0.getId()){
		
		case 0:
			b.damage(1,0);
			break;
		
		case 1:
			b.damage(2,0);
			break;
			
		case R.id.bAttack:
			b.damage(0,0);
			break;
		
		case R.id.bSkill1:
			b.damage(1,0);
			break;
		
		case R.id.bSkill2:
			b.damage(2,0);
			break;
		
		case R.id.bSkill3:
			b.damage(3,0);
			break;
		
		case R.id.bSkill4:
			b.damage(4,0);
			break;
		
		case R.id.bSkill5:
			b.damage(5,0);
			break;
		
		case R.id.bSkill6:
			b.damage(6,0);
			break;
		
		case R.id.bSkill7:
			b.damage(7,0);
			break;
		
		case R.id.bSkill8:
			b.damage(8,0);
			break;
		
		case R.id.bSkill9:
			b.damage(9,0);
			break;
		
		case R.id.bSkill10:
			b.damage(10,0);
			break;
		
		case R.id.bSkill11:
			b.damage(11,0);
			break;
		
		case R.id.bSkill12:
			b.damage(12,0);
			break;
		
		case R.id.bSkill13:
			b.damage(13,0);
			break;
		
		case R.id.bSkill14:
			b.damage(14,0);
			break;
		
		case R.id.bSkill15:
			b.damage(15,0);
			break;
			
		}
	}
		if(b.p2hp<0){
			b.p2hp=0;
		}
		if(hpCheck(b.p2hp)){
			appendTextAndScroll(p2name+" has been defeted");
			attack.setClickable(false);
		}
		//machinePlayer Attack
		if(Integer.valueOf(p2SuccStrike.get(0).toString())==0){
			appendTextAndScroll("P2 Attack Missed");
		}else{
		AI(arg0);
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
				+"\nP2 new HP = "+b.p2hp+"\ndamage = "+b.p1damage+"\nAttack code = "+t);
		appendTextAndScroll(s);
	}
	
	//ph_dam,mag_dam,ph_def,mag_def,e_s_dam,s_hp,b_mana,speed
	public Integer[] setPlaMinAtts(){
		Integer[] ItemAtts = new Integer[]{1,1,10,10,0,0,0,0};
		for(int i=0;i<4;i++){
			String[] itmAtts=sa.getAllItms(String.valueOf(sa.eqInv[i]));
			if(itmAtts[i]!=null){
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
			if(itmAtts[i]!=null){
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
