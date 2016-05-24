package shapes;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.vecmath.Point3d;

import processing.core.PApplet;

/**
 * Represents a little snowball that will increase the size of snowball upon collision.
 * 
 * @author Michelle Zhang and Waveley Qiu
 * @version 5.23.2016
 */
public class LittleSnowball extends Obstacle{
	
	private float r;
	private static final int MOVE_BY = 10;
	
	/**
	 * Constructs a little snowball with bottom point at (x, y, z) and radius r.
	 * @param x the x coordinate of the bottom point of little snowball.
	 * @param y the y coordinate of the bottom point of little snowball.
	 * @param z the z coordinate of the bottom point of little snowball.
	 * @param r the radius of the little snowball.
	 */
	public LittleSnowball(float x, float y, float z, float r) {
		super(x, y, z);
		this.r = r;
	}
	
	/**
	 * Moves the little snowball forward by a set amount.
	 */
	public void act(){
		super.setZ((float)(getZ() + MOVE_BY));
	}
	
	/**
	 * Returns the bounding shape in the same size as the little snowball.
	 */
	public Bounds getBoundingShape() {
		Point3d center = new Point3d(getX(), getY(), getZ());
		return new BoundingSphere(center, r);
	}
	
	/**
	 * Draws the little snowball.
	 * @param p the PApplet used to draw the snowball.
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
