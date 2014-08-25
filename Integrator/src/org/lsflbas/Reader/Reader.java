package org.lsflbas.Reader;
public class Reader {
	
	public static void main(String[] args) {
		final long sTime = System.currentTimeMillis();
		 	String filepath="C://Users/Hunter/IdeaProjects/d3js/list.csv";
		 	String path="C://Users/Hunter/IdeaProjects/d3js/edges.txt";
		 		 	
		 	Edge e=new Edge();
		 	e.Nodes(filepath);
		 	e.EdgeReader(path);
		 	final long eTime = System.currentTimeMillis();
		 	System.out.println(eTime-sTime);
	}	

}
