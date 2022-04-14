import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Block extends GameObject  // Create Blocks for the map of the Game
{	
	
   public Block(float x, float y, ObjectId id) 
   {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> go) {}

	public void draw(Graphics g)        // Display Blocks
   {	
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 25, 25);
	}

	public Rectangle getBounds() 
   {
		return new Rectangle((int)x, (int)y, 25, 25);
	}	

}