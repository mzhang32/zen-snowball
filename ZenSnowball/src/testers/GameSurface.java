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

/**
 * This class holds the objects of the game and handles user input. Also draws to the screen using 
 * processing.
 * 
 * @author Michelle Z. and Waveley Q.
 * @version 05.16.2016
 *
 */
public class GameSurface extends PApplet{
	
	
	public static final int Z_FROM_SCREEN = 50;
	public static final int OBSTACLE_WIDTH = 100;
	public static final int INIT_RADIUS = 30;

	private Snowball snowball;
	private Path path;
	private ArrayList<Collidable> obstacles = new ArrayList<Collidable>();
	private ArrayList<Drawable> items = new ArrayList<Drawable>();
	
	private boolean isGame = true;

	/**
	 * Initializes the drawing surface and the objects of the game.
	 */
	public GameSurface(){
				
		snowball = new Snowball(0,0,0,INIT_RADIUS);
		add(snowball);
		
		path = new Path(500, 1000);
		add(path);
		
		//TESTING OBSTACLES
		obstacles.add(new Obstacle(-path.getWidth()/2, 0, 0, path.getWidth()/3, INIT_RADIUS*2, INIT_RADIUS*2));
		obstacles.add(new Obstacle(-path.getWidth()/6, 0, -200, path.getWidth()/3, INIT_RADIUS*2, INIT_RADIUS*2));
		obstacles.add(new Obstacle(path.getWidth()/6, 0, -600, path.getWidth()/3, INIT_RADIUS*2, INIT_RADIUS*2));
		
		for(int x = 0; x < obstacles.size(); x++){
			add(obstacles.get(x));
		}
		

	}
	
	/**
	 * Sets up the drawing surface.
	 */
	public void setup(){
		noStroke();
		lights();		
		size(700,700,P3D);
	} 
	 
	/**
	 * Calls the act methods of all the game objects once.
	 */
	public void runOnce() {	 
		snowball.act();	
	    
	}
	
	/**
	 * Adds the object to be drawn. Must be called in order to have the object show up.
	 * @param c the object to be drawn.
	 */
	public void add(Drawable c){
		items.add(c);
	}
	
	/**
	 * Runs repeatedly to draw all the objects onto the screen.
	 */
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
	
	/**
	 * Handles key presses.
	 */
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
