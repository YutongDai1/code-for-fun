package dyt4;

//双连通分支个数
public class biconnectedcomponents {

	Graph g;
	private int count=0;
	private int res=0;
	private boolean []visited;
	private  int []num;
	private int []low;
	private int []parent;
	public biconnectedcomponents(Graph g)
	{
		this.g=g;
		visited=new boolean [g.V()];
		num=new int[g.V()];
		low=new int[g.V()];
		parent=new int[g.V()];
		for(int i=0;i<g.V();i++)
		{
			parent[i]=i;
			visited[i]=false;
			num[i]=Integer.MAX_VALUE;
			low[i]=Integer.MAX_VALUE;
		}
	}
	private void getbiconnectedcomponents(int v)  //传入一个点，作为dfs的起点
	{
		visited[v]=true;
		num[v]=low[v]=count++;
		for(int w:g.adj(v))
		{
			if(!visited[w])
			{
				parent[w]=v;
				getbiconnectedcomponents(w);
				if(low[w]==num[v])   
					res++;
				low[v]=Math.min(low[v], low[w]);
			}
			else
			{
				if(parent[v]!=w)
				{
					low[v]=Math.min(low[v], num[w]);
				}
				
				
			}
		}
	}
	
	public int getresult(int v)       //返回最终值 ，调用私有函数  （传入一个点，作为dfs的起点）
	{
		getbiconnectedcomponents( v);
		return res+1;
	}
}