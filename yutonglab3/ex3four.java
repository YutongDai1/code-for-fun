package dyt;

import java.util.Stack;

public class ex3four {

	public static void main(String []a)
	{
		avlavl<Integer>avl=new avlavl<>();
		avl.add(3);
		avl.add(1);
		avl.add(4);
		avl.add(6);
		avl.add(9);
		avl.add(7);
		avl.add(5);
		avl.add(2);
		avl.getleafnode();
	}
}
class avlavl<E extends Comparable<E>>
{
	private class Node
	{
		Node left;
		Node right;
		E e;
		int height;
		public Node(E e)
		{
			this.e=e;
			height=1;
			left=null;
			right=null;
		}
		
	}
	private int size;
	private Node root;
	private int leafnode;
	public avlavl()
	{
			root=null;
			size=0;
			leafnode=0;
	}
	public int getsize()
	{
		return size;
	}
	
	public boolean isempty()
	{
		return  size==0;
	}
	private int getheight(Node node)
	{
		if(node==null)
			return 0;
		return node.height;
	}
	private int getbalancefactor(Node node)
	{
		if(node==null)
			return 0;
		return getheight(node.left)-getheight(node.right);
	}
	private Node leftrotate(Node node)
	{
		Node x=node.right;
		Node x1=x.left;
		node.right=x1;
		x.left=node;
		
		
		node.height=1+Math.max(getheight(node.left), getheight(node.right));
		x.height=1+Math.max(getheight(x.left), getheight(x.right));
		return x;
		
		
	}
	private Node rightrotate(Node node)
	{
		Node x=node.left;
		Node x1=x.right;
		node.left=x1;
		x.right=node;
		
		
		node.height=1+Math.max(getheight(node.left), getheight(node.right));
		x.height=1+Math.max(getheight(x.left), getheight(x.right));
		return x;
	}
	private void leafnode(Node node)
	{
		if(node==null)
			return;
		
		if(node.left==null&&node.right==null)
		{
			leafnode++;
		}
		leafnode(node.left);
		leafnode(node.right);
	}
	public void getleafnode()
	{
		leafnode(root);
		System.out.println(leafnode);
	}
	public void add(E e)
	{
		root=add(root,e);
	}
	private Node add(Node node,E e)
	{
		if(node==null)
		{
			return new Node(e);
		}
		Node T =node;
		Stack <Node >stack=new Stack<>();
		stack.push(T);
		Node son;
		Node fa;
		while(true)
		{
			if(e.compareTo(T.e)<0)
			{
				T=T.left;
				if(T==null)
				{
					T=new Node(e);
					stack.push(T);
					break;
				}
				else
				{
					stack.push(T);
					continue;
				}
			}
			 if(e.compareTo(T.e)>0)
			{
				T=T.right;
				if(T==null)
				{
					T=new Node(e);
					stack.push(T);
					break;
				}
				else
				{
					stack.push(T);
					continue;
				}
			}		
			
		}
		while(stack.size()>1)
		{
			son=stack.pop();
			fa=stack.pop();
			
			//比较son和fa节点e大小
			 if(fa.e.compareTo(son.e)>0)
			 {
				 fa.left=son;
				 //进行平衡维护
				 fa.height=1+Math.max(getheight(fa.left),getheight(fa.right));
				 
				 if(getbalancefactor(fa)>1&&getbalancefactor(fa.left)>=0) 
				 {
					 fa=rightrotate(fa);
				 }
				 if(getbalancefactor(fa)<-1&&getbalancefactor(fa.left)<=0) 
				 {
					 fa=leftrotate(fa);
				 }
				 if(getbalancefactor(fa)>1&&getbalancefactor(fa.left)<0) 
				 {
					 fa.left=leftrotate(fa.left);
					 fa=rightrotate(fa);
				 }
				 if(getbalancefactor(fa)<-1&&getbalancefactor(fa.left)>0) 
				 {
					 fa.right=rightrotate(fa.right);
					 fa=leftrotate(fa);
				 }
				 
				 stack.push(fa);
				 continue;
			 }
			 if(fa.e.compareTo(son.e)<0)
			 {
				 fa.right=son;
				 
				 fa.height=1+Math.max(getheight(fa.left),getheight(fa.right));
				 
				 if(getbalancefactor(fa)>1&&getbalancefactor(fa.left)>=0) 
				 {
					 fa=rightrotate(fa);
				 }
				 if(getbalancefactor(fa)<-1&&getbalancefactor(fa.left)<=0) 
				 {
					 fa=leftrotate(fa);
				 }
				 if(getbalancefactor(fa)>1&&getbalancefactor(fa.left)<0) 
				 {
					 fa.left=leftrotate(fa.left);
					 fa=rightrotate(fa);
				 }
				 if(getbalancefactor(fa)<-1&&getbalancefactor(fa.left)>0) 
				 {
					 fa.right=rightrotate(fa.right);
					 fa=leftrotate(fa);
				 }
				 
				 stack.push(fa);
				 continue;
			 }
		}
		
		return stack.pop();
	}
}