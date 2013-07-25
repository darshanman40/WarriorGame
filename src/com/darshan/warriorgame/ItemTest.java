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
	    String nonNull;
	    String[] rows = new String [750];
	    //String [] rowData = new String[7500];
	    int i=0;
	    StringBuilder total = new StringBuilder();
	    //line = reader.readLine();
	    while ((line = reader.readLine()) != null){
	    	String[] rowData = line.split(",");
	    	for(int j=0;j<rowData.length;j++)
	    		total.append(rowData[j]+" ");
	    		//nonNull = rowData[j].replace("null", "");
	    	total.append("\n");
	    }
	    	return total.toString().split("\n");
	    	//return rowData;
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	//rowData[0]=sd;
	    	//int j;
	    	/*
	    	for(j=0;j<rowData.length;j++){
	    			if(rows[i]!=null){
	    				if(rowData[j]!=null)
	    				rows[i] = rows[i]+rowData[j]+" ";
	    			}else
	    				rows[i] = rowData[j]+" ";
	    		}
	    	rows[i]=rows[i]+"\n";
	    	//if(rowData[j-1]!=null)
	    		i++;
	    	}
	    String[] nonNull = new String[i];
	    i=0;
	    while(rows[i]!=null){
	    	nonNull[i]=rows[i];
	    	i++;
	    }
	    */
	    	

	  //  }catch (FileNotFoundException e){
	// TODO Auto-generated catch block
	   // 	e.printStackTrace();
	   // } catch (IOException e) {
	// TODO Auto-generated catch block
	    //	e.printStackTrace();
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	   return null;
		}
	/*
	public String[] readSkill(){
		try{
		InputStream is = this.getClass().getResourceAsStream("/assets/skills.csv");
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    	String line;
	    	String rows[] = new String [750];
	    	String [] rowData = new String[7500];
	    	int i=0;
	    	while ((line = reader.readLine()) != null){
	    		rowData = line.split(",");
	    		int j;
	    		for(j=0;j<rowData.length;j++){
	    			if(rows[i]!=null)
	    				rows[i] = rows[i]+rowData[j]+" ";
	    			else
	    				rows[i] = rowData[j]+" ";
	    		}
	    		rows[i]=rows[i]+"\n";
	    		if(rowData[j-1]!=null)
	    		i++;
	    	}
	    	
	    	
	    	return rows;

	    }catch (FileNotFoundException e){
	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    } catch (IOException e) {
	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	   return null;
		}
	*/

public String[] getColNames(){
	//String val1[]="";
	String [] rowData = new String[75];
	try{
		InputStream is = this.getClass().getResourceAsStream("/assets/inventory.csv");
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	String line;
    	int i=0;
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
