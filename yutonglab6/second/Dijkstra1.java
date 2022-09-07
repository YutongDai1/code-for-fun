package dyt4;

//»ùÓÚÁÚ½Ó¾ØÕó
public class Dijkstra1 {

	DenseweightedGraph<Integer>g;
	public  int []dijkstra(int v)
	{
		if(v<0||v>=g.V())
			throw new ArrayIndexOutOfBoundsException();
		
		boolean []marked=new boolean[g.V()];
		
		 int []distance=new int[g.V()];
		
		 for(int i=0;i<g.V();i++)
		 {
			 for(int j=i+1;j<g.V();j++)
			 {
				 if(!g.hasEdge(i, j))
					 g.set(i, j, Integer.MAX_VALUE);			 
			 }
		 }
		 int x=0;
		 for(Edge<Integer> e:g.adj(v))
		 {
			 distance[x++]=e.weight();
		 }
		
		 marked[v]=true;
		 for(int i=0;i<g.V();i++)
		 {
			 int index=-1;
			 int min=Integer.MAX_VALUE;
			 for(int j=0;j<g.V();j++)
			 {
				 if(!marked[j]&&distance[j]<min)
				 {
					 min=distance[j];
					 index=j;
				 }
				 
			 }
			 marked[index]=true;
			 
			 for(int j=0;j<g.V();j++)
			 {
				 if(g.hasEdge(index,j)&&(min+distance[index])<distance[j])
				 {
					 
					 distance[j]=min+distance[index];
				 }
			 }
			  
		 }
		 
		 return distance;
		
	}
}
