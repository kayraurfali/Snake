package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import screen.*;

public class Snake {
	private int sqSize = Main.sqSize;
	private int snakeMax = (Main.WIDTH/sqSize) * (Main.HEIGHT/sqSize);
	private int[] x = new int[snakeMax];
	private int[] y = new int[snakeMax];
	public int points = 0;
	private int oldX,oldY;
	
	public Snake() {
		init();
	}
	
	/*
	 * Starting point of snake's head.
	 */
	private void init() {
		x[points] = oldX = 3;
		y[points] = oldY =3;
	}
	/*
	 * Renders a snake part with a size of sqSize-6
	 */
	public void render(Graphics2D g) {
		for(int i = 0; i < points+1; i++) {
			g.setColor(Color.white);
			if(x[i] != 0 || y[i] != 0) {
				g.fillRect(x[i], y[i], sqSize-6, sqSize-6);
			}
		}
	}
	
	/*
	 * First adds a point to the score,
	 * Then adds a snake part behind the last part
	 * 
	 * @param direction the snake was going while eating
	 * " 0 up, 1 down, 2 right, 3 left "
	 */
	public void eat(int direction) {

		points++;
		
		if (direction == 0) {
			y[points] = y[points - 1] + sqSize;
			x[points] = x[points - 1];
		} else if (direction == 1) {
			y[points] = y[points - 1] - sqSize;
			x[points] = x[points - 1];
		} else if (direction == 2) {
			x[points] = x[points - 1] - sqSize;
			y[points] = y[points - 1];
		} else if (direction == 3) {
			x[points] = x[points - 1] + sqSize;
			y[points] = y[points - 1];
		}

	}
	
	public void move(int direction) {
		
		oldX = x[0];
		oldY = y[0];
		if(direction == 0) {
			y[0] -= sqSize;
			
		}
		else if(direction == 1) {
			y[0] += sqSize;
		}
		else if(direction == 2) {
			x[0] += sqSize;
			
		}
		else if(direction == 3) {
			x[0] -= sqSize;
			
		}
		for(int i = 1; i < points+1;i++){
			int xx = oldX;
			int yy = oldY;
			
			oldX = x[i];
			oldY = y[i];
			
			x[i] = xx;
			y[i] = yy;
		}
		collision();
	}
	
	public void collision() {
		int i = 0;
		for(Rectangle r: bodyBounds()) {
			if(i == 0) {
				i++;
			}else {
				if(bounds().intersects(r)) {
					Main.running = false;
				}
			}
		}
	}
	
	public Rectangle[] bodyBounds() {
		Rectangle[] list = new Rectangle[points+1];
		for(int i = 0; i < points+1; i++) {
			list[i] = new Rectangle(x[i],y[i],sqSize-6,sqSize-6);
		}
		return list;
	}
	
	public Rectangle bounds() {
		
		return new Rectangle(x[0],y[0],sqSize-6,sqSize-6);
		
	}
}
