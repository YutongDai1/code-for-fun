package dyt7;
 import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
public class dyt7<E extends Comparable<E>> {

	
		
		public static void main(String[] args)
		{
		
		System.out.println("请输入要随机产生的数字个数");
			Scanner in=new Scanner(System.in);
			
			int x;                                            //设置的数字个数
			
			x=in.nextInt();
			
			try 
			{
				File file = new File("D:\\sort\\randomnum.txt");			
				if(!file.exists())
				{	                                         //如果不存在data.txt文件则创建
					file.createNewFile();
					System.out.println("data.txt创建完成");				
				}
				FileWriter fw = new FileWriter(file);		  //创建文件写入
				BufferedWriter bw = new BufferedWriter(fw);
				
				//产生随机数据，写入文件
				Random random = new Random();
				for(int j=0;j<x;j++)
				{	
					int randint =(random.nextInt(10*x));	//产生0-10*x之间随机数		
					bw.write(String.valueOf(randint));		//写入一个随机数
					bw.newLine();		                    //新的一行
				}
				bw.close();
				fw.close();
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			System.out.println("请输入要比较的排序算法序号 并且输入数字0作为确实键    ***注意：最多只能比较8组排序 超出8组将会强制执行***");
			
			System.out.print("1 选择排序\n"
					+ "2 插入排序\n"
					+ "3 希尔排序\n"
					+ "4 快速排序\n"
					+ "5 冒泡排序\n"
					+ "6 归并排序\n"
					+ "7 桶排序\n"
					+ "8 基数排序\n"
					);
			
			int s;
			int sum=0;
			Stack<Integer>list=new Stack<>();
			s=in.nextInt();
			while(s!=0)
			{
				sum++;
				list.add(s);
				if(sum==8)
				{
					System.out.println("达到最大数量，强制执行");
					break;
				}
				s=in.nextInt();
				
				
			}
			
			System.out.println("请等待......");
			Integer [] num=new Integer[x];
			Integer [] num2=new Integer[x];
			Integer [] num3=new Integer[x];
			Integer [] num4=new Integer[x];
			Integer [] num5=new Integer[x];
			Integer [] num6=new Integer[x];
			Integer [] num7=new Integer[x];
			Integer [] num8=new Integer[x];
			String tmp;

		      File file = new File("D:\\sort\\randomnum.txt");
		      BufferedReader br = null;
		      
		
			
			while(!list.isEmpty())
			{
				int temp;
				temp=list.pop();
				switch(temp)
				{
				
				case 1:
				{
					int i=0;
					try {
						br = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e1) {
					
						e1.printStackTrace();
					}
					try {
						while((tmp = br.readLine()) != null){
							num[i++]=Integer.parseInt(tmp);
							}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 long startTime = System.currentTimeMillis();
					 Sort.selectionsort(num);
					long endTime = System.currentTimeMillis(); 
					long res=endTime-startTime;
					
					try 
					{
						
						File file1 = new File("D:\\sort\\selectsort.txt");			
						 
						FileWriter fw = new FileWriter(file1);		//创建文件写入
						
						fw.write("选择排序程序对"+x+"个随机数字排序运行时间："+res+"ms"+"\n");
						System.out.println("选择排序程序对"+x+"个随机数字排序运行时间："+res+"ms");
						for(int j=0;j<x;j++)
						{	
							
							fw.write(num[j]+"\n");		
							
						}
						
						fw.close();
						
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}

				
					
				
					
					break;
				}
				case 2:
				{
					int i=0;
					try {
						br = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e1) {
					
						e1.printStackTrace();
					}
					try {
						while((tmp = br.readLine()) != null){
							num2[i++]=Integer.parseInt(tmp);
							}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 long startTime = System.currentTimeMillis();
					 Sort.insertsort(num2);
					long endTime = System.currentTimeMillis(); 
					long res=endTime-startTime;
					
					try 
					{
						
						File file1 = new File("D:\\sort\\insertsort.txt");			
						 
						FileWriter fw = new FileWriter(file1);		//创建文件写入
						
						fw.write("插入排序程序对"+x+"个随机数字运行时间："+res+"ms"+"\n");	
						System.out.println("插入排序程序对"+x+"个随机数字排序运行时间："+res+"ms");
						for(int j=0;j<x;j++)
						{	
							
							fw.write(num2[j]+"\n");		
							
						}
						
						fw.close();
						
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}

				
					
				
					
					break;
				}
				case 3:
				{
					int i=0;
					try {
						br = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e1) {
					
						e1.printStackTrace();
					}
					try {
						while((tmp = br.readLine()) != null){
							num3[i++]=Integer.parseInt(tmp);
							}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 long startTime = System.currentTimeMillis();
					 Sort.shellsort(num3);
					long endTime = System.currentTimeMillis(); 
					long res=endTime-startTime;
					
					try 
					{
						
						File file1 = new File("D:\\sort\\shellsort.txt");			
						 
						FileWriter fw = new FileWriter(file1);		//创建文件写入
						
						fw.write("希尔排序程序对"+x+"个随机数字运行时间："+res+"ms"+"\n");	
						System.out.println("希尔排序程序对"+x+"个随机数字排序运行时间："+res+"ms");
						for(int j=0;j<x;j++)
						{	
							
							fw.write(num3[j]+"\n");		
							
						}
						
						fw.close();
						
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}

				
					
				
					
					break;
				}
				case 4:
				{
					int i=0;
					try {
						br = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e1) {
					
						e1.printStackTrace();
					}
					try {
						while((tmp = br.readLine()) != null){
							num4[i++]=Integer.parseInt(tmp);
							}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 long startTime = System.currentTimeMillis();
					 Sort.quicksort(num4);
					long endTime = System.currentTimeMillis(); 
					long res=endTime-startTime;
					
					try 
					{
						
						File file1 = new File("D:\\sort\\quicksort.txt");			
						 
						FileWriter fw = new FileWriter(file1);		//创建文件写入
						
						fw.write("快速排序程序对"+x+"个随机数字运行时间："+res+"ms"+"\n");	
						System.out.println("快速排序程序对"+x+"个随机数字排序运行时间："+res+"ms");
						for(int j=0;j<x;j++)
						{	
							
							fw.write(num4[j]+"\n");		
							
						}
						
						fw.close();
						
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}

				
					
				
					
					break;
				}
				case 5:
				{
					int i=0;
					try {
						br = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e1) {
					
						e1.printStackTrace();
					}
					try {
						while((tmp = br.readLine()) != null){
							num5[i++]=Integer.parseInt(tmp);
							}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 long startTime = System.currentTimeMillis();
					 Sort.bubblesort(num5);
					long endTime = System.currentTimeMillis(); 
					long res=endTime-startTime;
					
					try 
					{
						
						File file1 = new File("D:\\sort\\bubblesort.txt");			
						 
						FileWriter fw = new FileWriter(file1);		//创建文件写入
						
						fw.write("冒泡排序程序对"+x+"个随机数字运行时间："+res+"ms"+"\n");
						System.out.println("冒泡排序程序对"+x+"个随机数字排序运行时间："+res+"ms");
						for(int j=0;j<x;j++)
						{	
							
							fw.write(num5[j]+"\n");		
							
						}
						
						fw.close();
						
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}

				
					
				
					
					break;
				}
				case 6:
				{
					int i=0;
					try {
						br = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e1) {
					
						e1.printStackTrace();
					}
					try {
						while((tmp = br.readLine()) != null){
							num6[i++]=Integer.parseInt(tmp);
							}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 long startTime = System.currentTimeMillis();
					 Sort.mergesort(num6);
					long endTime = System.currentTimeMillis(); 
					long res=endTime-startTime;
					
					try 
					{
						
						File file1 = new File("D:\\sort\\mergesort.txt");			
						 
						FileWriter fw = new FileWriter(file1);		//创建文件写入
						
						fw.write("归并排序程序对"+x+"个随机数字运行时间："+res+"ms"+"\n");	
						System.out.println("归并排序程序对"+x+"个随机数字排序运行时间："+res+"ms");
						for(int j=0;j<x;j++)
						{	
							
							fw.write(num6[j]+"\n");		
							
						}
						
						fw.close();
						
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}

				
					
				
					
					break;
				}
				case 7:
				{
					int i=0;
					try {
						br = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e1) {
					
						e1.printStackTrace();
					}
					try {
						while((tmp = br.readLine()) != null){
							num7[i++]=Integer.parseInt(tmp);
							}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 long startTime = System.currentTimeMillis();
					 Sort.bucketsort(num7);
					long endTime = System.currentTimeMillis(); 
					long res=endTime-startTime;
					
					try 
					{
						
						File file1 = new File("D:\\sort\\bucketsort.txt");			
						 
						FileWriter fw = new FileWriter(file1);		//创建文件写入
						
						fw.write("桶排序程序对"+x+"个随机数字运行时间："+res+"ms"+"\n");	
						System.out.println("桶排序程序对"+x+"个随机数字排序运行时间："+res+"ms");
						for(int j=0;j<x;j++)
						{	
							
							fw.write(num7[j]+"\n");		
							
						}
						
						fw.close();
						
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}

				
					
				
					
					break;
				}
				case 8:
				{
					int i=0;
					try {
						br = new BufferedReader(new FileReader(file));
					} catch (FileNotFoundException e1) {
					
						e1.printStackTrace();
					}
					try {
						while((tmp = br.readLine()) != null){
							num8[i++]=Integer.parseInt(tmp);
							}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 long startTime = System.currentTimeMillis();
					 Sort.basesort(num8);
					long endTime = System.currentTimeMillis(); 
					long res=endTime-startTime;
					
					try 
					{
						
						File file1 = new File("D:\\sort\\basesort.txt");			
						 
						FileWriter fw = new FileWriter(file1);		//创建文件写入
						
						fw.write("基数排序程序对"+x+"个随机数字运行时间："+res+"ms"+"\n");	
						System.out.println("基数排序程序对"+x+"个随机数字排序运行时间："+res+"ms");
						for(int j=0;j<x;j++)
						{	
							
							fw.write(num8[j]+"\n");		
							
						}
						
						fw.close();
						
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}

				
					
				
					
					break;
				}
					
				}
			}
			
			System.out.println("结果已全部保存，请去对应文件查看相关结果");

			
			
			
			
			
			
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		
		}	

		
}
