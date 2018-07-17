//#Ashish

/**
 * Player Handler Class
 * */

package ashish.hattimare;

public class Player
{
  
  /**************************
    * Instance Variables
    * **********************/
  
  //Player's total lives
  private int lives;
  
  //Player's total points
  private int points;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor
   * */
  public Player()
  {
    this.setDefault();
  }// end Player constructor
  
  
  /**************************
    * Set Methods
    * **********************/
  
  /**
   * Default Player Setting
   */
  public void setDefault()
  {
    this.lives = 3;
    this.points = 0;
  }// end setDefault()
  
  /**
   * Set lives remaining for the player
   * @param lives - 
   */
  public void setLives(int lives)
  {
    this.lives = lives;
  }// end setLives(int)
  
  /**
   * Add points to the player's total points
   * @param points -  points that are to be added to the total points
   */
  public void setPoints(int points)
  {
    this.points = this.points + points;
    Destructor.lblScore.setText("Score : " + Destructor.player.getPoints());
  }// end setPoints(int)
  
  
  /**************************
    * Get Methods
    * **********************/
  
  /**
   * Get player's pads live remaining
   */
  public int getLives()
  {
    return this.lives;
  }// end getLives()
  
  /**
   * Get player's total points
   * @return
   */
  public int getPoints()
  {
    return this.points;
  }// end getPoints()
  
}// end Player Class
