package shapes;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.media.j3d.Shape3D;
import javax.vecmath.Point3d;

import processing.core.PApplet;

public class Snowball extends Shape3D implements Collidable, Drawable{
		private static final double GRAVITY = 0.7;
		private double xVelocity, yVelocity;
		private static final double JUMP_STRENGTH = 15;
		private static final double MOVE_SPEED = 5;
		private float x, y, z, r;

		private ArrayList<Shape> obstacles;
		private boolean onASurface = true;

		
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
		
		public void draw(PApplet p){
			p.pushStyle();
			p.pushMatrix();
			p.noStroke();
			p.translate(x,y-r/2,z);
			p.sphere(r);
			p.popMatrix();
			p.popStyle();
		}
		
		public void roll(){
			
		}
		
		public Point3d getCenter(){
			return new Point3d(x,y,z);
		}

		public boolean collides() {
			// TODO Auto-generated method stub
			return false;
		}

		public void move(float x, float y, float z) {
			// TODO Auto-generated method stub
			
		}
		
		public void moveLeft(){
			xVelocity = -MOVE_SPEED;
		}
		
		public void moveRight(){
			xVelocity = MOVE_SPEED;
		}
		
		public void jump(){
			yVelocity -= JUMP_STRENGTH;
		}
		
		public void act(ArrayList<Shape> obstacles) {
			
		}

		public void act() {
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
			x +=xVelocity;
			if(x > 200 || x < -200){
				xVelocity = 0;
			}
			
			
			// TODO Auto-generated method stub
			
		}
}
