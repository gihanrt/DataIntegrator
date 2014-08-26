package org.lsflbas.Reader;
public class Reader {
	
	public static void main(String[] args) {
		final long sTime = System.currentTimeMillis();
		 	String filepath="C://Users/Hunter/Desktop/R-d3/Node.csv";
		 	String path="C://Users/Hunter/Desktop/R-d3/MyData.csv";
		 		 	
		 	Edge e=new Edge();
		 	e.Nodes(filepath);
		 	e.EdgeReader(path);
		 	final long eTime = System.currentTimeMillis();
		 	System.out.println(eTime-sTime);
	}	

}
