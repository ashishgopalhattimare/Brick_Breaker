//#Ashish

// Import all the required java packages

package ashish.hattimare;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Power_Ups
{
  
  /**************************
    * Instance Variables
    * **********************/
  
  // The x and y coordinate of the power image
  private int x, y;
  
  // The type of the powerUp
  private int powerType;
  
  // Image of the powerUp
  private BufferedImage image;
  
  // The constant dimensions of the image of the powerUp
  public static final int DIMENSION = 32;
  
  
  /**************************
    * Constructor
    * **********************/
  
  public Power_Ups(){}
  
  /**
   * Default Constructor
   * 
   * When the powerUp is set on the screen when a certain brick is destroyed
   * */
  public Power_Ups(int x, int y, int type)
  {
    this.x = x;
    this.y = y;
    this.powerType = type;
    
    // Get the powerUp image from the Sprite Class
    this.image = SpriteSheets.powerCrop(type * 32);
    
  }// end Power_Ups(int, int, int) default constructor
  
  
  /**************************
    * Instance Methods
    * **********************/
  
  /**
   * Move the power ups down the screen
   */
  public void update()
  {
    this.y += 3;
  }//end update()
  
  /**
   * Draw the power
   * @param g - Graphics required to draw the power Ups
   */
  public void draw(Graphics g)
  {
    g.drawImage(this.image, this.x, this.y, DIMENSION, DIMENSION, null);
    
  }// end draw(Graphics)
  
  
  /**************************
    * Get Methods
    * **********************/
  
  /**
   * Get the type of the powerUp
   * */
  public int getType()
  {
    return this.powerType;
  }// end getType()
  
  /**
   * Get the x coordinate of the powerUp
   * */
  public int getX()
  {
    return this.x;
  }// end getX()
  
  /**
   * Get the y coordinate of the powerUp
   * */
  public int getY()
  {
    return this.y;
  }// end getY()
  
  /**
   * Get the dimensions of the brick for collision
   */
  public Rectangle getBounds()
  {
    return (new Rectangle(this.x, this.y, DIMENSION, DIMENSION));
  }// end getBounds()
  
}// end Power_Ups Class
