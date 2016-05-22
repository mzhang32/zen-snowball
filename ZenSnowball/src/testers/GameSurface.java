package testers;

//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import javax.swing.JPanel;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.geom.AffineTransform;
//
//import javax.media.j3d.BranchGroup;
//import javax.swing.*;

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
	private int jumpCount = 1;
	private Snowball snowball;
	//private BigSnowball bigsnowball;
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

		//bigsnowball = new BigSnowball(0,0,-500, (float)(snowball.getRadius()*1.5));
		//items.add(bigsnowball);

		path = new Path(500, 1000);
		add(path);		
		obstacles = path.getObstacles();

	}
	/**
	 * Restarts the game. 
	 */
	public void restart(){
		background(255);
		path = new Path(500, 1000);
		add(path);		
		obstacles = path.getObstacles();
		snowball.moveToOrigin();
	}
	
	/**
	 * Sets up the drawing surface.
	 */
	public void setup(){
		noStroke();
		lights();		
		size(700,700,P3D);
		colorMode(RGB);
	} 
	 
	/**
	 * Calls the act methods of all the game objects once.
	 */
	public void runOnce() {	 
		path.act();
		snowball.act(path);
		//bigsnowball.act(path);
		
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
		background(255, 255, 255);
		//System.out.println("draw() was called");
		if(isGame){
			//int time = millis();
			pushMatrix();
			pushStyle();
			colorMode(RGB);	
			lights();
			background(255);
			stroke(0,0,255,100);
			fill(0, 0,255,100);
			rect(0,0,700,350);
			fill(0,0,255,50);		
			
			beginShape();
			vertex(0, 350);
			vertex(0, 700);
			vertex(30, 700);
			vertex(250, 350);
			endShape(CLOSE);
			
			fill(0,0,255,50);
			beginShape();
			vertex(30, 20);
			vertex(85, 20);
			vertex(85, 75);
			vertex(30, 75);
			endShape(CLOSE);
			
			pushStyle();			
			background(255);
			stroke(0);		
			String back = "BACK";
			fill(0);
			textAlign(LEFT);
			textSize(24);
			text(back, 10, 10, 100, 75);  
			String score = "Score: " + snowball.getScore();
			fill(0);
			textAlign(RIGHT);
			textSize(24);
			text(score, 450, 10, 200, 75);  
			fill(255);
			pushStyle();
			popMatrix();
			if(mousePressed && overRect( 10, 10, 100, 75)){
				isGame = false;
				isStartScreen= true;
				isInstructions = false;
				restart();
			}
			
			fill(255);	
			pushMatrix();
			//camera(); //will do something with camera later
			camera(width/2.0f, height/2.0f + path.getYTilt(), (float)((height/2.0) / Math.tan(Math.PI*30.0 / 180.0)), width/2.0f, height/2.0f, 0f, 0f, 1f, 0f);
			translate(width/2, height-height/10, -Z_FROM_SCREEN); //IMPORTANT translated everything
			
			popStyle();
			
			lights();
			for(int x = 0; x < items.size(); x++){
				items.get(x).draw(this);
			}
			for(int x = 0; x < obstacles.size(); x++){
				obstacles.get(x).draw(this);
			}
			popMatrix(); 
			

			if(!snowball.isGameOver()) {
				runOnce();
			}	
			else if(snowball.isGameOver()) {
				pushStyle();
				textSize(30);
				textAlign(CENTER);
				fill(255, 0, 0);
				text("Game Over", width/2, height/3);
				text("Score: " + snowball.getScore(), width/2, height/3 + height/15);
				popStyle();
			}

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
			pushStyle();
			String goBack = "GO BACK TO START SCREEN";
			fill(0);
			textAlign(LEFT);
			textSize(24);
			text(goBack, 250, 550, 425, 75);  
			popStyle();
			if(mousePressed && overRect(250, 550, 425, 75)){
				isGame = false;
				isStartScreen= true;
				isInstructions = false;
			}
			
		}
		
	}
		
	
	/**
	 * Handles key presses.
	 */
	public void keyPressed(){
		
	if( jumpCount > 0 && keyCode == ' '){			
			System.out.println("Jump Count: " + jumpCount);
			snowball.jump();			
			jumpCount--;

		}
		
		 if(snowball.onSurface()){
			jumpCount = 1;
		}
	
		 if(keyCode == LEFT){
			snowball.moveLeft();
			jumpCount = 1;
		}
		
		 if(keyCode == RIGHT){
			snowball.moveRight();
			jumpCount = 1;
		}
	
		 if(keyCode == UP){
			 snowball.moveUp();
		 }
		
		
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
