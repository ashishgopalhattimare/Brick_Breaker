//#Ashish

// Import all the required java packages

package ashish.hattimare;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Slider
{
  
  
  /**************************
    * Instance Variables
    * **********************/
  
  // Coordinate of the slider
  private int x, y;
  
  // Width of the slider
  private int width;
  
  // x Coordinate of the slider's center 
  private int sliderCenter;
  
  // Speed of the slider
  private int speed;
  
  // Boolean to activate the blaster
  private boolean blasterSlider;
  
  // Slider Components
  public final int EDGE_ROUND = 14;
  public final int HEIGHT = 20;
  public final int BOTTOM_GAP = 6;
  public final int SLIDER_WIDTH = 110;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Slider Constructor
   */
  public Slider(int x)
  {
    this.setDefault();
    
    this.x = x - this.width / 2;
    this.sliderCenter = this.x + this.width / 2;
    
    this.speed = 8;
    
  }// end Slider(int) constructor
  
  
  /**************************
    * Set Methods
    * **********************/
  
  /**
   * Default Slider when the game starts
   */
  public void setDefault()
  {
    this.blasterSlider = false;
    this.width = SLIDER_WIDTH;
    this.speed = 8;
    
  }// end setDefault()
  
  /**
   * Move the slider towards to pointer (MouseMotion)
   * @param x - the new x coordinate of the slider
   */
  public void setX(int x)
  {
    // If the ball is released, use smooth motion for the slider
    if (Destructor.startGame && !Destructor.godMode)
    {
      // If the difference between the pointer and the slider is less than speed
      if (Math.abs(x - this.x) < this.speed)
      {
        this.x = x;
      }
      
      // Move the slider in the right direction
      else if (x - this.x > 1)
      {
        this.x += this.speed;
      }
      
      // Move the slider in the left direction
      else if (x - this.x < 1)
      {
        this.x -= this.speed;
      }// end if
    }// end if
    
    // If the ball is not released, use mouse pointer as the slider center
    else
    {
      this.x = x;
    }//end if
  }// end setX(int)
  
  /**
   * Change the slider type
   * @param blaster - if the blaster is activated or not
   */
  public void setBlaster(boolean blaster)
  {
    this.blasterSlider = blaster;
    this.width = SLIDER_WIDTH;
  }// end setBlaster(boolean)
  
  /**
   * Set the new width of the slider
   */
  public void setWidth(int width)
  {
    this.width = width;
  }// end setWidth(int)
  
  
  /**************************
    * Get Methods
    * **********************/
  
  /**
   * Get the x coordinate of the slider
   */
  public int getX()
  {
    return this.x;
  }// end getX()
  
  /**
   * Get the width of the slider
   */
  public int getWidth()
  {
    return this.width;
  }// end getWidth()
  
  /**
   * Get the center x coordinate of the slider
   */
  public int getCenter()
  {
    return this.sliderCenter;
  }// end getCenter()
  
  /**
   * Get the blasterSlider acivated boolean
   * */
  public boolean getBlaster()
  {
    return this.blasterSlider;
  }// end getBlaster()
  
  /**
   * Get the coordinates of the slider in the form of rectangle
   */
  public Rectangle getBounds()
  {
    return (new Rectangle(this.x, this.y, this.width, 15));
  }// end getBounds()
  
  
  /**************************
    * Instance Methods
    * **********************/
  
  //#instance
  /**
   * Method Name : drawSlider
   * 
   * @param g - draw the slider on the screen
   * @param y - the y coordinate of the slider
   * 
   * @return void - display the image of the slider
   */
  public void drawSlider(Graphics g, int y)
  {
    
    this.y = y;
    this.sliderCenter = this.x + this.width / 2;
    
    g.setColor(Color.BLACK);
    g.drawRoundRect(this.x, y, this.width, HEIGHT - BOTTOM_GAP, EDGE_ROUND, EDGE_ROUND);
    
    //Draw the default slider
    if(!this.blasterSlider)
    {
      colorEnds(g, Color.GREEN, y);
      colorCenter(g, Color.GRAY, y);
      
      g.setColor(Color.BLACK);
      g.drawLine(this.sliderCenter, y, this.sliderCenter, y + HEIGHT - BOTTOM_GAP);
    }
    
    //Draw the blaster slider
    else
    {
      colorEnds(g, Color.RED, y);
      colorCenter(g, Color.ORANGE, y);
      colorOrigin(g, y);
      
    }// end if
    
    // Draw the white strips on the slider
    g.setColor(Color.WHITE);
    g.fillRect(this.x + this.EDGE_ROUND + 2, y + 3, this.width - 2 * this.EDGE_ROUND - 3, 2);
    g.fillRect(this.x + 10, y + 2, 2, 11);
    g.fillRect(this.x + this.width - 12, y + 2, 2, 11);
    
  }// end drawSlider(Graphics, int)
  
  /**
   * Draw the blaster shooter for the blaster slider
   * @param g - Graphics required to draw
   * @param y - the y coordinate of the slider
   * */
  private void colorOrigin(Graphics g, int y)
  {
    g.setColor(Color.RED);
    g.fillRect(this.sliderCenter - 4, y, 8, 15);
    
    g.setColor(Color.BLACK);
    g.drawLine(this.sliderCenter - 4, y, this.sliderCenter - 4, y + 14);
    g.drawLine(this.sliderCenter + 4, y, this.sliderCenter + 4, y + 14);
    
  }// end colorOrigin(Graphics, int)
  
  /**
   * Draw the center section of the slider
   * @param g - Graphics required to draw
   * @param color - color of the slider
   * @param y - the y coordinate of the slider
   * */
  private void colorCenter(Graphics g, Color color, int y)
  {
    g.setColor(Color.BLACK);
    g.drawRect(this.x + this.EDGE_ROUND, y, this.width - 2 * this.EDGE_ROUND, 14);
    
    g.setColor(color);
    g.fillRect(this.x + this.EDGE_ROUND + 1, y + 1, this.width - 2 * this.EDGE_ROUND - 1, 13);
    
  }// end colorCenter(Graphics, Color, int)
  
  /**
   * Draw the end sections of the slider
   * @param g - Graphics required to draw
   * @param color - color of the slider
   * @param y - the y coordinate of the slider
   * */
  private void colorEnds(Graphics g, Color color, int y)
  {
    g.setColor(color);
    g.drawRoundRect(this.x + 1, y + 1, this.width - 2, HEIGHT - BOTTOM_GAP - 2, EDGE_ROUND - 5, EDGE_ROUND - 5);
    g.fillRoundRect(this.x + 1, y + 1, this.width - 2, HEIGHT - BOTTOM_GAP - 2, EDGE_ROUND - 5, EDGE_ROUND - 5);
    
  }// end colorEnds(Graphics, Color, int)
  
}// end Slider Class
