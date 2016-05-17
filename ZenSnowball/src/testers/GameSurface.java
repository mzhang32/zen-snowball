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

public class GameSurface extends PApplet{
	
	
	public static final int Z_FROM_SCREEN = 50;
	public static final int OBSTACLE_WIDTH = 100;
	
	ArrayList<Drawable> items = new ArrayList<Drawable>();
	private Snowball snowball;
	private Path path;
	private ArrayList<Collidable> obstacles = new ArrayList<Collidable>();
	
	private boolean isGame = false;
	private boolean isStartScreen = true;
	private boolean isInstructions = false;

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
