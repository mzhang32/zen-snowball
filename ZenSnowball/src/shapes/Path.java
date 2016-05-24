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
	private double tilt;
	public static final int WHEN_STUFF_DISSAPEARS = 250;
	public static final int MAX_TILT = 100;
	
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
	public void generateBigSnowball(){
		float j = (float)(Math.random()*this.width-width/2);
		addObstacles(new BigSnowball(j, 0, -4100, 100));
	}
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
	
	public void setYTilt(double tilt) {
		this.tilt = tilt;
		//System.out.println("Tile set to " + tilt);		
	}
	
	public float getYTilt() {
		return (float)tilt;
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

	/**
	 * Calls the act method for every obstacle on the path
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
//			if(s.isDead(obstacles.get(x))){
//				
//			}
			
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
		//System.out.println("Draw path called");
		p.pushMatrix();
		p.pushStyle();
		p.stroke(0);
		p.line(-width/2, 0, 0, -width/2, 0, -depth);//need to change z-coord
		p.line(width/2, 0, 0, width/2, 0, -depth);
		p.popMatrix();
		p.popStyle();
	}

	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
	
	
}
