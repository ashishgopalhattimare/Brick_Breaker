//#Ashish

/**
 * Arrow Handler Class
 * * */

package ashish.hattimare;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * The ball angle is 0 degree at + x axis and it increases in clockwise
 * direction
 * * */
public class Arrow_Class
{
  
  
  /**************************
    * Instance Variables
    * **********************/
  
  // The release angle of the ball
  private int releaseAngle;
  
  // The x and y coordinate of the image of the arrow
  private int xArrow, yArrow;
  
  // The direction at which the arrow will spin
  private boolean leftSpin;
  
  // The arrow image
  private BufferedImage arrow;
  
  // Graphics required to rotate the image
  private AffineTransform at;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor
   * */
  public Arrow_Class()
  {
    
    // An attempt to get the image from the directory
    try
    {
      this.arrow = ImageIO.read(new File("arrow.png"));
    }
    catch (Exception e)
    {
      this.arrow = null;
      System.out.println("Image arrow error");
    }// end try/catch
    
    // Let the initial direction of spin of the arrow be in the left
    // direction
    this.leftSpin = true;
    
    // Set the initial angle of the arrow
    this.releaseAngle = (int) (Math.random() * 71) + 270;
    
  }// end Ball_Release() constructor
  
  
  /**************************
    * Set Methods
    * **********************/
  
  /**
   * Set the x coordinate of the arrow image
   * */
  public void setX(int x)
  {
    this.xArrow = x;
  }// end setX(int)
  
  /**
   * Set the y coordinate of the arrow image
   * */
  public void setY(int y)
  {
    this.yArrow = y;
  }// end setY(int)
  
  
  /**************************
    * Get Methods
    * **********************/
  
  /**
   * The angle of the arrow
   * @return - the current angle of the arrow
   * */
  public int getAngle()
  {
    return this.releaseAngle;
  }// end getAngle()
  
  /**
   * Draw the image of the arrow above the ball
   * @param g - draw the arrow
   * */
  public void drawArrow(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    
    // Change the direction of the arrow
    if (this.releaseAngle < 210 || this.releaseAngle > 340)
    {
      this.leftSpin = !this.leftSpin;
    }
    
    // Rotate the arrow in left and right direction
    this.releaseAngle = (this.leftSpin ? this.releaseAngle - 1 : this.releaseAngle + 1);
    
    // Attempt to load the image of the arrow on the screen
    try
    {
      // Import the rotating arrow on the screen
      this.at = AffineTransform.getTranslateInstance(this.xArrow, this.yArrow);
      this.at.rotate(Math.toRadians(this.releaseAngle), this.arrow.getWidth() / 2, this.arrow.getHeight() / 2);
      
      //Draw the arrow
      g2.drawImage(this.arrow, this.at, null);
    }
    
    // If the image of the arrow is not able to load on the screen, display
    // an error message
    catch (Exception e)
    {
      e.printStackTrace();
      
    }// end try/catch
  }// end drawArrow(Graphics)
  
}// end Arrow_Class
