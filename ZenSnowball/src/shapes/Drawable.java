package shapes;

import processing.core.PApplet;

/**
 * Represents a object that can be drawn with processing.
 * 
 * @author Michelle Z. and Waveley Q.
 * @version 05.16.2016
 */
public interface Drawable {
	
	/**
	 * Draws the object using processing.
	 * 
	 * @param p the PApplet used to draw the object.
	 * @pre The PApplet must be initialized.
	 */
	void draw(PApplet p);
	void act();
}
