package dyt5;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CJLUMAP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		List<place>list=new ArrayList<>();
		DenseweightedGraph graph=new DenseweightedGraph(11);
		func.initgraph(graph);
		func.initinformation(list);
		int x;
		while(true)
		{
			func.menu();
			System.out.println("**************������ Ҫ���������************");
			x=in.nextInt();
			if(x==5)
			{
				System.out.println("**************ллʹ��************");
				break;
			}
			else if(x!=1&&x!=2&&x!=3&&x!=4)
			{
				System.out.println("**************������Ҫ������������************");
				continue;
			}
			switch(x)
			{
			case 1:
			{
				func.displayTU();
				break;
			}
			case 2:
			{
				func.showinformation(list);
				break;
			}
			case 3:
			{
				func.dijstra(graph, list);
				break;
			}
			case 4:
			{
				func.floyd(graph,list);
				break;
			}
		
			}
			
			
		}

	}

}
