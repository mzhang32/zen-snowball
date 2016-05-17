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
		game.init();
		//System.out.println("it works");
		add(game);
		setVisible(true);
	}
	public static void main(String[]args)
	{
		Main main = new Main ("Zen Snowball");	
	}

	
}
