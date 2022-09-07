package dyt;
public class exfive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public static mylinkedlist2 add(mylinkedlist2 l1,mylinkedlist2 l2)
	{
		mylinkedlist2 l3=new mylinkedlist2();
		int i=0,j=0;
		int n1=l1.getex(i);
		int n2=l2.getex(j);
		int n11=l1.getco(i);
		int n22=l2.getco(j);
		while(i<l1.getsize()&&j<l2.getsize())
		{
			
			if(n1==n2)
			{
				l3.addfirst(n1, n11+n22);
				i++;
				j++;
				if(i>=l1.getsize()||j>=l2.getsize())
					break;
					
				n1=l1.getex(i);
				n2=l2.getex(j);
				n11=l1.getco(i);
			    n22=l2.getco(j);
			}
			else if(n1>n2)
			{
				l3.addfirst(n2,n22);
				j++;
				if(j>=l2.getsize())
					break;
				n2=l2.getex(j);
				n22=l2.getco(j);
			}
			else
			{
				l3.addfirst(n1,n11);
				i++;
				if(i>=l1.getsize())
					break;
				n1=l1.getex(i);
				n11=l1.getco(i);
			}
		}
		while(i<l1.getsize())
		{
			n1=l1.getex(i);
			n11=l1.getco(i);
			i++;
			l3.addfirst(n1, n11);
			
			
		}
		while(j<l2.getsize())
		{
			n2=l2.getex(j);
			n22=l2.getco(j);
			j++;
			l3.addfirst(n2, n22);
			
			
		}
		return l3;
	}
	public static mylinkedlist2 multiply(mylinkedlist2 l1,mylinkedlist2 l2)
	{
		mylinkedlist2 l3=new mylinkedlist2();
		for(int i=0;i<l1.getsize();i++)
		{
			int n1=l1.getex(i);
			int n11=l1.getco(i);
			for(int j=0;j<l2.getsize();j++)
			{
				int n2=l2.getex(j);
				int n22=l2.getco(j);
				int n3=n1+n2;
				int n33=n11*n22;
				if(!l3.iscontainsex(n3))
				{
				l3.addfirst(n3, n33);
				}
				else
				{
				l3.set(n3,n33);            //原来的值加上n33
				}
			}
		}	
		return l3;
	}

}
class mylinkedlist2
{
	private class Node
	{
		Node next;
		int ex;
		int co;
		Node(int ex ,int co,Node next)
		{
			this.ex=ex;
			this.co=co;
			this.next=next;
		}
		Node(int ex,int co )
		{
			this(ex,co,null);
		}
		Node()
		{
			this(0,0,null);
		}
		
	}
	private int size;
	private Node dummyhead;
	mylinkedlist2()
	{
		size=0;;
	    dummyhead=new Node(0,0,null);
	}
    public int getsize()
    {
    	return size;
    }
	
	 public void addfirst(int ex,int co)
	    {
	    	add(0,ex,co);
	    }
	    public void add(int index,int ex,int co)
	    {
	    	if(index<0||index>size)
	    		throw new IllegalArgumentException("Add failed. Illegal index.");
	    	Node pre=dummyhead;
	    	for(int i=0;i<index;i++)
	    	{
	    		pre=pre.next;
	    	}
	    	Node node=new Node(ex,co);
	    	node.next=pre.next;
	    	pre.next=node;
	    	
	    	size++;
	    }
	 
	    public int getex(int index)
	    {
	    	if(index<0||index>=size)
	    		throw new IllegalArgumentException("Add failed. Illegal index.");
	    	Node cur=dummyhead.next;
	    	for(int i=0;i<index;i++)		
	    	{
	    		cur=cur.next;
	    	}
	    	return cur.ex;
	    }
	    public int getco(int index)
	    {
	    	if(index<0||index>=size)
	    		throw new IllegalArgumentException("Add failed. Illegal index.");
	    	Node cur=dummyhead.next;
	    	for(int i=0;i<index;i++)		
	    	{
	    		cur=cur.next;
	    	}
	    	return cur.co;
	    }
		boolean iscontainsex(int ex)
		{
		     Node cur=dummyhead.next;
		     for(int i=0;i<size;i++)
		     {
		    	 if(cur.ex==ex)
		    	 {
		    		 return true;
		    	 }
		    	 cur=cur.next;
		     }
		     return false;
		}
		public void set(int ex,int co)
		{
			Node node=dummyhead.next;
			for(int i=0;i<size;i++)
			{
				if(node.ex==ex)
				{
					node.co=node.co+co;
					break;
				}
			}
		}
	
}