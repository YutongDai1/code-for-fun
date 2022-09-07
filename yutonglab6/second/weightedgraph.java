package dyt4;

public interface weightedgraph<W extends Comparable<W>> {

	public int V();
	public int E();
	public void addEdge(Edge<W>e);
	public boolean hasEdge(int v,int w);
	public Iterable<Edge<W>>adj(int v);
	public void show();
}
