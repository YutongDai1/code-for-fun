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
		
		System.out.println("������Ҫ������������ָ���");
			Scanner in=new Scanner(System.in);
			
			int x;                                            //���õ����ָ���
			
			x=in.nextInt();
			
			try 
			{
				File file = new File("D:\\sort\\randomnum.txt");			
				if(!file.exists())
				{	                                         //���������data.txt�ļ��򴴽�
					file.createNewFile();
					System.out.println("data.txt�������");				
				}
				FileWriter fw = new FileWriter(file);		  //�����ļ�д��
				BufferedWriter bw = new BufferedWriter(fw);
				
				//����������ݣ�д���ļ�
				Random random = new Random();
				for(int j=0;j<x;j++)
				{	
					int randint =(random.nextInt(10*x));	//����0-10*x֮�������		
					bw.write(String.valueOf(randint));		//д��һ�������
					bw.newLine();		                    //�µ�һ��
				}
				bw.close();
				fw.close();
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			System.out.println("������Ҫ�Ƚϵ������㷨��� ������������0��Ϊȷʵ��    ***ע�⣺���ֻ�ܱȽ�8������ ����8�齫��ǿ��ִ��***");
			
			System.out.print("1 ѡ������\n"
					+ "2 ��������\n"
					+ "3 ϣ������\n"
					+ "4 ��������\n"
					+ "5 ð������\n"
					+ "6 �鲢����\n"
					+ "7 Ͱ����\n"
					+ "8 ��������\n"
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
					System.out.println("�ﵽ���������ǿ��ִ��");
					break;
				}
				s=in.nextInt();
				
				
			}
			
			System.out.println("��ȴ�......");
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
						 
						FileWriter fw = new FileWriter(file1);		//�����ļ�д��
						
						fw.write("ѡ����������"+x+"�����������������ʱ�䣺"+res+"ms"+"\n");
						System.out.println("ѡ����������"+x+"�����������������ʱ�䣺"+res+"ms");
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
						 
						FileWriter fw = new FileWriter(file1);		//�����ļ�д��
						
						fw.write("������������"+x+"�������������ʱ�䣺"+res+"ms"+"\n");	
						System.out.println("������������"+x+"�����������������ʱ�䣺"+res+"ms");
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
						 
						FileWriter fw = new FileWriter(file1);		//�����ļ�д��
						
						fw.write("ϣ����������"+x+"�������������ʱ�䣺"+res+"ms"+"\n");	
						System.out.println("ϣ����������"+x+"�����������������ʱ�䣺"+res+"ms");
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
						 
						FileWriter fw = new FileWriter(file1);		//�����ļ�д��
						
						fw.write("������������"+x+"�������������ʱ�䣺"+res+"ms"+"\n");	
						System.out.println("������������"+x+"�����������������ʱ�䣺"+res+"ms");
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
						 
						FileWriter fw = new FileWriter(file1);		//�����ļ�д��
						
						fw.write("ð����������"+x+"�������������ʱ�䣺"+res+"ms"+"\n");
						System.out.println("ð����������"+x+"�����������������ʱ�䣺"+res+"ms");
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
						 
						FileWriter fw = new FileWriter(file1);		//�����ļ�д��
						
						fw.write("�鲢��������"+x+"�������������ʱ�䣺"+res+"ms"+"\n");	
						System.out.println("�鲢��������"+x+"�����������������ʱ�䣺"+res+"ms");
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
						 
						FileWriter fw = new FileWriter(file1);		//�����ļ�д��
						
						fw.write("Ͱ��������"+x+"�������������ʱ�䣺"+res+"ms"+"\n");	
						System.out.println("Ͱ��������"+x+"�����������������ʱ�䣺"+res+"ms");
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
						 
						FileWriter fw = new FileWriter(file1);		//�����ļ�д��
						
						fw.write("������������"+x+"�������������ʱ�䣺"+res+"ms"+"\n");	
						System.out.println("������������"+x+"�����������������ʱ�䣺"+res+"ms");
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
			
			System.out.println("�����ȫ�����棬��ȥ��Ӧ�ļ��鿴��ؽ��");

			
			
			
			
			
			
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		
		}	

		
}
