package shapes;
import javax.media.j3d.Bounds;
import javax.media.j3d.Shape3D;

import processing.core.PApplet;

/**
 * Represents an obstacle. 
 * 
 * @author Michelle Zhang and Waveley Qiu
 * @version 05.23.2016
 *
 */
public class Obstacle extends Shape3D implements Collidable{
	private float x, y, z;
	
	/**
	 * Creates a new obstacle with coordinates (x, y, z).
	 * @param x the x coordinate.
	 * @param y the y coordinate. 
	 * @param z the z coordinate.
	 */
	public Obstacle(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 *  Draws nothing. Must use subclasses if want to draw things.
	 */
	public void draw(PApplet p) {
		
	}
	
	/**
	 * Sets the x coordinate of the obstacle.
	 * @param i the new x coordinate of the obstacle.
	 */
	public void setX(float i){
		x = i;
	}
	
	/**
	 * Sets the y coordinate of the obstacle.
	 * @param i the new y coordinate of the obstacle.
	 */
	public void setY(float i){
		y = i;
	}
	
	/**
	 * Sets the z coordinate of the obstacle.
	 * @param i the new z coordinate of the obstacle.
	 */
	public void setZ(float i){
		z = i;
	}
	
	/**
	 * Returns the x coordinate of the obstacle.
	 * @return the x coordinate of the obstacle.
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * Returns the y coordinate of the obstacle.
	 * @return the y coordinate of the obstacle.
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Returns the z coordinate of the obstacle.
	 * @return the z coordinate of the obstacle.
	 */
	public double getZ(){
		return z;
	}
	
	/**
	 * Checks to see of one object collides into the other. 
	 * @return boolean true if collides, false if otherwise. 
	 */
	public boolean collides(Collidable other) {
		if(this.getBoundingShape().intersect(other.getBoundingShape())){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 *  Returns null unless overridden by subclass.
	 */
	public Bounds getBoundingShape() {
		return null;
	}

	/**
	 * Does nothing unless overridden by child classes.
	 */
	public void act() {

	}

}
