
package dyt;
import java.util.Scanner;
public class ex2one {
class mystack{
	private class Node
	{
		Node next;
		int e;
		Node(int e,Node next)
		{
			this.e=e;
			this.next=next;
		}
		Node (int e)
		{
			this(e,null);
		}
		Node ()
		{
			this(0,null);
		}
	}
	private int size;
	private Node head;
	public mystack()
	{
		size=0;
		head=new Node();
	}
	public void add()
	{
		Scanner in=new Scanner(System.in);
		int e=in.nextInt();
		Node pre=head;
		while(e!=0)
		{
			pre.next=new Node(e,pre.next);
			size++;
			e=in.nextInt();
		}
		pre=head;
		while(pre.next!=null)
		{
			pre=pre.next;
			System.out.println(pre.e);
		}
		
	}
	public void insert(int x,int index)
	{
		Node pre=head;
		for(int i=0;i<index-1;i++)
		{
			pre=pre.next;
		}
		pre.next=new Node(x,pre.next);
		size++;
		pre=head.next;
		for(int i=0;i<size;i++)
		{
			System.out.println(pre.e);
			pre=pre.next;
		}
	}
	public void remove()
	{
		if(size==0)
			throw new IllegalArgumentException("stack为空");
		Node pre=head;
		pre.next=pre.next.next;
		size--;
		pre=head.next;
		for(int i=0;i<size;i++)
		{
			System.out.println(pre.e);
			pre=pre.next;
		}
	}
	public void  get(int num)
	{
		if(num>size)
			System.out.print("元素不存在");
		
		Node pre=head.next;	
		for(int i=0;i<num-1;i++)
			{
				pre=pre.next;
			}
		System.out.println(pre.e);
	}
}

	public static void main(String args[])
	{
		mystack stack=new ex2one().new mystack();
		stack.add();
		stack.remove();
		stack.insert(10, 2);
		stack.get(3);
	}
}