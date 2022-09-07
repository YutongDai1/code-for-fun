package dyt4;

//基于数组实现
//Low[] num[] 
//DFS 
public class cutPoint {

	
	Graph g;
	private int count;
	private boolean []visited;
	private int num[];
	private int low[];
	private int parent[];
	
	public cutPoint(Graph g)
	{
		count=0;
		this.g=g;
		visited=new boolean [g.V()];
		num=new int [g.V()];
		low=new int [g.V()];
		parent=new int [g.V()];
		for(int i=0;i<g.V();i++)
		{
			visited[i]=false;
			parent[i]=i;
			low[i]=Integer.MAX_VALUE;
			num[i]=Integer.MAX_VALUE;
		}
	}
	private void findArtwithoutroot(int v)
	{
		
		visited[v]=true;
		num[v]=low[v]=count++;
		for(int w:g.adj(v))
		{
			if(!visited[w])
			{
				parent[w]=v;
				findArtwithoutroot(w);
				if(low[w]>=num[v]&&parent[v]!=v)
					System.out.println(v+"is an articulation point");
				low[v]=Math.min(low[w], low[v]);
				
			}
			else
			{
				if(parent[v]!=w)
				{
					low[v]=Math.min(low[v],num[w]);
				}	
			}
		}
	}
	private void findArtwithroot(int v)
	{
		int index=-1;
		for(int i=0;i<parent.length;i++)
			if(parent[i]==v&&i!=v)
				index++;
		
		if(index!=-1)
			System.out.println(v+"is an articulation point");
				
	}
	public void findArt(int v)
	{
		findArtwithoutroot(v);
		findArtwithroot(v);
	}
}
