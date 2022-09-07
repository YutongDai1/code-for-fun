package dyt;
import java.util.Queue;
import java.util.Vector;
import java.util.Stack;
import java.util.LinkedList;
public class ex6first2 {

}
class myshortestpath
{
	Graph g;
	int s;
	private boolean []visited=new boolean[g.V()];;
	private int []ord=new int[g.V()];
	private int []from=new int[g.V()];
	public myshortestpath(Graph graph,int s)//�涨��ʼ��
	{
		g=graph;
		this.s=s;
		for(int i=0;i<g.V();i++)
		{
			from[i]=-1;
			visited[i]=false;
		}
		
		
		bfs();
	}
	
	//BFS
	public void bfs()
	{
		Queue<Integer>q=new LinkedList<>();
		q.add(s);
		visited[s]=false;
		while(!q.isEmpty())
		{
			
			int v=q.poll();
			for(int i:g.adj(v))
			{
				if(!visited[i])
				{
					q.add(i);
					visited[i]=true;
					from[i]=v;
					ord[i]=ord[v]+1;
				}
			}
			
		}
	}
	
	//���ش�s��w�����·���ĳ���
	public  int getlength(int w)
	{
		return ord[w];
	}
	
	//���ش�s��w�����·���ľ���·��
	public void showpath(int w)
	{
		assert w>=0&&w<g.V();
		Stack<Integer>stack=new Stack<>();
		stack.add(w);
		int f=from[w];
		while(f!=-1)
		{
			stack.add(f);
			f=from[f];
		}
		for(int i=0;i<stack.size();i++)
		{
			if(i==stack.size()-1)
				System.out.print(stack.pop());
			else
			{
				System.out.print(stack.pop()+"->");
			}
		}
	}
	
}