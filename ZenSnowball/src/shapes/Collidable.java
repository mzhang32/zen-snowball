package shapes;

import javax.media.j3d.Bounds;

import processing.core.PApplet;

/**
 * This interface represents objects in which collision detection will be implemented.
 * 
 * @author Michelle Z and Waveley Q
 * @version 05.23.2016
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

	
}
