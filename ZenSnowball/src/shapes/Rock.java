package shapes;


import javax.vecmath.Point3d;
import javax.media.j3d.Bounds;
import javax.media.j3d.BoundingBox;

import processing.core.PApplet;

/**
 * This class represents the "rocks" the Snowball is not supposed to collide with.
 * 
 * @author Michelle Z. and Waveley Q.
 * @version 05.23.2016
 */
public class Rock extends Obstacle{

	private float width, height, depth;
	private static final int MOVE_BY = 10;
	
	/**
	 * Creates an Rock with bottom-left-closest to viewer-corner coordinates at x, y, z, 
	 * and dimensions width, height, depth.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 * @param width the width of the obstacle
	 * @param height the height of the obstacle
	 * @param depth the depth of the obstacle
	 */
	public Rock(float x, float y, float z, float width, float height, float depth) {
		super(x,y,z);
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	
	/**
	 *  Draws the rock as a rectangular prism.
	 *  
	 *  @param p the PApplet used to draw the obstacle.
	 */
	public void draw(PApplet p) {
		p.pushMatrix();
		p.pushStyle();
		p.stroke(0);
		p.lights();
		p.translate((float)(getX()+width/2),(float)(getY()-height/2),(float)(getZ()-depth/2)); //Must translate to draw b/c processing drawing from center
		p.box(width, height, depth);
		p.popStyle();
		p.popMatrix();	
	}


	/**
	 * Gets called repeatedly to move forward towards the player.
	 */
	public void act(){
		super.setZ((float)(getZ() + MOVE_BY));
	}
	
	/**
	 * Returns a bounding shape the same size as this rock.
	 * 
	 * @return the bounding shape.
	 */
	public Bounds getBoundingShape() {
		Point3d min = new Point3d(getX(), getY()-height, getZ()-depth);
		Point3d max = new Point3d(getX() + width, getY(), getZ());
		return new BoundingBox(min, max);
	}
	
	/**
	 * Returns the width of this rock.
	 * @return the width of this rock.
	 */
	public double getWidth(){
		return width;
	}
	
	/**
	 * Returns the height of this rock.
	 * @return the height of this rock.
	 */
	public double getHeight(){
		return height;
	}
	
	/**
	 * Returns the depth of this rock.
	 * @return the depth of this rock.
	 */
	public double getDepth(){
		return depth;
	}

	
	
}
