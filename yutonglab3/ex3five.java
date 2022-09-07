package dyt;

public class ex3five {
	public static void main(String []a)
	{
		BT<Integer>bt=new BT<>();
		bt.add(bt.root, 2, true);
		bt.add(bt.root, 3, true);
		bt.add(bt.root.left, 5, true);
		bt.add(bt.root, 7, false);
		bt.add(bt.root.right, 8,true);
		bt.add(bt.root.right.left, 9, false);
		String str=bt.findway(9);
		System.out.print(str);
	}

}
class BT<E>
{
	public boolean left=true;
	 public boolean right=false;
	 class Node
	{
		Node left;
		Node right;
		E e;
		public Node(E e)
		{
			this.e=e;
			left=null;
			right=null;
		}
	}
	private int size;
	Node root;
	public BT()
	{
		size=0;
		root=null;
	}
	public int getsize()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return size==0;
	}
	public void  add(Node node,E e, boolean left)
	{
		if(node==null)
		{
			root=new Node(e);
			return;
		}
		if(left)
		{
			if(node.left==null)
			{
				node.left=new Node(e);
				size++;
			}
			else
			{
				throw new IllegalArgumentException("error");
			}
		}
		else
		{
			if(node.right==null)
			{
				node.right=new Node(e);
				size++;
			}
			else
			{
				throw new IllegalArgumentException("error");
			}
		}
	}
	public boolean iscontains(E e)
	{
		return iscontains(root,e);
	}
	private boolean iscontains(Node node,E e)
	{
		if(node==null)
			return false;
		
		if(node.e.equals(e))
			return true;
		return iscontains(node.left,e)||iscontains(node.right,e);
	}
	public void remove(E e)
	{
		root=remove(root,e);
	}
	private Node getleft(Node node)
	{
		if(node==null)
			return null;
		if(node.left==null&&node.right==null)
			return node;
		return getleft(node.left);
	}
	private Node remove(Node node,E e)
	{
		
		if(node==null)
			return null;
		
		if(node.e.equals(e))
		{
			if(node.left==null)
			{
				Node  right=node.right;
				node.right=null;
				size--;
				return right;
			}
			else if(node.right==null)
			{
				Node left=node.left;
				node.left=null;
				size--;
				return left;
			}
			else
			{
				
				Node ret=getleft(node);
				ret.left=node.left;
				ret.right=node.right;
				node.left=node.right=null;
				size--;
				return ret;
			}
			
		}
		if(iscontains(node.left,e))
		{
			node.left=remove(node.left,e);
		}
		if(iscontains(node.right,e))
		{
			node.right=remove(node.right,e);
		}
		
		return node;
	}
	public String findway(E e)
	{
		return findway(root,e);
	}
	private String findway(Node node,E e)
	{
		StringBuilder res=new StringBuilder();
		if(node==null||!iscontains(e))
			return null;
		while(!node.e.equals(e))
		{
			res.append(node.e+"->");
			if(iscontains(node.left,e))
			{
				node=node.left;
				continue;
			}
			if(iscontains(node.right,e))
			{
				node=node.right;
				continue;
			}
		}
		res.append(node.e);
		return res.toString();
	}
	
}