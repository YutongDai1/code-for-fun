package dyt4;

import java.util.Vector;

public class DenseweightedGraph<W extends Comparable<W>>implements weightedgraph {

	private int n;
	private int m;
	private boolean directed;
	private Edge<W>[][]g;
	
	public DenseweightedGraph(int n,boolean directed)
	{
		this.directed=directed;
		this.n=n;
		m=0;
		g=new Edge[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				g[i][j]=null;
	}
	@Override
	public int V() {
		return n;
	}

	@Override
	public int E() {
		return m;
	}

	@Override
	public void addEdge(Edge e) {
		assert e.V()>=0 &&e.V()<n;
		assert e.W()>=0&&e.W()<n;
		
		g[e.V()][e.W()]=new Edge(e);
		if(!directed&&e.V()!=e.W())
			g[e.W()][e.V()]=new Edge(e.W(),e.V(),e.weight());
		
		m++;
	}

	@Override
	public boolean hasEdge(int v, int w) {
		assert v>=0&&v<n;
		assert w>=0&&w<n;
		
		return g[v][w]!=null;
	}

	@Override
	public Iterable<Edge<W>> adj(int v) {
		assert v>=0&&v<n;
		
		Vector<Edge<W>>adjv=new Vector<>();
		for(int i=0;i<n;i++)
		{
			if(g[v][i]!=null)
				adjv.add(g[v][i]);
		}
		return adjv;
	}

	@Override
	public void show() 
	{
		
		
	}

	public void set(int i,int j,W w)
	{
		g[i][j].set(w);
	}
	
}
