package shapes;

import java.util.ArrayList;

import processing.core.PApplet;


/**
 * This class handles creation of obstacles and moving them.
 * 
 * @author Michelle Z. and Waveley Q.
 * @version 05.16.2016
 */
public class Path implements Drawable{
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private float width;
	private float depth;
	
	/**
	 * Constructs a path with a width and a depth(distance into the screen from the game's origin)
	 * 
	 * @param width the width of the path.
	 * @param depth the distance into the -z from (0, 0, 0)
	 */
	public Path(float width, float depth) {
		this.width = width;
		this.depth = depth;
		obstacles.add(new Obstacle(-getWidth()/2, 0, 0, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(-getWidth()/6, 0, -600, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(getWidth()/6, 0, -600, getWidth()/3, 60, 60));	
		obstacles.add(new Obstacle(-getWidth()/2, 0, -400, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(-getWidth()/6, 0, -1000, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(-getWidth()/6, 0, -200, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(getWidth()/6, 0, -800, getWidth()/3, 60, 60));	
		obstacles.add(new Obstacle(-getWidth()/2, 0, -800, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(-getWidth()/2, 0, -1000, getWidth()/3, 60, 60));
	}
	
	/**
	 * Generates a random obstacle in one of 3 x-positions. 
	 * @param z z-coordinate that the obstacle is drawn. 
	 */
	public void generateObstacle(float z){
		double x = Math.random()*3;
		
		if(x > 2){
			obstacles.add(new Obstacle(-getWidth()/2, 0, z, getWidth()/3, 60, 60));
		}
		
		else if(x > 1){
			obstacles.add(new Obstacle(-getWidth()/6, 0, z, getWidth()/3, 60, 60));
		}
		
		else if(x > 0){
			obstacles.add(new Obstacle(getWidth()/6, 0, z, getWidth()/3, 60, 60));	
		}
		
	}
		
	/**
	 * Returns the width of the path.
	 * @return the width of the path.
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * Returns the ArrayList of obstacles. 
	 * @return the ArrayList of obstacles
	 */
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}
	
	/**
	 * Adds an ArrayList of Obstacles to the obstacles ArrayList. 
	 * @param o ArrayList of Obstacles
	 */
	
	public void addObstacles(ArrayList<Obstacle> o){
		for(int x = 0; x < o.size(); x++){
			obstacles.add(o.get(x));
		}
	}
	
	
	/**
	 * Calls the act method for every obstacle on the path
	 * @post All the obstacles on the path will move towards the positive z
	 */
	public void act() {
		for(int x = 0; x < obstacles.size(); x++) {
			obstacles.get(x).act();
			
			if(obstacles.get(x).getZ() > 250){
				obstacles.remove(x);					
					
				}	
			if(obstacles.size() < 9)
			generateObstacle(-1600);


			}

		}
	
	/**
	 *  Draws the path.
	 *  
	 *  @param p the PApplet used to draw the obstacle.
	 */
	public void draw(PApplet p) {
		p.pushMatrix();
		p.pushStyle();
		p.stroke(0);
		p.line(-width/2, 0, 0, -width/2, 0, -depth);
		p.line(width/2, 0, 0, width/2, 0, -depth);
		p.popMatrix();
		p.popStyle();
	}
	
	
}
