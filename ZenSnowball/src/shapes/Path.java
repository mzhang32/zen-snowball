package shapes;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * This class handles creation of obstacles and moving them.
 * @author mzhan
 *
 */
public class Path {
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private float width;
	
	public Path(float width) {
		this.width = width;
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
	
	public void draw(PApplet p) {
		
	}
	
	
}
