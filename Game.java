import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Game extends Canvas implements Runnable              // Use the Game class to run the Game!
{   
			
	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	private int [] positions = new int [2];
	private int [] rowsAndcolumns = new int [2];
	private int [][] mapData;
	
	Handler handler;
	Camera cam;
	
	private void init() 
   {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		handler = new Handler();
		
		cam = new Camera (0, 0);
      
		try
		{
			Scanner scan = new Scanner(new File("PlatformMap.txt"));	// Create Scanner for text file to read PlatformMap data
			
         for (int i = 0; i < positions.length; i++) 
         {	                                                      // Loop to find coordinates for player
				positions[i] = scan.nextInt();
			}
         
			handler.addObject(new Player(positions[0]*25, positions[1]*25, handler, ObjectId.Player));	// Draw Player at coordinates
			
			for (int i = 0; i < rowsAndcolumns.length; i++) 
         {	                                                      // Loop to find rows and columns of Platform Map
				rowsAndcolumns[i] = scan.nextInt();
			}
         
			int rows = rowsAndcolumns[0];
			int columns = rowsAndcolumns[1];
			
			mapData = new int [rows][columns];	                     // Set up 2D Array to be based on how many rows and columns were read from text file
			
			for (int i = 0; i < rows; i++) 
         {
				for (int j = 0; j < columns; j++) 
            {
					mapData[i][j] = scan.nextInt();
					if (mapData[i][j] == 1) 
               {	                                                // If element in 2D Array is 1, draw ordinary block
						handler.addObject(new Block(j*25,i*25, ObjectId.Block));
					}
					if (mapData[i][j] == 2) 
               {                                                  // If element in 2D Array is 2, draw victory block
						handler.addObject(new VictoryBlock(j*25, i*25, ObjectId.VictoryBlock));
					}
				}
			}
		}
		catch (FileNotFoundException fnfe) {}
						
		this.addKeyListener(new KeyInput(handler));
	}
		
	public void start()                                            // Use start() to display and play the Game
   {	
		if (running)
      {
			return;
		}
      
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run()                                              // Use run() to set up timer and run the game based on amount of ticks
   {	
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int newTicks = 0;
		int frames = 0;
		
		while (running)                                             // As the game runs, update the timer
      {	
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) 
         {
				tick();
				newTicks++;
				delta--;
			}
			
			render();                                                // Call render() to render at computer's speed
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) 
         {
				timer += 1000;
				frames = 0;
				newTicks = 0;				
			}
		}
	}
	
	private void tick()                                            // Use tick() to allow camera to follow Player
   {	
		handler.tick();
		for (int i = 0; i < handler.getObject().size(); i++) 
      {
			if (handler.getObject().get(i).getId() == ObjectId.Player) 
         {	
				cam.tick(handler.getObject().get(i));
			}
		}
	}
	
	private void render()                                          // Use render() to provide background of the game
   {	
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) 
      {
			this.createBufferStrategy(3);	                           // Triple buffer to load multiple graphics
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
				
		// Draw black background 
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(cam.getX(), cam.getY());                      // Start of camera position
		
		handler.draw(g);	                                          // Draw all Graphics to properly display as camera moves
		
		g2d.translate(-cam.getX(), -cam.getY());                    // End of camera position

		g.dispose();	                                             // Use dispose() when finished with Graphics 
		bs.show();	                                                // Use show() to display next buffer image
	}
	
	public static void main(String args[])                         // Display Platform Game
   {	
		new WindowPlatform(800,600,"Neon Platformer Project", new Game());
	}

}