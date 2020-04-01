package GamePlane;

import java.awt.Color;
import java.awt.Graphics;


public class Bullet extends GameObject implements Runnable {
	boolean islive;
	public Bullet(double x,double y)
	{
		this.x = x;
		this.y = y;
		speed = 3;
		islive = true;
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void draw(Graphics g) {
		if(islive) {
		g.setColor(Color.BLUE);
		y-=speed;
		g.fillOval((int)x, (int)y,20, 20);
		}
	}
}
