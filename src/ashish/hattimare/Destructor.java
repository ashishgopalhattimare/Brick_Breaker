//#Ashish

/**
 * Game Class
 * The name of the Class must be changed to **Destructor
 * */

package ashish.hattimare;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Destructor implements ActionListener
{
  
  
  /**************************
    * Instance Variables
    * **********************/
  
  public JPanel screenPanel, gamePanel;
  public JPanel scoreboardPanel, bottomPanel;
  public JPanel titlePanel, livescorePanel, levelBrickPanel;
  public JPanel livePanel, scorePanel, levelPanel, brickPanel, quitPanel;
  
  public JButton btnQuit;
  
  public Sound sound;
  
  // Game Class that handles the animation
  public Game_Panel game;
  
  // Rectangular Bricks
  public Rectangle_Bricks rectBricks;
  
  // Power Ups Class
  public Power_Ups powerUps;
  
  // Arrow Class that is displayed at the beginning of the ball above the ball
  public Arrow_Class arrow;
  
  // Ball Class that is used to break the bricks
  public Ball ball;
  
  // Collision Class that handles all type of collision that happens during the game
  public Collision collision;
  
  public FireBalls fireBalls;
  public Fire_Controller fireControl;
  public FileControl file;
  
  public Mouse_Handler mouseHandler;
  public Key_Handler keyHandler;
  
  public Font font = new Font("Bauhaus 93", Font.BOLD, 25);
  public Font comboFont = new Font("Bauhaus 93", Font.BOLD, 45);
  
  // Image of the title
  public ImageIcon imgTitle, successImage, demoImage;
  
  
  /**************************
    * Class Variables
    * **********************/
  
  // Set the dimensions of the screen
  public static final int WIDTH = 1000, HEIGHT = 550;
  
  // Total Levels in the game
  public static final int LEVEL_END = 10;
  
  // Game Frame
  public static JFrame gameFrame;
  
  // Static Components required for the gamePlay
  public static JPanel successPanel, demoPanel;
  public static JLabel lblScore, lblLives, lblTitle, lblBricks, lblLevel;
  public static long startTime, endTime, gameTime, nextLevelTime;
  public static boolean godMode;
  
  // Player Class
  public static Player player;
  
  // Slider Class
  public static Slider slider;
  
  // Timer
  public static Timer fps;
  
  // Start the game
  public static boolean startGame;
  
  // Chekc whether the player is playing campaign mode or not
  public static boolean campaign;
  public static boolean loadClasses;
  
  public static boolean levelDone;
  
  // Current level int
  public static int currentLevel;
  
  // How many brick must be destroyed to get powerUp
  public static int powerCounter, comboPoints, comboCounter;
  
  // ArrayList for the Bricks
  public static ArrayList<Rectangle_Bricks> rectBrick_List;
  
  // ArrayList for the powers
  public static ArrayList<Power_Ups> powerList;
  
  // Multiple Ball
  public static ArrayList<Ball> multipleList;
  
  // Store the choice of the player
  public static int choice;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor
   * @param file - Create a copy of the FileControl Class to use it in the this class
   * */
  public Destructor(FileControl file)
  {
    
    // Create a copy of the file Class
    this.file = file;
    
    loadClasses = false;
    
    startGame  = false;
    
    // Whether the player is playing campaign mode or the player mode
    campaign = true;
    
    // Whether the current level is completed or not
    levelDone = false;
    
    // Display the power on the screen after this much brick are destroyed
    powerCounter = 23;
    
    // Set the comboPoints to 750
    comboPoints = 750;
    comboCounter = 0;
    
    // Set the default god mode to false
    godMode = false;
    
    // Game Frame load
    gameFrame = new JFrame();
    gameFrame.setTitle("Destructor");
    
    // Load all the Classes that required to 
    game         = new Game_Panel();
    sound        = new Sound();
    slider       = new Slider(WIDTH);
    ball         = new Ball(slider.getX());
    arrow        = new Arrow_Class();
    player       = new Player();
    collision    = new Collision();
    //fireBalls    = new FireBalls();
    fireControl  = new Fire_Controller(slider);
    mouseHandler = new Mouse_Handler(fireControl, slider);
    keyHandler   = new Key_Handler(slider, fireControl);
    rectBrick_List = new ArrayList<Rectangle_Bricks>();
    powerList      = new ArrayList<Power_Ups>();
    multipleList   = new ArrayList<Ball>();
    
    // All the JPanel that are required during the game play
    screenPanel     = new JPanel();
    livescorePanel  = new JPanel();
    levelBrickPanel = new JPanel();
    gamePanel       = new JPanel();
    successPanel    = new JPanel(new BorderLayout());
    demoPanel       = new JPanel(new BorderLayout());
    scoreboardPanel = new JPanel(new GridLayout(1, 2));
    bottomPanel     = new JPanel(new GridLayout(1, 2));
    titlePanel      = new JPanel(new FlowLayout(FlowLayout.LEFT));
    livePanel       = new JPanel(new FlowLayout(FlowLayout.LEFT));
    scorePanel      = new JPanel(new FlowLayout(FlowLayout.LEFT));
    levelPanel      = new JPanel(new FlowLayout(FlowLayout.LEFT));
    brickPanel      = new JPanel(new FlowLayout(FlowLayout.LEFT));
    quitPanel       = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    //Image display when all the levels are completed in the campaign mode
    successImage = new ImageIcon("success.png");
    successPanel.add(new JLabel(successImage));
    
    //Display message after the demo level is played
    demoImage    = new ImageIcon("demo.png");
    demoPanel.add(new JLabel(demoImage));
    
    // Load all the JLabel for the main game
    lblTitle = new JLabel();
    lblLives = new JLabel("Pads Left : " + player.getLives(), JLabel.LEFT);
    lblScore = new JLabel(" Score : " + player.getPoints(), JLabel.LEFT);
    lblLevel = new JLabel("Level " + currentLevel, JLabel.LEFT);
    lblBricks = new JLabel("Bricks Left : " + FileControl.totalBricks, JLabel.LEFT);
    
    // ScoreBoard display at the top and the bottom display in the gamePlay
    livescorePanel.setLayout(new BoxLayout(livescorePanel, BoxLayout.X_AXIS));
    levelBrickPanel.setLayout(new BoxLayout(levelBrickPanel, BoxLayout.X_AXIS));
    
    // Screen Panel for the game where the game is displayed
    screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.Y_AXIS));
    screenPanel.setBorder(BorderFactory.createCompoundBorder(
                          BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(0, 15, 0, 15)));
    
    // Setup the game Panel
    gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));
    gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    gamePanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 4), BorderFactory.createLoweredBevelBorder()));
    
    
    // Load the title image of the gamePlay
    loadTitle();
    
    // Set the animator
    fps = new Timer(13, this);
    
    // Add the default ball to the multipleList array of the ball
    multipleList.add(ball);
    
    // Add all the necessary Listener to the game
    game.addMouseListener(mouseHandler);
    game.addMouseMotionListener(mouseHandler);
    game.addKeyListener(keyHandler);
    
    // Quit Button Initializer
    btnQuit = new JButton("QUIT");
    btnQuit.addActionListener(this);
    btnQuit.setFont(font);
    quitPanel.add(btnQuit);
    
    
    // Set the Font of the JLabels in the gamePlay
    lblLives.setFont(font);
    lblScore.setFont(font);
    lblLevel.setFont(font);
    lblBricks.setFont(font);
    
    // Add the label to their respective panels
    livePanel.add(lblLives);
    scorePanel.add(lblScore);
    levelPanel.add(lblLevel);
    brickPanel.add(lblBricks);
    
    // Add the "left pads" and "score" labels to the livescorePanel
    livescorePanel.add(livePanel);
    livescorePanel.add(scorePanel);
    
    // Add the "current level" and the "total Bricks left" labels to the levelBrickpanel
    levelBrickPanel.add(levelPanel);
    levelBrickPanel.add(brickPanel);
    
    // Add the game title to the titlePanel
    titlePanel.add(lblTitle);
    
    // Add the title, left pads and the score label at the top of the game screen
    scoreboardPanel.add(titlePanel);
    scoreboardPanel.add(livescorePanel);
    
    // Add the quit button to the bottom Panel
    bottomPanel.add(quitPanel);
    bottomPanel.add(levelBrickPanel);
    
    // Add the animation of the game (gamePanel) to the game
    gamePanel.add(game);
    
    // Add the scoreboard, game, and the bottom displayed labels to the mainScreen panel
    screenPanel.add(scoreboardPanel, BorderLayout.NORTH);
    screenPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    screenPanel.add(gamePanel, BorderLayout.CENTER);
    screenPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    screenPanel.add(bottomPanel, BorderLayout.SOUTH);
    
    // Display the game screen
    gameFrame.add(screenPanel);
    
    // Focus the gameScreen to use KeyListener
    game.requestFocusInWindow();
    gameFrame.requestFocusInWindow();
    
  }// end Destructor(FileControl) default Constructor
  
  
  /**
   * Drawing and Animation of all the objects in the game (Ball, Bricks, Slider, Points)
   * @author Ashish
   */
  @SuppressWarnings("serial")
  class Game_Panel extends JPanel
  {
    // Dimension of the Game Panel
    public int width, height;
    
    // Image at the background of the gamePlay
    Image background;
    
    /****************************/
    //long time = System.currentTimeMillis();
    //int fps = 0;
    
    /**
     * Default Constructor
     * */
    public Game_Panel()
    {
      width = getWidth();
      height = getHeight();
      background = Toolkit.getDefaultToolkit().getImage("background.png");
      
      UIManager UI= new UIManager();
      UI.put("OptionPane.background", Color.WHITE);
      UI.put("Panel.background", Color.WHITE);
      
    }// end Game_Panel
    
    @Override
    public void paintComponent(Graphics g)
    {
      /*
       fps++;
       if (System.currentTimeMillis() - time >= 1000)
       {
       time = System.currentTimeMillis();
       System.out.println(Ball.increase);
       fps = 0;
       }*/
      
      super.paintComponent(g);
      
      // Get the dimensions of the screen
      width = getWidth();
      height = getHeight();
      
      // Draw the background image on the screen
      g.drawImage(background, 0, 0, width, height, this);
      
      // Set the center x coordinate of the slider to the mouse pointer
      slider.setX(mouseHandler.getX() - slider.getWidth() / 2);
      
      // Get the game time
      endTime = System.currentTimeMillis();
      gameTime = (int) (endTime - startTime) / 1000;
      
      // Reset all the classes
      if(!loadClasses)
      {
        resetObjects(height);
        loadClasses = true;
      }// end if
      
      // Delay the game for one second
      if (gameTime >= 1)
      {
        // Activate the firing
        Fire_Controller.fireActivate = true;
        
        // When the ball is not released
        if (!startGame)
        {
          // Set the initial coordinates of the ball to the center of the slider
          for(Ball b : multipleList)
          {
            b.setX(slider.getCenter() - Ball.DIAMETER / 2);
            b.setY(height - slider.HEIGHT - Ball.DIAMETER - 3);
            
            // The angle of translation of the ball from the slider
            b.setSpeed(arrow.getAngle());
          }// end for
          
          // Draw the arrow on the screen
          arrow.setX(slider.getCenter() - 55);
          arrow.setY(height - 86);
          arrow.drawArrow(g);
        }
        
        // When the ball is released
        else
        {
          for(Ball b : multipleList)
          {
            b.update();
          }// end for
        }// end if
        
        // Draw the ball on the screen
        for(Ball b : multipleList)
        {
          b.setImage();
          g.drawImage(b.getImage(), (int) b.getX(), (int) b.getY(), Ball.DIAMETER, Ball.DIAMETER, this);
          
        }// end for
      }// end if
      
      // Update the fireBalls
      fireControl.update();
      
      // Draw the fireBalls
      fireControl.draw(g);
      
      // Check for fireball collision only if blaster slider is activated or the fireball are on the screen
      if (slider.getBlaster() || fireControl.getList().size() > 0)
      {
        // Check for every fireballs collision detection
        for (int i = 0; i < fireControl.getList().size(); i++)
        {     
          // Check for the collision with all the bricks
          for (Rectangle_Bricks r : rectBrick_List)
          {
            // Check if the brick is not destroyed and collision happened
            if (!r.getDestroy() && Collision.fire_rectBricks(fireControl.getList().get(i), r))
            {
              // Reduce the health of the brick only if its not a metal brick
              if (!(r.getType() == 6 || r.getType() == 7))
              {
                r.setHealth(r.getHealth() - FireBalls.MASS);
              }// end if
              
              // Remove the fireball if it hits the non soft brick
              if (r.getType() != 0)
              {
                fireControl.removeFire(fireControl.getList().get(i));
              }// end if
              
              // Play the break sound when the fireball hits the brick
              Sound.play("break.wav");
              break;
              
            }// end if
          }// end for
        }// end for
      }// end if
      
      //Brick rectangluar events detection only if the current level is less than LEVEL_TYPE_CHANGE limit
      if(currentLevel <= LEVEL_END)
      {
        // Brick to Brick collision
        for (Rectangle_Bricks r : rectBrick_List)
        {
          // Move the brick only if the brick are movable
          if ((r.getType() == 4 || r.getType() == 5 || r.getType() == 7))
          {
            // Number for collisions checked every loop
            for (int i = 0; i < r.getSpeed(); i++)
            {
              collision.brickCollision(r, rectBrick_List, height, width);
              
            }// end for
          }// end if
        }// end for
        
        
        // Draw the bricks and update the brick's state
        for (Rectangle_Bricks r : rectBrick_List)
        {
          // Draw the brick until the opacity of the brick is 0
          if (r.brickOpacity > 0)
          {
            r.drawBrick(g);
            r.updateBrick(rectBrick_List, width, height);
            
          }// end if
        }// end for
        
        // Check for the ball(s) and brick(s) collision
        for(Ball b : multipleList)
        {
          for (Rectangle_Bricks r : rectBrick_List)
          {
            // If the brick is not destroyed
            if (!r.getDestroy())
            {
              // Check ball and brick collision
              collision.ballBrick(r, b, slider);
              
              // If the ball collided with the Brick
              if (collision.getHit())
              {
                // Skip the health reduction if the brick is metal type
                if (r.getType() != 6 && r.getType() != 7)
                {
                  r.setHealth(r.getHealth() - Ball.mass);
                  Sound.play("hit.wav");
                  
                }// end if
              }// end if
            }// end if
          }// end for
        }// end for
      }// end if
      
      // Draw the powerUps
      for(Power_Ups p : powerList)
      {
        // Draw the powerUps
        p.update();
        p.draw(g);
        
        // Add the power to the game, if collision event occurs
        if((p.getBounds().intersects(slider.getBounds())))
        { 
          // If the power is taken by the player
          powerFeatures(p);
          
          // remove the power from the powerList
          powerList.remove(p);
          break;
        }// end if
        
        // Remove the powerUp from the powerList if the power is not taken
        if(p.getY() + Power_Ups.DIMENSION > height)
        {
          // remove the power from the powerList
          powerList.remove(p);
          break;
        }// end if
      }// end for
      
      // Draw the slider on the screen
      slider.drawSlider(g, height - slider.HEIGHT);
      
      // Ball to the Wall collision
      for(int x = 0; x < multipleList.size(); x++)
      {
        Collision.ballWall(multipleList.get(x), width, height, player, slider, x);
      }// end for
      
      // Ball to the Slider collision
      for(Ball b : multipleList)
      {
        if (!b.getBounce() && b.getY() + Ball.DIAMETER > height - 30)
        {
          collision.ballSlider(b, slider, player, height, sound);
        }// end if
      }// end for
      
      // If the player has finshed the current level
      if(levelDone && fireControl.getList().size() == 0 && System.currentTimeMillis() - nextLevelTime >= 500)
      {
        //Stop the background game
        Destructor.fps.stop();
        
        // If the player is playing the campaign mode
        if(campaign)
        {
          // If the current level is the last level
          if(currentLevel == LEVEL_END)
          {
            // Display te congratulations label on the screen
            JOptionPane.showMessageDialog(null, successPanel, "Destructor", JOptionPane.PLAIN_MESSAGE);
            
            Main.mEnd.endScore(player.getPoints());
            Main.core.end();
            
            choice = -1;
          }
          else
          {
            // wait for 2 seconds before the next level begins
            try{Thread.sleep(2000);}catch(InterruptedException e){}
            
            choice = 0;
          }// end if
        }
        
        // If the player is playing the player mode
        else
        { 
          // Display the demo label
          JOptionPane.showMessageDialog(null, demoPanel, "Destructor", JOptionPane.PLAIN_MESSAGE);
          
          choice = -1;
          
          // Reset the game and close the game after the demo label is closed and go to the main menu
          resetObjects(height);
          
          // Go the main menu
          deactivate();
          Main.core.main();
        }
        
        // If the player has decided to go to next level in the campaign mode
        if(choice == 0)
        {
          // If the player is playing campaign mode and has finished the current level
          if(campaign)
          {
            // Rectangular next level
            if(!(currentLevel + 1 > LEVEL_END))
            {
              //Next Level activate
              currentLevel++;
              
              //Reset all the objects
              resetObjects(height);
              
              // Read the text file of the next level and load it
              Destructor.rectBrick_List = file.rectLevel("level" + currentLevel + ".txt", currentLevel);
              
              // Reset the brick Breaker Class
              setDefault();
              
            }// end if
          }
          
          // Go the main Menu, If the Player want to quit the level
          else
          {
            resetObjects(height);
            
            // Go the main menu
            deactivate();
            Main.core.main();
            
          }// end if 
        }
        
        // Go to game menu
        else
        {
          resetObjects(height);
          
          // Go the main menu
          deactivate();
          Main.core.main();
          
        }// end if
      }// end if
      
      // Display the pause
      if(keyHandler.getPause())
      {
        keyHandler.drawPause(g, width, height);
        Destructor.fps.stop();
      }// end if
      
    }// end paintComponent(Graphics)
    
  }// end GamePanel Class
  
  
  /**************************
    * Instance Methods
    * **********************/
  
  /**
   * Load the image of the title
   */
  public void loadTitle()
  {
    // Attempt to load the title image on to the JLabel for title
    try
    {
      imgTitle = new ImageIcon("title.png");
      imgTitle = new ImageIcon(imgTitle.getImage().getScaledInstance(360, 40, java.awt.Image.SCALE_SMOOTH));
      lblTitle.setIcon(imgTitle);
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }// end try/catch
    
  }// end loadTitle()
  
  /**
   * If the powers are taken by the player's slider, check for the type of power
   * */
  public void powerFeatures(Power_Ups p)
  {
    // take the power in player mode only if the ball is launched
    if(startGame)
    {
      // Activate the blaster slider
      if(p.getType() == 0)
      {
        slider.setBlaster(true);
      }
      
      // Change the type of the ball
      else if(p.getType() == 1)
      {
        Ball.ballType = (int)(Math.random() * 4);
        
        for(Ball b : multipleList)
        {
          if(Ball.ballType == 1)
            Ball.mass = 900; // Change ball mass to 900 if red ball
          else
            Ball.mass = 50;  // Change ball mass to 50 if any other
          
          b.setChange(true);
        }// end for
      }
      
      // Multiple Balls
      else if(p.getType() == 2)
      {
        //#object
        multipleList.add(new Ball((int)multipleList.get(0).getX(), (int)multipleList.get(0).getY(), (float)(Math.random() * 360)));
        multipleList.add(new Ball((int)multipleList.get(0).getX(), (int)multipleList.get(0).getY(), (float)(Math.random() * 360)));
      }
      
      // Extra Lives
      else if(p.getType() == 3)
      {
        player.setLives(player.getLives() + 1);
        lblLives.setText("Pads Left : " + player.getLives());
      }
      
      // Minimize the slider
      else if(p.getType() == 4)
      {
        slider.setBlaster(false);
        slider.setWidth(50);
      }
      
      // Maximize the slider
      else if(p.getType() == 5)
      {
        slider.setBlaster(false);
        slider.setWidth(160);
      }
      
      //Increase player's points
      else if(p.getType() == 6)
      {
        player.setPoints((int)(Math.random() * 50) * 55);
      }
      
      // Increase the speed of the ball
      else if(p.getType() == 7)
      {
        Ball.tempIncrease = 1.7f;
        Ball.increase = Ball.tempIncrease;
      }
      
      // Decrease the speed of the ball
      else if(p.getType() == 8)
      {
        Ball.tempIncrease = 1.0f;
        Ball.increase = Ball.tempIncrease;
      }//end if
    }// end if
  }// end powerFeatures(Power_Ups)
  
  
  /**************************
    * Class Methods
    * **********************/
  
  /**
   * Reset the game components
   */
  public static void setDefault()
  {
    // Start the game by allowing the player to launch the ball
    startGame = false;
    
    // Set the default level completed to false
    levelDone = false;
    
    // Load the game before the game starts
    loadClasses = false;
    
    choice = 2;
    powerCounter = 23;
    comboCounter = 0;
    
    // Start the game time again
    startTime = System.currentTimeMillis();
    
    // Start the timer
    fps.start();
    
  }// end setDefault()
  
  /**
   * Run the Game
   * */
  public static void activate()
  {
    
    gameFrame.pack();
    gameFrame.setVisible(true);
    gameFrame.setResizable(false);
    gameFrame.setLocationRelativeTo(null);
  }// end activate()
  
  /**
   * Close the game and go to main menu
   * */
  public static void deactivate()
  {
    gameFrame.setVisible(false);
  }// end deactivate()
  
  /**
   * Reset the multiple ball list to 1 ball in the list (initial ball)
   */
  public static void resetMultipleList()
  {
    while(multipleList.size() != 1)
    {
      multipleList.remove(1);
    }// end while()
    
  }// end resetMultipleList()
  
  /**
   * Reset all the objects in the game
   * @param height
   */
  public static void resetObjects(int height)
  {
    // Set the slider to default
    slider.setDefault();
    
    // Reset the multiple ball list if their are more than one ball
    if(multipleList.size() > 1)
    {
      resetMultipleList();
    }// end if
    
    // Set the location of the ball to default
    multipleList.get(0).setDefault(height - slider.HEIGHT - Ball.DIAMETER - 3, 0);
    
    Ball.tempIncrease = Ball.INCREASE;
    Ball.increase = Ball.tempIncrease;
    
    // Empty the powerList
    powerList.clear();
  }// end resetGame(int)
  
  
  /**************************
    * Event Handler Method
    * **********************/
  
  /**
   * Run the animation and listen to the button clicked events during the game
   * */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    // Repaint the game screen
    game.repaint();
    
    // Focus the game for the keyListener every interval
    game.requestFocusInWindow();
    
    // If the quit button is checked
    if (e.getSource() == btnQuit)
    {
      // Stop the timer
      fps.stop();
      
      // Display the JOptionPane for the Quit Decision
      choice = JOptionPane.showConfirmDialog(null, "Do you want to QUIT the Game?\n", "Quit the Game", JOptionPane.YES_NO_OPTION);
      
      // Continue the game
      if(choice != 0)
      {
        fps.start();
      }
      
      // Quit the game and go to main menu
      else
      { 
        resetObjects(HEIGHT - 9);
        
        // Reset the fireBalls array for the next game
        fireControl.setDefault();
        
        // Close the game and open the main menu
        deactivate();
        Main.core.main();
        
      }// end if
    }// end if
  }// end actionPerformed(ActionEvent)
  
}// end Destructor Class
