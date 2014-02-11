package com.darshan.warriorgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PotionStore extends Activity implements OnClickListener,OnItemClickListener{

	TextView tvLPQty,tvMPQty,tvGold,tvDet;
	ListView lvPotions;
	Button bBuy;
	String[] potionId= new String[]{"501","502"};
	String[] details ={"Item Id","Item","Str","Physical damage","Magical damage","Physical defense","Magical defense","Extra Shield Damage","Shield HP","Bonus Mana","Speed","Cost","Selling Price","HP Plus","Mana Plus"};
	SharingAtts sa;
	ItemTest it;
	DBManager db;
	Toast t;
	
	int costOfItm;
	int selectedItm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.potionstore);
		
		it = new ItemTest();
		sa =((SharingAtts)getApplication());
		selectedItm=0;
		costOfItm=0;
		
		tvDet=(TextView)findViewById(R.id.tvDetail4);
		tvGold = (TextView)findViewById(R.id.tvGold2);
		tvLPQty = (TextView)findViewById(R.id.tvLPQty2);
		tvMPQty = (TextView)findViewById(R.id.tvMPQty2);
		lvPotions = (ListView)findViewById(R.id.lvPotions);
		bBuy= (Button)findViewById(R.id.bBuy4);
		
		tvLPQty.setText("x"+sa.poInv[0]);
		tvMPQty.setText("x"+sa.poInv[1]);
		
		String g="";
		for(int i=0;i<potionId.length;i++){
			String[] m =sa.allItms.get(potionId[i]);
			g=g+m[1]+" ";
		}
		
		String[] pots = g.split(" ");
		for(int i=0;i<pots.length;i++){
			pots[i] = pots[i].replace("_", " ");
		}
		
		lvPotions.setAdapter(new PotionArrayAdapter(this, pots));
		lvPotions.setOnItemClickListener(this);
		bBuy.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		arg2=arg2+501;
		selectedItm=arg2;
		String[] h=sa.getAllItms(String.valueOf(arg2));
		String detail="";
		
		for(int i=2;i<h.length;i++){
			if(!h[i].contentEquals("0"))
				detail = detail +details[i]+"   "+h[i]+"\n";
			if(details[i].contentEquals("Cost"))
				costOfItm = Integer.valueOf(h[i]);
		}
		tvDet.setText(detail);
		//Toast.makeText(getApplicationContext(), "selectedItm= "+arg2, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId()==R.id.bBuy4){	
			if(selectedItm!=0){
				if(costOfItm>sa.gold){
					t= Toast.makeText(getApplicationContext(), "Insuffecient Gold    "+costOfItm, Toast.LENGTH_LONG);
					t.show();
				}else{
				
					sa.poInv[selectedItm-501]+=1;
					sa.gold = sa.gold-costOfItm;
					tvGold.setText("Gold "+String.valueOf(sa.gold));
					tvLPQty.setText("x"+sa.poInv[0]);
					tvMPQty.setText("x"+sa.poInv[1]);
					t= Toast.makeText(getApplicationContext(), "Item purchased successfully", Toast.LENGTH_LONG);
					t.show();
					
				}
			
				//sa.setPlaInv(inv);
				
			
				
		//}
		}else{
			t= Toast.makeText(getApplicationContext(), "Item not selected", Toast.LENGTH_LONG);
			t.show();
		}
			
	}
}
		//	break;
}
		
	
	//}


