package org.lsflbas.Reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
/*nodes csv reader method*/

public class Edge {

	public String[][] dataset=new String[52][3];
	String id;
	
	public void Nodes(String path){
		   
	    try{
	    	
	    	File f = new File(path);
            Scanner sc = new Scanner(f);
            int countLine=0;

            while(sc.hasNextLine()){
            	
                String line = sc.nextLine();               
                String[] arr = line.split(",");
                
                if(arr.length == 3){
                	dataset[countLine][0]=arr[0];//group
                	dataset[countLine][1]=arr[1];//id
                	dataset[countLine][2]=arr[2];//name               
                	countLine++;
                }
     
            }
            
            sc.close();
           //System.out.println(countLine);
          
	    }catch(Exception e){
	        e.printStackTrace();                      
	    }	
	}
	
	

	public void EdgeReader(String path,String writerPath){
			
		try{
			
			File file = new File(writerPath);
	        BufferedWriter output = new BufferedWriter(new FileWriter(file));
	        output.write("\"source\",\"target\"\n");
	              	
	    	File f = new File(path);
            Scanner sc = new Scanner(f);
            int countLine=0;

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                //System.out.println(line);
                
                String[] arr = line.split(",");
                
                                
                if(arr.length == 4 && arr[3].equals("TRUE")){
                	
                	//System.out.println("original line -> "+line);
                	
                	String[] sub=arr[1].split("[.]");
                	String[] sub2=arr[2].split("[.]");
                		
                	String x="\""+sub[1];
                	String x1="\""+sub2[1];
                	
                	String y=findCorrect(x);
                	String y1=findCorrect(x1);
                	
                	String p=findWord(sub[0]);
                	String p1=findWord(sub2[0]);
                	
                	String z=findId(y,p);
                	String z1=findId(y1,p1);
                	
                	//System.out.println("location      -> "+x+" "+p+" "+y+" "+z+" "+x1+" "+" "+p1+" "+y1+" "+z1);
                	
                	System.out.println(z+","+z1);
                	output.write(z+","+z1+"\n");
                	countLine++;
                }
     
            }
            
            sc.close();
            output.close();
            System.out.println("number of counted line : "+countLine);
                       
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public String findId(String check,String group){
		String founder = null;

			for (int i = 0; i < dataset.length; i++) {
				
		 		if( dataset[i][1].equals(check) && group.equals("bond") && "1".equals(dataset[i][0])){
		 			//bond 
		 			founder =Integer.toString(i);
		 			break;
		 		}else if(dataset[i][1].equals(check) && group.equals("equity") && "2".equals(dataset[i][0])){
		 			//equity
		 			founder= Integer.toString(i);
		 			break;
		 		}
				
			}
	
		return founder;

	}
	
 
	public String findCorrect(String charsequences){
		
		char[] set=new char[2];
		
		set[0]=charsequences.charAt(1);
		set[1]=charsequences.charAt(2);
		
		String text=String.copyValueOf(set);
		return  text;
	}
	
	public String findWord(String word){
		char[] sett=new char[word.length()-1];
		
		for (int i = 1; i < word.length(); i++) {
			sett[i-1]=word.charAt(i);
		}
		
		String text=String.copyValueOf(sett);
		return text;
	}

}