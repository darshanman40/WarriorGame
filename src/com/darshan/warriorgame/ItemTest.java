package com.darshan.warriorgame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ItemTest {

	public String[] printData(String filename){
		try{
		InputStream is = this.getClass().getResourceAsStream("/assets/"+filename+".csv");
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    String line;
	    StringBuilder total = new StringBuilder();
	    line = reader.readLine();
	    total.append(line+"\n");
	    while ((line = reader.readLine()) != null){
	    	String[] rowData = line.split(",");
	    	for(int j=0;j<rowData.length;j++)
	    		total.append(rowData[j]+" ");
	    	total.append("\n");
	    }
	    	return total.toString().split("\n");
		}catch (Exception e) {
	    	e.printStackTrace();
	    }
	   return null;
	}
	

public String[] getColNames(){
	//String val1[]="";
	String [] rowData = new String[75];
	try{
		InputStream is = this.getClass().getResourceAsStream("/assets/inventory.csv");
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	String line;
    	//int i=0;
    	//while ((line = reader.readLine()) != null ){
    		
    		line = reader.readLine();
    		rowData = line.split(",");
    		
    	return rowData;

    }catch (FileNotFoundException e){
// TODO Auto-generated catch block
    	e.printStackTrace();
    } catch (IOException e) {
// TODO Auto-generated catch block
    	e.printStackTrace();
    }catch (Exception e) {
    	e.printStackTrace();
    }
    //}
	
    return rowData;
	}
	
}	
	
//}
