package shapes;

import java.util.ArrayList;

import processing.core.PApplet;


/**
 * This class handles creation of obstacles and moving them. The obstacles include the "rocks", big snowballs, and little
 * snowballs.
 * 
 * @author Michelle Z. and Waveley Q.
 * @version 05.23.2016
 */
public class Path implements Drawable{
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private float width;
	private float depth;
	private double tilt;
	public static final int WHEN_STUFF_DISSAPEARS = 250;
	public static final int MAX_TILT = 120;
	public static final int MIN_TILT = -200;
	
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
		obstacles.add(new Rock(-getWidth()/2, 0, -1200, getWidth()/3, 60, 60));
		obstacles.add(new Rock(-getWidth()/6, 0, -1800, getWidth()/3, 60, 60));
		obstacles.add(new Rock(getWidth()/6, 0, -1800, getWidth()/3, 60, 60));	
		obstacles.add(new Rock(-getWidth()/2, 0, -1600, getWidth()/3, 60, 60));
		obstacles.add(new Rock(-getWidth()/6, 0, -2200, getWidth()/3, 60, 60));
		obstacles.add(new Rock(-getWidth()/6, 0, -1400, getWidth()/3, 60, 60));
		obstacles.add(new Rock(getWidth()/6, 0, -2000, getWidth()/3, 60, 60));	
		obstacles.add(new Rock(-getWidth()/2, 0, -2000, getWidth()/3, 60, 60));
		obstacles.add(new Rock(-getWidth()/2, 0, -2200, getWidth()/3, 60, 60));
		addObstacles(o);
		ArrayList<Obstacle> littlesnowball = new ArrayList<Obstacle>();
		littlesnowball.add(new LittleSnowball(getWidth()/6,0,-900,10));
		littlesnowball.add(new LittleSnowball(-getWidth()/6, 0, -1100, 10));
		littlesnowball.add(new LittleSnowball(-getWidth()/2, 0, -700, 10));
		addObstacles(littlesnowball);
		ArrayList<Obstacle> bigsnowball = new ArrayList<Obstacle>();
		bigsnowball.add(new BigSnowball(getWidth()/6, 0, -3500, 75));
		tilt = 0;
	}
	
	/**
	 * Adds new "rocks" to the path. The column to which a rock is added is random.
	 * @param z the z coordinate at which the rocks will be added. 
	 */
	public void generateObstacle(float z){
		double x = Math.random()*3;
		
		if(x > 2){
			addObstacles(new Rock(-getWidth()/2, 0, z, getWidth()/3, 60, 60));

		}
		else if(x > 1){
			addObstacles(new Rock(-getWidth()/6, 0, z, getWidth()/3, 60, 60));

		}
		else if(x > 0){
			addObstacles(new Rock(getWidth()/6, 0, z, getWidth()/3, 60, 60));	
		}
		
	}
	
	/**
	 * Adds a big snowball to the path. The x location is random.
	 */
	public void generateBigSnowball(){
		float j = (float)(Math.random()*this.width-width/2);
		addObstacles(new BigSnowball(j, 0, -4100, 100));
	}
	
	/**
	 * Adds a small snowball to the path. The x location is random.
	 */
	public void generateLittleSnowball(){
		float j = (float)(Math.random()*this.width-width/2);
		addObstacles(new LittleSnowball(j, 0, -2300, 10));
	}

	/**
	 * Returns the width of the path.
	 * @return the width of the path.
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * Sets the tilt of the path. A negative tilt makes the path appear to tilt upwards, while a positive
	 * tilt makes the path appear to tilt downwards.
	 * @param tilt the amount of tilt.
	 */
	public void setYTilt(double tilt) {
		this.tilt = tilt;	
	}
	
	/**
	 * Returns the amount of tilt. A negative tilt means the path appears to tilt upwards, while a positive
	 * tilt means the path appears to tilt downwards.
	 * @return the amount of tilt
	 */
	public float getYTilt() {
		return (float)tilt;
	}
	
	/**
	 * Returns an array list of all the obstacles on the path. This includes little and big snowballs and 
	 * "rocks."
	 * @return
	 */
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}
	
	/**
	 * Adds an array list of obstacles to the path.
	 * @param o The array list of obstacles to add to the path.
	 */
	public void addObstacles(ArrayList<Obstacle> o){
		for(int x = 0; x < o.size(); x++){
			obstacles.add(o.get(x));
		}
	}
	
	/**
	 * Adds an obstacle to the path.
	 * @param o The obstacle to add to the path.
	 */
	public void addObstacles(Obstacle o){
			obstacles.add(o);
	}

	/**
	 * Calls the act method for every obstacle on the path. Also generates obstacles as appropriate.
	 * @post All the obstacles on the path will move towards the positive z
	 */
	public void act(Snowball s) {
		for(int x = 0; x < obstacles.size(); x++) {
			
			obstacles.get(x).act();
			
			if(obstacles.get(x).getZ() > WHEN_STUFF_DISSAPEARS || 
					s.isEating(obstacles.get(x)) || 
					s.isDying(obstacles.get(x))){
				if(obstacles.get(x) instanceof Rock || obstacles.get(x) instanceof LittleSnowball)
				obstacles.remove(x);
			}	
			
			if(obstacles.size() < 10){
				double y = Math.random()*20;
				if(y > 9){
					generateObstacle(-2800);
				}
				else if(y > 1){
					generateLittleSnowball();
				}
				else {
					generateBigSnowball();
				}
			}
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

	/**
	 * Returns the depth of the path.
	 */
	public double getZ() {
		return depth;
	}

	
}
