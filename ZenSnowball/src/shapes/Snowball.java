package shapes;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3d;

import processing.core.PApplet;

/**
 * This class represent a snowball, a jumping and rolling object that interacts with obstacles.
 * 
 * @author Michelle Z. and Waveley Q.
 * @version 05.16.2016
 *
 */
public class Snowball extends Shape3D implements Collidable{
	public static int score;
	private static final double GRAVITY = 0.7;
	private double xVelocity, yVelocity;
	private static final double JUMP_STRENGTH = 15;
	private static final double MOVE_SPEED = 5;
	private float x, y, z, r;
	private boolean isColliding;
	private boolean isGameOver;

	private ArrayList<Shape> obstacles;
	private boolean onASurface = true, touchingXSurface = false, touchingYSurface = false, touchingZSurface = false;

	
	/**
	 * Creates Snowball centered at (x,y, z) with radius r
	 * @param x x-coordinate of the Snowball's bottom point
	 * @param y y-coordinate of the Snowball's bottom point
	 * @param z z-coordinate of the Snowball's bottom point
	 * @param r radius of the Snowball
	 */
	public Snowball(float x, float y, float z, float r){
		score = 0;
		this.x = x;
		this.y = y; 
		this.z = z;
		this.r = r;
		isColliding = false;
		isGameOver = false;
	}
	

	/**
	 *  Draws the snowball.
	 *  
	 *  @param p the PApplet used to draw the obstacle.
	 */
	public void draw(PApplet p){
		p.pushStyle();
		p.pushMatrix();
		p.fill(255, 255, 255);
		p.noStroke();
		p.translate(x,y-r/2,z);				
		p.sphere(r);	
		p.popMatrix();
		p.popStyle();
	}

	/**
	 * Detects whether the object is colliding with another collidable.
	 * 
	 * @return true if the object collides with another object.
	 */
	
	public void moveToOrigin(){
		x = 0;
		y = 0;
		z = 0;
	}


	/**
	 *  Decreases the snowball's velocity in the x direction by a set amount.
	 */
	public void moveLeft(){
		xVelocity = -MOVE_SPEED;
	}
	
	/**
	 *  Increases the snowball's velocity in the x direction by a set amount.
	 */
	public void moveRight(){
		xVelocity = MOVE_SPEED;
	}
	
	/**
	 * Increases the y velocity in the upward direction by a set amount.
	 */
	public void jump(){
		yVelocity -= JUMP_STRENGTH;
	}
	
	
	/**
	 * returns radius of the Snowball. 
	 * @return returns radius of the Snowball. 
	 */
	public float getRadius(){
		return r;
	}
	
	/**
	 * Gets called repeatedly to move the snowball according to the current velocities within the bounds of 
	 * the game.
	 * Also controls the snowball's size over time and handles collisions.
	 */
	public void act(Path path) {
		
		ArrayList<Obstacle> obs = path.getObstacles();
		
		Obstacle curColliding = null;
		for(int i = 0; i < obs.size(); i++) {
			Obstacle o = obs.get(i);
			if(this.collides(o)) {
				score--;
				System.out.println("Snowball is colliding with something");
				isColliding = true;
				curColliding = o;
				break;
			}
			else if(i == obs.size() - 1) {
				isColliding = false;
			}
		}
		
		if(isColliding) { //does not allow snowball to fall into obstacle from above
			if(this.z - this.r <= curColliding.getZ()) {
				this.z = (float)curColliding.getZ() + this.r;
			}
			else {
				yVelocity = 0;
				y = -(float)curColliding.getHeight() - 20; //sketchy stuff
			}	
			System.out.print("obsheight is " + curColliding.getHeight() + " and snowball y is " + this.y);
		}
		
		y += yVelocity;
		if(y < 0){		
			yVelocity += GRAVITY;		
		}
		else if(y >= 0){
			yVelocity = 0;
			y = 0;
		}		
		if(yVelocity == 0){
			x +=xVelocity;
			if(x >= path.getWidth()/2 ){
				xVelocity = 0;
				x = path.getWidth()/2;
			}
			if(x <= -path.getWidth()/2 ){
				xVelocity = 0;
				x = -path.getWidth()/2;
			}
		}
		
		if(z > Path.WHEN_STUFF_DISSAPEARS) {
			isGameOver = true;
		}
			
			
	}
		
	/**
	 * 
	 * @return A bounding sphere.
	 */
	public Bounds getBoundingShape() {
		Point3d center = new Point3d(x, y, z);
		return new BoundingSphere(center, r);
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
	 *Returns whether or not the snowball is on a surface.  
	 * @return boolean true if on a surface, false if otherwise. 
	 */
	public boolean onSurface(){
		return onASurface;
	}

	/** 
	 * @return boolean true if is colliding, false if otherwise. 
	 */
	public boolean isColliding() {
		return isColliding;
	}
	

	/**
	 * Does nothing. 
	 */

	public boolean isGameOver() {
		return isGameOver;
	}
	
	public void act() {
		
	}
	/**
	 * Returns y coordinate of the snowball's center
	 * @return y value of the snowball's center. 
	 */
	public double getY() {
		return y;
	}
		
}

