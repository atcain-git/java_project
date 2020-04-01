package GamePlane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.print.attribute.standard.SheetCollate;


public class MyGameFrame extends Frame{
	
	Image planeImg = GameUtil.getImage("Image/plane.png");
	Image bg = GameUtil.getImage("Image/bg.jpg");
	Plane plane = new Plane(planeImg, 250, Constant.GAME_HEIGHT-150);
	Shell[] shell = new Shell[20];
	Explode bao ;
	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g);
		
		for(int i = 0;i<shell.length;i++)
		{
			Color   c =  g.getColor();
			shell[i].drawSelf(g);
			boolean boom = shell[i].getRect().intersects(plane.getRect());
			for(int j=0;j<plane.all.size();j++) {
			boolean coll = shell[i].getRect().intersects(plane.all.get(j).getRect());
					if(coll)
					{
						shell[i].live = false;
						plane.all.get(j).islive = false;
					}
			}
			if(boom) {
				plane.live = false;
				for(i = 0;i<shell.length;i++) {
				shell[i].live = false;
				}
				if(bao == null)
				{
					bao = new Explode(plane.x, plane.y);
				}
				bao.draw(g);
			}
			if(!plane.live)
			{
				g.setColor(Color.red);
				Font f = new Font("宋体", Font.BOLD, 50);
				g.setFont(f);
				g.drawString("你输了", (int)plane.x,(int) plane.y);
			}
			g.setColor(c);
		}
		
	}
	class PaintThread extends Thread
	{
		@Override
		public void run() {
			while(true)
			{
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	class   KeyMonitor extends  KeyAdapter  {

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDection(e);
		}
		
		
	}
	public void lunchFrame() {
		this.setTitle("hello!This is the first Game");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.setLocation(100, 100);
		
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		new PaintThread().start();	//启动重画窗口的线程
		addKeyListener(new KeyMonitor());   //给窗口增加键盘的监听
		for(int i =0;i<shell.length;i++)
		{
			shell[i] = new Shell();
		}
	}
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.lunchFrame();
	}
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}
}

