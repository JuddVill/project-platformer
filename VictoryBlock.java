import java.util.*;
import javax.swing.*;
import java.awt.*;

public class VictoryBlock extends GameObject // Create a Victory Block
{		

	public VictoryBlock(float x, float y, ObjectId id) 
   {
		super(x,y,id);
	}

	public void tick(LinkedList<GameObject> go) {}

	public void draw(Graphics g)            // Use draw() to display Victory Block
   {	
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 25, 25);
	}

	public Rectangle getBounds() 
   {
		return new Rectangle((int)x, (int)y, 25, 25);
	}

}