package dyt4;

import java.util.Vector;
//无向无权图
public class Graph {

	private int n;  //点数
	private int m;  //边数
	
	Vector<Integer> []vect;
	
	public Graph(int n,int m)
	{
		this.n=n;
		this.m=m;
		vect=new Vector[n];
	}
	public int V()
	{
		return n;
	}
	public int E()
	{
		return m;
	}
	
	public void addEdge(int v,int w)
	{
		assert v>=0&&v<n;
		assert w>=0&&w<n;
		
		vect[v].add(w);
		vect[w].add(v);
	}
	
	public boolean hasEdge(int v,int w)
	{
		assert v>=0&&v<n;
		assert w>=0&&w<n;
		
		for(int i=0;i<vect[v].size();i++)
		{
			if(vect[v].elementAt(i)==w)
				return true;
		}
		return false;
	}
	
	public Iterable<Integer> adj(int v)
	{
		assert v>=0&&v<n;
		return vect[v];
	}
	
	
	
	
}
