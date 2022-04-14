public class Camera                       // Create Camera class to follow (side-scroll the map) the player as it moves
{	
	
	private float x, y;
	
	public Camera (float x, float y) 
   {
		this.x = x;
		this.y = y;
	}
	
	public void tick (GameObject player)   // Use tick() to snap camera towards player and keep player in center of camera
   {	
		x = -player.getX() + Game.WIDTH/2;
	}

	public float getX() 
   {
		return x;
	}

	public void setX(float x) 
   {
		this.x = x;
	}

	public float getY() 
   {
		return y;
	}

	public void setY(float y) 
   {
		this.y = y;
	}

}