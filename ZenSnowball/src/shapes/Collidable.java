package shapes;

import javax.media.j3d.Bounds;

import processing.core.PApplet;

/**
 * This interface represents objects in which collision detection will be implemented.
 * 
 * 
 * @author Michelle Z and Waveley Q
 * @version 05.16.2016
 */
public interface Collidable extends Drawable{
	
	/**
	 * Detects whether the object is colliding with another collidable.
	 * 
	 * @return true if the object collides with another object.
	 */
	boolean collides(Collidable other);
	
	/**
	 * 
	 * @return bounds of the bounding shape. 
	 */
	public Bounds getBoundingShape();
	
	/**
	 * Gets called repeatedly. Checks for collisions and acts accordingly.
	 * 
	 * @post The x, y, z coordinates will be changed accordingly to collisions or other game dictated behavior.
	 */
	void act();
}
