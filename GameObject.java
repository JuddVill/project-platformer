import java.util.*;
import javax.swing.*;
import java.awt.*;

public abstract class GameObject    // Create GameObject Class to create GameObject for the Game
{
	
	protected float x, y;
	protected float velX = 0, velY = 0;

	protected ObjectId id; 
   
	protected boolean falling = true;
	protected boolean jumping = false;
	
	public GameObject(float x, float y, ObjectId id) 
   {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick (LinkedList<GameObject> go);
	public abstract void draw (Graphics g);
	public abstract Rectangle getBounds ();
	
	public float getX()              // Return x-coordinate
   {
		return x;
	}

	public void setX(float x)        // Set new x-coordinate
   {
		this.x = x;
	}

	public float getY()              // Return y-coordinate
   {
		return y;
	}

	public void setY(float y)        // Set new y-coordinate
   {
		this.y = y;
	}

	public float getVelX()           // Return velocity in x-direction
   {
		return velX;
	}
   
	public float getVelY()           // Return velocity in y-direction
   {
		return velY;
	}
   
	public void setVelX(float velX)  // Set new velocity in x-direction
   {
		this.velX = velX;
	}
   
	public void setVelY(float velY)  // Return velocity in y-direction
   {
		this.velY = velY;
	}
	
	public boolean isFalling() 
   {
		return falling;
	}

	public boolean isJumping() 
   {
		return jumping;
	}

	public void setJumping(boolean jumping) 
   {
		this.jumping = jumping;
	}

	public ObjectId getId()          // Return ObjectId
   {
		return id;
	}

}