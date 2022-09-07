
package dyt6;

public class Position {

	private int row;
	private int col;

	
	public Position(int row,int col)
	{
		this.row=row;
		this.col=col;
	}
	
	public int i()
	{
		return row;
	}
	
	public int j()
	{
		return col;
	}
	public int hashCode() {
		return row* 1000 + col;
	}

	public boolean equals(Object x) {
		
		Position p = (Position) x;
		return p.i() == row && p.j() == col;
	}

}
