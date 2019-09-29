package screen;

import java.awt.Dimension;

import javax.swing.*;

public class Display {
	
	private JFrame f;
	
	public Display(int w, int h, String title, Main o) {
		o.setPreferredSize(new Dimension(w,h));
		o.setMinimumSize(new Dimension(w,h));
		o.setMaximumSize(new Dimension(w,h));
		
		f = new JFrame(title);
		f.add(o);
		f.pack();
		f.setSize(new Dimension(w+7,h+30));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		//f.setTitle(title);
		f.setVisible(true);
		o.start();
	}
}
 