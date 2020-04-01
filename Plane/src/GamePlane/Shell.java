package GamePlane;

import java.awt.Color;
import java.awt.Graphics;

public class Shell extends GameObject{
	double degree;
	boolean live;
	public Shell()
	{
		x = 200;
		y = 200;
		width = 20;
		height = 20;
		speed = 3;
		degree = Math.random()*Math.PI*2;
		live = true;
	}
	@Override
	public void drawSelf(Graphics g) {
		if (live) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillOval((int)x, (int)y, width, height);
		x += Math.cos(degree)*speed;
		y += Math.sin(degree)*speed;
		if(x<0||x>Constant.GAME_WIDTH-width)
		{
			degree = Math.PI-degree;
		}
		if(y<30||y>Constant.GAME_HEIGHT-height)
		{
			degree = -degree;
		}
		g.setColor(c);
		}
	}

}
