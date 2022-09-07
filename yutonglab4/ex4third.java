package dyt;
import java.util.Scanner;
public class ex4third {
	
	public static void main(String []a)
	{
		
		myQuadraticProbingHashTable hash=new myQuadraticProbingHashTable();
		hash.creathashmap();
		System.out.println();
		hash.show();
		System.out.println();
		System.out.print(hash.showposition( 55));
	}

}
class myQuadraticProbingHashTable
{
	
	Scanner in=new Scanner(System.in);
	private class HashEntry
	{
		public int e;
		public boolean isActive;
		public HashEntry(int e,boolean isActive)
		{
			this.e=e;
			this.isActive=isActive;
		}
		public HashEntry(int e)
		{
			this(e,true);
		}
	}
	private HashEntry []array;
	private int cursize;
	private void allocateArray()
	{
		array=new HashEntry[20];
	}
	public myQuadraticProbingHashTable()
	{
		 allocateArray();
		 makeEmpty();
	}
	
	//输入哈希表中记录的个数 n(n<=20)和各记录的关键字值
	public void creathashmap()
	{
		int n;
		n=in.nextInt();//输入个数 小于20个
		int num;
		for(int i=0;i<n;i++)
		{
			num=in.nextInt();
			insert(num);
		}
		
	}
	//输出
	public void show()
	{
		for(int i=0;i<array.length;i++)
		{
			if(isActive(i))
		System.out.print(array[i].e+"\t");
		}
	}
	//通过key找position,查找失败返回-1;
	public int  showposition( int e)
	{
		for(int i=0;i<20;i++)
		{
			if(isActive(i))
				if(array[i].e==e)
					return i;
		}
		return -1;
	}
	private boolean isActive(int currentPos) 
	{
		return array[currentPos]!=null&&array[currentPos].isActive;
	}
	private int findPos(int e)
	{
		int currentpos =myhash(e);
		while(array[currentpos]!=null&&!(array[currentpos].e==e))
		{
			currentpos++;
			if(currentpos>=array.length)
				currentpos-=array.length;
		}
		return currentpos;
	}
	
	
	public void makeEmpty()
	{
		for(int i=0;i<array.length;i++)
			array[i]=null;
		cursize=0;
	}
	public void insert(int e)
	{
		int currentpos=findPos(e);
		if(isActive(currentpos))
			return;
		array[currentpos]=new HashEntry(e,true);
		
	}
	public void remove(int e)
	{
		int currentpos=findPos(e);
		if(isActive(currentpos))
			array[currentpos].isActive=false;
	}
	public boolean contains(int e)
	{
		int currentpos=findPos(e);
		return isActive(currentpos);
		
	}
	public int myhash(int e)
	{
		int hashval=e%array.length;
		if(hashval<0)
			hashval+=array.length;
		return hashval;
	}

	
}
