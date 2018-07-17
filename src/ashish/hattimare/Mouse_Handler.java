//#Ashish

// Import all the required java packages

package ashish.hattimare;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse_Handler implements MouseListener, MouseMotionListener
{
  
  /**************************
    * Instance Variables
    * **********************/
  
  // The x coordinate of the mouse when is on the screen
  private int xMouse;
  
  // Copy of the Classes that are by this Mouse Handler Class
  Fire_Controller control;
  Slider slider;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor
   */
  public Mouse_Handler(Fire_Controller control, Slider slider)
  {
    this.control = control;
    this.slider = slider;
  }// end Mouse_Handler(FireController, Slider) constructor
  
  
  /**************************
    * Event Handler Methods
    * **********************/
  
  @Override
  public void mouseMoved(MouseEvent e)
  {
    this.xMouse = e.getX();
  }// end mouseMoved(MouseEvent)
  
  @Override
  public void mouseDragged(MouseEvent e)
  {
    this.xMouse = e.getX();
  }// end mouseDragged(MouseEvent)
  
  public int getX()
  {
    return this.xMouse;
  }// end getX()
  
  /**
   * Run the game and lunch the ball when the mouse is clicked
   */
  public void mousePressed(MouseEvent e)
  {
    //play the sound when ball is launched and start the game
    if (Destructor.gameTime >= 1)
    {
      //Play the bounce sound when ball is released
      if (!Destructor.startGame)
      {
        Sound.play("bounce.wav");
      }// end if
      
      // Start the game
      Destructor.startGame = true;
      
    }// end if
    
    // Shot fireballs when clicked and blaster slider activated
    if (slider.getBlaster() && !Destructor.levelDone)
    {
      control.addFire(new FireBalls(slider.getCenter(), 491));
    }// end if
  }// end mousePressed(MouseEvent)
  
  
  /*******************************************************
    * Unused MouseListener and MouseMotionListener Events
    * ***************************************************/
  
  @Override
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mouseClicked(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
  
}// end Mouse_Handler Class
