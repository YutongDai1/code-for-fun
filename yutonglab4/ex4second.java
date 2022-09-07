package dyt;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.HashSet;
public class ex4second {

	public static void main(String []a)
	{
		//TreeMap
		int []nums= {4371, 1223, 6173, 4199, 4344, 9679, 1989};
		TreeMap<Integer,Integer>map=new TreeMap<>();
		for(int i=0;i<nums.length;i++)
			map.put(i,nums[i]);
		map.remove(0);
		map.remove(3);
		for(int i:map.keySet())
			System.out.print(map.get(i)+"\t");
		
		System.out.println();
		//TreeSet
		TreeSet<Integer>set=new TreeSet<>();
		for(int i=0;i<nums.length;i++)
			set.add(nums[i]);
		set.remove(4371);
		set.remove(9679);
		for(int i:set)
			System.out.print(i+"\t");
		
		System.out.println();
		//HashSet
		HashSet<Integer>hash=new HashSet<>();
		for(int i=0;i<nums.length;i++)
			hash.add(nums[i]);
		hash.remove(4371);
		hash.remove(9679);
		for(int i: hash)
			System.out.print(i+"\t");
		
		System.out.println();
		//HashMap
		HashMap<Integer,Integer>hashmap=new HashMap<>();
		for(int i=0;i<nums.length;i++)
			hashmap.put(i,nums[i]);
		hashmap.remove(0);
		hashmap.remove(3);
		for(int i:hashmap.keySet())
			System.out.print(hashmap.get(i)+"\t");
	}
	
	
}
