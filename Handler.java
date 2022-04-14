import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Handler                             // Create Handler class to store all GameObjects
{	
	
	private LinkedList<GameObject> go = new LinkedList<GameObject>();
	
	private GameObject temp;
	
	public void tick ()                           
   {
		for (int i = 0; i < go.size(); i++) 
      {
			temp = go.get(i);
			
			temp.tick(go);
		}
	}
	
	public void draw(Graphics g)                 // Use draw() to draw each GameObject in LinkedList
   {
		for (int i = 0; i < go.size(); i++) 
      {
			temp = go.get(i);
			
			temp.draw(g);
		}
	}
	
	public void addObject (GameObject go)        // Use addObject() to add GameObject to LinkedList
   {	
		this.go.add(go); 
	}
	
	public void removeObject (GameObject go)     // Use removeObject() to remove GameObject from LinkedList
   {	
		this.go.remove(go); 	
	}
   
   public LinkedList<GameObject> getObject()
   {
      return go;
   }
   
}