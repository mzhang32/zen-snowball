package shapes;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a tree outside of the path.
 * 
 * @author Michelle Zhang and Waveley Qiu
 * @version 05.23.2016
 */
public class Tree implements Drawable {

	public static final int WIDTH = 100;
	public static final int HEIGHT = 120;
	private float x, y, z;
	
	/**
	 * Constructs a tree with width 100 and height 120 with lower left corner at (x, y, z). 
	 * @param x the lower left x coordinate of the tree.
	 * @param y the lower left y coordinate of the tree.
	 * @param z the lower left z coordinate of the tree.
	 */
	public Tree(float x, float y, float z) {

		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 *  Draws the tree.
	 *  
	 *  @param p the PApplet used to draw the tree.
	 */
	public void draw(PApplet p) {
		p.pushMatrix();
		p.pushStyle();
		p.stroke(0);
		p.translate(x, y, z);		
		p.line(WIDTH/2, 0, 0, WIDTH/2, -HEIGHT, 0);
		p.line(0,  -HEIGHT/4, 0, WIDTH/2, -HEIGHT/2, 0);
		p.line(WIDTH/2, -HEIGHT/2, 0, WIDTH, -HEIGHT/4, 0);
		p.line(WIDTH/5, -HEIGHT/2, WIDTH/2, -HEIGHT*3/4);
		p.line(WIDTH*4/5, -HEIGHT/2, WIDTH/2, -HEIGHT*3/4);
		p.line(WIDTH/4, -HEIGHT*3/4, WIDTH/2, -HEIGHT);
		p.line(WIDTH*3/4, -HEIGHT*3/4, WIDTH/2, -HEIGHT);

		p.popStyle();
		p.popMatrix();	
	}

	/**
	 * Moves the tree forward toward the screen.
	 */
	public void act() {
		z += 10;
		
	}

	/**
	 * Returns the z coordinate.
	 * @return the z coordinate of the tree.
	 */
	public double getZ() {
		
		return z;
	}

}
