
public class MonsterBullet extends Bullet{
	public static int SIZE = 50;
	public int X;
	public int Y;
	public int dx;
	public int dy;
	public int velocity=20;

	
	public MonsterBullet() 
	{
		X=0;
		Y=0;
		dx=(int) (Math.random()*6-3)*velocity/2;
		dy=velocity;
	}
	
	public MonsterBullet(int x,int y) 
	{
		this.X=x;
		this.Y=y;
		dx=(int) (Math.random()*6-3)*velocity/2;
		dy=velocity;
	}
	
	public void move() {
		X+=dx;
		Y+=dy;
	}
}
