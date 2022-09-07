package dyt5;

public class place {

	int No;
	private String name;
	private String introduction;
	public place(int No,String name,String introduction)
	{
		this.introduction=introduction;
		this.No=No;
		this.name=name;
	}
	@Override
	public String toString()
	{
		StringBuilder res=new StringBuilder();
		res.append(name+":  "+introduction);
		
		return res.toString();
		
	}
	public String name()
	{
		return name;
	}
}
