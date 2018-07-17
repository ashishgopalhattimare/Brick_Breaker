//#Zhaoyang

/**
 * Level Handler Class
 * */

//Import required packages

package ashish.hattimare;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class FileControl
{
  
  /*****************************
    * Instance Variables
    * **************************/
  
  // ArrayList for the rectangular bricks
  public ArrayList<Rectangle_Bricks> rectList;
  
  // Health of the bricks depending on the level
  public int[] healthLevel1_5 =  { 50,  50,  50,  50, 100, 150, 1, 1};
  public int[] healthLevel6_10 = { 50, 100, 150, 200, 250, 300, 1, 1};
  
  // File Array that stores the text from the fiel being read
  private String fileArray;
  
  // Char temporarily stores the character found at a certain position of teh level text file
  private char fileChar;
  
  // Int variable temporarily stores position in health array of a brick
  private int healthIndex;
  
  // The initial x and y coordinate of the brick
  private int x, y, xTranslation;
  
  // The width and height of the brick
  private int xUnit, yUnit;
  
  /*****************************
    * Class Variables
    ****************************/
  
  //Total number of bricks
  public static int totalBricks;
  
  /*****************************
   * Constructors
   ****************************/
  
  /**
   * Default Constructor:
   * Constructs a file control object, sets the number of bricks to zero and creates the list of bricks
   **/
  public FileControl()
  {
    totalBricks = 0;
    this.rectList = new ArrayList<Rectangle_Bricks>();
    
  }//end default constructor
  
  /*****************************
    * Instance Methods
    * **************************/
  
  //#level
  /**
   * Determines the size of the brick based on level, then reads through a text file to generate the 
   * level based on the character it finds in each position of the text file.
   * @param String level - level file name
   * @paran int intLevel - level number
   * @return ArrayList<Rectangle_Bricks> - the arrayList for the rectangular bricks
   * */
  public ArrayList<Rectangle_Bricks> rectLevel(String level, int intLevel)
  {
    // Clears the current level array 
    this.rectList.clear();
    
    // Sets the number of brick currently present to zero
    totalBricks = 0;
    
    // Odd levels get square bricks
    if (intLevel % 2 != 0)
    {
      this.xUnit = 33;
      this.yUnit = 33;
      this.xTranslation = 0;
    }
    // Even levels get rectangular bricks
    else if (intLevel % 2 == 0)
    {
      this.xUnit = 45;
      this.yUnit = 25;
      this.xTranslation = 11;
    }// end if
    
    // Sets the position of the starting brick to the top left of the screen
    this.x = this.xTranslation;
    this.y = 0;
    
    // Attempt to read the level file
    try
    {
      // Creates a scanner to read the file with
      Scanner scanFile = new Scanner(new File(level));
      
      // Continues to read the file as long as there are lines remaining
      do
      {
        if (scanFile.hasNextLine())
        {
          // Retrieve the current line of the text file
          fileArray = scanFile.nextLine();
          
          // Iterate through the line character by character
          for (int i = 0; i < fileArray.length(); i++)
          {
            // Take sth echaracter at the current position of the line
            this.fileChar = fileArray.charAt(i);
            
            //Adds a brick tot eh brick list based on the character taken from the line of the text file
            if (this.fileChar == 's' || this.fileChar == 'S')
            {  
              // Adds a soft brick (silver colored) to the brick array at and assigns it the current x and y 
              // values for it's position
              
              // Index position 0 in the health array contains the number of hits this brick can take
              this.healthIndex = 0;
              
              // Increases the number of bricks by 1
              totalBricks++;
              
              // Depending on the level a different health index is used to determine the health of the brick
              // Assigns it the current x and y position, and it's height and width
              if (intLevel <= 5)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel1_5[this.healthIndex], "soft"));
              }
              else if (intLevel <= Destructor.LEVEL_END)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel6_10[this.healthIndex], "soft"));
              }// end if
            }
            else if (this.fileChar == 'r' || this.fileChar == 'R')
            {
              // Adds a red brick to the brick array at and assigns it the current x and y 
              // values for it's position
              
              // Index position 1 in the health array contains the number of hits this brick can take
              this.healthIndex = 1;
              
              // Increases the number of bricks by 1
              totalBricks++;
              
              // Depending on the level a different health index is used to determine the health of the brick
              // Assigns it the current x and y position, and it's height and width
              if (intLevel <= 5)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel1_5[this.healthIndex], "red"));
              }
              else if (intLevel <= Destructor.LEVEL_END)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel6_10[this.healthIndex], "red"));
              }// end if
            }// end if            
            else if (this.fileChar == 'o' || this.fileChar == 'O')
            {
              // Adds a orange brick to the brick array at and assigns it the current x and y 
              // values for it's position
              
              // Index position 2 in the health array contains the number of hits this brick can take
              this.healthIndex = 2;
              
              // Increases the number of bricks by 1
              totalBricks++;
              
              // Depending on the level a different health index is used to determine the health of the brick
              // Assigns it the current x and y position, and it's height and width
              if (intLevel <= 5)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel1_5[this.healthIndex], "orange"));
              }
              else if (intLevel <= Destructor.LEVEL_END)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel6_10[this.healthIndex], "orange"));
              }// end if
            }
            else if (this.fileChar == 'y' || this.fileChar == 'Y')
            {
              // Adds a yellow brick to the brick array at and assigns it the current x and y 
              // values for it's position
              
              // Index position 3 in the health array contains the number of hits this brick can take
              this.healthIndex = 3;
              
              // Increases the number of bricks by 1
              totalBricks++;
              
              // Depending on the level a different health index is used to determine the health of the brick
              // Assigns it the current x and y position, and it's height and width
              if (intLevel <= 5)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel1_5[this.healthIndex], "yellow"));
              }
              else if (intLevel <= Destructor.LEVEL_END)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel6_10[this.healthIndex], "yellow"));
              }// end if
            }
            else if (this.fileChar == 'b' || this.fileChar == 'B')
            {              
              // Adds a blue brick to the brick array at and assigns it the current x and y 
              // values for it's position, blue bricks move from side to side
              
              // Index position 4 in the health array contains the number of hits this brick can take
              this.healthIndex = 4;
              
              // Increases the number of bricks by 1
              totalBricks++;
              
              // Depending on the level a different health index is used to determine the health of the brick
              // Assigns it the current x and y position, and it's height and width
              if (intLevel <= 5)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel1_5[this.healthIndex], "blue"));
              }
              else if (intLevel <= Destructor.LEVEL_END)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel6_10[this.healthIndex], "blue"));
              }// end if
            }      
            else if (this.fileChar == 'p' || this.fileChar == 'P')
            {
              // Adds a purple brick to the brick array at and assigns it the current x and y 
              // values for it's position, purple bricks move from side to side
              
              // Index position 5 in the health array contains the number of hits this brick can take
              this.healthIndex = 5;
              
              // Increases the number of bricks by 1              
              totalBricks++;
              
              // Depending on the level a different health index is used to determine the health of the brick
              // Assigns it the current x and y position, and it's height and width
              if (intLevel <= 5)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel1_5[this.healthIndex], "purple"));
              }
              else if (intLevel <= Destructor.LEVEL_END)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel6_10[this.healthIndex], "purple"));
              }// end if
            }
            else if (this.fileChar == 'm' || this.fileChar == 'M')
            {
              // Adds a metallic brick to the brick array at and assigns it the current x and y 
              // values for it's position, metallic bricks are indestructable
              // metallic bricks do not increase the brick count
              
              // Index position 6 in the health array contains the number of hits this brick can take
              this.healthIndex = 6;
              
              // Depending on the level a different health index is used to determine the health of the brick
              // Assigns it the current x and y position, and it's height and width
              if (intLevel <= 5)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel1_5[this.healthIndex], "metal"));
              }
              else if (intLevel <= Destructor.LEVEL_END)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel6_10[this.healthIndex], "metal"));
              }// end if
            }     
            else if(this.fileChar == 'n' || this.fileChar == 'N')
            {
              // Adds a moving metallic brick to the brick array at and assigns it the current x and y 
              // values for it's position, moving metallic bricks are indestructable and move
              // metallic bricks do not increase the brick count
              
              // Index position 7 in the health array contains the number of hits this brick can take
              this.healthIndex = 7;
              
              // Depending on the level a different health index is used to determine the health of the brick
              // Assigns it the current x and y position, and it's height and width
              if(intLevel <= 5)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel1_5[this.healthIndex], "metal"));
              }
              else if(intLevel <= Destructor.LEVEL_END)
              {
                this.rectList.add(new Rectangle_Bricks(x + 1, y + 1, this.xUnit, this.yUnit, this.healthIndex, 
                                                       this.healthLevel6_10[this.healthIndex], "metal"));
              }// end if
            }// end if
            
            // Increase the x posion so that the next brick is placed next to the current brick
            this.x += (this.xUnit + 1);
            
          }// end for
        }// end if
        
        else
        {
          // Closes the scanner and exits the loop
          scanFile.close();
          break;
        }// end if
        
        //Resets the x position to 0 and increases the y position, prepares to read the next line in teh text file
        this.y += (this.yUnit + 1);
        this.x = this.xTranslation;
        
      } while (true); // end do while      
    }    
    catch (Exception e)
    {
      e.printStackTrace();
    }// end try/catch
    
    // Update the level and totalbricks label in the game
    Destructor.lblLevel.setText("Level " + intLevel);
    Destructor.lblBricks.setText("Bricks Left : " + totalBricks);
    
    // Return the finished brick list
    return this.rectList;
    
  }// end rectLevel(String, int)
  
}// end FileControl
