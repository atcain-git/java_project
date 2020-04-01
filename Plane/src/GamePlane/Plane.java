package GamePlane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class Plane extends GameObject{
	boolean left,right,up,down,space;
	boolean live;
	List<Bullet> all = new ArrayList<Bullet>();
	public Plane (Image img,double x,double y)
	{
		live = true;
		this.image = img;
		this.x = x;
		this.y = y;
		speed = 10;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
	@Override
	public void drawSelf(Graphics g) {
		if(live)
		{
			g.drawImage(image, (int)x,(int) y, null);
			oper();
			for(int i = 0;i<all.size();i++)
			{
				new Thread(all.get(i)).start();
				all.get(i).draw(g);
			}
		}
	}
	public void oper() {
		if(left){
			if(x>10)
			{
				x-=speed;
			}
			
		}
		else if (down) {
			if(y<Constant.GAME_HEIGHT-50)
			{
				y+=speed;
			}
		}
		else if (right) {
			if(x<Constant.GAME_WIDTH-30)
			{x+=speed;}
		}
		else if (up) {
			if(y>30) 
			{y-=speed;}
		}
	}

	public void addDection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;	
			break ;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_SPACE:
			all.add(new Bullet(x, y));
			break;
		}
	}
	public void minusDection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;	
			break ;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		}
	}
}
