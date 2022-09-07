package dyt;
import java.util.Scanner;
public class ex5first {

	public static void main(String []args)
	{
		
		minheapA<Integer>heap;
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		Integer []arr=new Integer[N];
		for(int i=0;i<N;i++)
		{
			arr[i]=in.nextInt();
		}
		heap=new minheapA(arr);
		System.out.println(heap.findmin());
	}
}
class minheapA<E extends Comparable<E>>
{
	private arrayA<E> data;
	public minheapA(int capacity)
	{
		data=new arrayA(capacity);
	}
	public minheapA()
	{
		data=new arrayA();
	}
	public  minheapA(E []arr)
	{
		data=new arrayA(arr);
		for(int i=getparent(data.getsize()-1);i>=0;i--)
			siftdown(i);
	}
	public void add(E e)
	{
		data.addlast(e);
		siftup(data.getsize()-1);
	}
	
	public E findmin()
	{
		if(data.isEmpty())
		throw new IllegalArgumentException("error");

		return data.get(0);
	}
	private void siftup(int index)
	{
		while(index!=0)
		{
			int pa=getparent(index);
			if(data.get(pa).compareTo(data.get(index))<0)
			break;
			data.swap(pa, index);
			index=pa;
		}
	}
	private void siftdown(int index)
	{
		int child;
		while(index<data.getsize())
		{
		if(getleftchild(index)<data.getsize())
		{
			child=getleftchild(index);
		if(getrightchild(index)<data.getsize()&&data.get(getrightchild(index)).compareTo(data.get(child))<0)
			child=getrightchild(index);
		if(data.get(index).compareTo(data.get(child))>0)
		{
			data.swap(index,child);
			index=child;
			continue;
		}
		else
		{
			break;
		}
			
		}
		else
			break;
		
		}

	}
	public boolean isEmpty()
	{
		return data.getsize()==0;
	}
	public int getparent(int index)
	{
		return (index-1)/2;
	}
	public int getleftchild(int index)
	{
		return index*2+1;
	}
	public int getrightchild(int index)
	{
		return index*2+2;
	}
	
	
}
class arrayA<E>
{
	private int size;
	private E []arr;
	
	public arrayA(int capacity)
	{
		size=0;
		arr=(E[])new Object[capacity];
	}
	public arrayA()
	{
		this(10);
	}
	public arrayA(E[]a)
	{
		arr=(E[])new Object[a.length];
		for(int i=0;i<a.length;i++)
		{
			arr[i]=a[i];
		}
		size=a.length;
	}
	public int getsize()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public boolean iscontains(E e)
	{
		for(int i=0;i<size;i++)
		{
			if(e.equals(arr[i]))
				return true;
		}
		return false;
	}
	public E get(int index)
	{
		if(index<0||index>=size)
			throw new IllegalArgumentException("get failed.index is wrong.");
		return arr[index];
	}
	public void add(int index,E e)
	{
		if(index<0||index>size)
			throw new IllegalArgumentException("add failed.index is wrong.");
		if(index==arr.length)
		{
			resize(2*arr.length);
		}
		for(int i=size-1;i>=index;i--)
		{
			arr[i+1]=arr[i];
		}
		arr[index]=e;
	}
	public void addfirst(E e)
	{
		add(0,e);
	}
	public void addlast(E e)
	{
		add(size,e);
	}
	private void resize(int newcapacity)
	{
		E [] newarr=(E[])new Object[newcapacity];
		for(int i=0;i<size;i++)
		{
			newarr[i]=arr[i];
		}
		arr=newarr;
	}
	public void swap(int i,int j)
	{
		E temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	
}