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
	ArrayList<Collidable> obstacles = new ArrayList<Collidable>();
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
		ArrayList<Collidable> o = new ArrayList<Collidable>();
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
		ArrayList<Collidable> littlesnowball = new ArrayList<Collidable>();
		littlesnowball.add(new LittleSnowball(0,0,-2800,10));
		addObstacles(littlesnowball);
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
	
	public ArrayList<Collidable> getObstacles(){
		return obstacles;
	}
		
	public void addObstacles(ArrayList<Collidable> o){
		for(int x = 0; x < o.size(); x++){
			obstacles.add(o.get(x));
		}
	}
		
	public void addObstacles(Collidable o){
			obstacles.add(o);
			
	}
		
	/**
	 * Calls the act method for every obstacle on the path
	 * @post All the obstacles on the path will move towards the positive z
	 */
	public void act() {
		for(int x = 0; x < obstacles.size(); x++) {
			obstacles.get(x).act();
			if(obstacles.get(x).getZ() > WHEN_STUFF_DISSAPEARS){
				obstacles.remove(x);									
			}	
			if(obstacles.size() < 9)
				generateObstacle(-2400);

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


	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
