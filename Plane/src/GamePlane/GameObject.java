package GamePlane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {

	Image image;
	double x,y;
	int speed;
	int width,height;
	
	public GameObject(Image image, double x, double y, int speed, int width, int height) {
		super();
		this.image = image;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}

	public GameObject(Image image, double x, double y) {
		super();
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public GameObject() {
		super();
	}
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int) y, width, height);
	}
	public void drawSelf(Graphics g) {
		g.drawImage(image, (int)x, (int)y, null);
	}
}
