package shapes;

import processing.core.PApplet;
import processing.core.PImage;


public class Tree implements Drawable {


	private float x, y, z;
	
	public Tree(float x, float y, float z) {

		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void draw(PApplet p) {
System.out.println("Drawing tree");		
		p.pushMatrix();
		p.pushStyle();
		p.stroke(0);
		p.translate(x, y, z);		
		p.line(40, 0, 0, 40, -100, 0);
		p.line(0,  -25, 0, 40, -50, 0);
		p.line(40, -50, 0, 80, -25, 0);

		p.popStyle();
		p.popMatrix();	
	}

	@Override
	public void act() {
		
		
	}

	@Override
	public double getZ() {
		
		return z;
	}

}
