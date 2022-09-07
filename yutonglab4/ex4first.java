package dyt;
import java.util.List;
import java.util.LinkedList;
public class ex4first {

	public static void main(String []a)
	{
	int []nums={4371, 1223, 6173, 4199, 4344, 9679, 1989};
	SeparateChainingHashTable<Integer>hash1=new SeparateChainingHashTable<>(11);
	for(int i=0;i<nums.length;i++)
		hash1.insert(nums[i]);
	hash1.show();//用于测试
	
	System.out.println();
	
	QuadraticProbingHashTable<Integer>hash2=new QuadraticProbingHashTable<>(11);
	for(int i=0;i<nums.length;i++)
		hash2.insert(nums[i]);
	hash2.show();//用于测试
	
	System.out.println();
	
	QuadraticProbingHashTable2<Integer>hash3=new QuadraticProbingHashTable2<>(11);
	for(int i=0;i<nums.length;i++)
		hash3.insert(nums[i]);
	hash3.show();//用于测试
	
System.out.println();
	
	QuadraticProbingHashTable3<Integer>hash4=new QuadraticProbingHashTable3<>(11);
	for(int i=0;i<nums.length;i++)
		hash4.insert(nums[i]);
	hash4.show();//用于测试
	}
}

//分离链接散列表
class SeparateChainingHashTable<E>
{

	
	public SeparateChainingHashTable(int size)
	{
	
		lists =new LinkedList[size];
		for(int i=0;i<lists.length;i++)
			lists[i]=new LinkedList<E>();
	}
	private List<E>[] lists;
	private int size;
	public void insert(E e)
	{
		List<E>list=lists[myhash(e)];
		if(!list.contains(e))
		{
			list.add(e);
			size++;
		}
	}
	//用于测试
	public void show()
	{
		for(int i=0;i<lists.length;i++)
		{
			System.out.print("第"+i+"行"+"\t");
			for(int j=0;j<lists[i].size();j++)
				System.out.print(lists[i].get(j)+"\t");
		System.out.println();	
		}
	}
	public void remove(E e)
	{
		List<E>list=lists[myhash(e)];
		if(list.contains(e))
		{
			list.remove(e);
			size--;
		}
	}
	public boolean contains(E e)
	{
		List<E>list=lists[myhash(e)];
		return list.contains(e);
	}
	public void makeEmpty()
	{
		for(int i=0;i<lists.length;i++)
			lists[i].clear();
		size=0;
	}
	public int myhash(E e)
	{
		int hashval=e.hashCode();
		hashval%=lists.length;
		if(hashval<0)
			hashval+=lists.length;
		return hashval;
	}
	
}

//使用线性探测的开放地址散列表，f(i)=i，
class QuadraticProbingHashTable<E>
{
	
	
	private class HashEntry<E>
	{
		public E e;
		public boolean isActive;
		public HashEntry(E e,boolean isActive)
		{
			this.e=e;
			this.isActive=isActive;
		}
		public HashEntry(E e)
		{
			this(e,true);
		}
	}
	private HashEntry<E> []array;
	private int cursize;
	private void allocateArray(int arraysize)
	{
		array=new HashEntry[arraysize];
	}
	public QuadraticProbingHashTable(int size)
	{
		 allocateArray(size);
		 makeEmpty();
	}
	private boolean isActive(int currentPos) 
	{
		return array[currentPos]!=null&&array[currentPos].isActive;
	}
	private int findPos(E e)
	{
		int currentpos =myhash(e);
		while(array[currentpos]!=null&&!array[currentpos].e.equals(e))
		{
			currentpos++;
			if(currentpos>=array.length)
				currentpos-=array.length;
		}
		return currentpos;
	}
	
	
	public void makeEmpty()
	{
		for(int i=0;i<array.length;i++)
			array[i]=null;
		cursize=0;
	}
	public void insert(E e)
	{
		int currentpos=findPos(e);
		if(isActive(currentpos))
			return;
		array[currentpos]=new HashEntry<>(e,true);
		
	}
	
	public void remove(E e)
	{
		int currentpos=findPos(e);
		if(isActive(currentpos))
			array[currentpos].isActive=false;
	}
	public boolean contains(E e)
	{
		int currentpos=findPos(e);
		return isActive(currentpos);
		
	}
	public int myhash(E e)
	{
		int hashval=e.hashCode();
		hashval%=array.length;
		if(hashval<0)
		hashval+=array.length;
		return hashval;
	}
	public void show()
	{
		for(int i=0;i<array.length;i++)
		{
			if(isActive(i))
		System.out.print(array[i].e+"\t");
		}
	}
	
}

//使用二次探测的开放地址散列表，f(i) = f(i-1) + 2i – 1
class QuadraticProbingHashTable2<E>
{
	
	
	private class HashEntry<E>
	{
		public E e;
		public boolean isActive;
		public HashEntry(E e,boolean isActive)
		{
			this.e=e;
			this.isActive=isActive;
		}
		public HashEntry(E e)
		{
			this(e,true);
		}
	}
	private HashEntry<E> []array;
	private int cursize;
	private void allocateArray(int arraysize)
	{
		array=new HashEntry[arraysize];
	}
	public QuadraticProbingHashTable2(int size)
	{
		 allocateArray(size);
		 makeEmpty();
	}
	private boolean isActive(int currentPos) 
	{
		return array[currentPos]!=null&&array[currentPos].isActive;
	}

	
	public void makeEmpty()
	{
		for(int i=0;i<array.length;i++)
			array[i]=null;
		cursize=0;
	}
	private int findPos(E e)
	{
		int offset=1;
		int currentpos =myhash(e);
		while(array[currentpos]!=null&&!array[currentpos].e.equals(e))
		{
			currentpos+=offset;
			offset+=2;
			if(currentpos>=array.length)
				currentpos-=array.length;
		}
		return currentpos;
	}
	public void insert(E e)
	{
		int currentpos=findPos(e);
		if(isActive(currentpos))
			return;
		array[currentpos]=new HashEntry<>(e,true);
		
	}
	public void remove(E e)
	{
		int currentpos=findPos(e);
		if(isActive(currentpos))
			array[currentpos].isActive=false;
	}
	public boolean contains(E e)
	{
		int currentpos=findPos(e);
		return isActive(currentpos);
		
	}
	public int myhash(E e)
	{
		int hashval=e.hashCode();
		hashval%=array.length;
		if(hashval<0)
		hashval+=array.length;
		return hashval;
	}
	public void show()
	{
		for(int i=0;i<array.length;i++)
		{
			if(isActive(i))
				System.out.print(array[i].e+"\t");
		}
	}
	
}


//使用双散列的开放地址散列表，h2(x) = 7 – ( X % 7 )，f(i)=i*h2(x)

class QuadraticProbingHashTable3<E>
{
	
	
	private class HashEntry<E>
	{
		public E e;
		public boolean isActive;
		public HashEntry(E e,boolean isActive)
		{
			this.e=e;
			this.isActive=isActive;
		}
		public HashEntry(E e)
		{
			this(e,true);
		}
	}
	private HashEntry<E> []array;
	private int cursize;
	private void allocateArray(int arraysize)
	{
		array=new HashEntry[arraysize];
	}
	public QuadraticProbingHashTable3(int size)
	{
		 allocateArray(size);
		 makeEmpty();
	}
	private boolean isActive(int currentPos) 
	{
		return array[currentPos]!=null&&array[currentPos].isActive;
	}
	private int findPos(E e)
	{
		int count=1;
		int currentpos =myhash(e);
		while(array[currentpos]!=null&&!array[currentpos].e.equals(e))
		{
			currentpos+=count*hash2(e);
			count++;
			if(currentpos>=array.length)
				currentpos-=array.length;
		}
		return currentpos;
	}
	
	
	public void makeEmpty()
	{
		for(int i=0;i<array.length;i++)
			array[i]=null;
		cursize=0;
	}
	public void insert(E e)
	{
		int currentpos=findPos(e);
		if(isActive(currentpos))
			return;
		array[currentpos]=new HashEntry<>(e,true);
		
	}
	public void remove(E e)
	{
		int currentpos=findPos(e);
		if(isActive(currentpos))
			array[currentpos].isActive=false;
	}
	public boolean contains(E e)
	{
		int currentpos=findPos(e);
		return isActive(currentpos);
		
	}
	
	//第二个散列函数
	private int hash2(E e)
	{
		int hashval=e.hashCode();
		hashval%=7;
		hashval=7-hashval;
		return hashval;
	}
	public int myhash(E e)
	{
		int hashval=e.hashCode();
		hashval%=array.length;
		if(hashval<0)
		hashval+=array.length;
		return hashval;
	}
	public void show()
	{
		for(int i=0;i<array.length;i++)
		{
			if(isActive(i))
		System.out.print(array[i].e+"\t");
		}
	}
	
}

//使用再散列的开放地址散列表
class QuadraticProbingHashTable4<E>
{
	
	
	private class HashEntry<E>
	{
		public E e;
		public boolean isActive;
		public HashEntry(E e,boolean isActive)
		{
			this.e=e;
			this.isActive=isActive;
		}
		public HashEntry(E e)
		{
			this(e,true);
		}
	}
	private HashEntry<E> []array;
	private int cursize;
	private void allocateArray(int arraysize)
	{
		array=new HashEntry[arraysize];
	}
	public QuadraticProbingHashTable4(int size)
	{
		 allocateArray(size);
		 makeEmpty();
	}
	
	//再散列
	private void rehash()
	{
		HashEntry<E>[] oldarray=array;
		allocateArray(2*array.length);
		for(int i=0;i<oldarray.length;i++)
		{
			if(oldarray[i]!=null&&oldarray[i].isActive)
				insert(oldarray[i].e);
		}
	}
	private boolean isActive(int currentPos) 
	{
		return array[currentPos]!=null&&array[currentPos].isActive;
	}
	private int findPos(E e)
	{
		int currentpos =myhash(e);
		while(array[currentpos]!=null&&!array[currentpos].e.equals(e))
		{
			currentpos++;
			if(currentpos>=array.length)
				currentpos-=array.length;
		}
		return currentpos;
	}
	
	
	public void makeEmpty()
	{
		for(int i=0;i<array.length;i++)
			array[i]=null;
		cursize=0;
	}
	public void insert(E e)
	{
		int currentpos=findPos(e);
		if(isActive(currentpos))
			return;
		array[currentpos]=new HashEntry<>(e,true);
		
	}
	public void remove(E e)
	{
		int currentpos=findPos(e);
		if(isActive(currentpos))
			array[currentpos].isActive=false;
	}
	public boolean contains(E e)
	{
		int currentpos=findPos(e);
		return isActive(currentpos);
		
	}
	
	public int myhash(E e)
	{
		int hashval=e.hashCode();
		hashval%=array.length;
		if(hashval<0)
		hashval+=array.length;
		return hashval;
	}
	public void show()
	{
		for(int i=0;i<array.length;i++)
		{
			if(isActive(i))
		System.out.print(array[i].e+"\t");
		}
	}
	
}
