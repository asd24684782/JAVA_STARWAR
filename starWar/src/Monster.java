
public class Monster extends Plane implements Common{
	
	public static int SIZE = 100;
	private static int velocity = 10;
	private static int MAXHP=5;
	
	public int X;
	public int Y;
	public int Hp;
	public int dir=1;
	

	
	public Monster() 
	{
		X = 210;
		Y = 400;
		Hp = 5;	
	}
	
	public Monster(int x,int y,int hp) 
	{
		X = x;
		Y = y;
		Hp = hp;	
	}
	
	
	public int getHp()
	{
		return Hp;
	}
	
	public int getX() 
	{
		return X;
	}
	
	public int getY() 
	{
		return Y;
	}
	
	public int getSize()
	{
		return SIZE;
	}
	
	public void move() 
	{
		X+=velocity*dir;
	}

	
	public void setHp(int hp)
	{
		Hp+=hp;
	}
	
	public void checkOutside() 
	{
		if(X+SIZE>=BG_WIDTH)
		{
			X=BG_WIDTH-SIZE;
			dir=-1;
		}
		else if(X<=0)
		{
			X=0;
			dir=1;
		}
	}


}
