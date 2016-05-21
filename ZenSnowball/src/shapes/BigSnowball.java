package shapes;

import processing.core.PApplet;

public class BigSnowball extends Snowball implements Drawable{
	
		float x;
		float y;
		float z;
		float r;
		
	/**
	 * Constructs a BigSnowball with center (x,y,z) and radius r. 
	 * @param x x coordinate of center of BigSnowball
	 * @param y y coordinate of center of BigSnowball
	 * @param z zcoordinate of center of BigSnowball
	 * @param r radius of BigSnowball
	 */
	public BigSnowball(float x, float y, float z, float r) {
		super(x, y, z, r);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets called repeatedly to move forward towards the player. Also handles collision detection.
	 */
	public void act(Path path) {
		z+=15;
	}
	
	/**
	 *  Draws the obstacle as a Sphere.
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