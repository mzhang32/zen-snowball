package testers;

import java.awt.Dimension;

import javax.media.j3d.Shape3D;
import javax.swing.JFrame;

import shapes.*;

public class Main extends JFrame{
	
	public Main(String title){
		super(title);
		
		GamePanel game = new GamePanel();
		JFrame window = new JFrame();
		window.setSize(700, 700);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(game);		
		game.run();
		window.add(game.getDrawingSurface());
		//System.out.println("it works");

		window.setVisible(true);
	}
	public static void main(String[]args)
		{
		//ds.draw();
		Main main = new Main ("please work");
		
		}
}
