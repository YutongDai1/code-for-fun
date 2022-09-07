package dyt;
import java.util.Queue;
import java.util.LinkedList;

public class ex2second {
	
	public static void  main(String []a)
	{
		//测试基于linkedlist队列
		myqueue1<Integer> queue1=new myqueue1<>();
		queue1.add(2);
		queue1.add(4);
		queue1.add(8);
		//队列2->4->8
		int res1=queue1.peek();
		System.out.println(res1);
		res1=queue1.remove();
		System.out.println(res1);
		System.out.println(queue1.peek());
		
		System.out.println();
		
		//测试基于数组
		myqueue2<Integer> queue2=new myqueue2<>();
		queue2.add(2);
		queue2.add(4);
		queue2.add(8);
		//队列2->4->8
		int res2=queue2.peek();
		System.out.println(res2);
		res2=queue2.remove();
		System.out.println(res2);
		System.out.println(queue2.peek());
		
		System.out.println();
		
		//测试基于循环数组
		myqueue3<Integer> queue3=new myqueue3<>();
		queue3.add(2);
		queue3.add(4);
		queue3.add(8);
		//队列2->4->8
		int res3=queue3.peek();
		System.out.println(res3);
		res3=queue3.remove();
		System.out.println(res3);
		System.out.println(queue3.peek());
		
		System.out.println();
		
		//测试java.util.Queue
		Queue<Integer> queue4=new LinkedList<>();
		queue4.add(2);
		queue4.add(4);
		queue4.add(8);
		//队列2->4->8
		int res4=queue4.peek();
		System.out.println(res4);
		res3=queue4.remove();
		System.out.println(res4);
		System.out.println(queue4.peek());
	}

}

//基于linkedlist
class myqueue1<E>
{
	private class Node {
		Node next;
		E e;
		Node (E e,Node next)
		{
			this.e=e;
			this.next=next;
		}
		Node(E e)
		{
			this(e,null);
		}
		Node ()
		{
			this(null,null);
		}
	}
	private int size;
	private Node dummyhead;
	myqueue1()
	{
		size=0;
		dummyhead=new Node(null,null);
	}
	public int getsize()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return size==0;
	}
	public void add(E e)
	{
		dummyhead.next=new Node(e,dummyhead.next);
		size++;
	}
	public E remove()
	{
		Node pre=dummyhead;
		for(int i=0;i<size-1;i++)
		{
			pre=pre.next;
		}
		
		Node cur=pre.next;
		pre.next=null;
		size--;
		return cur.e;
	}
	public E peek()
	{
		Node pre=dummyhead;
		for(int i=0;i<size;i++)
		{
			pre=pre.next;
		}
		return pre.e;
	}
}
//基于数组
class myqueue2<E>{
	private  int size;
	private E []array;
	myqueue2(int capacity)
	{
		array=(E[])new Object[capacity];
	}
	myqueue2()
	{
		this(10);
	}
	public int getsize()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return size==0;
	}
	public void add(E e)
	{
		if(size==array.length)
			resize(2*array.length);
		for(int i=size;i>0;i--)
		{
			array[i]=array[i-1];
		}
		size++;
		array[0]=e;
	}
	public E remove()
	{
		E cur=array[size-1];
		array[size-1]=null;
		size--; 
		if(size==array.length/4&&array.length/2!=0)
			resize(array.length/2);
		return cur;
	}
	public void resize(int newcapacity)
	{
		E []newarray=(E[])new Object[newcapacity+1];
		for(int i=0;i<size;i++)
		{
			newarray[i]=array[i];
		}
		array=newarray;
		
	}
	public E peek()
	{
		return array[size-1];
	}	
}

//基于循环数组
class myqueue3<E>
{
     private E[]data;
     private int front,tail;
     private int size;
     myqueue3(int capacity )
     {
    	 data=(E[])new Object[capacity];
    	 size=0;
    	 front=0;
    	 tail=0;
     }
     myqueue3()
     {
    	 this(10);
     }
     public int getsize()
     {
    	 return size;
     }
     public boolean isEmpty()
     {
     return size==0;
     }
     public void add(E e)
     {
    	 if((tail+1)%data.length==front)
    		resize(2*data.length);
    	 data[tail]=e;
    	 tail=(tail+1)%data.length;
    	 size++;
     }
     public E remove()
     {
    	E ret=data[front];
    	data[front]=null;
    	front=(front+1)%data.length;
    	size--;
    	if(size==data.length/4&&data.length/2!=0)
    	resize(data.length/2);
    	return ret;
    	
     }
     public E peek()
     {
    	 if(isEmpty())
			  throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
		return data[front];
     }
     public void resize(int newcapacity)
     {
    	 E []newdata=(E [])new Object[newcapacity+1];
    	 for(int i=0;i<size;i++)
    	 {
    		 newdata[i]=data[i+front%data.length];
    	 }
    	 data=newdata;
    	 front =0;
    	 tail=size;
     }
     
}