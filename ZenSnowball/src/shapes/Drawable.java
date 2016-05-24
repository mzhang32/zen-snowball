package shapes;

import processing.core.PApplet;

/**
 * Represents a object that can be drawn with processing.
 * 
 * @author Michelle Z. and Waveley Q.
 * @version 05.23.2016
 */
public interface Drawable {
	
	/**
	 * Draws the object using processing.
	 * 
	 * @param p the PApplet used to draw the object.
	 * @pre The PApplet must be initialized.
	 */
	void draw(PApplet p);
	
	/**
	 * Returns the z coordinate of the drawable object.
	 * @return the z coordinate of the drawable object.
	 */
	double getZ();
}
