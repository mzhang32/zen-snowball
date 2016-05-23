package shapes;
import javax.media.j3d.Bounds;
import javax.media.j3d.Shape3D;

import processing.core.PApplet;

public class Obstacle extends Shape3D implements Collidable{
	private float x, y, z;
	
	public Obstacle(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void draw(PApplet p) {
		
	}
	public void setX(float i){
		x = i;
	}
	public void setY(float i){
		y = i;
	}
	public void setZ(float i){
		z = i;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}
	public boolean collides(Collidable other) {
		if(this.getBoundingShape().intersect(other.getBoundingShape())){
			//isColliding = true;
			return true;
		}
		else{
			//isColliding = false;
			return false;
		}
	}

	public Bounds getBoundingShape() {
		// TODO Auto-generated method stub
		return null;
	}

	public void act() {
		// TODO Auto-generated method stub
		
	}

}
