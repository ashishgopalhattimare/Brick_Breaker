//#Ashish

// Import required packages

package ashish.hattimare;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FireBalls
{
  
  /*****************************
    * Instance Variables
    * **************************/
  
  // The x and y coordinate of the fire ball
  private int x, y;
  
  // The width and height of the fire ball
  private int dimension;
  
  // Image of the fire ball
  private BufferedImage image; 
  
  /*****************************
    * Class Variables
    ****************************/
  
  // Mass of the fire ball
  public static final int MASS = 900;
  
  /*****************************
    * Constructors
    ****************************/
  
  /**
   * Default Constructor:
   * @param none
   **/
  public FireBalls()
  {
    
  }// end FireBalls()
  
  /**
   * Creates an instance of this class, sets x and y to the given values
   * @param x - the initial x coordinate of the fire ball
   * @param y - the initial y coordinate of the fire ball
   * @param slider - Slider Class
   */
  public FireBalls(int x, int y)
  {
    this.dimension = 20;
    this.x = x - dimension / 2;
    this.y = y;
    
    this.image = SpriteSheets.fireCrop(0);
  }// end FireBalls(int, int)
  
  
  /*****************************
    * Get Methods
    ****************************/
  
  /**
   * Gets the y position of the ball
   * @param none
   * @return int - the y position of the ball
   **/
  public int getY()
  {
    return this.y;
  }// end getY
  
  /**
   * Gets the x position of the ball
   * @param none
   * @return int - the x position of the ball
   **/
  public int getX()
  {
    return this.x;
  }// end getX
  
  /**
   * Gets the center
   * @param none
   * @return int - the center of the ball
   **/
  public int getCenter()
  {
    return this.dimension / 2;
  }// end getCenter
  
  /**
   * Gets the center x position of the ball
   * @param none
   * @return int - the center x position of the ball
   **/
  public int getX_Center()
  {
    return this.x + this.dimension / 2;
  }// end getX_Center
  
  /**
   * Gets the center y position of the ball
   * @param none
   * @return int - the center y position of the ball
   **/
  public int getY_Center()
  {
    return this.y + this.dimension / 2;
  }// end getY_Center
  
  /**
   * Gets the outside boundry of the ball
   * @param none
   * @return Rectangle - a rectangular boundry around the ball
   **/
  public Rectangle getBounds()
  {
    return (new Rectangle(this.x, this.y, this.dimension, this.dimension));
  }// end getBounds
  
  
  /*****************************
    * Instance Methods
    * **************************/
  
  /**
   * Moves the ball vertically
   * @param none
   * @return void
   **/
  public void update()
  {
    this.y -= 5;
  }// end update
  
  /**
   * Draw the fire ball on the screen
   * @param g - Graphics required to draw the fireball
   * @return void
   */
  public void drawFire(Graphics g)
  {
    g.drawImage(this.image, this.x, this.y, this.dimension, this.dimension, null);
  }// end drawFire(Graphics)
  
}//end FireBalls Class
