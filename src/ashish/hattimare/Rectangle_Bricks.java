//#Zhaoyang

//import required packages

package ashish.hattimare;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Rectangle_Bricks
{
  
  /**************************
    * Instance Variables
    * **********************/
  
  // The x and y coordinate of the brick
  private int x, y;
  
  // The width and the height of the brick
  private int width, height;
  
  // The total width and the height of the rectangle
  private int totalWidth, totalHeight;
  
  // Amount of hits required to break the brick
  private int health;
  
  // The type of brick (i.e. moving, metallic, etc)
  private int type;
  
  // State of the brick (i.e. direction of translation for the moving bricks)
  private int state;
  
  // Speed of the brick
  private int speed;
  
  // Store the image of the brick
  private BufferedImage image;
  
  // Transparent Brick Components
  private AlphaComposite ac;
  private Graphics2D g2d;
  public float brickOpacity, pointOpacity;
  
  // Store the points each brick has
  private int points;
  
  // Corner Collision Component
  private int vertex, corner;
  
  // Check whether the brick is destroyed or not
  private boolean destroy;
  
  // Font required to show the points when the bricks are destroyed
  private Font font;
  
 
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Constructs a Brick with the given values, then assigns a image to the
   * brick based on those value
   * 
   * @param int x - the x-coordinate of the top left corner
   * @param int y - the y-coordinate of the top left corner
   * @param int width - the width of teh brick
   * @param int height - the height of the brick
   * @param int type - the type of brick (determines image and hp)
   **/
  public Rectangle_Bricks(int x, int y, int width, int height, int type, int health, String file)
  {
    this.x = x;
    this.y = y;
    this.width  = width;
    this.height = height;
    this.type   = type;
    
    //#level
    // If the brick is movable
    if(type == 4 || type == 5 || type == 7 )
    {
      this.state = (int)(Math.random() * 2) + 1;
      
      // Set the speed of the ball
      if(type == 4)
      {
        this.speed = 1;
      }
      else
      {
        this.speed = 2;
      }// end if
    }
    
    // If the brick is not movable
    else
    {
      this.state = 0;
      this.speed = 0;
    }// end if
    
    // Set the default brick destroyed to false
    this.destroy = false;
    
    // Set the default brick opacity to 1 
    this.brickOpacity = 1;
    this.pointOpacity = 1;
    
    this.vertex = (int) (Math.sqrt(2 * (Ball.DIAMETER / 2 * Ball.DIAMETER / 2)) / 4);
    this.corner = Ball.DIAMETER;
    
    // Set the total height and the total width of this brick
    this.totalWidth  = this.x + this.width;
    this.totalHeight = this.y + this.height;
    
    // Set the point display font
    this.font = new Font("Serif", Font.CENTER_BASELINE | Font.BOLD, 28);
    
    // Points
    switch(this.type)
    {
      case 0: this.points = 50 ; break; // soft brick points
      
      case 1: this.points = 100; break; // red brick points
      
      case 2: this.points = 200; break; // orange brick points
      
      case 3: this.points = 250; break; // yellow brick points
      
      case 4: this.points = 300; break; // blue brick points
      
      case 5: this.points = 350; break; // purple brick points
    }
    
    // Load the brick image from the directory
    loadImage(health, file + "Brick.jpg");
    
  }
  
  /**
   * Load the image of the brick
   * 
   * @param hp - the health of the brick
   * @param imageName - name of the brick in the directory
   */
  public void loadImage(int hp, String imageName)
  {
    
    this.health = hp;
    
    // Attempt to load the image of the brick
    try
    {
      if(this.type == 6 || this.type == 7)
        this.image = SpriteSheets.brickCrop(6 * 31);
      else
        this.image = SpriteSheets.brickCrop(this.type * 31);
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
    }// end try/catch
  }// end loadImage(int, String)
  
  
  /**************************
    * Get Methods
    * **********************/
  
  /**
   * @return - The x coordinate of the brick
   */
  public int getX()
  {
    return this.x;
  }// end getX()
  
  /**
   * @return - The y coordinate of the brick
   */
  public int getY()
  {
    return this.y;
  }// end getY()
  
  /**
   * @return - The width of the brick
   */
  public int getWidth()
  {
    return this.width;
  }// end getWidth
  
  /**
   * @return - The height of the brick
   */
  public int getHeight()
  {
    return this.height;
  }// end getHeight()
  
  /**
   * @return - The totalHeight of the brick
   */
  public int getTotalHeight()
  {
    return this.totalHeight;
  }// end getTotalHeight()
  
  /**
   * @return - The totalHeight of the brick
   */
  public int getTotalWidth()
  {
    return this.totalWidth;
  }// end getTotalWidth()
  
  /**
   * @return - The health of the brick
   */
  public int getHealth()
  {
    return this.health;
  }// end getHealth()
  
  /**
   * @return - The type of the brick
   */
  public int getType()
  {
    return this.type;
  }// end getType()
  
  /**
   * @return - The state of the brick of movable i.e. right or left translation
   */
  public int getState()
  {
    return this.state;
  }// end getState()
  
  /**
   * @return - The speed of the brick if movable
   */
  public int getSpeed()
  {
    return this.speed;
  }// end getSpeed()
  
  public int getVertex()
  {
    return this.vertex;
  }// end getVertex()
  
  public int getCorner()
  {
    return this.corner;
  }// end getCorner()
  
  /**
   * @return - Whether the brick if destroyed or not
   */
  public boolean getDestroy()
  {
    return this.destroy;
  }// end getDestroy()
  
  /**
   * Get the transparent rectangle for the collision check
   */
  public Rectangle getBounds()
  {
    return (new Rectangle(this.x, this.y, this.width, this.height));
  }// end getBounds()
  
  
  /**************************
    * Set Methods
    * **********************/
  
  /**
   * Set the x coordinate of the brick
   */
  public void setX(int x)
  {
    this.x = x;
  }// end setX(int)
  
  /**
   * Set the y coordinate of the brick
   */
  public void setY(int y)
  {
    this.y = y;
  }// end setY(int)
  
  /**
   * Set the direction of translation of of the brick
   */
  public void setState(int state)
  {
    this.state = state;
  }// end setState(int)
  
  /**
   * Set the health of the brick
   */
  public void setHealth(int health)
  {
    this.health = health;
  }// end setHealth(int)
  
  
  /**************************
    * Instance Methods
    * **********************/
  
  /**
   * Update the bricks
   * 
   * @param ArrayList <Brick> - array list containing all the brick objects
   * @param int width - the width of the Jframe
   * @param int height - the height of the Jframe
   * @return void - update the brick information
   * */
  public void updateBrick(ArrayList<Rectangle_Bricks> brickList, int width, int height)
  {
    
    // Update the coordinate of the total width and height of the brick
    this.totalWidth = this.x + this.width;
    this.totalHeight = this.y + this.height;
    
    // Destroy the brick if the health of the brick reaches 0
    if (this.health <= 0 && this.brickOpacity >= 0)
    {
      // If the brick is not destroyed
      if(!this.destroy)
      {
        // Decrease the total number of left over brick to 1
        FileControl.totalBricks--;
        
        // Increase the number of comboCounter by 1 after a brick is destroyed
        Destructor.comboCounter++;
        
        Destructor.player.setPoints(this.points);
        Destructor.lblBricks.setText("Bricks Left : " + FileControl.totalBricks);
        
        // Drop a random power ups
        if(FileControl.totalBricks % Destructor.powerCounter == 0)
        {
          Destructor.powerList.add(new Power_Ups(this.x, this.y, (int)(Math.random() * 9)));
          Destructor.powerCounter = (int)(Math.random() * 5) + 15;
        }
        
        // Drop treasure after every brick is destroyed
        else if(Ball.ballType == 2 && FileControl.totalBricks % 3 == 0)
        {
          Destructor.powerList.add(new Power_Ups(this.x, this.y, 6));
        }// end if
        
        // If all the bricks are destroyed, display the next level option
        if(FileControl.totalBricks == 0)
        {
          Destructor.levelDone = true;
          Destructor.nextLevelTime = System.currentTimeMillis();
        }// end if
      }// end if
      
      // Destroy the brick
      this.destroy = true;
      
      // Decrease the opacity of the brick after it is destroyed
      this.brickOpacity -= 0.1;
      
      // Decrease the opacity of the points
      this.pointOpacity -= 0.01;
      
    }// end if
  }// end refreshBrick(ArrayList<Rectangle_Bricks>, int, int)
  
  /**
   * Draws the image associated with the brick object
   * 
   * @param Graphics g - used for drawing in Java
   * @return void - draw the brick
   * */
  public void drawBrick(Graphics g)
  {
    // Get the Graphics2D
    this.g2d = (Graphics2D) g;
    
    // Draw the brick if the brick is not destroyed
    if (this.brickOpacity > 0)
    {
      // Decrease the opacity of the brick after it is hit by the ball
      this.ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.brickOpacity);
      this.g2d.setComposite(ac);
      
      // Draws the image associated with the brick object given by the instance method
      this.g2d.drawImage(this.image, this.x, this.y, this.width, this.height, null);
      
    }// end if
    
    //Display the point at the top of the brick
    if(this.destroy && this.pointOpacity > 0)
    {
      // Decrease the opacity of the brick after it is hit by the ball
      this.ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
      this.g2d.setComposite(ac);
      
      this.g2d.setFont(this.font);
      this.g2d.setColor(Color.BLUE);
      this.g2d.drawString("" + this.points, this.x, this.y - (this.pointOpacity * 10));
      
    }// end if
  }// end drwaBrick(Graphics)
  
}// end Rectangle_Brick Class
