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
	public static final int Z_ORIGIN = -2400;
	public static final int PATH_WIDTH = 500;
	private int jumpCount = 1;
	private Snowball snowball;
	private Path path;
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private ArrayList<Drawable> items = new ArrayList<Drawable>();
	private ArrayList<Tree> trees = new ArrayList<Tree>();

	private boolean isGame = false;
	private boolean isStartScreen = true;
	private boolean isInstructions = false;

	/**
	 * Initializes the drawing surface and the objects of the game.
	 */
	public GameSurface(){
		snowball = new Snowball(0,0,0,(int)Snowball.INIT_RADIUS);
		items.add(snowball);

		path = new Path(PATH_WIDTH, -Z_ORIGIN);
		add(path);		
		obstacles = path.getObstacles();

	}
	public void restartInGame(){
		background(255);
		fill(255);
		path = new Path(500, 1000);
		obstacles = path.getObstacles();
		snowball.revive(false);
		snowball.moveToOrigin();	
		items.add(snowball);
		add(path);	
		System.out.println("Game Restarted");
	}
	
	/**
	 * Restarts the game. 
	 */
	public void restart(){
		background(255);
		fill(255);
		path = new Path(500, 1000);
		obstacles = path.getObstacles();
		//snowball = new Snowball(0,0,0, (int)Snowball.INIT_RADIUS);	
		snowball.restart();
		snowball.moveToOrigin();	
		items.add(snowball);
		add(path);				

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
		path.act(snowball);
		snowball.act(path);
		moveEnvironment();
	}
	
	/**
	 * Adds the object to be drawn. Must be called in order to have the object show up.
	 * @param c the object to be drawn.
	 */
	public void add(Drawable c){
		items.add(c);
	}
	
	public void moveEnvironment() {
		for(int i = 0; i < trees.size(); i++) {
			Tree tree = trees.get(i);
			if(tree.getZ() > path.WHEN_STUFF_DISSAPEARS) {
				trees.remove(i);
				items.remove(tree);
				i--;
			}	
			else
				trees.get(i).act();
			
		}
	System.out.println((trees.size() + 1.0)/60);
		if(trees.size() < 15 && Math.random() < 3.0/(16*(trees.size()+1))) {	
			
			float randX;
			if(Math.random() < .5) 
				randX = (float)-Math.random()*(width) - path.getWidth()/2 - Tree.WIDTH;
			else
				randX = (float)Math.random()*(width) + path.getWidth()/2;
			Tree newTree = new Tree(randX, 0, Z_ORIGIN);
			trees.add(newTree);
			items.add(newTree);
		}
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
			pushStyle();			
			background(255);
			stroke(0);		
			String back = "BACK";
			fill(0);
			textAlign(LEFT);
			textSize(24);
			text(back, 10, 10, 100, 75);  
			String score = "Score: " + snowball.getScore() + "\nLives: " + snowball.getLives();
			fill(0);
			textAlign(RIGHT);
			textSize(24);
			text(score, 450, 10, 200, 75);  
			
			rectMode(CENTER);
			noFill();
			rect(width/2, height/20, width/2, 10);
			fill(0, 255, 0);
			rectMode(CORNER);
			double winInterval = Snowball.WIN_RADIUS - Snowball.INIT_RADIUS;
			rect(width/4f, (float)(height/20 - 5), (float)(width/2 * (snowball.getRadius()-Snowball.INIT_RADIUS)/winInterval), 10);
			
			fill(255);
			pushStyle();
			popMatrix();
			if(mousePressed && overRect( 10, 10, 100, 75)){
				isGame = false;
				isStartScreen= true;
				isInstructions = false;
				//items.remove(snowball);
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
			
			if(!snowball.isGameOver() && !snowball.isDead()) {
				runOnce();
			}	
			
			else if(snowball.isDead()){
				
				if(snowball.getLives() > 0){
					pushStyle();
					textSize(30);
					textAlign(CENTER);
					fill(255, 0, 0);
					text("You Died! Lives Left: " + snowball.getLives(), width/2, height/3);
					fill(0,0,0);
					textSize(18);
					text("Press SPACE to continue.", width/2, height/3+height/12);
					if(keyPressed){
						System.out.println("Key Pressed");
						restartInGame();
					}
				}
					else{
						pushStyle();
						textSize(30);
						textAlign(CENTER);
						fill(255, 0, 0);
						text("Game Over", width/2, height/3);
						text("Score: " + snowball.getScore(), width/2, height/3 + height/15);
						popStyle();
					}
				popStyle();

				}
				
				//popStyle();
			
			
			else if(snowball.isWin()) {
				pushStyle();
				textSize(30);
				textAlign(CENTER);
				fill(0, 255, 0);
				text("YOU WON!!! GOOD JOB!!", width/2, height/3);
				popStyle();
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
			String instructions = "INSTRUCTIONS: Use the left, right, and up arrows to navigate Zen through the blustery winter wonderland. Zen must avoid all dangerous �rocks�(by jumping over them) as well as all snowballs larger than itself (by moving out of their way). While hitting a �rock� will decrease Zen�s size by X%, if Zen is unable to move out of the larger snowball�s way, Zen will be absorbed into the larger snowball. ";
			fill(255);
			textAlign(LEFT);
			textSize(27);
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
