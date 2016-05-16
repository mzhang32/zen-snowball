package shapes;

import javax.media.j3d.Shape3D;

import processing.core.PApplet;

/**
 * This class represents the "rocks" the Snowball is not supposed to collide with.
 * 
 * @author mzhan
 * 
 */
public class Obstacle extends Shape3D implements Collidable, Drawable{

	private float x, y, z;
	private float width, height, depth;
	
	/**
	 * Creates an Obstacles with bottom-left-closest to viewer-corner coordinates at x, y, z, 
	 * and dimensions width, height, depth.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param width
	 * @param height
	 * @param depth
	 */
	public Obstacle(float x, float y, float z, float width, float height, float depth) {
		this.x = x;
		this.y = y; 
		this.z = z;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	
	public void draw(PApplet p) {
		p.pushMatrix();
		p.pushStyle();
		p.stroke(0);
		p.noLights();
		p.translate(x+width/2,y-height/2,z-depth/2); //Must translate to draw b/c processing drawing from center
		p.box(width, height, depth);
		p.popStyle();
		p.popMatrix();	
	}

	public boolean collides() {

		return false;
	}

	public void move(float x, float y, float z) {
		
	}
	public void act(){
		
	}
	
}
