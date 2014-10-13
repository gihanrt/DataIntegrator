package org.lsflbas.Reader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Reader {
	
	public static void main(String[] args) throws IOException {
		
		 	String filepath="C://Users/lsf-admin/Desktop/R-Code/node.csv";
		 	
		 	String year="2013";
		 	
		 	String yearly="C://Users/lsf-admin/Desktop/R-Code/Yealy Granger Causality/edges/edge."+year+".csv";
		 	String quartely="C://Users/lsf-admin/Desktop/R-Code/Quately Granger Causality/Qedges/Q."+year+".csv";
		 	String writepath="C://Users/lsf-admin/Desktop/R-Code/jprg/edgeset."+year+".csv";
		 			
		 	Edge e=new Edge();
		 	e.Nodes(filepath);
		 	e.EdgeReader(yearly,quartely,writepath);
		 	//System.out.println(eTime-sTime);
	}	

}
