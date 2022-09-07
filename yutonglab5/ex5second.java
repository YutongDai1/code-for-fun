package dyt;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class ex5second {

	public static void main(String []a)
	{
		Integer []arr1= {3,7,8,2,9};
		Integer []arr2={10,12,6,1,3};
		minheapB <Integer>heap1=new minheapB<>(arr1);
		minheapB <Integer>heap2=new minheapB<>(arr2);
		minheapB <Integer>heap = new minheapB<>();
		heap=heap.merge(heap1, heap2);
		heap.show();//作为测试
		System.out.println();
		System.out.println(heap.findmin());//输出堆顶值
		System.out.print(heap.findnode(7).e);//位置从1开始计数
	}
}
class minheapB<E extends Comparable<E>>
{
	public class Node
	{
		Node left;
		Node right;
		Node parent;
		E e;
		public Node(Node left,Node right)
		{
			this.left=left;
			this.right=right;
			parent=null;
		}
		public Node()
		{
			this(null,null);
		}
		public Node(E e)
		{
			this.e=e;
			left=right=parent=null;
		}
	}
	private Node root;
	public minheapB()
	{
		root=null;
	}
	
	
	
	
	//生成一个完全二叉堆
	public minheapB(E []arr)
	{
		if(arr.length==0)
			return;
		if(arr.length==1)
			root=new Node(arr[0]);
		
		List<Node>list=new ArrayList<>();
		for(int i=0;i<arr.length;i++)
			list.add(new Node(arr[i]));
		
		int temp=0;
		while(temp<arr.length/2)
		{
			if(2*temp+1<arr.length)
			{
				list.get(temp).left=list.get(2*temp+1);
				list.get(2*temp+1).parent=list.get(temp);
				siftup(list.get(2*temp+1));
				
			}
			if(2*temp+2<arr.length)
			{
				list.get(temp).right=list.get(2*temp+2);
				list.get(2*temp+2).parent=list.get(temp);
				siftup(list.get(2*temp+2));
				
			}
			temp++;
			
		}
		root=list.get(0);
	}
	
	private void siftup(Node node)
	{
		if(node==null||node.parent==null)
			return;
		while(node.parent!=null)
		{
		if(node.e.compareTo(node.parent.e)<0)
		{
			E temp=node.e;
			node.e=node.parent.e;
			node.parent.e=temp;
			node=node.parent;
		}
		else
			break;
		}
	}
	
	//合并两个堆
	public minheapB merge(minheapB h1,minheapB h2)
	{
		if(h1==null)
			return h2;
		if(h2==null)
			return h1;
		
		if(h1.findmin().compareTo(h2.findmin())<0)
		{	
			   Node right1=h1.root.right;
			   Node right2=h2.root.right;
			   Node node1=h1.root;
			    Node node2=h2.root;
			    node1.right=node2;
			    node2.parent=node1;
			    
			while(right1!=null&&right2!=null)
			{
			   
			    if(right1.e.compareTo(right2.e)<0)
			    {
			    	node2.right=right1;
			    	right1.parent=node2;
			    	node2=node2.right;
			    	right1=right1.right;
			    	
			    }
			    else
			    {
			    	node2.right=right2;
			    	right2.parent=node2;
			    	node2=node2.right;
			    	right2=right2.right;
			    }
			}
			if(right1==null)
				node2.right=right2;
			if(right2==null)
				node2.right=right1;
			
			return h1;
		}
		else 
		{
			  Node right1=h1.root.right;
			   Node right2=h2.root.right;
			   Node node1=h1.root;
			    Node node2=h2.root;
			    node2.right=node1;
			    node1.parent=node2;
			    
			while(right1!=null&&right2!=null)
			{
			   
			    if(right1.e.compareTo(right2.e)<0)
			    {
			    	node1.right=right1;
			    	right1.parent=node1;
			    	node1=node1.right;
			    	right1=right1.right;
			    	
			    }
			    else
			    {
			    	node1.right=right2;
			    	right2.parent=node1;
			    	node1=node1.right;
			    	right2=right2.right;
			    }
			}
			if(right1==null)
				node1.right=right2;
			if(right2==null)
				node1.right=right1;
			
			return h2;
			
		}
		
	}
	public E findmin()
	{
		if(root==null)
			return null;
		return root.e;
	}
	
	//用层序遍历来找到对应位置的树节点
	public Node findnode(int num)
	{
		int i=0;
		Queue<Node>queue=new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty())
		{
			Node node=queue.remove();
			i++;
			if(i==num)
				return node;
			queue.add(node.left);
			queue.add(node.right);
		}
			
		return null;
		
	}
	//用于测试
	public void show()
	{
		show(root);
	}
	private void show(Node root)
	{
		if(root==null)
			return;
		show(root.left);
		System.out.print(root.e+"\t");
		show(root.right);	
	}
	
}