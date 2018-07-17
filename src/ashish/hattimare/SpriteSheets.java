//#Ashish

// Import all the required packages

package ashish.hattimare;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class SpriteSheets
{
  
  
  /**************************
    * Instance Variables
    * **********************/
  
  // Buffered Images used for the spriteSheet
  private static BufferedImage brickSheet, ballSheet, fireSheet, buttonSheet, titleSheet, powerSheet, levelSheet;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor
   * Load all the sprite sheets
   * */
  public SpriteSheets()
  {
    // Attempt to import all the SpriteSheets into the game
    try
    {
      brickSheet  = ImageIO.read(new File("brickSheet.jpg"));
      ballSheet   = ImageIO.read(new File("ballSheet.jpg"));
      fireSheet   = ImageIO.read(new File("fireSheet.jpg"));
      buttonSheet = ImageIO.read(new File("buttonSheet.jpg"));
      titleSheet  = ImageIO.read(new File("titleSheet.jpg"));
      powerSheet  = ImageIO.read(new File("powerSheet.jpg"));
      levelSheet  = ImageIO.read(new File("levelSheet.jpg"));
    }
    catch(Exception e)
    {
      e.printStackTrace();
      
    }//end try / catch
  }// end SpriteSheets() constructor
  
  
  /**************************
    * Class Methods
    * **********************/
  
  /**
   * Get the brick image from the brick sprite
   * @param y - the intial y coodinate of the brick image
   * @return - crop the brick image and return it
   */
  public static BufferedImage brickCrop(int y)
  {
    return brickSheet.getSubimage(0, y, 67, 31);
  }// end brickCrops(int)
  
  /**
   * Get the ball image from the ball sprite
   * @param y - the intial y coodinate of the ball image
   * @return - crop the ball image and return it
   */
  public static BufferedImage ballCrop(int y)
  {
    return ballSheet.getSubimage(0, y, 100, 100);
  }// end ballCrops(int)
  
  /**
   * Get the fire image from the fire sprite
   * @param y - the intial y coodinate of the fire image
   * @return - fire image
   */
  public static BufferedImage fireCrop(int y)
  {
    return fireSheet.getSubimage(0, y, 14, 14);
  }// end sliderCrops(int)
  
  /**
   * Get the button image from the button sprite
   * @param y - the intial y coodinate of the button image
   * @return - crop the button image and return it
   */
  public static BufferedImage buttonCrop(int y)
  {
    return buttonSheet.getSubimage(0, y, 200, 60);
  }// end buttonCrop(int)
  
  /**
   * Get the title image from the button sprite
   * @param y - the intial y coodinate of the title image
   * @return - crop the title image and return it
   */
  public static BufferedImage titleCrop(int y)
  {
    return titleSheet.getSubimage(0, y, 500, 180);
  }// end titleCrop(int)
  
  /**
   * Get the title image from the power sprite
   * @param y - the intial y coodinate of the power image
   * @return - crop the power image and return it
   */
  public static BufferedImage powerCrop(int y)
  {
    return powerSheet.getSubimage(0, y, 32, 32);
  }// end powerCrop(int)
  
  /**
   * Get the level image from the level sprite
   * @param y - the intial y coodinate of the level image
   * @return - crop the level image and return it
   */
  public static BufferedImage levelCrop(int x, int y)
  {
    return levelSheet.getSubimage(x * 100, y * 75, 100, 75);
  }// end levelCrop(int)
  
}// end SpriteSheet Class
