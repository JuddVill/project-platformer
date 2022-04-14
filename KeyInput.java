import java.awt.event.*;

public class KeyInput extends KeyAdapter                         // Create KeyInput class to respond to keys pressed/released
{
	
	Handler handler;
	
	public KeyInput(Handler handler) 
   {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)                            // Use keyPressed() to move Player based on the following keys
   {	
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.getObject().size(); i++) 
      {
			GameObject temp = handler.getObject().get(i);
			
			if (temp.getId() == ObjectId.Player) 
         {
				if (key == KeyEvent.VK_D) temp.setVelX(5);	        // If D is pressed, move to the right 5 pixels
				if (key == KeyEvent.VK_A) temp.setVelX(-5);	        // If A is pressed, move to the left 5 pixels
				if (key == KeyEvent.VK_W && !temp.isJumping())       // If W is pressed, jump 10 pixels
            {	
					temp.setJumping(true);                            
					temp.setVelY(-10);
				}

			}
		}
		
		if (key == KeyEvent.VK_ESCAPE)                             // If the "esc" key is pressed, exit the Game
      {	
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e)                           // Use keyReleased() to stop Player in place if the following key is released
   {	
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.getObject().size(); i++) 
      {
			GameObject temp = handler.getObject().get(i);
			
			if (temp.getId() == ObjectId.Player) 
         {
				if (key == KeyEvent.VK_D) temp.setVelX(0);
				if (key == KeyEvent.VK_A) temp.setVelX(0);
			}
		}
	}

}