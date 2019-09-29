package screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import objects.Food;
import objects.Snake;

public class Main extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 500, HEIGHT = 500;
	public static boolean running = false;
	public static int sqSize = 25;
	private Thread thread;
	public static Snake s;
	private Food f;
	public static int direction = 2;
	

	public static void main(String[] args) {
		
		new Display(WIDTH,HEIGHT, "Snake Game", new Main());
		
	}
	

	public void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void init() {
		f = new Food();
		s = new Snake();
		this.addKeyListener(new Mouse());
		f.spawn();
	}
	
	@Override
	public void run() {
		init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 3;
        double ns = 1000000000 / amountOfTicks; //seconds per tick
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while(delta >= 1){
                        update();
                        updates++;
                        delta--;
                }
                render();
                frames++;
                

                if(System.currentTimeMillis() - timer > 1000){
                        timer += 1000;
                        //System.out.println("FPS: " + frames + " TICKS: " + updates);
                        frames = 0;
                        updates = 0;
                }
        }
	}
	int i = 1;
	private void update() {
		
		/*
		 * eating the food
		 */
		if(f.touch(s) && Food.eaten == false) {
			s.eat(direction); 
			Food.eaten = true;
			f.spawn();
		}
		s.move(direction);
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        // For visualizing squares
        
//        for(int y = 0;y < HEIGHT; y+=sqSize) {
//        	for(int x = 0; x < WIDTH; x+=sqSize) {
//        		g2.setColor(Color.blue);
//        		g2.drawRect(x, y, sqSize, sqSize);
//        	}
//        }
        
        f.render(g2);
        s.render(g2);
        
        g.dispose();
        bs.show();

	}
	
}
