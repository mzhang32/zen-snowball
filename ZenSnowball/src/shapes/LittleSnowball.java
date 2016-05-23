package shapes;

import processing.core.PApplet;

public class LittleSnowball extends Obstacle{
	float x;
	float y;
	float z;
	float r;
	
	public LittleSnowball(float x, float y, float z, float r) {
		super(x, y, z);
		this.r = r;
		// TODO Auto-generated constructor stub
	}
	
	public void act(){
		this.z+=15;
	}
	
	
	public void draw(PApplet p){

	
	}
}
