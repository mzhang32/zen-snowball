package testers;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends JFrame{
	
	public Main(String title){
		super(title);
		setSize(700, 700);
		setMinimumSize(new Dimension(100,100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameSurface game = new GameSurface();
		add(game);
		//System.out.println("it works");

		setVisible(true);
	}
	public static void main(String[]args)
	{
		Main main = new Main ("please work");	
	}

	
}
