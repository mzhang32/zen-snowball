package shapes;

import processing.core.PApplet;

public interface Collidable {
		void draw(PApplet p);
		boolean collides();
}
