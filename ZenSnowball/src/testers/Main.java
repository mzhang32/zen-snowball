package testers;

import java.awt.Dimension;

import javax.media.j3d.Shape3D;
import javax.swing.JFrame;

import shapes.*;

public class Main {
	

	public static void main(String[]args)
		{
		DrawingSurface ds = new DrawingSurface();
		Snowball snowball = new Snowball(300,300,300,100);
		ds.add(snowball);
		//ds.draw();
	
		
		ds.init();
		JFrame window = new JFrame();
		window.setSize(700, 700);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(ds);
		
		window.setVisible(true);
		}
}
