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
	ArrayList<LittleSnowball> littlesnowballs = new ArrayList<LittleSnowball>();
	private float width;
	private float depth;
	public static final int WHEN_STUFF_DISSAPEARS = 250;
	
	/**
	 * Constructs a path with a width and a depth(distance into the screen from the game's origin)
	 * 
	 * @param width the width of the path.
	 * @param depth the distance into the -z from (0, 0, 0)
	 */
	public Path(float width, float depth) {
		this.width = width;
		this.depth = depth;
		ArrayList<Obstacle> o = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(-getWidth()/2, 0, -800, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(-getWidth()/6, 0, -1400, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(getWidth()/6, 0, -1400, getWidth()/3, 60, 60));	
		obstacles.add(new Obstacle(-getWidth()/2, 0, -1200, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(-getWidth()/6, 0, -1800, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(-getWidth()/6, 0, -1000, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(getWidth()/6, 0, -1600, getWidth()/3, 60, 60));	
		obstacles.add(new Obstacle(-getWidth()/2, 0, -1600, getWidth()/3, 60, 60));
		obstacles.add(new Obstacle(-getWidth()/2, 0, -1800, getWidth()/3, 60, 60));
		addObstacles(o);
		littlesnowballs.add(new LittleSnowball(0,0,-2800,10));
	}
	
	public void generateObstacle(float z){
		double x = Math.random()*3;
		
		if(x > 2){
			addObstacles(new Obstacle(-getWidth()/2, 0, z, getWidth()/3, 60, 60));

		}
		else if(x > 1){
			addObstacles(new Obstacle(-getWidth()/6, 0, z, getWidth()/3, 60, 60));

		}
		else if(x > 0){
			addObstacles(new Obstacle(getWidth()/6, 0, z, getWidth()/3, 60, 60));	
		}
//		}
//			if(-getWidth()/x > -getWidth()/2){
//				addObstacles(new Obstacle((float)(-getWidth()/(x)), 0, z, getWidth()/3, 60, 60));
//			}
//			else if (getWidth()/x < getWidth()/2){
//				addObstacles(new Obstacle((float)(getWidth()/(x)), 0, z, getWidth()/3, 60, 60));
//	
//				}
		
	}
		
	/**
	 * Returns the width of the path.
	 * @return the width of the path.
	 */
	public float getWidth() {
		return width;
	}
	
	public float getYTilt() {
		return 0;
	}
	
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}
		
	public void addObstacles(ArrayList<Obstacle> o){
		for(int x = 0; x < o.size(); x++){
			obstacles.add(o.get(x));
		}
	}
		
	public void addObstacles(Obstacle o){
			obstacles.add(o);
			
	}
	public ArrayList<LittleSnowball> getLittleSnowballs(){
		return littlesnowballs;
	}
		
	/**
	 * Calls the act method for every obstacle on the path
	 * @post All the obstacles on the path will move towards the positive z
	 */
	public void act() {
		for(int x = 0; x < littlesnowballs.size(); x++){
			littlesnowballs.get(x).act();
			if(obstacles.get(x).getZ() > WHEN_STUFF_DISSAPEARS){
				obstacles.remove(x);									
			}	
		}
		for(int x = 0; x < obstacles.size(); x++) {
			obstacles.get(x).act();
			
			if(obstacles.get(x).getZ() > WHEN_STUFF_DISSAPEARS){
				obstacles.remove(x);									
			}	
			if(obstacles.size() < 9)
				generateObstacle(-2400);

		}

	}
		
	
	public void scroll(){
		
	}
	/**
	 *  Draws the path.
	 *  
	 *  @param p the PApplet used to draw the obstacle.
	 */
	public void draw(PApplet p) {
		//System.out.println("Draw path called");
		p.pushMatrix();
		p.pushStyle();
		p.stroke(0);
		p.line(-width/2, 0, 0, -width/2, 0, -depth);//need to change z-coord
		p.line(width/2, 0, 0, width/2, 0, -depth);
	//	p.line(-width/2, 0, 0, width/2, 0, 0); //delete later x axis
	//	p.line(0, -1000, 0, 0, 100, 0);//delete later y axis
	//	p.line(0, 0, 10, 0, 0, -1000);//delete later z axis
		p.popMatrix();
		p.popStyle();
	}
	
	
}
