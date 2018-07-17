//#Ashish

/**
 * Ball Handler Class
 * */

package ashish.hattimare;

import java.awt.image.BufferedImage;

public class Ball
{
  
  /**************************
    * Instance Variables
    * **********************/
  
  // The x and y coordinate of the Ball
  private float x, y;
  
  // The angle of translation of the ball on the screen
  private int angle;
  
  // Change the ball type
  private boolean change;
  
  // Image of the ball
  private BufferedImage image;
  
  private boolean bounce;
  
  // The x and y component of the speed of the ball
  public float xSpeed, ySpeed;
  
  
  /**************************
    * Class Variables
    * **********************/
  
  public static int mass;
  
  // The magnitude at which the ball will translate
  public static float increase, tempIncrease;
  
  // The width of the ball
  public static final int DIAMETER = 16;
  public static final int SPEED = 4;
  
  // The default increase speed of the ball
  public static final float INCREASE = 1.4f;
  
  // Type of the ball
  public static int ballType;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor
   * */
  public Ball()
  {
    
  }// end Ball() constructor
  
  //#define
  /**
   * Default Constructor for the launching ball
   * @param x - the initial x coordinate of the ball
   * */
  public Ball(int x)
  {
    this.x = x;
    increase = INCREASE;
    tempIncrease = INCREASE;
    
    ballType = 0;
    mass     = 50;
    
    this.change = true;
    this.bounce = false;
    
  }// end Ball(int) constructor
  
  //#define
  /**
   * Default constructor for the multiple balls creation
   * 
   * @param x - the x coordiante of the ball
   * @param y - the y coordinate of the ball
   * @param angle - the release angle of the ball
   * */
  public Ball(int x, int y, float angle)
  {
    this.x = x;
    this.xSpeed = (float) ( this.SPEED * Math.cos(Math.toRadians(angle)));
    this.ySpeed = (float) (-this.SPEED * Math.sin(Math.toRadians(angle)));
    this.bounce = false;
    
    setDefault(y , ballType);
    
  }// end Ball(int, int, float) constructor
  
  
  /**************************
    * Set Methods
    * **********************/
  
  /**
   * Set the x coordinate of the Ball
   * @param x - the new x coordinate of the ball
   * */
  public void setX(float x)
  {
    this.x = x;
  }// end setX(float)
  
  /**
   * Set the y coordinate of the Ball
   * @param y - the new y coordinate of the ball
   * */
  public void setY(float y)
  {
    this.y = y;
  }// end setY(float)
  
  public void setBounce(boolean bounce)
  {
    this.bounce = bounce;
  }// end setBounce
  
  /**
   * Set the xSpeed and the ySpeed of the Ball
   * @param angle - the angle at the which the ball moves
   * */
  public void setSpeed(int angle)
  {
    this.angle = angle;
    
    this.xSpeed = (float) (this.SPEED * (double) Math.cos(Math.toRadians(angle)));
    this.ySpeed = (float) (this.SPEED * (double) Math.sin(Math.toRadians(angle)));
    
  }// end setSpeed(double, int)
  
  /**
   * Change the image
   * @param change - change image boolean
   * */
  public void setChange(boolean change)
  {
    this.change = change;
  }// end setChange(boolean)
  
  /**
   * Draw the ball
   * @param g - Graphics required to draw the ball
   * */
  public void setImage()
  {
    if(change)
    {
      this.image = SpriteSheets.ballCrop(ballType * 100);
      change = false;
    }// end if
    
  }// end setImage()
  
  /**
   * Default Ball 
   * @param y
   * */
  public void setDefault(int y, int type)
  {
    this.y = y;
    this.change = true;
    this.bounce = false;
    increase = INCREASE;
    tempIncrease = INCREASE;
    
    ballType = type;
    mass     = 50;
    
  }// end setDefault(int)
  
  
  /**************************
    * Get Methods
    * **********************/
  
  /**
   * Get the image of the Ball
   * */
  public BufferedImage getImage()
  {
    return this.image;
  }// end getImage()
  
  /**
   * Get the x coordinate of the Ball
   * @return - the x coordinate of the ball
   * */
  public double getX()
  {
    return this.x;
  }// end getX()
  
  /**
   * Get the y coordinate of the Ball
   * @return - the y coordinate of the ball
   * */
  public double getY()
  {
    return this.y;
  }// end getY()
  
  public boolean getBounce()
  {
    return this.bounce;
  }// end getBounce()
  
  /**
   * Get the angle at which it is released from the Slider
   * @return - the angle of the release
   * */
  public int getAngle()
  {
    return this.angle;
  }// end getAngle()
  
  
  /**************************
    * Instance Methods
    * **********************/
  
  /**
   * Update the x and y coordinates of the ball
   * */
  public void update()
  {
    this.x += (this.xSpeed * increase);
    this.y += (this.ySpeed * increase);
    
  }// end update()
  
  @Override
  /**
   * Method Name : toString
   * return String - the coordinates of the ball
   * */
    public String toString()
  {
    return String.format("Ball Coordinates : %d, %d", (int) this.x, (int) this.y);
  }// end toString()
  
  
  /**************************
    * Class Methods
    * **********************/
  
  /**
   * Get the radius of the ball
   * */
  public static int getRadius()
  {
    return DIAMETER / 2;
  }// end getRadius()
  
}// end Ball
