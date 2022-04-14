import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Player extends GameObject    // Create Player for the Game
{	
	
	private float width = 25, height = 25;
	
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	
	private Handler handler;

	public Player(float x, float y, Handler handler, ObjectId id) 
   {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick(LinkedList<GameObject> go) 
   {
		x += velX;
		y += velY;
		
		if (falling || jumping)             // When falling or jumping, apply gravity to lower player towards ground
      {	
			velY += gravity;
			
			if (velY > MAX_SPEED)            // Prevent downwards velocity from exceeding maximum velocity
         {	
				velY = MAX_SPEED;
			}
		}
		collides(go);
	}
	
	public Rectangle getBounds()           // Get Bottom bounds of Player
   {	
		return new Rectangle ((int) ((int)x+(width/2)-((width/2)/2)), (int)((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	
	public Rectangle getBoundsTop()        // Get Top bounds of Player
   {	
		return new Rectangle ((int) ((int)x+(width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}
	
	public Rectangle getBoundsRight()      // Get Right bounds of Player
   {	
		return new Rectangle ((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10);
	}
	
	public Rectangle getBoundsLeft()       // Get Left bounds of Player
   {	
		return new Rectangle ((int)x, (int)y+5, (int)5, (int)height-10);
	}

	private void collides(LinkedList<GameObject> go) // Use collides() to check the boundaries
   { 
		for (int i = 0; i < handler.getObject().size(); i++) 
      {
			GameObject temp = handler.getObject().get(i);
			
			if (temp.getId() == ObjectId.Block)             // If Player collides with Block
         { 
				if (getBoundsTop().intersects(temp.getBounds()))   // If Top side of Player collides with Block
            { 
					y = temp.getY() + 25; 
					velY = 0;
				}
			
				if (getBounds().intersects(temp.getBounds()))      // If Bottom side of Player collides with Block
            {	
					y = temp.getY() - height; 
					velY = 0;
					falling = false;
					jumping = false;
				} 
            else
            {
					falling = true;
            }
				
				if (getBoundsRight().intersects(temp.getBounds())) // If Right side of Player collides with Block
            {	
					x = temp.getX() - width; 
				}
				
				if (getBoundsLeft().intersects(temp.getBounds())) // If Left side of Player collides with Block
            {	
					x = temp.getX() + 25; 
				}
			}
			 
			if (temp.getId() == ObjectId.VictoryBlock)      // If Player collides with Victory Block, congratulate the Player and exit the Game
         {	
				JFrame frame = new JFrame();
				if (getBoundsTop().intersects(temp.getBounds())) // If Top side of Player collides with Victory Block
            {	
					y = temp.getY() + 25; 
					velY = 0;
					JOptionPane.showMessageDialog(frame, "Congratulations! You Win!");
					System.exit(1);
				}
			
				if (getBounds().intersects(temp.getBounds()))   // If Bottom side of Player collides with Victory Block
            {	
					y = temp.getY() - height; 
					velY = 0;
					falling = false;
					jumping = false;
					JOptionPane.showMessageDialog(frame, "Congratulations! You Win!");
					System.exit(1);
				} 
            else
            {
					falling = true;
            }
				
				if (getBoundsRight().intersects(temp.getBounds())) // If Right side of Player collides with Victory Block
            {	
					x = temp.getX() - width; 
					JOptionPane.showMessageDialog(frame, "Congratulations! You Win!");
					System.exit(1);
				}
				
				if (getBoundsLeft().intersects(temp.getBounds())) // If Left side of Player collides with Victory Block
            { 	
					x = temp.getX() + 25; 
					JOptionPane.showMessageDialog(frame, "Congratulations! You Win!");
					System.exit(1);
				}
				
			}
		}
	}

	public void draw(Graphics g) // Use draw() to draw player
   { 
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

}