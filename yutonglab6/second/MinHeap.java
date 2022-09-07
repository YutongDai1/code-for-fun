package dyt4;

public class MinHeap<E extends Comparable<E>> {


	
	private MyArray<E>arr;
	private int count;
	public MinHeap(int capacity)
	{
		arr=new MyArray(capacity);
		count=0;
	}
	
	public int getleft(int index)
	{
		return 2*index+1;
	}
	public int getright(int index)
	{
		return 2*index+2;
	}
	public int getparent(int index)
	{
		return (index-1)/2;
	}
	public boolean isEmpty()
	{
		return count==0;
	}
	public E extractmin()
	{
		E ret=findMin();
		count--;
		arr.swap(0,arr.getsize()-1);
		arr.removelast();
		siftdown(0);
		return ret;
	}
	public E findMin()
	{
		assert arr.getsize()!=0;
		return arr.get(0);
	}
	public void add(E e)
	{
		
		arr.addlast(e);
		count++;
		siftup(arr.getsize()-1);
	}
	private void siftup(int index)
	{
		
		while(index!=0)
		{
			if(arr.get(index).compareTo(arr.get(getparent(index)))<0)
			{
				arr.swap(index, getparent(index));
				index=getparent(index);
			}
			else
				break;
		}
			
	}
	private void siftdown(int index)
	{
		int temp;
		while(getleft(index)<arr.getsize())
		{
			temp=getleft(index);
			if(getright(index)<arr.getsize()&&arr.get(getright(index)).compareTo(arr.get(temp))<0)
				temp=getright(index);
			if(arr.get(index).compareTo(arr.get(temp))<0)
				break;
			arr.swap(index, temp);
			index=temp;
		}
		
	}
	
}
