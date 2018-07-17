//#Ashish

/**
 * Collision Handler Class
 * * */

// Import all the required java packages

package ashish.hattimare;

import java.util.ArrayList;
import javax.swing.JOptionPane;



public class Collision
{
  
  /**************************
    * Instance Variables
    * **********************/
  
  // The extreme left x coordinate of the ball
  private int xLeft;
  
  // The extreme right x coordinate of the ball
  private int xRight;
  
  // The center x coordinate of the ball
  private int xCenter;
  
  // The extreme bottom y coordinate of the ball
  private int yBottom;
  
  // The distance between the slider center and the ball center x coordinate
  private float distance;
  
  // Circular Collision variables
  private float xDiff, yDiff, tempDistance;
  private float xCollision, yCollision;
  private float dotProduct;
  private float collisionScale;
  
  // Boolean to check whether the brick and ball collision event occurred or not
  private boolean brickHit;
  
  // Check whether the brick is moving or not
  public boolean brickMoved;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Collision() constructor
   * */
  public Collision()
  {
    this.xLeft = 0;
    this.xCenter = 0;
    this.xRight = 0;
    this.yBottom = 0;
    this.distance = 0;
    
    this.brickHit = false;
    
  }// end Collision() Constructor
  
  
  /**************************
    * Get Methods
    * **********************/
  
  /**
   * Get the hit boolean for the brick and ball collision event
   * * */
  public boolean getHit()
  {
    return this.brickHit;
  }// end getHit()
  
  
  /**************************
    * Instance Methods
    * **********************/
  
  /**
   * @author Ashish
   * 
   * Method name : ballSlider 
   * Purpose : Change the direction of the ball if it collides with the slider
   * 
   * @param ball - The Ball Class
   * @param slider - The Slider Class
   * @param player - Player Class
   * @param height - the height of the screen
   * @param sound - Sound Class
   * 
   * @return void - change the direction of the ball after it collides with the slider
   * */
  public void ballSlider(Ball ball, Slider slider, Player player, int height, Sound sound)
  {
    // Set the extreme left x coordinate of the ball
    this.xLeft = (int) ball.getX();
    
    // Set the extreme right x coordinate of the ball
    this.xRight = (int) (ball.getX() + Ball.DIAMETER);
    
    // Set the center x coordinate of the ball
    this.xCenter = (int) (ball.getX() + Ball.DIAMETER / 2);
    
    // Set the extreme bottom y coordinate of the ball
    this.yBottom = (int) (ball.getY() + Ball.DIAMETER) + 1;
    
    // Set the distance between the slider center and the ball center x
    // coordinate
    this.distance = Math.abs(this.xCenter - slider.getCenter());
    
    if (this.yBottom >= height - slider.HEIGHT)
    {
      // If the ball hits the left side of the slider
      if (this.xRight >= slider.getX() && this.xCenter <= slider.getCenter())
      {
        // Change the vertical direction of the ball
        ball.ySpeed = -ball.ySpeed;
        
        // Change the horizontal direction of the ball
        ball.xSpeed = (ball.xSpeed < 0) ? ball.xSpeed : -ball.xSpeed;
        
        // If the ball hits the powerside of the slider
        if ((this.xRight >= slider.getX() && this.xCenter <= slider.getX() + slider.EDGE_ROUND))
        {
          Ball.increase = (Ball.increase + 0.1f);
        }
        
        // If the ball hits the gray area of the left side of the slider
        else
        {
          ball.setY(height - slider.HEIGHT - slider.BOTTOM_GAP - Ball.DIAMETER);
          Ball.increase = Ball.tempIncrease;
        }// end if
        
        // Update the angle of ball after the deflection
        ball.setSpeed(270 - (int) this.distance);
        
        // Give combo points to the player
        if(Destructor.comboCounter > 5)
        {
          player.setPoints(Destructor.comboCounter * Destructor.comboPoints);
        }
        Destructor.comboCounter = 0;
        
        //Play the sound or not
        if (!ball.getBounce())
        {
          Sound.play("bounce.wav");
          ball.setBounce(true);
          
        }// end if
      }// end if
      
      // If the ball hits the right side of the slider
      else if (this.xCenter >= slider.getCenter() && this.xLeft <= slider.getX() + slider.getWidth())
      {
        // Change the vertical direction of the ball
        ball.ySpeed = -ball.ySpeed;
        
        // Change the horizontal direction of the ball
        ball.xSpeed = (ball.xSpeed < 0) ? -ball.xSpeed : ball.xSpeed;
        
        // If the ball hits the powerside of the slider
        if ((this.xLeft <= slider.getX() + slider.getWidth() && this.xCenter >= slider.getX() + slider.getWidth() - slider.EDGE_ROUND))
        {
          Ball.increase = Ball.increase + 0.1f;
        }
        
        // If the ball hits the gray area of the left side of the slider
        else
        {
          ball.setY(height - slider.HEIGHT - slider.BOTTOM_GAP - Ball.DIAMETER);
          Ball.increase = Ball.tempIncrease;
        }// end if
        
        ball.setSpeed(270 + (int) this.distance);
        
        // Give combo points to the player
        if(Destructor.comboCounter > 5)
        {
          player.setPoints(Destructor.comboCounter * Destructor.comboPoints);
        }
        // Reset the comboCounter
        Destructor.comboCounter = 0;
        
        // Play the bounce sound or not
        if (!ball.getBounce())
        {
          Sound.play("bounce.wav");
          ball.setBounce(true);
          
        }// end if
      }// end if
    }// end if
    
  }// end ballSlider(Ball, Slider, Ball_Release, int)
  
  //#level
  /**
   * @author Zhauyang
   * 
   * Method name : brickCollision
   * Purpose : Change the direction of the moving bricks when they collide with the other bricks
   * 
   * @param brick - The current brick
   * @param brickList - The arrayList that contains all the remainging Rectangular Brick Classes
   * @param height - Maximum height of the screen
   * @param width - Maximum width of the screen
   * 
   * @return void - change the direction of the bricks after they collide
   * */
  public void brickCollision(Rectangle_Bricks brick, ArrayList<Rectangle_Bricks> brickList, int height, int width)
  {
    brickMoved = false;
    
    // Check for collision between bricks
    for (Rectangle_Bricks r : brickList)
    {
      // Check for brick collision moving in left direction
      if ((brick.getX() - 1) == (r.getX() + r.getWidth()) && brick.getState() == 1 && !r.getDestroy() &&
          brick.getY() == r.getY() || (brick.getY() > r.getY() && brick.getY() < r.getY() + r.getHeight()))
      {
        brick.setState(2);
        brickMoved = true;
      }
      // Check for brick collision moving in right direction
      else if ((brick.getX() + brick.getWidth() + 1) == r.getX() && brick.getState() == 2 && !r.getDestroy() &&
               brick.getY() == r.getY() || (brick.getY() > r.getY() && brick.getY() < r.getY() + r.getHeight()))
      {
        brick.setState(1);
        brickMoved = true;
      }// end if
    }// end for
    
    if (!brickMoved)
    {
      // If the moving bricks collide, change the direction
      if (brick.getX() == 0 && brick.getState() == 1)
      {
        brick.setState(2);
      }
      
      // If the moving bricks collide, change the direction
      else if (brick.getX() + brick.getWidth() == width && brick.getState() == 2)
      {
        brick.setState(1);
      }
      
      // translate the brick in left direction
      else if (brick.getState() == 1)
      {
        brick.setX(brick.getX() - 1);
      }
      
      // translate the brick in right direction
      else if (brick.getState() == 2)
      {
        brick.setX(brick.getX() + 1);
        
      }// end if
    }// end if
    
  }// end brickCollision(Rectangle_Bricks, ArrayList<Rectangle_Bricks>, int, int)
  
  /**
   * @author Ashish
   * 
   * Method name : ballBrick
   * Purpose : Brick to Ball Collision
   * 
   * @param r - Rectangular Brick Class
   * @param b - Ball Class
   * @param slider - Slider Class
   * 
   * @return void - change the direction of the ball after it collides with the brick
   * */
  public void ballBrick(Rectangle_Bricks r, Ball b, Slider slider)
  {
    // Set the collison event to false before checking for collision
    this.brickHit = false;
    
    // If the ball hits the bottom of the brick
    if (b.getX() + Ball.DIAMETER / 2 >= r.getX() && b.getX() + Ball.DIAMETER / 2 <= r.getTotalWidth() &&
        b.getY() >= r.getY() && b.getY() <= r.getTotalHeight())
    {
      // Deflect the ball is the ball is not red or the brick is not soft
      if ((r.getType() != 0 && Ball.ballType != 1) || ((r.getType() == 6 || r.getType() == 7) && Ball.ballType == 1))
      {
        b.ySpeed = -b.ySpeed;
        b.setY(r.getTotalHeight());
      }// end if
      
      this.brickHit = true;
      b.setBounce(false);
    }
    
    // If the ball hits the top of the brick
    else if (b.getX() + Ball.DIAMETER / 2 >= r.getX() && b.getX() + Ball.DIAMETER / 2 <= r.getTotalWidth() &&
             b.getY() + Ball.DIAMETER >= r.getY() && b.getY() + Ball.DIAMETER <= r.getTotalHeight())
    {
      // Deflect the ball is the ball is not red or the brick is not soft
      if ((r.getType() != 0 && Ball.ballType != 1) || ((r.getType() == 6 || r.getType() == 7) && Ball.ballType == 1))
      {
        b.ySpeed = -b.ySpeed;
        b.setY(r.getY() - Ball.DIAMETER);
      }// end if
      
      this.brickHit = true;
      b.setBounce(false);
    }
    
    // If the ball hits the left side of the brick
    else if (b.getX() + Ball.DIAMETER >= r.getX() && b.getX() + Ball.DIAMETER <= r.getTotalWidth() &&
             b.getY() + Ball.DIAMETER / 2 >= r.getY() && b.getY() + Ball.DIAMETER / 2 <= r.getTotalHeight())
    {
      // Deflect the ball if the ball is not red or 
      if ((r.getType() != 0 && Ball.ballType != 1) || ((r.getType() == 6 || r.getType() == 7) && Ball.ballType == 1))
      {
        b.xSpeed = -b.xSpeed;
        b.setX(r.getX() - Ball.DIAMETER);
      }// end if
      
      this.brickHit = true;
      b.setBounce(false);
    }
    
    // If the ball hit the right side of the brick
    else if (b.getX() >= r.getX() && b.getX() <= r.getTotalWidth() && b.getY() + Ball.DIAMETER / 2 >= r.getY() &&
             b.getY() + Ball.DIAMETER / 2 <= r.getTotalHeight())
    {
      // Deflect the ball is the ball is not red or the brick is not soft
      if ((r.getType() != 0 && Ball.ballType != 1) || ((r.getType() == 6 || r.getType() == 7) && Ball.ballType == 1))
      {
        b.xSpeed = -b.xSpeed;
        b.setX(r.getTotalWidth());
      }// end if
      
      this.brickHit = true;
      b.setBounce(false);
    }
    
    // Check Collision with the vertices of the brick
    else
    {
      // If the ball hits the top left vertex of the brick
      if (!this.brickHit)
      {
        vertexCollide(b, r.getX() - r.getVertex(), r.getY() - r.getVertex(), r.getType(), slider);
      }// end if
      
      // If the ball hits the bottom right vertex of the brick
      if (!this.brickHit)
      {
        vertexCollide(b, r.getTotalWidth() - r.getCorner() + r.getVertex(), 
                      r.getTotalHeight() - r.getCorner() + r.getVertex(), r.getType(), slider);
      }// end if
      
      // If the ball hits the top right vertex of the brick
      if (!this.brickHit)
      {
        vertexCollide(b, r.getTotalWidth() - r.getCorner() + r.getVertex(), r.getY() - r.getVertex(), r.getType(), slider);
      }// end if
      
      // If the ball hits the bottom left vertex of the brick
      if (!this.brickHit)
      {
        vertexCollide(b, r.getX() - r.getVertex(), r.getTotalHeight() - r.getCorner() + r.getVertex(), r.getType(), slider);
      }// end if
      
    }// end if
    
  }// end ballBrick(Rectangle_Bricks, Ball)
  
  /**
   * @author Ashish
   * 
   * Method name : vertextCollide
   * Purpose : Deflect the ball when it hits the vertex of the rectangular brick
   * 
   * @param Ball b - Ball Class
   * @param int x - get the x coordinate of the vertex
   * @param int y - get the y coordinate of the vertex
   * @param int type - type of brick the ball is colliding with
   * @Param slider - Slider Class
   * 
   * @return void - change the direction of the ball after it collides with the corner of the brick
   * * */
  public void vertexCollide(Ball b, int x, int y, int type, Slider slider)
  {
    
    // Get the difference in the x coordinates
    this.xDiff = (float) (x - b.getX());
    
    // Get the difference in the y coordinates
    this.yDiff = (float) (y - b.getY());
    
    // Get the squared distance instead of the perfect distance
    this.distance = this.xDiff * this.xDiff + this.yDiff * this.yDiff;
    
    // Get the perfect distance between the ball's center and the vertex
    this.tempDistance = (float) (Math.sqrt(this.distance));
    
    // If the ball center intersects with the vertex of the rectangular brick
    if (tempDistance <= 2 * (Ball.DIAMETER / 2))
    {
      // Get the dot product of the ball's speed and the distance components
      this.dotProduct = (float) (this.xDiff * b.xSpeed + this.yDiff * b.ySpeed);
      
      // If the ball is moving towards the rectangular brick
      if (this.dotProduct > 0)
      {
        
        // Deflect the ball is the ball is not red or the brick is not soft
        if ((type != 0 && Ball.ballType != 1) || ((type == 6 || type == 7) && Ball.ballType == 1))
        {
          /**
           * The Collision vector is the speed difference projected on the distance vector.
           * Thus, it is the component of the speed difference that is needed for the
           * speed components after the collision
           * * */
          this.collisionScale = (float) (this.dotProduct / this.distance);
          
          this.xCollision = this.xDiff * this.collisionScale;
          this.yCollision = this.yDiff * this.collisionScale;
          
          // Change the direction of the ball after collision
          b.xSpeed -= (2 * xCollision);
          b.ySpeed -= (2 * yCollision);
        }// end if
        
        this.brickHit = true;
        b.setBounce(false);
        
      }// end if
    }// end if
  }// end (Ball, int, int, String)
  
  
  /**************************
    * Class Methods
    * **********************/
  
  //#static
  /**
   * @author Ashish
   * 
   * Method name : ballWall
   * Purpose : Change the direction of the ball when it hits the wall of the screen
   * 
   * @param ball - ball that hits the wall
   * @param width - width of the screen
   * @param height - height of the screen
   * @param player - Player class that handles the lives of the player
   * @param slider - Slider Class
   * @param index - index of the ball object in the multipleList arrayList
   * 
   * @return void
   * */
  public static void ballWall(Ball ball, int width, int height, Player player, Slider slider, int index)
  {
    // When the ball collides with the left side wall of the screen
    if (ball.getX() < 0)
    {
      ball.xSpeed = -ball.xSpeed;
      ball.setX(0);
      ball.setBounce(false);
    }
    
    // When the ball collides with the right side
    else if (ball.getX() + Ball.DIAMETER > width)
    {
      ball.xSpeed = -ball.xSpeed;
      ball.setX(width - Ball.DIAMETER);
      ball.setBounce(false);
    }// end if
    
    // Whe the ball collides with the top of the screen
    if (ball.getY() < 0)
    {
      ball.ySpeed = -ball.ySpeed;
      ball.setY(0);
      ball.setBounce(false);
    }
    
    // End the game or reduce the lives or the multiple ball on the screen by 1 
    else if (ball.getY() + Ball.DIAMETER > height)
    {
      ball.ySpeed = -ball.ySpeed;
      ball.setY(height - Ball.DIAMETER);
      
      // Give combo points to the player
      if(Destructor.comboCounter > 5)
      {
        player.setPoints(Destructor.comboCounter * Destructor.comboPoints);
      }
      // Reset the comboCounter
      Destructor.comboCounter = 0;
      
      // Continue playing the game until the player's lives reaches 0
      if (player.getLives() >= 1 && !Destructor.levelDone)
      {
        // If all the ball in the game are down, decrease the number of pads left by 1
        if(Destructor.multipleList.size() == 1)
        {
          // Reduce the number of lives left by 1
          if(Destructor.startGame)
          {
            player.setLives(player.getLives() - 1);
            Destructor.startGame = false;
            System.out.println(Destructor.multipleList.size());
          }
          
          // Load next pad into the game
          Destructor.lblLives.setText("Pads Left : " + player.getLives());
          
          // Restart the timer of the game
          Destructor.fps.restart();
          Destructor.startTime = System.currentTimeMillis();
          
          // Deactivate the blaster mode and reset its width to default
          slider.setBlaster(false);
          
          // Set the Ball variables before launching the ball again
          Destructor.multipleList.get(0).setDefault(502, 0);
          
        }// end if
        
        else
        {
          // remove the ball from the list of balls when the touches the base of the game
          Destructor.multipleList.remove(Destructor.multipleList.get(index));
        }// end if
      }
      
      // When the player ends the game, and want to go back to the Main menu
      else if (player.getLives() == 0 && !Destructor.levelDone)
      {
        
        Destructor.resetObjects(height);
        
        Destructor.lblLives.setText("Pads Left : " + player.getLives());
        Destructor.startGame = false;
        
        Destructor.fps.stop();
        
        // Ask the Player to enter his name in the highscore panel only if he/ she
        // is playing campaign mode
        if(Destructor.campaign)
        { 
          Main.mEnd.endScore(player.getPoints());
          Main.core.end();
        }
        
        // End the game and go to the main menu
        else
        {
          JOptionPane.showMessageDialog(null, Destructor.demoPanel, "Destructor", JOptionPane.PLAIN_MESSAGE);
          
          Destructor.deactivate();
          Main.core.main();
        }// end if
      }// end if
    }// end if
    
  }// end ballCollision(ball, int, int, Player)
  
  /**
   * Collision between the fire ball and the bricks
   * 
   * @param f - FireBalls Class objects
   * @param r - Rectangle_Bricks Class objects
   * 
   * @return - whether the fireball intersect / collide or not
   * */
  public static boolean fire_rectBricks(FireBalls f, Rectangle_Bricks r)
  {
    return (f.getBounds().intersects(r.getBounds()));
    
  }// end fireBricks(FireBalls, Rectangle_Bricks)
  
}// end Collision Class
