//#Ashish

// Import required packages

package ashish.hattimare;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Key_Handler implements KeyListener
{
  
  /*****************************
    * Instance Variables
    * **************************/
  
  // Stores a given slider object
  private Slider slider;
  
  // Store a copy of the File_Controlle Class
  private Fire_Controller fireControl;
  
  // Boolean variable determines whether the game is paused
  private boolean pauseGame;
  
  private boolean shootFire;
  
  // Font for teh pause message
  private Font font;
  
  // Stores keyCode for use with keyEvent in KeyListener
  private int keyCode;
  
  /*****************************
    * Constructors
    ****************************/
  
  /**
   * Default Constructor:
   * Creates an instance of this class, assigns the slider to this class, sets the font, and sets state to unpaused
   * @param Slider - Slider object the fire is launched from
   **/
  public Key_Handler(Slider slider, Fire_Controller fireControl)
  {
    this.slider = slider;
    this.fireControl = fireControl;
    this.pauseGame = false;
    this.shootFire = true;
    this.font = new Font("Bauhaus 93", Font.BOLD, 70);
    
  }// end default constructor
  
  /*****************************
    * Get Methods
    ****************************/
  
  /**
   * Return boolean value for whether or not the game is paused
   * @param none
   * @return boolean - Whether the game is paused
   */
  public boolean getPause()
  {
    return this.pauseGame;
  }// end getPause
  
  /*****************************
    * Instance Methods
    * **************************/
  
  //#action
  /**
   * Methods uses KeyListener to watch for keyboard presses
   * @param KeyEvent - java KeyListener object
   * @return void
   **/
  @Override
  public void keyPressed(KeyEvent e)
  {
    //Retrieves the keycode of the key presses
    this.keyCode = e.getKeyCode();
    
    if (this.keyCode == KeyEvent.VK_SPACE)
    {
      // Launch a fire ball when spacebar is pressed and the game is started
      if (this.shootFire && slider.getBlaster() && !Destructor.levelDone && Destructor.startGame)
      {
        fireControl.addFire(new FireBalls(slider.getCenter(), 491));
        this.shootFire = false;
      }// end if
      
      // Start the game
      if(!Destructor.startGame && Destructor.gameTime > 1)
      {
        Destructor.startGame = true;
      }// end if
    }
    else if (this.keyCode == KeyEvent.VK_P)
    {
      // Pause the game when "P" is pressed
      if(!Destructor.levelDone)
      {
        // Pause the game
        if(Destructor.fps.isRunning())
        {
          this.pauseGame = true;
        }
        
        // Continue the game
        else
        {
          this.pauseGame = false;
          Destructor.fps.start();
        }// end if
      }// end if
    }// end if
    
  }// end keyPressed
  
  //#cheat
  /**
   * Methods uses KeyListener to watch for keyboard releases, used for cheat mode commands
   * @param KeyEvent - java KeyListener object
   * @return void
   **/
  @SuppressWarnings("unchecked")
  @Override
  public void keyReleased(KeyEvent e)
  {
    //Retrieves the keycode of the key releases
    this.keyCode = e.getKeyCode();
    
    if(this.keyCode == KeyEvent.VK_SPACE)
    {
      this.shootFire = true;
    }
    
    // Cheat Mode only if the God Mode is activated
    if(Destructor.godMode)
    { 
      // Determines which cheat is activated based on the keyRelease
      if (this.keyCode == KeyEvent.VK_NUMPAD0 || this.keyCode == KeyEvent.VK_0 || this.keyCode == KeyEvent.VK_INSERT)
      {
        // Balster activate and deactivate
        slider.setBlaster(!slider.getBlaster());
      }
      else if (this.keyCode == KeyEvent.VK_NUMPAD1 || this.keyCode == KeyEvent.VK_1 || this.keyCode == KeyEvent.VK_END)
      {
        // Change the type of the ball
        Ball.ballType = (int)(Math.random() * 4);
        
        // change the mass of the ball
        for(Ball b : Destructor.multipleList)
        {
          
          if(Ball.ballType == 1)
            Ball.mass = 900; // Change ball mass to 900 if red ball
          else
            Ball.mass = 50;  // Change ball mass to 50 if any other 
          
          b.setChange(true);
        }// end for
      }
      else if (this.keyCode == KeyEvent.VK_NUMPAD2 || this.keyCode == KeyEvent.VK_2 || this.keyCode == KeyEvent.VK_DOWN)
      {
        //#object
        // multiple balls
        for(int x = 0; x < 4 && Destructor.multipleList.size() < 30 && Destructor.startGame; x++)
        {
          Destructor.multipleList.add(new Ball((int)Destructor.multipleList.get(0).getX(),
                                     (int)Destructor.multipleList.get(0).getY(), (float)(Math.random() * 360)));
        }// end for
      }
      else if (this.keyCode == KeyEvent.VK_NUMPAD3 || this.keyCode == KeyEvent.VK_3 || this.keyCode == KeyEvent.VK_PAGE_DOWN)
      {
        // Extra life
        Destructor.player.setLives(Destructor.player.getLives() + 1);
        Destructor.lblLives.setText("Pads Left : " + Destructor.player.getLives());
        
      }
      else if (this.keyCode == KeyEvent.VK_NUMPAD4 || this.keyCode == KeyEvent.VK_4 || this.keyCode == KeyEvent.VK_LEFT)
      {
        // Minimize slider
        slider.setBlaster(false);
        slider.setWidth(50);
      }
      else if (this.keyCode == KeyEvent.VK_NUMPAD5 || this.keyCode == KeyEvent.VK_5 || this.keyCode == KeyEvent.VK_CLEAR)
      {
        // Maximize slider
        slider.setBlaster(false);
        slider.setWidth(150);
      }
      else if (this.keyCode == KeyEvent.VK_NUMPAD7 || this.keyCode == KeyEvent.VK_7 || this.keyCode == KeyEvent.VK_HOME)
      {
        // Fast Ball
        Ball.tempIncrease = 1.8f;
        Ball.increase = Ball.tempIncrease;
      }
      else if (this.keyCode == KeyEvent.VK_NUMPAD8 || this.keyCode == KeyEvent.VK_8 || this.keyCode == KeyEvent.VK_UP)
      {
        // Slow Ball
        Ball.tempIncrease = 1.0f;
        Ball.increase = Ball.tempIncrease;
      }// end if
    }// end if
  }// if keyReleased(KeyEvent)
  
  /**
   * Mandatory keyTyped method for KeyListener
   * @param KeyEvent - java KeyListener object
   * @return void
   **/
  @Override
  public void keyTyped(KeyEvent e){  
  }// end KeyTyped
  
  /**
   * Draw the Pause Label at the center of the game when paused
   * @param g - Graphics required to draw the Pause Label
   * @param width - width of the game screen
   * @param height - height of the game screen
   * @return void
   */
  public void drawPause(Graphics g, int width, int height)
  {
    if(this.pauseGame)
    {
      //Crestes a border for teh puse message
      g.setColor(Color.BLACK);
      g.fillRect(width / 2 - 150, height / 2 - 50, 300, 100); 
      g.setColor(Color.WHITE);
      g.fillRect(width / 2 - 147, height / 2 - 47, 294, 94);
      
      //Write the pause message
      g.setFont(font);
      g.setColor(Color.BLACK);
      g.drawString("PAUSE", width / 2 - 105, height / 2 + 25);
    }// end if  
  }// end drawPause(Graphics, int, int)
  
}// end Key_Handler
