package dyt5;


public class DenseweightedGraph  {

	private int n;
	private int [][]Matrix;
	public DenseweightedGraph(int n)
	{
		this.n=n;
		Matrix=new int[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				if(i!=j)
				   Matrix[i][j]=Integer.MAX_VALUE;
	}
	public void addEdge(int v,int w,int weight)
	{
		Matrix[v][w]=weight;
		Matrix[w][v]=weight;
	}
	public boolean hasEdge(int v,int w)
	{
		return Matrix[v][w]!=Integer.MAX_VALUE;
	}
	public int N()
	{
		return n;
	}
	public int weight(int v,int w)
	{
		return Matrix[v][w];
	}
}
