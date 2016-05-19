package shapes;

import javax.media.j3d.BoundingBox;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3d;

import processing.core.PApplet;

/**
 * This class represents the "rocks" the Snowball is not supposed to collide with.
 * 
 * @author Michelle Z. and Waveley Q.
 * @version 05.16.2016
 */
public class Obstacle extends Shape3D implements Collidable, Drawable{
	public static int score;
	private float x, y, z;
	private float width, height, depth;
	
	/**
	 * Creates an Obstacles with bottom-left-closest to viewer-corner coordinates at x, y, z, 
	 * and dimensions width, height, depth.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 * @param width the width of the obstacle
	 * @param height the height of the obstacle
	 * @param depth the depth of the obstacle
	 */
	public Obstacle(float x, float y, float z, float width, float height, float depth) {
		this.x = x;
		this.y = y; 
		this.z = z;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	
	/**
	 *  Draws the obstacle as a rectangular prism.
	 *  
	 *  @param p the PApplet used to draw the obstacle.
	 */
	public void draw(PApplet p) {
		p.pushMatrix();
		p.pushStyle();
		p.stroke(0);
		p.lights();
		p.translate(x+width/2,y-height/2,z-depth/2); //Must translate to draw b/c processing drawing from center
		p.box(width, height, depth);
		p.popStyle();
		p.popMatrix();	
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
		z+=2;

	}
	
	public BoundingBox getBoundingBox() {
		Point3d min = new Point3d(x, y-height, z-depth);
		Point3d max = new Point3d(x+width, y, z);
		return new BoundingBox(min, max);
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}
	public double getWidth(){
		return width;
	}
	public double getHeight(){
		return height;
	}
	public double getDepth(){
		return depth;
	}
	
}
