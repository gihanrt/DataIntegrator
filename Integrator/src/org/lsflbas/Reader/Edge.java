package org.lsflbas.Reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
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
            
            for (int i = 0; i < 52; i++) {
				//System.out.println(dataset[i][0]+" "+dataset[i][1]+" "+dataset[i][2]);
			}
           System.out.println(countLine);
          
	    }catch(Exception e){
	        e.printStackTrace();                      
	    }	
	}
	
	

	public void EdgeReader(String yearly,String quartely,String writepath) throws IOException{
		
		
		File fileYear=new File(yearly);
		File fileQuarter = new File(quartely);
		File writefile= new File(writepath);
		
		Scanner sc = new Scanner(fileYear);
		Scanner sc2 =new Scanner(fileQuarter);
	
		BufferedWriter output = new BufferedWriter(new FileWriter(writefile));
		
        output.write("\"source\",\"target\",\"type\"\n");
		
		Vector<String> v1=new Vector<String>();
		//Vector<String> v2=new Vector<String>();

        while(sc.hasNextLine()){        	
            String line = sc.nextLine();                 
            v1.add(line);
        }
        
        Vector<String> v2=new Vector<String>();
        Vector<String> v3=new Vector<String>();
        Vector<String> v4=new Vector<String>();
       

                
        for (int i = 0; i < v1.size(); i++) {
			String[] data=v1.get(i).split(",");
			
			//System.out.println(data[1]+" "+data[2]+ " "+ data[3]);
			if(data[3].equals("\"TRUE\"") || data[3].equals("\"TRUE/FALSE\"")){
				v2.add(data[1]);
				v3.add(data[2]);
				v4.add("\"TRUE\"");				
			}
			
		}
        
        String[][] yearlyArry=new String[v2.size()][3];
        
        for (int i = 0; i < v2.size(); i++) {
			yearlyArry[i][0] = v2.get(i);
			yearlyArry[i][1] = v3.get(i);
			yearlyArry[i][2] = v4.get(i);
			
		}
        
        
        String[][] QuartelyArray=new String[v2.size()][6];
        
        while(sc2.hasNextLine()){
        	String line=sc2.nextLine();
        	String[] spliter=line.split(",");
        	//System.out.println(spliter[0]+" "+spliter[1]+" "+spliter[2]+" "+spliter[3]+" "+spliter[4]+" "+spliter[5]+" "+spliter[6]);
        	for (int i = 0; i < v2.size(); i++) {
				if(spliter[1].equals(yearlyArry[i][0]) && spliter[2].equals(yearlyArry[i][1])){
					QuartelyArray[i][0] = spliter[1];
					QuartelyArray[i][1] = spliter[2];
					QuartelyArray[i][2] = spliter[3];
					QuartelyArray[i][3] = spliter[4];
					QuartelyArray[i][4] = spliter[5];
					QuartelyArray[i][5] = spliter[6];
				}
			}
        }
        
        /*for (int i = 0; i < v2.size(); i++) {
			System.out.println(yearlyArry[i][0]+" "+yearlyArry[i][1]+ " ----"+QuartelyArray[i][0]+" "+QuartelyArray[i][1]);
		}*/
        
        System.out.println("size"+QuartelyArray.length);
        
        sc.close();
        sc2.close();
        
        int nuldata=0,unnul=0;
        System.out.println("going to start.....");
        
        for (int i = 0; i < QuartelyArray.length; i++) {
			
        		//yearlyArry[0][0] = "bond.21" yearlyArry[0][1] = "bond.45" yearlyArry[0][0]= "TRUE"
        	
        	System.out.println(yearlyArry[i][0]+" "+yearlyArry[i][1]+ " ----"+QuartelyArray[i][0]+" "+QuartelyArray[i][1]);
        	
        	String[] subOfyearly1=yearlyArry[i][0].split("[.]");  // "bond , 21"
			String[] subOfyearly2=yearlyArry[i][1].split("[.]");  // "bond , 45"
        	
        	String x="\""+subOfyearly1[1]; 	// "21"
        	String x1="\""+subOfyearly2[1]; // "12"
        	
        	String y=findCorrect(x);
        	String y1=findCorrect(x1);
        	
        	String p=findWord(subOfyearly1[0]);
        	String p1=findWord(subOfyearly2[0]);
        	
        	String z=findId(y,p);
        	String z1=findId(y1,p1);
        	
			if(QuartelyArray[i][0] != null && QuartelyArray[i][0] != null){
				
				
				
				//String[] subOfQuartely1=QuartelyArray[i][0].split("[.]");
				//String[] subOfQuartely2=QuartelyArray[i][1].split("[.]");
		
            	
            	System.out.println("original  " + yearlyArry[i][0]+" "+ yearlyArry[i][1]+ " "+ yearlyArry[i][2]);
            	System.out.println("location      -> "+x+" "+p+" "+y+" "+z+" "+x1+" "+" "+p1+" "+y1+" "+z1);  
            	
            	int countTrue=0,countSustain=0,countFalse=0;
            	for (int j = 2; j < 6; j++) {
            		
					if (QuartelyArray[i][j].equals("\"TRUE\"")) {
						if(j < 5 && QuartelyArray[i][j+1].equals("\"TRUE\"")){
							countSustain++;
						}else{
							countTrue++;
						}
					}else if(QuartelyArray[i][j].equals("\"FALSE\"")){
						countFalse++;
					}
				}
            	
            	System.out.println("episodic"+countTrue+" sustained "+countSustain+" weak"+countFalse);
            	
            	if(countTrue > 0 && countSustain > 0){
            		//goto sustain
            		//output.write(yearlyArry[i][0]+","+yearlyArry[i][1]+","+"sustained"+"\n");
            		output.write(z+","+z1+","+"sustained"+"\n");
            	}else if(countTrue > 0 && countSustain == 0){
            		//goto episodic
            		output.write(z+","+z1+","+"episodic"+"\n");
            	}else if(countFalse == 4){
            		//goto weak
            		output.write(z+","+z1+","+"weak"+"\n");
            	}
            	
            	//output.write(z+","+z1+"\n");
            	//output.write(yearlyArry[i][0]+","+yearlyArry[i][1]+"\n");
            	
				
				unnul++;
			}else{				
				//System.out.println("null are "+ yearlyArry[i][0]+ " "+ yearlyArry[i][1]);
				output.write(z+","+z1+","+"weak"+"\n");
				nuldata++;
			}
		}
        
        System.out.println("nuldata"+nuldata+"  unnul data"+unnul);
        output.close();
    
		/*try{
			
			//File file = new File(writerPath);
	        BufferedWriter output = new BufferedWriter(new FileWriter(file));
	        output.write("\"source\",\"target\"\n");
	              	
	    	File f = new File(path);
            Scanner sc = new Scanner(f);
            int countLine=0;

            */
            
           /* 
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                //System.out.println(line);                
                String[] arr = line.split(",");    // "4" "bond.21" "bond.12" "TRUE"                                          
                if(arr.length == 4 && arr[3].equals("TRUE")){
                	
                	//System.out.println("original line -> "+line);
                	
                	String[] sub=arr[1].split("[.]");    // "bond 21"
                	String[] sub2=arr[2].split("[.]");   // "bond 12"
                		
                	String x="\""+sub[1]; 	// "21"
                	String x1="\""+sub2[1]; // "12"
                	
                	String y=findCorrect(x);
                	String y1=findCorrect(x1);
                	
                	String p=findWord(sub[0]); "bond
                	String p1=findWord(sub2[0]);  "bond
                	
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
		}		*/
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