package shapes;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

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
public class Snowball extends Shape3D implements Collidable, Drawable{
		
	private static final double GRAVITY = 0.7;
	private double xVelocity, yVelocity;
	private static final double JUMP_STRENGTH = 15;
	private static final double MOVE_SPEED = 5;
	private float x, y, z, r;

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
		this.x = x;
		this.y = y; 
		this.z = z;
		this.r = r;
		
		
	}
	
	/**
	 *  Draws the snowball.
	 *  
	 *  @param p the PApplet used to draw the obstacle.
	 */
	public void draw(PApplet p){
		p.pushStyle();
		p.pushMatrix();
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
	public boolean collides(Obstacle o) {
		double lowerXBound = o.getX();
		double upperXBound = o.getX() + o.getWidth();
		double lowerYBound = o.getY();
		double upperYBound = o.getY()+o.getHeight();
		double lowerZBound = o.getZ();
		double upperZBound = o.getZ() + o.getDepth();
		if(x+r > lowerXBound && x+r < upperXBound){
			touchingXSurface = true;
			return true;
		}
		if(y + r >lowerYBound && y+r < upperYBound){
			touchingYSurface = true;
			return true;
		}
		if(z+r > lowerZBound && z+r < upperZBound){
			touchingZSurface = true;
			return true;
		}
		return false;
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
	
	public float getRadius(){
		return r;
	}
	
	/**
	 * Gets called repeatedly to move the snowball according to the current velocities within the bounds of 
	 * the game.
	 * Also controls the snowball's size over time and handles collisions.
	 */
		public void act(Path path) {
			y += yVelocity;
			if(y < 0){
				onASurface = false;
				yVelocity += GRAVITY;
				
			}
			else if(y >= 0){
				onASurface = true;
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
			
			// TODO Auto-generated method stub
			
		}

	@Override
	public boolean collides() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
		
	}

