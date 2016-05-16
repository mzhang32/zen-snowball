package shapes;

import java.util.ArrayList;

import processing.core.PApplet;
import testers.GamePanel;

/**
 * This class handles creation of obstacles and moving them.
 * @author mzhan
 *
 */
public class Path implements Drawable{
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private float width;
	private float depth;
	
	public Path(float width, float depth) {
		this.width = width;
		this.depth = depth;
	}
	
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
	 * Draws the path.
	 * 
	 * @param p
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
