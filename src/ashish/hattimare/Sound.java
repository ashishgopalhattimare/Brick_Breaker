//#Ashish

// Import all the required java packages

package ashish.hattimare;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
  
  
  /**************************
    * Instance Methods
    * **********************/
  
  // Component required to pal;
  public static AudioInputStream audioIn;
  
  //Any audio clip from the directory that has to be run
  public static Clip sound;
  
  
  /**************************
    * Constructor
    * **********************/
  
  public Sound() {}
  
  
  /**************************
    * Class Methods
    * **********************/
  
  /**
   * Pick Audio from the directory and play it
   * */
  public static void play(String filename)
  {
    
    // Attempt to play the audio
    try
    {
      audioIn = AudioSystem.getAudioInputStream(new File(filename));
      sound = AudioSystem.getClip();
      sound.open(audioIn);
      
      sound.start();
    }
    catch (Exception e) 
    {
      e.printStackTrace();
    }// end try/ catch
    
  }// end play(String)
  
}// end Sound Class
