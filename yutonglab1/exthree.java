package dyt;
import java.util.LinkedList;
import java.util.List;
public class exthree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer>l1=new LinkedList<>();
		List<Integer>l2=new LinkedList<>();
		l1.add(1);
		l1.add(2);
		l1.add(4);
		l1.add(9);
		l2.add(2);
		l2.add(4);
		l2.add(6);
		l2.add(10);
		List <Integer>res=method1(l1,l2);
		for(int i=0;i<res.size();i++)
			System.out.print(res.get(i)+" ");
		
		System.out.println();
		
		List<Integer>res2=method2(l1,l2);
		for(int i=0;i<res2.size();i++)
			System.out.print(res2.get(i)+" ");
	}
	public static <E extends Comparable> List<E> method1(List<E> l1,List<E> l2)
	{
		List<E>l3=new LinkedList<>();
		
		int i=0,j=0;
		while(i<l1.size()&&j<l2.size())
		{
			E e1=l1.get(i);
			E e2=l2.get(j);
			if(e1.compareTo(e2)==0)
			{
				l3.add(e1);
				i++;
				j++;
			}
			else if(e1.compareTo(e2)<0)
			{
				i++;
			}
			else
			{
				j++;
			}
			
		}
	 
		return l3;
	}
 
	public static<E extends Comparable> List<E> method2(List<E> l1,List<E> l2)
	{
		List<E>l3=new LinkedList<>();
		
		int i=0,j=0;
		
		while(i<l1.size()&&j<l2.size())
		{
			E e1=l1.get(i);
			E e2=l2.get(j);
			
			if(e1.compareTo(e2)<0)
			{
				
				l3.add(e1);
				i++;
			}
			else if(e1.compareTo(e2)>0)
			{
				l3.add(e2);
				j++;
			}
			else
			{
				l3.add(e1);
				i++;
				j++;
			}
		}
		while(i<l1.size())
		{
		    l3.add(l1.get(i));
		    i++;
		}
		while(j<l2.size())
		{
			 l3.add(l2.get(j));
			  j++;
		}
		
		return l3;
	}
	
	
}
