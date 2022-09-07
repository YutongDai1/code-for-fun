package dyt;
//char->int   operator,,,,,,
public class ex2five {
     public static void main(String []args)
     {
    	 operate("12/2+4*2+(2*4)-1");
     }
     public static void operate( String exp)
     
     {
    	 Mystack2<Integer>numstack=new Mystack2<>();
    	 Mystack2<Character>operatorstack=new Mystack2<>();
    	 int num1=0;
    	 int num2=0;
    	 char ch=' ';
    	 char operator=' ';
    	 String str="";
    	 int res;
    	 for(int i=0;i<exp.length();i++)
    	 {
    		  ch=exp.charAt(i);
    		  if(operatorstack.isEmpty()&&operatorstack.isoperator(ch))
    		  {
    			  operatorstack.push(ch);
    		  }
    		  else if(ch=='(')
    		  { 		  
    				  operatorstack.push(ch);	    
    		  }
    		  else if (ch==')')
    		  {
    			  if(operatorstack.iscontainszuokuohao())
    			  {
    				  while(true)
    			  {
    			      operator=operatorstack.pop();
    			      if(operator=='(')
    			    	  break;
    				  num1=numstack.pop();
    				  num2=numstack.pop();
    				  res=operatorstack.calculator(num1, num2, operator);
    				  numstack.push(res);
    				  
    			  }
    			  }
    		  }
    		  else if(operatorstack.isoperator(ch))
    		  {
    			  if(!operatorstack.isEmpty())
    			  {
    				  if(operatorstack.peek().equals('('))
    					  operatorstack.push(ch);
    				  else if(operatorstack.priority(ch)<operatorstack.priority(operatorstack.peek()))
    				  {
    					  num1=numstack.pop();
    					  num2=numstack.pop();
    					  operator=operatorstack.pop();
    					  res=operatorstack.calculator(num1, num2, operator);
    					  numstack.push(res);
    					  operatorstack.push(ch);
    				  }
    				  else
    				  {
    					  operatorstack.push(ch);
    				  }
    			  }
    			  else {
    				  operatorstack.push(ch);
    			  }  
    		  }
    		  else
    		  {
    			  str+=ch;
    			  if(i==exp.length()-1)
    			  {
    				  numstack.push(Integer.parseInt(str));
    			  }
    			  else 
    			  {
    				  if(operatorstack.isoperator(exp.charAt(i+1)))
    				  {
    					  numstack.push(Integer.parseInt(str));
    					  str="";
    				  }		  
    			  }
    		  }  	  
    	 }
    	 while(!operatorstack.isEmpty())
    	 {
    		 num1=numstack.pop();
    		 num2=numstack.pop();
    		 operator=operatorstack.pop();
    		 numstack.push(operatorstack.calculator(num1, num2, operator));
    		 
    	 }
    	 System.out.println(numstack.peek());
     }
    
}
class Mystack2<E>
{
	private class Node
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
			this(e,null);
		}
		Node()
		{
			this(null,null);
		}
	}
	private int size;
	private Node head;
	Mystack2()
	{
		size=0;
		head=new Node();
	}
	public boolean isEmpty()
	{
		return size==0;
	}
	public void push(E e)
	{
		Node pre=head;
		pre.next=new Node(e,pre.next);
		size++;
	}
	public E peek()
	{
		if(size==0)
			throw new IllegalArgumentException("stackÎª¿Õ");
		return head.next.e;
	}
	public E pop()
	{
		if(size==0)
		throw new IllegalArgumentException("stackÎª¿Õ");
		Node cur=head.next;
		head.next=head.next.next;
		size--;
		cur.next=null;
		return cur.e;
	}
	public int priority(char operator)
	{
		
	       if(operator=='*'||operator=='/')
			return 1;
		else if(operator=='+'||operator=='-')
			return 0;
		else 
			return -1;
	}
	public boolean iscontainszuokuohao()
	{
		Node cur=head.next;
		for(int i=0;i<size;i++)
		{
			if(cur.e.equals('('))
				return true;
			cur=cur.next;
		}
		return false;
	}
	public boolean isoperator(char operator)
	{
		return operator=='+'||operator=='-'||operator=='*'||operator=='/'||operator=='('||operator==')';   
	}
	public int calculator(int num1,int num2,char operator)
	{
		int result=0;
		switch(operator)
		{
		case '+':
			result=num1+num2;
			break;
		case '-':
			result=num2-num1;
			break;
		case '*':
			result=num1*num2;
			break;
		case '/':
			result=num2/num1;
			break;
		}
		return result;
	}
}