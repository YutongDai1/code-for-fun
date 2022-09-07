package dyt;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class ex6first {

	public static void main(String []ax)
	{
		int count=0;  //用来判断是否存在环 
		int []a=new int[7];
		node []nodes=new node[7];
		List<Integer>[]list=new ArrayList[7];
		for(int i=0;i<7;i++)
		{
			nodes[i]=new node(i);
			list[i]=new ArrayList<>();
		}
		initmap(nodes,list,a);
		Queue<node>queue=new LinkedList<>();
		for(int i=0;i<7;i++)
		{
			if(a[i]==0)
				queue.add(nodes[i]);
		}
		List<Integer>res=new ArrayList<>();   //存放结果
		while(!queue.isEmpty())
		{
			node n=queue.poll();
			res.add(n.value);
			count++;
			List<Integer>next=n.next;
			for(int i=0;i<next.size();i++)
			{
				if(--a[next.get(i)]==0)
				{
					queue.add(nodes[next.get(i)]);
				}
			}
			
		}
		
		if(count!=7)   //说明存在环
		{
			System.out.print("has a circle");
		}
		else
		{
			for(int i=0;i<res.size();i++)
				System.out.print(res.get(i)+" ");
		}
	}
	static class node
	{
		int value;
		List<Integer>next;
		public node(int value)
		{
			this.value=value;
			next=new ArrayList<>();
		}
		public void setnext(List<Integer> list)
		{
			this.next=list;
		}
		
	}
	
	public  static void initmap(node []nodes,List<Integer>[]list,int []a)
	{
		
		list[0].add(1);
		list[0].add(2);
		list[0].add(3);
		nodes[0].setnext(list[0]);
		a[1]++;
		a[2]++;
		a[3]++;
		list[1].add(3);
		list[1].add(4);
		nodes[1].setnext(list[1]);
		a[4]++;
		a[3]++;
		list[2].add(5);
		nodes[2].setnext(list[2]);
		a[5]++;
		list[3].add(2);
		list[3].add(5);
		list[3].add(6);
		nodes[3].setnext(list[3]);
		a[2]++;
		a[5]++;
		a[6]++;
		list[4].add(3);
		list[4].add(6);
		nodes[4].setnext(list[4]);
		a[3]++;
		a[6]++;
		nodes[5].setnext(list[5]);
		
		list[6].add(5);
		nodes[6].setnext(list[6]);
		a[5]++;

	}
	
}
