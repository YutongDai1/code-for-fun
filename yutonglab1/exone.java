package dyt;
import java.util.*;
public class exone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List L=new ArrayList();
		List P=new ArrayList();
		for(int i=0;i<10;i++)
		L.add(i);
		P.add(1);
		P.add(3);
		printLots(L,P);
		
  
	}
	public static void printLots(List<Integer> L,List<Integer> P)
	{
		Iterator l=L.iterator();
		Iterator p=P.iterator();
		int start =0;
		int i;
		int j =0;
		while(l.hasNext()&&p.hasNext())
		{
			i=(int) p.next();
			while(start<i)
			{
				start++;
				j=(int )l.next();
				
			}
			System.out.println(j);
			
		}
		
	}

}
