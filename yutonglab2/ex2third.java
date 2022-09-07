package dyt;

import java.util.Stack;

public class ex2third {
	public static void main(String[] args) {
		String s="/*{}*/()";
		System.out.println(isValidwithMyStack3(s));
		System.out.println(isValid(s));
	}
	public static boolean isValid(String s)
	{
		Stack<Character> stack =new Stack();
		for(int i=0;i<s.length();i++)
		{
			char c=s.charAt(i);
			if(c=='['||c=='{'||c=='(')
			{
				stack.push(c);
			}
			else if (c=='*')
			{
				if(stack.isEmpty())
					return false;
				char cur=stack.peek();
				if(cur=='/')
					stack.push(c);
				else if(cur=='*')
					stack.push(c);
				else
					return false;
			}
			else if(c=='/')
			{
				if(stack.isEmpty())
				{
					stack.push(c);
					continue;
				}
				char cur=stack.peek();
				if(cur!='*')
					stack.push(c);
				if(cur=='/')
					return false;
				if(cur=='*')
				{
					char cur2=stack.pop();
					char cur3=stack.pop();
					if(cur3!='*')
						return false;
					char cur4=stack.pop();
					if(cur4!='/')
						return false;
				}
			}
			else
			{
				if(stack.isEmpty())
					return false;
				char cur=stack.pop();
				if(c==')'&&cur!='(')
					return false;
				else if(c=='{'&&cur!='}')
					return false;
				else if(c=='['&&cur!=']')
					return false;
		}
			
		}
		return stack.isEmpty();
	}
	public static boolean isValidwithMyStack3(String s)
	{
		MyStack3<Character> MyStack3=new MyStack3<Character>();
		for(int i=0;i<s.length();i++)
		{
			char c=s.charAt(i);
			if(c=='['||c=='{'||c=='(')
			{
				MyStack3.push(c);
			}
			else if (c=='*')
			{
				if(MyStack3.isEmpty())
					return false;
				char cur=MyStack3.peek();
				if(cur=='/')
					MyStack3.push(c);
				else if(cur=='*')
					MyStack3.push(c);
				else
					return false;
			}
			else if(c=='/')
			{
				if(MyStack3.isEmpty())
				{
					MyStack3.push(c);
					continue;
				}
				char cur=MyStack3.peek();
				if(cur!='*')
					MyStack3.push(c);
				if(cur=='/')
					return false;
				if(cur=='*')
				{
					char cur2=MyStack3.pop();
					char cur3=MyStack3.pop();
					if(cur3!='*')
						return false;
					char cur4=MyStack3.pop();
					if(cur4!='/')
						return false;
				}
			}
			else
			{
				if(MyStack3.isEmpty())
					return false;
				char cur=MyStack3.pop();
				if(c==')'&&cur!='(')
					return false;
				else if(c=='{'&&cur!='}')
					return false;
				else if(c=='['&&cur!=']')
					return false;
		}
			
		}
		return MyStack3.isEmpty();
	}
	
    }

class MyStack3<E>
{
    class Node
    {
    	Node next;
    	E e;
    	Node(E e,Node next)
    	{
    		this.e=e;
    		this.next=next;
    	}
    	Node(E e)
    	{
    		this(e, null);
    	}
    	Node()
    	{
    		this(null,null);
    	}	
    }
    private int size;
    Node dummyhead;
    MyStack3()
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
	public E pop()
	{
		Node cur=dummyhead.next;
		dummyhead.next=cur.next;
		cur.next=null;
		size--;
		return cur.e;
	}
	public E peek()
	{
		Node cur=dummyhead.next;
		return cur.e;		
	}
	public void push(E e)
	{
		Node node=new Node(e);
		node.next=dummyhead.next;
		dummyhead.next=node;
		size++;
	}
	
}


