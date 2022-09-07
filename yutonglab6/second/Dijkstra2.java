package dyt4;

//使用最小堆
public class Dijkstra2 {

	int s;//定义起始点
	weightedgraph<Integer> g;
	
	private boolean []marked;
	private int  []dist;
	public Dijkstra2(weightedgraph<Integer> g,int s)
	{
		this.g=g;
		this.s=s;
		
		marked=new boolean [g.V()];
		for(int i=0;i<g.V();i++)
		{
			marked[i]=false;
			dist[i]=Integer.MAX_VALUE;
		}
		MinHeap<Edge<Integer>> heap=new MinHeap<>(g.E());
		marked[s]=true;
		dist[s]=0;
		for(Edge<Integer>e:g.adj(s))
		{
			heap.add(e);
		}
		while(!heap.isEmpty())
		{
			Edge e=heap.findMin();
			if(marked[e.W()])
			{
				heap.extractmin();
			}
			else
			{
				heap.extractmin();
				marked[e.W()]=true;
				for(Edge<Integer>e2:g.adj(e.W()))
				{
					if(!marked[e2.W()])
					{
						if(dist[e2.W()]>dist[e.W()]+e2.weight())
						{
							dist[e2.W()]=dist[e.W()]+e2.weight();
							heap.add(new Edge(e.V(),e2.W(),dist[e2.W()]));
						}
					}
				}
			}
		}
								
	}
}
