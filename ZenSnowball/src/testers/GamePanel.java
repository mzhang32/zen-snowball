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
		super();
		
		surface = new DrawingSurface();
		snowball = new Snowball(300,300,0,30);
		surface.add(snowball);
		
		for(int x = 0; x < objects.size(); x++){
			surface.add(objects.get(x));
		}
		
		surface.init();
		new Thread(this).start();

		}
	 
	 public void run(){
		 
	 }
	 
	 public void runOnce() {
		 
			snowball.act();
		
	 }
	
	/**
	 * Must use this in the Main method. 
	 * @return
	 */
	public DrawingSurface getDrawingSurface() {
		return surface;
	}
	
public class DrawingSurface extends PApplet{
		
		ArrayList<Collidable> items = new ArrayList<Collidable>();
		
		public DrawingSurface(){
			super();
		}
		
		public void add(Collidable c){
			items.add(c);
		}
		
		public void setup(){
			noStroke();
			lights();		
			size(700,700,P3D);
		}
		
		public void draw(){
			//System.out.println("draw() was called");
			colorMode(RGB);
			background(255);
			stroke(0);
			lights();
			
			for(int x = 0; x < items.size(); x++){
				items.get(x).draw(this);
			}
			runOnce();
			
			//System.out.println("draw() runs all the way through");
		}	
		public void keyPressed(){
			if(keyCode == UP){
				snowball.jump();
			}
		}
	}

	
}

