package dyt4;

public class Edge<Weight extends Comparable<Weight>> implements Comparable<Edge<Weight>>{

	private int v;
	private int w;
	private Weight weight;
	public Edge(int v,int w,Weight weight)
	{
		this.v=v;
		this.w=w;
		this.weight=weight;
	}
	public Edge(Edge<Weight>e)
	{
		this(e.V(),e.W(),e.weight());
	}
	public int V()
	{
		return v;
	}
	public int W()
	{
		return w;
	}
	public void set(Weight w)
	{
		this.weight=w;
	}
	public Weight weight()
	{
		return weight;
	}
	@Override
	public String toString()
	{
		return v+"-"+w+":"+weight;
	}
	public int theother(int x)
	{
		assert x==v||x==w;
		return x==v?w:v;
	}
	@Override
	public int compareTo(Edge<Weight> e)
	{
		if(weight.compareTo(e.weight())>0)
			return 1;
		else if(weight.compareTo(e.weight())<0)
			return -1;
		else
			return 0;
	}
	
}
