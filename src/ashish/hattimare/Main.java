package ashish.hattimare;

/**********************************************************************************************************************
  * Summative Assignment - Brick Breaker
  * 
  * Description: The game Destructor is very similar to the arcade game called as "Brick Breaker"
  *              It is a single player game, and the main purpose of the player in the game is to destroy
  *              all the bricks on the screen before the player loses all the pads(lives). The game is divided
  *              into two game parts i.e. campaign mode and the level mode. If the player selects the "Start Game"
  *              option, the player will automatically enter the campaign mode, where the player would
  *              play all the level or would continue to level up until he dies or there are no more level
  *              for the player to test on. The game is a 10 level game.
  *              Besides the game mode, there is also user mode i.e. player mode and the god mode. The player
  *              mode restricts the player from using cheat codes, while in the god mode, the player gets
  *              complete freedom to add extra pads, change the type of the ball, add multiple balls on the
  *              screen and so on.
  *              To know more about the game, play the game and explore it by yourself.
  * 
  * Author: Ashish Hattimare and Zhaoyang
  * Date: June 14, 2016
  * Course: ICS4U1
  *
  ********************************************************************************************************************/

public class Main
{
  
  
  /*****************************
    * Instance Variables
    * **************************/
  
  // All the images used during the program are loaded in SpriteSheets
  public SpriteSheets sprite;
  
  // Initialize an instance of the game Class
  public static Destructor game;
  
  // Initialize an instance of the Score class, and use if for reading and writing the highscore
  public static Score score;
  
  // Initializes an instance of FileCOntrol class, used to read the level texts and generate the levels
  public static FileControl file;
  
  // Initialize the menus options
  public static MainMenu menu;
  public static LevelMenu mLevel;
  public static ScoreMenu mScore;
  public static SettingMenu mSetting;
  public static CreditMenu mCredit;
  public static HowMenu mHow;
  public static ControlMenu mControl;
  public static EndMenu mEnd;
  
  // Main Class
  public static Main core;
  
  // Initialize the splash screen when the game starts
  public static SplashScreen splash;
  
  
  /*****************************
    * Constructors
    * **************************/
  
  /**
   * Default Constructor:
   * Constructs a Main with a object of every other class
   * */
  public Main()
  {
    /**
     * Assign the Score object and then tells it to read the list of scores from a text file
     */
    score   = new Score();
    score.readScore();
    
    // Game utilizers
    file     = new FileControl();
    sprite   = new SpriteSheets();
    game     = new Destructor(file);
    
    // Game Menu
    menu     = new MainMenu();
    mLevel   = new LevelMenu(file);
    mScore   = new ScoreMenu();
    mSetting = new SettingMenu();
    mCredit  = new CreditMenu();
    mHow     = new HowMenu();
    mControl = new ControlMenu();
    mEnd    = new EndMenu();
    
    // Calls the method that activates the main manu
    this.main();
  }// end default constructor
  
  /**
   * Run the main
   */
  public static void main(String[] args)
  {
    
    // Run the splash screen
    splash = new SplashScreen(5000);
    splash.showSplashAndExit();
    
    // Assigns an instance of this class to core
    core = new Main();
  }// end main method
  
  /**
   * Retrieves several game assets and starts the game.
   * @return void
   * */
  public void startGame()
  {
    // Start from level 1
    Destructor.rectBrick_List = file.rectLevel("level1.txt", 1);
    
    // Play Campaign Game
    Destructor.campaign = true;
    Destructor.currentLevel = 1;
    
    // Reset the Player Class and the scoreboard
    Destructor.player.setDefault();
    Destructor.lblLives.setText("Pads Left : " + Destructor.player.getLives());
    Destructor.lblScore.setText("Score : " + Destructor.player.getPoints());
    
    // Reset the slider
    Destructor.slider.setDefault();
    
    // Start the game
    Destructor.setDefault();
    Destructor.activate();
  }// end startGame()
  
  
  /*****************************
    * Activate Methods
    * ***************************/ 
  
  /**
   * Calls the activate instance method in menu to make the MainMenu visible.
   * @return void
   * */
  public void main()
  {
    menu.activate();
  }// end main()
  
  /**
   * Calls the activate instance method in mLevel to make the LevelMenu visible.
   * @return void
   * */
  public void level()
  {
    mLevel.activate();
  }// end level()
  
  /**
   * Calls the activate instance method in mScore to make the ScoreMenu visible.
   * @return void
   * */
  public void score()
  {
    mScore.activate();
  }// end score()
  
  /**
   * Calls the activate instance method in mSetting to make the SettingMenu visible.
   * @return void
   * */
  public void setting()
  {
    mSetting.activate();
  }// end setting()
  
  /**
   * Calls the activate instance method in mHow to make HowMenu visible.
   * @return void
   * */
  public void howTo()
  {
    mHow.activate();
  }// end howTo()
  
  /**
   * Calls the activate instance method in mCredit to make the CreditMenu visible.
   * @return void
   * */
  public void credit()
  {
    mCredit.activate();
  }// end credit()
  
  /**
   * Calls the activate instance method in mControl to make the ControlMenu visible.
   * @return void
   * */
  public void control()
  {
    mControl.activate();
  }// end control()

  /**
   * Calls the activate instance method in mEnd to make the EndMenu visible.
   * @return void
   * */
  public void end()
  {
    mEnd.activate();
  }// end end()
  
}// end Main Class


