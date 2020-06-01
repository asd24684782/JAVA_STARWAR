
public class Collison {

	private static boolean coll;
	
	public static boolean collide(Plane p,Bullet b)
	{
			
		if((b.X+b.WIDTH/2)<=p.getX()+p.getSize()&&(b.X+b.WIDTH/2)>=p.getX())
		{
			if(b.Y>p.getY()&&b.Y<=p.getY()+p.getSize())
			{
				coll = true;
			}
			else 
			{
				coll = false;
			}
		}		
		else
		{
			coll=false;
		}
		return coll;
	}
	
	public static boolean MonsterCollide(Plane p,MonsterBullet b)
	{
			
		if((b.X+b.SIZE/2)<=p.getX()+p.getSize()&&(b.X+b.WIDTH/2)>=p.getX())
		{
			if(b.Y+b.SIZE>p.getY()&&b.Y+b.SIZE<=p.getY()+p.getSize())
			{
				coll = true;
			}
			else 
			{
				coll = false;
			}
		}		
		else
		{
			coll=false;
		}
		return coll;
	}
}
