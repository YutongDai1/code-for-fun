package dyt7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sort<E extends Comparable<E>> {


	         //1 选择排序
		public static<E>void selectionsort(E []arr)
		{
			
			for(int i=0;i<arr.length;i++)
			{
				E temp;
				int min=i;
				for(int j=i+1;j<arr.length;j++)
				{
					if(((Comparable<E>) arr[j]).compareTo(arr[min])<0)
						min=j;
				}
				temp=arr[min];
				arr[min]=arr[i];
				arr[i]=temp;
			}
			
		}
		
		//2 插入排序
		public static <E>void insertsort(E []arr)
		{
			E temp;
			for(int i=0;i<arr.length;i++)
			{
				for(int j=i+1;j<arr.length&&j>=1&&((Comparable<E>) arr[j-1]).compareTo(arr[j])>0;j--)
				{
					temp=arr[j];
					arr[j]=arr[j-1];
					arr[j-1]=temp;
				}
			}
		}
		
		//3 希尔排序
		public static <E>void shellsort(E []arr)
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
				for(int j=i;j>=h&&((Comparable<E>) arr[j-h]).compareTo(arr[j])>0;j--)
				{
					temp=arr[j];
					arr[j]=arr[j-h];
					arr[j-h]=temp;
				}
			}
			    h/=3;
			}
			
		}
		
		//4 快速排序
		public static <E> void quicksort(E []arr)
		{
			quicksort(arr,0,arr.length-1);
			
		}
		private static <E>void quicksort(E[]arr,int l ,int r)
		{
			
			if(l>=r)
				return;
			
			int j=partition(arr,l, r);
			quicksort(arr,l,j-1);
			quicksort(arr,j+1,r);
			
		}
		
		private static <E> int partition(E[]arr,int l,int r)
		{
			E v=arr[l];
			E temp;
			int j=l;
			for(int i=l+1;i<=r;i++)
			{
				if(((Comparable<E>) arr[i]).compareTo(v)<0)
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
		
		//5冒泡排序
		public static <E>void bubblesort(E []arr)
		{
			E temp;
			for(int i=0;i<arr.length-1;i++)
			{
				for(int j=0;j<arr.length-1-i;j++)
				{
					if(((Comparable<E>) arr[j]).compareTo(arr[j+1])>0)
					{
						temp=arr[j];
						arr[j]=arr[j+1];
						arr[j+1]=temp;
					}
				}
			}
		}
		
		
		//6归并排序
		public static <E>void mergesort(E []arr)
		{
			mergesort(arr,0,arr.length-1);
		}
		private static <E>void mergesort(E []arr,int l,int r)
		{
			if(l>=r)
				return ;
			
			int mid=l+(r-l)/2;
			
			mergesort(arr,l,mid);
			mergesort(arr,mid+1,r);
			merge(arr,l,mid,r);
			
		}
		private static <E>void merge(E[] arr,int l,int mid,int r)
		{
			Comparable[] aux = (Comparable[]) Arrays.copyOfRange(arr, l, r+1);
			for(int i=l;i<=r;i++)
				aux[i-l]=(Comparable) arr[i];
			
			int i=l;
			int j=mid+1;
			for(int k=l;k<=r;k++)
			{
				if(i>mid)
				{
					arr[k]=(E) aux[j-l];
					j++;
				}
				else if(j>r)
				{
					arr[k]=(E) aux[i-l];
					i++;
				}
				else if(aux[i-l].compareTo(aux[j-l])<0)
					
				{
					arr[k]=(E) aux[i-l];
					i++;
				}
				else
				{
					arr[k]=(E) aux[j-l];
					j++;
				}
			}
		}
		
		//7桶排序
		public static <E>void bucketsort(Integer[] num2)
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
		
		//8基数排序
		private static class Queue<E>
		{
			int data[];
			int front;
			int rear;
		}
		public static <E>void basesort(Integer[] num)
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
		private static  int getmax(Integer []arr)
		{
			int max=0;
			for(int i=1;i<arr.length;i++)
			{
				if(arr[i]>arr[max])
					max=i;
			}
			return arr[max];
		}
		
		private static int getcount(int num) //取最大位
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
