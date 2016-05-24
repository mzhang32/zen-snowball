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
		//System.out.println("Drawing tree");		
		p.pushMatrix();
		p.pushStyle();
		//p.translate((float)(getX()+width/2),(float)(getY()-height/2),(float)(getZ()-depth/2)); //Must translate to draw b/c processing drawing from center
		
		//p.translate(x, y - image.height, z);
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
