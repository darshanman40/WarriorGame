package com.darshan.warriorgame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

public class CheatCompiler extends AlertDialog{

	AlertDialog.Builder alert;
	SharingAtts sa;
	protected CheatCompiler(final Context context) {
		super(context);
		sa = ((SharingAtts)context.getApplicationContext());
		 alert = new AlertDialog.Builder(context);
		alert.setTitle("Title");  
        alert.setMessage("Message");  

        // Set an EditText view to get user input   
        final EditText inputName = new EditText(context);  
        alert.setView(inputName);
        alert.setPositiveButton("Set Route Name", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 String cCode = inputName.getText().toString();
				 String rply = sa.processCheat(cCode);
				 Toast.makeText(context, rply+"cheat= "+cCode, Toast.LENGTH_SHORT).show();
			}

		});
        
       
		//alert.show();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void show(){
		alert.show();
	}

}
