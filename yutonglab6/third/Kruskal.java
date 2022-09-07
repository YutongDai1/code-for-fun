package dyt4;

import java.util.Vector;

public class Kruskal<W extends Number&Comparable<W>> {

	private Vector<Edge<W>> mst;  //最小生成树所包含的所有边
	private Number weight;
	
	public Kruskal(weightedgraph<W> g)
	{
		MinHeap<Edge<W>>pq=new MinHeap<>(g.E());  //创建最小堆
		mst=new Vector<Edge<W>>();
		for(int i=0;i<g.V();i++)
		{
			for(Edge e:g.adj(i))
			{
				if(e.V()<=e.W())
				{
					pq.add(e);
				}
			}
		}
		UnionFind uf=new UnionFind(g.V());   //借助并查集 判断
		
		while(!pq.isEmpty()&&mst.size()<g.V()-1)
		{
			Edge e=pq.extractmin();
			
			if(!uf.isconnected(e.V(), e.W()))
			{
				mst.add(e);
				uf.union(e.V(), e.W());
			}
			
		}
		weight=mst.elementAt(0).weight();
		for(int i=1;i<mst.size();i++)
			weight=weight.doubleValue()+mst.elementAt(i).weight().doubleValue();
	}
	public Vector<Edge<W>>mstEdges()
	{
		return mst;
	}
	public Number result()
	{
		return weight;
	}
}
