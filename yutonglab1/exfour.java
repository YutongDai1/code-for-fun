
/*4.假设单链表使用一个头节点实现，但无尾节点，并假设它只保留对该头节点的引用。编写一个类，包含（见教材课后练习3.11）
a.返回链表大小的方法。
b.打印链表的方法。
c.测试值x是否含于链表的方法。
d.如果值x尚未含于链表，添加值x到该链表的方法。
e.如果值x含于链表，将x从该链表中删除的方法。*/
package dyt;

public class exfour {
}
class mylinkedlist<E>
{
	private class Node
	{
		Node next;
		E e;
		Node(E e ,Node next)
		{
			this.e=e;
			this.next=next;
		}
		Node(E e)
		{
			this(e,null);
		}
		Node()
		{
			this(null,null);
		}
		public String toString()
		{
			return e.toString();
		}
	}
	private int size;
	private Node dummyhead;
	mylinkedlist()
	{
		size=0;;
	    dummyhead=new Node(null,null);
	}
	int getsize()
	{
	  return size;
	}
	boolean iscontains(E x)
	{
	     Node cur=dummyhead.next;
	     for(int i=0;i<size;i++)
	     {
	    	 if(cur.e==x)
	    	 {
	    		 return true;
	    	 }
	    	 cur=cur.next;
	     }
	     return false;
	}
	boolean remove(E x)
	{
		if(!iscontains(x))
			return false;
		else
		{
			Node cur=dummyhead.next;
			Node track=dummyhead;
			for(int i=0;i<size;i++)
			{
				if(cur.e.equals(x))
				{
					track.next=cur.next;
					cur.next=null;
					size--;
					break;
				}
				track=track.next;
			}
		}
		return true;
	}
	boolean add(E x)
	{
		if(iscontains(x))
			return false;
		else
		{
			Node node=new Node(x);
			node.next=dummyhead.next;
			dummyhead.next=node;	
			size++;
		}
		return true;
	}
	
	void print()
	{
		Node cur=dummyhead.next;
		for(int i=0;i<size;i++)
		{
			System.out.print(cur.e+" ");
			cur=cur.next;
		}
	}
	
}