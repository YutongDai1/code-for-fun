
/*4.���赥����ʹ��һ��ͷ�ڵ�ʵ�֣�����β�ڵ㣬��������ֻ�����Ը�ͷ�ڵ�����á���дһ���࣬���������̲Ŀκ���ϰ3.11��
a.���������С�ķ�����
b.��ӡ����ķ�����
c.����ֵx�Ƿ�������ķ�����
d.���ֵx��δ�����������ֵx��������ķ�����
e.���ֵx����������x�Ӹ�������ɾ���ķ�����*/
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