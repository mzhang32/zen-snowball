package shapes;

public class LittleSnowball extends Snowball{
	float x;
	float y;
	float z;
	float r;
	public LittleSnowball(float x, float y, float z, float r) {
		super(x, y, z, r);
		// TODO Auto-generated constructor stub
	}
	
	public void act(Path p){
		this.z+=30;
	}
}
