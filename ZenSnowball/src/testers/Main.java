package testers;

import java.awt.Dimension;

import javax.media.j3d.Shape3D;
import javax.swing.JFrame;

import shapes.*;

public class Main extends JFrame{
	
	public Main(String title){
		super(title);
		setSize(700, 700);
		setMinimumSize(new Dimension(100,100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel game = new GamePanel();
		

		add(game.getDrawingSurface());
		//System.out.println("it works");

		setVisible(true);
	}
	public static void main(String[]args)
	{

		Main main = new Main ("please work");
		
	}

	
}
