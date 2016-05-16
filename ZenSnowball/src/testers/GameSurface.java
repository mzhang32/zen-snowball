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

public class GameSurface extends PApplet{
	
	
	public static final int Z_FROM_SCREEN = 50;
	public static final int OBSTACLE_WIDTH = 100;
	
	ArrayList<Drawable> items = new ArrayList<Drawable>();
	private Snowball snowball;
	private Path path;
	private ArrayList<Collidable> obstacles = new ArrayList<Collidable>();
	
	private boolean isGame = true;

	public GameSurface(){
				
		snowball = new Snowball(0,0,0,30);
		add(snowball);
		
		path = new Path(500, 1000);
		add(path);
		
		//TESTING OBSTACLES
		obstacles.add(new Obstacle(-path.getWidth()/2, 0, 0, 100, 100, 100));
		
		for(int x = 0; x < obstacles.size(); x++){
			add(obstacles.get(x));
		}
		

	}
	
	public void setup(){
		noStroke();
		lights();		
		size(700,700,P3D);
	} 
	 
	public void runOnce() {	 
		snowball.act();	
	    
	}
	 	
	public void add(Drawable c){
		items.add(c);
	}
	
	
	
	
	public void draw(){
		//System.out.println("draw() was called");
		if(isGame){
		pushMatrix();
		pushStyle();
		translate(width/2, height-height/10, -Z_FROM_SCREEN); //IMPORTANT translated everything
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
		}
		else{
			
		}
		
		//System.out.println("draw() runs all the way through");
	}	
	public void keyPressed(){
		if(keyCode == UP){
			snowball.jump();
		}
		if(keyCode == LEFT)
		snowball.moveLeft();
		if(keyCode == RIGHT)
			snowball.moveRight();

	}
	
}
