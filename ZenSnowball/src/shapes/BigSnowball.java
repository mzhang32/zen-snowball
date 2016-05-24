package shapes;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.vecmath.Point3d;

import processing.core.PApplet;

public class BigSnowball extends Obstacle{
	
	float r;
	public BigSnowball(float x, float y, float z, float r) {
		super(x, y, z);
		this.r = r;
	}
	
	public void act(){
		
		super.setZ((float)(getZ() + 25));
	}
	
	public Bounds getBoundingShape() {
		Point3d center = new Point3d(getX(), getY(), getZ());
		return new BoundingSphere(center, r);
	}
	
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