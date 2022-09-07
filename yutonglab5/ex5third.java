package dyt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
public class ex5third {

	public static void main(String []a)
	{
		sort<Integer> s=new sort<>();
		Integer []num=new Integer[10000];
		Integer []num2=new Integer[10000];
		Integer []num3=new Integer[10000];
		Integer []num4=new Integer[10000];
		Integer []num5=new Integer[10000];
		Integer []num6=new Integer[10000];
		Integer []num7=new Integer[10000];
		Integer []num8=new Integer[10000];
		Random r=new Random();
		for(int i=0 ; i<10000;i++)
		{
			int ran1=r.nextInt(100000);
			num[i]=ran1;
			num2[i]=ran1;
			num3[i]=ran1;
			num4[i]=ran1;
			num5[i]=ran1;
			num6[i]=ran1;
			num7[i]=ran1;
			num8[i]=ran1;
		}
		
		long startTime = System.currentTimeMillis();
		s.selectionsort(num);
		long endTime = System.currentTimeMillis(); 
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		
		 startTime = System.currentTimeMillis();
			s.basesort(num2);
			 endTime = System.currentTimeMillis(); 
			System.out.println("程序运行时间："+(endTime-startTime)+"ms");
			
		 startTime = System.currentTimeMillis();
		s.insertsort(num3);
		 endTime = System.currentTimeMillis(); 
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		
		 startTime = System.currentTimeMillis();
		s.bubblesort(num4);
		 endTime = System.currentTimeMillis(); 
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		
		 startTime = System.currentTimeMillis();
		s.bucketsort(num5);
		 endTime = System.currentTimeMillis(); 
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		
		 startTime = System.currentTimeMillis();
		s.mergesort(num6);
		endTime = System.currentTimeMillis(); 
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		
		 startTime = System.currentTimeMillis();
		s.quicksort(num7);
		 endTime = System.currentTimeMillis(); 
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		
		 startTime = System.currentTimeMillis();
		s.shellsort(num8);
		 endTime = System.currentTimeMillis(); 
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		
	}
}
class sort<E extends Comparable<E>>
{
	
	//选择排序
	public void selectionsort(E []arr)
	{
		
		for(int i=0;i<arr.length;i++)
		{
			E temp;
			int min=i;
			for(int j=i+1;j<arr.length;j++)
			{
				if(arr[j].compareTo(arr[min])<0)
					min=j;
			}
			temp=arr[min];
			arr[min]=arr[i];
			arr[i]=temp;
		}
		
	}
	
	//插入排序
	public void insertsort(E []arr)
	{
		E temp;
		for(int i=0;i<arr.length;i++)
		{
			for(int j=i+1;j<arr.length&&j>=1&&arr[j-1].compareTo(arr[j])>0;j--)
			{
				temp=arr[j];
				arr[j]=arr[j-1];
				arr[j-1]=temp;
			}
		}
	}
	
	//希尔排序
	public void shellsort(E []arr)
	{
		int h=1;
		while(h<arr.length/3)
		{
			h=3*h+1;
		}
		while(h>=1)
		{
		for(int i=h;i<arr.length;i++)
		{
			E temp;
			for(int j=i;j>=h&&arr[j-h].compareTo(arr[j])>0;j--)
			{
				temp=arr[j];
				arr[j]=arr[j-h];
				arr[j-h]=temp;
			}
		}
		    h/=3;
		}
		
	}
	
	//两个冒泡排序
	public void bubblesort(E []arr)
	{
		E temp;
		for(int i=0;i<arr.length-1;i++)
		{
			for(int j=0;j<arr.length-1-i;j++)
			{
				if(arr[j].compareTo(arr[j+1])>0)
				{
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
	}
	public void bubblesort2(E []arr)
	{
		E temp;
		boolean swapped=true;
		while(swapped)
		{
			int n=arr.length;
			swapped=false;
			for(int i=1;i<n;i++)
			{
				if(arr[i-1].compareTo(arr[i])>0)
				{
					temp=arr[i];
					arr[i]=arr[i-1];
					arr[i-1]=temp;
					swapped=true;
				}
			}
			n--;
		}
	}
	
	//归并排序
	public void mergesort(E []arr)
	{
		mergesort(arr,0,arr.length-1);
	}
	private void mergesort(E []arr,int l,int r)
	{
		if(l>=r)
			return ;
		
		int mid=l+(r-l)/2;
		
		mergesort(arr,l,mid);
		mergesort(arr,mid+1,r);
		merge(arr,l,mid,r);
		
	}
	private void merge(Comparable[] arr,int l,int mid,int r)
	{
		Comparable[] aux = Arrays.copyOfRange(arr, l, r+1);
		for(int i=l;i<=r;i++)
			aux[i-l]=arr[i];
		
		int i=l;
		int j=mid+1;
		for(int k=l;k<=r;k++)
		{
			if(i>mid)
			{
				arr[k]=aux[j-l];
				j++;
			}
			else if(j>r)
			{
				arr[k]=aux[i-l];
				i++;
			}
			else if(aux[i-l].compareTo(aux[j-l])<0)
				
			{
				arr[k]=aux[i-l];
				i++;
			}
			else
			{
				arr[k]=aux[j-l];
				j++;
			}
		}
	}
	
	//快速排序
	public void quicksort(E []arr)
	{
		quicksort(arr,0,arr.length-1);
		
	}
	private void quicksort(E[]arr,int l ,int r)
	{
		
		if(l>=r)
			return;
		
		int j=partition(arr,l, r);
		quicksort(arr,l,j-1);
		quicksort(arr,j+1,r);
		
	}
	
	private int partition(E[]arr,int l,int r)
	{
		E v=arr[l];
		E temp;
		int j=l;
		for(int i=l+1;i<=r;i++)
		{
			if(arr[i].compareTo(v)<0)
			{
				temp=arr[i];
				arr[i]=arr[++j];
				arr[j]=temp;
			}
			
		}
		temp=arr[j];
		arr[j]=arr[l];
		arr[l]=temp;
				
		return j;	
	}
	
	//桶排序
	public void bucketsort(Integer[] num2)
	{
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		int count=0;
		for(int i=0;i<num2.length;i++)
		{
			max=Math.max(max, num2[i]);
			min=Math.min(min, num2[i]);
		}
		
		int bucketnum=(max-min)/num2.length+1;
		ArrayList<ArrayList<Integer>> list=new ArrayList<>();
		for(int i=0;i<bucketnum;i++)
		{
			list.add(new ArrayList<Integer>());
		}
		
		for(int i=0;i<num2.length;i++)
		{
			int num=(num2[i]-min)/num2.length;
			list.get(num).add(num2[i]);
		}
		for(int i=0;i<list.size();i++)
		{
			Collections.sort(list.get(i));
		}
		for(int i=0;i<list.size();i++)
		{
			for(int j=0;j<list.get(i).size();j++)
				num2[count++]=list.get(i).get(j);
		}
			
	}
	
	//基数排序
	private class Queue<E>
	{
		int data[];
		int front;
		int rear;
	}
	public void basesort(Integer[] num)
	{
		Queue []queues=new Queue[10];
		int count=getcount(getmax(num));
		
		for(int i=0;i<10;i++)
		{
			queues[i]=new Queue();
			queues[i].data=new int[num.length];
			queues[i].front=queues[i].rear=-1;
		}
		int m=1;//m控制取第几位，从各位开始，直到count
		while(count>0)
		{
			for(int i=0;i<num.length;i++)
			{
				int t=num[i]/m%10;
				queues[t].data[++queues[t].rear]=num[i];
			}
			int n=0;
			for(int i=0;i<10;i++)
			{
				while(queues[i].front!=queues[i].rear)
				{
					num[n++]=queues[i].data[++queues[i].front];
				}
				queues[i].front=queues[i].rear=-1;
			}
			m*=10;
			count--;
		}
		
		
	}
	private int getmax(Integer []arr)
	{
		int max=0;
		for(int i=1;i<arr.length;i++)
		{
			if(arr[i]>arr[max])
				max=i;
		}
		return arr[max];
	}
	
	private int getcount(int num) //取最大位
	{
		int count=0;
		
		while(num>0)
		{
			count++;
			num/=10;
		}
		return count;
	}
}