import java.awt.Color;
import java.io.File;
import javax.swing.ImageIcon;



public interface Common 
{
	
	public static final int BG_WIDTH = 1200;
	public static final int BG_HEIGHT = 1000;
	public static final Color BACKGROUND = new Color(204, 204, 204);  

	
	
	public static final ImageIcon plane =  new ImageIcon("src/image/plane.png");
	public static final ImageIcon BG = new ImageIcon("src/image/BG.jpg");
	public static final ImageIcon EAR = new ImageIcon("src/image/ear.png");
	public static final ImageIcon KNIFE = new ImageIcon("src/image/knife.png");
	public static final ImageIcon STARTBG = new ImageIcon("src/image/startBG.png");
	public static final ImageIcon BLOOD = new ImageIcon("src/image/blood.png");
	public static final ImageIcon ROUND = new ImageIcon("src/image/round.jpg");
	public static final ImageIcon WIN = new ImageIcon("src/image/win.jpg");
	public static final ImageIcon LOSS = new ImageIcon("src/image/loss.jpg");
	
	public static final String GOGOGO = "ABBAABRRL";
	public static final File BGM =new File("src/sound/BGM.mp3");
	
}
