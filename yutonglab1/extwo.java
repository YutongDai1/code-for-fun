package dyt;

public class extwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           
	}
	  //������
	public static void swap(Node bp) {
		Node p,ap;//������Ԫ��
		p=bp.next;
		ap=p.next;
		p.next=ap.next;
		bp.next=ap;
		ap.next=p;	
	}
	//˫����
	public static void swap2(Node p)
	{
		Node bp,ap;
		bp=p.pre;
		ap=p.next;
		p.next=ap.next;
		bp.next=ap;
		ap.next=p;
		p.next.pre=p;
		p.pre=ap;
		ap.pre=bp;
	}

}
class Node
{
	Node next;
	Node pre;
	
}