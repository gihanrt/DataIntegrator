package org.lsflbas.Reader;
public class Reader {
	
	public static void main(String[] args) {
		final long sTime = System.currentTimeMillis();
		 	String filepath="C://Users/Hunter/Desktop/R-d3/Node.csv";
		 	String path="C://Users/Hunter/Desktop/R-d3/OriginalEdges/edges-2013.csv";
		 	String writerpath="C://Users/Hunter/Desktop/R-d3/jprg/edgeset-2013.csv";
		 		 	
		 	Edge e=new Edge();
		 	e.Nodes(filepath);
		 	e.EdgeReader(path,writerpath);
		 	final long eTime = System.currentTimeMillis();
		 	System.out.println(eTime-sTime);
	}	

}
