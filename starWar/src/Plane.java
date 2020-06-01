public class Plane implements Common{

	private static int SIZE = 100;

	private int X;
	private int Y;
	private int Hp;
	private static int velocity = 10;
	public static int MAXHP=10;

	
	public Plane() 
	{
		X = 210;
		Y = 400;
		Hp = 10;	
	}
	
	public Plane(int x,int y,int hp) 
	{
		X = x;
		Y = y;
		Hp = hp;	
	}
	
	public int getSize()
	{
		return SIZE;
	}
	
	public int getX() 
	{
		return X;
	}
	
	public int getY() 
	{
		return Y;
	}
	
	public int getHp()
	{
		return Hp;
	}
	
	public void moveX(int x) 
	{
		X+=velocity*x;
	}
	
	public void moveY(int y) 
	{
		Y+=velocity*y;
	}
	
	public void setHp(int hp)
	{
		Hp+=hp;
	}
	
	public void checkOutside() 
	{
		if(X+SIZE>=BG_WIDTH)
			X=BG_WIDTH-SIZE;
		else if(X<=0)
			X=0;
		
		if(Y+SIZE>=BG_HEIGHT)
			Y=BG_HEIGHT-SIZE;
		else if(Y<=0)
			Y=0;
	}
	

	
	
	
}
