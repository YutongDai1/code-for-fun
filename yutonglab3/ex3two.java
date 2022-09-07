package dyt;

public class ex3two {

}
// µœ÷¿¡∂Ë…æ≥˝
class mybst2<E extends Comparable<E>>
{
	private class Node
	{
		public int flag;
		public E e;
		public Node left;
		public Node right;
		public Node (E e)
		{
			flag=0;
			this.e=e;
			left=null;
			right=null;
		}
	}
	private int size;
	private int deletesize;
	private Node root;
	public mybst2()
	{
		size=0;
		deletesize=0;
		root=null;
	}
	public boolean isempty()
	{
		return size-deletesize==0;
	}
	public void remove(E e)
	{
		remove(root,e);
	}
	private void remove(Node node,E e)
	{
		if(node==null)
			return;
		if(e.compareTo(node.e)<0)
			remove(node.left,e);
		else if(e.compareTo(node.e)>0)
			remove(node.right,e);
		else
		{
			node.flag=1;
			size--;
			deletesize++;
			checkdelete();
			
		}
		
	}
	private void checkdelete()
	{
		if(size<=deletesize)
		{
			root=doremove(root);
			deletesize=0;
		}
	}
	private Node doremove(Node node)
	{
		if(node ==null)
			return null;
		if(node.flag==0)
		{
			node.left=doremove(node.left);
			node.right=doremove(node.right);
		}
		else
		{
			if(node.left==null||node.right==null)
			{
				node= node.left==null? node.right:node.left;
				node=doremove(node);
			}
			else
			{
				Node successor=min(node.right);
				if(successor==null)
				{
					node.right=null;
					node=doremove(node.right);
				}
				else
				{
					node.e=successor.e;
					node.flag=0;
					successor.flag=1;
					node=doremove(node);
				}
			}
		}
		return node;
	}
	private Node min(Node node)
	{
		if(node.left!=null)
			return min(node.left);
		else
		{
			if(node.flag==0)
				return node;
			else 
			{
				return min(node.right);
			}
		}
		
	}
	
}