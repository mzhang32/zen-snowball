package shapes;

import java.util.ArrayList;

import processing.core.PApplet;


/**
 * This class handles creation of obstacles and moving them.
 * 
 * @author Michelle Z. and Waveley Q.
 * @version 05.16.2016
 */
public class Path implements Drawable{
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private float width;
	private float depth;
	
	/**
	 * Constructs a path with a width and a depth(distance into the screen from the game's origin)
	 * 
	 * @param width the width of the path.
	 * @param depth the distance into the -z from (0, 0, 0)
	 */
	public Path(float width, float depth) {
		this.width = width;
		this.depth = depth;
	}
	
	/**
	 * Returns the width of the path.
	 * @return the width of the path.
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * Calls the act method for every obstacle on the path
	 * @post All the obstacles on the path will move towards the positive z
	 */
	public void act() {
		for(Obstacle rock : obstacles) {
			rock.act();
		}
		//TODO: Generate new obstacles at appropriate time in appropriate place.
	}
	
	/**
	 *  Draws the path.
	 *  
	 *  @param p the PApplet used to draw the obstacle.
	 */
	public void draw(PApplet p) {
		//System.out.println("Draw path called");
		p.pushMatrix();
		p.pushStyle();
		p.stroke(0);
		p.line(-width/2, 0, 0, -width/2, 0, -depth);//need to change z-coord
		p.line(width/2, 0, 0, width/2, 0, -depth);
		p.line(-width/2, 0, 0, width/2, 0, 0); //delete later x axis
		p.line(0, -1000, 0, 0, 100, 0);//delete later y axis
		p.line(0, 0, 10, 0, 0, -1000);//delete later z axis
		p.popMatrix();
		p.popStyle();
	}
	
	
}
