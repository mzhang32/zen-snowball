package shapes;

import processing.core.PApplet;
import processing.core.PImage;


public class Tree implements Drawable {

	public static final int WIDTH = 100;
	public static final int HEIGHT = 120;
	private float x, y, z;
	
	public Tree(float x, float y, float z) {

		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void draw(PApplet p) {

		p.pushMatrix();
		p.pushStyle();
		p.stroke(0);
		p.translate(x, y, z);		
		p.line(WIDTH/2, 0, 0, WIDTH/2, -HEIGHT, 0);
		p.line(0,  -HEIGHT/4, 0, WIDTH/2, -HEIGHT/2, 0);
		p.line(WIDTH/2, -HEIGHT/2, 0, WIDTH, -HEIGHT/4, 0);
		p.line(WIDTH/5, -HEIGHT/2, WIDTH/2, -HEIGHT*3/4);
		p.line(WIDTH*4/5, -HEIGHT/2, WIDTH/2, -HEIGHT*3/4);
		p.line(WIDTH/4, -HEIGHT*3/4, WIDTH/2, -HEIGHT);
		p.line(WIDTH*3/4, -HEIGHT*3/4, WIDTH/2, -HEIGHT);

		p.popStyle();
		p.popMatrix();	
	}

	@Override
	public void act() {
		z += 10;
		
	}

	@Override
	public double getZ() {
		
		return z;
	}

}
