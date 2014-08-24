package org.lsflbas.Reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
	
	/*this can be load from the github*/
	public static void main(String[] args) {

		 	String filepath="C://Users/Hunter/IdeaProjects/d3js/list.csv";
		    List<String> group=new ArrayList<String>();
		    List<String> id=new ArrayList<String>();
		    List<String> name=new ArrayList<String>();
		   
		    try{
		    	
		    	File f = new File(filepath);
	            Scanner sc = new Scanner(f);
	            int countLine=0;

	            while(sc.hasNextLine()){
	                String line = sc.nextLine();
	                //System.out.println(line.length());
	                String[] arr = line.split(",");
	                
	                if(arr.length == 3){
	                	//System.out.println(arr[0]+" "+arr[1]+" "+arr[2]);
	                	group.add(arr[0]);
	                	id.add(arr[1]);
	                	//System.out.println(arr[2]);
	                	name.add(arr[2]);
	                	countLine++;
	                }
	     
	            }
	            
	            sc.close();
	            System.out.println(countLine);
	            
	            for(int i=0;i <name.size();i++){
	            	System.out.println(group.get(i)+" "+id.get(i)+" "+name.get(i));
	            }
	            
	            System.out.println(name.size());

		    }catch(Exception e){
		        e.printStackTrace();                      
		    }	

	}	

}
