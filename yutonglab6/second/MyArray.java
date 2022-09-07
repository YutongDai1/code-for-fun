package dyt4;

public class MyArray<E extends Comparable<E>> {

	private E []array;
	private int size;
	
	public MyArray(int capacity)
	{
		array=(E[])new Object[capacity];
		size=0;
		
	}
	public int getsize()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return size==0;
	}
	public void add(int index,E e)
	{
		assert index>=0&&index<size;
		
		if(index==array.length)	
		{
			resize(2*array.length);
		}
		for(int i=size-1;i>=index;i--)
		{
			array[i+1]=array[i];
		}
		array[index]=e;
		size++;
	}
	private void resize(int newcapacity)
	{
		E []arr=(E[])new Object[newcapacity];
		
		for(int i=0;i<size;i++)
			arr[i]=array[i];
		array=arr;
	}
	public void addlast(E e)
	{
		add(size-1,e);
	}
	public E remove(int index)
	{
		assert index>=0&&index<size;
		E ret=array[index];
		for(int i=index;i<size-1;i++)
			array[i]=array[i+1];
		size--;
		return ret;
	}
	public E removelast()
	{
		return remove(size-1);
	}
	public void swap(int i,int j)
	{
		assert i>=0&&i<size&&j>=0&&j<size;
		E e=array[i];
		array[i]=array[j];
		array[j]=e;
	}
	public E get(int index)
	{
		assert index>=0&&index<size;
		return array[index];
	}
	
	public void set(int index,E e)
	{
		assert index>=0&&index<size;
		
		array[index]=e;
	}
	
}
