package shapes;

import processing.core.PApplet;

public class BigSnowball extends Snowball implements Drawable{
		float x;
		float y;
		float z;
float r;
	
	public BigSnowball(float x, float y, float z, float r) {
		super(x, y, z, r);
		// TODO Auto-generated constructor stub
	}
	
	public void act(Path path) {
		z+=15;
			}
	/**
	 *  Draws the obstacle as a rectangular prism.
	 *  
	 *  @param p the PApplet used to draw the obstacle.
	 */
	public void draw(PApplet p) {
		super.draw(p);
	}

	/**
	 * Detects whether the object is colliding with another collidable.
	 * 
	 * @return true if the object collides with another object.
	 */
	public boolean collides(Collidable other) {

		return false;
	}

	/**
	 * Gets called repeatedly to move forward towards the player. Also handles collision detection.
	 */
	public void act(){
		z+=10;

	}
	
}