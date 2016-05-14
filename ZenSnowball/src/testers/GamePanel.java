package testers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import java.util.*;

import processing.core.PApplet;
import shapes.*;

public class GamePanel extends JPanel implements Runnable{
	
	DrawingSurface surface;
	Snowball snowball;
	ArrayList<Collidable> objects = new ArrayList<Collidable>();
	
	public GamePanel(){
		surface = new DrawingSurface();
		snowball = new Snowball(300,300,0,30);
		surface.add(snowball);
		for(int x = 0; x < objects.size(); x++){
		surface.add(objects.get(x));
		}
		surface.init();
	}
	
	public void run() {
		//while(true) {
		snowball.act();
		//}	
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Must use this in the Main method. 
	 * @return
	 */
	public DrawingSurface getDrawingSurface() {
		return surface;
	}
	

	public class KeyHandler implements KeyListener {
	  private boolean rightKey, leftKey, upKey;
		
	  public void keyPressed(KeyEvent e) {
	  	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	  		leftKey = true;
	  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightKey = true;
	  	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
	  		snowball.jump();
			upKey = true;
	  	}
	  }
	
	  public void keyReleased(KeyEvent e) {
	  	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	  		leftKey = false;
	  		if(rightKey);
	  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightKey = false;
			if (leftKey);
	  	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			upKey = false;
	  	}
	  }
	
	  public void keyTyped(KeyEvent e) {
	
	  }

	}
	
	public class DrawingSurface extends PApplet{
		
		ArrayList<Collidable> items = new ArrayList<Collidable>();
		//Snowball snowball = new Snowball(200,200,200,100);
		
		public DrawingSurface(){
			
		}
		
		public void add(Collidable c){
			items.add(c);
		}
		
		public void setup(){
			System.out.println("no problem here");
			noStroke();
			//lights();		
			System.out.println("Lights are on");

			size(700,700,P3D);
		}
		
		public void draw(){

			
			colorMode(RGB);
			
			background(255);
			stroke(0);
			for(int x = 0; x < items.size(); x++){
				items.get(x).draw(this);
			}
			snowball.act();
			//System.out.println(" DrawingSurface works too");
		}
		
		
		
	}
}

