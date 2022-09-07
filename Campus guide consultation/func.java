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
		
		 System.out.println("--------中国计量大学校园导游咨询--------");
		 System.out.println("       1.景点分布展示.");
		 System.out.println("       2.景点信息查询.");
		 System.out.println("       3.查看一景点到其他景点的最短路径.");
		 System.out.println("       4.查看任意两景点最短路径。");
		 System.out.println("       5.退出.");
		 System.out.println("--------------------------------------");

	}
	public static void displayTU()
	{
		System.out.print("***---------------------------------------------------中国计量大学平面图--------------------------------------------------------------------------------------------------------------***\n\n");
		System.out.print("***                                                                                                                                      ***\n");
		System.out.print("***                   赛博北楼(4)----------------------------------------------------------- 工程训练中心(8)                              ***\n");
		System.out.print("***                        |                                           |                      |                  |                       ***\n");
		System.out.print("***                      |                 格致中楼(5)                                         |                         网球场(9)        ***\n");
		System.out.print("***                     |             |                                                      |                           |               ***\n");
		System.out.print("***                逸夫图书馆(3)                                                              |                        |                  ***\n");
		System.out.print("***                |            |                                                           |                    |                      ***\n");
		System.out.print("***              |                   |                                                     |                |                          ***\n");
		System.out.print("***            |                         日月湖 (10)                                        |            |                               ***\n");
		System.out.print("***           |                                                   |                       |       |                                    ***\n");
		System.out.print("***          |                             |                                      启明活动中心(6)                                        ***\n");
		System.out.print("***         |                          |                                           |                                                    ***\n");
		System.out.print("***       |                        |                                           |                                                         ***\n");
		System.out.print("***     |                      |                                          |        |                                                     ***\n");
		System.out.print("*** 闻厅(0)—— —— —— —— —— —— —— 翔宇楼(1)   —— —— —— —— —— —— —— 环宇楼(2)         |                                                        ***\n\n");
		System.out.print("***                                                              |          |                                                            ***\n\n");
		System.out.print("***                                                            天健体育馆 (7)                                                                                                              ***\n\n");

	}
	public static void initinformation(List<place> list)
	{
		place []places=new place[11];
		places[0]=new place(0,"闻厅","老师领导们开会的主要地方之一");
		places[1]=new place(1,"翔宇楼","学生上课的主要地方之一");
		places[2]=new place(2,"环宇楼","学生上课的主要地方之一，分为ABCD四个区域");
		places[3]=new place(3,"逸夫图书馆","学习最大建筑之一，高达11层，藏书丰富");
		places[4]=new place(4,"赛博北楼","信息工程学院，计算机上机地方");
		places[5]=new place(5,"格致中楼","理学院，量新学院所在办公室");
		places[6]=new place(6,"启明活动中心","学生参加表演等活动的地方");
		places[7]=new place(7,"天健体育馆","室内体育馆，学生们进行室内运动的地方");
		places[8]=new place(8,"工程训练中心","学生们金工实习的地方");
		places[9]=new place(9,"网球场","学生们打网球的地方");
		places[10]=new place(10,"日月湖","美丽日月湖，黑白天鹅来相伴");
		for(int i=0;i<11;i++)
		{
			list.add(places[i]);
		}
	}
	public static void showinformation(List<place> list)
	{
		System.out.print("--------------请输入对应地点的序号以查询信息---------------------------\n");
		System.out.print("0.闻厅\r\n" + 
				"1.翔宇楼\r\n" + 
				"2.环宇楼\r\n" + 
				"3.逸夫图书馆\r\n" + 
				"4.赛博北楼\r\n" + 
				"5.格致中楼\r\n" + 
				"6.启明活动中心\r\n" + 
				"7.天健体育馆\r\n" + 
				"8.工程训练中心\r\n" + 
				"9.网球场\r\n" + 
				"10.日月湖\n");
		int x;
		x=in.nextInt();
		while(x<0||x>=11)
		{
			System.out.println("输入值不合法，请重新输入");
			x=in.nextInt();
		}
		System.out.print(list.get(x).toString()+"\n\n");
	}
	public static void dijstra(DenseweightedGraph g,List<place> list)
	{
		System.out.print("--------------请输入对应地点的序号以查询最短路径---------------------------\n");
		System.out.print("0.闻厅\r\n" + 
				"1.翔宇楼\r\n" + 
				"2.环宇楼\r\n" + 
				"3.逸夫图书馆\r\n" + 
				"4.赛博北楼\r\n" + 
				"5.格致中楼\r\n" + 
				"6.启明活动中心\r\n" + 
				"7.天健体育馆\r\n" + 
				"8.工程训练中心\r\n" + 
				"9.网球场\r\n" + 
				"10.日月湖\n");
		
		
		int v;
		v=in.nextInt();
		while(v<0||v>=11)
		{
			System.out.println("输入值不合法，请重新输入");
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
			System.out.print(list.get(v).name()+"->"+list.get(i).name()+":"+res[i]+"m"+"  "+"路径为：");
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
		System.out.print("--------------请输入起点和终点的序号以查询---------------------------\n");
		System.out.print("0.闻厅\r\n" + 
				"1.翔宇楼\r\n" + 
				"2.环宇楼\r\n" + 
				"3.逸夫图书馆\r\n" + 
				"4.赛博北楼\r\n" + 
				"5.格致中楼\r\n" + 
				"6.启明活动中心\r\n" + 
				"7.天健体育馆\r\n" + 
				"8.工程训练中心\r\n" + 
				"9.网球场\r\n" + 
				"10.日月湖\n");
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
			System.out.println("输入值不合法，请重新输入");
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