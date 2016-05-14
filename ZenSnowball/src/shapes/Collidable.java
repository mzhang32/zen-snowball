package shapes;

import processing.core.PApplet;

public interface Collidable {
		void draw(PApplet p);
		boolean collides();
		void move(float x, float y, float z);
		void act();
}
