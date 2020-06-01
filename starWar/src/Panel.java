import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import jaco.mp3.player.MP3Player;
public class Panel extends JPanel implements Common
{
	private static final long serialVersionUID = 1L;
	private BufferedImage myImage;
    private Graphics myBuffer;
    JFrame frame=new JFrame();
      
    private Timer inGameTimer; 
    private Timer starTimer;
    private Timer helpTimer;
    private Timer settingTimer;
    private Timer winTimer;
    private Timer lossTimer;
    private Timer roundTimer;
    private Timer seletTimer;
    
    private Plane player;
    private boolean Up = false;
    private boolean Down = false;
    private boolean Right = false;
    private boolean Left = false;
    private boolean Enter = false;
    private int bulletNum = 0;
    
    boolean infinity=false;
    boolean  superMan=false; //�L�ļҦ�
    
    int SquareX= BG_WIDTH/2-200; 
    int SquareY= BG_HEIGHT/2;
    

    int scope=0;	//����  
    int volume=60; //���q  
    int round=1; //�^�X��
    int wait=0; //�^�X���ݵe����
    
    
    private List<Bullet> playerBullet = new ArrayList<>();
    private List<MonsterBullet> monsterBullets= new ArrayList<>();
    private List<Monster> monsters =new ArrayList<Monster>();
    
    Thread bgmThread=new Thread(new BGMThread());
    
    
    
    //------------------------------------------------------------
	public Panel()
	{
		
		new JFrame("STAR WAR");
		frame.setSize(BG_WIDTH+10, BG_HEIGHT+10);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.setVisible(true);
        
        addKeyListener(new Key());
        setFocusable(true);
        
	   	myImage =  new BufferedImage(BG_WIDTH, BG_HEIGHT, BufferedImage.TYPE_INT_RGB);
      	myBuffer = myImage.getGraphics();
        	
      	starTimer = new Timer(25, new Start());	
      	inGameTimer = new Timer(25,new InGame());
      	helpTimer = new Timer(25,new Help());
      	settingTimer =new Timer(25,new Setting());
      	winTimer=new Timer(25,new win());
      	lossTimer = new Timer(25,new loss());
      	roundTimer=new Timer(25, new Round());
      	seletTimer=new Timer(25,new select());
      	
      	starTimer.start();
      	bgmThread.start();
      	
      	
	}
	//------------------------------------------------------------
    public void paintComponent(Graphics g)
    {
 	   g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }
    //---------------------------------------------------------------
    public void drawSquare(Graphics g,int x1,int y1,int width,int height)
    {
    	Graphics2D g2 =(Graphics2D) g;
    	g2.setStroke(new BasicStroke(5.0f));
    	g2.setColor(Color.RED.darker());
    	g2.drawRect(x1, y1, width, height);
    }
    //----------------------------------------------------------------
    public void drawHP(Graphics g,int x,int y,double hp,int size)
    {
    	g.setColor(Color.black);
    	g.fillRect(x+size/2-50, y+size+10, 100, 20);   	
    	g.setColor(Color.red);
    	g.fillRect(x+size/2-50, y+size+10, (int)(100*(hp/Plane.MAXHP)) , 20);
    }
    //----------------------------------------------------------------
    class Start implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
       {
        	myBuffer.drawImage(STARTBG.getImage(), 0, 0, BG_WIDTH, BG_HEIGHT, null);//�e�I�� 
        	      	
        	drawSquare(myBuffer, SquareX, SquareY, 400, 100); //��ܮ�  
        	
        	myBuffer.setFont(new Font("Monospaced", Font.BOLD, 60));
        	myBuffer.setColor(Color.black);
        	
        	myBuffer.drawString("�}�l�C��", 480 , 570);
        	myBuffer.drawString("�C������", 480 , 690);
        	myBuffer.drawString("�C���]�m", 480 , 810);
        	
        	if(Enter&&SquareY==500)			//�i�J�C��
        	{
        		Enter=false;
        		starTimer.stop();    	
              	seletTimer.start();
        	}
        	else if(Enter&&SquareY==620)  //�i�J����
            {
        		Enter=false;
        		starTimer.stop();
        		helpTimer.start();
            }
            else if(Enter&&SquareY==740)  //�i�J�C���]�m
            {
            	Enter=false;
            	starTimer.stop();
            	settingTimer.start();
            }
                  
        	
        	if(Up)
        	{
        		SquareY-=120;
        		if(SquareY<500)
        		{
        			SquareY+=120;
        		}
        		Up=false;
        	}
        	if(Down) 
        	{
        		SquareY+=120;
        		if(SquareY>740)
        		{
        			SquareY-=120;
        		}
        		Down=false;
        	}
        	
        	repaint();
        	
       }

    }
    //---------------------------------------------------------------
    class Help implements ActionListener
    {
    	 public void actionPerformed(ActionEvent e)
         {
    		myBuffer.drawImage(STARTBG.getImage(), 0, 0, BG_WIDTH, BG_HEIGHT, null);//�e�I�� 
 	      	
         	
         	myBuffer.setFont(new Font("Monospaced", Font.BOLD, 60));
         	myBuffer.setColor(Color.black);
         	
         	myBuffer.drawString("�W�U���k ����", 400 , 270);
         	myBuffer.drawString("���Us�}�ҵL�ļҦ�", 400 , 390);
         	myBuffer.drawString("�l�u�۰ʵo�g", 400 , 510);
         	myBuffer.drawString("ENTER�^�D���", 400 , 630);
         	
         	if(Enter)
         	{
         		Enter=false;
         		helpTimer.stop();
         		starTimer.start();
         	}
    		repaint();
         }
    }
    //---------------------------------------------------------------
    class Setting implements ActionListener
    {
    	 public void actionPerformed(ActionEvent e)
         {
    		myBuffer.drawImage(STARTBG.getImage(), 0, 0, BG_WIDTH, BG_HEIGHT, null);//�e�I�� 
    		
    		
    		
    		
    		
          	if(Enter)
          	{
          		Enter=false;
          		settingTimer.stop();
          		starTimer.start();
          	}
    		 repaint();
         }
    }
    //---------------------------------------------------------------
    class select implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
       {
        	myBuffer.drawImage(STARTBG.getImage(), 0, 0, BG_WIDTH, BG_HEIGHT, null);//�e�I�� 
        	      	
        	drawSquare(myBuffer, SquareX, SquareY, 400, 100); //��ܮ�  
        	
        	myBuffer.setFont(new Font("Monospaced", Font.BOLD, 60));
        	myBuffer.setColor(Color.black);
        	
        	myBuffer.drawString("�@��Ҧ�", 480 , 570);
        	myBuffer.drawString("�L�ɼҦ�", 480 , 690);
        	
        	if(Enter&&SquareY==500)			//�@��Ҧ�
        	{
        		infinity=false;
        		Enter=false;
        		seletTimer.stop();    	
              	roundTimer.start();
        	}
        	else if(Enter&&SquareY==620)  //�L�ɼҦ�
            {
        		infinity=true;
        		Enter=false;
        		seletTimer.stop();    	
              	roundTimer.start();
            }

        	if(Up)
        	{
        		SquareY-=120;
        		if(SquareY<500)
        		{
        			SquareY+=120;
        		}
        		Up=false;
        	}
        	if(Down) 
        	{
        		SquareY+=120;
        		if(SquareY>620)
        		{
        			SquareY-=120;
        		}
        		Down=false;
        	}    	
        	repaint(); 	
       }

    }
    //---------------------------------------------------------------
    class Round implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
        {
    		myBuffer.drawImage(ROUND.getImage(), 0, 0, BG_WIDTH, BG_HEIGHT, null);
    		
    		myBuffer.setFont(new Font("SERIF", Font.BOLD, 60));
	        myBuffer.setColor(Color.black);	         
	        myBuffer.drawString("Round "+round, 480 , 470);
    		 		
    		player=new Plane(BG_WIDTH/2,BG_HEIGHT-200,10);
    		
          	monsterBullets.clear();
          	playerBullet.clear();
          	monsters.clear();
 	
          	if(wait==40)
          	{

    			for(int i=0;i<4+round;i++)
    			{
    				monsters.add(new Monster(100+(i%5)*180,i/5*120,1));			
    			}	
    			
          		roundTimer.stop();
          		inGameTimer.start();
          		wait=0;
          	}
          	else
          	{
          		wait++;
			}
          	
          	
   		 	repaint();
        }
    }
    //----------------------------------------------------------------- 
    class InGame implements ActionListener
    {
            public void actionPerformed(ActionEvent e)
            {
	          	  myBuffer.setColor(BACKGROUND);
	          	  myBuffer.fillRect(0,0,BG_WIDTH, BG_HEIGHT);
	          	  myBuffer.drawImage(BG.getImage(), 0, 0, BG_WIDTH, BG_HEIGHT, null); 	 //�e�I�� 
	          	   
	          	  
	          	  if(monsters.size()<=0)	//����
	          	  {
	          		  if(round==5&&!infinity)
	          		  {
	          			SquareX=255;
	          			SquareY=630;
	          			inGameTimer.stop();
	            		winTimer.start();
	          		  }
	          		  else 
	          		  {
	          			round++;
	          			inGameTimer.stop();
	            		roundTimer.start();
	          		  }	  
	          	  }
	
	          	  
	          	  if(player.getHp()<=0)			//���ѵe��
	          	  {
	          		  SquareX=255;
	          		  SquareY=630;
	          		  inGameTimer.stop();
	          		  lossTimer.start();
	          	  }
	          	  
	          	  
	          	  for(int i = 0;i<playerBullet.size();i++)//player�l�u����
	          	  {
	          		  for(int j=0;j<monsters.size();j++)
	          		  {
	          			  if(Collison.collide(monsters.get(j), playerBullet.get(i)))
	          			  {
	          				  monsters.get(j).setHp(-1);
	          				  
		          			  if(monsters.get(j).Hp<=0)
		                  	  {
		                  		 scope++;
		                  		 monsters.remove(j);
		                  		 j--;
		                  	  }
		          			  
	          				  playerBullet.remove(i);
	          				  i--;	 
	          				  break;
	          			  }		  
	          		  }
	          	  }
	          	  
	          	  for(int i=0;i<monsterBullets.size();i++)	//monster�l�u����
	          	  {
	          		 if(Collison.MonsterCollide(player, monsterBullets.get(i)))
	     			  {
	          			  if(!superMan)		//�L�ļҦ����l��
	          			  {
	          				  player.setHp(-1);
	          			  }		  
	     				  monsterBullets.remove(i);
	     				  i--;
	     			  }	
	          	  }
	          	         	  
	          	
	          	  if(Up)										//player ����
	          		player.moveY(-1);
	          	  if(Down)
	          		player.moveY(1);
	          	  if(Left)
	          		player.moveX(-1);
	          	  if(Right)
	          		player.moveX(1);
	
	          	  bulletNum++;
	          	  
	          	  if(bulletNum%20==0)//���s�g
	          	  {
	          		playerBullet.add(new Bullet(player.getX(),player.getY()));		//�W�[player�l�u
	          		for(int i=0;i<monsters.size();i++)								//�W�[monster�l�u
					{
	          			monsterBullets.add(new MonsterBullet(monsters.get(i).X+(Monster.SIZE)/2,
								monsters.get(i).Y+Monster.SIZE));
					}
	          		if(bulletNum==1000)		//reset bulletNum
	              		bulletNum=0;
	          	  }
	          	  
	          	
	          	  
	          	  
	          	  player.checkOutside();											//���_�X��
	          	  myBuffer.drawImage(plane.getImage(),player.getX() ,player.getY() //�eplayer
	          			  ,player.getSize() ,player.getSize(),null); 
	          
	          	  drawHP(myBuffer, player.getX(), player.getY(), player.getHp(), //�eplayer hp
	          			  player.getSize());
	          	  
	
	          	  
	          	  
	          	  
	          	  if(playerBullet.size()!=0) //ø�s�l�u
	          	  {
	          		  for(int i=0;i<playerBullet.size();i++)
	          		  {
	          			
	    				myBuffer.drawImage(KNIFE.getImage(),playerBullet.get(i).X ,playerBullet.get(i).Y
	          					,Bullet.WIDTH ,Bullet.HEIGHT,null);				
	    				playerBullet.get(i).move();
	
	          			
	          			if(playerBullet.get(i).Y<0) //�����W�X�ù����l�u
	          			{
	          				playerBullet.remove(i);
	          				i--;
	          			}
	          		  }
	          	  }
	          	  
	          	for(int i=0;i<monsters.size();i++)		//�e���~
	    		{	
	    			myBuffer.drawImage(EAR.getImage(),monsters.get(i).X ,monsters.get(i).Y
	    					,Monster.SIZE ,Monster.SIZE,null);
	    			monsters.get(i).move();
	    			monsters.get(i).checkOutside();
	    		}
	          	
	          	for(int i=0;i<monsterBullets.size();i++)//�e���~�l�u
	          	{
	          		myBuffer.drawImage(BLOOD.getImage(),monsterBullets.get(i).X ,monsterBullets.get(i).Y
	    					,MonsterBullet.SIZE ,MonsterBullet.SIZE,null);
	          		monsterBullets.get(i).move();
	          		if(monsterBullets.get(i).Y>=1000)
	          			monsterBullets.remove(i);
	          	}
	          	   
	          	if(superMan)
	          	{
	          		myBuffer.setFont(new Font("BOLD", Font.BOLD, 40));
      		        myBuffer.setColor(Color.black);	         
      		        myBuffer.drawString("�~�� �p�}�ɱ� ", player.getX()-100 , player.getY()+20);
	          	}
	          	
	          	repaint();
           }
            
    }
    //------------------------------------------------------------------
    class win implements ActionListener
    {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		 myBuffer.drawImage(WIN.getImage(), 0, 0, BG_WIDTH, BG_HEIGHT,null);	 
	    	 myBuffer.setFont(new Font("SERIF", Font.BOLD, 60));
	         myBuffer.setColor(Color.RED.brighter());         
	         myBuffer.drawString("you win", 480 , 470);
	         myBuffer.drawString("scope: "+scope, 480 , 570);         
	         myBuffer.setColor(Color.CYAN);
	         myBuffer.drawString("�^�D���", 280 , 690);
	         myBuffer.drawString("�A..�A�@��", 620 , 690);
	         

	         
	         drawSquare(myBuffer,SquareX,SquareY,300,80);
	         

	         if(Right)
	         { 
	        	SquareX=610;	//again
	         }
	         else if(Left) 
	         {
	        	 SquareX=255;	//home
			 }
	         
	         if(SquareX==255&&Enter)
	         {
	        	 winTimer.stop();
	        	 starTimer.start();
	        	 SquareX=400;
	        	 SquareY=500;
	        	 Enter=false;
	        	 superMan=false;
	        	 round=1;
	        	 scope=0;
	         }
	         else if(SquareX==610&&Enter)
	         {
	        	 winTimer.stop();
	        	 roundTimer.start();
	        	 round=1;
	        	 superMan=false;
	        	 Enter=false;
	        	 scope=0;
	         }
	        
	         
    		 repaint();
    	 }
    }
    //------------------------------------------------------------------
    class loss implements ActionListener
    {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		 myBuffer.drawImage(LOSS.getImage(), 0, 0, BG_WIDTH, BG_HEIGHT,null);
    		 
	    	 myBuffer.setFont(new Font("SERIF", Font.BOLD, 60));
	         myBuffer.setColor(Color.RED.brighter());
	         
	         myBuffer.drawString("you loss", 480 , 470);
	         myBuffer.drawString("scope: "+scope, 460 , 570);

	         
	         myBuffer.setColor(Color.CYAN);
	         myBuffer.drawString("�^�D���", 280 , 690);
	         myBuffer.drawString("�A..�A�@��", 620 , 690);
	         

	         
	         drawSquare(myBuffer,SquareX,SquareY,300,80);
	         

	         if(Right)
	         { 
	        	SquareX=610;	//again
	         }
	         else if(Left) 
	         {
	        	 SquareX=255;	//home
			 }
	         
	         if(SquareX==255&&Enter)
	         {
	        	 lossTimer.stop();
	        	 starTimer.start();
	        	 SquareX=400;
	        	 SquareY=500;
	        	 
	        	 Enter=false;
	        	 superMan=false;
	        	 round=1;
	        	 scope=0;
	         }
	         else if(SquareX==610&&Enter)
	         {
	        	 lossTimer.stop();
	        	 roundTimer.start();
	        	 
	        	 round=1;
	        	 Enter=false;
	        	 superMan=false;
	        	 scope=0;
	         }
	         
    		 repaint();
    	 }
    }
    //------------------------------------------------------------����Ĳ�o
	class Key extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode()==KeyEvent.VK_UP)
				Up=true;
			if(e.getKeyCode()==KeyEvent.VK_DOWN)
				Down=true;
			if(e.getKeyCode()==KeyEvent.VK_LEFT)
				Left=true;
			if(e.getKeyCode()==KeyEvent.VK_RIGHT)
				Right=true;	
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				Enter=true;	
		}
		
		public void keyReleased(KeyEvent e)
		{
			if(e.getKeyCode()==KeyEvent.VK_UP)
				Up=false;
			if(e.getKeyCode()==KeyEvent.VK_DOWN)
				Down=false;
			if(e.getKeyCode()==KeyEvent.VK_LEFT)
				Left=false;
			if(e.getKeyCode()==KeyEvent.VK_RIGHT)
				Right=false;
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				Enter=false;	
		}	
		
		public void keyTyped(KeyEvent e)
		{
			if(e.getKeyChar()=='s'||e.getKeyChar()=='S')
			{
				if(superMan)
				{	
					superMan=false;
				}	
				else
				{
					superMan=true;
				}
					
			}
		}
	}
	//-------------------------------------------------------------------
	
  
}
