package dyt6;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Maze {

	private int row,col;
	private Position start;
	private Position end;
	private char [][]maze;
	private HashSet<Position>set=new HashSet<>();
	Scanner in=new Scanner(System.in);
	Random ran=new Random();
	public Maze()
	{
		
	}
	public Maze(int row,int col,  Position start,Position end)
	{
		this.row=row;
		this.col=col;
		maze=new char[row][col];
		int temp;
		for(int i=0;i<row;i++)
		   for(int j=0;j<col;j++)
		  {
			   temp=ran.nextInt(2);
			   if(temp==0)
				   maze[i][j]='0';
			   else if(temp==1)
				   maze[i][j]='1';
		  }
		  
		this.start=start;
		this.end=end;
		maze[start.i()][start.j()]='A';
		maze[end.i()][end.j()]='B';
	
	}
	public Maze(int row,int col)
	{
		this.row=row;
		this.col=col;
		maze=new char[row][col];
		int temp;
		for(int i=0;i<row;i++)
		   for(int j=0;j<col;j++)
		  {
			
			   temp=ran.nextInt(2);
			   if(temp==0)
				   maze[i][j]='0';
			   else if(temp==1)
				   maze[i][j]='1';
		  }
		start=new Position(0,0);
		end=new Position(row-1,col-1);
		maze[0][0]='A';
		maze[row-1][col-1]='B';
	}
	
	public void getinput(List<String>s)
	{
		maze=new char[s.size()][];
		for(int i=0;i<s.size();i++)
		{
			maze[i]=s.get(i).toCharArray();
		}
		row=s.size();
		col=s.get(0).length();
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
			{
				if(maze[i][j]=='A')
					start=new Position(i,j);
				else if(maze[i][j]=='B')
					end=new Position(i,j);
			}
			
	}
	public void show()
	{
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
			{
				if(set.contains(new Position(i,j))&&maze[i][j]!='A'&&maze[i][j]!='B')
						maze[i][j]='*';
			}
			
		
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	public void search()
	{
	     HashSet<Position>path=new HashSet<>();
	     if(!search(start,path))
	     {
	    	 System.out.println("不存在通路");
	     }
	     else 
	     {
	    	 show();
	     }
		
	}
	private boolean search(Position pos,HashSet<Position>path)
	{
		if(pos.equals(end))
			return true;
		path.add(pos);
		 
		// 上下左右，邻居接点
		Position [] t = { new Position(pos.i(), pos.j() - 1), new Position(pos.i(), pos.j() + 1),
				new Position(pos.i() - 1, pos.j()), new Position(pos.i() + 1, pos.j()) };
		
		for (int i = 0; i < t.length; i++) {
			try {
				if (maze[t[i].i()][t[i].j()] != '1' && !path.contains(t[i]))
					if (search(t[i], path)) {
						set.add(pos);
						return true;
					}
			} catch (Exception e) 
			{
				// 忽略出界的问题
			}
		}



		
		return false;
		
	}
	
	
	public static void main(String []args)
	{
		
		int x;
		Scanner in =new Scanner(System.in);
		System.out.println("请输入数字选择迷宫生成方式\n"
				+ "1 手动生成\n"
				+ "2 自动生成");
		
		x=in.nextInt();
		switch(x)
		{
		case 1:
		{
			Maze maze=new Maze();
			List<String> s=new ArrayList<>();
			String d;
			System.out.println("请手动输入地图，注意0表示通路，1表示障碍，A表示起点，B表示终点，请每输入一行按回车，并且以输入quit结束输入");
			d=in.next();
			while(!d.equals("quit"))
			{
				s.add(d);
				d=in.next();
			}
			maze.getinput(s);
			maze.show();
			maze.search();
			break;
		}
		case 2:
		{
			int d,y,t;
			System.out.println("输入0 默认左上角 入口，右下角出口\n"
					          +"输入1 可以自定义入口与出口");
			t=in.nextInt();
			switch(t)
			{
			case 0:
			{
				System.out.println("请输入迷宫的行数与列数");
				d=in.nextInt();
				y=in.nextInt();
				 Maze maze=new Maze(d,y);
				   maze.show();
				   maze.search();
				   break;
			}
			case 1:
			{
				System.out.println("请输入迷宫的行数与列数");
				d=in.nextInt();
				y=in.nextInt();
				int x1,x2,x3,x4;
				System.out.println("请输入起点的坐标,注意：左上角为（0，0）");
				x1=in.nextInt();
				x2=in.nextInt();
				System.out.println("请输入终点的坐标,注意：左上角为（0，0）");
				x3=in.nextInt();
				x4=in.nextInt();
				Position po1=new Position(x1,x2);
				Position po2=new Position(x3,x4);
				Maze maze=new Maze(d,y,po1,po2);
				maze.show();
				maze.search();
				break;
			}
			}
		
		   break;
		}
		
		
		}
		
		
	}
	
}
