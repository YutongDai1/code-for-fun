package dyt4;

public class UnionFind {

	private int []rank;
	private int []parent;
	private int count;
	
	public UnionFind(int count)
	{
		this.count=count;
		parent=new int[count];
		rank=new int[count];
		for(int i=0;i<count;i++)
		{
			parent[i]=i;
			rank[i]=1;
		}
	}
	
	public boolean isconnected(int p,int q)
	{
		return parent[p]==parent[q];
	}
	
	public void union(int p,int q)
	{
		int proot=find(p);
		int qroot=find(q);
		
		if(rank[proot]<rank[qroot])
			parent[proot]=qroot;
		else if(rank[proot]>rank[qroot])
			parent[qroot]=proot;
		else
		{
			parent[proot]=qroot;
			rank[qroot]++;
		}
		
	}
	
	public int find(int p)
	{
		int ret=p;
		while(ret!=parent[ret])
		{
			parent[ret]=parent[parent[ret]];
			ret=parent[ret];
		}
		return ret;
	}
}
