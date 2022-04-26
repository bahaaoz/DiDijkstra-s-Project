package application;

import java.util.ArrayList;

public class Node implements Comparable<Node>{

	public String name ;
	public boolean known;
	public double dist;
	public Node path;
	public double x;
	public double y;
	ArrayList<adj> list = new ArrayList<>();

	
	
	public Node(String name, boolean known, double dist, Node path, double x, double y)
	{
		this.name = name;
		this.known = known;
		this.dist = dist;
		this.path = path;
		this.x = x;
		this.y = y;
	}
	public Node()
	{
		
	}



	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		if(this.dist > o.dist)
			return 1;
		else if(this.dist < o.dist)
			return -1;
			else
				return 0;
	}
	
	
	
}
