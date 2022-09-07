package dyt;

public class ex3one {

	public static void main(String []a)
	{
		mybst<Integer>bst=new mybst<>();
		bst.add(3);
		bst.add(1);
		bst.add(4);
		bst.add(6);
		bst.add(9);
		bst.add(7);
		bst.add(5);
		bst.add(2);
		bst.getleafnode();//输出叶节点个数
		System.out.println();
		bst.preorder();
		System.out.println();
		bst.inorder();
		System.out.println();
		bst.postorder();
		
	}
	
}
class mybst<E extends Comparable<E>>
{
	private class Node
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
	private Node root;
	private int leafnode=0;
	public mybst()
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
	public void add(E e)
	{
		if(size==0)
		{
			root=new Node(e);
			size++;
		}
			
		else
			add(root,e);
	}
	private void add(Node root,E e)
	{
		if(root.e.compareTo(e)>0&&root.left==null)
		{
			size++;
			root.left=new Node(e);
		}
		else if(root.e.compareTo(e)<0&&root.right==null)
		{
			size++;
			root.right=new Node(e);
		}
		else 
		{
			if(root.e.compareTo(e)>0)
				add(root.left,e);
			 if(root.e.compareTo(e)<0)
				add(root.right,e);
		}
	}
	public E max()
	{
		return max(root).e;
	}
	private Node max(Node root)
	{
		if(root.right==null)
			return root;
		else
			return max(root.right);
	}
	public E min()
	{
		return min(root).e;
	}
	private Node min(Node root)
	{
		if(root.left==null)
			return root;
		else
			return min(root.left);
	}
	public Node removemin(Node root)
	{
		if(root.left==null)
		{
			size--;
			return root;
		}
			
		else
			return removemin(root.left);
	}
	public void  remove(E e)
	{
		if(size==0)
			throw new IllegalArgumentException("error");
		remove(root,e);
	}
	private Node remove(Node root,E e)
	{
		
		 if(root.e.compareTo(e)>0)
		{
			return remove(root.left,e);
		}
		else if(root.e.compareTo(e)<0)
		{
			return remove(root.right,e);
		}
		else
		{
			if(root.left==null)
		{
			Node ret=root.right;
			root.right=null;
			size--;
			return ret;
		}
		else if(root.right==null)
		{
			Node ret=root.left;
			root.left=null;
			size--;
			return ret;
		}
		else
		{
			Node successor=min(root.right);
			successor.left=root.left;
			successor.right=removemin(root.right);
			root.left=root.right=null;
			return successor;
		}
			
		}	
		 
	}
	public void preorder()
	{
		preorder( root);
	}
	private  void preorder(Node root)
	{
		if(root==null)
			return ;
		System.out.println(root.e);
		preorder(root.left);
		preorder(root.right);
	}
	public void inorder()
	{
		inorder(root);
	}
	private void inorder(Node root)
	{
		if(root==null)
			return;
		inorder(root.left);
		System.out.println(root.e);
		inorder(root.right);
	}
	public void postorder()
	{
		postorder(root);
	}
	private  void postorder(Node root)
	{
		if(root==null)
			return ;
		postorder(root.left);
		postorder(root.right);
		System.out.println(root.e);
	}
	
	
	private  void leafnode(Node root)
	{
		if(root==null)
			return ;
		if(root.left==null&&root.right==null)
			leafnode++;
		leafnode(root.left);
		leafnode(root.right);
	}
	public void getleafnode()
	{
		leafnode(root);
		System.out.println(leafnode);
	}
}
