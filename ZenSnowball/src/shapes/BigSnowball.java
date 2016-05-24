package shapes;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.vecmath.Point3d;

import processing.core.PApplet;

/**
 * Represents a big snowball that will engulf the snowball upon collision.
 * 
 * @author Michelle Z and Waveley Q
 * @version 05.23.2016
 */
public class BigSnowball extends Obstacle{
	
	private float r;
	private static final int MOVE_BY = 25;
	
	
	/**
	 * Constructs a big snowball with bottom point at (x, y, z) and radius r.
	 * @param x the x coordinate of the bottom point of big snowball.
	 * @param y the y coordinate of the bottom point of big snowball.
	 * @param z the z coordinate of the bottom point of big snowball.
	 * @param r the radius of the big snowball.
	 */
	public BigSnowball(float x, float y, float z, float r) {
		super(x, y, z);
		this.r = r;
	}
	
	/**
	 * Moves the big snowball forward by a set amount.
	 */
	public void act(){	
		super.setZ((float)(getZ() + MOVE_BY));
	}
	
	/**
	 * Returns a bounding shape half the size as the large snowball.
	 */
	public Bounds getBoundingShape() {
		Point3d center = new Point3d(getX(), getY(), getZ());
		return new BoundingSphere(center, r/2);
	}
	
	/**
	 * Draws the large snowball.
	 * 
	 * @param p the PApplet used to draw the large snowball.
	 */
	public void draw(PApplet p){
		p.pushStyle();
		p.pushMatrix();
		p.fill(255, 255, 255);
		p.noStroke();
		p.translate((float)(getX()),(float)(getY()-r/2),(float)(getZ()));				
		p.sphere(r);	
		p.popMatrix();
		p.popStyle();
	}
	
}