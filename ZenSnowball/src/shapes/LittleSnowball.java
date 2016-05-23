package shapes;

import processing.core.PApplet;

public class LittleSnowball extends Snowball{
	float x;
	float y;
	float z;
	float r;
	
	public LittleSnowball(float x, float y, float z, float r) {
		super(x, y, z, r);
		// TODO Auto-generated constructor stub
	}
	
	public void act(){
		this.z+=15;
	}
	
	public void act(Path p){
		int y = 0;
	}
	public void draw(PApplet p){
		super.draw(p);
	}
}
