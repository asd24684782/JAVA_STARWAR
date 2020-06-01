public class Bullet {
	
	public  static int WIDTH = 20;
	public  static int HEIGHT = 67;
	public static int r = (int) Math.pow(WIDTH*WIDTH+HEIGHT*HEIGHT, 0.5)/2;
	public  int X;
	public  int Y;
	private static int velocity = 20;

	
	
	public Bullet() 
	{
		X = 0;
		Y = 0;	
	}
	
	public Bullet(int x,int y) 
	{
		X = x;
		Y = y;	
	}
	
	public void move()
	{
		Y-=velocity;
	}
	

	
}
