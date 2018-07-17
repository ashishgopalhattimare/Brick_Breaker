//#Ashish

//Import required packages

package ashish.hattimare;

import java.awt.Graphics;
import java.util.ArrayList;

public class Fire_Controller
{
  
  /*****************************
    * Instance Variables
    * **************************/
  
  // Create an arrayList that stores the fireBall objects from the FireBalls Class
  private ArrayList<FireBalls> fireList;
  
  // Create a temporary FireBall class
  FireBalls TempFire;
  
  // Get access to the Slider class
  Slider slider;
   
  /*****************************
    * Class Variables
    ****************************/
  
  //Boolean stores whether or not fire is enabled
  public static boolean fireActivate;

  /*****************************
    * Constructors
    ****************************/
  
  /**
   * Default Constructor:
   * Creates an instance of this class, assigns teh slider to this class, and creates a list of fireballs
   * @param Slider - Slider object the fire is launched from
   **/
  public Fire_Controller(Slider slider)
  {
    this.slider  = slider;
    fireActivate = false;
    
    this.fireList = new ArrayList<FireBalls>();
  }// end Fire_Controller (Slider) constructor
    
  /*****************************
    * Get Methods
    ****************************/
  
  /**
   * Gets array of FireBalls
   * @param none
   * @return ArrayList<FireBalls> - an array storing all of the FireBalls objects
   **/
  public ArrayList<FireBalls> getList()
  {
    return this.fireList;
  }// end getList
  
  /*****************************
    * Set Methods
    ****************************/
  
  /**
   * Erases all fireballs from the fireball list
   * @param none
   * @return void
   **/
  public void setDefault()
  {
    this.fireList.clear();
  }// end setDefault
  
  /*****************************
    * Instance Methods
    * **************************/
  
  /**
   * Moves all the fireballs in the list, and removes those that are off screen
   * @param none
   * @return void
   **/
  public void update()
  {
    //Iterates through the array of fireballs and modifies each
    for (int i = 0; i < fireList.size(); i++)
    {
      TempFire = fireList.get(i);
      
      //Remove the fireball if it is off screen
      if (TempFire.getY() < 0)
      {
        removeFire(TempFire);
      }// end if
      
      TempFire.update();   
    }// end for
  }//end update

  /**
   * Goes through teh array and calls drawFir in FireBalls on each to draw the fireballs
   * @param g - Graphics required to draw the fireball
   * @return void
   */
  public void draw(Graphics g)
  {
    // Iterates through teh fireballs array and draws each
    for (int i = 0; i < fireList.size(); i++)
    {
      TempFire = fireList.get(i);
      TempFire.drawFire(g);
    }// end for
  }// end draw(Graphics)

  /**
   * Adds a newly created fireball to the fireball array, adn play the associated sound
   * @param FireBalls - a FireBalls object, readied to be added to teh array
   * @return void
   */
  public void addFire(FireBalls fire)
  {
    Sound.play("shoot.wav");
    this.fireList.add(fire);
  }// end addFire(FireBalls)
  
  /**
   * Removes the given fireball from the fireball arrray
   * @param FireBalls - a FireBalls object, her to be removed from the array
   * @return void
   */
  public void removeFire(FireBalls fire)
  {
    this.fireList.remove(this.fireList.indexOf(fire));
  }// end removeFire(FireBalls)
  
}// end Fire_Controller
