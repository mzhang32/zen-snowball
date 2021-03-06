package shapes;


import java.util.ArrayList;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3d;

import processing.core.PApplet;

/**
 * This class represents a snowball, a jumping and rolling object that interacts with obstacles.
 * 
 * @author Michelle Z. and Waveley Q.
 * @version 05.23.2016
 *
 */
public class Snowball extends Shape3D implements Collidable{

	private int lives;
	public static final double INIT_RADIUS = 30.0;
	public static final double WIN_RADIUS = 60;//Set to 60
	
	private static final double GRAVITY = 0.7;
	private double xVelocity, yVelocity, zVelocity;
	private static final double JUMP_STRENGTH = 15;
	private static final double MOVE_SPEED = 5;
	
	private float x, y, z, r;
	private boolean isColliding, isEating, isDead;
	private boolean isGameOver;
	private boolean isWin;

	
	/**
	 * Creates Snowball centered at (x,y, z) with radius r
	 * @param x x-coordinate of the Snowball's bottom point
	 * @param y y-coordinate of the Snowball's bottom point
	 * @param z z-coordinate of the Snowball's bottom point
	 * @param r radius of the Snowball
	 */
	public Snowball(float x, float y, float z, float r){

		lives = 3;
		this.x = x;
		this.y = y; 
		this.z = z;
		this.r = r;
		isDead = false;
		isColliding = false;
		isEating = false;
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
		if(isColliding || isDead)
			p.fill(255, 0, 0);
		else
			p.fill(255, 255, 255);
		p.noStroke();
		p.translate(x,y-r/2,z);				
		p.sphere(r);	
		p.popMatrix();
		p.popStyle();
	}

	/**
	 * Moves the snowball to (0, 0, 0).
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
	 * Sets the snowball's velocity in the z direction to MOVE_SPEED.
	 */
	public void moveUp(){
		zVelocity = MOVE_SPEED;
	}
	
	/**
	 * Increases the y velocity in the upward direction by a set amount.
	 */
	public void jump(){
		yVelocity -= JUMP_STRENGTH;
	}
	
	
	/**
	 * Returns the radius of the Snowball. 
	 * @return radius of the Snowball. 
	 */
	public float getRadius(){
		return r;
	}
	
	/**
	 * Gets called repeatedly to move the snowball according to the current velocities within the bounds of 
	 * the game.
	 * Also controls the snowball's size over time and handles collisions.
	 * @param path the Path on which the snowball is operating on.
	 */
	public void act(Path path) {
		
		ArrayList<Obstacle> obs = path.getObstacles();
		
		Collidable curColliding = null;
		for(int i = 0; i < obs.size(); i++) {
			Collidable o = obs.get(i);
			if(this.collides(o)) {
				isColliding = true;
				if(r > INIT_RADIUS)
					r -= .5;
				curColliding = o;
				break;
			}
			else if(i == obs.size() - 1) {
				isColliding = false;
			}
		}
		
		if(isColliding){
			if(curColliding instanceof Rock) { //does not allow snowball to fall into obstacle from above
				isEating = false;
				if(this.z - this.r <= curColliding.getZ()) {
					this.z = (float)curColliding.getZ() + this.r;
				}
				else {
					yVelocity = 0;
					y = -(float)((Rock) curColliding).getHeight() - 20; //sketchy stuff
				}				
			}	
			else 
				isEating = false;
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
		
		if(z > 0)
			z -= zVelocity;
		
		if(z > Path.WHEN_STUFF_DISSAPEARS) {
			isDead = true;
			lives--;
		}
		else if(r > WIN_RADIUS) {
			isWin = true;
			isGameOver = true;
		}
		
		if(r % 10 > 5) {
			if(path.getYTilt() > Path.MIN_TILT)
				path.setYTilt(path.getYTilt()-.3);
		}
		else{
			if(path.getYTilt() < Path.MAX_TILT)
				path.setYTilt(path.getYTilt()+.3);
		}
		
		r += .01;	

	}
		
	/**
	 * Returns a bounding shape the same size as the snowball.
	 * @return A bounding shape.
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
			isColliding = true;
			return true;
		}
		else{
			isColliding = false;
			return false;
		}
	}
	
	/**
	 * Returns whether the snowball is colliding with a little snowball.
	 * @param c the other object to check.
	 * @return true if the snowball is colliding with a little snowball.
	 */
	public boolean isEating(Collidable c){
		if (c instanceof LittleSnowball && collides(c)){
			isEating = true;
		}
		else
			isEating = false;
		return isEating;
	}
	
	/**
	 * Returns whether the snowball is colliding with a big snowball.
	 * @param c the other object to check.
	 * @return true if the snowball is colliding with a big snowball.
	 */
	public boolean isDying(Collidable c){
		if (c instanceof BigSnowball && collides(c)){
			System.out.println("collide with big snowbalL!");
			isDead = true;
			lives--;
			if(lives <1){
				isGameOver = true;
			}
		}
		return isDead;
	}
	
	
	/**
	 * Returns whether or not the snowball is on a surface.  
	 * @return boolean true if on a surface, false if otherwise. 
	 */
	public boolean onSurface(){
		return y==0;
	}

	/** 
	 * @return boolean true if the snowball is colliding with an obstacle.
	 */
	public boolean isColliding() {
		return isColliding;
	}
	
	/**
	 * 
	 * @return true if the snowball is colliding with a big snowball.
	 */
	public boolean isDead(){
		return isDead;
	}
	
	/**
	 * Set the state of the snowball as dead or live.
	 * @param b true if alive and false is dead.
	 */
	public void revive(boolean b){
		isDead = b;
	}

	/**
	 * Returns whether the game is over.
	 * @return true if the game is over.
	 */
	public boolean isGameOver() {
		return isGameOver;
	}
	
	/**
	 * Sets whether the game is over.
	 * @param b whether the game is over.
	 */
	public void setIsGameOver(boolean b){
		isGameOver = b;
	}
	
	/**
	 * Sets the radius back to the initial size.
	 */
	public void resetRadius(){
		r = (float)INIT_RADIUS;
	}
	
	/**
	 * Resets the radius and lives of the snowball.
	 */
	public void restart(){
		r = (float)INIT_RADIUS;
		lives = 3;
		isGameOver = false;
		isDead = false;
	}
	
	/**
	 * Returns whether the game has been won.
	 * @return true if the game has been won.
	 */
	public boolean isWin() {
		return isWin;
	}
	
	/**
	 * Returns the score.
	 * @return the score.
	 */
	public int getScore() {
		return (int)(r - INIT_RADIUS);
	}
	
	/**
	 * Returns the number of lives left.
	 * @return The number of lives left.
	 */
	public int getLives(){
		return lives;
	}

	/**
	 * Returns y coordinate of the snowball's center
	 * @return y value of the snowball's center. 
	 */
	public double getY() {
		return y;
	}

	/**
	 * Returns z coordinate of the snowball's center
	 * @return z value of the snowball's center. 
	 */
	public double getZ() {
		return z;
	}
		
}

