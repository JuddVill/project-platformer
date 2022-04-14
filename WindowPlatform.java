import javax.swing.*;
import java.awt.*;

public class WindowPlatform                        // Create Window of Platform Game
{	

	public WindowPlatform (int w, int h, String title, Game g) 
   { 
		g.setPreferredSize(new Dimension(w, h));	   // Set up dimensions of Game panel 
		g.setMaximumSize(new Dimension(w, h));
		g.setMinimumSize(new Dimension(w, h));
		
		JFrame frame = new JFrame(title);
		frame.add(g);	                              // Add Game to frame
		frame.pack();		                           // Size the frame to keep contents at or above preferred sizes
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);	                  // Prevent window from being stretched or shrunk
		frame.setLocationRelativeTo(null);	         // Display window at center of screen but user can move window around screen
		frame.setVisible(true);
		
		g.start();	                                 // Start the Game
	}
		
}