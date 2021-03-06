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
import processing.core.PImage;
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
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private ArrayList<Drawable> items = new ArrayList<Drawable>();
	
	private boolean isGame = false;
	private boolean isStartScreen = true;
	private boolean isInstructions = false;

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
		fill(255);	
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
		
		else if (isStartScreen){
			PImage img = loadImage("ZenSnowball.png");
			image (img,0,0);
			stroke(0);
			noFill();
			String play = "PLAY";
			fill(0);
			textAlign(CENTER);
			textSize(56);
			text(play, 60, 350, 140, 75);  
			noFill();
			String instructions = "INSTRUCTIONS";
			fill(0);
			textAlign(LEFT);
			textSize(56);
			text(instructions, 250, 350, 425, 75);  
			
			if(mousePressed && overRect(60, 350, 140, 75)){
				isGame = true;
				isStartScreen= false;
				isInstructions = false;
			}
			
			else if(mousePressed && overRect(250, 350, 425, 75)){
				isGame = false;
				isStartScreen= false;
				isInstructions = true;
			}
			fill(255);
		}
		else if(isInstructions){
			background(0,0,255);
			stroke(255,0,0);
			fill(255,0,0);
			String instructions = "This is the instructions page.";
			fill(255);
			textAlign(LEFT);
			textSize(64);
			text(instructions, 50, 50, 500, 500);  
			
			String goBack = "GO BACK TO START SCREEN";
			fill(0);
			textAlign(LEFT);
			textSize(24);
			text(goBack, 250, 550, 425, 75);  

			if(mousePressed && overRect(250, 550, 425, 75)){
				isGame = false;
				isStartScreen= true;
				isInstructions = false;
			}
			
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
	private boolean overRect(int x, int y, int width, int height)  {
		  if (mouseX >= x && mouseX <= x+width && 
		      mouseY >= y && mouseY <= y+height) {
		    return true;
		  } else {
		    return false;
		  }
		}
	
}
