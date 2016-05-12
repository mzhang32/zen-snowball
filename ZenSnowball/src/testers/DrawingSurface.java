package testers;
import java.util.ArrayList;

import processing.core.PApplet;
import shapes.*;

public class DrawingSurface extends PApplet{
	
	public DrawingSurface(){
		
	}
	ArrayList<Collidable> items = new ArrayList<Collidable>();
	//Snowball snowball = new Snowball(200,200,200,100);
	public void add(Collidable c){
		items.add(c);
	}
	
	public void setup(){
			noStroke();
			lights();
		  size(650,650,P3D);
		  System.out.println("no problem here");
	}
	
	public void draw(){
		colorMode(RGB);
		background(255);
		for(int x = 0; x < items.size(); x++){
			items.get(x).draw(this);
		}
	}
	
}
