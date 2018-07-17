//#Zhaoyang

// Import required packages

package ashish.hattimare;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Score
{
  
  
  /**************************
    * Instance Variables
    * **********************/
  
  // Stores names and scores, name in one array corresponds to the score in the other
  private ArrayList<Integer> scoreNum;
  private ArrayList<String> scoreName;
  
  // Keeps track of which line the file reader is on
  private String currentLine;
  
  // Determines whether the file reader is reading a name or a score
  // (scores and names are placed on one line seperated by a space)
  private boolean readName = true;
  
  // Name and score of the current player
  private String name = "";
  private String scoreStr = "";
  
  // Length of a name, restricted to 15 letters
  private int len;
  
  
  /**************************
    * Constructor
    * **********************/
  
  /**
   * Default Constructor: Constructs a Score and creates the two arraylists
   * used for storing names and scores
   *
   * */
  public Score()
  {
    scoreNum = new ArrayList<Integer>();
    scoreName = new ArrayList<String>();
  }// end default constructor
  
  
  /**************************
    * Get Methods
    * **********************/
  
  /**
   * Gets the array of scores
   * @return ArrayList scoreNum - array of all the scores read from text file
   * */
  public ArrayList<Integer> getScoreNum()
  {
    return scoreNum;
  }// end getScoreNum
  
  /**
   * Gets the array of names
   * @return ArrayList scoreName - array of all the names read from text file
   * */
  public ArrayList<String> getScoreName()
  {
    return scoreName;
  }// end getScoreName
  
  
  /**************************
    * Instance Methods
    * **********************/
  
  /**
   * Adds the name and score to arraylist, after first determining if the name
   * is short enough (under 15 characters), and changing all spaces to underscores
   * 
   * @param String name - the name of the player
   * @param int score - the score o fthe player
   * @return void
   * */
  public void addScore(String name, int score)
  {
    // Cuts off the name if it is over 15 characters long
    if (name.length() >= 15)
    {
      len = 15;
    }
    else
    {
      len = name.length();
    }// end if
    
    // Replaces all spaces with underscores
    name = (name.replaceAll(" ", "_")).substring(0, len);
    
    // Adds the name and score to their respective arrays
    scoreNum.add(score);
    scoreName.add(name);
    
    // Sorts the arrays
    this.sortScore();
    
  }// end addScore
  
  //#read
  /**
   * Reads the score text file and then adds the contents to the arrays, calls
   * addScore to add the name and score to teh arrays
   *
   * @return void
   * */
  public void readScore()
  {
    // Try reading from the text file
    try
    {
      // Assigns a file to scanner
      Scanner scanFile = new Scanner(new File("score.txt"));
      
      // Continues while there are lines to read
      while (true)
      {
        // COntinues reading if there are morelines
        if (scanFile.hasNextLine())
        {
          // Resets variables
          readName = true;
          name = "";
          scoreStr = "";
          
          // Takes a line from the text file
          currentLine = scanFile.nextLine();
          
          // Goes through all the characters in the line of teh text file
          for (int i = 0; i < currentLine.length(); i++)
          {
            // Reads the name, changes to reading score when it encounters a space
            if (currentLine.charAt(i) == ' ')
            {
              readName = false;
            } 
            else if (readName)
            {
              name = name + currentLine.charAt(i);
            } 
            else
            {
              scoreStr = scoreStr + currentLine.charAt(i);
            }// end if
          }// end for
          
          // Calls addScore to add the name and score to the arrays
          addScore(name, Integer.valueOf(scoreStr));
        }
        else
        {
          // Closes the file and break the while loop
          scanFile.close();
          break;
        }// end if
      }// end while
    } 
    catch (IOException e)
    {
      e.printStackTrace();
    }// end try/catch
  }// end readScore
  
  //#save
  /**
   * Writes the contents of the arrays to a text file
   * @return void
   * */
  public void writeScore()
  {
    // Try to write to teh specified file
    try
    {
      // Create a file writer and tells it to write to the specified file
      FileWriter writeFile = new FileWriter("score.txt");
      PrintWriter writeTo = new PrintWriter(writeFile);
      
      // Goes through the Score array and writes each (in order) to the
      // text file, in the format (name score)
      for (int i = 0; i < scoreNum.size(); i++)
      {
        writeTo.println(scoreName.get(i) + " " + scoreNum.get(i));
      }// end for
      
      // Closes the file
      writeFile.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }// end try/catch
    
  }// end writeScore
  
  //#alg
  /**
   * Sorts the arrays everytime an element is added
   * @return void
   * */
  public void sortScore()
  {
    // Starts from the last index position, where the new element has been added
    int currentPos = scoreNum.size() - 1;
    
    // The name and score that has been added
    int currentNum;
    String currentName;
    
    // Continues while the score is not in the right position
    while (true)
    {
      // The score one index position below is greater then the new score,
      // or the score is at the bottom of the array
      if (currentPos != 0
            && scoreNum.get(currentPos) < scoreNum.get(currentPos - 1))
      {
        // Stores the current name and score
        currentNum = scoreNum.get(currentPos);
        currentName = scoreName.get(currentPos);
        
        // Switches the current score with the next score in teh array
        scoreNum.set(currentPos, scoreNum.get(currentPos - 1));
        scoreNum.set(currentPos - 1, currentNum);
        
        // Switches the name, this is so the name follows the score and
        // they are at the same position in their respective arrays
        scoreName.set(currentPos, scoreName.get(currentPos - 1));
        scoreName.set(currentPos - 1, currentName);
        
        // Changes teh current position of teh newly added elements
        currentPos--;
      }
      else
      {
        //Exit the loop
        break;
      }// end if
    }// end while
  }// end sortScore
  
}// end Score Class
