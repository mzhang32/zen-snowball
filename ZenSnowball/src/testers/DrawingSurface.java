package testers;
import java.util.ArrayList;

import processing.core.PApplet;
import shapes.*;

/**
 * 
 * @author WaveleyQiu, Michelle Z.
 *
 */
public class DrawingSurface extends PApplet{
	
	ArrayList<Collidable> items = new ArrayList<Collidable>();
	//Snowball snowball = new Snowball(200,200,200,100);
	
	public DrawingSurface(){
		
	}
	
	public void add(Collidable c){
		items.add(c);
	}
	
	public void setup(){
		System.out.println("no problem here");
		noStroke();
		lights();
		size(700,700,P3D);
		System.out.println("no problem here");
	}
	
	public void draw(){
		colorMode(RGB);
		
		background(255);
		stroke(0);
		for(int x = 0; x < items.size(); x++){
			items.get(x).draw(this);
		}
	}
	
}
