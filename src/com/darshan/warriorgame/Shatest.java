package com.darshan.warriorgame;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Shatest extends Activity implements OnClickListener {

	TextView tvResult;
	Button bResult;
	EditText etText;
	// String salt =50fdfaf2339d8acd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shatest);
		try{
		tvResult = (TextView) findViewById(R.id.tvShaTest);
		bResult = (Button) findViewById(R.id.bResult3);
		etText = (EditText) findViewById(R.id.etShaTest);
		
		bResult.setOnClickListener(this);
		}catch(Exception e){
			System.err.print(e);
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	if(arg0.getId()==R.id.bResult3){
			String text =etText.getText().toString();
			try {
				text = shaConv(text);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tvResult.setText(text);
		}
		
	}
	
	public String shaConv(String text) throws NoSuchAlgorithmException{
		
		//String password = "50fdfaf2339d8acd";
		 
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
           }
        text = sb.toString();
        //text = md.
        return text;
	}

}
