# zen-snowball
Zen Snowball
Michelle Zhang and Waveley Qiu
05.17.2016

Introduction: 
Once upon a time, on a mountaintop far far away, there was a young snowball named Zen. Zen was rolling along the mountain side when it slipped and started tumbling down uncontrollably. Can you help Zen get to the bottom of the mountain? Zen Snowball™ is a single player game in which the player takes control of a out-of-control snowball, aspiring to collect smaller snowballs to grow bigger and avoid obstacles(“rocks”) that will shatter the snowball. Zen can only move side to side and jump, and must stay on the mountain path. The longer the player can last, the more points they get. 

Instructions:
Use the left, right, and up arrows to navigate Zen through the blustery winter wonderland. Zen must avoid all dangerous “rocks”(by jumping over them) as well as all snowballs larger than itself (by moving out of their way). While hitting a “rock” will decrease Zen’s size by X%, if Zen is unable to move out of the larger snowball’s way, Zen will be absorbed into the larger snowball. 
	
Features List:
	Must-Have features
	-Sphere: Must be able to jump(y axis) and move side to side(x axis)
	-Obstacles: Must generate boxes not too close/intersecting, scrolling surfaces
	-Big snowballs that must be avoided
	-Little snowballs that increase the size of our snowball
	-Menu Window (Start Screen): Instructions, “Play” button, 
	-Collision Detection between the sphere and the obstacles
	-Scorekeeping abilities
	
Want-To-Have features
	-Elevator Music
	-Load/Save Option
	-Death animation
	-Power-up #1:  Allow the large snowball to attract the smaller snowballs
	-Power-up #2: Propeller -- propels Zen a certain distance, like the rocket power-up in MarioKart	

Stretch Features:
	-Scenic scrolling background(hills, sky)
	-Turning the view(rotate everything around y)
	-Multiplayer
	-Textures on the objects	
	-Networking

Class List:
	-Snowball
	-Obstacle
	-Drawable(interface)
	-Collidable(interface)
	-GameSurface
	-Main
	-Scoreboard
	-Score
	-Game
	-ObstacleHolder for Scroll/Timer

Package: 
	-Shapes
	-Graphics
	-UserInput

Responsibilities List:

Michelle Z: Write the graphical class, main, scoreboard, and game
Waveley Q: Write the snowball, obstacle class, collision detection/scrolling

