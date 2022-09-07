package dyt4;
import java.util.Vector;
public class Prim<W extends Number&Comparable<W>> {

	
	private weightedgraph<W> g;
	private MinHeap<Edge<W>> pq;
	private boolean marked[];
	private Vector<Edge<W>> mst;
	private Number weight;
	public Prim(weightedgraph<W> g)
	{
		this.g=g;
		marked=new boolean [g.V()];
		pq=new MinHeap(g.E());
		mst=new Vector<Edge<W>>();
		
		visit(0);
		while(!pq.isEmpty())
		{
			Edge e=pq.extractmin();
			if(marked[e.V()]||marked[e.W()])
				continue;
			
			mst.add(e);
			if(!marked[e.V()])
				visit(e.W());
			else
				visit(e.V());
		}
		
		weight=mst.elementAt(0).weight();
		for(int i=1;i<mst.size();i++)
		{
			weight=weight.doubleValue()+mst.elementAt(i).weight().doubleValue();
		}
	}
	private void visit(int k)
	{
		assert !marked[k];
		marked[k]=true;
		for(Edge<W>e:g.adj(k))
		{
			if(!marked[e.theother(k)])
				pq.add(e);
		}
	}
	public Vector<Edge<W>> mstEdges()
	{
		return mst;
	}
	public Number result()
	{
		return weight;
	}
}
