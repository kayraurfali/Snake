package screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Mouse implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
	        case KeyEvent.VK_RIGHT: 
	        	if(Main.direction != 3) {
	        		Main.direction = 2;
	        		break;
	        	}else {
	        		break;
	        	}
	        case KeyEvent.VK_LEFT: 
	        	if(Main.direction != 2) {
	        		Main.direction = 3;
	        		break;
	        	}
	        	else {
	        		break;
	        	}
	        case KeyEvent.VK_UP: 
	        	if(Main.direction != 1) {
	        		Main.direction = 0;	     
	        		break;
	        	}else {
	        		break;
	        	}
	        case KeyEvent.VK_DOWN: 
	        	if(Main.direction != 0) {
	        		Main.direction = 1;
	        		break;
	        	}else {
	        		break;
	        	}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
