package dyt5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class func
{
	static Scanner in=new Scanner(System.in);
	public static void menu()
	{
		
		 System.out.println("--------�й�������ѧУ԰������ѯ--------");
		 System.out.println("       1.����ֲ�չʾ.");
		 System.out.println("       2.������Ϣ��ѯ.");
		 System.out.println("       3.�鿴һ���㵽������������·��.");
		 System.out.println("       4.�鿴�������������·����");
		 System.out.println("       5.�˳�.");
		 System.out.println("--------------------------------------");

	}
	public static void displayTU()
	{
		System.out.print("***---------------------------------------------------�й�������ѧƽ��ͼ--------------------------------------------------------------------------------------------------------------***\n\n");
		System.out.print("***                                                                                                                                      ***\n");
		System.out.print("***                   ������¥(4)----------------------------------------------------------- ����ѵ������(8)                              ***\n");
		System.out.print("***                        |                                           |                      |                  |                       ***\n");
		System.out.print("***                      |                 ������¥(5)                                         |                         ����(9)        ***\n");
		System.out.print("***                     |             |                                                      |                           |               ***\n");
		System.out.print("***                �ݷ�ͼ���(3)                                                              |                        |                  ***\n");
		System.out.print("***                |            |                                                           |                    |                      ***\n");
		System.out.print("***              |                   |                                                     |                |                          ***\n");
		System.out.print("***            |                         ���º� (10)                                        |            |                               ***\n");
		System.out.print("***           |                                                   |                       |       |                                    ***\n");
		System.out.print("***          |                             |                                      ���������(6)                                        ***\n");
		System.out.print("***         |                          |                                           |                                                    ***\n");
		System.out.print("***       |                        |                                           |                                                         ***\n");
		System.out.print("***     |                      |                                          |        |                                                     ***\n");
		System.out.print("*** ����(0)���� ���� ���� ���� ���� ���� ���� ����¥(1)   ���� ���� ���� ���� ���� ���� ���� ����¥(2)         |                                                        ***\n\n");
		System.out.print("***                                                              |          |                                                            ***\n\n");
		System.out.print("***                                                            �콡������ (7)                                                                                                              ***\n\n");

	}
	public static void initinformation(List<place> list)
	{
		place []places=new place[11];
		places[0]=new place(0,"����","��ʦ�쵼�ǿ������Ҫ�ط�֮һ");
		places[1]=new place(1,"����¥","ѧ���Ͽε���Ҫ�ط�֮һ");
		places[2]=new place(2,"����¥","ѧ���Ͽε���Ҫ�ط�֮һ����ΪABCD�ĸ�����");
		places[3]=new place(3,"�ݷ�ͼ���","ѧϰ�����֮һ���ߴ�11�㣬����ḻ");
		places[4]=new place(4,"������¥","��Ϣ����ѧԺ��������ϻ��ط�");
		places[5]=new place(5,"������¥","��ѧԺ������ѧԺ���ڰ칫��");
		places[6]=new place(6,"���������","ѧ���μӱ��ݵȻ�ĵط�");
		places[7]=new place(7,"�콡������","���������ݣ�ѧ���ǽ��������˶��ĵط�");
		places[8]=new place(8,"����ѵ������","ѧ���ǽ�ʵϰ�ĵط�");
		places[9]=new place(9,"����","ѧ���Ǵ�����ĵط�");
		places[10]=new place(10,"���º�","�������º����ڰ���������");
		for(int i=0;i<11;i++)
		{
			list.add(places[i]);
		}
	}
	public static void showinformation(List<place> list)
	{
		System.out.print("--------------�������Ӧ�ص������Բ�ѯ��Ϣ---------------------------\n");
		System.out.print("0.����\r\n" + 
				"1.����¥\r\n" + 
				"2.����¥\r\n" + 
				"3.�ݷ�ͼ���\r\n" + 
				"4.������¥\r\n" + 
				"5.������¥\r\n" + 
				"6.���������\r\n" + 
				"7.�콡������\r\n" + 
				"8.����ѵ������\r\n" + 
				"9.����\r\n" + 
				"10.���º�\n");
		int x;
		x=in.nextInt();
		while(x<0||x>=11)
		{
			System.out.println("����ֵ���Ϸ�������������");
			x=in.nextInt();
		}
		System.out.print(list.get(x).toString()+"\n\n");
	}
	public static void dijstra(DenseweightedGraph g,List<place> list)
	{
		System.out.print("--------------�������Ӧ�ص������Բ�ѯ���·��---------------------------\n");
		System.out.print("0.����\r\n" + 
				"1.����¥\r\n" + 
				"2.����¥\r\n" + 
				"3.�ݷ�ͼ���\r\n" + 
				"4.������¥\r\n" + 
				"5.������¥\r\n" + 
				"6.���������\r\n" + 
				"7.�콡������\r\n" + 
				"8.����ѵ������\r\n" + 
				"9.����\r\n" + 
				"10.���º�\n");
		
		
		int v;
		v=in.nextInt();
		while(v<0||v>=11)
		{
			System.out.println("����ֵ���Ϸ�������������");
			v=in.nextInt();
		}
			
		TreeMap<Integer,Integer>map=new TreeMap<>();
		for(int i=0;i<g.N();i++)
		{
			map.put(i, v);
		}
		
		int []res=new int[g.N()];
		boolean []visited=new boolean [g.N()];
		for(int i=0;i<g.N();i++)
		{
			if(!g.hasEdge(v, i))
				res[i]=Integer.MAX_VALUE;
			else
				res[i]=g.weight(v, i);
		}
		visited[v]=true;
		for(int i=0;i<g.N();i++)
		{
			int index=-1;
			int min=Integer.MAX_VALUE;
			for(int j=0;j<res.length;j++)
			{
				if(!visited[j]&&res[j]<min)
				{
					min=res[j];
					index=j;
				}
			}
			if(index!=-1)
			{
			visited[index]=true;
			for(int j=0;j<g.N();j++)
			{
				if(g.hasEdge(index, j))
				if(!visited[j]&&min+g.weight(index, j)<res[j])
				{
					res[j]=min+g.weight(index, j);
					map.put(j, index);
				}
			}
			}
		}
		
		int x;
		Stack<Integer>stack=new Stack<>();
		for(int i=0;i<res.length;i++)
		{
			
			if(res[i]!=Integer.MAX_VALUE)
			{
			System.out.print(list.get(v).name()+"->"+list.get(i).name()+":"+res[i]+"m"+"  "+"·��Ϊ��");
			stack.add(i);
			int temp=i;
			while(map.get(temp)!=v)
			{
				stack.add(map.get(temp));
				temp=map.get(temp);
			}
			stack.add(v);
			while(!stack.isEmpty())
			{
				x=stack.pop();
				System.out.print(list.get(x).name());
				if(!stack.isEmpty())
					System.out.print("->");
			}
			System.out.println();
			}
			
		}
		
	}
	public static void floyd(DenseweightedGraph g,List<place>list)
	{
		System.out.print("--------------�����������յ������Բ�ѯ---------------------------\n");
		System.out.print("0.����\r\n" + 
				"1.����¥\r\n" + 
				"2.����¥\r\n" + 
				"3.�ݷ�ͼ���\r\n" + 
				"4.������¥\r\n" + 
				"5.������¥\r\n" + 
				"6.���������\r\n" + 
				"7.�콡������\r\n" + 
				"8.����ѵ������\r\n" + 
				"9.����\r\n" + 
				"10.���º�\n");
		int [][]e=new int[g.N()][g.N()];
		for(int i=0;i<g.N();i++)
		{
			for(int j=0;j<g.N();j++)
				e[i][j]=g.weight(i, j);
		}
		for(int k=0;k<g.N();k++)
			for(int i=0;i<g.N();i++)
				for(int j=0;j<g.N();j++)
				{
					int temp=(e[i][k]==Integer.MAX_VALUE||e[k][j]==Integer.MAX_VALUE)?Integer.MAX_VALUE:e[i][k]+e[k][j];
					if(e[i][j]>temp)
					{
						e[i][j]=temp;
					}
					
				}
		
		int v,w;
		v=in.nextInt();
		w=in.nextInt();
		while(v<0||v>=11||w<0||w>=11)
		{
			System.out.println("����ֵ���Ϸ�������������");
			v=in.nextInt();
			w=in.nextInt();
		}
		System.out.println(list.get(v).name()+"->"+list.get(w).name()+":"+e[v][w]+"m");
		
	}
	public static void initgraph(DenseweightedGraph g)
	{	
		g.addEdge(0, 0, 0);
		g.addEdge(1, 1, 0);
		g.addEdge(2, 2, 0);
		g.addEdge(3, 3, 0);
		g.addEdge(4, 4, 0);
		g.addEdge(5, 5, 0);
		g.addEdge(6, 6, 0);
		g.addEdge(7, 7, 0);
		g.addEdge(8, 8, 0);
		g.addEdge(9, 9, 0);
		g.addEdge(10,10,0);
		
		g.addEdge(0, 1,10 );
		g.addEdge(0, 3,14);
		g.addEdge(1, 10, 7);
		g.addEdge(1, 2, 9);
		g.addEdge(2, 6, 7);
		g.addEdge(2, 7, 2);
		g.addEdge(3, 4, 6);
		g.addEdge(3, 5, 7);
		g.addEdge(3, 10, 8);
		g.addEdge(5, 8, 12);
		g.addEdge(6, 7, 6);
		g.addEdge(6, 8, 11);
		g.addEdge(6, 9, 12);
		g.addEdge(6, 10, 10);
		g.addEdge(8, 9, 10);
	}
	
}