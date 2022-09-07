package dyt;
public class ex3three {
	public static void main(String []a)
	{
		myavl<Integer>avl=new myavl<>();
		avl.add(3);
		avl.add(1);
		avl.add(4);
		avl.add(6);
		avl.add(9);
		avl.add(7);
		avl.add(5);
		avl.add(2);
		avl.getleafnode();//AVL 树的叶节点个数
		
		System.out.println();
		
		avl.preorder();
		System.out.println();
		avl.inorder();
		System.out.println();
		avl.postorder();
		
	}

}
class myavl<E extends Comparable<E>>
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
	public myavl()
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
	
	public void add(E e)
	{
		root=add(root,e);
	}
	private Node add(Node node,E e)
	{
		if(node==null)
		{
			size++;
			return new Node(e);
		}
		if(e.compareTo(node.e)<0)
		{
			node.left=add(node.left,e);
		}
		else if(e.compareTo(node.e)>0)
		{
			node.right=add(node.right,e);
		}
		else {
			node.e=e;
		}
		node.height=1+Math.max(getheight(node.left), getheight(node.right));
		
		int factor=getbalancefactor(node);
		if(factor>1&&getbalancefactor(node.left)>=0)
			return rightrotate(node);
		if(factor<-1&&getbalancefactor(node.right)<=0)
			return leftrotate(node);
		if(factor>1&&getbalancefactor(node.left)<0)
		{
			node.left=leftrotate(node.left);
			return rightrotate(node);
		}
		if(factor<-1&&getbalancefactor(node.right)>0)
		{
			node.right=rightrotate(node.right);
			return leftrotate(node);
		}
		
		
		return node;
	}
	public void preorder()
	{
		preorder(root);
	}
	private void preorder(Node node)
	{
		if(node==null)
			return;
		System.out.println(node.e);
		preorder(node.left);
		preorder(node.right);
	}
	public void inorder()
	{
		inorder(root);
	}
	private void inorder(Node node)
	{
		if(node==null)
			return;
		
		inorder(node.left);
		System.out.println(node.e);
		inorder(node.right);
	}
	public void postorder()
	{
		postorder(root);
	}
	private void postorder(Node node)
	{
		if(node==null)
			return;
		
		postorder(node.left);
		postorder(node.right);
		System.out.println(node.e);
	}
     private void leafnode(Node node)
	{
		if(node==null)
			return;
		
		if(node.left==null&&node.right==null)
			leafnode++;
		
		leafnode(node.left);
		leafnode(node.right);
		
	}
     public void getleafnode()
     {
    	 leafnode(root);
    	 System.out.println(leafnode);
     }
	

	
}