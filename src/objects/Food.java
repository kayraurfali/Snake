package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import screen.Main;

public class Food {
		
	private int x= 9999999;
	private int y = 9999999;
	private int size = 15;
	
	public static boolean eaten = false;
	
	/*
	 * if snake touches the food
	 * this triggers eating
	 */
	public boolean touch(Snake s) {
		return s.bounds().intersects(bounds())?true:false;
	}
	
	/*
	 * selects a spot for the food;
	 * if the spot is occupied by snake, chooses another location
	 */
	public void spawn() {
			x = 5 + Main.sqSize*new Random().nextInt(Main.WIDTH/Main.sqSize-1);
			y = 5 + Main.sqSize*new Random().nextInt(Main.HEIGHT/Main.sqSize-1);
			for(Rectangle r: Main.s.bodyBounds())
				if(bounds().intersects(r)) {
					spawn();
				}
			Food.eaten = false;
		//System.out.println("x " + x + "  y   "+ y); 
	}
	
	public void render(Graphics2D g) {
		if(!eaten) {
			if(x != 0 || y != 0) {
				g.setColor(Color.red);
				g.fillRect(x, y, size, size);
			}
		}
	}
	
	public Rectangle bounds(){
		return new Rectangle(x,y,size,size);
	}
}
