package shapes;

import javax.media.j3d.Shape3D;
import javax.vecmath.Point3d;

import processing.core.PApplet;

public class Snowball extends Shape3D implements Collidable{
		private static final double GRAVITY = -3;
		private double xVelocity, yVelocity;
		private static final double JUMP_STRENGTH = 15;
		float x, y, z, r;
		
		/**
		 * Creates Snowball centered at (x,y, z) with radius r
		 * @param x x-coordinate of the Snowball's center
		 * @param y y-coordinate of the Snowball's center
		 * @param z z-coordinate of the Snowball's center
		 * @param r radius of the Snowball
		 */
		public Snowball(float x, float y, float z, float r){
			this.x = x;
			this.y = y; 
			this.z = z;
			this.r = r;
			System.out.println("works");
			
		}
		
		public void draw(PApplet p){
			p.pushMatrix();
			p.noStroke();
			p.lights();
			p.translate(x,y,z);
			p.sphere(r);
			p.popMatrix();
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
		public void jump(){
			yVelocity += JUMP_STRENGTH;
		}
		
		public void act(){
			System.out.println("Snowball act method");
			x += xVelocity;
			y += yVelocity;
			
			if( y > 0) {
				yVelocity -= GRAVITY;
			}
			
		}
}
