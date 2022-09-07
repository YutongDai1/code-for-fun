package dyt4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FordFulkerson
{

	private int value=0;
	private FlowEdge []edgeto;
	public FordFulkerson(Flowgraph g,int s,int t)
	{
		
		while(hasAugmentingpath(g,s,t))
		{
		
			
			int v=t;
			int min=Integer.MAX_VALUE;
			while(v!=s)
			{
				min=Math.min(min, edgeto[v].remain());
				v=edgeto[v].theother(v);
			}

			v=t;
			while(v!=s)
			{
				edgeto[v].addflow(min);
				v=edgeto[v].theother(v);
			}
			value+=min;
		}
		
	}
	public boolean hasAugmentingpath(Flowgraph g,int s, int t)
	{
		boolean []visited=new boolean [g.V()];
		edgeto=new FlowEdge[g.V()];
		visited[s]=true;
		
		Queue<Integer>q=new LinkedList<>();
		q.add(s);
		while(!q.isEmpty())
		{
			int v=q.poll();
			for(FlowEdge e:g.adj(v))
			{
				int w=e.theother(v);
				if(!visited[w]&&e.remain()>0)
				{
					visited[w]=true;
					q.add(w);
					edgeto[w]= e;
				}
				
			}
		}
		return visited[t];
		
		
	}
	public int result()
	{
		return value;
	}

	
}
class FlowEdge
{	
	private int v;
	private int w;
	private int flow;
	private int capacity;
	
	public FlowEdge(int v,int w,int capacity)
	{
		this.capacity=capacity;
		this.v=v;
		this.w=w;
		flow=0;
	}
	public int from()
	{
		return v;
	}
	public int to()
	{
		return w;
	}
	public void addflow(int d)
	{
		flow+=d;
	}
	public int remain()
	{
		return capacity-flow;
	}
	public int theother(int q)
	{
		return q==v?w:v;
	}
	
}
class Flowgraph
{
	private int v;
	private List<FlowEdge> []adj;
	
	public Flowgraph(int v)
	{
		this.v=v;
		adj=new List[v];
		for(int i=0;i<v;i++)
			adj[i]=new LinkedList<FlowEdge>();
	}
	
	public Iterable<FlowEdge>adj(int p)
	{
		return adj[p];
	}
	
	public int V()
	{
		return v;
	}
	
	
	
	
	
}
