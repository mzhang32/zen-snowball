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
	Path path;
	ArrayList<Collidable> obstacles = new ArrayList<Collidable>();

	public GamePanel(){
		super();
		
		surface = new DrawingSurface();
		snowball = new Snowball(0,0,0,30);
		surface.add(snowball);
		
		path = new Path(500, 1000);
		surface.add(path);
		
		for(int x = 0; x < obstacles.size(); x++){
			surface.add(obstacles.get(x));
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
		
		ArrayList<Drawable> items = new ArrayList<Drawable>();
		
		public DrawingSurface(){
			super();
		}
		
		public void add(Drawable c){
			items.add(c);
		}
		
		public void setup(){
			noStroke();
			lights();		
			size(700,700,P3D);
		}
		
		public void draw(){
			//System.out.println("draw() was called");
			pushMatrix();
			pushStyle();
			translate(width/2, height-height/10, -50); //IMPORTANT translated everything
			colorMode(RGB);		
			background(255);
			stroke(0);
			lights();
		    //box(100); //delete later
			
			popStyle();
			for(int x = 0; x < items.size(); x++){
				items.get(x).draw(this);
			}
			

			popMatrix(); //Matrix is at the end b/c translate needs to apply to everything drawn
			
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

